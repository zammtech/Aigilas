package com.spx.io;import com.xna.wrapper.*;import java.util.*;import com.lidgren.wrapper.*;import com.spx.devtools.*;import com.spx.core.*;

    public class Server
    {
        public static final String ConnectionName = "Aigilas";   
        private static Server __instance;
        private static boolean __otherServerExists;        

        public static Server Get()
        {
            if (__instance == null && !__otherServerExists)
            {
                __instance = new Server();
            }
            return __instance;
        }

        private NetIncomingMessage _message;
        private NetPeerConfiguration _config;
        private NetServer _server;

        private HashMap<Integer, HashMap<Integer, Boolean>> _playerStatus = new HashMap<Integer, HashMap<Integer, Boolean>>();
        private int _rngSeed = Environment.TickCount();

        private Server()
        {
            try
            {
                _config = new NetPeerConfiguration(ConnectionName);                _config.Port = Settings.Get().GetPort();
                _config.MaximumConnections = 20;
                _config.EnableMessageType(NetIncomingMessageType.ConnectionApproval);
                _server = new NetServer(_config);
                _server.Start();
                for (int ii = 0; ii < MessageContents.PlayerMax; ii++)
                {
                    _playerStatus.put(ii,new HashMap<Integer, Boolean>());
                    for (int jj = 0; jj < MessageContents.CommandMax; jj++)
                    {
                        _playerStatus.get(ii).put(jj,false);
                    }
                }
                __otherServerExists = false;
                Console.WriteLine("Spinning up a server instance");
            }
            catch (Exception hide)
            {
                __otherServerExists = true;
                Console.WriteLine("SERVER:   Failure to start. If this isn't the host, then this message is harmless.");
                DevConsole.Get().Add("SERVER:   Failure to start. If this isn't the host, then this message is harmless.");
            }
        }

        private MessageContents _contents = MessageContents.Empty();
        private Integer _turnCount = 0;
        private boolean[] _readyCheckIn = { true, true, true, true};
        private HashMap<NetConnection, Integer> _addressToIndexMap = new HashMap<NetConnection, Integer>();
        public void Update()
        {
            while((_message = _server.ReadMessage()) != null)
            {                
                switch (_message.MessageType)
                {
                    case ConnectionApproval:
                        Console.WriteLine("SERVER:   New client connection");
                        _message.SenderConnection.Approve();
					try {						Thread.sleep(100);					} catch (InterruptedException e) {						// TODO Auto-generated catch block						e.printStackTrace();					}
                        InitPlayer(_server.ConnectionsCount - 1, 0);
                        Reply(MessageContents.CreateInit(_server.ConnectionsCount - 1, _rngSeed), _message.SenderConnection);
                        _addressToIndexMap.put(_message.SenderConnection, _server.ConnectionsCount - 1);
                        if (Settings.Get().GetServerVerbose()) Console.WriteLine("SERVER:   Accepted new connection");
                        _turnCount = 0;
					try {						Thread.sleep(100);					} catch (InterruptedException e) {						// TODO Auto-generated catch block						e.printStackTrace();					}
                        break;
                    case Data:
                        _contents.Deserialize(_message);
                        switch (_contents.MessageType)
                        {
                            case MessageTypes.CHECK_STATE:
                                InitPlayer(_contents.PlayerIndex, _contents.Command);
                                _contents.IsActive = _playerStatus.get(_contents.PlayerIndex).get(_contents.Command);                                
                                if (Settings.Get().GetServerVerbose()) Console.WriteLine("SERVER:   Check extends  CMD({1}) PI({0}) AC({2})", _contents.PlayerIndex, _contents.Command, _playerStatus.get(_contents.PlayerIndex).get(_contents.Command));
                                Reply(_contents, _message.SenderConnection);
                                break;
                            case MessageTypes.MOVEMENT:                                
                                InitPlayer(_contents.PlayerIndex, _contents.Command);
                                _playerStatus.get(_contents.PlayerIndex).put((int)_contents.Command,_contents.IsActive);
                                if (Settings.Get().GetServerVerbose()) Console.WriteLine("SERVER:   Moves extends  CMD({1}) PI({0}) AC({2})", _contents.PlayerIndex, _contents.Command, _contents.IsActive);
                                break;
                            case MessageTypes.START_GAME:
                                Console.WriteLine("SERVER:   Announcing game commencement.");
                                Announce(_contents);                                
                                break;
                            case MessageTypes.PLAYER_COUNT:
                                if (Settings.Get().GetServerVerbose()) Console.WriteLine("SERVER:   PLAYER COUNT");
                                Reply(MessageContents.CreatePlayerCount(_server.ConnectionsCount), _message.SenderConnection);
                                break;
                            case MessageTypes.READY_FOR_NEXT_TURN:
                                if (Settings.Get().GetServerVerbose()) Console.WriteLine("SERVER:   Received ready signal from client");
                                _readyCheckIn[_addressToIndexMap.get(_message.SenderConnection)] = true;                                
                                break;
                            case MessageTypes.HEART_BEAT:
                            	_readyCheckIn[_addressToIndexMap.get(_message.SenderConnection)] = true;
                                break;
                            default:
                                if (Settings.Get().GetServerVerbose()) Console.WriteLine("SERVER:   Unknown message");
                                break;
                        }
                        break;
                    default:
                        //Console.WriteLine("SERVER:  An unhandled MessageType was received extends  " + _message.ReadString());
                        break;
                }
                _server.Recycle(_message);
            }
            int readyCount = 0;
            for (int ii = 0; ii < _readyCheckIn.length; ii++)
            {
                readyCount += _readyCheckIn[ii] ? 1 : 0;
            }
            if (readyCount >= _server.ConnectionsCount)
            {
                if (Settings.Get().GetServerVerbose()) Console.WriteLine("SERVER:   Announcing player input status.");
                Announce(MessageContents.CreatePlayerState(_playerStatus, _turnCount++));
                for (int ii = 0; ii < _readyCheckIn.length; ii++)
                {
                    _readyCheckIn[ii] = false;
                }
            }
        }

        private void InitPlayer(int playerIndex, int command)
        {
            if (!_playerStatus.containsKey(playerIndex))
            {
                _playerStatus.put(playerIndex, new HashMap<Integer, Boolean>());
            }
            if (!_playerStatus.get(playerIndex).containsKey(command))
            {
                _playerStatus.get(playerIndex).put(command, false);
            }
        }

        private NetOutgoingMessage _announcement;
        private void Announce(MessageContents contents)
        {
            if (_server.ConnectionsCount > 0)
            {
                _announcement = _server.CreateMessage(MessageContents.ByteCount);
                contents.Serialize(_announcement);
                _server.SendMessage(_announcement, _server.Connections, NetDeliveryMethod.ReliableOrdered, 0);
            }
        }

        private NetOutgoingMessage _reply;
        private void Reply(MessageContents contents, NetConnection target)
        {
            _reply = _server.CreateMessage(MessageContents.ByteCount);
            contents.Serialize(_reply);
            _server.SendMessage(_reply, target, NetDeliveryMethod.ReliableOrdered, 0);
        }

        public boolean IsOnlyInstance()
        {
            return !__otherServerExists;
        }

        public void Close()
        {
            _server.Shutdown("SERVER: Shutting down");
        }
    }

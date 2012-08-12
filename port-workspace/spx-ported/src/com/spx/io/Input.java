package com.spx.io;import com.xna.wrapper.*;import java.util.*;import com.spx.devtools.*;
    public class Input
    {        
        //Maps a playerId to a context
        private static HashMap<Integer, Integer> __contexts;

        //Lists what commands are locked for a given player
        private static List<CommandLock> __locks = new ArrayList<CommandLock>(); 

        //The commands that cannot be used by simply holding down the command's input depending on the given context
        private static HashMap<Integer, Integer> __lockOnPress = new HashMap<Integer, Integer>();
        private static HashMap<Integer, Keys> _keyboardMapping = new HashMap<Integer, Keys>();
        private static HashMap<Integer, Buttons> _gamePadMapping = new HashMap<Integer, Buttons>();
        private static HashMap<Integer, Boolean> __inputs = new HashMap<Integer, Boolean>();
        private static boolean __isInputActive = false;

        public static void Setup(IInputInitializer initializer)        {        __contexts = new HashMap<Integer, Integer>();        __contexts.put(0, Contexts.Free);        __contexts.put(1, Contexts.Free);        __contexts.put(2, Contexts.Free);        __contexts.put(3, Contexts.Free);                
            for (CommandDefinition command:initializer.GetCommands())
            {
                _keyboardMapping.put(command.Command, command.Keyboard);
                _gamePadMapping.put(command.Command, command.Gamepad);
                if (command.LockContext >= 0)
                {
                    __lockOnPress.put(command.Command, command.LockContext);
                }
            }
        }

        public static boolean DetectState(int command, int playerIndex)
        {
            return GamePad.GetState(PlayerIndex.values()[playerIndex]).IsButtonDown(_gamePadMapping.get(command))
            ||
            (playerIndex == Client.Get().GetFirstPlayerIndex() && Keyboard.GetState().IsKeyDown(_keyboardMapping.get(command)));
        }

        private static boolean IsDown(int command, int playerIndex)
        {
            return Client.Get().IsActive(command, playerIndex);            
        }
        public static boolean IsActive(int command, int playerIndex)        {            return IsActive(command,playerIndex,false);        }        
        public static boolean IsActive(int command, int playerIndex,boolean failIfLocked)
        {
            __isInputActive = IsDown(command,playerIndex);
            if (!__isInputActive && ShouldLock(command,playerIndex))
            {
                Unlock(command, playerIndex);
            }

            if (IsLocked(command, playerIndex) && failIfLocked)
            {
                return false;
            }

            if (__isInputActive && ShouldLock(command,playerIndex))
            {
                Lock(command,playerIndex);
            }

            return __isInputActive;
        }

        //If the key is marked to be locked on press and its lock context is currently inactive
        private static boolean ShouldLock(int command,int playerIndex)
        {
            for(int key:__lockOnPress.keySet())
            {
                if (key == command)
                {
                    if (__lockOnPress.get(key) == __contexts.get(playerIndex) ||
                        (__lockOnPress.get(key) == Contexts.Nonfree && __contexts.get(playerIndex) != Contexts.Free) ||
                        __lockOnPress.get(key) == Contexts.All)
                    {
                        return true;
                    }
                }
            }
            return false;
        }

        public static void SetContext(int context,int playerIndex)
        {
            __contexts.put(playerIndex,context);
        }
        
        public static boolean IsContext(int context,int playerIndex)
        {
            return __contexts.get(playerIndex) == context;
        }

        public static boolean IsLocked(int command,int playerIndex)
        {
            for (CommandLock pair:__locks)
            {
                if (pair.Command == command && pair.PlayerIndex == playerIndex)
                {
                    return true;
                }
            }
            return false;
        }

        public static void Lock(int command,int playerIndex)
        {
            __locks.add(new CommandLock(command,playerIndex));
        }

        public static void Unlock(int command, int playerIndex)
        {
            for(int ii = 0;ii<__locks.size();ii++)
            {
                if(__locks.get(ii).Command==command && __locks.get(ii).PlayerIndex==playerIndex)
                {
                    __locks.remove(__locks.get(ii));
                    ii--;
                }
            }
        }        

        public static void Update()
        {
            //Remove command locks if the associated key/button isn't being pressed
            for(int ii = 0;ii<__locks.size();ii++)
            {
                if (!IsDown(__locks.get(ii).Command, __locks.get(ii).PlayerIndex))
                {
                    __locks.remove(__locks.get(ii));
                    ii--;
                }
            }
            
            for (int command:_keyboardMapping.keySet())
            {
                Client.Get().SetState(command, Client.Get().GetFirstPlayerIndex(), DetectState(command, Client.Get().GetFirstPlayerIndex()));
            }
        }        
    }

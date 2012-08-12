package com.spx.io;

public class CmtString
{
    public static String Get(byte messageType)
    {
        switch (messageType)
        {
            case MessageTypes.CONNECT: return "CONNECT";
            case MessageTypes.MOVEMENT: return "MOVEMENT";
            case MessageTypes.START_GAME: return "START_GAME";
            case MessageTypes.CHECK_STATE: return "CHECK_STATE";
            case MessageTypes.PLAYER_COUNT: return "PLAYER_COUNT";
            case MessageTypes.SYNC_STATE: return "SYNC_STATE";
            case MessageTypes.READY_FOR_NEXT_TURN: return "READY_FOR_NEXT_TURN";
            case MessageTypes.HEART_BEAT: return "HEART_BEAT";
        }
        return "INVALID MESSAGE TYPE";
    }
}

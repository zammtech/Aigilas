package com.spx.io;import com.xna.wrapper.*;import java.util.*;    public class MessageTypes
    {
        public static final byte CONNECT = 1;
        public static final byte MOVEMENT = 2;
        public static final byte START_GAME = 3;
        public static final byte CHECK_STATE = 4;
        public static final byte PLAYER_COUNT = 5;
        public static final byte SYNC_STATE = 6;
        public static final byte READY_FOR_NEXT_TURN = 7;
        public static final byte HEART_BEAT = 8;
    }

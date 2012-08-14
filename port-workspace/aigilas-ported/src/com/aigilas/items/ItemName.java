package com.aigilas.items;import com.xna.wrapper.*;import java.util.*;    public class ItemName
    {
        public static final int NULL = 0;
        public static final int Sword = 1;
        public static final int Pants = 2;
        public static final int Dagger = 3;
        public static final int Shield = 4;
        public static final int Bow = 5;
        public static final int Arrow = 6;
        public static final int Staff = 7;
        public static final int Hood = 8;
        public static final int Shirt = 9;
        public static final int Flak = 10;

        public static int[] Values =
        {
            NULL,
            Sword,
            Pants,
            Dagger,
            Shield,
            Bow,
            Arrow,
            Staff,
            Hood,
            Shirt,
            Flak
        };

        public static HashMap<Integer, String> Names = new HashMap<Integer, String>();        static{
            Names.put(NULL,"NONE");
            Names.put(Sword,"Sword");
            Names.put(Pants,"Pants");
            Names.put(Dagger,"Dagger");
            Names.put(Shield,"Shield");
            Names.put(Bow,"Bow");
            Names.put(Arrow,"Arrow");
            Names.put(Staff,"Staff");
            Names.put(Hood,"Hood");
            Names.put(Shirt,"Shirt");
            Names.put(Flak,"Flak");
        };
    }

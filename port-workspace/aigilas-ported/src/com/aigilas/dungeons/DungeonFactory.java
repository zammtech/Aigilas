package com.aigilas.dungeons;
    public class DungeonFactory
    {
        public static int BlocksHigh = 20;
        public static int BlocksWide = 30;

        private static int __floorCount = 0;

        private static HashMap<Integer, DungeonSet> _world = new HashMap<Integer, DungeonSet>();
        private static List<Entity> _cache = new ArrayList<Entity>(); 

        public static void GetNextFloor() throws Exception
        {
            _world.get(Location.Depths).GotoNext();
        }

        public static boolean GetPreviousFloor() throws Exception
        {
            return _world.get(Location.Depths).GotoPrevious();
        }

        public static void AddToCache(Entity content)
        {
            _cache.add(content);
        }

        public static List<IEntity> FlushCache()
        {
            ArrayList<IEntity> result = new ArrayList<IEntity>(_cache);
            _cache.clear();
            return result;
        }

        public static void Start() throws Exception
        {           
            _world = new HashMap<Integer, DungeonSet>();
            _cache = new ArrayList<Entity>();
            _world.put(Location.Depths, new DungeonSet());            
            while (CreatureFactory.BossesRemaining() > 0)
            {
                GetNextFloor();
            }
            while (GetPreviousFloor()) { }            
        }

        public static int GetFloorCount()
        {
            return __floorCount;
        }

        public static void IncreaseFloorCount()
        {
            __floorCount++;
        }
    }   
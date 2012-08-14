package com.aigilas.items;
    public class Equipment
    {
        public static int ClassToSlot(int iClass)
        {
            switch(iClass)
            {
                case ItemClass.Melee_Weapon:
                    return ItemSlot.RIGHT_HAND;
                case ItemClass.Ranged_Weapon:
                    return ItemSlot.RIGHT_HAND;
                case ItemClass.Ranged_Ammo:
                    return ItemSlot.LEFT_HAND ;
                case ItemClass.Ring:
                    return ItemSlot.LEFT_FINGER;
                case ItemClass.Leggings: 
                    return ItemSlot.LEGS;
                case ItemClass.Torso_Garb:
                    return ItemSlot.TORSO;
                case ItemClass.Feet:
                    return ItemSlot.FEET;
                case ItemClass.Head_Gear:
                    return ItemSlot.HEAD;
                case ItemClass.Gloves:
                    return ItemSlot.HANDS;
                case ItemClass.Shield:
                    return ItemSlot.LEFT_HAND;
            }
            return ItemSlot.NULL;
        }

        private HashMap<Integer, GenericItem> _slots = new HashMap<Integer, GenericItem>();
        private ICreature _parent;


        public Equipment(ICreature owner)
        {
            _parent = owner;
        }

        public void Unequip(GenericItem item)
        {
            if (IsRegistered(item))
            {
                Unregister(item);
            }
        }

        public void Register(GenericItem item)
        {
            int itemSlot = ClassToSlot(item.GetItemClass());
            if(_slots.containsKey(itemSlot))
            {
                Unequip(_slots.get(itemSlot));
            }
                _slots.put(itemSlot, item);            
        }

        public void Unregister(GenericItem item)
        {
            int itemSlot = ClassToSlot(item.GetItemClass());
            if (_slots.containsKey(itemSlot))
            {
                _parent.PickupItem(_slots.get(itemSlot));
                _slots.remove(itemSlot);
            }
        }

        public boolean IsRegistered(GenericItem item)
        {
            int itemClass = ClassToSlot(item.GetItemClass());
            if(_slots.containsKey(itemClass))
            {
                return (item == _slots.get(itemClass)) ;
            }
            return false;
        }

        private float bonusSum;
        public float CalculateBonus(String stat)
        {
            bonusSum = 0;
            for (Integer slot:_slots.keySet())
            {
                bonusSum += _slots.get(slot).GetStatBonus(stat);
            }
            return bonusSum;
        }

        public HashMap<Integer,GenericItem> GetItems()
        {
            return _slots;
        }
    }
package com.aigilas.skills.impl;import com.aigilas.creatures.ICreature;import com.aigilas.creatures.StatType;import com.aigilas.entities.Elements;import com.aigilas.items.GenericItem;import com.aigilas.skills.AnimationType;import com.aigilas.skills.ISkill;import com.aigilas.skills.SkillId;    public class ThrowItemSkill  extends  ISkill    {        private float _itemStrength = 0;        public ThrowItemSkill()            { super(SkillId.THROW_ITEM, AnimationType.RANGED);            Add(Elements.AIR);            AddCost(StatType.MANA, 0);        }@Override        public  void Activate(ICreature source)        {            GenericItem item = source.DestroyRandomItemFromInventory();            if (item != null)            {                _itemStrength = item.Modifers.GetSum() * 3;                super.Activate(source);            }        }@Override        public  void Affect(ICreature target)        {            target.ApplyDamage(_itemStrength, _source);        }    }
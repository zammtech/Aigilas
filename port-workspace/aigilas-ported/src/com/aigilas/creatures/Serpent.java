package com.aigilas.creatures;
    public class Serpent  extends  AbstractCreature
    {
        public Serpent()
            {
            Strengths(StatType.HEALTH, StatType.HEALTH, StatType.HEALTH);
            _strategy = StrategyFactory.Create(Strategy.ConfusedAndDying,this);
        }
    }
package com.aigilas.creatures;import com.xna.wrapper.*;import java.util.*;import com.aigilas.skills.*;import com.spx.sprites.*;import com.aigilas.strategies.*;import com.aigilas.entities.*;import com.spx.entities.*;import com.spx.core.*;
    public class IceShard  extends  Minion
    {
        public IceShard()
            { super(AigilasActorType.MINION);            _strategy = StrategyFactory.Create(Strategy.MinionOneUse, this);
            Add(SkillId.ICE_SHARD);
            _composition.add(Elements.WATER);
        }
    }


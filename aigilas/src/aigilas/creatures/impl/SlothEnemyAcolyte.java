package aigilas.creatures.impl;

import aigilas.creatures.StatType;
import aigilas.entities.Elements;
import sps.bridge.ActorType;

public class SlothEnemyAcolyte extends BaseEnemy {
    public SlothEnemyAcolyte() {
        super(ActorType.SLOTH_ACOLYTE);
        Weaknesses(StatType.STRENGTH, StatType.HEALTH, StatType.MOVE_COOL_DOWN);
        Compose(Elements.EARTH);
    }
}
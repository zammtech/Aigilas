package aigilas.creatures.impl;

import aigilas.creatures.StatType;
import aigilas.entities.Elements;
import aigilas.management.SpriteType;
import aigilas.skills.SkillId;
import sps.bridge.ActorType;

public class Lust extends BaseEnemy {
    public Lust() {
        super(ActorType.LUST, SpriteType.LUST);
        Compose(Elements.FIRE);
        Strengths(StatType.STRENGTH, StatType.STRENGTH);
        add(SkillId.BRIMSTONE);
    }
}
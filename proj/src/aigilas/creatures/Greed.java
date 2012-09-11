package aigilas.creatures;

import spx.bridge.ActorType;
import aigilas.entities.Elements;
import aigilas.management.SpriteType;
import aigilas.skills.SkillId;

public class Greed extends AbstractCreature {
	public Greed() {
		super(ActorType.GREED, SpriteType.GREED);
		Compose(Elements.AIR);
		Strengths(StatType.STRENGTH, StatType.STRENGTH);
		Add(SkillId.BOIL);
	}
}
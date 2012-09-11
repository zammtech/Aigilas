package aigilas.skills.impl;

import spx.entities.Entity;
import aigilas.creatures.CreatureFactory;
import aigilas.creatures.StatType;
import aigilas.entities.Elements;
import aigilas.entities.SkillEffect;
import aigilas.skills.AnimationType;
import aigilas.skills.ISkill;
import aigilas.skills.SkillId;

public class DartTrapSkill extends ISkill {
	public DartTrapSkill()

	{
		super(SkillId.DART_TRAP, AnimationType.RANGED);

		AddCost(StatType.MANA, 10);
		Add(Elements.DARK);

	}

	@Override
	public void Cleanup(Entity target, SkillEffect source)

	{
		CreatureFactory.CreateMinion(_implementationId, _source, source, target.GetLocation());

	}

}
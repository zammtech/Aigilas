package aigilas.skills.impl;

import aigilas.creatures.ICreature;
import aigilas.creatures.StatType;
import aigilas.entities.Elements;
import aigilas.skills.AnimationType;
import aigilas.skills.ISkill;
import aigilas.skills.SkillId;
import aigilas.statuses.Status;
import aigilas.statuses.StatusFactory;

public class ElectrifySkill extends ISkill {
	public ElectrifySkill()

	{
		super(SkillId.ELECTRIFY, AnimationType.SELF);

		Add(Elements.AIR);
		AddCost(StatType.MANA, 10);

	}

	@Override
	public void Activate(ICreature source) {
		super.Activate(source);
		StatusFactory.Apply(source, Status.Electrify);

	}

}
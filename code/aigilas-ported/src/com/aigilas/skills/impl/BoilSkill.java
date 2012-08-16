package com.aigilas.skills.impl;import com.aigilas.creatures.ICreature;import com.aigilas.creatures.StatType;import com.aigilas.entities.Elements;import com.aigilas.skills.AnimationType;import com.aigilas.skills.ISkill;import com.aigilas.skills.SkillId;import com.aigilas.statuses.Status;import com.aigilas.statuses.StatusFactory;public class BoilSkill extends ISkill {	public BoilSkill()	{		super(SkillId.BOIL, AnimationType.SELF);		Add(Elements.AIR);		AddCost(StatType.MANA, 10);	}	@Override	public void Activate(ICreature source) {		StatusFactory.Apply(source, Status.Boil);	}}
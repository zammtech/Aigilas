package com.aigilas.skills.impl;import com.aigilas.creatures.ICreature;import com.aigilas.creatures.StatType;import com.aigilas.entities.Elements;import com.aigilas.skills.AnimationType;import com.aigilas.skills.ISkill;import com.aigilas.skills.SkillId;import com.aigilas.statuses.Status;public class SpeedUpSkill extends ISkill {	public SpeedUpSkill()	{		super(SkillId.SPEED_UP, AnimationType.SELF);		Add(Elements.WATER);		AddCost(StatType.MANA, 10);	}	@Override	public void Activate(ICreature source) {		super.Activate(source);		ApplyToPlayers(Status.SpeedUp);	}}
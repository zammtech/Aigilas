package com.aigilas.strategies.impl;import com.aigilas.creatures.ICreature;import com.aigilas.strategies.IStrategy;import com.aigilas.strategies.Strategy;public class MinionCloudStrategy extends IStrategy {	public MinionCloudStrategy(ICreature parent)	{		super(parent, Strategy.MinionCloud);		parent.SetSkillVector(parent.GetSkillVector());	}	@Override	public void Act() {		_parent.UseActiveSkill();		_parent.SetInactive();	}}
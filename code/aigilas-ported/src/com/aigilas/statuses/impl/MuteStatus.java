package com.aigilas.statuses.impl;import com.aigilas.creatures.CreatureAction;import com.aigilas.creatures.ICreature;import com.aigilas.statuses.IStatus;public class MuteStatus extends IStatus {	public MuteStatus(ICreature target)	{		super(target);		_prevents.add(CreatureAction.SkillUsage);	}}
package com.aigilas.statuses.impl;import com.aigilas.creatures.CreatureFactory;import com.aigilas.creatures.ICreature;import com.aigilas.skills.SkillId;import com.aigilas.statuses.IStatus;public class ToxicStatus extends IStatus {	public ToxicStatus(ICreature target)	{		super(target);		_target = target;	}	@Override	public void Act() {		try {			CreatureFactory.CreateMinion(SkillId.PLAGUE, _target, null,					_target.GetLocation());		} catch (Exception e) {			e.printStackTrace();		}	}}
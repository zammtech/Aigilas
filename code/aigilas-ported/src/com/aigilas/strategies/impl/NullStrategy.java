package com.aigilas.strategies.impl;import com.aigilas.creatures.ICreature;import com.aigilas.strategies.IStrategy;import com.aigilas.strategies.Strategy;public class NullStrategy extends IStrategy {	public NullStrategy(ICreature parent, int... targetTypes)	{		super(parent, Strategy.Null);		for (int targetType : targetTypes) {			_targets.AddTargetTypes(targetType);		}	}	@Override	public void Act() {	}}
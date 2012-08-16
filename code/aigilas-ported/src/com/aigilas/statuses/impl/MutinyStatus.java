package com.aigilas.statuses.impl;import com.aigilas.creatures.CreatureAction;import com.aigilas.creatures.ICreature;import com.aigilas.statuses.IStatus;import com.aigilas.strategies.Strategy;import com.aigilas.strategies.StrategyFactory;public class MutinyStatus extends IStatus {	private int previousStrategy;	public MutinyStatus(ICreature target) {		super(target);		_prevents.add(CreatureAction.WontHitNonTargets);	}	@Override	public void Setup() {		super.Setup();		previousStrategy = _target.GetStrategyId();		_target.SetStrategy(StrategyFactory.Create(Strategy.Mutiny, _target));	}	@Override	public void Cleanup() {		super.Cleanup();		_target.SetStrategy(StrategyFactory.Create(previousStrategy, _target));	}}
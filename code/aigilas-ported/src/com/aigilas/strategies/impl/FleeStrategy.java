package com.aigilas.strategies.impl;import com.aigilas.creatures.ICreature;import com.aigilas.strategies.IStrategy;import com.aigilas.strategies.Strategy;import com.spx.core.Point2;public class FleeStrategy extends IStrategy {	public FleeStrategy(ICreature parent, int... targetTypes)	{		super(parent, Strategy.Flee);		for (int targetType : targetTypes) {			_targets.AddTargetTypes(targetType);		}	}	private Point2 _transfer = new Point2(0, 0);	@Override	public void Act() {		if (AbleToMove()) {			_transfer = targetPath.GetNextMove();			if (_transfer != null) {				nextMove.Copy(_parent.GetLocation().Add(						_transfer.Minus(_parent.GetLocation()).Rotate180()));				_parent.MoveTo(nextMove);			}		}	}}
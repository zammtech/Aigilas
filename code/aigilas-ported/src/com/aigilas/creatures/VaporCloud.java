package com.aigilas.creatures;import com.aigilas.entities.Elements;import com.aigilas.skills.SkillId;import com.aigilas.strategies.Strategy;import com.aigilas.strategies.StrategyFactory;import com.spx.entities.EntityManager;import com.spx.entities.IActor;public class VaporCloud extends Minion {	private ICreature _host = null;	public VaporCloud() {		super(AigilasActorType.MINION);		_strategy = StrategyFactory.Create(Strategy.MinionCloud, this);		Add(SkillId.VAPOR_CLOUD);		_composition.add(Elements.WATER);	}	@Override	public void Update() {		super.Update();		if (_host == null) {			for (IActor creature : EntityManager.GetActorsAt(_location)) {				if (creature != this) {					_host = (ICreature) creature;				}			}			if (_host == null) {				SetInactive();			}		}		if (_host != null) {			SetLocation(_host.GetLocation());		}	}}
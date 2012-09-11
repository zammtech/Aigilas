package aigilas.reactions.impl;

import spx.entities.EntityManager;
import spx.entities.IActor;
import aigilas.creatures.ICreature;
import aigilas.reactions.IReaction;

public class ExplosionReaction implements IReaction {
	@Override
	public void Affect(ICreature target) {
		for (IActor creature : EntityManager.GetActorsSurrounding(target.GetLocation(), 2)) {
			((ICreature) creature).ApplyDamage(10);
		}
	}
}
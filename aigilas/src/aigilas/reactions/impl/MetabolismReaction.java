package aigilas.reactions.impl;

import aigilas.creatures.BaseCreature;
import aigilas.reactions.BaseReaction;
import aigilas.statuses.Status;
import aigilas.statuses.StatusFactory;

public class MetabolismReaction extends BaseReaction {
    @Override
    public void affect(BaseCreature target) {
        StatusFactory.apply(target, Status.PreventRegeneration);
    }
}

package aigilas.creatures.impl;

import aigilas.management.Common;
import sps.bridge.ActorTypes;

public class Greed extends BaseEnemy {
    public Greed() {
        super(ActorTypes.get(Common.Greed));
    }
}

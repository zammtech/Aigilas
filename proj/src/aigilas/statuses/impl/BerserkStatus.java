package aigilas.statuses.impl;

import aigilas.creatures.ICreature;
import aigilas.statuses.IStatus;
import aigilas.statuses.Status;
import aigilas.statuses.StatusComponent;

public class BerserkStatus extends IStatus {
	public BerserkStatus(ICreature target)

	{
		super(target);
		Add(Status.RandomBuff, StatusComponent.KillReward);
	}
}
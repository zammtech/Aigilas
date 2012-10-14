package aigilas.skills.impl;

import aigilas.creatures.BaseCreature;
import aigilas.skills.AnimationType;
import aigilas.skills.BaseSkill;
import aigilas.skills.SkillId;
import aigilas.statuses.Status;
import aigilas.statuses.StatusFactory;

public class ManaUpSkill extends BaseSkill {
    public ManaUpSkill()

    {
        super(SkillId.Mana_Up, AnimationType.SELF);


    }

    @Override
    public void activate(BaseCreature source) {
        super.activate(source);
        StatusFactory.apply(source, Status.ManaUp);

    }

}

package aigilas.skills.impl;

import aigilas.creatures.BaseCreature;
import aigilas.skills.AnimationType;
import aigilas.skills.BaseSkill;
import aigilas.skills.SkillId;

public class MagicMapSkill extends BaseSkill {
    public MagicMapSkill()

    {
        super(SkillId.Magic_Map, AnimationType.RANGED);


    }

    @Override
    public void affect(BaseCreature target)

    {

    }

}

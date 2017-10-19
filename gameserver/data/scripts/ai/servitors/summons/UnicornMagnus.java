package ai.servitors.summons;

import ai.base.summons.SummonPhysicalAttackerUse3Skill;
import l2p.gameserver.ai.base.RetailClass;
import l2p.gameserver.model.Summon;

/**
 * @author : Alice
 * @date : 29.03.17
 * @time : 13:26
 * <p/>
 */
@RetailClass(name = "unicorn_magnus", withSuffix = true)
public class UnicornMagnus extends SummonPhysicalAttackerUse3Skill {
    public UnicornMagnus(Summon actor)
    {
        super(actor);
        //actor::AddPetDefaultDesire_Follow(20.000000);
    }
}

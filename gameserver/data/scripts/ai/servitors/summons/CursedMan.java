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
@RetailClass(name = "cursed_man_", withSuffix = true)
public class CursedMan extends SummonPhysicalAttackerUse3Skill {
    public CursedMan(Summon actor)
    {
        super(actor);
        //actor::AddPetDefaultDesire_Follow(20.000000);
    }
}

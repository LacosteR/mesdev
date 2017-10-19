package ai.servitors.summons;

import ai.base.summons.SummonMagicalTankerUse3Skill;
import l2p.gameserver.ai.base.RetailClass;
import l2p.gameserver.model.Summon;

/**
 * @author : Alice
 * @date : 29.03.17
 * @time : 13:26
 * <p/>
 */
@RetailClass(name = "nightshade_", withSuffix = true)
public class Nightshade extends SummonMagicalTankerUse3Skill {
    public Nightshade(Summon actor)
    {
        super(actor);
        //actor::AddPetDefaultDesire_Follow(20.000000);
    }
}

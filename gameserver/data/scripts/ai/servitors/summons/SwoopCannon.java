package ai.servitors.summons;

import ai.base.summons.SummonSiegeCannon;
import l2p.gameserver.ai.base.RetailClass;
import l2p.gameserver.model.Summon;

/**
 * @author : Alice
 * @date : 29.03.17
 * @time : 13:26
 * <p/>
 */
@RetailClass(name = "swoop_cannon", withSuffix = true)
public class SwoopCannon extends SummonSiegeCannon {
    public SwoopCannon(Summon actor)
    {
        super(actor);
        //actor::AddPetDefaultDesire_Follow(20.000000);
    }
}

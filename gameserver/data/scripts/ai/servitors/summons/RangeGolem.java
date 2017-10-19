package ai.servitors.summons;

import ai.base.summons.SummonSiegeTank;
import l2p.gameserver.ai.base.RetailClass;
import l2p.gameserver.model.Summon;

/**
 * @author : Alice
 * @date : 29.03.17
 * @time : 13:26
 * <p/>
 */
@RetailClass(name = "range_golem", withSuffix = true)
public class RangeGolem extends SummonSiegeTank {
    public RangeGolem(Summon actor)
    {
        super(actor);
        //actor::AddPetDefaultDesire_Follow(20.000000);
    }
}

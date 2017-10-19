package ai.servitors.summons;

import ai.base.summons.SummonHeal;
import l2p.gameserver.ai.base.RetailClass;
import l2p.gameserver.model.Summon;

/**
 * @author : Alice
 * @date : 29.03.17
 * @time : 13:26
 * <p/>
 */
@RetailClass(name = "silver_cat_", withSuffix = true)
public class SilverCat extends SummonHeal {
    public SilverCat(Summon actor)
    {
        super(actor);
        //actor::AddPetDefaultDesire_Follow(20.000000);
    }
}

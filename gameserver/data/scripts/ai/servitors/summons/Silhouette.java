package ai.servitors.summons;

import ai.base.summons.SummonMagicalAttack;
import l2p.gameserver.ai.base.RetailClass;
import l2p.gameserver.model.Summon;

/**
 * @author : Alice
 * @date : 29.03.17
 * @time : 13:26
 * <p/>
 */
@RetailClass(name = "silhouette_", withSuffix = true)
public class Silhouette extends SummonMagicalAttack {
    public Silhouette(Summon actor)
    {
        super(actor);
        //actor::AddPetDefaultDesire_Follow(20.000000);
    }
}

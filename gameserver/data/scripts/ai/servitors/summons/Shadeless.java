package ai.servitors.summons;

import ai.base.summons.SummonMagicalAttack2;
import l2p.gameserver.ai.base.RetailClass;
import l2p.gameserver.model.Summon;

/**
 * @author : Alice
 * @date : 29.03.17
 * @time : 13:26
 * <p/>
 */
@RetailClass(name = "shadeless_", withSuffix = true)
public class Shadeless extends SummonMagicalAttack2 {
    public Shadeless(Summon actor)
    {
        super(actor);
        //actor::AddPetDefaultDesire_Follow(20.000000);
    }
}

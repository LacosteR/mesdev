package ai.servitors.summons;

import ai.base.summons.SummonActiveGrow;
import l2p.gameserver.ai.base.RetailClass;
import l2p.gameserver.model.Summon;

/**
 * @author : Alice
 * @date : 29.03.17
 * @time : 13:26
 * <p/>
 */
@RetailClass(name = "divine_beast", withSuffix = true)
public class DivineBeast extends SummonActiveGrow {
    public DivineBeast(Summon actor)
    {
        super(actor);
        //actor::AddPetDefaultDesire_Follow(20.000000);
    }
}

package ai.servitors.summons;

import ai.base.summons.PailakaPetAI;
import l2p.gameserver.ai.base.RetailClass;
import l2p.gameserver.model.Summon;

/**
 * @author : Alice
 * @date : 29.03.17
 * @time : 13:26
 * <p/>
 */
@RetailClass(name = "pailaka_summon_", withSuffix = true)
public class PailakaSummon extends PailakaPetAI {
    public PailakaSummon(Summon actor)
    {
        super(actor);
        //actor::AddPetDefaultDesire_Follow(20.000000);
    }
}

package ai.servitors.summons;

import ai.base.summons.SummonMagicalBufferUse3Skill;
import l2p.gameserver.ai.base.RetailClass;
import l2p.gameserver.model.Summon;

/**
 * @author : Alice
 * @date : 29.03.17
 * @time : 13:26
 * <p/>
 */
@RetailClass(name = "unicorn_seraphim_", withSuffix = true)
public class UnicornSeraphim extends SummonMagicalBufferUse3Skill {
    public UnicornSeraphim(Summon actor)
    {
        super(actor);
        //actor::AddPetDefaultDesire_Follow(20.000000);
    }
}

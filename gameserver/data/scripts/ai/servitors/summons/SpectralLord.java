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
@RetailClass(name = "spectral_lord", withSuffix = true)
public class SpectralLord extends SummonPhysicalAttackerUse3Skill {
    public SpectralLord(Summon actor)
    {
        super(actor);
        //actor::AddPetDefaultDesire_Follow(20.000000);
    }
}

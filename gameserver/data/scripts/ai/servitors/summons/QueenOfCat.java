package ai.servitors.summons;

import ai.base.summons.SummonPhysicalBufferUse3Skill;
import l2p.gameserver.ai.base.RetailClass;
import l2p.gameserver.model.Summon;

/**
 * @author : Alice
 * @date : 29.03.17
 * @time : 13:26
 * <p/>
 */
@RetailClass(name = "queen_of_cat_", withSuffix = true)
public class QueenOfCat extends SummonPhysicalBufferUse3Skill {
    public QueenOfCat(Summon actor)
    {
        super(actor);
        //actor::AddPetDefaultDesire_Follow(20.000000);
    }
}

package ai.base.summons;

import l2p.gameserver.ai.SummonAI;
import l2p.gameserver.model.Summon;

/**
 * @author : Alice
 * @date : 29.03.17
 * @time : 13:27
 * <p/>
 */
public class SummonPhysicalAttack extends SummonAI {
    public SummonPhysicalAttack(Summon actor)
    {
        super(actor);
        //actor::AddPetDefaultDesire_Follow(20.000000);
    }
    /*
    EventHandler NO_DESIRE()
    {
        actor::AddPetDefaultDesire_Follow(20.000000);
    } */

    @Override
    public void onEvtMenuSelected(int action_id) {
        super.onEvtMenuSelected(action_id);
         /*
         if( ask == 1 )
        {
        }
          */
    }
}

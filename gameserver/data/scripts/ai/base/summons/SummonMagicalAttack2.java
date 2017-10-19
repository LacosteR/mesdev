package ai.base.summons;

import l2p.gameserver.ai.SummonAI;
import l2p.gameserver.model.Creature;
import l2p.gameserver.model.Summon;

/**
 * @author : Alice
 * @date : 29.03.17
 * @time : 0:23
 * <p/>
 */
public class SummonMagicalAttack2 extends SummonAI {
    public SummonMagicalAttack2(Summon actor) {
        super(actor);
    }

    @Override
    public void onEvtMenuSelected(int action_id) {
        if (action_id == 36) {
            castSkill(summonAiComponent.getRangeDebuff(), (Creature) getMaster().getTarget());
        } else if (action_id == 39) {
            castSkill(summonAiComponent.getRangeDD(), (Creature) getMaster().getTarget());
        }
    }
}

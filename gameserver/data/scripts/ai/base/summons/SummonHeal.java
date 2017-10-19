package ai.base.summons;

import l2p.gameserver.ai.SummonAI;
import l2p.gameserver.ai.base.StepAI;
import l2p.gameserver.model.Summon;

/**
 * @author : Alice
 * @date : 28.03.17
 * @time : 23:55
 * <p/>
 */
public class SummonHeal extends SummonAI {
    public SummonHeal(Summon actor) {
        super(actor);
    }

    @Override
    public void onEvtMenuSelected(int action_id) {
        StepAI current_step_ai = summonAiComponent.getStepAI();
        if (current_step_ai.getAction(SKILL_NUMBER_ONE, -1) != -1) {
            if (current_step_ai.getAction(SKILL_NUMBER_ONE, -1) == action_id) {
                castSkill(summonAiComponent.getHealMagic(), getMaster());
            }
        }
    }
}

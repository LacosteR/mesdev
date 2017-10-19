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
public class SummonBomb extends SummonAI {
    public SummonBomb(Summon actor) {
        super(actor);
        /*
        actor::Summon_SetOption(3, 1);
		actor::Summon_SetOption(2, 1);
         */
    }

    @Override
    public void onEvtMenuSelected(int action_id) {
        if (action_id == 22) {
            if (getMaster().getTargetId() <= 0 || getMaster().getTargetId() == getActor().getObjectId() || getActor().isMovementDisabled()) {
                return;
            }
            getActor().setFollowMode(false);
            getActor().moveToLocation(getMaster().getTarget().getPositionComponent().getLoc(), 100, true);
            //actor::AddMoveToTargetDesire(i0, 150, 0, 10000);
        }
        StepAI current_step_ai = summonAiComponent.getStepAI();
        if (current_step_ai.getAction(SKILL_NUMBER_ONE, -1) != -1) {
            if (current_step_ai.getAction(SKILL_NUMBER_ONE, -1) == action_id) {
                castSkill(summonAiComponent.getDdMagic(), getActor());
            }
        }
    }
}

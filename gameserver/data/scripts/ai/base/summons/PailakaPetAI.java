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
public class PailakaPetAI extends SummonAI {
    public PailakaPetAI(Summon actor) {
        super(actor);
        addTimer(1000, 1000);
    }

    @Override
    public void onEvtMenuSelected(int action_id) {
        if (getMaster().getTarget() != null) {
            //if (action_id == step_action_id) {
                castSkill(summonAiComponent.getDdMagic(), (Creature) getMaster().getTarget());
            //}
        }
    }

    @Override
    protected void onEvtTimer(int timerId, Object arg1, Object arg2) {
        if (timerId == 1000) {
            if (getActor().getPositionComponent().getReflectionId() == 0) {
                getActor().unSummon();
            }
        }
        addTimer(1000, 2000);
    }

    @Override
    protected void onEvtDead(Creature killer) {
        if (killer != null) {
            //actor::BroadcastScriptEvent (2316003, gg::GetIndexFromCreature (attacker), 2000);
        }
        super.onEvtDead(killer);
    }
}

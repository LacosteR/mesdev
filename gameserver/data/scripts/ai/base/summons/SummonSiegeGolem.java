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
public class SummonSiegeGolem extends SummonAI {
    public SummonSiegeGolem(Summon actor) {
        super(actor);
        /*
        actor::Summon_SetOption(1, 900);

         */
        addTimer(100020, 1000);
        addTimer(100021, 1000 * 60);
        setArtillery(true);
    }

    @Override
    public void onEvtMenuSelected(int action_id) {
        if (action_id == 1000) {
            castSkill(4079, 1, (Creature) getMaster().getTarget());
        } else if (action_id == 2) {
            castSkill(4079, 1, (Creature) getMaster().getTarget());
        }
    }

    @Override
    protected void onEvtTimer(int timerId, Object arg1, Object arg2) {
        if (timerId == 100020) {
            if (getMaster().isDead()) {
            }
            addTimer(100020, 1000);
        }
        if (timerId == 100021) {
            if (getMaster() != null && getMaster().getInventory().getCountOf(2131) < 140 && getMaster().getInventory().getCountOf(2131) >= 70) {
                sayFStr(1000169);
            }
            addTimer(100021, 1000 * 60);
        }
    }
}

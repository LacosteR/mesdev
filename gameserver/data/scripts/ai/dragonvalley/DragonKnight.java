package ai.dragonvalley;

import l2p.gameserver.ai.CtrlEvent;
import l2p.gameserver.ai.Fighter;
import l2p.gameserver.model.Creature;
import l2p.gameserver.model.instances.NpcInstance;
import l2p.gameserver.utils.NpcUtils;
import l2p.technical.util.Rnd;

/**
 * @author pchayka
 */
public class DragonKnight extends Fighter {
    public DragonKnight(NpcInstance actor) {
        super(actor);
    }

    @Override
    protected void onEvtDead(Creature killer) {
        super.onEvtDead(killer);
        switch (getActor().getNpcId()) {
            case 22844:
                if (Rnd.chance(50)) {
                    NpcInstance n = NpcUtils.spawnSingle(22845, getActor().getPositionComponent().getLoc());
                    n.getAI().notifyEvent(CtrlEvent.EVT_AGGRESSION, killer, 2);
                } else if (Rnd.chance(50)) {
                    NpcInstance n = NpcUtils.spawnSingle(22847, getActor().getPositionComponent().getLoc());
                    n.getAI().notifyEvent(CtrlEvent.EVT_AGGRESSION, killer, 2);
                }
                break;
            case 22845:
                if (Rnd.chance(50)) {
                    NpcInstance n = NpcUtils.spawnSingle(22846, getActor().getPositionComponent().getLoc());
                    n.getAI().notifyEvent(CtrlEvent.EVT_AGGRESSION, killer, 2);
                }
                break;
        }

    }
}
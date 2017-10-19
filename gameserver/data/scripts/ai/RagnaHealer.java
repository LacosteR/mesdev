package ai;

import l2p.gameserver.ai.CtrlEvent;
import l2p.gameserver.ai.Priest;
import l2p.gameserver.model.Creature;
import l2p.gameserver.model.Skill;
import l2p.gameserver.model.instances.NpcInstance;
import l2p.gameserver.utils.GArray;

/**
 * @author Diamond
 */
public class RagnaHealer extends Priest {
    private long lastFactionNotifyTime;

    public RagnaHealer(NpcInstance actor) {
        super(actor);
    }

    @Override
    protected void onEvtAttacked(Creature attacker, int damage, Skill skill) {
        NpcInstance actor = getActor();
        if (attacker == null)
            return;

        if (System.currentTimeMillis() - lastFactionNotifyTime > 10000) {
            lastFactionNotifyTime = System.currentTimeMillis();
            GArray<NpcInstance> around = actor.getAroundNpc(500, 300);
            if (around != null && !around.isEmpty())
                for (NpcInstance npc : around)
                    if (npc.isMonster() && npc.getNpcId() >= 22691 && npc.getNpcId() <= 22702)
                        npc.getAI().notifyEvent(CtrlEvent.EVT_AGGRESSION, attacker, 5000);
        }

        super.onEvtAttacked(attacker, damage, skill);
    }
}
package ai.freya;

import l2p.gameserver.ai.CtrlEvent;
import l2p.gameserver.ai.Fighter;
import l2p.gameserver.model.Creature;
import l2p.gameserver.model.Skill;
import l2p.gameserver.model.instances.NpcInstance;
import l2p.gameserver.utils.GArray;

/**
 * @author pchayka
 */

public class JiniaGuild extends Fighter {
    public JiniaGuild(NpcInstance actor) {
        super(actor);
    }

    @Override
    protected boolean thinkActive() {
        NpcInstance actor = getActor();
        if (actor.isDead())
            return false;

        GArray<NpcInstance> around = actor.getAroundNpc(4000, 300);
        if (around != null && !around.isEmpty())
            for (NpcInstance npc : around)
                if (npc.getNpcId() == 29179)
                    actor.getAI().notifyEvent(CtrlEvent.EVT_AGGRESSION, npc, 3000);
        return true;
    }

    @Override
    protected void onEvtAttacked(Creature attacker, int damage, Skill skill) {
        if (attacker == null || attacker.isPlayable())
            return;

        super.onEvtAttacked(attacker, damage, skill);
    }

    @Override
    public boolean checkAggression(Creature target) {
        if (target.isPlayable())
            return false;

        return super.checkAggression(target);
    }
}
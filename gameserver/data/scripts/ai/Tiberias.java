package ai;

import l2p.gameserver.ai.Fighter;
import l2p.gameserver.model.Creature;
import l2p.gameserver.model.instances.NpcInstance;
import l2p.gameserver.network.serverpackets.components.NpcString;
import l2p.gameserver.scripts.Functions;

/**
 * AI рейдбосса Tiberias
 * любит поговорить после смерти
 *
 * @author n0nam3
 */
public class Tiberias extends Fighter {
    public Tiberias(NpcInstance actor) {
        super(actor);
    }

    @Override
    protected void onEvtDead(Creature killer) {
        NpcInstance actor = getActor();

        Functions.npcShout(actor, NpcString.YOUR_SKILL_IS_IMPRESSIVE);

        super.onEvtDead(killer);
    }
}
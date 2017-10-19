package ai;

import instances.KamalokaNightmare;
import l2p.gameserver.ThreadPoolManager;
import l2p.gameserver.ai.CtrlEvent;
import l2p.gameserver.ai.Fighter;
import l2p.technical.threading.RunnableImpl;
import l2p.gameserver.model.Creature;
import l2p.gameserver.model.Player;
import l2p.gameserver.model.SimpleSpawner;
import l2p.gameserver.model.World;
import l2p.gameserver.model.entity.Reflection;
import l2p.gameserver.model.instances.MonsterInstance;
import l2p.gameserver.model.instances.NpcInstance;
import l2p.gameserver.utils.PositionUtils;

public class InstanceBoss extends Fighter {
    public InstanceBoss(NpcInstance actor) {
        super(actor);
    }

    @Override
    protected void onEvtDead(Creature killer) {
        NpcInstance actor = getActor();
        Reflection r = actor.getPositionComponent().getReflection();
		r.setReenterTime(System.currentTimeMillis());
		r.clearReflection(5, true);
        super.onEvtDead(killer);
    }
}
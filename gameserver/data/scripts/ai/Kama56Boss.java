package ai;

import l2p.gameserver.ai.CtrlEvent;
import l2p.gameserver.ai.Fighter;
import l2p.gameserver.model.Player;
import l2p.gameserver.model.World;
import l2p.gameserver.model.instances.NpcInstance;
import l2p.gameserver.network.serverpackets.components.NpcString;
import l2p.gameserver.scripts.Functions;
import l2p.technical.collections.LazyArrayList;
import l2p.technical.lang.reference.HardReference;
import l2p.technical.lang.reference.HardReferences;
import l2p.technical.util.Rnd;

import java.util.List;


public class Kama56Boss extends Fighter {
    private long _nextOrderTime = 0;
    private HardReference<Player> _lastMinionsTargetRef = HardReferences.emptyRef();

    public Kama56Boss(NpcInstance actor) {
        super(actor);
    }

    private void sendOrderToMinions(NpcInstance actor) {
        if (!actor.isInCombat()) {
            _lastMinionsTargetRef = HardReferences.emptyRef();
            return;
        }

        List<NpcInstance> ml = actor.getMinionList();
        if (ml == null || ml.isEmpty()) {
            _lastMinionsTargetRef = HardReferences.emptyRef();
            return;
        }

        long now = System.currentTimeMillis();
        if (_nextOrderTime > now && _lastMinionsTargetRef.get() != null) {
            Player old_target = _lastMinionsTargetRef.get();
            if (old_target != null && !old_target.isAlikeDead()) {
                for (NpcInstance m : ml)
                    if (m.getAI().getAttackTarget() != old_target)
                        m.getAI().notifyEvent(CtrlEvent.EVT_AGGRESSION, old_target, 10000000);
                return;
            }
        }

        _nextOrderTime = now + 30000;

        List<Player> pl = World.getAroundPlayers(actor);
        if (pl.isEmpty()) {
            _lastMinionsTargetRef = HardReferences.emptyRef();
            return;
        }

        List<Player> alive = new LazyArrayList<Player>();
        for (Player p : pl)
            if (!p.isAlikeDead())
                alive.add(p);
        if (alive.isEmpty()) {
            _lastMinionsTargetRef = HardReferences.emptyRef();
            return;
        }

        Player target = alive.get(Rnd.get(alive.size()));
        _lastMinionsTargetRef = target.getRef();

        Functions.npcSay(actor, NpcString.YOU_S1_ATTACK_THEM, target.getName());
        for (NpcInstance m : ml) {
            m.getAggroList().clear();
            m.getAI().notifyEvent(CtrlEvent.EVT_AGGRESSION, target, 10000000);
        }
    }

    @Override
    protected void thinkAttack() {
        NpcInstance actor = getActor();
        if (actor == null)
            return;

        sendOrderToMinions(actor);
        super.thinkAttack();
    }
}
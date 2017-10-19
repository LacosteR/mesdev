package ai;

import l2p.gameserver.ThreadPoolManager;
import l2p.gameserver.ai.CtrlIntention;
import l2p.gameserver.ai.DefaultAI;
import l2p.gameserver.config.ConfigAI;
import l2p.gameserver.config.ConfigOthers;
import l2p.gameserver.model.Creature;
import l2p.gameserver.model.instances.MonsterInstance;
import l2p.gameserver.model.instances.NpcInstance;
import l2p.gameserver.utils.Location;
import l2p.gameserver.utils.PositionUtils;
import l2p.technical.threading.RunnableImpl;
import l2p.technical.util.Rnd;

import java.util.concurrent.ScheduledFuture;

public class FollowNpc extends DefaultAI {
    private boolean _thinking = false;
    private ScheduledFuture<?> _followTask;

    public FollowNpc(NpcInstance actor) {
        super(actor);
    }

    @Override
    protected boolean randomWalk() {
        if (getActor() instanceof MonsterInstance)
            return true;

        return false;
    }

    @Override
    protected void onEvtThink() {
        NpcInstance actor = getActor();
        if (_thinking || actor.isActionsDisabled() || actor.isAfraid() || actor.isDead() || actor.isMovementDisabled())
            return;

        _thinking = true;
        try {
            if (!ConfigAI.BLOCK_ACTIVE_TASKS && (getIntention() == CtrlIntention.AI_INTENTION_ACTIVE || getIntention() == CtrlIntention.AI_INTENTION_IDLE))
                thinkActive();
            else if (getIntention() == CtrlIntention.AI_INTENTION_FOLLOW)
                thinkFollow();
        } catch (Exception e) {
            _log.error("", e);
        } finally {
            _thinking = false;
        }
    }

    protected void thinkFollow() {
        NpcInstance actor = getActor();

        Creature target = actor.getFollowTarget();

        //Находимся слишком далеко цели, либо цель не пригодна для следования, либо не можем перемещаться
        if (target == null || target.isAlikeDead() || PositionUtils.getDistance(actor, target) > 4000 || actor.isMovementDisabled()) {
            clientActionFailed();
            return;
        }

        //Уже следуем за этой целью
        if (actor.isFollow && actor.getFollowTarget() == target) {
            clientActionFailed();
            return;
        }

        //Находимся достаточно близко
        if (PositionUtils.isInRange(actor, target, ConfigOthers.FOLLOW_RANGE + 20, false))
            clientActionFailed();

        if (_followTask != null) {
            _followTask.cancel(false);
            _followTask = null;
        }

        _followTask = ThreadPoolManager.getInstance().schedule(new ThinkFollow(), 250L);
    }

    protected class ThinkFollow extends RunnableImpl {
        public NpcInstance getActor() {
            return FollowNpc.this.getActor();
        }

        @Override
        public void runImpl() {
            NpcInstance actor = getActor();
            if (actor == null)
                return;

            Creature target = actor.getFollowTarget();

            if (target == null || target.isAlikeDead() || PositionUtils.getDistance(actor, target) > 4000) {
                setIntention(CtrlIntention.AI_INTENTION_ACTIVE);
                return;
            }

            if (!PositionUtils.isInRange(actor, target, ConfigOthers.FOLLOW_RANGE + 20, false) && (!actor.isFollow || actor.getFollowTarget() != target)) {
                Location loc = new Location(target.getPositionComponent().getX() + Rnd.get(-60, 60), target.getPositionComponent().getY() + Rnd.get(-60, 60), target.getPositionComponent().getZ());
                actor.followToCharacter(loc, target, ConfigOthers.FOLLOW_RANGE, false);
            }
            _followTask = ThreadPoolManager.getInstance().schedule(this, 250L);
        }
    }
}
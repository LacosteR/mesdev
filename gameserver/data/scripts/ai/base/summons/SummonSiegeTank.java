package ai.base.summons;

import l2p.gameserver.ThreadPoolManager;
import l2p.gameserver.ai.SummonAI;
import l2p.gameserver.model.Creature;
import l2p.gameserver.model.GameObjectTasks;
import l2p.gameserver.model.Summon;
import l2p.gameserver.model.instances.StaticObjectInstance;
import l2p.gameserver.network.serverpackets.ChangeWaitType;
import l2p.gameserver.utils.PositionUtils;

/**
 * @author : Alice
 * @date : 29.03.17
 * @time : 0:23
 * <p/>
 */
public class SummonSiegeTank extends SummonAI {
    public SummonSiegeTank(Summon actor) {
        super(actor);
        /*
        actor::Summon_SetOption(3, 1);
		actor::Summon_SetOption(2, 1);
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
        if (action_id == 22) {
            if (getMaster().getTargetId() <= 0 || getMaster().getTargetId() == getActor().getObjectId() || getActor().isMovementDisabled()) {
                return;
            }
            getActor().setFollowMode(false);
            getActor().moveToLocation(getMaster().getTarget().getPositionComponent().getLoc(), 100, true);
            //actor::AddMoveToTargetDesire(i0, 150, 0, 10000);
        } else if (action_id == 32) {
            if (getActor().isActionsDisabled() || getActor().isAlikeDead()) {
                return;
            }
            int string = getActor().getWaitType() == ChangeWaitType.WT_SITTING ? 1110072 : 1110071; // если сидим, то отсылаем пакет, что встаём и наоборот
            if (getActor().isChangingWaitType() && getActor().isSummon()) {
                sayFStr(string);
                return;
            }
            int waitType = getActor().getWaitType() == ChangeWaitType.WT_SITTING ? ChangeWaitType.WT_STANDING : ChangeWaitType.WT_SITTING;
            getActor().setChangingWaitType(true);
            getActor().broadcastPacket(new ChangeWaitType(getActor(), waitType));
            ThreadPoolManager.getInstance().schedule(new GameObjectTasks.ChangeWaitTypeTask(getActor(), waitType), 30000);
        } else if (action_id == 41) {
            if (getActor().isActionsDisabled() || getActor().isAlikeDead()) {
                return;
            }
            int string = getActor().getWaitType() == ChangeWaitType.WT_SITTING ? 1110072 : 1110071; // если сидим, то отсылаем пакет, что встаём и наоборот
            if (getActor().isChangingWaitType() && getActor().isSummon()) {
                sayFStr(string);
                return;
            }
            if (getActor().getWaitType() == ChangeWaitType.WT_STANDING) {
                sayFStr(1110073);   // It is possible to use a skill while sitting down.
                return;
            }
            if (getMaster().getTarget().isStaticObject()) {
                StaticObjectInstance so0 = (StaticObjectInstance) getMaster().getTarget();
                if (PositionUtils.getDistance(getActor(), so0) >= 2500) {
                    sayFStr(1110074);   // is out of range.
                    return;
                } else if (getActor().isSkillDisabled(summonAiComponent.getDdMagic())) {
                    getActor().sendReuseMessage(summonAiComponent.getDdMagic());
                    return;
                }
                castSkill(summonAiComponent.getDdMagic(), (Creature) getMaster().getTarget());
            }
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
            if (getMaster() != null && getMaster().getInventory().getCountOf(2132) < 60 && getMaster().getInventory().getCountOf(2132) >= 30) {
                sayFStr(1000169);
            }
            addTimer(100021, 1000 * 60);
        }
    }
}

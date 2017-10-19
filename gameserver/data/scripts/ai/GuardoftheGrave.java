package ai;

import l2p.gameserver.ThreadPoolManager;
import l2p.gameserver.ai.Fighter;
import l2p.gameserver.managers.factory.NpcFactory;
import l2p.gameserver.model.Creature;
import l2p.gameserver.model.instances.NpcInstance;
import l2p.gameserver.utils.PositionUtils;
import l2p.technical.threading.RunnableImpl;
import l2p.technical.util.Rnd;

/**
 * AI мобов Guard of the Grave на Crypts Of Disgrace.<br>
 * - Деспавнятся при простое более 2 минут<br>
 * - Не используют функцию Random Walk<br>
 * ID: 18815
 *
 * @author n0nam3
 */
public class GuardoftheGrave extends Fighter {
    private static final int DESPAWN_TIME = 2 * 45 * 1000;
    private static final int CHIEFTAINS_TREASURE_CHEST = 18816;

    public GuardoftheGrave(NpcInstance actor) {
        super(actor);
        actor.startDamageBlockedHp();
        actor.startDebuffImmunity();
        actor.startImmobilized();
    }

    @Override
    protected void onEvtSpawn() {
        super.onEvtSpawn();
        ThreadPoolManager.getInstance().schedule(new DeSpawnTask(), DESPAWN_TIME + Rnd.get(1, 30));
    }

    @Override
    protected boolean checkTarget(Creature target, int range) {
        NpcInstance actor = getActor();
        if (actor != null && target != null && !PositionUtils.isInRange(actor, target, actor.getAggroRange(), false)) {
            actor.getAggroList().remove(target, true);
            return false;
        }
        return super.checkTarget(target, range);
    }

    protected void spawnChest(NpcInstance actor) {
        try {
            NpcInstance npc = NpcFactory.createNpc(CHIEFTAINS_TREASURE_CHEST);
            npc.setSpawnedLoc(actor.getPositionComponent().getLoc());
            npc.setCurrentHpMp(npc.getMaxHp(), npc.getMaxMp(), true);
            npc.spawnMe(npc.getSpawnedLoc());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private class DeSpawnTask extends RunnableImpl {
        @Override
        public void runImpl() {
            NpcInstance actor = getActor();
            spawnChest(actor);
            actor.deleteMe();
        }
    }
}
package ai;

import l2p.gameserver.ai.DefaultAI;
import l2p.gameserver.model.Creature;
import l2p.gameserver.model.Skill;
import l2p.gameserver.model.World;
import l2p.gameserver.model.instances.NpcInstance;
import l2p.technical.lang.reference.HardReference;
import l2p.technical.lang.reference.HardReferences;
import l2p.technical.util.Rnd;

public class EdwinFollower extends DefaultAI {
    private static final int EDWIN_ID = 32072;
    private static final int DRIFT_DISTANCE = 200;
    private long _wait_timeout = 0;
    private HardReference<? extends Creature> _edwinRef = HardReferences.emptyRef();

    public EdwinFollower(NpcInstance actor) {
        super(actor);
    }

    @Override
    public boolean isGlobalAI() {
        return true;
    }

    @Override
    protected boolean randomAnimation() {
        return false;
    }

    @Override
    protected boolean randomWalk() {
        return false;
    }

    @Override
    protected boolean thinkActive() {
        NpcInstance actor = getActor();
        Creature edwin = _edwinRef.get();
        if (edwin == null) {
            // Ищем преследуемого не чаще, чем раз в 15 секунд, если по каким-то причинам его нету
            if (System.currentTimeMillis() > _wait_timeout) {
                _wait_timeout = System.currentTimeMillis() + 15000;
                for (NpcInstance npc : World.getAroundNpc(actor))
                    if (npc.getNpcId() == EDWIN_ID) {
                        _edwinRef = npc.getRef();
                        return true;
                    }
            }
        } else if (!actor.isMoving) {
            int x = edwin.getPositionComponent().getX() + Rnd.get(2 * DRIFT_DISTANCE) - DRIFT_DISTANCE;
            int y = edwin.getPositionComponent().getY() + Rnd.get(2 * DRIFT_DISTANCE) - DRIFT_DISTANCE;
            int z = edwin.getPositionComponent().getZ();

            actor.setRunning(); // всегда бегают
            actor.moveToLocation(x, y, z, 0, true);
            return true;
        }
        return false;
    }

    @Override
    protected void onEvtAttacked(Creature attacker, int damage, Skill skill) {
    }

    @Override
    protected void onEvtAggression(Creature target, int aggro) {
    }
}
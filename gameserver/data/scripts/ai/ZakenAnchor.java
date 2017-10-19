package ai;

import l2p.gameserver.ai.DefaultAI;
import l2p.gameserver.model.instances.NpcInstance;

public class ZakenAnchor extends DefaultAI {
    private static final int DayZaken = 29176;
    private static final int UltraDayZaken = 29181;
    private static final int Candle = 32705;
    private int i = 0;

    public ZakenAnchor(NpcInstance actor) {
        super(actor);
    }

    @Override
    protected boolean thinkActive() {
        NpcInstance actor = getActor();
        for (NpcInstance npc : actor.getAroundNpc(1000, 100))
            if (npc.getNpcId() == Candle && npc.getRightHandItem() == 15302)
                i++;

        if (i >= 4) {
            if (actor.getPositionComponent().getReflection().getInstancedZoneId() == 133) {
                actor.getPositionComponent().getReflection().addSpawnWithoutRespawn(DayZaken, actor.getPositionComponent().getLoc(), 0);
                for (int i = 0; i < 4; i++) {
                    actor.getPositionComponent().getReflection().addSpawnWithoutRespawn(20845, actor.getPositionComponent().getLoc(), 200);
                    actor.getPositionComponent().getReflection().addSpawnWithoutRespawn(20847, actor.getPositionComponent().getLoc(), 200);
                }
                actor.deleteMe();
                return true;
            } else if (actor.getPositionComponent().getReflection().getInstancedZoneId() == 135) {
                for (NpcInstance npc : actor.getPositionComponent().getReflection().getNpcs())
                    if (npc.getNpcId() == UltraDayZaken) {
                        npc.teleToLocation(actor.getPositionComponent().getLoc());
                    }
                for (int i = 0; i < 4; i++) {
                    actor.getPositionComponent().getReflection().addSpawnWithoutRespawn(29184, actor.getPositionComponent().getLoc(), 300);
                    actor.getPositionComponent().getReflection().addSpawnWithoutRespawn(29183, actor.getPositionComponent().getLoc(), 300);
                }
                actor.deleteMe();
                return true;
            }
        } else
            i = 0;

        return false;
    }
}
package ai.hellbound;

import l2p.gameserver.ai.Fighter;
import l2p.gameserver.geodata.GeoEngine;
import l2p.gameserver.model.Creature;
import l2p.gameserver.model.Skill;
import l2p.gameserver.model.instances.NpcInstance;
import l2p.gameserver.utils.Location;

public class FoundryWorker extends Fighter {
    public FoundryWorker(NpcInstance actor) {
        super(actor);
    }

    @Override
    protected void onEvtAttacked(Creature attacker, int damage, Skill skill) {
        NpcInstance actor = getActor();
        if (attacker != null) {
            Location pos = Location.findPointToStay(actor, 150, 250);
            if (GeoEngine.canMoveToCoord(attacker.getPositionComponent().getX(), attacker.getPositionComponent().getY(), attacker.getPositionComponent().getZ(), pos.x, pos.y, pos.z, actor.getPositionComponent().getGeoIndex())) {
                actor.setRunning();
                addMoveToDesire(pos, false, 5);
            }
        }
    }

    @Override
    public boolean checkAggression(Creature target) {
        return false;
    }

    @Override
    protected void onEvtAggression(Creature target, int aggro) {
    }
}
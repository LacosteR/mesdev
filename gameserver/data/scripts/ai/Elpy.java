package ai;

import l2p.gameserver.ai.Fighter;
import l2p.gameserver.geodata.GeoEngine;
import l2p.gameserver.model.Creature;
import l2p.gameserver.model.Skill;
import l2p.gameserver.model.instances.NpcInstance;
import l2p.gameserver.utils.Location;
import l2p.technical.util.Rnd;

public class Elpy extends Fighter {
    public Elpy(NpcInstance actor) {
        super(actor);
    }

    @Override
    protected void onEvtAttacked(Creature attacker, int damage, Skill skill) {
        NpcInstance actor = getActor();
        if (attacker != null && Rnd.chance(50)) {
            Location pos = Location.findPointToStay(actor, 150, 200);
            if (GeoEngine.canMoveToCoord(actor.getPositionComponent().getX(), actor.getPositionComponent().getY(), actor.getPositionComponent().getZ(), pos.x, pos.y, pos.z, actor.getPositionComponent().getGeoIndex())) {
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
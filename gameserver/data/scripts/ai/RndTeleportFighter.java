package ai;

import l2p.gameserver.ThreadPoolManager;
import l2p.gameserver.ai.Fighter;
import l2p.gameserver.config.ConfigAI;
import l2p.gameserver.geodata.GeoEngine;
import l2p.gameserver.model.Territory;
import l2p.gameserver.model.instances.NpcInstance;
import l2p.gameserver.network.serverpackets.MagicSkillUse;
import l2p.gameserver.templates.spawn.SpawnRange;
import l2p.gameserver.utils.Location;
import l2p.gameserver.utils.PositionUtils;
import l2p.technical.util.Rnd;

/**
 * Моб использует телепортацию вместо рандом валка.
 *
 * @author SYS
 */
public class RndTeleportFighter extends Fighter {
    private long _lastTeleport;

    public RndTeleportFighter(NpcInstance actor) {
        super(actor);
    }

    @Override
    protected boolean maybeMoveToHome() {
        NpcInstance actor = getActor();
        if (System.currentTimeMillis() - _lastTeleport < 10000)
            return false;

        boolean randomWalk = actor.hasRandomWalk();
        Location sloc = actor.getSpawnedLoc();
        if (sloc == null)
            return false;

        // Random walk or not?
        if (randomWalk && Rnd.chance(2))
            return false;

        if (!randomWalk && PositionUtils.isInRange(actor, sloc, ConfigAI.MAX_DRIFT_RANGE, true))
            return false;

        int x = sloc.x + Rnd.get(-ConfigAI.MAX_DRIFT_RANGE, ConfigAI.MAX_DRIFT_RANGE);
        int y = sloc.y + Rnd.get(-ConfigAI.MAX_DRIFT_RANGE, ConfigAI.MAX_DRIFT_RANGE);
        int z = GeoEngine.getHeight(x, y, sloc.z, actor.getPositionComponent().getGeoIndex());

        if (sloc.z - z > 64)
            return false;

        SpawnRange spawnRange = actor.getSpawnRange();
        boolean isInside = true;
        if (spawnRange != null && spawnRange instanceof Territory)
            isInside = ((Territory) spawnRange).isInside(x, y);

        if (isInside) {
            actor.broadcastPacketToOthers(new MagicSkillUse(actor, actor, 4671, 1, 500, 0));
            ThreadPoolManager.getInstance().schedule(new Teleport(new Location(x, y, z)), 500);
            _lastTeleport = System.currentTimeMillis();
        }
        return isInside;
    }
}
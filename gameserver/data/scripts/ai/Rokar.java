package ai;

import l2p.gameserver.ai.DefaultAI;
import l2p.gameserver.model.Creature;
import l2p.gameserver.model.Skill;
import l2p.gameserver.model.instances.NpcInstance;
import l2p.gameserver.utils.Location;
import l2p.technical.util.Rnd;

public class Rokar extends DefaultAI {
    static final Location[] points = {
            new Location(-46516, -117700, -264),
            new Location(-45550, -115420, -256),
            new Location(-44052, -114575, -256),
            new Location(-44024, -112688, -256),
            new Location(-45748, -111665, -256),
            new Location(-46512, -109390, -232),
            new Location(-45748, -111665, -256),
            new Location(-44024, -112688, -256),
            new Location(-44052, -114575, -256),
            new Location(-45550, -115420, -256)};

    private int current_point = -1;
    private long wait_timeout = 0;
    private boolean wait = false;

    public Rokar(NpcInstance actor) {
        super(actor);
    }

    @Override
    public boolean isGlobalAI() {
        return true;
    }

    @Override
    protected boolean thinkActive() {
        NpcInstance actor = getActor();
        if (actor.isDead())
            return true;

        if (_def_think) {
            doTask();
            return true;
        }

        if (System.currentTimeMillis() > wait_timeout && (current_point > -1 || Rnd.chance(5))) {
            if (!wait)
                switch (current_point) {
                    case 5:
                        wait_timeout = System.currentTimeMillis() + 30000;
                        wait = true;
                        return true;
                }

            wait_timeout = 0;
            wait = false;
            current_point++;

            if (current_point >= points.length)
                current_point = 0;

            addMoveToDesire(points[current_point], true, 5);
            doTask();
            return true;
        }

        if (randomAnimation())
            return true;

        return false;
    }

    @Override
    protected void onEvtAttacked(Creature attacker, int damage, Skill skill) {
    }

    @Override
    protected void onEvtAggression(Creature target, int aggro) {
    }
}
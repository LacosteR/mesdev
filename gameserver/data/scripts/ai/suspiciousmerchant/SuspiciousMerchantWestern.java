//
// Suspicious Merchant - Western Fortress (36211).
//
package ai.suspiciousmerchant;

import l2p.gameserver.ai.DefaultAI;
import l2p.gameserver.model.Creature;
import l2p.gameserver.model.Skill;
import l2p.gameserver.model.instances.NpcInstance;
import l2p.gameserver.utils.Location;
import l2p.technical.util.Rnd;

public class SuspiciousMerchantWestern extends DefaultAI {
    static final Location[] points = {
            new Location(114221, -18762, -1768),
            new Location(115920, -19177, -2120),
            new Location(117105, -19759, -2400),
            new Location(118417, -20135, -2632),
            new Location(118881, -20011, -2712),
            new Location(117210, -18329, -1816),
            new Location(118881, -20011, -2712),
            new Location(118417, -20135, -2632),
            new Location(117105, -19759, -2400),
            new Location(115920, -19177, -2120),
            new Location(114221, -18762, -1768)};

    private int current_point = -1;
    private long wait_timeout = 0;
    private boolean wait = false;

    public SuspiciousMerchantWestern(NpcInstance actor) {
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

        if (actor.isMoving)
            return true;

        if (System.currentTimeMillis() > wait_timeout && (current_point > -1 || Rnd.chance(5))) {
            if (!wait)
                switch (current_point) {
                    case 0:
                        wait_timeout = System.currentTimeMillis() + 30000;
                        wait = true;
                        return true;
                    case 3:
                        wait_timeout = System.currentTimeMillis() + 30000;
                        wait = true;
                        return true;
                    case 5:
                        wait_timeout = System.currentTimeMillis() + 60000;
                        wait = true;
                        return true;
                    case 7:
                        wait_timeout = System.currentTimeMillis() + 30000;
                        wait = true;
                        return true;
                    case 10:
                        wait_timeout = System.currentTimeMillis() + 30000;
                        wait = true;
                        return true;
                }

            wait_timeout = 0;
            wait = false;
            current_point++;

            if (current_point >= points.length)
                current_point = 0;

            addMoveToDesire(points[current_point], false, 5);
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
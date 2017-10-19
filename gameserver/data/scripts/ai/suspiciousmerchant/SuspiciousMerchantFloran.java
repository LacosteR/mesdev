//
// Suspicious Merchant - Floran Fortress (36035).
//
package ai.suspiciousmerchant;

import l2p.gameserver.ai.DefaultAI;
import l2p.gameserver.model.Creature;
import l2p.gameserver.model.Skill;
import l2p.gameserver.model.instances.NpcInstance;
import l2p.gameserver.utils.Location;
import l2p.technical.util.Rnd;

public class SuspiciousMerchantFloran extends DefaultAI {
    static final Location[] points = {
            new Location(14186, 149947, -3352),
            new Location(16180, 150387, -3216),
            new Location(18387, 151874, -3317),
            new Location(18405, 154770, -3616),
            new Location(17655, 156863, -3664),
            new Location(12303, 153937, -2680),
            new Location(17655, 156863, -3664),
            new Location(18405, 154770, -3616),
            new Location(18387, 151874, -3317),
            new Location(16180, 150387, -3216),
            new Location(14186, 149947, -3352)};

    private int current_point = -1;
    private long wait_timeout = 0;
    private boolean wait = false;

    public SuspiciousMerchantFloran(NpcInstance actor) {
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
                        wait_timeout = System.currentTimeMillis() + 20000;
                        wait = true;
                        return true;
                    case 3:
                        wait_timeout = System.currentTimeMillis() + 30000;
                        wait = true;
                        return true;
                    case 7:
                        wait_timeout = System.currentTimeMillis() + 30000;
                        wait = true;
                        return true;
                    case 10:
                        wait_timeout = System.currentTimeMillis() + 20000;
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
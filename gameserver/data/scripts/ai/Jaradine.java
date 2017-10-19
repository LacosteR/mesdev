package ai;

import l2p.gameserver.ai.DefaultAI;
import l2p.gameserver.model.Creature;
import l2p.gameserver.model.Skill;
import l2p.gameserver.model.instances.NpcInstance;
import l2p.gameserver.network.serverpackets.components.NpcString;
import l2p.gameserver.scripts.Functions;
import l2p.gameserver.utils.Location;
import l2p.technical.util.Rnd;

public class Jaradine extends DefaultAI {
    static final Location[] points = {
            new Location(44964, 50568, -3056),
            new Location(44435, 50025, -3056),
            new Location(44399, 49078, -3056),
            new Location(45058, 48437, -3056),
            new Location(46132, 48724, -3056),
            new Location(46452, 49743, -3056),
            new Location(45730, 50590, -3056)};

    private int current_point = -1;
    private long wait_timeout = 0;
    private boolean wait = false;

    public Jaradine(NpcInstance actor) {
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
                    case 3:
                        wait_timeout = System.currentTimeMillis() + 15000;
                        Functions.npcSay(actor, NpcString.THE_MOTHER_TREE_IS_SLOWLY_DYING);
                        wait = true;
                        return true;
                    case 4:
                        wait_timeout = System.currentTimeMillis() + 15000;
                        Functions.npcSay(actor, NpcString.HOW_CAN_WE_SAVE_THE_MOTHER_TREE);
                        wait = true;
                        return true;
                    case 6:
                        wait_timeout = System.currentTimeMillis() + 60000;
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
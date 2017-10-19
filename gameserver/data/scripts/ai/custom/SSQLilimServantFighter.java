package ai.custom;

import l2p.gameserver.ai.Fighter;
import l2p.gameserver.model.Creature;
import l2p.gameserver.model.Skill;
import l2p.gameserver.model.instances.NpcInstance;
import l2p.gameserver.scripts.Functions;
import l2p.technical.util.Rnd;

/**
 * AI Lilim Servants: 27371-27379
 *
 * @author pchayka
 */
public class SSQLilimServantFighter extends Fighter {
    private boolean _attacked = false;

    public SSQLilimServantFighter(NpcInstance actor) {
        super(actor);
    }

    @Override
    protected void onEvtAttacked(Creature attacker, int damage, Skill skill) {
        if (Rnd.chance(30) && !_attacked) {
            Functions.npcSay(getActor(), Rnd.chance(50) ? "Those who are afraid should get away and those who are brave should fight!" : "This place once belonged to Lord Shilen.");
            _attacked = true;
        }
        super.onEvtAttacked(attacker, damage, skill);
    }

    @Override
    protected void onEvtDead(Creature killer) {
        if (Rnd.chance(30))
            Functions.npcSay(getActor(), Rnd.chance(50) ? "Why are you getting in our way?" : "Shilen... our Shilen!");
        super.onEvtDead(killer);
    }
}
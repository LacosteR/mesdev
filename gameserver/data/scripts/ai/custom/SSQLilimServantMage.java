package ai.custom;

import l2p.gameserver.ai.Mystic;
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
public class SSQLilimServantMage extends Mystic {
    private boolean _attacked = false;

    public SSQLilimServantMage(NpcInstance actor) {
        super(actor);
    }

    @Override
    protected void onEvtAttacked(Creature attacker, int damage, Skill skill) {
        super.onEvtAttacked(attacker, damage, skill);
        if (Rnd.chance(30) && !_attacked) {
            Functions.npcSay(getActor(), "Who dares enter this place?");
            _attacked = true;
        }
    }

    @Override
    protected void onEvtDead(Creature killer) {
        if (Rnd.chance(30))
            Functions.npcSay(getActor(), "Lord Shilen... some day... you will accomplish... this mission...");
        super.onEvtDead(killer);
    }
}
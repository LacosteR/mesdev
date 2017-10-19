package ai.hellbound;

import l2p.gameserver.ai.CtrlIntention;
import l2p.gameserver.ai.Fighter;
import l2p.gameserver.model.Creature;
import l2p.gameserver.model.instances.NpcInstance;
import l2p.gameserver.scripts.Functions;
import l2p.technical.util.Rnd;

/**
 * AI Town Guard в городе-инстанте на Hellbound<br>
 * - перед тем как броситься в атаку кричат
 *
 * @author SYS
 */
public class TownGuard extends Fighter {
    public TownGuard(NpcInstance actor) {
        super(actor);
    }

    @Override
    protected void onIntentionAttack(Creature target) {
        NpcInstance actor = getActor();
        if (getIntention() == CtrlIntention.AI_INTENTION_ACTIVE && Rnd.chance(50))
            Functions.npcSay(actor, "Invader!");
        super.onIntentionAttack(target);
    }
}
package ai;

import l2p.gameserver.ai.Ranger;
import l2p.gameserver.model.Creature;
import l2p.gameserver.model.Skill;
import l2p.gameserver.model.instances.NpcInstance;
import l2p.gameserver.network.serverpackets.components.NpcString;
import l2p.gameserver.scripts.Functions;
import l2p.technical.util.Rnd;

/**
 * AI для Karul Bugbear ID: 20600
 *
 * @author Diamond
 */
public class KarulBugbear extends Ranger {
    private boolean _firstTimeAttacked = true;

    public KarulBugbear(NpcInstance actor) {
        super(actor);
    }

    @Override
    protected void onEvtSpawn() {
        _firstTimeAttacked = true;
        super.onEvtSpawn();
    }

    @Override
    protected void onEvtAttacked(Creature attacker, int damage, Skill skill) {
        NpcInstance actor = getActor();
        if (_firstTimeAttacked) {
            _firstTimeAttacked = false;
            if (Rnd.chance(25))
                Functions.npcSay(actor, NpcString.YOUR_REAR_IS_PRACTICALLY_UNGUARDED);
        } else if (Rnd.chance(10))
            Functions.npcSay(actor, NpcString.S1_WATCH_YOUR_BACK, attacker.getPlayer().getName());
        super.onEvtAttacked(attacker, damage, skill);
    }
}
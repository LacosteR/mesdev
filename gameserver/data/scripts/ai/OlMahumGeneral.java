package ai;

import l2p.gameserver.ai.Fighter;
import l2p.gameserver.model.Creature;
import l2p.gameserver.model.Skill;
import l2p.gameserver.model.instances.NpcInstance;
import l2p.gameserver.network.serverpackets.components.NpcString;
import l2p.gameserver.scripts.Functions;
import l2p.technical.util.Rnd;

/**
 * AI для Karul Bugbear ID: 20438
 *
 * @author Diamond
 */
public class OlMahumGeneral extends Fighter {
    private boolean _firstTimeAttacked = true;

    public OlMahumGeneral(NpcInstance actor) {
        super(actor);
    }

    @Override
    protected void onEvtAttacked(Creature attacker, int damage, Skill skill) {
        NpcInstance actor = getActor();
        if (_firstTimeAttacked) {
            _firstTimeAttacked = false;
            if (Rnd.chance(25))
                Functions.npcSay(actor, NpcString.WE_SHALL_SEE_ABOUT_THAT);
        } else if (Rnd.chance(10))
            Functions.npcSay(actor, NpcString.I_WILL_DEFINITELY_REPAY_THIS_HUMILIATION);
        super.onEvtAttacked(attacker, damage, skill);
    }

    @Override
    protected void onEvtDead(Creature killer) {
        _firstTimeAttacked = true;
        super.onEvtDead(killer);
    }
}
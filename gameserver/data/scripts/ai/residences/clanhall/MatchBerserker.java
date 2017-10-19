package ai.residences.clanhall;

import l2p.gameserver.model.Creature;
import l2p.gameserver.model.Skill;
import l2p.gameserver.model.instances.NpcInstance;
import l2p.gameserver.tables.SkillTable;
import l2p.technical.util.Rnd;

/**
 * @author VISTALL
 * @date 16:29/22.04.2011
 */
public class MatchBerserker extends MatchFighter {
    public static final Skill ATTACK_SKILL = SkillTable.getInstance().getInfo(4032, 6);

    public MatchBerserker(NpcInstance actor) {
        super(actor);
    }

    @Override
    public void onEvtAttacked(Creature attacker, int dam, Skill skill) {
        super.onEvtAttacked(attacker, dam, skill);

        if (Rnd.chance(10))
            addUseSkillDesire(attacker, ATTACK_SKILL, 10000);
    }
}

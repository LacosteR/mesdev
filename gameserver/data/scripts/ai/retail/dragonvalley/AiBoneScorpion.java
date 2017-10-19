package ai.retail.dragonvalley;

import l2p.gameserver.ai.base.RetailClass;
import l2p.gameserver.ai.retail.DetectPartyWarrior;
import l2p.gameserver.model.Creature;
import l2p.gameserver.model.Skill;
import l2p.gameserver.model.instances.NpcInstance;
import l2p.gameserver.utils.PositionUtils;
import l2p.technical.util.Rnd;
/**
 * @author : Alice
 * @date : 08.10.2017
 * @time : 14:42
 * <p/>
 */
@RetailClass(name = "ai_bone_scorpion")
public class AiBoneScorpion extends DetectPartyWarrior {
    private int bossSkill = 449708033;
    private int poisonSkill = 449904641;

    public AiBoneScorpion(NpcInstance actor) {
        super(actor);
    }

    @Override
    public void onEvtSpawn() {
        if (myself.getPrivates() != null) {
            myself.createPrivates();
        }
        if (myself.getLeader() != null) {
            addFollowDesire2(myself.getLeader(), 100, 150 + Rnd.get(150), 90 + Rnd.get(180));
        }
        super.onEvtSpawn();
    }

    @Override
    public void onEvtPartyAttacked(Creature attacker, Creature victim, int damage) {
        super.onEvtPartyAttacked(attacker, victim, damage);
        if (attacker != null) {
            addAttackDesire(attacker, 1, 1000);
        }
    }

    @Override
    public void onEvtSeeSpell(Skill spell, Creature speller) {
        if (speller != null) {
            if (isSkillId(spell, bossSkill)) {
                if (PositionUtils.getDistance3D(myself, speller) < 600) {
                    addUseSkillDesire(speller, poisonSkill, 0, 1, Integer.MAX_VALUE);
                }
            }
        }
        super.onEvtSeeSpell(spell, speller);
    }
}

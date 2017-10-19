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
 * @time : 13:51
 * <p/>
 */
@RetailClass(name = "ai_drakos_templar")
public class AiDrakosTemplar extends DetectPartyWarrior {
    private int detectSkill = 449576961;
    private int speedUpSkill = 449642497;

    public AiDrakosTemplar(NpcInstance actor) {
        super(actor);
    }

    @Override
    public void onEvtSpawn() {
        if (myself.getPrivates() != null) {
            myself.createPrivates();
        }
        if (myself.getLeader() != null) {
            addFollowDesire2(myself.getLeader(), 100, 150 + Rnd.get(150), 45 + Rnd.get(90));
        }
        super.onEvtSpawn();
    }

    @Override
    public void onEvtAttacked(Creature attacker, int damage, Skill skill) {
        if (myself.getCurrentHp() < (myself.getMaxHp() * 0.600000)) {
            myself.lookNeighbor(600);
        }
        super.onEvtAttacked(attacker, damage, skill);
    }

    @Override
    public void onEvtSeeCreature(Creature creature) {
        if (creature.isNpc() && creature.getNpcId() == 1022824) {
            if (creature.getCurrentHp() > (creature.getMaxHp() * 0.600000)) {
                addUseSkillDesire(creature, speedUpSkill, 1, 1, Integer.MAX_VALUE);
            }
        }
        super.onEvtSeeCreature(creature);
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
            if (isSkillId(spell, detectSkill)) {
                if (PositionUtils.getDistance3D(myself, speller) < 600) {
                    addUseSkillDesire(speller, detectSkill, 0, 1, Integer.MAX_VALUE);
                }
            }
        }
        super.onEvtSeeSpell(spell, speller);
    }
}

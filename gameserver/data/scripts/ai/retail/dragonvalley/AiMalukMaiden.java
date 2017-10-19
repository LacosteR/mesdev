package ai.retail.dragonvalley;

import l2p.gameserver.ai.base.RetailClass;
import l2p.gameserver.ai.retail.WarriorUseSkill;
import l2p.gameserver.model.Creature;
import l2p.gameserver.model.Skill;
import l2p.gameserver.model.instances.NpcInstance;
import l2p.technical.util.Rnd;
/**
 * @author : Alice
 * @date : 08.10.2017
 * @time : 11:14
 * <p/>
 */
@RetailClass(name = "ai_maluk_maiden")
public class AiMalukMaiden extends WarriorUseSkill {
    private int SelfHealSkill = 448724993;
    public AiMalukMaiden(NpcInstance actor) {
        super(actor);
    }

    @Override
    public void onEvtAttacked(Creature attacker, int damage, Skill skill) {
        if(myself.getCurrentHp() < myself.getMaxHp() * 0.5 && Rnd.get(100) < 10) {
            addUseSkillDesire(myself, SelfHealSkill, 1, 0, 999999999);
        }
        super.onEvtAttacked(attacker, damage, skill);
    }
}

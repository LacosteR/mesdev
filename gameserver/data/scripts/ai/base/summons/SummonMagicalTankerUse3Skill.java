package ai.base.summons;

import l2p.gameserver.ai.SummonAI;
import l2p.gameserver.model.Creature;
import l2p.gameserver.model.Summon;
import l2p.gameserver.tables.SkillTable;

/**
 * @author : Alice
 * @date : 29.03.17
 * @time : 0:28
 * <p/>
 */
public class SummonMagicalTankerUse3Skill extends SummonAI {
    public SummonMagicalTankerUse3Skill(Summon actor) {
        super(actor);
    }

    @Override
    public void onEvtMenuSelected(int action_id) {
        switch (action_id) {
            case 1013:
                castSkill(summonAiComponent.getDebuff1(), getMaster()); // Buff1
                break;
            case 1014:
                castSkill(summonAiComponent.getDebuff2(), getMaster()); // Buff2
                break;
            case 1015:
                castSkill(summonAiComponent.getHeal(), getMaster()); // DDMagic
                break;
        }
        if(summonAiComponent.getBuffAction(3) != -1 && summonAiComponent.getBuff(3) != SkillTable.getInstance().getInfo(DEFAULT_SKILL_ID, 1)) {
            if(action_id == summonAiComponent.getBuffAction(3)) {
                castSkillTarget(summonAiComponent.getBuff(3), summonAiComponent.getBuffTarget(3), (Creature)getMaster().getTarget()); // buff3
            }
        }
        if(summonAiComponent.getBuffAction(4) != -1 && summonAiComponent.getBuff(4) != SkillTable.getInstance().getInfo(DEFAULT_SKILL_ID, 1)) {
            if(action_id == summonAiComponent.getBuffAction(4)) {
                castSkillTarget(summonAiComponent.getBuff(4), summonAiComponent.getBuffTarget(4), (Creature)getMaster().getTarget()); // buff4
            }
        }
        if(summonAiComponent.getBuffAction(5) != -1 && summonAiComponent.getBuff(5) != SkillTable.getInstance().getInfo(DEFAULT_SKILL_ID, 1)) {
            if(action_id == summonAiComponent.getBuffAction(5)) {
                castSkillTarget(summonAiComponent.getBuff(5), summonAiComponent.getBuffTarget(5), (Creature)getMaster().getTarget()); // buff5
            }
        }
    }
}

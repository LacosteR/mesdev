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
public class SummonMagicalBufferUse3Skill extends SummonAI {

    public SummonMagicalBufferUse3Skill(Summon actor) {
        super(actor);
    }

    @Override
    public void onEvtMenuSelected(int action_id) {
        switch (action_id) {
            case 1010:
                castSkill(summonAiComponent.getBuff(1), getMaster()); // Buff1
                break;
            case 1011:
                castSkill(summonAiComponent.getBuff(2), getMaster()); // Buff2
                break;
            case 1012:
                castSkill(summonAiComponent.getDdMagic(), getMaster()); // DDMagic
                break;
        }
        if(summonAiComponent.getBuffAction(3) != -1 && summonAiComponent.getBuff(3) != SkillTable.getInstance().getInfo(DEFAULT_SKILL_ID, 1)) {
            if(action_id == summonAiComponent.getBuffAction(3)) {
                castSkillTarget(summonAiComponent.getBuff(3), summonAiComponent.getBuffTarget(3),(Creature)getMaster().getTarget()); // buff3
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
        if(summonAiComponent.getBuffAction(6) != -1 && summonAiComponent.getBuff(6) != SkillTable.getInstance().getInfo(DEFAULT_SKILL_ID, 1)) {
            if(action_id == summonAiComponent.getBuffAction(6)) {
                castSkillTarget(summonAiComponent.getBuff(6), summonAiComponent.getBuffTarget(6), (Creature)getMaster().getTarget()); // buff6
            }
        }
    }
}

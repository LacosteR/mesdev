package ai.base.summons;

import l2p.gameserver.ai.SummonAI;
import l2p.gameserver.model.Creature;
import l2p.gameserver.model.Summon;

/**
 * @author : Alice
 * @date : 29.03.17
 * @time : 0:28
 * <p/>
 */
public class SummonPhysicalAttackerUse3Skill extends SummonAI {
    public SummonPhysicalAttackerUse3Skill(Summon actor) {
        super(actor);
    }

    @Override
    public void onEvtMenuSelected(int action_id) {
        switch (action_id) {
            case 1016:
                castSkill(summonAiComponent.getPhysicalSpecial1(), (Creature) getMaster().getTarget());
                break;
            case 1017:
                castSkill(summonAiComponent.getPhysicalSpecial2(), (Creature) getMaster().getTarget());
                break;
            case 1018:
                castSkill(summonAiComponent.getPhysicalSpecial3(), (Creature) getMaster().getTarget());
                break;
            case 1031:
                castSkill(summonAiComponent.getPhysicalSpecial1(), (Creature) getMaster().getTarget());
                break;
            case 1032:
                castSkill(summonAiComponent.getPhysicalSpecial2(), (Creature) getMaster().getTarget());
                break;
            case 1033:
                castSkill(summonAiComponent.getPhysicalSpecial3(), (Creature) getMaster().getTarget());
                break;
            case 1034:
                castSkill(summonAiComponent.getPhysicalSpecial1(), (Creature) getMaster().getTarget());
                break;
            case 1035:
                castSkill(summonAiComponent.getPhysicalSpecial2(), (Creature) getMaster().getTarget());
                break;
            case 1036:
                castSkill(summonAiComponent.getPhysicalSpecial1(), (Creature) getMaster().getTarget());
                break;
            case 1037:
                castSkill(summonAiComponent.getPhysicalSpecial2(), (Creature) getMaster().getTarget());
                break;
            case 1038:
                castSkill(summonAiComponent.getPhysicalSpecial3(), (Creature) getMaster().getTarget());
                break;
        }
    }
}

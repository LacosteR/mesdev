package ai.base.pets;

import l2p.gameserver.ai.PetAI;
import l2p.gameserver.ai.base.PetAiComponent;
import l2p.gameserver.data.retail.holder.NpcDataHolder;
import l2p.gameserver.model.Creature;
import l2p.gameserver.model.Summon;

/**
 * @author : Alice
 * @date : 21.12.16
 * @time : 14:05
 * <p/>
 */
public class PetWarriorUse2Skill extends PetAI {
    public PetWarriorUse2Skill(Summon actor) {
        super(actor);
        lvStepArray = new int[1];
        petAiComponent = new PetAiComponent(NpcDataHolder.getInstance().getNpcAI(getActor().getNpcId()), lvStepArray);
    }

    @Override
    public void onEvtMenuSelected(int action_id) {
        switch (action_id) {
            case 1003:
                castSkill(petAiComponent.getPhysicalSpecial().getId(), calcSkillLvl(), (Creature)getActor().getPlayer().getTarget());
                break;
            case 1004:
                castSkill(petAiComponent.getBuff().getId(), calcSkillLvl(), getActor());
                break;
        }
    }
}
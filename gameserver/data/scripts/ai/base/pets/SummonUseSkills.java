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
public class SummonUseSkills extends PetAI {
    public SummonUseSkills(Summon actor) {
        super(actor);
        lvStepArray = new int[1];
        petAiComponent = new PetAiComponent(NpcDataHolder.getInstance().getNpcAI(getActor().getNpcId()), lvStepArray);
    }

    @Override
    public void onEvtMenuSelected(int action_id) {
        if(getActor().getLevel() < 60) {
            if(action_id == petAiComponent.getSkillPet(1).getActionId()) {
                castSkill(petAiComponent.getSkillPet(1).getSkill().getId(), 1, (Creature)getActor().getPlayer().getTarget());
            }
        } else if(getActor().getLevel() < 65) {
            if(action_id == petAiComponent.getSkillPet(1).getActionId()) {
                castSkill(petAiComponent.getSkillPet(1).getSkill().getId(), 2, (Creature)getActor().getPlayer().getTarget());
            } else if(action_id == petAiComponent.getSkillPet(2).getActionId()) {
                castSkill(petAiComponent.getSkillPet(2).getSkill().getId(), 2, (Creature)getActor().getPlayer().getTarget());
            }
        } else if(getActor().getLevel() < 70) {
            if(action_id == petAiComponent.getSkillPet(1).getActionId()) {
                castSkill(petAiComponent.getSkillPet(1).getSkill().getId(), 3, (Creature)getActor().getPlayer().getTarget());
            } else if(action_id == petAiComponent.getSkillPet(2).getActionId()) {
                castSkill(petAiComponent.getSkillPet(2).getSkill().getId(), 3, (Creature)getActor().getPlayer().getTarget());
            } else if(action_id == petAiComponent.getSkillPet(3).getActionId()) {
                castSkill(petAiComponent.getSkillPet(3).getSkill().getId(), 3, (Creature)getActor().getPlayer().getTarget());
            }
        } else {
            if(action_id == petAiComponent.getSkillPet(1).getActionId()) {
                castSkill(petAiComponent.getSkillPet(1).getSkill().getId(), 4, (Creature)getActor().getPlayer().getTarget());
            } else if(action_id == petAiComponent.getSkillPet(2).getActionId()) {
                castSkill(petAiComponent.getSkillPet(2).getSkill().getId(), 4, (Creature)getActor().getPlayer().getTarget());
            } else if(action_id == petAiComponent.getSkillPet(3).getActionId()) {
                castSkill(petAiComponent.getSkillPet(3).getSkill().getId(), 4, (Creature)getActor().getPlayer().getTarget());
            } else if(action_id == petAiComponent.getSkillPet(4).getActionId()) {
                castSkill(petAiComponent.getSkillPet(4).getSkill().getId(), 4, (Creature)getActor().getPlayer().getTarget());
            }
        }
    }
}
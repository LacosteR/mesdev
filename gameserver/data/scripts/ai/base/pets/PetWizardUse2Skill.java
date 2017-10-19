package ai.base.pets;

import l2p.gameserver.ai.PetAI;
import l2p.gameserver.ai.base.PetAiComponent;
import l2p.gameserver.data.retail.holder.NpcDataHolder;
import l2p.gameserver.model.Creature;
import l2p.gameserver.model.Skill;
import l2p.gameserver.model.Summon;

/**
 * @author : Alice
 * @date : 21.12.16
 * @time : 14:05
 * <p/>
 */
public class PetWizardUse2Skill extends PetAI {
    public PetWizardUse2Skill(Summon actor) {
        super(actor);
        lvStepArray = new int[1];
        petAiComponent = new PetAiComponent(NpcDataHolder.getInstance().getNpcAI(getActor().getNpcId()), lvStepArray);
    }

    @Override
    public void onEvtMenuSelected(int action_id) {
        switch (action_id) {
            case 1005:
                castSkill(petAiComponent.getDdMagic().getId(), calcSkillLvl(), (Creature)getActor().getPlayer().getTarget());
                break;
            case 1006:
                castSkill(petAiComponent.getHeal().getId(), calcSkillLvl(), getActor());
                break;
        }
    }

    @Override
    public void onEvtUseSkillFinished(Creature target, Skill skill) {
        castSkill(petAiComponent.getHeal().getId(), calcSkillLvl(), (Creature)getActor().getPlayer().getTarget());
    }
}
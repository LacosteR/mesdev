package ai.base.pets;

import l2p.gameserver.ai.PetAI;
import l2p.gameserver.ai.base.PetAiComponent;
import l2p.gameserver.data.retail.holder.NpcDataHolder;
import l2p.gameserver.model.Summon;
import l2p.technical.util.Rnd;

/**
 * @author : Alice
 * @date : 21.12.16
 * @time : 14:05
 * <p/>
 */
public class PetCuteBabyUse2Skill extends PetAI {
    public PetCuteBabyUse2Skill(Summon actor) {
        super(actor);
        addTimer(3000, 1000);
        lvStepArray = new int[1];
        petAiComponent = new PetAiComponent(NpcDataHolder.getInstance().getNpcAI(getActor().getNpcId()), lvStepArray);
    }

    @Override
    protected void onEvtTimer(int timerId, Object arg1, Object arg2) {
        if (timerId == 3000) {
            if (Rnd.get(100) <= 25) {
                if (getActor().getPlayer().getCurrentHpPercents() > 0 && getActor().getPlayer().getCurrentHpPercents() < 80) {
                    castSkill(petAiComponent.getHeal1().getId(), calcSkillLvl(), getActor().getPlayer());
                }
            }
            if (Rnd.get(100) <= 75) {
                if (getActor().getPlayer().getCurrentHpPercents() > 0 && getActor().getPlayer().getCurrentHpPercents() < 15) {
                    castSkill(petAiComponent.getHeal2().getId(), calcSkillLvl(), getActor().getPlayer());
                }
            }
            addTimer(3000, 1000);
        }
        super.onEvtTimer(timerId, arg1, arg2);
    }
}
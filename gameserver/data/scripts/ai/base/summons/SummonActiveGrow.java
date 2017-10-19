package ai.base.summons;

import l2p.gameserver.ai.SummonAI;
import l2p.gameserver.ai.base.PetAiComponent;
import l2p.gameserver.ai.base.StepAI;
import l2p.gameserver.data.retail.holder.NpcDataHolder;
import l2p.gameserver.model.Creature;
import l2p.gameserver.model.Player;
import l2p.gameserver.model.Summon;

/**
 * @author : Alice
 * @date : 29.03.17
 * @time : 13:23
 * <p/>
 */
public class SummonActiveGrow extends SummonAI{
    protected PetAiComponent petAiComponent;
    protected int[] lvStepArray;
    public SummonActiveGrow(Summon actor) {
        super(actor);
        lvStepArray = new int[]{60, 65, 70, 75, 80, 85};
        petAiComponent = new PetAiComponent(NpcDataHolder.getInstance().getNpcAI(getActor().getNpcId()), lvStepArray);
        //addTimer(1131, 2000);   Убрано, т.к. отсутствует код реализации таимера. Или декомпилер тупанул, или его и быть там не должно
    }

    @Override
    public void onEvtMenuSelected(int action_id) {
        Player master = getActor().getPlayer();
        if(getActor().getLevel() < petAiComponent.getStepAI(STEP_LEVEL_ZERO).getStepLv()) { // step0
            StepAI current_step_ai = petAiComponent.getStepAI(STEP_LEVEL_ZERO);
            if(current_step_ai.getSkill(SKILL_NUMBER_ONE).getId() != DEFAULT_SKILL_ID && current_step_ai.getAction(SKILL_NUMBER_ONE, -1) != -1) {
                if(current_step_ai.getAction(SKILL_NUMBER_ONE, -1) == action_id) {
                    castSkillTarget(current_step_ai.getSkill(SKILL_NUMBER_ONE), current_step_ai.getSkillTargetType(SKILL_NUMBER_ONE, DEFAULT_SKILL_TARGET), (Creature)master.getTarget());
                }
            }
            if(current_step_ai.getSkill(SKILL_NUMBER_TWO).getId() != DEFAULT_SKILL_ID && current_step_ai.getAction(SKILL_NUMBER_TWO, -1) != -1) {
                if(current_step_ai.getAction(SKILL_NUMBER_TWO, -1) == action_id) {
                    castSkillTarget(current_step_ai.getSkill(SKILL_NUMBER_TWO), current_step_ai.getSkillTargetType(SKILL_NUMBER_TWO, DEFAULT_SKILL_TARGET), (Creature)master.getTarget());
                }
            }
            if(current_step_ai.getSkill(SKILL_NUMBER_THREE).getId() != DEFAULT_SKILL_ID && current_step_ai.getAction(SKILL_NUMBER_THREE, -1) != -1) {
                if(current_step_ai.getAction(SKILL_NUMBER_THREE, -1) == action_id) {
                    castSkillTarget(current_step_ai.getSkill(SKILL_NUMBER_THREE), current_step_ai.getSkillTargetType(SKILL_NUMBER_THREE, DEFAULT_SKILL_TARGET), (Creature)master.getTarget());
                }
            }
        } else if(getActor().getLevel() < petAiComponent.getStepAI(STEP_LEVEL_ONE).getStepLv()) { // step1
            StepAI current_step_ai = petAiComponent.getStepAI(STEP_LEVEL_ONE);
            if(current_step_ai.getSkill(SKILL_NUMBER_ONE).getId() != DEFAULT_SKILL_ID && current_step_ai.getAction(SKILL_NUMBER_ONE, -1) != -1) {
                if(current_step_ai.getAction(SKILL_NUMBER_ONE, -1) == action_id) {
                    castSkillTarget(current_step_ai.getSkill(SKILL_NUMBER_ONE), current_step_ai.getSkillTargetType(SKILL_NUMBER_ONE, DEFAULT_SKILL_TARGET), (Creature)master.getTarget());
                }
            }
            if(current_step_ai.getSkill(SKILL_NUMBER_TWO).getId() != DEFAULT_SKILL_ID && current_step_ai.getAction(SKILL_NUMBER_TWO, -1) != -1) {
                if(current_step_ai.getAction(SKILL_NUMBER_TWO, -1) == action_id) {
                    castSkillTarget(current_step_ai.getSkill(SKILL_NUMBER_TWO), current_step_ai.getSkillTargetType(SKILL_NUMBER_TWO, DEFAULT_SKILL_TARGET), (Creature)master.getTarget());
                }
            }
            if(current_step_ai.getSkill(SKILL_NUMBER_THREE).getId() != DEFAULT_SKILL_ID && current_step_ai.getAction(SKILL_NUMBER_THREE, -1) != -1) {
                if(current_step_ai.getAction(SKILL_NUMBER_THREE, -1) == action_id) {
                    castSkillTarget(current_step_ai.getSkill(SKILL_NUMBER_THREE), current_step_ai.getSkillTargetType(SKILL_NUMBER_THREE, DEFAULT_SKILL_TARGET), (Creature)master.getTarget());
                }
            }
            if(current_step_ai.getSkill(SKILL_NUMBER_FOUR).getId() != DEFAULT_SKILL_ID && current_step_ai.getAction(SKILL_NUMBER_FOUR, -1) != -1) {
                if(current_step_ai.getAction(SKILL_NUMBER_FOUR, -1) == action_id) {
                    castSkillTarget(current_step_ai.getSkill(SKILL_NUMBER_FOUR), current_step_ai.getSkillTargetType(SKILL_NUMBER_FOUR, DEFAULT_SKILL_TARGET), (Creature)master.getTarget());
                }
            }
        } else if(getActor().getLevel() < petAiComponent.getStepAI(STEP_LEVEL_TWO).getStepLv()) { // step2
            StepAI current_step_ai = petAiComponent.getStepAI(STEP_LEVEL_TWO);
            if(current_step_ai.getSkill(SKILL_NUMBER_ONE).getId() != DEFAULT_SKILL_ID && current_step_ai.getAction(SKILL_NUMBER_ONE, -1) != -1) {
                if(current_step_ai.getAction(SKILL_NUMBER_ONE, -1) == action_id) {
                    castSkillTarget(current_step_ai.getSkill(SKILL_NUMBER_ONE), current_step_ai.getSkillTargetType(SKILL_NUMBER_ONE, DEFAULT_SKILL_TARGET), (Creature)master.getTarget());
                }
            }
            if(current_step_ai.getSkill(SKILL_NUMBER_TWO).getId() != DEFAULT_SKILL_ID && current_step_ai.getAction(SKILL_NUMBER_TWO, -1) != -1) {
                if(current_step_ai.getAction(SKILL_NUMBER_TWO, -1) == action_id) {
                    castSkillTarget(current_step_ai.getSkill(SKILL_NUMBER_TWO), current_step_ai.getSkillTargetType(SKILL_NUMBER_TWO, DEFAULT_SKILL_TARGET), (Creature)master.getTarget());
                }
            }
            if(current_step_ai.getSkill(SKILL_NUMBER_THREE).getId() != DEFAULT_SKILL_ID && current_step_ai.getAction(SKILL_NUMBER_THREE, -1) != -1) {
                if(current_step_ai.getAction(SKILL_NUMBER_THREE, -1) == action_id) {
                    castSkillTarget(current_step_ai.getSkill(SKILL_NUMBER_THREE), current_step_ai.getSkillTargetType(SKILL_NUMBER_THREE, DEFAULT_SKILL_TARGET), (Creature)master.getTarget());
                }
            }
            if(current_step_ai.getSkill(SKILL_NUMBER_FOUR).getId() != DEFAULT_SKILL_ID && current_step_ai.getAction(SKILL_NUMBER_FOUR, -1) != -1) {
                if(current_step_ai.getAction(SKILL_NUMBER_FOUR, -1) == action_id) {
                    castSkillTarget(current_step_ai.getSkill(SKILL_NUMBER_FOUR), current_step_ai.getSkillTargetType(SKILL_NUMBER_FOUR, DEFAULT_SKILL_TARGET), (Creature)master.getTarget());
                }
            }
            if(current_step_ai.getSkill(SKILL_NUMBER_FIVE).getId() != DEFAULT_SKILL_ID && current_step_ai.getAction(SKILL_NUMBER_FIVE, -1) != -1) {
                if(current_step_ai.getAction(SKILL_NUMBER_FIVE, -1) == action_id) {
                    castSkillTarget(current_step_ai.getSkill(SKILL_NUMBER_FIVE), current_step_ai.getSkillTargetType(SKILL_NUMBER_FIVE, DEFAULT_SKILL_TARGET), (Creature)master.getTarget());
                }
            }
            if(current_step_ai.getSkill(SKILL_NUMBER_SIX).getId() != DEFAULT_SKILL_ID && current_step_ai.getAction(SKILL_NUMBER_SIX, -1) != -1) {
                if(current_step_ai.getAction(SKILL_NUMBER_SIX, -1) == action_id) {
                    castSkillTarget(current_step_ai.getSkill(SKILL_NUMBER_SIX), current_step_ai.getSkillTargetType(SKILL_NUMBER_SIX, DEFAULT_SKILL_TARGET), (Creature)master.getTarget());
                }
            }
        } else if(getActor().getLevel() < petAiComponent.getStepAI(STEP_LEVEL_THREE).getStepLv()) { // step3
            StepAI current_step_ai = petAiComponent.getStepAI(STEP_LEVEL_THREE);
            if(current_step_ai.getSkill(SKILL_NUMBER_ONE).getId() != DEFAULT_SKILL_ID && current_step_ai.getAction(SKILL_NUMBER_ONE, -1) != -1) {
                if(current_step_ai.getAction(SKILL_NUMBER_ONE, -1) == action_id) {
                    castSkillTarget(current_step_ai.getSkill(SKILL_NUMBER_ONE), current_step_ai.getSkillTargetType(SKILL_NUMBER_ONE, DEFAULT_SKILL_TARGET), (Creature)master.getTarget());
                }
            }
            if(current_step_ai.getSkill(SKILL_NUMBER_TWO).getId() != DEFAULT_SKILL_ID && current_step_ai.getAction(SKILL_NUMBER_TWO, -1) != -1) {
                if(current_step_ai.getAction(SKILL_NUMBER_TWO, -1) == action_id) {
                    castSkillTarget(current_step_ai.getSkill(SKILL_NUMBER_TWO), current_step_ai.getSkillTargetType(SKILL_NUMBER_TWO, DEFAULT_SKILL_TARGET), (Creature)master.getTarget());
                }
            }
            if(current_step_ai.getSkill(SKILL_NUMBER_THREE).getId() != DEFAULT_SKILL_ID && current_step_ai.getAction(SKILL_NUMBER_THREE, -1) != -1) {
                if(current_step_ai.getAction(SKILL_NUMBER_THREE, -1) == action_id) {
                    castSkillTarget(current_step_ai.getSkill(SKILL_NUMBER_THREE), current_step_ai.getSkillTargetType(SKILL_NUMBER_THREE, DEFAULT_SKILL_TARGET), (Creature)master.getTarget());
                }
            }
            if(current_step_ai.getSkill(SKILL_NUMBER_FOUR).getId() != DEFAULT_SKILL_ID && current_step_ai.getAction(SKILL_NUMBER_FOUR, -1) != -1) {
                if(current_step_ai.getAction(SKILL_NUMBER_FOUR, -1) == action_id) {
                    castSkillTarget(current_step_ai.getSkill(SKILL_NUMBER_FOUR), current_step_ai.getSkillTargetType(SKILL_NUMBER_FOUR, DEFAULT_SKILL_TARGET), (Creature)master.getTarget());
                }
            }
            if(current_step_ai.getSkill(SKILL_NUMBER_FIVE).getId() != DEFAULT_SKILL_ID && current_step_ai.getAction(SKILL_NUMBER_FIVE, -1) != -1) {
                if(current_step_ai.getAction(SKILL_NUMBER_FIVE, -1) == action_id) {
                    castSkillTarget(current_step_ai.getSkill(SKILL_NUMBER_FIVE), current_step_ai.getSkillTargetType(SKILL_NUMBER_FIVE, DEFAULT_SKILL_TARGET), (Creature)master.getTarget());
                }
            }
            if(current_step_ai.getSkill(SKILL_NUMBER_SIX).getId() != DEFAULT_SKILL_ID && current_step_ai.getAction(SKILL_NUMBER_SIX, -1) != -1) {
                if(current_step_ai.getAction(SKILL_NUMBER_SIX, -1) == action_id) {
                    castSkillTarget(current_step_ai.getSkill(SKILL_NUMBER_SIX), current_step_ai.getSkillTargetType(SKILL_NUMBER_SIX, DEFAULT_SKILL_TARGET), (Creature)master.getTarget());
                }
            }
            if(current_step_ai.getSkill(SKILL_NUMBER_SEVEN).getId() != DEFAULT_SKILL_ID && current_step_ai.getAction(SKILL_NUMBER_SEVEN, -1) != -1) {
                if(current_step_ai.getAction(SKILL_NUMBER_SEVEN, -1) == action_id) {
                    castSkillTarget(current_step_ai.getSkill(SKILL_NUMBER_SEVEN), current_step_ai.getSkillTargetType(SKILL_NUMBER_SEVEN, DEFAULT_SKILL_TARGET), (Creature)master.getTarget());
                }
            }
            if(current_step_ai.getSkill(SKILL_NUMBER_EIGHT).getId() != DEFAULT_SKILL_ID && current_step_ai.getAction(SKILL_NUMBER_EIGHT, -1) != -1) {
                if(current_step_ai.getAction(SKILL_NUMBER_EIGHT, -1) == action_id) {
                    castSkillTarget(current_step_ai.getSkill(SKILL_NUMBER_EIGHT), current_step_ai.getSkillTargetType(SKILL_NUMBER_EIGHT, DEFAULT_SKILL_TARGET), (Creature)master.getTarget());
                }
            }
        } else if(getActor().getLevel() < petAiComponent.getStepAI(STEP_LEVEL_FOUR).getStepLv()) { // step4
            StepAI current_step_ai = petAiComponent.getStepAI(STEP_LEVEL_FOUR);
            if(current_step_ai.getSkill(SKILL_NUMBER_ONE).getId() != DEFAULT_SKILL_ID && current_step_ai.getAction(SKILL_NUMBER_ONE, -1) != -1) {
                if(current_step_ai.getAction(SKILL_NUMBER_ONE, -1) == action_id) {
                    castSkillTarget(current_step_ai.getSkill(SKILL_NUMBER_ONE), current_step_ai.getSkillTargetType(SKILL_NUMBER_ONE, DEFAULT_SKILL_TARGET), (Creature)master.getTarget());
                }
            }
            if(current_step_ai.getSkill(SKILL_NUMBER_TWO).getId() != DEFAULT_SKILL_ID && current_step_ai.getAction(SKILL_NUMBER_TWO, -1) != -1) {
                if(current_step_ai.getAction(SKILL_NUMBER_TWO, -1) == action_id) {
                    castSkillTarget(current_step_ai.getSkill(SKILL_NUMBER_TWO), current_step_ai.getSkillTargetType(SKILL_NUMBER_TWO, DEFAULT_SKILL_TARGET), (Creature)master.getTarget());
                }
            }
            if(current_step_ai.getSkill(SKILL_NUMBER_THREE).getId() != DEFAULT_SKILL_ID && current_step_ai.getAction(SKILL_NUMBER_THREE, -1) != -1) {
                if(current_step_ai.getAction(SKILL_NUMBER_THREE, -1) == action_id) {
                    castSkillTarget(current_step_ai.getSkill(SKILL_NUMBER_THREE), current_step_ai.getSkillTargetType(SKILL_NUMBER_THREE, DEFAULT_SKILL_TARGET), (Creature)master.getTarget());
                }
            }
            if(current_step_ai.getSkill(SKILL_NUMBER_FOUR).getId() != DEFAULT_SKILL_ID && current_step_ai.getAction(SKILL_NUMBER_FOUR, -1) != -1) {
                if(current_step_ai.getAction(SKILL_NUMBER_FOUR, -1) == action_id) {
                    castSkillTarget(current_step_ai.getSkill(SKILL_NUMBER_FOUR), current_step_ai.getSkillTargetType(SKILL_NUMBER_FOUR, DEFAULT_SKILL_TARGET), (Creature)master.getTarget());
                }
            }
            if(current_step_ai.getSkill(SKILL_NUMBER_FIVE).getId() != DEFAULT_SKILL_ID && current_step_ai.getAction(SKILL_NUMBER_FIVE, -1) != -1) {
                if(current_step_ai.getAction(SKILL_NUMBER_FIVE, -1) == action_id) {
                    castSkillTarget(current_step_ai.getSkill(SKILL_NUMBER_FIVE), current_step_ai.getSkillTargetType(SKILL_NUMBER_FIVE, DEFAULT_SKILL_TARGET), (Creature)master.getTarget());
                }
            }
            if(current_step_ai.getSkill(SKILL_NUMBER_SIX).getId() != DEFAULT_SKILL_ID && current_step_ai.getAction(SKILL_NUMBER_SIX, -1) != -1) {
                if(current_step_ai.getAction(SKILL_NUMBER_SIX, -1) == action_id) {
                    castSkillTarget(current_step_ai.getSkill(SKILL_NUMBER_SIX), current_step_ai.getSkillTargetType(SKILL_NUMBER_SIX, DEFAULT_SKILL_TARGET), (Creature)master.getTarget());
                }
            }
            if(current_step_ai.getSkill(SKILL_NUMBER_SEVEN).getId() != DEFAULT_SKILL_ID && current_step_ai.getAction(SKILL_NUMBER_SEVEN, -1) != -1) {
                if(current_step_ai.getAction(SKILL_NUMBER_SEVEN, -1) == action_id) {
                    castSkillTarget(current_step_ai.getSkill(SKILL_NUMBER_SEVEN), current_step_ai.getSkillTargetType(SKILL_NUMBER_SEVEN, DEFAULT_SKILL_TARGET), (Creature)master.getTarget());
                }
            }
            if(current_step_ai.getSkill(SKILL_NUMBER_EIGHT).getId() != DEFAULT_SKILL_ID && current_step_ai.getAction(SKILL_NUMBER_EIGHT, -1) != -1) {
                if(current_step_ai.getAction(SKILL_NUMBER_EIGHT, -1) == action_id) {
                    castSkillTarget(current_step_ai.getSkill(SKILL_NUMBER_EIGHT), current_step_ai.getSkillTargetType(SKILL_NUMBER_EIGHT, DEFAULT_SKILL_TARGET), (Creature)master.getTarget());
                }
            }
        } else if(getActor().getLevel() < petAiComponent.getStepAI(STEP_LEVEL_FIVE).getStepLv()) { // step5
            StepAI current_step_ai = petAiComponent.getStepAI(STEP_LEVEL_FIVE);
            if(current_step_ai.getSkill(SKILL_NUMBER_ONE).getId() != DEFAULT_SKILL_ID && current_step_ai.getAction(SKILL_NUMBER_ONE, -1) != -1) {
                if(current_step_ai.getAction(SKILL_NUMBER_ONE, -1) == action_id) {
                    castSkillTarget(current_step_ai.getSkill(SKILL_NUMBER_ONE), current_step_ai.getSkillTargetType(SKILL_NUMBER_ONE, DEFAULT_SKILL_TARGET), (Creature)master.getTarget());
                }
            }
            if(current_step_ai.getSkill(SKILL_NUMBER_TWO).getId() != DEFAULT_SKILL_ID && current_step_ai.getAction(SKILL_NUMBER_TWO, -1) != -1) {
                if(current_step_ai.getAction(SKILL_NUMBER_TWO, -1) == action_id) {
                    castSkillTarget(current_step_ai.getSkill(SKILL_NUMBER_TWO), current_step_ai.getSkillTargetType(SKILL_NUMBER_TWO, DEFAULT_SKILL_TARGET), (Creature)master.getTarget());
                }
            }
            if(current_step_ai.getSkill(SKILL_NUMBER_THREE).getId() != DEFAULT_SKILL_ID && current_step_ai.getAction(SKILL_NUMBER_THREE, -1) != -1) {
                if(current_step_ai.getAction(SKILL_NUMBER_THREE, -1) == action_id) {
                    castSkillTarget(current_step_ai.getSkill(SKILL_NUMBER_THREE), current_step_ai.getSkillTargetType(SKILL_NUMBER_THREE, DEFAULT_SKILL_TARGET), (Creature)master.getTarget());
                }
            }
            if(current_step_ai.getSkill(SKILL_NUMBER_FOUR).getId() != DEFAULT_SKILL_ID && current_step_ai.getAction(SKILL_NUMBER_FOUR, -1) != -1) {
                if(current_step_ai.getAction(SKILL_NUMBER_FOUR, -1) == action_id) {
                    castSkillTarget(current_step_ai.getSkill(SKILL_NUMBER_FOUR), current_step_ai.getSkillTargetType(SKILL_NUMBER_FOUR, DEFAULT_SKILL_TARGET), (Creature)master.getTarget());
                }
            }
            if(current_step_ai.getSkill(SKILL_NUMBER_FIVE).getId() != DEFAULT_SKILL_ID && current_step_ai.getAction(SKILL_NUMBER_FIVE, -1) != -1) {
                if(current_step_ai.getAction(SKILL_NUMBER_FIVE, -1) == action_id) {
                    castSkillTarget(current_step_ai.getSkill(SKILL_NUMBER_FIVE), current_step_ai.getSkillTargetType(SKILL_NUMBER_FIVE, DEFAULT_SKILL_TARGET), (Creature)master.getTarget());
                }
            }
            if(current_step_ai.getSkill(SKILL_NUMBER_SIX).getId() != DEFAULT_SKILL_ID && current_step_ai.getAction(SKILL_NUMBER_SIX, -1) != -1) {
                if(current_step_ai.getAction(SKILL_NUMBER_SIX, -1) == action_id) {
                    castSkillTarget(current_step_ai.getSkill(SKILL_NUMBER_SIX), current_step_ai.getSkillTargetType(SKILL_NUMBER_SIX, DEFAULT_SKILL_TARGET), (Creature)master.getTarget());
                }
            }
            if(current_step_ai.getSkill(SKILL_NUMBER_SEVEN).getId() != DEFAULT_SKILL_ID && current_step_ai.getAction(SKILL_NUMBER_SEVEN, -1) != -1) {
                if(current_step_ai.getAction(SKILL_NUMBER_SEVEN, -1) == action_id) {
                    castSkillTarget(current_step_ai.getSkill(SKILL_NUMBER_SEVEN), current_step_ai.getSkillTargetType(SKILL_NUMBER_SEVEN, DEFAULT_SKILL_TARGET), (Creature)master.getTarget());
                }
            }
            if(current_step_ai.getSkill(SKILL_NUMBER_EIGHT).getId() != DEFAULT_SKILL_ID && current_step_ai.getAction(SKILL_NUMBER_EIGHT, -1) != -1) {
                if(current_step_ai.getAction(SKILL_NUMBER_EIGHT, -1) == action_id) {
                    castSkillTarget(current_step_ai.getSkill(SKILL_NUMBER_EIGHT), current_step_ai.getSkillTargetType(SKILL_NUMBER_EIGHT, DEFAULT_SKILL_TARGET), (Creature)master.getTarget());
                }
            }
        } else if(getActor().getLevel() >= petAiComponent.getStepAI(STEP_LEVEL_FIVE).getStepLv()) { // step6
            StepAI current_step_ai = petAiComponent.getStepAI(STEP_LEVEL_FIVE);
            if(current_step_ai.getSkill(SKILL_NUMBER_ONE).getId() != DEFAULT_SKILL_ID && current_step_ai.getAction(SKILL_NUMBER_ONE, -1) != -1) {
                if(current_step_ai.getAction(SKILL_NUMBER_ONE, -1) == action_id) {
                    castSkillTarget(current_step_ai.getSkill(SKILL_NUMBER_ONE), current_step_ai.getSkillTargetType(SKILL_NUMBER_ONE, DEFAULT_SKILL_TARGET), (Creature)master.getTarget());
                }
            }
            if(current_step_ai.getSkill(SKILL_NUMBER_TWO).getId() != DEFAULT_SKILL_ID && current_step_ai.getAction(SKILL_NUMBER_TWO, -1) != -1) {
                if(current_step_ai.getAction(SKILL_NUMBER_TWO, -1) == action_id) {
                    castSkillTarget(current_step_ai.getSkill(SKILL_NUMBER_TWO), current_step_ai.getSkillTargetType(SKILL_NUMBER_TWO, DEFAULT_SKILL_TARGET), (Creature)master.getTarget());
                }
            }
            if(current_step_ai.getSkill(SKILL_NUMBER_THREE).getId() != DEFAULT_SKILL_ID && current_step_ai.getAction(SKILL_NUMBER_THREE, -1) != -1) {
                if(current_step_ai.getAction(SKILL_NUMBER_THREE, -1) == action_id) {
                    castSkillTarget(current_step_ai.getSkill(SKILL_NUMBER_THREE), current_step_ai.getSkillTargetType(SKILL_NUMBER_THREE, DEFAULT_SKILL_TARGET), (Creature)master.getTarget());
                }
            }
            if(current_step_ai.getSkill(SKILL_NUMBER_FOUR).getId() != DEFAULT_SKILL_ID && current_step_ai.getAction(SKILL_NUMBER_FOUR, -1) != -1) {
                if(current_step_ai.getAction(SKILL_NUMBER_FOUR, -1) == action_id) {
                    castSkillTarget(current_step_ai.getSkill(SKILL_NUMBER_FOUR), current_step_ai.getSkillTargetType(SKILL_NUMBER_FOUR, DEFAULT_SKILL_TARGET), (Creature)master.getTarget());
                }
            }
            if(current_step_ai.getSkill(SKILL_NUMBER_FIVE).getId() != DEFAULT_SKILL_ID && current_step_ai.getAction(SKILL_NUMBER_FIVE, -1) != -1) {
                if(current_step_ai.getAction(SKILL_NUMBER_FIVE, -1) == action_id) {
                    castSkillTarget(current_step_ai.getSkill(SKILL_NUMBER_FIVE), current_step_ai.getSkillTargetType(SKILL_NUMBER_FIVE, DEFAULT_SKILL_TARGET), (Creature)master.getTarget());
                }
            }
            if(current_step_ai.getSkill(SKILL_NUMBER_SIX).getId() != DEFAULT_SKILL_ID && current_step_ai.getAction(SKILL_NUMBER_SIX, -1) != -1) {
                if(current_step_ai.getAction(SKILL_NUMBER_SIX, -1) == action_id) {
                    castSkillTarget(current_step_ai.getSkill(SKILL_NUMBER_SIX), current_step_ai.getSkillTargetType(SKILL_NUMBER_SIX, DEFAULT_SKILL_TARGET), (Creature)master.getTarget());
                }
            }
            if(current_step_ai.getSkill(SKILL_NUMBER_SEVEN).getId() != DEFAULT_SKILL_ID && current_step_ai.getAction(SKILL_NUMBER_SEVEN, -1) != -1) {
                if(current_step_ai.getAction(SKILL_NUMBER_SEVEN, -1) == action_id) {
                    castSkillTarget(current_step_ai.getSkill(SKILL_NUMBER_SEVEN), current_step_ai.getSkillTargetType(SKILL_NUMBER_SEVEN, DEFAULT_SKILL_TARGET), (Creature)master.getTarget());
                }
            }
            if(current_step_ai.getSkill(SKILL_NUMBER_EIGHT).getId() != DEFAULT_SKILL_ID && current_step_ai.getAction(SKILL_NUMBER_EIGHT, -1) != -1) {
                if(current_step_ai.getAction(SKILL_NUMBER_EIGHT, -1) == action_id) {
                    castSkillTarget(current_step_ai.getSkill(SKILL_NUMBER_EIGHT), current_step_ai.getSkillTargetType(SKILL_NUMBER_EIGHT, DEFAULT_SKILL_TARGET), (Creature)master.getTarget());
                }
            }
        }
    }
}

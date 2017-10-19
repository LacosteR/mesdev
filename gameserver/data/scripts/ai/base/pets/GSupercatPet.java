package ai.base.pets;

import l2p.gameserver.ai.base.StepAI;
import l2p.gameserver.model.Creature;
import l2p.gameserver.model.Player;
import l2p.gameserver.model.Summon;
import l2p.gameserver.network.serverpackets.components.SystemMsg;

/**
 * @author : Alice
 * @date : 27.03.17
 * @time : 21:13
 * <p/>
 */
public class GSupercatPet extends PetPremiumHybrid {
    public GSupercatPet(Summon actor) {
        super(actor);
    }

    @Override
    public void onEvtMenuSelected(int action_id) {
        Player master = getActor().getPlayer();
        StepAI act_step_ai = petAiComponent.getStepAI(STEP_LEVEL_ZERO);

        if (act_step_ai.getAction(SKILL_NUMBER_SEVEN, -1) == action_id && getActor().i_ai0 == 0) {
            if (act_step_ai.getSkill(SKILL_NUMBER_SEVEN).getId() != DEFAULT_SKILL_ID && act_step_ai.getAction(SKILL_NUMBER_SEVEN, -1) != -1) {
                if (act_step_ai.getAction(SKILL_NUMBER_SEVEN, -1) == action_id) {
                    castSkillTarget(act_step_ai.getSkill(SKILL_NUMBER_SEVEN), act_step_ai.getSkillTargetType(SKILL_NUMBER_SEVEN, DEFAULT_SKILL_TARGET), (Creature) master.getTarget());
                }
            }
        } else if (act_step_ai.getAction(SKILL_NUMBER_SIX, -1) == action_id && getActor().i_ai0 == 0) {
            if (act_step_ai.getSkill(SKILL_NUMBER_SIX).getId() != DEFAULT_SKILL_ID && act_step_ai.getAction(SKILL_NUMBER_SIX, -1) != -1) {
                if (act_step_ai.getAction(SKILL_NUMBER_SIX, -1) == action_id) {
                    castSkillTarget(act_step_ai.getSkill(SKILL_NUMBER_SIX), act_step_ai.getSkillTargetType(SKILL_NUMBER_SIX, DEFAULT_SKILL_TARGET), (Creature) master.getTarget());
                }
            }
        }

        if (act_step_ai.getSkill(SKILL_NUMBER_FIVE).getId() != DEFAULT_SKILL_ID && act_step_ai.getAction(SKILL_NUMBER_FIVE, -1) != -1) {
            if (act_step_ai.getAction(SKILL_NUMBER_FIVE, -1) == action_id) {
                if (getActor().i_ai0 == 1) {
                    getActor().i_ai0 = 0;
                } else {
                    getActor().i_ai0 = 1;
                }
                castSkillTarget(act_step_ai.getSkill(SKILL_NUMBER_FIVE), act_step_ai.getSkillTargetType(SKILL_NUMBER_FIVE, DEFAULT_SKILL_TARGET), (Creature) master.getTarget());
            }
        }
        if (getActor().i_ai0 != 0) {
            getActor().getPlayer().sendPacket(SystemMsg.A_PET_ON_AUXILIARY_MODE_CANNOT_USE_SKILLS);
            return;
        }

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
            if(current_step_ai.getSkill(SKILL_NUMBER_FOUR).getId() != DEFAULT_SKILL_ID && current_step_ai.getAction(SKILL_NUMBER_FOUR, -1) != -1) {
                if(current_step_ai.getAction(SKILL_NUMBER_FOUR, -1) == action_id) {
                    castSkillTarget(current_step_ai.getSkill(SKILL_NUMBER_FOUR), current_step_ai.getSkillTargetType(SKILL_NUMBER_FOUR, DEFAULT_SKILL_TARGET), (Creature)master.getTarget());
                }
            }
        }

        if(getActor().getLevel() < petAiComponent.getStepAI(STEP_LEVEL_ONE).getStepLv()) { // step1
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
        }

        if(getActor().getLevel() < petAiComponent.getStepAI(STEP_LEVEL_TWO).getStepLv()) { // step2
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
        }

        if(getActor().getLevel() < petAiComponent.getStepAI(STEP_LEVEL_THREE).getStepLv()) { // step3
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
        }

        if(getActor().getLevel() < petAiComponent.getStepAI(STEP_LEVEL_FOUR).getStepLv()) { // step4
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
        }

        if(getActor().getLevel() < petAiComponent.getStepAI(STEP_LEVEL_FIVE).getStepLv()) { // step5
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
        }

        if(getActor().getLevel() < petAiComponent.getStepAI(STEP_LEVEL_SIX).getStepLv()) { // step6
            StepAI current_step_ai = petAiComponent.getStepAI(STEP_LEVEL_SIX);
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
        }

        if(getActor().getLevel() >= petAiComponent.getStepAI(STEP_LEVEL_SIX).getStepLv()) { // step7
            StepAI current_step_ai = petAiComponent.getStepAI(STEP_LEVEL_SIX);
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
        }
    }
}

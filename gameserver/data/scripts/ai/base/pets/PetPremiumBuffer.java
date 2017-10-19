package ai.base.pets;

import l2p.gameserver.ai.PetAI;
import l2p.gameserver.ai.base.PetAiComponent;
import l2p.gameserver.ai.base.StepAI;
import l2p.gameserver.data.retail.holder.NpcDataHolder;
import l2p.gameserver.model.Creature;
import l2p.gameserver.model.Player;
import l2p.gameserver.model.Summon;

/**
 * @author : Alice
 * @date : 21.12.16
 * @time : 14:05
 * <p/>
 */
public class PetPremiumBuffer extends PetAI {
    /*
    int	lv_step1 = 10;
	int	lv_step2 = 20;
	int	lv_step3 = 30;
	int	lv_step4 = 40;
	int	lv_step5 = 50;
	int	lv_step6 = 60;
	int	lv_step7 = 70;
     */
    public PetPremiumBuffer(Summon actor) {
        super(actor);
        lvStepArray = new int[]{10, 20, 30, 40, 50, 60, 70};
        petAiComponent = new PetAiComponent(NpcDataHolder.getInstance().getNpcAI(getActor().getNpcId()), lvStepArray);
        if (petAiComponent.getMoveAroundSocial() > 0) {
            addTimer(1671, 10000);
        }
        addTimer(2001, buffTime);
        addTimer(2002, healDelay * 1000);
    }

    @Override
    public void onEvtMenuSelected(int action_id) {
        Player master = getActor().getPlayer();
        if (getActor().getLevel() < petAiComponent.getStepAI(STEP_LEVEL_ZERO).getStepLv()) { // step0
            StepAI current_step_ai = petAiComponent.getStepAI(STEP_LEVEL_ZERO);
            if (current_step_ai.getSkill(SKILL_NUMBER_THREE).getId() != DEFAULT_SKILL_ID && current_step_ai.getAction(SKILL_NUMBER_THREE, -1) != -1) {
                if (current_step_ai.getAction(SKILL_NUMBER_THREE, -1) == action_id) {
                    castSkillTarget(current_step_ai.getSkill(SKILL_NUMBER_THREE), current_step_ai.getSkillTargetType(SKILL_NUMBER_THREE, DEFAULT_SKILL_TARGET), (Creature) master.getTarget());
                }
            }
            if (current_step_ai.getSkill(SKILL_NUMBER_FOUR).getId() != DEFAULT_SKILL_ID && current_step_ai.getAction(SKILL_NUMBER_FOUR, -1) != -1) {
                if (current_step_ai.getAction(SKILL_NUMBER_FOUR, -1) == action_id) {
                    castSkillTarget(current_step_ai.getSkill(SKILL_NUMBER_FOUR), current_step_ai.getSkillTargetType(SKILL_NUMBER_FOUR, DEFAULT_SKILL_TARGET), (Creature) master.getTarget());
                }
            }
            if (current_step_ai.getSkill(SKILL_NUMBER_FIVE).getId() != DEFAULT_SKILL_ID && current_step_ai.getAction(SKILL_NUMBER_FIVE, -1) != -1) {
                if (current_step_ai.getAction(SKILL_NUMBER_FIVE, -1) == action_id) {
                    castSkillTarget(current_step_ai.getSkill(SKILL_NUMBER_FIVE), current_step_ai.getSkillTargetType(SKILL_NUMBER_FIVE, DEFAULT_SKILL_TARGET), (Creature) master.getTarget());
                }
            }
        } else if (getActor().getLevel() < petAiComponent.getStepAI(STEP_LEVEL_ONE).getStepLv()) { // step1
            StepAI current_step_ai = petAiComponent.getStepAI(STEP_LEVEL_ONE);
            if (current_step_ai.getSkill(SKILL_NUMBER_THREE).getId() != DEFAULT_SKILL_ID && current_step_ai.getAction(SKILL_NUMBER_THREE, -1) != -1) {
                if (current_step_ai.getAction(SKILL_NUMBER_THREE, -1) == action_id) {
                    castSkillTarget(current_step_ai.getSkill(SKILL_NUMBER_THREE), current_step_ai.getSkillTargetType(SKILL_NUMBER_THREE, DEFAULT_SKILL_TARGET), (Creature) master.getTarget());
                }
            }
            if (current_step_ai.getSkill(SKILL_NUMBER_FOUR).getId() != DEFAULT_SKILL_ID && current_step_ai.getAction(SKILL_NUMBER_FOUR, -1) != -1) {
                if (current_step_ai.getAction(SKILL_NUMBER_FOUR, -1) == action_id) {
                    castSkillTarget(current_step_ai.getSkill(SKILL_NUMBER_FOUR), current_step_ai.getSkillTargetType(SKILL_NUMBER_FOUR, DEFAULT_SKILL_TARGET), (Creature) master.getTarget());
                }
            }
            if (current_step_ai.getSkill(SKILL_NUMBER_FIVE).getId() != DEFAULT_SKILL_ID && current_step_ai.getAction(SKILL_NUMBER_FIVE, -1) != -1) {
                if (current_step_ai.getAction(SKILL_NUMBER_FIVE, -1) == action_id) {
                    castSkillTarget(current_step_ai.getSkill(SKILL_NUMBER_FIVE), current_step_ai.getSkillTargetType(SKILL_NUMBER_FIVE, DEFAULT_SKILL_TARGET), (Creature) master.getTarget());
                }
            }
        } else if (getActor().getLevel() < petAiComponent.getStepAI(STEP_LEVEL_TWO).getStepLv()) { // step2
            StepAI current_step_ai = petAiComponent.getStepAI(STEP_LEVEL_TWO);
            if (current_step_ai.getSkill(SKILL_NUMBER_THREE).getId() != DEFAULT_SKILL_ID && current_step_ai.getAction(SKILL_NUMBER_THREE, -1) != -1) {
                if (current_step_ai.getAction(SKILL_NUMBER_THREE, -1) == action_id) {
                    castSkillTarget(current_step_ai.getSkill(SKILL_NUMBER_THREE), current_step_ai.getSkillTargetType(SKILL_NUMBER_THREE, DEFAULT_SKILL_TARGET), (Creature) master.getTarget());
                }
            }
            if (current_step_ai.getSkill(SKILL_NUMBER_FOUR).getId() != DEFAULT_SKILL_ID && current_step_ai.getAction(SKILL_NUMBER_FOUR, -1) != -1) {
                if (current_step_ai.getAction(SKILL_NUMBER_FOUR, -1) == action_id) {
                    castSkillTarget(current_step_ai.getSkill(SKILL_NUMBER_FOUR), current_step_ai.getSkillTargetType(SKILL_NUMBER_FOUR, DEFAULT_SKILL_TARGET), (Creature) master.getTarget());
                }
            }
            if (current_step_ai.getSkill(SKILL_NUMBER_FIVE).getId() != DEFAULT_SKILL_ID && current_step_ai.getAction(SKILL_NUMBER_FIVE, -1) != -1) {
                if (current_step_ai.getAction(SKILL_NUMBER_FIVE, -1) == action_id) {
                    castSkillTarget(current_step_ai.getSkill(SKILL_NUMBER_FIVE), current_step_ai.getSkillTargetType(SKILL_NUMBER_FIVE, DEFAULT_SKILL_TARGET), (Creature) master.getTarget());
                }
            }
        } else if (getActor().getLevel() < petAiComponent.getStepAI(STEP_LEVEL_THREE).getStepLv()) { // step3
            StepAI current_step_ai = petAiComponent.getStepAI(STEP_LEVEL_THREE);
            if (current_step_ai.getSkill(SKILL_NUMBER_THREE).getId() != DEFAULT_SKILL_ID && current_step_ai.getAction(SKILL_NUMBER_THREE, -1) != -1) {
                if (current_step_ai.getAction(SKILL_NUMBER_THREE, -1) == action_id) {
                    castSkillTarget(current_step_ai.getSkill(SKILL_NUMBER_THREE), current_step_ai.getSkillTargetType(SKILL_NUMBER_THREE, DEFAULT_SKILL_TARGET), (Creature) master.getTarget());
                }
            }
            if (current_step_ai.getSkill(SKILL_NUMBER_FOUR).getId() != DEFAULT_SKILL_ID && current_step_ai.getAction(SKILL_NUMBER_FOUR, -1) != -1) {
                if (current_step_ai.getAction(SKILL_NUMBER_FOUR, -1) == action_id) {
                    castSkillTarget(current_step_ai.getSkill(SKILL_NUMBER_FOUR), current_step_ai.getSkillTargetType(SKILL_NUMBER_FOUR, DEFAULT_SKILL_TARGET), (Creature) master.getTarget());
                }
            }
            if (current_step_ai.getSkill(SKILL_NUMBER_FIVE).getId() != DEFAULT_SKILL_ID && current_step_ai.getAction(SKILL_NUMBER_FIVE, -1) != -1) {
                if (current_step_ai.getAction(SKILL_NUMBER_FIVE, -1) == action_id) {
                    castSkillTarget(current_step_ai.getSkill(SKILL_NUMBER_FIVE), current_step_ai.getSkillTargetType(SKILL_NUMBER_FIVE, DEFAULT_SKILL_TARGET), (Creature) master.getTarget());
                }
            }
        } else if (getActor().getLevel() < petAiComponent.getStepAI(STEP_LEVEL_FOUR).getStepLv()) { // step4
            StepAI current_step_ai = petAiComponent.getStepAI(STEP_LEVEL_FOUR);
            if (current_step_ai.getSkill(SKILL_NUMBER_THREE).getId() != DEFAULT_SKILL_ID && current_step_ai.getAction(SKILL_NUMBER_THREE, -1) != -1) {
                if (current_step_ai.getAction(SKILL_NUMBER_THREE, -1) == action_id) {
                    castSkillTarget(current_step_ai.getSkill(SKILL_NUMBER_THREE), current_step_ai.getSkillTargetType(SKILL_NUMBER_THREE, DEFAULT_SKILL_TARGET), (Creature) master.getTarget());
                }
            }
            if (current_step_ai.getSkill(SKILL_NUMBER_FOUR).getId() != DEFAULT_SKILL_ID && current_step_ai.getAction(SKILL_NUMBER_FOUR, -1) != -1) {
                if (current_step_ai.getAction(SKILL_NUMBER_FOUR, -1) == action_id) {
                    castSkillTarget(current_step_ai.getSkill(SKILL_NUMBER_FOUR), current_step_ai.getSkillTargetType(SKILL_NUMBER_FOUR, DEFAULT_SKILL_TARGET), (Creature) master.getTarget());
                }
            }
            if (current_step_ai.getSkill(SKILL_NUMBER_FIVE).getId() != DEFAULT_SKILL_ID && current_step_ai.getAction(SKILL_NUMBER_FIVE, -1) != -1) {
                if (current_step_ai.getAction(SKILL_NUMBER_FIVE, -1) == action_id) {
                    castSkillTarget(current_step_ai.getSkill(SKILL_NUMBER_FIVE), current_step_ai.getSkillTargetType(SKILL_NUMBER_FIVE, DEFAULT_SKILL_TARGET), (Creature) master.getTarget());
                }
            }
        } else if (getActor().getLevel() < petAiComponent.getStepAI(STEP_LEVEL_FIVE).getStepLv()) { // step5
            StepAI current_step_ai = petAiComponent.getStepAI(STEP_LEVEL_FIVE);
            if (current_step_ai.getSkill(SKILL_NUMBER_THREE).getId() != DEFAULT_SKILL_ID && current_step_ai.getAction(SKILL_NUMBER_THREE, -1) != -1) {
                if (current_step_ai.getAction(SKILL_NUMBER_THREE, -1) == action_id) {
                    castSkillTarget(current_step_ai.getSkill(SKILL_NUMBER_THREE), current_step_ai.getSkillTargetType(SKILL_NUMBER_THREE, DEFAULT_SKILL_TARGET), (Creature) master.getTarget());
                }
            }
            if (current_step_ai.getSkill(SKILL_NUMBER_FOUR).getId() != DEFAULT_SKILL_ID && current_step_ai.getAction(SKILL_NUMBER_FOUR, -1) != -1) {
                if (current_step_ai.getAction(SKILL_NUMBER_FOUR, -1) == action_id) {
                    castSkillTarget(current_step_ai.getSkill(SKILL_NUMBER_FOUR), current_step_ai.getSkillTargetType(SKILL_NUMBER_FOUR, DEFAULT_SKILL_TARGET), (Creature) master.getTarget());
                }
            }
            if (current_step_ai.getSkill(SKILL_NUMBER_FIVE).getId() != DEFAULT_SKILL_ID && current_step_ai.getAction(SKILL_NUMBER_FIVE, -1) != -1) {
                if (current_step_ai.getAction(SKILL_NUMBER_FIVE, -1) == action_id) {
                    castSkillTarget(current_step_ai.getSkill(SKILL_NUMBER_FIVE), current_step_ai.getSkillTargetType(SKILL_NUMBER_FIVE, DEFAULT_SKILL_TARGET), (Creature) master.getTarget());
                }
            }
        } else if (getActor().getLevel() < petAiComponent.getStepAI(STEP_LEVEL_SIX).getStepLv()) { // step6
            StepAI current_step_ai = petAiComponent.getStepAI(STEP_LEVEL_SIX);
            if (current_step_ai.getSkill(SKILL_NUMBER_THREE).getId() != DEFAULT_SKILL_ID && current_step_ai.getAction(SKILL_NUMBER_THREE, -1) != -1) {
                if (current_step_ai.getAction(SKILL_NUMBER_THREE, -1) == action_id) {
                    castSkillTarget(current_step_ai.getSkill(SKILL_NUMBER_THREE), current_step_ai.getSkillTargetType(SKILL_NUMBER_THREE, DEFAULT_SKILL_TARGET), (Creature) master.getTarget());
                }
            }
            if (current_step_ai.getSkill(SKILL_NUMBER_FOUR).getId() != DEFAULT_SKILL_ID && current_step_ai.getAction(SKILL_NUMBER_FOUR, -1) != -1) {
                if (current_step_ai.getAction(SKILL_NUMBER_FOUR, -1) == action_id) {
                    castSkillTarget(current_step_ai.getSkill(SKILL_NUMBER_FOUR), current_step_ai.getSkillTargetType(SKILL_NUMBER_FOUR, DEFAULT_SKILL_TARGET), (Creature) master.getTarget());
                }
            }
            if (current_step_ai.getSkill(SKILL_NUMBER_FIVE).getId() != DEFAULT_SKILL_ID && current_step_ai.getAction(SKILL_NUMBER_FIVE, -1) != -1) {
                if (current_step_ai.getAction(SKILL_NUMBER_FIVE, -1) == action_id) {
                    castSkillTarget(current_step_ai.getSkill(SKILL_NUMBER_FIVE), current_step_ai.getSkillTargetType(SKILL_NUMBER_FIVE, DEFAULT_SKILL_TARGET), (Creature) master.getTarget());
                }
            }
        } else if (getActor().getLevel() >= petAiComponent.getStepAI(STEP_LEVEL_SIX).getStepLv()) { // step7
            StepAI current_step_ai = petAiComponent.getStepAI(STEP_LEVEL_SIX);
            if (current_step_ai.getSkill(SKILL_NUMBER_THREE).getId() != DEFAULT_SKILL_ID && current_step_ai.getAction(SKILL_NUMBER_THREE, -1) != -1) {
                if (current_step_ai.getAction(SKILL_NUMBER_THREE, -1) == action_id) {
                    castSkillTarget(current_step_ai.getSkill(SKILL_NUMBER_THREE), current_step_ai.getSkillTargetType(SKILL_NUMBER_THREE, DEFAULT_SKILL_TARGET), (Creature) master.getTarget());
                }
            }
            if (current_step_ai.getSkill(SKILL_NUMBER_FOUR).getId() != DEFAULT_SKILL_ID && current_step_ai.getAction(SKILL_NUMBER_FOUR, -1) != -1) {
                if (current_step_ai.getAction(SKILL_NUMBER_FOUR, -1) == action_id) {
                    castSkillTarget(current_step_ai.getSkill(SKILL_NUMBER_FOUR), current_step_ai.getSkillTargetType(SKILL_NUMBER_FOUR, DEFAULT_SKILL_TARGET), (Creature) master.getTarget());
                }
            }
            if (current_step_ai.getSkill(SKILL_NUMBER_FIVE).getId() != DEFAULT_SKILL_ID && current_step_ai.getAction(SKILL_NUMBER_FIVE, -1) != -1) {
                if (current_step_ai.getAction(SKILL_NUMBER_FIVE, -1) == action_id) {
                    castSkillTarget(current_step_ai.getSkill(SKILL_NUMBER_FIVE), current_step_ai.getSkillTargetType(SKILL_NUMBER_FIVE, DEFAULT_SKILL_TARGET), (Creature) master.getTarget());
                }
            }
        }
    }
    /*

     */

    @Override
    protected void onEvtTimer(int timerId, Object arg1, Object arg2) {
        Player master = actor.getPlayer();
        switch (timerId) {
            case 1671:
                if (petAiComponent.getMoveAroundSocial() > 0 && actor.getCurrentHp() > (actor.getMaxHp() * 0.400000) && !actor.isDead()) {
                    addEffectActionDesire(1, petAiComponent.getMoveAroundSocial() * 1000 / 30, 50);
                }
                addTimer(1671, 10000);
                break;
            case 2001:  // buff
                if (getAbnormalLevel(getActor(), 5771, 1) >= 1) {    // если набафан pet buff control
                    addTimer(2001, buffTime * 1000);    // то не бафаем, а просто перезапускаем таск
                    break;
                }
                StepAI buff_step_ai = petAiComponent.getStepAI(STEP_LEVEL_ZERO);
                castBuff(buff_step_ai.getBuff(SKILL_NUMBER_ONE), buff_step_ai.getMergedBuffType(SKILL_NUMBER_ONE), buff_step_ai.getBuffTargetType(SKILL_NUMBER_ONE, DEFAULT_PBUFF_TARGET));
                castBuff(buff_step_ai.getBuff(SKILL_NUMBER_TWO), buff_step_ai.getMergedBuffType(SKILL_NUMBER_TWO), buff_step_ai.getBuffTargetType(SKILL_NUMBER_TWO, DEFAULT_PBUFF_TARGET));
                castBuff(buff_step_ai.getBuff(SKILL_NUMBER_THREE), buff_step_ai.getMergedBuffType(SKILL_NUMBER_THREE), buff_step_ai.getBuffTargetType(SKILL_NUMBER_THREE, DEFAULT_PBUFF_TARGET));
                castBuff(buff_step_ai.getBuff(SKILL_NUMBER_FOUR), buff_step_ai.getMergedBuffType(SKILL_NUMBER_FOUR), buff_step_ai.getBuffTargetType(SKILL_NUMBER_FOUR, DEFAULT_PBUFF_TARGET));
                castBuff(buff_step_ai.getBuff(SKILL_NUMBER_FIVE), buff_step_ai.getMergedBuffType(SKILL_NUMBER_FIVE), buff_step_ai.getBuffTargetType(SKILL_NUMBER_FIVE, DEFAULT_PBUFF_TARGET));
                castBuff(buff_step_ai.getBuff(SKILL_NUMBER_SIX), buff_step_ai.getMergedBuffType(SKILL_NUMBER_SIX), buff_step_ai.getBuffTargetType(SKILL_NUMBER_SIX, DEFAULT_PBUFF_TARGET));
                castBuff(buff_step_ai.getBuff(SKILL_NUMBER_SEVEN), buff_step_ai.getMergedBuffType(SKILL_NUMBER_SEVEN), buff_step_ai.getBuffTargetType(SKILL_NUMBER_SEVEN, DEFAULT_PBUFF_TARGET));
                castBuff(buff_step_ai.getBuff(SKILL_NUMBER_EIGHT), buff_step_ai.getMergedBuffType(SKILL_NUMBER_EIGHT), buff_step_ai.getBuffTargetType(SKILL_NUMBER_EIGHT, DEFAULT_PBUFF_TARGET));
                addTimer(2001, buffTime * 1000);
                break;
            case 2002:  // heal
                if (!getActor().isInCombat()) {
                    addTimer(2002, healDelay * 1000);
                    return;
                }
                switch (petAiComponent.getHealType()) {
                    case 1:
                        if (master.getCurrentHpPercents() < 70 && master.getCurrentHpPercents() >= 30) {
                            if (getActor().getLevel() < petAiComponent.getStepAI(STEP_LEVEL_ZERO).getStepLv()) {
                                StepAI current_step_ai = petAiComponent.getStepAI(STEP_LEVEL_ZERO);
                                castHeal(current_step_ai.getHeal(SKILL_NUMBER_ONE), current_step_ai.getHealTargetType(SKILL_NUMBER_ONE, DEFAULT_PHEAL_TARGET));
                            } else if (getActor().getLevel() < petAiComponent.getStepAI(STEP_LEVEL_ONE).getStepLv()) {
                                StepAI current_step_ai = petAiComponent.getStepAI(STEP_LEVEL_ONE);
                                castHeal(current_step_ai.getHeal(SKILL_NUMBER_ONE), current_step_ai.getHealTargetType(SKILL_NUMBER_ONE, DEFAULT_PHEAL_TARGET));
                            } else if (getActor().getLevel() < petAiComponent.getStepAI(STEP_LEVEL_TWO).getStepLv()) {
                                StepAI current_step_ai = petAiComponent.getStepAI(STEP_LEVEL_TWO);
                                castHeal(current_step_ai.getHeal(SKILL_NUMBER_ONE), current_step_ai.getHealTargetType(SKILL_NUMBER_ONE, DEFAULT_PHEAL_TARGET));
                            } else if (getActor().getLevel() < petAiComponent.getStepAI(STEP_LEVEL_THREE).getStepLv()) {
                                StepAI current_step_ai = petAiComponent.getStepAI(STEP_LEVEL_THREE);
                                castHeal(current_step_ai.getHeal(SKILL_NUMBER_ONE), current_step_ai.getHealTargetType(SKILL_NUMBER_ONE, DEFAULT_PHEAL_TARGET));
                            } else if (getActor().getLevel() < petAiComponent.getStepAI(STEP_LEVEL_FOUR).getStepLv()) {
                                StepAI current_step_ai = petAiComponent.getStepAI(STEP_LEVEL_FOUR);
                                castHeal(current_step_ai.getHeal(SKILL_NUMBER_ONE), current_step_ai.getHealTargetType(SKILL_NUMBER_ONE, DEFAULT_PHEAL_TARGET));
                            } else if (getActor().getLevel() < petAiComponent.getStepAI(STEP_LEVEL_FIVE).getStepLv()) {
                                StepAI current_step_ai = petAiComponent.getStepAI(STEP_LEVEL_FIVE);
                                castHeal(current_step_ai.getHeal(SKILL_NUMBER_ONE), current_step_ai.getHealTargetType(SKILL_NUMBER_ONE, DEFAULT_PHEAL_TARGET));
                            } else if (getActor().getLevel() < petAiComponent.getStepAI(STEP_LEVEL_SIX).getStepLv()) {
                                StepAI current_step_ai = petAiComponent.getStepAI(STEP_LEVEL_SIX);
                                castHeal(current_step_ai.getHeal(SKILL_NUMBER_ONE), current_step_ai.getHealTargetType(SKILL_NUMBER_ONE, DEFAULT_PHEAL_TARGET));
                            } else if (getActor().getLevel() >= petAiComponent.getStepAI(STEP_LEVEL_SIX).getStepLv()) {
                                StepAI current_step_ai = petAiComponent.getStepAI(STEP_LEVEL_SIX);
                                castHeal(current_step_ai.getHeal(SKILL_NUMBER_ONE), current_step_ai.getHealTargetType(SKILL_NUMBER_ONE, DEFAULT_PHEAL_TARGET));
                            }
                        } else if (master.getCurrentHpPercents() < 30) {
                            if (getActor().getLevel() < petAiComponent.getStepAI(STEP_LEVEL_ZERO).getStepLv()) {
                                StepAI current_step_ai = petAiComponent.getStepAI(STEP_LEVEL_ZERO);
                                castHeal(current_step_ai.getHeal(SKILL_NUMBER_TWO), current_step_ai.getHealTargetType(SKILL_NUMBER_TWO, DEFAULT_PHEAL_TARGET));
                            } else if (getActor().getLevel() < petAiComponent.getStepAI(STEP_LEVEL_ONE).getStepLv()) {
                                StepAI current_step_ai = petAiComponent.getStepAI(STEP_LEVEL_ONE);
                                castHeal(current_step_ai.getHeal(SKILL_NUMBER_TWO), current_step_ai.getHealTargetType(SKILL_NUMBER_TWO, DEFAULT_PHEAL_TARGET));
                            } else if (getActor().getLevel() < petAiComponent.getStepAI(STEP_LEVEL_TWO).getStepLv()) {
                                StepAI current_step_ai = petAiComponent.getStepAI(STEP_LEVEL_TWO);
                                castHeal(current_step_ai.getHeal(SKILL_NUMBER_TWO), current_step_ai.getHealTargetType(SKILL_NUMBER_TWO, DEFAULT_PHEAL_TARGET));
                            } else if (getActor().getLevel() < petAiComponent.getStepAI(STEP_LEVEL_THREE).getStepLv()) {
                                StepAI current_step_ai = petAiComponent.getStepAI(STEP_LEVEL_THREE);
                                castHeal(current_step_ai.getHeal(SKILL_NUMBER_TWO), current_step_ai.getHealTargetType(SKILL_NUMBER_TWO, DEFAULT_PHEAL_TARGET));
                            } else if (getActor().getLevel() < petAiComponent.getStepAI(STEP_LEVEL_FOUR).getStepLv()) {
                                StepAI current_step_ai = petAiComponent.getStepAI(STEP_LEVEL_FOUR);
                                castHeal(current_step_ai.getHeal(SKILL_NUMBER_TWO), current_step_ai.getHealTargetType(SKILL_NUMBER_TWO, DEFAULT_PHEAL_TARGET));
                            } else if (getActor().getLevel() < petAiComponent.getStepAI(STEP_LEVEL_FIVE).getStepLv()) {
                                StepAI current_step_ai = petAiComponent.getStepAI(STEP_LEVEL_FIVE);
                                castHeal(current_step_ai.getHeal(SKILL_NUMBER_TWO), current_step_ai.getHealTargetType(SKILL_NUMBER_TWO, DEFAULT_PHEAL_TARGET));
                            } else if (getActor().getLevel() < petAiComponent.getStepAI(STEP_LEVEL_SIX).getStepLv()) {
                                StepAI current_step_ai = petAiComponent.getStepAI(STEP_LEVEL_SIX);
                                castHeal(current_step_ai.getHeal(SKILL_NUMBER_TWO), current_step_ai.getHealTargetType(SKILL_NUMBER_TWO, DEFAULT_PHEAL_TARGET));
                            } else if (getActor().getLevel() >= petAiComponent.getStepAI(STEP_LEVEL_SIX).getStepLv()) {
                                StepAI current_step_ai = petAiComponent.getStepAI(STEP_LEVEL_SIX);
                                castHeal(current_step_ai.getHeal(SKILL_NUMBER_TWO), current_step_ai.getHealTargetType(SKILL_NUMBER_TWO, DEFAULT_PHEAL_TARGET));
                            }
                        }
                        break;
                    case 0:
                        if (master.getCurrentHpPercents() < 30) {
                            if (getActor().getLevel() < petAiComponent.getStepAI(STEP_LEVEL_ZERO).getStepLv()) {
                                StepAI current_step_ai = petAiComponent.getStepAI(STEP_LEVEL_ZERO);
                                castHeal(current_step_ai.getHeal(SKILL_NUMBER_TWO), current_step_ai.getHealTargetType(SKILL_NUMBER_TWO, DEFAULT_PHEAL_TARGET));
                            } else if (getActor().getLevel() < petAiComponent.getStepAI(STEP_LEVEL_ONE).getStepLv()) {
                                StepAI current_step_ai = petAiComponent.getStepAI(STEP_LEVEL_ONE);
                                castHeal(current_step_ai.getHeal(SKILL_NUMBER_TWO), current_step_ai.getHealTargetType(SKILL_NUMBER_TWO, DEFAULT_PHEAL_TARGET));
                            } else if (getActor().getLevel() < petAiComponent.getStepAI(STEP_LEVEL_TWO).getStepLv()) {
                                StepAI current_step_ai = petAiComponent.getStepAI(STEP_LEVEL_TWO);
                                castHeal(current_step_ai.getHeal(SKILL_NUMBER_TWO), current_step_ai.getHealTargetType(SKILL_NUMBER_TWO, DEFAULT_PHEAL_TARGET));
                            } else if (getActor().getLevel() < petAiComponent.getStepAI(STEP_LEVEL_THREE).getStepLv()) {
                                StepAI current_step_ai = petAiComponent.getStepAI(STEP_LEVEL_THREE);
                                castHeal(current_step_ai.getHeal(SKILL_NUMBER_TWO), current_step_ai.getHealTargetType(SKILL_NUMBER_TWO, DEFAULT_PHEAL_TARGET));
                            } else if (getActor().getLevel() < petAiComponent.getStepAI(STEP_LEVEL_FOUR).getStepLv()) {
                                StepAI current_step_ai = petAiComponent.getStepAI(STEP_LEVEL_FOUR);
                                castHeal(current_step_ai.getHeal(SKILL_NUMBER_TWO), current_step_ai.getHealTargetType(SKILL_NUMBER_TWO, DEFAULT_PHEAL_TARGET));
                            } else if (getActor().getLevel() < petAiComponent.getStepAI(STEP_LEVEL_FIVE).getStepLv()) {
                                StepAI current_step_ai = petAiComponent.getStepAI(STEP_LEVEL_FIVE);
                                castHeal(current_step_ai.getHeal(SKILL_NUMBER_TWO), current_step_ai.getHealTargetType(SKILL_NUMBER_TWO, DEFAULT_PHEAL_TARGET));
                            } else if (getActor().getLevel() < petAiComponent.getStepAI(STEP_LEVEL_SIX).getStepLv()) {
                                StepAI current_step_ai = petAiComponent.getStepAI(STEP_LEVEL_SIX);
                                castHeal(current_step_ai.getHeal(SKILL_NUMBER_TWO), current_step_ai.getHealTargetType(SKILL_NUMBER_TWO, DEFAULT_PHEAL_TARGET));
                            } else if (getActor().getLevel() >= petAiComponent.getStepAI(STEP_LEVEL_SIX).getStepLv()) {
                                StepAI current_step_ai = petAiComponent.getStepAI(STEP_LEVEL_SIX);
                                castHeal(current_step_ai.getHeal(SKILL_NUMBER_TWO), current_step_ai.getHealTargetType(SKILL_NUMBER_TWO, DEFAULT_PHEAL_TARGET));
                            }
                        } else if(master.getCurrentMpPercents() < 60) {
                            if (getActor().getLevel() < petAiComponent.getStepAI(STEP_LEVEL_ZERO).getStepLv()) {
                                StepAI current_step_ai = petAiComponent.getStepAI(STEP_LEVEL_ZERO);
                                castHeal(current_step_ai.getHeal(SKILL_NUMBER_ONE), current_step_ai.getHealTargetType(SKILL_NUMBER_ONE, DEFAULT_PHEAL_TARGET));
                            } else if (getActor().getLevel() < petAiComponent.getStepAI(STEP_LEVEL_ONE).getStepLv()) {
                                StepAI current_step_ai = petAiComponent.getStepAI(STEP_LEVEL_ONE);
                                castHeal(current_step_ai.getHeal(SKILL_NUMBER_ONE), current_step_ai.getHealTargetType(SKILL_NUMBER_ONE, DEFAULT_PHEAL_TARGET));
                            } else if (getActor().getLevel() < petAiComponent.getStepAI(STEP_LEVEL_TWO).getStepLv()) {
                                StepAI current_step_ai = petAiComponent.getStepAI(STEP_LEVEL_TWO);
                                castHeal(current_step_ai.getHeal(SKILL_NUMBER_ONE), current_step_ai.getHealTargetType(SKILL_NUMBER_ONE, DEFAULT_PHEAL_TARGET));
                            } else if (getActor().getLevel() < petAiComponent.getStepAI(STEP_LEVEL_THREE).getStepLv()) {
                                StepAI current_step_ai = petAiComponent.getStepAI(STEP_LEVEL_THREE);
                                castHeal(current_step_ai.getHeal(SKILL_NUMBER_ONE), current_step_ai.getHealTargetType(SKILL_NUMBER_ONE, DEFAULT_PHEAL_TARGET));
                            } else if (getActor().getLevel() < petAiComponent.getStepAI(STEP_LEVEL_FOUR).getStepLv()) {
                                StepAI current_step_ai = petAiComponent.getStepAI(STEP_LEVEL_FOUR);
                                castHeal(current_step_ai.getHeal(SKILL_NUMBER_ONE), current_step_ai.getHealTargetType(SKILL_NUMBER_ONE, DEFAULT_PHEAL_TARGET));
                            } else if (getActor().getLevel() < petAiComponent.getStepAI(STEP_LEVEL_FIVE).getStepLv()) {
                                StepAI current_step_ai = petAiComponent.getStepAI(STEP_LEVEL_FIVE);
                                castHeal(current_step_ai.getHeal(SKILL_NUMBER_ONE), current_step_ai.getHealTargetType(SKILL_NUMBER_ONE, DEFAULT_PHEAL_TARGET));
                            } else if (getActor().getLevel() < petAiComponent.getStepAI(STEP_LEVEL_SIX).getStepLv()) {
                                StepAI current_step_ai = petAiComponent.getStepAI(STEP_LEVEL_SIX);
                                castHeal(current_step_ai.getHeal(SKILL_NUMBER_ONE), current_step_ai.getHealTargetType(SKILL_NUMBER_ONE, DEFAULT_PHEAL_TARGET));
                            } else if (getActor().getLevel() >= petAiComponent.getStepAI(STEP_LEVEL_SIX).getStepLv()) {
                                StepAI current_step_ai = petAiComponent.getStepAI(STEP_LEVEL_SIX);
                                castHeal(current_step_ai.getHeal(SKILL_NUMBER_ONE), current_step_ai.getHealTargetType(SKILL_NUMBER_ONE, DEFAULT_PHEAL_TARGET));
                            }
                        }
                        break;
                }
                addTimer(2002, healDelay * 1000);
                break;
        }
        super.onEvtTimer(timerId, arg1, arg2);
    }
}


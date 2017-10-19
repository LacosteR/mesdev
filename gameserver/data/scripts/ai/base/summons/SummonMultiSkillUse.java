package ai.base.summons;

import l2p.gameserver.ai.SummonAI;
import l2p.gameserver.ai.base.StepAI;
import l2p.gameserver.model.Summon;

/**
 * @author : Alice
 * @date : 29.03.17
 * @time : 0:28
 * <p/>
 */
public class SummonMultiSkillUse extends SummonAI {

    public SummonMultiSkillUse(Summon actor) {
        super(actor);
    }

    @Override
    public void onEvtMenuSelected(int action_id) {
        StepAI step_ai = summonAiComponent.getStepAI();
        if (step_ai.getAction(SKILL_NUMBER_ONE, -1) == action_id) {
            castSkillTarget(step_ai.getSkill(SKILL_NUMBER_ONE), step_ai.getSkillTargetType(SKILL_NUMBER_ONE, DEFAULT_SKILL_TARGET), getMaster());
        }
        if (step_ai.getAction(SKILL_NUMBER_TWO, -1) == action_id) {
            castSkillTarget(step_ai.getSkill(SKILL_NUMBER_TWO), step_ai.getSkillTargetType(SKILL_NUMBER_TWO, DEFAULT_SKILL_TARGET), getMaster());
        }
        if (step_ai.getAction(SKILL_NUMBER_THREE, -1) == action_id) {
            castSkillTarget(step_ai.getSkill(SKILL_NUMBER_THREE), step_ai.getSkillTargetType(SKILL_NUMBER_THREE, DEFAULT_SKILL_TARGET), getMaster());
        }
        if (step_ai.getAction(SKILL_NUMBER_FOUR, -1) == action_id) {
            castSkillTarget(step_ai.getSkill(SKILL_NUMBER_FOUR), step_ai.getSkillTargetType(SKILL_NUMBER_FOUR, DEFAULT_SKILL_TARGET), getMaster());
        }
        if (step_ai.getAction(SKILL_NUMBER_FIVE, -1) == action_id) {
            castSkillTarget(step_ai.getSkill(SKILL_NUMBER_FIVE), step_ai.getSkillTargetType(SKILL_NUMBER_FIVE, DEFAULT_SKILL_TARGET), getMaster());
        }
        if (step_ai.getAction(SKILL_NUMBER_SIX, -1) == action_id) {
            castSkillTarget(step_ai.getSkill(SKILL_NUMBER_SIX), step_ai.getSkillTargetType(SKILL_NUMBER_SIX, DEFAULT_SKILL_TARGET), getMaster());
        }
        if (step_ai.getAction(SKILL_NUMBER_SEVEN, -1) == action_id) {
            castSkillTarget(step_ai.getSkill(SKILL_NUMBER_SEVEN), step_ai.getSkillTargetType(SKILL_NUMBER_SEVEN, DEFAULT_SKILL_TARGET), getMaster());
        }
        if (step_ai.getAction(SKILL_NUMBER_EIGHT, -1) == action_id) {
            castSkillTarget(step_ai.getSkill(SKILL_NUMBER_EIGHT), step_ai.getSkillTargetType(SKILL_NUMBER_EIGHT, DEFAULT_SKILL_TARGET), getMaster());
        }
    }
}

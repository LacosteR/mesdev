package ai.isle_of_prayer;

import instances.CrystalCaverns;
import l2p.gameserver.ai.DefaultAI;
import l2p.gameserver.model.Creature;
import l2p.gameserver.model.Skill;
import l2p.gameserver.model.instances.NpcInstance;
import l2p.gameserver.skills.enums.SkillType;

public class EvasProtector extends DefaultAI {
    public EvasProtector(NpcInstance actor) {
        super(actor);
        actor.setHasChatWindow(false);
    }

    @Override
    protected void onEvtSeeSpell(Skill skill, Creature caster) {
        NpcInstance actor = getActor();
        if (skill.getSkillType() == SkillType.HEAL && actor.getPositionComponent().getReflection().getInstancedZoneId() == 10)
            ((CrystalCaverns) actor.getPositionComponent().getReflection()).notifyProtectorHealed(actor);
        super.onEvtSeeSpell(skill, caster);
    }

    @Override
    protected boolean randomWalk() {
        return false;
    }
}
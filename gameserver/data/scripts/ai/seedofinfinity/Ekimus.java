package ai.seedofinfinity;

import instances.HeartInfinityAttack;
import l2p.gameserver.ai.CtrlEvent;
import l2p.gameserver.ai.Mystic;
import l2p.gameserver.model.Creature;
import l2p.gameserver.model.Skill;
import l2p.gameserver.model.instances.NpcInstance;

/**
 * @author pchayka
 */
public class Ekimus extends Mystic {
    private long delayTimer = 0;

    public Ekimus(NpcInstance actor) {
        super(actor);
    }

    @Override
    protected boolean randomAnimation() {
        return false;
    }

    @Override
    protected boolean randomWalk() {
        return false;
    }

    @Override
    protected void onEvtAttacked(Creature attacker, int damage, Skill skill) {
        NpcInstance actor = getActor();
        for (NpcInstance npc : actor.getPositionComponent().getReflection().getAllByNpcId(29151, true))
            npc.getAI().notifyEvent(CtrlEvent.EVT_AGGRESSION, attacker, damage);
        super.onEvtAttacked(attacker, damage, skill);
    }

    @Override
    protected void thinkAttack() {
        if (delayTimer + 5000 < System.currentTimeMillis()) {
            delayTimer = System.currentTimeMillis();
            if (getActor().getPositionComponent().getReflection().getInstancedZoneId() == 121)
                ((HeartInfinityAttack) getActor().getPositionComponent().getReflection()).notifyEkimusAttack();
        }
        super.thinkAttack();
    }

    @Override
    protected boolean thinkActive() {
        if (delayTimer + 5000 < System.currentTimeMillis()) {
            delayTimer = System.currentTimeMillis();
            if (getActor().getPositionComponent().getReflection().getInstancedZoneId() == 121)
                ((HeartInfinityAttack) getActor().getPositionComponent().getReflection()).notifyEkimusIdle();
        }
        return super.thinkActive();
    }
}
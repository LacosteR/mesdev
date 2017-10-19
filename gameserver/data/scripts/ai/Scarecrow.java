package ai;

import l2p.gameserver.ai.Fighter;
import l2p.gameserver.model.Creature;
import l2p.gameserver.model.Skill;
import l2p.gameserver.model.instances.NpcInstance;

public class Scarecrow extends Fighter {
    public Scarecrow(NpcInstance actor) {
        super(actor);
        actor.block();
        actor.startDamageBlockedHp();
        actor.startDebuffImmunity();
    }

    @Override
    protected void onIntentionAttack(Creature target) {
    }

    @Override
    protected void onEvtAttacked(Creature attacker, int damage, Skill skill) {
    }

    @Override
    protected void onEvtAggression(Creature attacker, int aggro) {
    }
}
package ai.retail.dragonvalley;

import l2p.gameserver.ai.base.RetailClass;
import l2p.gameserver.ai.retail.WarriorUseSkill;
import l2p.gameserver.model.Creature;
import l2p.gameserver.model.GameObjectsStorage;
import l2p.gameserver.model.instances.NpcInstance;
/**
 * @author : Alice
 * @date : 08.10.2017
 * @time : 11:58
 * <p/>
 */
@RetailClass(name = "ai_mesmer_drake")
public class AiMesmerDrake extends WarriorUseSkill {
    int sleepSkill = 449118209;
    int holdSkill = 449183745;
    int SkillTimer = 20100506;

    public AiMesmerDrake(NpcInstance actor) {
        super(actor);
        getAiVariablesComponent().replaceVariable("SuperPointMethod", "0", actor.getNpcId(), "ai_mesmer_drake");
        getAiVariablesComponent().replaceVariable("SuperPointDesire", "50", actor.getNpcId(), "ai_mesmer_drake");
        getAiVariablesComponent().replaceVariable("SuperPointName", "", actor.getNpcId(), "ai_mesmer_drake");
    }

    @Override
    public void onEvtSpawn() {
        if (myself.getPrivates() != null) {
            myself.createPrivates();
        }
        //if (myself->IsNullString(SuperPointName) != 1) TODO Alice -> узнать что это значит
        addMoveSuperPointDesire(getAiVariablesComponent().getSuperPointName(), getAiVariablesComponent().getSuperPointMethod(), getAiVariablesComponent().getSuperPointDesire());
        super.onEvtSpawn();
    }

    @Override
    public void onEvtSeeCreature(Creature creature) {
        if (creature.getPlayer() != null && myself.i_ai2 == 0) {
            addUseSkillDesire(creature, sleepSkill, 0, 1, Integer.MAX_VALUE);
            myself.c_ai0 = creature.getObjectId();
            addTimer(SkillTimer, 10 * 1000);
            myself.i_ai2 = 1;
        }
        super.onEvtSeeCreature(creature);
    }

    @Override
    public void onEvtTimer(int timer_id, Object obj1, Object obj2) {
        if (timer_id == SkillTimer) {
            Creature c0 = GameObjectsStorage.getAsCharacter(myself.c_ai0);
            if (c0 != null) {
                addUseSkillDesire(c0, holdSkill, 0, 1, Integer.MAX_VALUE);
            }
        }
        super.onEvtTimer(timer_id, obj1, obj2);
    }
}

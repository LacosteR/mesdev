package ai.retail;

import l2p.gameserver.ai.base.RetailClass;
import l2p.gameserver.ai.retail.WarriorUseSkill;
import l2p.gameserver.model.Creature;
import l2p.gameserver.model.GameObjectsStorage;
import l2p.gameserver.model.instances.NpcInstance;
/**
 * @author : Alice
 * @date : 08.10.2017
 * @time : 11:30
 * <p/>
 */
@RetailClass(name = "ai_maluk_summon_zombie")
public class AiMalukSummonZombie extends WarriorUseSkill {
    public AiMalukSummonZombie(NpcInstance actor) {
        super(actor);
    }

    @Override
    public void onEvtSpawn() {
        if (myself.param1 != 0) {
            Creature c0 = GameObjectsStorage.getAsCharacter(myself.param1);
            if (c0 != null) {
                addAttackDesire(c0, 1, 10000);
            }
        }
        super.onEvtSpawn();
    }
}

package ai.retail.dragonvalley;

import l2p.gameserver.ai.base.RetailClass;
import l2p.gameserver.ai.retail.DetectPartyWarrior;
import l2p.gameserver.model.Creature;
import l2p.gameserver.model.GameObjectsStorage;
import l2p.gameserver.model.instances.NpcInstance;
/**
 * @author : Alice
 * @date : 08.10.2017
 * @time : 13:48
 * <p/>
 */
@RetailClass(name = "ai_drakos_assasin")
public class AiDrakosAssasin extends DetectPartyWarrior {
    public AiDrakosAssasin(NpcInstance actor) {
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

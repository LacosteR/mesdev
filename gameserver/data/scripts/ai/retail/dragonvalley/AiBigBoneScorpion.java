package ai.retail.dragonvalley;

import l2p.gameserver.ai.base.RetailClass;
import l2p.gameserver.ai.retail.DetectPartyWarrior;
import l2p.gameserver.model.instances.NpcInstance;
/**
 * @author : Alice
 * @date : 08.10.2017
 * @time : 14:38
 * <p/>
 */
@RetailClass(name = "ai_big_bone_scorpion")
public class AiBigBoneScorpion extends DetectPartyWarrior {
    public AiBigBoneScorpion(NpcInstance actor) {
        super(actor);
    }

    @Override
    public void onEvtSpawn() {
        if (myself.getPrivates() != null) {
            myself.createPrivates();
        }
        super.onEvtSpawn();
    }
}

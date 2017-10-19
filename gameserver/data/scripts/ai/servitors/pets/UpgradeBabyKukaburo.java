package ai.servitors.pets;

import ai.base.pets.PetPassiveGrow;
import l2p.gameserver.ai.base.RetailClass;
import l2p.gameserver.model.Summon;

/**
 * @author : Alice
 * @date : 27.03.17
 * @time : 0:19
 * <p/>
 */
@RetailClass(name = "upgrade_baby_kukaburo")
public class UpgradeBabyKukaburo extends PetPassiveGrow {
    public UpgradeBabyKukaburo(Summon actor) {
        super(actor);
    }
}

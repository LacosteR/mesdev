package ai.servitors.pets;

import ai.base.pets.BrPetEvent;
import l2p.gameserver.ai.base.RetailClass;
import l2p.gameserver.model.Summon;

/**
 * @author : Alice
 * @date : 27.03.17
 * @time : 0:19
 * <p/>
 */
@RetailClass(name = "br_rose_lekann_b")
public class BrRoseLekannB extends BrPetEvent {
    public BrRoseLekannB(Summon actor) {
        super(actor);
    }
}

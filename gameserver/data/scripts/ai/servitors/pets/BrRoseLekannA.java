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
@RetailClass(name = "br_rose_lekann_a")
public class BrRoseLekannA extends BrPetEvent {
    public BrRoseLekannA(Summon actor) {
        super(actor);
    }
}

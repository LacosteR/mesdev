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
@RetailClass(name = "br_rose_laffame_a")
public class BrRoseLaffameA extends BrPetEvent {
    public BrRoseLaffameA(Summon actor) {
        super(actor);
    }
}

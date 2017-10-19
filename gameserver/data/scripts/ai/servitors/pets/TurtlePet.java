package ai.servitors.pets;

import ai.base.pets.PetPremiumHybrid;
import l2p.gameserver.ai.base.RetailClass;
import l2p.gameserver.model.Summon;

/**
 * @author : Alice
 * @date : 27.03.17
 * @time : 0:19
 * <p/>
 */
@RetailClass(name = "turtle_pet")
public class TurtlePet extends PetPremiumHybrid {
    public TurtlePet(Summon actor) {
        super(actor);
    }
}

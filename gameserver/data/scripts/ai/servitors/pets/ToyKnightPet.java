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
@RetailClass(name = "toy_knight_pet")
public class ToyKnightPet extends PetPremiumHybrid {
    public ToyKnightPet(Summon actor) {
        super(actor);
    }
}

package ai.servitors.pets;

import ai.base.pets.PetActiveGrow;
import l2p.gameserver.ai.base.RetailClass;
import l2p.gameserver.model.Summon;

/**
 * @author : Alice
 * @date : 27.03.17
 * @time : 0:19
 * <p/>
 */
@RetailClass(name = "br_rudolf_baby_pet_p")
public class BrRudolfBabyPetP extends PetActiveGrow {
    public BrRudolfBabyPetP(Summon actor) {
        super(actor);
    }
}

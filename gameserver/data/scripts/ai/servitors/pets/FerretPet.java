package ai.servitors.pets;

import ai.base.pets.PetPremiumBuffer;
import l2p.gameserver.ai.base.RetailClass;
import l2p.gameserver.model.Summon;

/**
 * @author : Alice
 * @date : 27.03.17
 * @time : 0:19
 * <p/>
 */
@RetailClass(name = "ferret_pet")
public class FerretPet extends PetPremiumBuffer {
    public FerretPet(Summon actor) {
        super(actor);
    }
}

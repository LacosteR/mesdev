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
@RetailClass(name = "fairy_princess_pet")
public class FairyPrincessPet extends PetPremiumBuffer {
    public FairyPrincessPet(Summon actor) {
        super(actor);
    }
}

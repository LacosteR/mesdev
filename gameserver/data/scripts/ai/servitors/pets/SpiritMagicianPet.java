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
@RetailClass(name = "spirit_magician_pet")
public class SpiritMagicianPet extends PetPremiumHybrid {
    public SpiritMagicianPet(Summon actor) {
        super(actor);
    }
}

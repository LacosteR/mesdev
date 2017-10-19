package ai.servitors.pets;

import ai.base.pets.PetPremiumAttacker;
import l2p.gameserver.ai.base.RetailClass;
import l2p.gameserver.model.Summon;

/**
 * @author : Alice
 * @date : 27.03.17
 * @time : 0:19
 * <p/>
 */
@RetailClass(name = "beast_fighter_pet")
public class BeastFighterPet extends PetPremiumAttacker {
    public BeastFighterPet(Summon actor) {
        super(actor);
    }
}

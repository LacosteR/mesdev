package ai.servitors.pets;

import ai.base.pets.PetPremiumCriminal;
import l2p.gameserver.ai.base.RetailClass;
import l2p.gameserver.model.Summon;

/**
 * @author : Alice
 * @date : 27.03.17
 * @time : 0:31
 * <p/>
 */
@RetailClass(name = "owl_wizard_pet")
public class OwlWizardPet extends PetPremiumCriminal {
    public OwlWizardPet(Summon actor) {
        super(actor);
    }
}

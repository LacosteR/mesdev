package ai.servitors.pets;

import ai.base.pets.PetWizardUse2Skill;
import l2p.gameserver.ai.base.RetailClass;
import l2p.gameserver.model.Summon;

/**
 * @author : Alice
 * @date : 27.03.17
 * @time : 0:19
 * <p/>
 */
@RetailClass(name = "hatchling_of_star")
public class HatchlingOfStar extends PetWizardUse2Skill {
    public HatchlingOfStar(Summon actor) {
        super(actor);
    }
}

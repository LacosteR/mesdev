package ai.servitors.pets;

import ai.base.pets.PetWizardUse2Skill;
import l2p.gameserver.ai.base.RetailClass;
import l2p.gameserver.model.Summon;

/**
 * @author : Alice
 * @date : 27.03.17
 * @time : 0:31
 * <p/>
 */
@RetailClass(name = "star_strider")
public class StarStrider extends PetWizardUse2Skill {
    public StarStrider(Summon actor) {
        super(actor);
    }
}

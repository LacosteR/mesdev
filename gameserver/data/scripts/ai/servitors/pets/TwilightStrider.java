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
@RetailClass(name = "twilight_strider")
public class TwilightStrider extends PetWizardUse2Skill {
    public TwilightStrider(Summon actor) {
        super(actor);
    }
}

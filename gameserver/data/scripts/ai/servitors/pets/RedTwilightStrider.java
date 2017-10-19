package ai.servitors.pets;

import ai.base.pets.PetActiveGrow;
import l2p.gameserver.ai.base.RetailClass;
import l2p.gameserver.model.Summon;

/**
 * @author : Alice
 * @date : 27.03.17
 * @time : 2:00
 * <p/>
 */
@RetailClass(name = "red_twilight_strider")
public class RedTwilightStrider extends PetActiveGrow {
    public RedTwilightStrider(Summon actor) {
        super(actor);
    }
}

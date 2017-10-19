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
@RetailClass(name = "red_wind_strider")
public class RedWindStrider extends PetActiveGrow {
    public RedWindStrider(Summon actor) {
        super(actor);
    }
}

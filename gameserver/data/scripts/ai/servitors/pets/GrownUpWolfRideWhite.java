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
@RetailClass(name = "grown_up_wolf_ride_white")
public class GrownUpWolfRideWhite extends PetActiveGrow {
    public GrownUpWolfRideWhite(Summon actor) {
        super(actor);
    }
}
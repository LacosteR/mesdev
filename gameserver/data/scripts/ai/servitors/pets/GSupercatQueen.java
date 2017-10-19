package ai.servitors.pets;

import ai.base.pets.GSupercatPet;
import l2p.gameserver.ai.base.RetailClass;
import l2p.gameserver.model.Summon;

/**
 * @author : Alice
 * @date : 27.03.17
 * @time : 0:19
 * <p/>
 */
@RetailClass(name = "g_supercat_queen")
public class GSupercatQueen extends GSupercatPet {
    public GSupercatQueen(Summon actor) {
        super(actor);
    }
}

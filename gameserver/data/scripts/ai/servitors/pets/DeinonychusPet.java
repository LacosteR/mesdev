package ai.servitors.pets;

import ai.base.pets.PetActiveGrow;
import l2p.gameserver.ai.base.RetailClass;
import l2p.gameserver.model.Summon;

/**
 * @author : Alice
 * @date : 27.03.17
 * @time : 0:31
 * <p/>
 */
@RetailClass(name = "deinonychus_pet")
public class DeinonychusPet extends PetActiveGrow {
    public DeinonychusPet(Summon actor) {
        super(actor);
    }
}

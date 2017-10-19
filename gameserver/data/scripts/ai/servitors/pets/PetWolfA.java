package ai.servitors.pets;

import ai.base.pets.PetPhysicalAttack;
import l2p.gameserver.ai.base.RetailClass;
import l2p.gameserver.model.Summon;

/**
 * @author : Alice
 * @date : 26.03.17
 * @time : 12:11
 * <p/>
 */
@RetailClass(name="pet_wolf_a")
public class PetWolfA extends PetPhysicalAttack {
    public PetWolfA(Summon actor) {
        super(actor);
    }
}

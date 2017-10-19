package ai.servitors.pets;

import ai.base.pets.SummonCriminal;
import l2p.gameserver.ai.base.RetailClass;
import l2p.gameserver.model.Summon;

/**
 * @author : Alice
 * @date : 27.03.17
 * @time : 0:31
 * <p/>
 */
@RetailClass(name = "sin_eater")
public class SinEater extends SummonCriminal {
    public SinEater(Summon actor) {
        super(actor);
    }
}

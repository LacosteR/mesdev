package ai.servitors.pets;

import ai.base.pets.SummonUseSkills;
import l2p.gameserver.ai.base.RetailClass;
import l2p.gameserver.model.Summon;

/**
 * @author : Alice
 * @date : 27.03.17
 * @time : 0:19
 * <p/>
 */
@RetailClass(name = "grown_up_wolf")
public class GrownUpWolf extends SummonUseSkills {
    public GrownUpWolf(Summon actor) {
        super(actor);
    }
}

package ai.servitors.pets;

import ai.base.pets.PetUseOneSkill;
import l2p.gameserver.ai.base.RetailClass;
import l2p.gameserver.model.Summon;

/**
 * @author : Alice
 * @date : 27.03.17
 * @time : 0:19
 * <p/>
 */
@RetailClass(name = "hatchling_of_twilight")
public class HatchlingOfTwilight extends PetUseOneSkill {
    public HatchlingOfTwilight(Summon actor) {
        super(actor);
    }
}

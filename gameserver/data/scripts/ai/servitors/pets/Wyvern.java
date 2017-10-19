package ai.servitors.pets;

import ai.base.pets.PetUseOneSkill;
import l2p.gameserver.ai.base.RetailClass;
import l2p.gameserver.model.Summon;

/**
 * @author : Alice
 * @date : 27.03.17
 * @time : 0:31
 * <p/>
 */
@RetailClass(name = "wyvern")
public class Wyvern extends PetUseOneSkill {
    public Wyvern(Summon actor) {
        super(actor);
    }
}

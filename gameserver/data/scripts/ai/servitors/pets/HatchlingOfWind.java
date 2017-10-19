package ai.servitors.pets;

import ai.base.pets.PetWarriorUse2Skill;
import l2p.gameserver.ai.base.RetailClass;
import l2p.gameserver.model.Summon;

/**
 * @author : Alice
 * @date : 27.03.17
 * @time : 0:19
 * <p/>
 */
@RetailClass(name = "hatchling_of_wind")
public class HatchlingOfWind extends PetWarriorUse2Skill {
    public HatchlingOfWind(Summon actor) {
        super(actor);
    }
}

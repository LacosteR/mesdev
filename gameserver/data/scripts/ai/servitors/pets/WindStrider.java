package ai.servitors.pets;

import ai.base.pets.PetWarriorUse2Skill;
import l2p.gameserver.ai.base.RetailClass;
import l2p.gameserver.model.Summon;

/**
 * @author : Alice
 * @date : 27.03.17
 * @time : 0:30
 * <p/>
 */
@RetailClass(name = "wind_strider")
public class WindStrider extends PetWarriorUse2Skill {
    public WindStrider(Summon actor) {
        super(actor);
    }
}

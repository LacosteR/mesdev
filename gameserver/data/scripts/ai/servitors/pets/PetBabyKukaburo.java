package ai.servitors.pets;

import ai.base.pets.PetCuteBabyUse2Skill;
import l2p.gameserver.ai.base.RetailClass;
import l2p.gameserver.model.Summon;

/**
 * @author : Alice
 * @date : 27.03.17
 * @time : 0:19
 * <p/>
 */
@RetailClass(name = "pet_baby_kukaburo")
public class PetBabyKukaburo extends PetCuteBabyUse2Skill {
    public PetBabyKukaburo(Summon actor) {
        super(actor);
    }
}

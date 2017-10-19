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
@RetailClass(name = "pet_baby_cougar")
public class PetBabyCougar extends PetCuteBabyUse2Skill {
    public PetBabyCougar(Summon actor) {
        super(actor);
    }
}

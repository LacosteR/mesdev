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
@RetailClass(name = "pet_baby_buffalo")
public class PetBabyBuffalo extends PetCuteBabyUse2Skill {
    public PetBabyBuffalo(Summon actor) {
        super(actor);
    }
}

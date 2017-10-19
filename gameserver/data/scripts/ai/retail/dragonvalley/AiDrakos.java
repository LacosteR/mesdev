package ai.retail.dragonvalley;

import l2p.gameserver.ai.base.RetailClass;
import l2p.gameserver.ai.retail.WarriorUseSkill;
import l2p.gameserver.model.instances.NpcInstance;
/**
 * @author : Alice
 * @date : 08.10.2017
 * @time : 11:09
 * <p>
 *     AI для дракосов
 * </p>
 */
@RetailClass(name = "ai_drakos")
public class AiDrakos extends WarriorUseSkill {
    public AiDrakos(NpcInstance actor) {
        super(actor);
    }
}

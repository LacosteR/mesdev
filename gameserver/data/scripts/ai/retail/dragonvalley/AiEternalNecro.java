package ai.retail.dragonvalley;

import l2p.gameserver.ai.base.RetailClass;
import l2p.gameserver.ai.retail.WarriorUseSkill;
import l2p.gameserver.model.instances.NpcInstance;
/**
 * @author : Alice
 * @date : 08.10.2017
 * @time : 13:43
 * <p/>
 */
@RetailClass(name = "ai_eternal_necro")
public class AiEternalNecro extends WarriorUseSkill {
    public AiEternalNecro(NpcInstance actor) {
        super(actor);
    }
}

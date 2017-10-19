package ai.retail.dragonvalley;

import l2p.gameserver.ai.base.RetailClass;
import l2p.gameserver.ai.retail.WarriorUseSkill;
import l2p.gameserver.model.instances.NpcInstance;
/**
 * @author : Alice
 * @date : 08.10.2017
 * @time : 11:19
 * <p/>
 */
@RetailClass(name = "ai_maluk_summoner")
public class AiMalukSummoner extends WarriorUseSkill{
    public AiMalukSummoner(NpcInstance actor) {
        super(actor);
    }
}

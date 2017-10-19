package ai.dragonvalley.pts;

import l2p.gameserver.ai.Fighter;
import l2p.gameserver.model.Creature;
import l2p.gameserver.model.instances.NpcInstance;
import l2p.technical.util.Rnd;

/**
 * @author : Alice
 * @date : 21.12.16
 * @time : 14:05
 * <p/>
 */
public class BloodyKarik extends Fighter {
    public BloodyKarik(NpcInstance actor) {
        super(actor);
    }

    @Override
    protected void onEvtDead(Creature killer) {
        if(actor.param1 == 0)
        {
            if(Rnd.get(15) < 1)
            {
                actor.createOnePrivate(22854, "BloodyKarik", 0, 0, (actor.getPositionComponent().getX() + 30), (actor.getPositionComponent().getY() + 10), actor.getPositionComponent().getZ(), 0, 1, 0, 0);
                actor.createOnePrivate(22854, "BloodyKarik", 0, 0, (actor.getPositionComponent().getX() + 30), (actor.getPositionComponent().getY() - 10), actor.getPositionComponent().getZ(), 0, 1, 0, 0);
                actor.createOnePrivate(22854, "BloodyKarik", 0, 0, (actor.getPositionComponent().getX() + 30), (actor.getPositionComponent().getY() + 30), actor.getPositionComponent().getZ(), 0, 1, 0, 0);
                actor.createOnePrivate(22854, "BloodyKarik", 0, 0, (actor.getPositionComponent().getX() + 30), (actor.getPositionComponent().getY() - 30), actor.getPositionComponent().getZ(), 0, 1, 0, 0);
                actor.createOnePrivate(22854, "BloodyKarik", 0, 0, (actor.getPositionComponent().getX() + 30), (actor.getPositionComponent().getY() - 50), actor.getPositionComponent().getZ(), 0, 1, 0, 0);
            }
        }
        super.onEvtDead(killer);
    }
}

package ai.seedofinfinity;

import l2p.gameserver.ai.DefaultAI;
import l2p.gameserver.model.Creature;
import l2p.gameserver.model.instances.NpcInstance;
import l2p.gameserver.tables.SkillTable;
import l2p.gameserver.utils.PositionUtils;
import l2p.technical.util.Rnd;

/**
 * @author pchayka
 */
public class WardofDeath extends DefaultAI {
    private static final int[] mobs = {22516, 22520, 22522, 22524};

    public WardofDeath(NpcInstance actor) {
        super(actor);
        actor.startImmobilized();
    }

    @Override
    protected boolean checkAggression(Creature target) {
        NpcInstance actor = getActor();
        if (PositionUtils.isInRange(target, actor, actor.getAggroRange(), false) && target.isPlayable() && !target.isDead() && !target.isInvisible()) {
            if (actor.getNpcId() == 18667) // trap skills
            {
                actor.doCast(SkillTable.getInstance().getInfo(Rnd.get(5423, 5424), 9), actor, false);
                actor.doDie(null);
            } else if (actor.getNpcId() == 18668) // trap spawn
            {
                for (int i = 0; i < Rnd.get(1, 4); i++)
                    actor.getPositionComponent().getReflection().addSpawnWithoutRespawn(mobs[Rnd.get(mobs.length)], actor.getPositionComponent().getLoc(), 100);
                actor.doDie(null);
            }
        }
        return true;
    }
}
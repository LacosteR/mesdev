package ai;

import l2p.gameserver.ThreadPoolManager;
import l2p.gameserver.ai.DefaultAI;
import l2p.gameserver.model.Creature;
import l2p.gameserver.model.Skill;
import l2p.gameserver.model.instances.NpcInstance;
import l2p.technical.threading.RunnableImpl;
import l2p.technical.util.Rnd;

public class Furance extends DefaultAI {
    public Furance(NpcInstance actor) {
        super(actor);
        actor.startImmobilized();
    }

    @Override
    protected void onEvtSpawn() {
        super.onEvtSpawn();

        NpcInstance actor = getActor();
        if (Rnd.chance(50))
            actor.setNpcState(1);
        ThreadPoolManager.getInstance().scheduleAtFixedRate(new Switch(), 5 * 60 * 1000L, 5 * 60 * 1000L);
    }

    public class Switch extends RunnableImpl {
        @Override
        public void runImpl() {
            NpcInstance actor = getActor();
            if (actor.getNpcState() == 1)
                actor.setNpcState(2);
            else
                actor.setNpcState(1);
        }
    }

    @Override
    protected void onEvtAttacked(Creature attacker, int damage, Skill skill) {
    }

    @Override
    protected void onEvtAggression(Creature target, int aggro) {
    }

    @Override
    protected boolean randomAnimation() {
        return false;
    }

    @Override
    public boolean isGlobalAI() {
        return true;
    }
}
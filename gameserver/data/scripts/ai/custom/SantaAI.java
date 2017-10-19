package ai.custom;

import l2p.gameserver.ai.DefaultAI;
import l2p.gameserver.model.instances.NpcInstance;
import l2p.gameserver.scripts.Functions;

public class SantaAI extends DefaultAI {
    private int _timer = 0;
    private static final String SANTAS_MESSAGE = "Yo nigga!";

    public SantaAI(NpcInstance actor) {
        super(actor);
    }

    @Override
    protected boolean thinkActive() {
        _timer++;
        if (_timer >= 12) {
            _timer = 0;

            final NpcInstance actor = getActor();
            if (actor == null)
                return false;

            actor.decayMe();
            actor.deleteMe();
        }

        return super.thinkActive();
    }

    @Override
    protected void onEvtSpawn() {
        super.onEvtSpawn();
        _timer = 0;
        Functions.npcSayInRange(getActor(), SANTAS_MESSAGE, 600);
    }
}

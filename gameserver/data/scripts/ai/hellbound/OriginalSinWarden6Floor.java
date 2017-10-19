package ai.hellbound;

import l2p.gameserver.ai.Fighter;
import l2p.gameserver.data.xml.holder.NpcHolder;
import l2p.gameserver.model.Creature;
import l2p.gameserver.model.SimpleSpawner;
import l2p.gameserver.model.instances.NpcInstance;
import l2p.gameserver.utils.Location;
import l2p.technical.util.Rnd;

/**
 * Original Sin Warden 8-го этажа Tully Workshop
 *
 * @автор VAVAN
 */
public class OriginalSinWarden6Floor extends Fighter {
    private static final int[] servants = {22424, 22425, 22426, 22427, 22428, 22429, 22430};
    private static final int[] DarionsFaithfulServants = {22405, 22406, 22407};

    public OriginalSinWarden6Floor(NpcInstance actor) {
        super(actor);
    }

    @Override
    protected void onEvtDead(Creature killer) {
        NpcInstance actor = getActor();

        if (Rnd.chance(15))
            try {
                Location loc = actor.getPositionComponent().getLoc();
                SimpleSpawner sp = new SimpleSpawner(NpcHolder.getInstance().getTemplate(DarionsFaithfulServants[Rnd.get(DarionsFaithfulServants.length - 1)]));
                sp.setLoc(Location.findPointToStay(actor, 150, 350));
                sp.doSpawn(true);
                sp.stopRespawn();
            } catch (Exception e) {
                e.printStackTrace();
            }
        super.onEvtDead(killer);
    }

}
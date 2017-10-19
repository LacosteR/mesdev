package ai.seedofdestruction;

import l2p.gameserver.ai.DefaultAI;
import l2p.gameserver.model.Creature;
import l2p.gameserver.model.instances.NpcInstance;
import l2p.gameserver.utils.Location;

/**
 * AI GreatPowerfulDevice (ID: 18777) в Seed of Destruction:
 * - при смерти всех в этом измерении шедулит открытие двери через 5 мин открывает двери
 * - при смерти всех в этом измерении спаунит толпу мобов вокруг обелиска
 *
 * @author pchayka
 */
public class GreatPowerfulDevice extends DefaultAI {
    private static final int[] MOBS = {22540, // White Dragon Leader
            22546, // Warrior of Light
            22542, // Dragon Steed Troop Magic Leader
            22547, // Dragon Steed Troop Healer
            22538 // Dragon Steed Troop Commander
    };
    private static final Location OBELISK_LOC = new Location(-245825, 217075, -12208);

    public GreatPowerfulDevice(NpcInstance actor) {
        super(actor);
        actor.block();
        actor.startDamageBlockedHp();
    }

    @Override
    protected void onEvtDead(Creature killer) {
        NpcInstance actor = getActor();
        if (checkAllDestroyed(actor.getNpcId())) {
            // Спаун мобов вокруг обелиска
            for (int i = 0; i < 6; i++)
                for (int mobId : MOBS)
                    actor.getPositionComponent().getReflection().addSpawnWithoutRespawn(mobId, Location.findPointToStay(OBELISK_LOC.clone().setZ(-12224), 600, 1200, actor.getPositionComponent().getGeoIndex()), 0);
            actor.getPositionComponent().getReflection().openDoor(12240027);
            for (NpcInstance n : actor.getPositionComponent().getReflection().getNpcs())
                if (n.getNpcId() == 18778)
                    n.stopDamageBlockedHp();
        }
        super.onEvtDead(killer);
    }

    /**
     * Проверяет, уничтожены ли все GreatPowerfulDevice в текущем измерении
     *
     * @return true если все уничтожены
     */
    private boolean checkAllDestroyed(int mobId) {
        for (NpcInstance n : getActor().getPositionComponent().getReflection().getNpcs())
            if (n.getNpcId() == mobId && !n.isDead())
                return false;
        return true;
    }
}
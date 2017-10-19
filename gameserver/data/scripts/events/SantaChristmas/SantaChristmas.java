package events.SantaChristmas;

import l2p.gameserver.Announcements;
import l2p.technical.util.Rnd;
import l2p.gameserver.data.xml.holder.NpcHolder;
import l2p.gameserver.listener.actor.OnDeathListener;
import l2p.gameserver.listener.actor.player.OnPlayerEnterListener;
import l2p.gameserver.model.Creature;
import l2p.gameserver.model.Player;
import l2p.gameserver.model.SimpleSpawner;
import l2p.gameserver.model.actor.listener.CharListenerList;
import l2p.gameserver.scripts.Functions;
import l2p.gameserver.scripts.ScriptFile;
import l2p.gameserver.templates.npc.NpcTemplate;
import l2p.gameserver.utils.ItemFunctions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SantaChristmas extends Functions implements ScriptFile, OnDeathListener, OnPlayerEnterListener {
    private static final int SANTA_ID = 13186;
    private static final int PRESENT_ID = 14616;
    private static final int SANTA_CHANCE = 1000000;

    private static final Logger _log = LoggerFactory.getLogger(SantaChristmas.class);

    private static boolean _active = false;

    @Override
    public void onLoad() {
        CharListenerList.addGlobal(this);
        if (isActive()) {
            _active = true;
            _log.info("Loaded Event: Santa Christmas [state: activated]");
        } else
            _log.info("Loaded Event: Santa Christmas [state: deactivated]");
    }

    /**
     * Читает статус эвента из базы.
     *
     * @return
     */
    private static boolean isActive() {
        return IsActive("SantaChristmas");
    }

    /**
     * Запускает эвент
     */
    public void startEvent() {
        Player player = getSelf();
        if (!player.getPlayerAccess().CanOperateGlobalEvents)
            return;

        if (SetActive("SantaChristmas", true)) {
            System.out.println("Event 'Santa Christmas' started.");
            Announcements.getInstance().announceByCustomMessage("scripts.events.Christmas.AnnounceEventStarted", null);
        } else
            player.sendMessage("Event 'Santa Christmas' already started.");

        _active = true;

        show("admin/events.htm", player);
    }

    /**
     * Останавливает эвент
     */
    public void stopEvent() {
        Player player = getSelf();
        if (!player.getPlayerAccess().CanOperateGlobalEvents)
            return;
        if (SetActive("SantaChristmas", false)) {
            System.out.println("Event 'Santa Christmas' stopped.");
            Announcements.getInstance().announceByCustomMessage("scripts.events.Christmas.AnnounceEventStoped", null);
        } else
            player.sendMessage("Event 'Santa Christmas' not started.");

        _active = false;

        show("admin/events.htm", player);
    }

    @Override
    public void onReload() {
        //
    }

    @Override
    public void onShutdown() {
        //
    }

    /**
     * Обработчик смерти мобов, управляющий эвентовым дропом
     */
    @Override
    public void onDeath(Creature cha, Creature killer) {
        if (_active && isEventMonster(cha.getNpcId()) && SimpleCheckDrop(cha, killer) && Rnd.get(SANTA_CHANCE, 1000000) <= SANTA_CHANCE) {
            SpawnSanta(cha);
            if (killer.getPlayer() != null) {
                ItemFunctions.addItem(killer.getPlayer(), PRESENT_ID, 1, true);
            }
        }
    }

    public boolean isEventMonster(int id) {
        return id == 2529 || id == 2530 || id == 2531 || id == 2532;
    }

    public static void SpawnSanta(Creature cha) {
        NpcTemplate template = NpcHolder.getInstance().getTemplate(SANTA_ID);
        if (template == null) {
            System.out.println("WARNING! Functions.SpawnNPCs template is null for npc: " + SANTA_ID);
            Thread.dumpStack();
            return;
        }

        SimpleSpawner sp = new SimpleSpawner(template);
        sp.setLoc(cha.getPositionComponent().getLoc());
        sp.setAmount(1);
        sp.setRespawnDelay(0);
        sp.init();
    }

    @Override
    public void onPlayerEnter(Player player) {
        if (_active)
            Announcements.getInstance().announceToPlayerByCustomMessage(player, "scripts.events.Christmas.AnnounceEventStarted", null);
    }
}

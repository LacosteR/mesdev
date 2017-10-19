package services.petevolve;

import l2p.gameserver.dao.PetDAO;
import l2p.gameserver.data.retail.holder.PetDataHolder;
import l2p.gameserver.model.Player;
import l2p.gameserver.model.instances.NpcInstance;
import l2p.gameserver.model.instances.PetInstance;
import l2p.gameserver.model.items.ItemInstance;
import l2p.gameserver.network.serverpackets.SystemMessage2;
import l2p.gameserver.scripts.Functions;
import l2p.gameserver.templates.player.PetTemplate;
import l2p.technical.dao.JdbcEntityState;

public class clanhall extends Functions {
    //TODO: переписать! Много дублирующегося кода!
    // -- Pet ID --
    private static final int GREAT_WOLF = 16025;
    private static final int WHITE_WOLF = 16037;
    private static final int FENRIR = 16041;
    private static final int WHITE_FENRIR = 16042;
    private static final int WIND_STRIDER = 12526;
    private static final int RED_WIND_STRIDER = 16038;
    private static final int STAR_STRIDER = 12527;
    private static final int RED_STAR_STRIDER = 16039;
    private static final int TWILING_STRIDER = 12528;
    private static final int RED_TWILING_STRIDER = 16040;

    // -- First Item ID --
    private static final int GREAT_WOLF_NECKLACE = PetDataHolder.getInstance().getControlItemId("grown_up_wolf");
    private static final int FENRIR_NECKLACE = PetDataHolder.getInstance().getControlItemId("grown_up_wolf_ride");
    private static final int WIND_STRIDER_ITEM = PetDataHolder.getInstance().getControlItemId("wind_strider");
    private static final int STAR_STRIDER_ITEM = PetDataHolder.getInstance().getControlItemId("star_strider");
    private static final int TWILIGHT_STRIDER_ITEM = PetDataHolder.getInstance().getControlItemId("twilight_strider");

    // -- Second Item ID --
    private static final int WHITE_WOLF_NECKLACE = PetDataHolder.getInstance().getControlItemId("grown_up_wolf_white");
    private static final int WHITE_FENRIR_NECKLACE = PetDataHolder.getInstance().getControlItemId("grown_up_wolf_ride_white");
    private static final int RED_WS_ITEM = PetDataHolder.getInstance().getControlItemId("red_wind_strider");
    private static final int RED_SS_ITEM = PetDataHolder.getInstance().getControlItemId("red_star_strider");
    private static final int RED_TW_ITEM = PetDataHolder.getInstance().getControlItemId("red_twilight_strider");

    public void evolve() {
        Player player = getSelf();
        NpcInstance npc = getNpc();
        if (player == null || npc == null)
            return;
        show("scripts/services/petevolve/chamberlain.htm", player, npc);
    }

    public void greatsw(String[] direction) {
        Player player = getSelf();
        NpcInstance npc = getNpc();
        if (player == null || npc == null)
            return;
        boolean fwd = Integer.parseInt(direction[0]) == 1;

        if (player.getInventory().getCountOf(fwd ? GREAT_WOLF_NECKLACE : WHITE_WOLF_NECKLACE) > 1) {
            show("scripts/services/petevolve/error_3.htm", player, npc);
            return;
        }
        if (player.getPet() != null) {
            show("scripts/services/petevolve/error_4.htm", player, npc);
            return;
        }
        ItemInstance collar = player.getInventory().getItemByItemId(fwd ? GREAT_WOLF_NECKLACE : WHITE_WOLF_NECKLACE);
        if (collar == null) {
            show("scripts/services/petevolve/no_item.htm", player, npc);
            return;
        }
        int npcId = PetDataHolder.getInstance().getPetId(collar.getItemId());
        if (npcId == 0)
            return;
        PetInstance pet = PetDAO.getInstance().restore(collar, new PetTemplate(npcId), player);

        if (npcId != (fwd ? GREAT_WOLF : WHITE_WOLF)) {
            show("scripts/services/petevolve/error_2.htm", player, npc);
            return;
        }
        if (pet.getLevel() < 55) {
            show("scripts/services/petevolve/error_lvl_greatw.htm", player, npc);
            return;
        }

        collar.setItemId(fwd ? WHITE_WOLF_NECKLACE : GREAT_WOLF_NECKLACE);
        collar.setJdbcState(JdbcEntityState.UPDATED);
        collar.update();
        player.sendItemList(false);
        player.sendPacket(SystemMessage2.obtainItems((fwd ? WHITE_WOLF_NECKLACE : GREAT_WOLF_NECKLACE), 1, 0));
        show("scripts/services/petevolve/end_msg3_gwolf.htm", player, npc);
    }

    public void fenrir(String[] direction) {
        Player player = getSelf();
        NpcInstance npc = getNpc();
        if (player == null || npc == null)
            return;
        boolean fwd = Integer.parseInt(direction[0]) == 1;

        if (player.getInventory().getCountOf(fwd ? FENRIR_NECKLACE : WHITE_FENRIR_NECKLACE) > 1) {
            show("scripts/services/petevolve/error_3.htm", player, npc);
            return;
        }
        if (player.getPet() != null) {
            show("scripts/services/petevolve/error_4.htm", player, npc);
            return;
        }
        ItemInstance collar = player.getInventory().getItemByItemId(fwd ? FENRIR_NECKLACE : WHITE_FENRIR_NECKLACE);
        if (collar == null) {
            show("scripts/services/petevolve/no_item.htm", player, npc);
            return;
        }
        int npcId = PetDataHolder.getInstance().getPetId(collar.getItemId());
        if (npcId == 0)
            return;
        PetInstance pet = PetDAO.getInstance().restore(collar, new PetTemplate(npcId), player);

        if (npcId != (fwd ? FENRIR : WHITE_FENRIR)) {
            show("scripts/services/petevolve/error_2.htm", player, npc);
            return;
        }
        if (pet.getLevel() < 55) {
            show("scripts/services/petevolve/error_lvl_fenrir.htm", player, npc);
            return;
        }

        collar.setItemId(fwd ? WHITE_FENRIR_NECKLACE : FENRIR_NECKLACE);
        collar.setJdbcState(JdbcEntityState.UPDATED);
        collar.update();
        player.sendItemList(false);
        player.sendPacket(SystemMessage2.obtainItems((fwd ? WHITE_FENRIR_NECKLACE : FENRIR_NECKLACE), 1, 0));
        show("scripts/services/petevolve/end_msg2_fenrir.htm", player, npc);
    }

    public void fenrirW(String[] direction) {
        Player player = getSelf();
        NpcInstance npc = getNpc();
        if (player == null || npc == null)
            return;
        boolean fwd = Integer.parseInt(direction[0]) == 1;

        if (player.getInventory().getCountOf(fwd ? WHITE_WOLF_NECKLACE : WHITE_FENRIR_NECKLACE) > 1) {
            show("scripts/services/petevolve/error_3.htm", player, npc);
            return;
        }
        if (player.getPet() != null) {
            show("scripts/services/petevolve/error_4.htm", player, npc);
            return;
        }
        ItemInstance collar = player.getInventory().getItemByItemId(fwd ? WHITE_WOLF_NECKLACE : WHITE_FENRIR_NECKLACE);
        if (collar == null) {
            show("scripts/services/petevolve/no_item.htm", player, npc);
            return;
        }
        int npcId = PetDataHolder.getInstance().getPetId(collar.getItemId());
        if (npcId == 0)
            return;
        PetInstance pet = PetDAO.getInstance().restore(collar, new PetTemplate(npcId), player);

        if (npcId != (fwd ? WHITE_WOLF : WHITE_FENRIR)) {
            show("scripts/services/petevolve/error_2.htm", player, npc);
            return;
        }
        if (pet.getLevel() < 70) {
            show("scripts/services/petevolve/no_level_gw.htm", player, npc);
            return;
        }

        collar.setItemId(fwd ? WHITE_FENRIR_NECKLACE : WHITE_WOLF_NECKLACE);
        collar.setJdbcState(JdbcEntityState.UPDATED);
        collar.update();
        player.sendItemList(false);
        player.sendPacket(SystemMessage2.obtainItems((fwd ? WHITE_FENRIR_NECKLACE : WHITE_WOLF_NECKLACE), 1, 0));
        show("scripts/services/petevolve/yes_wolf.htm", player, npc);
    }

    public void wstrider(String[] direction) {
        Player player = getSelf();
        NpcInstance npc = getNpc();
        if (player == null || npc == null)
            return;
        boolean fwd = Integer.parseInt(direction[0]) == 1;

        if (player.getInventory().getCountOf(fwd ? WIND_STRIDER_ITEM : RED_WS_ITEM) > 1) {
            show("scripts/services/petevolve/error_3.htm", player, npc);
            return;
        }
        if (player.getPet() != null) {
            show("scripts/services/petevolve/error_4.htm", player, npc);
            return;
        }
        ItemInstance collar = player.getInventory().getItemByItemId(fwd ? WIND_STRIDER_ITEM : RED_WS_ITEM);
        if (collar == null) {
            show("scripts/services/petevolve/no_item.htm", player, npc);
            return;
        }
        int npcId = PetDataHolder.getInstance().getPetId(collar.getItemId());
        if (npcId == 0)
            return;
        PetInstance pet = PetDAO.getInstance().restore(collar, new PetTemplate(npcId), player);

        if (npcId != (fwd ? WIND_STRIDER : RED_WIND_STRIDER)) {
            show("scripts/services/petevolve/error_2.htm", player, npc);
            return;
        }
        if (pet.getLevel() < 55) {
            show("scripts/services/petevolve/error_lvl_strider.htm", player, npc);
            return;
        }

        collar.setItemId(fwd ? RED_WS_ITEM : WIND_STRIDER_ITEM);
        collar.setJdbcState(JdbcEntityState.UPDATED);
        collar.update();
        player.sendItemList(false);
        player.sendPacket(SystemMessage2.obtainItems((fwd ? RED_WS_ITEM : WIND_STRIDER_ITEM), 1, 0));
        show("scripts/services/petevolve/end_msg_strider.htm", player, npc);
    }

    public void sstrider(String[] direction) {
        Player player = getSelf();
        NpcInstance npc = getNpc();
        if (player == null || npc == null)
            return;
        boolean fwd = Integer.parseInt(direction[0]) == 1;

        if (player.getInventory().getCountOf(fwd ? STAR_STRIDER_ITEM : RED_SS_ITEM) > 1) {
            show("scripts/services/petevolve/error_3.htm", player, npc);
            return;
        }
        if (player.getPet() != null) {
            show("scripts/services/petevolve/error_4.htm", player, npc);
            return;
        }
        ItemInstance collar = player.getInventory().getItemByItemId(fwd ? STAR_STRIDER_ITEM : RED_SS_ITEM);
        if (collar == null) {
            show("scripts/services/petevolve/no_item.htm", player, npc);
            return;
        }
        int npcId = PetDataHolder.getInstance().getPetId(collar.getItemId());
        if (npcId == 0)
            return;
        PetInstance pet = PetDAO.getInstance().restore(collar, new PetTemplate(npcId), player);

        if (npcId != (fwd ? STAR_STRIDER : RED_STAR_STRIDER)) {
            show("scripts/services/petevolve/error_2.htm", player, npc);
            return;
        }
        if (pet.getLevel() < 55) {
            show("scripts/services/petevolve/error_lvl_strider.htm", player, npc);
            return;
        }

        collar.setItemId(fwd ? RED_SS_ITEM : STAR_STRIDER_ITEM);
        collar.setJdbcState(JdbcEntityState.UPDATED);
        collar.update();
        player.sendItemList(false);
        player.sendPacket(SystemMessage2.obtainItems((fwd ? RED_SS_ITEM : STAR_STRIDER_ITEM), 1, 0));
        show("scripts/services/petevolve/end_msg_strider.htm", player, npc);
    }

    public void tstrider(String[] direction) {
        Player player = getSelf();
        NpcInstance npc = getNpc();
        if (player == null || npc == null)
            return;
        boolean fwd = Integer.parseInt(direction[0]) == 1;

        if (player.getInventory().getCountOf(fwd ? TWILIGHT_STRIDER_ITEM : RED_TW_ITEM) > 1) {
            show("scripts/services/petevolve/error_3.htm", player, npc);
            return;
        }
        if (player.getPet() != null) {
            show("scripts/services/petevolve/error_4.htm", player, npc);
            return;
        }
        ItemInstance collar = player.getInventory().getItemByItemId(fwd ? TWILIGHT_STRIDER_ITEM : RED_TW_ITEM);
        if (collar == null) {
            show("scripts/services/petevolve/no_item.htm", player, npc);
            return;
        }
        int npcId = PetDataHolder.getInstance().getPetId(collar.getItemId());
        if (npcId == 0)
            return;
        PetInstance pet = PetDAO.getInstance().restore(collar, new PetTemplate(npcId), player);

        if (npcId != (fwd ? TWILING_STRIDER : RED_TWILING_STRIDER)) {
            show("scripts/services/petevolve/error_2.htm", player, npc);
            return;
        }
        if (pet.getLevel() < 55) {
            show("scripts/services/petevolve/error_lvl_strider.htm", player, npc);
            return;
        }

        collar.setItemId(fwd ? RED_TW_ITEM : TWILIGHT_STRIDER_ITEM);
        collar.setJdbcState(JdbcEntityState.UPDATED);
        collar.update();
        player.sendItemList(false);
        player.sendPacket(SystemMessage2.obtainItems((fwd ? RED_TW_ITEM : TWILIGHT_STRIDER_ITEM), 1, 0));
        show("scripts/services/petevolve/end_msg_strider.htm", player, npc);
    }
}
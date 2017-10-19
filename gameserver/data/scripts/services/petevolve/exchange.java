package services.petevolve;

import l2p.gameserver.cache.Msg;
import l2p.gameserver.config.ConfigOthers;
import l2p.gameserver.config.ConfigServices;
import l2p.gameserver.data.retail.holder.PetDataHolder;
import l2p.gameserver.data.xml.holder.ItemHolder;
import l2p.gameserver.model.Player;
import l2p.gameserver.model.Summon;
import l2p.gameserver.model.items.ItemInstance;
import l2p.gameserver.network.serverpackets.InventoryUpdate;
import l2p.gameserver.network.serverpackets.components.SystemMsg;
import l2p.gameserver.scripts.Functions;
import l2p.gameserver.templates.item.ItemTemplate;
import l2p.gameserver.utils.Util;
import l2p.technical.dao.JdbcEntityState;

public class exchange extends Functions {
    /**
     * Билеты для обмена *
     */
    private static final int PEticketB = 7583;
    private static final int PEticketC = 7584;
    private static final int PEticketK = 7585;

    /**
     * Дудки для вызова петов *
     */
    private static final int BbuffaloP = 6648;
    private static final int BcougarC = 6649;
    private static final int BkookaburraO = 6650;

    public void exch_1() {
        Player player = getSelf();
        if (player == null)
            return;
        if (getItemCount(player, PEticketB) >= 1) {
            removeItem(player, PEticketB, 1);
            addItem(player, BbuffaloP, 1);
            return;
        }
        show("scripts/services/petevolve/exchange_no.htm", player);
    }

    public void exch_2() {
        Player player = getSelf();
        if (player == null)
            return;
        if (getItemCount(player, PEticketC) >= 1) {
            removeItem(player, PEticketC, 1);
            addItem(player, BcougarC, 1);
            return;
        }
        show("scripts/services/petevolve/exchange_no.htm", player);
    }

    public void exch_3() {
        Player player = getSelf();
        if (player == null)
            return;

        if (getItemCount(player, PEticketK) >= 1) {
            removeItem(player, PEticketK, 1);
            addItem(player, BkookaburraO, 1);
            return;
        }
        show("scripts/services/petevolve/exchange_no.htm", player);
    }

    public void showBabyPetExchange() {
        Player player = getSelf();
        if (player == null)
            return;
        if (!ConfigServices.SERVICES_EXCHANGE_BABY_PET_ENABLED) {
            show("Сервис отключен.", player);
            return;
        }
        ItemTemplate item = ItemHolder.getInstance().getTemplate(ConfigServices.SERVICES_EXCHANGE_BABY_PET_ITEM);
        String out = "";
        out += "<html><body><font color=3293F3>Вы можете в любое время обменять вашего Improved Baby пета на другой вид, без потери опыта. Пет при этом должен быть вызван.</font>";
        out += "<br><font color=LEVEL>Стоимость обмена: " + Util.formatAdena(ConfigServices.SERVICES_EXCHANGE_BABY_PET_PRICE) + " " + item.getName() + "</font>";
        out += "<br><button width=250 height=15 back=\"L2UI_CT1.Button_DF_Down\" fore=\"L2UI_CT1.Button_DF\" action=\"bypass -h scripts_services.petevolve.exchange:exToCougar\" value=\"Обменять на Improved Cougar\">";
        out += "<br1><button width=250 height=15 back=\"L2UI_CT1.Button_DF_Down\" fore=\"L2UI_CT1.Button_DF\" action=\"bypass -h scripts_services.petevolve.exchange:exToBuffalo\" value=\"Обменять на Improved Buffalo\">";
        out += "<br1><button width=250 height=15 back=\"L2UI_CT1.Button_DF_Down\" fore=\"L2UI_CT1.Button_DF\" action=\"bypass -h scripts_services.petevolve.exchange:exToKookaburra\" value=\"Обменять на Improved Kookaburra\">";
        out += "</body></html>";
        show(out, player);
    }

    public void exToCougar() {
        Player player = getSelf();
        if (player == null)
            return;
        if (!ConfigServices.SERVICES_EXCHANGE_BABY_PET_ENABLED) {
            show("Сервис отключен.", player);
            return;
        }
        Summon pl_pet = player.getPet();
        if (pl_pet == null || pl_pet.isDead() || !(pl_pet.getNpcId() == IMPROVED_BABY_BUFFALO_ID || pl_pet.getNpcId() == IMPROVED_BABY_KOOKABURRA_ID)) {
            show("Пет должен быть вызван.", player);
            return;
        }
        if (player.getInventory().destroyItemByItemId(ConfigServices.SERVICES_EXCHANGE_BABY_PET_ITEM, ConfigServices.SERVICES_EXCHANGE_BABY_PET_PRICE)) {
            ItemInstance control = player.getInventory().getItemByObjectId(player.getPet().getControlItemObjId());
            control.setItemId(PetDataHolder.getInstance().getControlItemId(16036));
            control.setJdbcState(JdbcEntityState.UPDATED);
            control.update();
            player.sendPacket(new InventoryUpdate().addModifiedItem(control));
            player.getPet().unSummon();
            show("Пет изменен.", player);
        } else if (ConfigServices.SERVICES_EXCHANGE_BABY_PET_ITEM == 57)
            player.sendPacket(Msg.YOU_DO_NOT_HAVE_ENOUGH_ADENA);
        else
            player.sendPacket(SystemMsg.INCORRECT_ITEM_COUNT);
    }

    private static int IMPROVED_BABY_COUGAR_ID = 16036; // TODO: убрать, переписать скрипт на основании petdata.txt, пока пусть работает так
    private static int IMPROVED_BABY_KOOKABURRA_ID = 16035;
    private static int IMPROVED_BABY_BUFFALO_ID = 16034;

    public void exToBuffalo() {
        Player player = getSelf();
        if (player == null)
            return;
        if (!ConfigServices.SERVICES_EXCHANGE_BABY_PET_ENABLED) {
            show("Сервис отключен.", player);
            return;
        }
        Summon pl_pet = player.getPet();
        if (pl_pet == null || pl_pet.isDead() || !(pl_pet.getNpcId() == IMPROVED_BABY_COUGAR_ID || pl_pet.getNpcId() == IMPROVED_BABY_KOOKABURRA_ID)) {
            show("Пет должен быть вызван.", player);
            return;
        }
        if (ConfigOthers.IMPROVED_PETS_LIMITED_USE && player.isMageClass()) {
            show("Этот пет только для воинов.", player);
            return;
        }
        if (player.getInventory().destroyItemByItemId(ConfigServices.SERVICES_EXCHANGE_BABY_PET_ITEM, ConfigServices.SERVICES_EXCHANGE_BABY_PET_PRICE)) {
            ItemInstance control = player.getInventory().getItemByObjectId(player.getPet().getControlItemObjId());
            control.setItemId(PetDataHolder.getInstance().getControlItemId(16034));
            control.setJdbcState(JdbcEntityState.UPDATED);
            control.update();
            player.sendPacket(new InventoryUpdate().addModifiedItem(control));
            player.getPet().unSummon();
            show("Пет изменен.", player);
        } else if (ConfigServices.SERVICES_EXCHANGE_BABY_PET_ITEM == 57)
            player.sendPacket(Msg.YOU_DO_NOT_HAVE_ENOUGH_ADENA);
        else
            player.sendPacket(SystemMsg.INCORRECT_ITEM_COUNT);
    }

    public void exToKookaburra() {
        Player player = getSelf();
        if (player == null)
            return;
        if (!ConfigServices.SERVICES_EXCHANGE_BABY_PET_ENABLED) {
            show("Сервис отключен.", player);
            return;
        }
        Summon pl_pet = player.getPet();
        if (pl_pet == null || pl_pet.isDead() || !(pl_pet.getNpcId() == IMPROVED_BABY_BUFFALO_ID || pl_pet.getNpcId() == IMPROVED_BABY_COUGAR_ID)) {
            show("Пет должен быть вызван.", player);
            return;
        }
        if (ConfigOthers.IMPROVED_PETS_LIMITED_USE && !player.isMageClass()) {
            show("Этот пет только для магов.", player);
            return;
        }
        if (player.getInventory().destroyItemByItemId(ConfigServices.SERVICES_EXCHANGE_BABY_PET_ITEM, ConfigServices.SERVICES_EXCHANGE_BABY_PET_PRICE)) {
            ItemInstance control = player.getInventory().getItemByObjectId(player.getPet().getControlItemObjId());
            control.setItemId(PetDataHolder.getInstance().getControlItemId(16035));
            control.setJdbcState(JdbcEntityState.UPDATED);
            control.update();
            player.sendPacket(new InventoryUpdate().addModifiedItem(control));
            player.getPet().unSummon();
            show("Пет изменен.", player);
        } else if (ConfigServices.SERVICES_EXCHANGE_BABY_PET_ITEM == 57)
            player.sendPacket(Msg.YOU_DO_NOT_HAVE_ENOUGH_ADENA);
        else
            player.sendPacket(SystemMsg.INCORRECT_ITEM_COUNT);
    }

    public static String DialogAppend_30731(Integer val) {
        return getHtmlAppends(val);
    }

    public static String DialogAppend_30827(Integer val) {
        return getHtmlAppends(val);
    }

    public static String DialogAppend_30828(Integer val) {
        return getHtmlAppends(val);
    }

    public static String DialogAppend_30829(Integer val) {
        return getHtmlAppends(val);
    }

    public static String DialogAppend_30830(Integer val) {
        return getHtmlAppends(val);
    }

    public static String DialogAppend_30831(Integer val) {
        return getHtmlAppends(val);
    }

    public static String DialogAppend_30869(Integer val) {
        return getHtmlAppends(val);
    }

    public static String DialogAppend_31067(Integer val) {
        return getHtmlAppends(val);
    }

    public static String DialogAppend_31265(Integer val) {
        return getHtmlAppends(val);
    }

    public static String DialogAppend_31309(Integer val) {
        return getHtmlAppends(val);
    }

    public static String DialogAppend_31954(Integer val) {
        return getHtmlAppends(val);
    }

    private static String getHtmlAppends(Integer val) {
        String ret = "";
        if (val != 0)
            return ret;
        if (ConfigServices.SERVICES_CHANGE_PET_NAME_ENABLED)
            ret = "<br>[scripts_services.petevolve.exchange:showErasePetName|Обнулить имя у пета]";
        if (ConfigServices.SERVICES_EXCHANGE_BABY_PET_ENABLED)
            ret += "<br>[scripts_services.petevolve.exchange:showBabyPetExchange|Обменять Improved Baby пета]";
        return ret;
    }
}
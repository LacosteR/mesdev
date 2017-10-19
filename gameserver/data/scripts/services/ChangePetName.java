package services;

import l2p.gameserver.cache.Msg;
import l2p.technical.dao.JdbcEntityState;
import l2p.gameserver.config.ConfigServices;
import l2p.gameserver.data.xml.holder.ItemHolder;
import l2p.gameserver.model.Player;
import l2p.gameserver.model.Summon;
import l2p.gameserver.model.instances.PetInstance;
import l2p.gameserver.model.items.ItemInstance;
import l2p.gameserver.network.serverpackets.InventoryUpdate;
import l2p.gameserver.network.serverpackets.components.SystemMsg;
import l2p.gameserver.scripts.Functions;
import l2p.gameserver.templates.item.ItemTemplate;
import l2p.gameserver.utils.Util;

/**
 * @author : Alice
 * @date : 25.11.16
 * @time : 5:30
 * <p/>
 * desc
 */
public class ChangePetName extends Functions {

    public void showErasePetName() {
        Player player = getSelf();
        if (player == null)
            return;
        if (!ConfigServices.SERVICES_CHANGE_PET_NAME_ENABLED) {
            show("Сервис отключен.", player);
            return;
        }
        ItemTemplate item = ItemHolder.getInstance().getTemplate(ConfigServices.SERVICES_CHANGE_PET_NAME_ITEM);
        String out = "";
        out += "<html><body><font color=3293F3>Вы можете обнулить имя у пета, для того чтобы назначить новое. Пет при этом должен быть вызван.</font>";
        out += "<br><font color=LEVEL>Стоимость обнуления: " + Util.formatAdena(ConfigServices.SERVICES_CHANGE_PET_NAME_PRICE) + " " + item.getName() + "</font>";
        out += "<br><button width=100 height=15 back=\"L2UI_CT1.Button_DF_Down\" fore=\"L2UI_CT1.Button_DF\" action=\"bypass -h scripts_services.petevolve.exchange:erasePetName\" value=\"Обнулить имя\">";
        out += "</body></html>";
        show(out, player);
    }

    public void erasePetName() {
        Player player = getSelf();
        if (player == null)
            return;
        if (!ConfigServices.SERVICES_CHANGE_PET_NAME_ENABLED) {
            show("Сервис отключен.", player);
            return;
        }
        Summon pet = player.getPet();
        if (pet == null || !pet.isPet()) {
            show("Питомец должен быть вызван.", player);
            return;
        }
        PetInstance pl_pet = (PetInstance)player.getPet();
        if (player.getInventory().destroyItemByItemId(ConfigServices.SERVICES_CHANGE_PET_NAME_ITEM, ConfigServices.SERVICES_CHANGE_PET_NAME_PRICE)) {
            pl_pet.setName(pl_pet.getTemplate().name);
            pl_pet.broadcastCharInfo();

            ItemInstance control = pl_pet.getControlItem();
            if (control != null) {
                control.setCustomType2(1);
                control.setJdbcState(JdbcEntityState.UPDATED);
                control.update();
                player.sendPacket(new InventoryUpdate().addModifiedItem(control));
            }
            show("Имя стерто.", player);
        } else if (ConfigServices.SERVICES_CHANGE_PET_NAME_ITEM == 57)
            player.sendPacket(Msg.YOU_DO_NOT_HAVE_ENOUGH_ADENA);
        else
            player.sendPacket(SystemMsg.INCORRECT_ITEM_COUNT);
    }
}

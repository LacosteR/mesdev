package services;

import l2p.gameserver.cache.Msg;
import l2p.gameserver.config.ConfigCharacter;
import l2p.gameserver.config.ConfigServices;
import l2p.gameserver.data.xml.holder.ItemHolder;
import l2p.gameserver.model.Player;
import l2p.gameserver.network.serverpackets.components.SystemMsg;
import l2p.gameserver.scripts.Functions;
import l2p.gameserver.templates.item.ItemTemplate;

public class ExpandCWH extends Functions {
    public void get() {
        Player player = getSelf();
        if (player == null)
            return;

        if (!ConfigServices.SERVICES_EXPAND_CWH_ENABLED) {
            show("Сервис отключен.", player);
            return;
        }

        if (player.getClan() == null) {
            player.sendMessage("You must be in clan.");
            return;
        }

        if (player.getInventory().destroyItemByItemId(ConfigServices.SERVICES_EXPAND_CWH_ITEM, ConfigServices.SERVICES_EXPAND_CWH_PRICE)) {
            player.getClan().setWhBonus(player.getClan().getWhBonus() + 1);
            player.sendMessage("Warehouse capacity is now " + (ConfigCharacter.WAREHOUSE_SLOTS_CLAN + player.getClan().getWhBonus()));
        } else if (ConfigServices.SERVICES_EXPAND_CWH_ITEM == 57)
            player.sendPacket(Msg.YOU_DO_NOT_HAVE_ENOUGH_ADENA);
        else
            player.sendPacket(SystemMsg.INCORRECT_ITEM_COUNT);

        show();
    }

    public void show() {
        Player player = getSelf();
        if (player == null)
            return;

        if (!ConfigServices.SERVICES_EXPAND_WAREHOUSE_ENABLED) {
            show("Сервис отключен.", player);
            return;
        }

        if (player.getClan() == null) {
            player.sendMessage("You must be in clan.");
            return;
        }

        ItemTemplate item = ItemHolder.getInstance().getTemplate(ConfigServices.SERVICES_EXPAND_CWH_ITEM);

        String out = "";

        out += "<html><body>Расширение кланового склада";
        out += "<br><br><table>";
        out += "<tr><td>Текущий размер:</td><td>" + (ConfigCharacter.WAREHOUSE_SLOTS_CLAN + player.getClan().getWhBonus()) + "</td></tr>";
        out += "<tr><td>Стоимость слота:</td><td>" + ConfigServices.SERVICES_EXPAND_CWH_PRICE + " " + item.getName() + "</td></tr>";
        out += "</table><br><br>";
        out += "<button width=100 height=15 back=\"L2UI_CT1.Button_DF_Down\" fore=\"L2UI_CT1.Button_DF\" action=\"bypass -h scripts_services.ExpandCWH:get\" value=\"Расширить\">";
        out += "</body></html>";

        show(out, player);
    }
}
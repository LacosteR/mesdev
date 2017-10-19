package services;

import l2p.gameserver.cache.Msg;
import l2p.gameserver.config.ConfigServices;
import l2p.gameserver.data.xml.holder.ItemHolder;
import l2p.gameserver.model.Player;
import l2p.gameserver.network.serverpackets.components.SystemMsg;
import l2p.gameserver.scripts.Functions;
import l2p.gameserver.templates.item.ItemTemplate;

public class ExpandInventory extends Functions {
    public void get() {
        Player player = getSelf();
        if (player == null)
            return;

        if (!ConfigServices.SERVICES_EXPAND_INVENTORY_ENABLED) {
            show("Сервис отключен.", player);
            return;
        }

        if (player.getInventoryLimit() >= ConfigServices.SERVICES_EXPAND_INVENTORY_MAX) {
            player.sendMessage("Already max count.");
            return;
        }

        if (player.getInventory().destroyItemByItemId(ConfigServices.SERVICES_EXPAND_INVENTORY_ITEM, ConfigServices.SERVICES_EXPAND_INVENTORY_PRICE)) {
            player.setExpandInventory(player.getExpandInventory() + 1);
            player.setVar("ExpandInventory", String.valueOf(player.getExpandInventory()), -1);
            player.sendMessage("Inventory capacity is now " + player.getInventoryLimit());
        } else if (ConfigServices.SERVICES_EXPAND_INVENTORY_ITEM == 57)
            player.sendPacket(Msg.YOU_DO_NOT_HAVE_ENOUGH_ADENA);
        else
            player.sendPacket(SystemMsg.INCORRECT_ITEM_COUNT);

        show();
    }

    public void show() {
        Player player = getSelf();
        if (player == null)
            return;

        if (!ConfigServices.SERVICES_EXPAND_INVENTORY_ENABLED) {
            show("Сервис отключен.", player);
            return;
        }

        ItemTemplate item = ItemHolder.getInstance().getTemplate(ConfigServices.SERVICES_EXPAND_INVENTORY_ITEM);

        String out = "";

        out += "<html><body>Расширение инвентаря";
        out += "<br><br><table>";
        out += "<tr><td>Текущий размер:</td><td>" + player.getInventoryLimit() + "</td></tr>";
        out += "<tr><td>Максимальный размер:</td><td>" + ConfigServices.SERVICES_EXPAND_INVENTORY_MAX + "</td></tr>";
        out += "<tr><td>Стоимость слота:</td><td>" + ConfigServices.SERVICES_EXPAND_INVENTORY_PRICE + " " + item.getName() + "</td></tr>";
        out += "</table><br><br>";
        out += "<button width=100 height=15 back=\"L2UI_CT1.Button_DF_Down\" fore=\"L2UI_CT1.Button_DF\" action=\"bypass -h scripts_services.ExpandInventory:get\" value=\"Расширить\">";
        out += "</body></html>";

        show(out, player);
    }
}
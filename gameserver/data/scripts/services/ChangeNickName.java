package services;

import l2p.gameserver.cache.Msg;
import l2p.gameserver.config.ConfigServer;
import l2p.gameserver.config.ConfigServices;
import l2p.gameserver.dao.CharacterDAO;
import l2p.gameserver.model.Player;
import l2p.gameserver.model.entity.events.impl.SiegeEvent;
import l2p.gameserver.network.serverpackets.components.CustomMessage;
import l2p.gameserver.network.serverpackets.components.SystemMsg;
import l2p.gameserver.scripts.Functions;
import l2p.gameserver.utils.Log;
import l2p.gameserver.utils.Util;

/**
 * @author : Alice
 * @date : 25.11.16
 * @time : 5:26
 * <p/>
 * desc
 */
public class ChangeNickName extends Functions {
    public void rename_page() {
        Player player = getSelf();
        if (player == null)
            return;

        String append = "!Rename";
        append += "<br>";
        append += "<font color=\"LEVEL\">" + new CustomMessage("scripts.services.Rename.RenameFor", getSelf()).addString(Util.formatAdena(ConfigServices.SERVICES_CHANGE_NICK_PRICE)).addItemName(ConfigServices.SERVICES_CHANGE_NICK_ITEM) + "</font>";
        append += "<table>";
        append += "<tr><td>" + new CustomMessage("scripts.services.Rename.NewName", getSelf()) + " <edit var=\"new_name\" width=80></td></tr>";
        append += "<tr><td></td></tr>";
        append += "<tr><td><button value=\"" + new CustomMessage("scripts.services.Rename.RenameButton", getSelf()) + "\" action=\"bypass -h scripts_services.Rename:rename $new_name\" width=80 height=15 back=\"L2UI_CT1.Button_DF_Down\" fore=\"L2UI_CT1.Button_DF\"></td></tr>";
        append += "</table>";
        show(append, player);
    }

    public void rename(String[] args) {
        Player player = getSelf();
        if (player == null) {
            return;
        }

        if (!ConfigServices.SERVICES_CHANGE_NICK_ENABLED) {
            show(new CustomMessage("scripts.services.servicedisabled", player), player);
            return;
        }

        if (args.length != 1) {
            show(new CustomMessage("scripts.services.Rename.incorrectinput", player), player);
            return;
        }

        if (player.getEvent(SiegeEvent.class) != null) {
            show(new CustomMessage("scripts.services.Rename.SiegeNow", player), player);
            return;
        }

        String name = args[0];

        if (!Util.isMatchingRegexp(name, ConfigServer.CNAME_TEMPLATE)) {
            show(new CustomMessage("scripts.services.Rename.incorrectinput", player), player);
            return;
        }

        if (getItemCount(player, ConfigServices.SERVICES_CHANGE_NICK_ITEM) < ConfigServices.SERVICES_CHANGE_NICK_PRICE) {
            if (ConfigServices.SERVICES_CHANGE_NICK_ITEM == 57)
                player.sendPacket(Msg.YOU_DO_NOT_HAVE_ENOUGH_ADENA);
            else
                player.sendPacket(SystemMsg.INCORRECT_ITEM_COUNT);
            return;
        }

        if (CharacterDAO.getInstance().getObjectIdByName(name) > 0) {
            show(new CustomMessage("scripts.services.Rename.Thisnamealreadyexists", player), player);
            return;
        }

        removeItem(player, ConfigServices.SERVICES_CHANGE_NICK_ITEM, ConfigServices.SERVICES_CHANGE_NICK_PRICE);

        String oldName = player.getName();
        player.reName(name, true);
        Log.add("Character " + oldName + " renamed to " + name, "renames");
        show(new CustomMessage("scripts.services.Rename.changedname", player).addString(oldName).addString(name), player);
    }
}

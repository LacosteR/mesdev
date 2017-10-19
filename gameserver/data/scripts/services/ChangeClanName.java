package services;

import l2p.gameserver.cache.Msg;
import l2p.gameserver.config.ConfigServer;
import l2p.gameserver.config.ConfigServices;
import l2p.gameserver.model.Player;
import l2p.gameserver.model.entity.events.impl.SiegeEvent;
import l2p.gameserver.model.pledge.Clan;
import l2p.gameserver.model.pledge.SubUnit;
import l2p.gameserver.network.serverpackets.SystemMessage;
import l2p.gameserver.network.serverpackets.components.CustomMessage;
import l2p.gameserver.network.serverpackets.components.SystemMsg;
import l2p.gameserver.scripts.Functions;
import l2p.gameserver.tables.ClanTable;
import l2p.gameserver.utils.Util;

/**
 * @author : Alice
 * @date : 25.11.16
 * @time : 5:29
 * <p/>
 * desc
 */
public class ChangeClanName extends Functions {
    public void rename_clan_page() {
        Player player = getSelf();
        if (player == null)
            return;

        if (player.getClan() == null || !player.isClanLeader()) {
            player.sendPacket(new SystemMessage(SystemMessage.S1_IS_NOT_A_CLAN_LEADER).addName(player));
            return;
        }

        String append = "!Rename clan";
        append += "<br>";
        append += "<font color=\"LEVEL\">" + new CustomMessage("scripts.services.Rename.RenameFor", getSelf()).addString(Util.formatAdena(ConfigServices.SERVICES_CHANGE_CLAN_NAME_PRICE)).addItemName(ConfigServices.SERVICES_CHANGE_CLAN_NAME_ITEM) + "</font>";
        append += "<table>";
        append += "<tr><td>" + new CustomMessage("scripts.services.Rename.NewName", getSelf()) + ": <edit var=\"new_name\" width=80></td></tr>";
        append += "<tr><td></td></tr>";
        append += "<tr><td><button value=\"" + new CustomMessage("scripts.services.Rename.RenameButton", getSelf()) + "\" action=\"bypass -h scripts_services.Rename:rename_clan $new_name\" width=80 height=15 back=\"L2UI_CT1.Button_DF_Down\" fore=\"L2UI_CT1.Button_DF\"></td></tr>";
        append += "</table>";
        show(append, player);
    }

    public void rename_clan(String[] param) {
        Player player = getSelf();
        if (player == null || param == null || param.length == 0)
            return;


        if (!ConfigServices.SERVICES_CHANGE_CLAN_NAME_ENABLED) {
            show(new CustomMessage("scripts.services.servicedisabled", player), player);
            return;
        }

        if (player.getClan() == null || !player.isClanLeader()) {
            player.sendPacket(new SystemMessage(SystemMessage.S1_IS_NOT_A_CLAN_LEADER).addName(player));
            return;
        }

        if (player.getEvent(SiegeEvent.class) != null) {
            show(new CustomMessage("scripts.services.Rename.SiegeNow", player), player);
            return;
        }

        if (!Util.isMatchingRegexp(param[0], ConfigServer.CLAN_NAME_TEMPLATE)) {
            player.sendPacket(Msg.CLAN_NAME_IS_INCORRECT);
            return;
        }
        if (ClanTable.getInstance().getClanByName(param[0]) != null) {
            player.sendPacket(Msg.THIS_NAME_ALREADY_EXISTS);
            return;
        }

        if (getItemCount(player, ConfigServices.SERVICES_CHANGE_CLAN_NAME_ITEM) < ConfigServices.SERVICES_CHANGE_CLAN_NAME_PRICE) {
            if (ConfigServices.SERVICES_CHANGE_CLAN_NAME_ITEM == 57)
                player.sendPacket(Msg.YOU_DO_NOT_HAVE_ENOUGH_ADENA);
            else
                player.sendPacket(SystemMsg.INCORRECT_ITEM_COUNT);
            return;
        }

        show(new CustomMessage("scripts.services.Rename.changedname", player).addString(player.getClan().getName()).addString(param[0]), player);
        SubUnit sub = player.getClan().getSubUnit(Clan.SUBUNIT_MAIN_CLAN);
        sub.setName(param[0], true);

        removeItem(player, ConfigServices.SERVICES_CHANGE_CLAN_NAME_ITEM, ConfigServices.SERVICES_CHANGE_CLAN_NAME_PRICE);
        player.getClan().broadcastClanStatus(true, true, false);
    }
}

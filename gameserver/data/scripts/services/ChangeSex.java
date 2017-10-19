package services;

import l2p.gameserver.cache.Msg;
import l2p.technical.dbutils.DbUtils;
import l2p.gameserver.config.ConfigServices;
import l2p.gameserver.database.DatabaseFactory;
import l2p.gameserver.model.Player;
import l2p.gameserver.model.base.Race;
import l2p.gameserver.network.serverpackets.components.CustomMessage;
import l2p.gameserver.network.serverpackets.components.SystemMsg;
import l2p.gameserver.scripts.Functions;
import l2p.gameserver.utils.Log;
import l2p.gameserver.utils.Util;

import java.sql.Connection;
import java.sql.PreparedStatement;

/**
 * @author : Alice
 * @date : 25.11.16
 * @time : 5:34
 * <p/>
 * desc
 */
public class ChangeSex extends Functions {

    public void changesex() {
        Player player = getSelf();
        if (player == null)
            return;

        if (player.getRace() == Race.kamael) {
            show("Not available for Kamael.", player);
            return;
        }

        if (!player.isInPeaceZone()) {
            show("You must be in peace zone to use this service.", player);
            return;
        }

        if (getItemCount(player, ConfigServices.SERVICES_CHANGE_SEX_ITEM) < ConfigServices.SERVICES_CHANGE_SEX_PRICE) {
            if (ConfigServices.SERVICES_CHANGE_SEX_ITEM == 57)
                player.sendPacket(Msg.YOU_DO_NOT_HAVE_ENOUGH_ADENA);
            else
                player.sendPacket(SystemMsg.INCORRECT_ITEM_COUNT);
            return;
        }

        Connection con = null;
        PreparedStatement offline = null;
        try {
            con = DatabaseFactory.getInstance().getConnection();
            offline = con.prepareStatement("UPDATE characters SET sex = ? WHERE obj_Id = ?");
            offline.setInt(1, player.getSex() == 1 ? 0 : 1);
            offline.setInt(2, player.getObjectId());
            offline.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            show(new CustomMessage("common.Error", player), player);
            return;
        } finally {
            DbUtils.closeQuietly(con, offline);
        }

        player.setHairColor(0);
        player.setHairStyle(0);
        player.setFace(0);
        removeItem(player, ConfigServices.SERVICES_CHANGE_SEX_ITEM, ConfigServices.SERVICES_CHANGE_SEX_PRICE);
        player.logout();
        Log.add("Character " + player + " sex changed to " + (player.getSex() == 1 ? "male" : "female"), "renames");
    }

    public void changesex_page() {
        Player player = getSelf();
        if (player == null)
            return;

        if (!player.isInPeaceZone()) {
            show("You must be in peace zone to use this service.", player);
            return;
        }

        if (!ConfigServices.SERVICES_CHANGE_SEX_ENABLED) {
            show("Service disabled.", player);
            return;
        }

        String append = "Sex changing";
        append += "<br>";
        append += "<font color=\"LEVEL\">" + new CustomMessage("scripts.services.SexChange.SexChangeFor", player).addString(Util.formatAdena(ConfigServices.SERVICES_CHANGE_SEX_PRICE)).addItemName(ConfigServices.SERVICES_CHANGE_SEX_ITEM) + "</font>";
        append += "<table>";
        append += "<tr><td><button value=\"" + new CustomMessage("scripts.services.SexChange.Button", player) + "\" action=\"bypass -h scripts_services.Rename:changesex\" width=80 height=15 back=\"L2UI_CT1.Button_DF_Down\" fore=\"L2UI_CT1.Button_DF\"></td></tr>";
        append += "</table>";
        show(append, player);
    }
}

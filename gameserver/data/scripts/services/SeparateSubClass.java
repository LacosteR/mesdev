package services;

import l2p.gameserver.config.ConfigServices;
import l2p.gameserver.data.htm.HtmCache;
import l2p.gameserver.data.retail.common.ClassID;
import l2p.gameserver.database.mysql;
import l2p.gameserver.model.Player;
import l2p.gameserver.model.SubClass;
import l2p.gameserver.model.instances.NpcInstance;
import l2p.gameserver.network.serverpackets.NpcHtmlMessage;
import l2p.gameserver.network.serverpackets.components.SystemMsg;
import l2p.gameserver.scripts.Functions;
import l2p.gameserver.templates.item.ItemTemplate;
import l2p.gameserver.utils.ItemFunctions;
import l2p.gameserver.utils.Log;

import java.util.Map;

/**
 * @author : Alice
 * @date : 25.11.16
 * @time : 5:44
 * <p/>
 * desc
 */
public class SeparateSubClass extends Functions {
    public void separate_page(Player player, NpcInstance npc, String[] arg) {
        if (!ConfigServices.SERVICES_SEPARATE_SUB_ENABLED) {
            player.sendPacket(new NpcHtmlMessage(5).setFile("scripts/services/service_disabled.htm"));
            return;
        }

        if (player.getSubClasses().size() == 1) {
            player.sendPacket(new NpcHtmlMessage(5).setFile("scripts/services/subclass_separate_err01.htm"));
            return;
        }

        NpcHtmlMessage msg = new NpcHtmlMessage(5).setFile("scripts/services/subclass_separate.htm");
        msg.replace("%item_id%", String.valueOf(ConfigServices.SERVICES_SEPARATE_SUB_ITEM));
        msg.replace("%item_count%", String.valueOf(ConfigServices.SERVICES_SEPARATE_SUB_PRICE));

        String item = HtmCache.getInstance().getNotNull("scripts/services/subclass_separate_list.htm", player);

        StringBuilder sb = new StringBuilder();
        for (SubClass s : player.getSubClasses().values())
            if (!s.isBase() && s.getClassId() != ClassID.inspector.getClassId() && s.getClassId() != ClassID.judicator.getClassId())
                sb.append(item.replace("%class_id%", String.valueOf(s.getClassId())));

        msg.replace("%list%", sb.toString());

        player.sendPacket(msg);
    }


    public void separate(Player player, NpcInstance npc, String[] arg) {
        if (!ConfigServices.SERVICES_SEPARATE_SUB_ENABLED) {
            player.sendPacket(new NpcHtmlMessage(5).setFile("scripts/services/service_disabled.htm"));
            return;
        }

        if (arg == null || arg.length < 2)
            return;

        if (player.getSubClasses().size() == 1) {
            player.sendPacket(new NpcHtmlMessage(5).setFile("scripts/services/subclass_separate_err01.htm"));
            return;
        }

        if (!player.getActiveClass().isBase()) {
            player.sendPacket(new NpcHtmlMessage(5).setFile("scripts/services/subclass_separate_err03.htm"));
            return;
        }

        if (player.getActiveClass().getLevel() < 75) {
            player.sendPacket(new NpcHtmlMessage(5).setFile("scripts/services/subclass_separate_err04.htm"));
            return;
        }

        if (player.isHero()) {
            player.sendPacket(new NpcHtmlMessage(5).setFile("scripts/services/subclass_separate_err05.htm"));
            return;
        }

        int classtomove = Integer.parseInt(arg[0]);
        int newcharid = 0;
        for (Map.Entry<Integer, String> entry : player.getAccountChars().entrySet()) {
            if (entry.getValue().equalsIgnoreCase(arg[1])) {
                newcharid = entry.getKey();
            }
        }

        if (newcharid == 0) {
            player.sendPacket(new NpcHtmlMessage(5).setFile("scripts/services/subclass_separate_err06.htm"));
            return;
        }

        if (classtomove == ClassID.inspector.getClassId() || classtomove == ClassID.judicator.getClassId()) {
            player.sendPacket(new NpcHtmlMessage(5).setFile("scripts/services/subclass_separate_err02.htm"));
            return;
        }

        if (mysql.simple_get_int("level", "character_subclasses", "char_obj_id=" + newcharid + " AND level > 1") > 1) {
            player.sendPacket(new NpcHtmlMessage(5).setFile("scripts/services/subclass_separate_err07.htm"));
            return;
        }

        if (!player.isInPeaceZone() || !player.getPositionComponent().getReflection().isDefault()) {
            player.sendPacket(new NpcHtmlMessage(5).setFile("scripts/services/subclass_separate_err08.htm"));
            return;
        }

        if (ItemFunctions.getItemCount(player, ConfigServices.SERVICES_SEPARATE_SUB_ITEM) < ConfigServices.SERVICES_SEPARATE_SUB_PRICE) {
            if (ConfigServices.SERVICES_SEPARATE_SUB_ITEM == ItemTemplate.ITEM_ID_ADENA)
                player.sendPacket(SystemMsg.YOU_DO_NOT_HAVE_ENOUGH_ADENA);
            else
                player.sendPacket(SystemMsg.INCORRECT_ITEM_COUNT);
            return;
        }

        ItemFunctions.deleteItem(player, ConfigServices.SERVICES_SEPARATE_SUB_ITEM, ConfigServices.SERVICES_SEPARATE_SUB_PRICE);

        mysql.set("DELETE FROM character_subclasses WHERE char_obj_id=" + newcharid);
        mysql.set("DELETE FROM character_skills WHERE char_obj_id=" + newcharid);
        mysql.set("DELETE FROM character_skills_save WHERE char_obj_id=" + newcharid);
        mysql.set("DELETE FROM character_effects_save WHERE object_id=" + newcharid);
        mysql.set("DELETE FROM character_hennas WHERE char_obj_id=" + newcharid);
        mysql.set("DELETE FROM character_shortcuts WHERE object_id=" + newcharid);
        mysql.set("DELETE FROM character_variables WHERE obj_id=" + newcharid);

        mysql.set("UPDATE character_subclasses SET char_obj_id=" + newcharid + ", isBase=1, certification=0 WHERE char_obj_id=" + player.getObjectId() + " AND class_id=" + classtomove);
        mysql.set("UPDATE character_skills SET char_obj_id=" + newcharid + " WHERE char_obj_id=" + player.getObjectId() + " AND class_index=" + classtomove);
        mysql.set("UPDATE character_skills_save SET char_obj_id=" + newcharid + " WHERE char_obj_id=" + player.getObjectId() + " AND class_index=" + classtomove);
        mysql.set("UPDATE character_effects_save SET object_id=" + newcharid + " WHERE object_id=" + player.getObjectId() + " AND id=" + classtomove);
        mysql.set("UPDATE character_hennas SET char_obj_id=" + newcharid + " WHERE char_obj_id=" + player.getObjectId() + " AND class_index=" + classtomove);
        mysql.set("UPDATE character_shortcuts SET object_id=" + newcharid + " WHERE object_id=" + player.getObjectId() + " AND class_index=" + classtomove);

        mysql.set("UPDATE character_variables SET obj_id=" + newcharid + " WHERE obj_id=" + player.getObjectId() + " AND name like 'TransferSkills%'");

        player.modifySubClass(classtomove, 0);

        player.logout();

        Log.add("Character " + player + " subclass separated to " + arg[1], "services");
    }
}

package services;

import l2p.gameserver.config.ConfigServices;
import l2p.gameserver.data.htm.HtmCache;
import l2p.gameserver.model.Player;
import l2p.gameserver.model.SubClass;
import l2p.gameserver.model.base.PlayerClass;
import l2p.gameserver.model.entity.olympiad.Olympiad;
import l2p.gameserver.network.serverpackets.NpcHtmlMessage;
import l2p.gameserver.network.serverpackets.components.SystemMsg;
import l2p.gameserver.scripts.Functions;
import l2p.gameserver.templates.item.ItemTemplate;
import l2p.gameserver.utils.ItemFunctions;
import l2p.gameserver.utils.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * @author : Alice
 * @date : 25.11.16
 * @time : 5:36
 * <p/>
 * desc
 */
public class ChangeBaseClass extends Functions {
    public void changebase_page() {
        Player player = getSelf();
        if (player == null) {
            return;
        }

        if (!ConfigServices.SERVICES_CHANGE_BASE_ENABLED) {
            player.sendPacket(new NpcHtmlMessage(5).setFile("scripts/services/service_disabled.htm"));
            return;
        }

        if (player.getSubClasses().size() == 1) {
            player.sendPacket(new NpcHtmlMessage(5).setFile("scripts/services/changebase_err01.htm"));
            return;
        }

        if (!player.getActiveClass().isBase()) {
            player.sendPacket(new NpcHtmlMessage(5).setFile("scripts/services/changebase_err02.htm"));
            return;
        }

        NpcHtmlMessage msg = new NpcHtmlMessage(5).setFile("scripts/services/changebase.htm");
        msg.replace("%item_id%", String.valueOf(ConfigServices.SERVICES_CHANGE_BASE_ITEM));
        msg.replace("%item_count%", String.valueOf(ConfigServices.SERVICES_CHANGE_BASE_PRICE));

        List<SubClass> possible = new ArrayList<SubClass>();
        if (player.getActiveClass().isBase()) {
            possible.addAll(player.getSubClasses().values());
            possible.remove(player.getSubClasses().get(player.getBaseClassId()));

            for (SubClass s : player.getSubClasses().values())
                for (SubClass s2 : player.getSubClasses().values())
                    if (s != s2 && !PlayerClass.areClassesComportable(PlayerClass.values()[s.getClassId()], PlayerClass.values()[s2.getClassId()]) || s2.getLevel() < 75)
                        possible.remove(s2);
        }

        StringBuilder sb = new StringBuilder();
        if (!possible.isEmpty()) {
            String item = HtmCache.getInstance().getNotNull("scripts/services/changebase_list.htm", player);
            for (SubClass s : possible)
                sb.append(item.replace("%class_id%", String.valueOf(s.getClassId())));
        }

        msg.replace("%list%", sb.toString());

        player.sendPacket(msg);
    }

    public void changebase(String[] arg) {
        Player player = getSelf();
        if (player == null) {
            return;
        }

        if (arg == null || arg.length < 1)
            return;

        if (!ConfigServices.SERVICES_CHANGE_BASE_ENABLED) {
            player.sendPacket(new NpcHtmlMessage(5).setFile("scripts/services/service_disabled.htm"));
            return;
        }

        if (player.getSubClasses().size() == 1) {
            player.sendPacket(new NpcHtmlMessage(5).setFile("scripts/services/changebase_err01.htm"));
            return;
        }

        if (!player.getActiveClass().isBase()) {
            player.sendPacket(new NpcHtmlMessage(5).setFile("scripts/services/changebase_err02.htm"));
            return;
        }

        if (!player.isInPeaceZone() || !player.getPositionComponent().getReflection().isDefault()) {
            player.sendPacket(new NpcHtmlMessage(5).setFile("scripts/services/changebase_err03.htm"));
            return;
        }

        if (player.isHero()) {
            player.sendPacket(new NpcHtmlMessage(5).setFile("scripts/services/changebase_err04.htm"));
            return;
        }

        if (ItemFunctions.getItemCount(player, ConfigServices.SERVICES_CHANGE_BASE_ITEM) < ConfigServices.SERVICES_CHANGE_BASE_PRICE) {
            if (ConfigServices.SERVICES_CHANGE_BASE_ITEM == ItemTemplate.ITEM_ID_ADENA)
                player.sendPacket(SystemMsg.YOU_DO_NOT_HAVE_ENOUGH_ADENA);
            else
                player.sendPacket(SystemMsg.INCORRECT_ITEM_COUNT);
            return;
        }

        ItemFunctions.deleteItem(player, ConfigServices.SERVICES_CHANGE_BASE_ITEM, ConfigServices.SERVICES_CHANGE_BASE_PRICE);

        int target = Integer.parseInt(arg[0]);
        SubClass newBase = player.getSubClasses().get(target);

        player.getActiveClass().setBase(false);
        player.getActiveClass().setCertification(newBase.getCertification());

        newBase.setCertification(0);
        player.getActiveClass().setExp(player.getExp());
        player.checkSkills();

        newBase.setBase(true);

        player.setBaseClass(target);

        player.setHairColor(0);
        player.setHairStyle(0);
        player.setFace(0);

        Olympiad.unRegisterNoble(player);

        player.logout();

        Log.add("Character " + player + " base changed to " + target, "services");
    }
}

package services;

import l2p.gameserver.config.ConfigServices;
import l2p.gameserver.data.xml.holder.ItemHolder;
import l2p.gameserver.model.Player;
import l2p.gameserver.model.base.Race;
import l2p.gameserver.mods.Merchant;
import l2p.gameserver.scripts.Functions;

public class RaceChange extends Functions {
    public void list() {
        Player player = getSelf();
        if (player == null)
            return;

        StringBuilder append = new StringBuilder();
        append.append("You can change your race for small price ").append(ConfigServices.RACE_CHANGE_PAY_COUNT).append(" ").append(ItemHolder.getInstance().getTemplate(ConfigServices.RACE_CHANGE_PAY_ITEM_ID).getName()).append(".");
        append.append("<br>Possible races:<br>");
        if (player.getRace() == Race.kamael)
            append.append("You are Kamael. Be proud of it.");
        else {
            //append.append("<br><a action=\"bypass -h scripts_services.RaceChange:change ").append(0).append("\"><font color=\"").append("FF6600").append("\">").append("Human").append("</font></a>");  // human
            append.append("<br><a action=\"bypass -h scripts_services.RaceChange:change ").append(1).append("\"><font color=\"").append("009900").append("\">").append("Elf").append("</font></a>");  // elf
            append.append("<br><a action=\"bypass -h scripts_services.RaceChange:change ").append(2).append("\"><font color=\"").append("0033CC").append("\">").append("Dark Elf").append("</font></a>");  // de
            //append.append("<br><a action=\"bypass -h scripts_services.RaceChange:change ").append(3).append("\"><font color=\"").append("FF3300").append("\">").append("Ork").append("</font></a>");  // ork
            append.append("<br><a action=\"bypass -h scripts_services.RaceChange:change ").append(4).append("\"><font color=\"").append("663300").append("\">").append("Dwarf").append("</font></a>");  // dwarf
            append.append("<br><a action=\"bypass -h scripts_services.RaceChange:change ").append(5).append("\"><font color=\"").append("663300").append("\">").append("Kamael").append("</font></a>");  // Kamael
        }
        append.append("<br><a action=\"bypass -h scripts_services.RaceChange:change ").append("revert").append("\"><font color=\"FFFFFF\">Revert to default (free)</font></a>");

        show(append.toString(), player, null);
    }

    public void change(String[] param) {
        Player player = getSelf();
        if (player == null)
            return;

        if (param[0].equalsIgnoreCase("revert")) {
            player.unsetVar("race");
            player.broadcastUserInfo(true);
            return;
        }

        if (Merchant.getPay(player, ConfigServices.RACE_CHANGE_PAY_ITEM_ID, ConfigServices.RACE_CHANGE_PAY_COUNT)) {
            player.setVar("race", param[0], -1);
            player.broadcastUserInfo(true);
        }
    }
}
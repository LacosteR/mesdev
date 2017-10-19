package services;

import l2p.gameserver.config.ConfigServices;
import l2p.gameserver.dao.AccountDAO;
import l2p.gameserver.dao.CharacterDAO;
import l2p.gameserver.data.xml.holder.ItemHolder;
import l2p.gameserver.model.Player;
import l2p.gameserver.mods.Merchant;
import l2p.gameserver.scripts.Functions;
import l2p.gameserver.utils.Htm;
import l2p.gameserver.utils.ItemFunctions;

/**
 * Created with IntelliJ IDEA.
 * User: Vitalay
 * Date: 01.11.13
 * Time: 9:52
 * To change this template use File | Settings | File Templates.
 */
public class CharacterTransfer extends Functions {
    public void charToAccPage() {
        Player player = getSelf();
        if (player == null) {
            return;
        }

        StringBuilder sb = new StringBuilder();
        sb.append("<font color=LEVEL>Перенос персонажа на другой аккаунт</font>");
        sb.append("<br>");
        sb.append("<font color=3293F3>С помощью данного сервиса вы можете мгновенно перенести персонажа на другой аккаунт</font>");
        sb.append("<br>");
        sb.append("Аккаунт куда хотите перенести персонажа:");
        sb.append("<edit var=\"acc\" width=150>");
        sb.append("<br>");
        sb.append(Htm.button("Перенести за " + ConfigServices.ACC_CHANGE_PAY_COUNT + " " + ItemHolder.getInstance().getTemplate(ConfigServices.ACC_CHANGE_PAY_ITEM_ID).getName(), "-h scripts_services.CharacterTransfer:charToAcc $acc", 150, 26));
        sb.append("<br>");
        show(sb.toString(), player);
    }

    public void charToAcc(String[] args) {
        Player player = getSelf();
        if (player == null) {
            System.out.println("CharacterTransfer: player == null");
            return;
        }
        if (!AccountDAO.getInstance().check(args[0])) {
            player.sendMessage("Такой аккаунт не существует");
            return;
        }
        if (CharacterDAO.getInstance().accountCharNumber(args[0]) >= 8) {
            player.sendMessage("Выбранный аккаунт переполнен");
            return;
        }
        if (Merchant.getPay(player, ConfigServices.ACC_CHANGE_PAY_ITEM_ID, ConfigServices.ACC_CHANGE_PAY_COUNT)) {
            int objId = player.getObjectId();
            player.kick();
            CharacterDAO.getInstance().setAccount(args[0], objId);
        } else
            player.sendMessage("Вам не хватает " + ConfigServices.ACC_CHANGE_PAY_COUNT + " " + ItemFunctions.getItemName(ConfigServices.ACC_CHANGE_PAY_ITEM_ID));
    }
}
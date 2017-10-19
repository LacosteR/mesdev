package services;

import l2p.gameserver.model.Player;
import l2p.gameserver.model.items.ItemInstance;
import l2p.gameserver.model.items.PcInventory;
import l2p.gameserver.mods.Merchant;
import l2p.gameserver.scripts.Functions;

public class Exchanger extends Functions {
    public void list() {
        Player player = getSelf();
        if (player == null)
            return;

        StringBuilder append = new StringBuilder();
        append.append("Вы можете обменять ресурсы, части и рецепты на адену.");
        append.append("<br>Выбор:<br>");

        append.append("<br><a action=\"bypass -h scripts_services.Exchanger:exchange keymaterials\"><font color=\"FFFFFF\">Обменять куски</font></a>");
        append.append("<br><a action=\"bypass -h scripts_services.Exchanger:exchange recipes\"><font color=\"FFFFFF\">Обменять рецепты</font></a>");

        show(append.toString(), player, null);
    }

    public void exchange(String[] param) {
        byte type = 0;

        if (param[0].equalsIgnoreCase("keymaterials"))
            type = 1;
        else if (param[0].equalsIgnoreCase("recipes"))
            type = 2;

        trade(type);
    }

    private void trade(byte type) {
        Player player = getSelf();
        if (player == null || type == 0)
            return;

        PcInventory inventory = player.getInventory();
        inventory.writeLock();
        try {
            for (ItemInstance item : inventory.getItems()) {
                if (type == 1 && item.getTemplate().isKeyMatherial())
                    Merchant.tradeItem(player, item.getItemId(), item.getCount(), 57, (long) (item.getTemplate().getReferencePrice() / 4));
                else if (type == 2 && item.getTemplate().isRecipe())
                    Merchant.tradeItem(player, item.getItemId(), item.getCount(), 57, (long) (item.getTemplate().getReferencePrice() / 4));
            }
        } finally {
            inventory.writeUnlock();
            player.updateStats();
        }
    }
}
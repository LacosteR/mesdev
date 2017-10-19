package handler.items;

import l2p.gameserver.model.Playable;
import l2p.gameserver.model.Player;
import l2p.gameserver.model.items.ItemInstance;
import l2p.gameserver.network.serverpackets.MagicSkillUse;

public class CharChangePotions extends ScriptItemHandler {
    private static final int[] _itemIds = {5235, 5236, 5237, // Face
            5238,
            5239,
            5240,
            5241, // Hair Colors
            5242,
            5243,
            5244,
            5245,
            5246,
            5247,
            5248 // Hair Style
        /*70500,
        70501,
        70502,
        70503,
        70504,
        70505,
        70506,
        70507, // New Hair Style
        70508,
        70509,
        70510,
        70511,
        70512,
        70513     */
    };

    @Override
    public boolean useItem(Playable playable, ItemInstance item, boolean ctrl) {
        if (playable == null || !playable.isPlayer())
            return false;
        Player player = (Player) playable;

        int itemId = item.getItemId();

        if (!player.getInventory().destroyItem(item, 1)) {
            player.sendActionFailed();
            return false;
        }

        switch (itemId) {
            case 5235:
                player.setFace(0);
                break;
            case 5236:
                player.setFace(1);
                break;
            case 5237:
                player.setFace(2);
                break;
            case 70508:
                player.setFace(3);
                break;
            case 70509:
                player.setFace(4);
                break;
            case 70510:
                player.setFace(5);
                break;
            case 70511:
                player.setFace(6);
                break;
            case 70512:
                player.setFace(7);
                break;
            case 70513:
                player.setFace(8);
                break;
            case 5238:
                player.setHairColor(0);
                break;
            case 5239:
                player.setHairColor(1);
                break;
            case 5240:
                player.setHairColor(2);
                break;
            case 5241:
                player.setHairColor(3);
                break;
            case 5242:
                player.setHairStyle(0);
                break;
            case 5243:
                player.setHairStyle(1);
                break;
            case 5244:
                player.setHairStyle(2);
                break;
            case 5245:
                player.setHairStyle(3);
                break;
            case 5246:
                player.setHairStyle(4);
                break;
            case 5247:
                if (player.getSex() == 0)
                    player.setHairColor(0);
                player.setHairStyle(5);
                break;
            case 5248:
                if (player.getSex() == 0)
                    player.setHairColor(0);
                player.setHairStyle(6);
                break;
            case 70500:
                player.setHairColor(0);
                player.setHairStyle(7);
                break;
            case 70501:
                player.setHairColor(0);
                player.setHairStyle(8);
                break;
            case 70502:
                player.setHairColor(0);
                player.setHairStyle(9);
                break;
            case 70503:
                player.setHairColor(0);
                player.setHairStyle(10);
                break;
            case 70504:
                player.setHairColor(0);
                player.setHairStyle(11);
                break;
            case 70505:
                player.setHairColor(0);
                player.setHairStyle(12);
                break;
            case 70506:
                player.setHairColor(0);
                player.setHairStyle(13);
                break;
            case 70507:
                player.setHairColor(0);
                player.setHairStyle(14);
                break;
        }

        player.broadcastPacket(new MagicSkillUse(player, player, 2003, 1, 1, 0));
        player.broadcastCharInfo();
        return true;
    }

    @Override
    public final int[] getItemIds() {
        return _itemIds;
    }
}
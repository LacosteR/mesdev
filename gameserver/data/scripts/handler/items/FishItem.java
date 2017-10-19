package handler.items;

import l2p.dataparser.nasc_loader.pch.LinkerFactory;
import l2p.gameserver.cache.Msg;
import l2p.gameserver.config.ConfigRates;
import l2p.gameserver.data.retail.holder.FishingDataHolder;
import l2p.gameserver.data.retail.holder.ItemDataHolder;
import l2p.gameserver.data.retail.holder.SkillDataHolder;
import l2p.gameserver.data.retail.holder.fishingdata.Fish;
import l2p.gameserver.data.retail.holder.itemdata.ItemData;
import l2p.gameserver.data.retail.holder.optiondata.common.Item;
import l2p.gameserver.data.retail.holder.skilldata.Effect;
import l2p.gameserver.data.retail.holder.skilldata.SkillData;
import l2p.gameserver.data.retail.holder.skilldata.effects.RestorationRandom;
import l2p.gameserver.model.Playable;
import l2p.gameserver.model.Player;
import l2p.gameserver.model.items.ItemInstance;
import l2p.gameserver.network.serverpackets.components.IStaticPacket;
import l2p.gameserver.network.serverpackets.components.SystemMsg;
import l2p.gameserver.utils.ItemFunctions;
import l2p.technical.util.Rnd;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.LinkedList;

public class FishItem extends ScriptItemHandler {
    private static final Logger _log = LoggerFactory.getLogger(FishItem.class);

    @Override
    public boolean useItem(Playable playable, ItemInstance item, boolean ctrl) {
        if (playable == null || !playable.isPlayer())
            return false;
        Player player = (Player) playable;

        if (player.getWeightPenalty() >= 3 || player.getInventory().getSize() > player.getInventoryLimit() - 10) {
            player.sendPacket(Msg.YOUR_INVENTORY_IS_FULL);
            return false;
        }

        if (!player.getInventory().destroyItem(item, 1L)) {
            player.sendActionFailed();
            return false;
        }
        String fish_name = null;
        for(Fish fish : FishingDataHolder.getInstance().fishes) {
            if(fish.item_id == item.getItemId()) {
                fish_name = fish.item_name;
                break;
            }
        }

        ItemData item_data = ItemDataHolder.getInstance().getItemData(fish_name);
        String skill_id = LinkerFactory.getInstance().findValueFor(item_data.item_skill);
        if(skill_id == null) {
            _log.error("Can't find skill for fish {}", fish_name);
            return false;
        }
        int cr_id = 0;
        try {
            cr_id = Integer.parseInt(skill_id);
        } catch (NumberFormatException e) {
            _log.error("Can't find skill for fish {} and item {}", fish_name, item.getName());
            return false;
        }

        SkillData skill_data = SkillDataHolder.getInstance().getSkill(cr_id);
        if(skill_data == null) {
            _log.error("Can't find skill_data for fish {}", fish_name);
            return false;
        }

        IStaticPacket sending_message = null;
        for(Effect effect : skill_data.getEffect()) {
            if(!(effect instanceof RestorationRandom)) {
                continue;
            }

            for(RestorationRandom.RestorationGroup restoration_group : ((RestorationRandom) effect).getGroupList()) {
                if(!Rnd.chance(restoration_group.getChance())) {
                    continue;
                }
                for(Item item_r : restoration_group.getItemList()) {
                    ItemFunctions.addItem(player, ItemDataHolder.getInstance().getItemData(item_r.getItemName()).itemId, (long)(item_r.getItemCount() * ConfigRates.RATE_FISH_DROP_COUNT), true);
                    sending_message = SystemMsg.THERE_WAS_NOTHING_FOUND_INSIDE;
                }
            }
        }

        if (sending_message != null)
            player.sendPacket(sending_message);
        return true;
    }

    @Override
    public int[] getItemIds() {
        LinkedList<Integer> list = new LinkedList<Integer>();
        for(Fish fish : FishingDataHolder.getInstance().fishes) {
            if(!list.contains(fish.item_id)) {
                list.add(fish.item_id);
            }
        }
        int[] array = new int[list.size()];
        for(int i = 0; i < array.length; i++) {
            array[i] = list.get(i);
        }

        return array;
    }
}
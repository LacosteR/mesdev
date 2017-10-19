package handler.items;

import gnu.trove.set.hash.TIntHashSet;
import l2p.technical.util.Rnd;
import l2p.gameserver.data.xml.holder.ItemHolder;
import l2p.gameserver.model.Player;
import l2p.gameserver.model.items.ItemInstance;
import l2p.gameserver.templates.item.ItemTemplate;
import l2p.gameserver.utils.ItemFunctions;

import java.util.List;

/**
 * @author Bonux
 */
public class ExtractableItem extends SimpleItemHandler {
    private int[] _itemIds;

    public ExtractableItem() {
        TIntHashSet set = new TIntHashSet();
        for (ItemTemplate template : ItemHolder.getInstance().getAllTemplates()) {
            if (template == null)
                continue;
            if (template.isExtractable())
                set.add(template.getItemId());
        }
        _itemIds = set.toArray();
    }

    @Override
    protected boolean useItemImpl(Player player, ItemInstance item, boolean ctrl) {
        int itemId = item.getItemId();

        if (!canBeExtracted(player, item))
            return false;

        if (!tryUseItem(player, item, 1, false))
            return false;

        List<ItemTemplate.CapsuledItem> capsuled_items = item.getTemplate().getCapsuledItems();
        for (ItemTemplate.CapsuledItem ci : capsuled_items) {
            if (Rnd.chance(ci.getChance()))
                ItemFunctions.addItem(player, ci.getItemId(), Rnd.get(ci.getMinCount(), ci.getMaxCount()), true);
        }

        List<ItemTemplate.CapsuledItem> capsuled_grouped_items = item.getTemplate().getRandomCapsuledGroupedItems();
        for (ItemTemplate.CapsuledItem ci : capsuled_grouped_items) {
            ItemFunctions.addItem(player, ci.getItemId(), Rnd.get(ci.getMinCount(), ci.getMaxCount()), true);
        }

        return true;
    }

    @Override
    public int[] getItemIds() {
        return _itemIds;
    }
}

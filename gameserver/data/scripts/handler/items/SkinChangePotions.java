package handler.items;

import l2p.gameserver.model.Playable;
import l2p.gameserver.model.items.ItemInstance;
import l2p.gameserver.mods.skins.SkinsHandler;

public class SkinChangePotions extends ScriptItemHandler {
    private static final int[] _itemIds = SkinsHandler.getInstance().getArraySkinChangeItemIDs();

    @Override
    public boolean useItem(Playable playable, ItemInstance item, boolean ctrl) {
        return SkinsHandler.getInstance().handleUseSkinBottle(playable, item);
    }

    @Override
    public final int[] getItemIds() {
        return _itemIds;
    }
}
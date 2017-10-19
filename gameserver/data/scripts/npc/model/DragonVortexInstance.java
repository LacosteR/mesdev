package npc.model;

import l2p.gameserver.model.Player;
import l2p.gameserver.model.instances.NpcInstance;
import l2p.gameserver.templates.npc.NpcTemplate;
import l2p.gameserver.utils.ItemFunctions;
import l2p.gameserver.utils.Location;
import l2p.gameserver.utils.NpcUtils;
import l2p.technical.util.Rnd;

import java.util.ArrayList;
import java.util.List;

public final class DragonVortexInstance extends NpcInstance {
    private final int[] bosses = {25718, 25719, 25720, 25721, 25722, 25723, 25724};
    private NpcInstance boss;

    private List<NpcInstance> bosses_list = new ArrayList<NpcInstance>();
    private List<NpcInstance> temp_list = new ArrayList<NpcInstance>();

    public DragonVortexInstance(int objectId, NpcTemplate template) {
        super(objectId, template);
    }

    @Override
    public void onBypassFeedback(Player player, String command) {
        if (!canBypassCheck(player, this))
            return;

        if (command.startsWith("request_boss")) {

            if (ItemFunctions.getItemCount(player, 17248) > 0) {
                if (validateBosses()) {
                    ItemFunctions.removeItem(player, 17248, 1, true);
                    boss = NpcUtils.spawnSingle(bosses[Rnd.get(bosses.length)], Location.coordsRandomize(getPositionComponent().getLoc(), 300, 600), getPositionComponent().getReflection());
                    bosses_list.add(boss);
                    showChatWindow(player, "default/32871-1.htm");
                } else
                    showChatWindow(player, "default/32871-3.htm");
            } else
                showChatWindow(player, "default/32871-2.htm");
        } else
            super.onBypassFeedback(player, command);
    }

    private boolean validateBosses() {
        if (bosses_list == null || bosses_list.isEmpty())
            return true;

        temp_list.addAll(bosses_list);

        for (NpcInstance npc : temp_list) {
            if (npc == null || npc.isDead())
                bosses_list.remove(npc);
        }

        temp_list.clear();

        if (bosses_list.size() >= 15)
            return false;

        return true;
    }
}
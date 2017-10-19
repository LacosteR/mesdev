package npc.model.residences.castle;

import l2p.gameserver.model.Player;
import l2p.gameserver.model.entity.residence.Castle;
import l2p.gameserver.model.instances.NpcInstance;
import l2p.gameserver.templates.npc.NpcTemplate;

public class VenomTeleporterInstance extends NpcInstance {
    private static final long serialVersionUID = -1716591883554340489L;

    public VenomTeleporterInstance(int objectId, NpcTemplate template) {
        super(objectId, template);
    }

    @Override
    public void onBypassFeedback(Player player, String command) {
        Castle castle = getCastle();
        if (castle.getSiegeEvent().isInProgress())
            showChatWindow(player, "residence2/castle/rune_massymore_teleporter002.htm");
        else if (!checkForDominionWard(player))
            player.teleToLocation(12589, -49044, -3008);
    }

    @Override
    public void showChatWindow(Player player, int val, Object... arg) {
        showChatWindow(player, "residence2/castle/rune_massymore_teleporter001.htm");
    }
}
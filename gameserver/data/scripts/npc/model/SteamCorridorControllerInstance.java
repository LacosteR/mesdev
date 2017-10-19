package npc.model;

import instances.CrystalCaverns;
import l2p.gameserver.model.Player;
import l2p.gameserver.model.instances.NpcInstance;
import l2p.gameserver.templates.npc.NpcTemplate;

public class SteamCorridorControllerInstance extends NpcInstance {
    public SteamCorridorControllerInstance(int objectId, NpcTemplate template) {
        super(objectId, template);
    }

    @Override
    public void onBypassFeedback(Player player, String command) {
        if (!canBypassCheck(player, this))
            return;

        if (command.equalsIgnoreCase("move_next")) {
            if (getPositionComponent().getReflection().getInstancedZoneId() == 10)
                ((CrystalCaverns) getPositionComponent().getReflection()).notifyNextLevel(this);
        } else
            super.onBypassFeedback(player, command);
    }
}

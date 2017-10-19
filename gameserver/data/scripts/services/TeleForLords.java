package services;

import l2p.gameserver.model.Player;
import l2p.gameserver.model.instances.NpcInstance;
import l2p.gameserver.scripts.Functions;

/**
 * Author: Bonux
 */
public class TeleForLords extends Functions {
    public void tele() {
        Player player = getSelf();
        NpcInstance npc = getNpc();

        if (player == null || npc == null)
            return;

        if (player.getQuestState("_11000_PathToTheMysteriousLord") == null || player.getQuestState("_11000_PathToTheMysteriousLord").getState() != 3) // if quest NOT COMPLETED
            show("teleporter/" + npc.getNpcId() + "-4.htm", player);
        else
            player.teleToLocation(-149406, 255247, -80);
    }
}

package npc.model;

import l2p.gameserver.model.Player;
import l2p.gameserver.model.instances.NpcInstance;
import l2p.gameserver.network.serverpackets.components.ChatType;
import l2p.gameserver.scripts.Functions;
import l2p.gameserver.templates.npc.NpcTemplate;

/**
 * @author : Alice
 * @date : 26.11.16
 * @time : 7:35
 * <p/>
 * desc
 */
public class EventJackpotBugInstance extends NpcInstance {
    public EventJackpotBugInstance(int objectId, NpcTemplate template) {
        super(objectId, template);
    }

    public void showChatWindow(Player player, int val) {
        if (val == 0)
            showChatWindow(player, "data/html/default/luckpi_001.htm");
        else if (val == 1) {
            if (i_ai0 >= 3) {
                Functions.npcSay(this, ChatType.NPC_ALL, 1900145);
                onDecay();
                doDie(null);
            } else
                showChatWindow(player, "data/html/default/luckpi_003.htm");
        } else if (val == 2)
            showChatWindow(player, "data/html/default/luckpi_002.htm");
    }

}
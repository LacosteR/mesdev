package npc.model;

import l2p.gameserver.model.Player;
import l2p.gameserver.model.instances.MerchantInstance;
import l2p.gameserver.network.serverpackets.NpcHtmlMessage;
import l2p.gameserver.templates.npc.NpcTemplate;


public final class ClassMasterInstance extends MerchantInstance {
    public ClassMasterInstance(int objectId, NpcTemplate template) {
        super(objectId, template);
    }

    private String makeMessage(Player player) {
        StringBuilder html = new StringBuilder();
        html.append("There is no class changes for you any more.");
        return html.toString();
    }

    @Override
    public void showChatWindow(Player player, int val, Object... arg) {
        NpcHtmlMessage msg = new NpcHtmlMessage(player, this);
        msg.setFile("custom/31860.htm");
        msg.replace("%classmaster%", makeMessage(player));
        player.sendPacket(msg);
    }

    @Override
    public void onBypassFeedback(Player player, String command) {
        if (!canBypassCheck(player, this))
            return;
        super.onBypassFeedback(player, command);
    }
}
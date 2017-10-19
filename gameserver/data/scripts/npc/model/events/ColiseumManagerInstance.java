package npc.model.events;

import l2p.gameserver.data.xml.holder.EventHolder;
import l2p.gameserver.model.Player;
import l2p.gameserver.model.entity.events.EventType;
import l2p.gameserver.model.entity.events.impl.UndergroundColiseumEvent;
import l2p.gameserver.model.party.Party;
import l2p.gameserver.network.serverpackets.NpcHtmlMessage;
import l2p.gameserver.templates.npc.NpcTemplate;
import l2p.technical.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.List;

public class ColiseumManagerInstance extends ColiseumHelperInstance {
    private String _startHtm;
    private int _coliseumId;

    public ColiseumManagerInstance(int objectId, NpcTemplate template) {
        super(objectId, template);

        _startHtm = getParameter("start_htm", StringUtils.EMPTY);
        _coliseumId = getParameter("coliseum_id", 0);
    }

    @Override
    public void onBypassFeedback(Player player, String command) {
        if (!canBypassCheck(player, this))
            return;

        UndergroundColiseumEvent coliseumEvent = EventHolder.getInstance().getEvent(EventType.MAIN_EVENT, _coliseumId);

        if (command.equals("register")) {
            Party party = player.getParty();
            if (party == null)
                showChatWindow(player, "events/kerthang_manager008.htm");
            else if (party.getPartyLeader() != player)
                showChatWindow(player, "events/kerthang_manager004.htm");
            else {
                for (Player $player : party) {
                    if ($player == null || coliseumEvent == null)
                        return;
                    if ($player.getLevel() < coliseumEvent.getMinLevel() || $player.getLevel() > coliseumEvent.getMaxLevel()) {
                        showChatWindow(player, "events/kerthang_manager011.htm", "%name%", $player.getName());
                        return;
                    }
                }

                coliseumEvent.addTeam(player);
            }
        } else if (command.equals("viewTeams")) {
            if (coliseumEvent.getRegisteredPlayers() == null)
                return;

            List<Player> reg = coliseumEvent.getRegisteredPlayers();

            NpcHtmlMessage msg = new NpcHtmlMessage(player, this);
            msg.setFile("events/kerthang_manager003.htm");
            for (int i = 0; i < 5; i++) {
                Player $player = CollectionUtils.safeGet(reg, i);

                msg.replace("%team" + i + "%", $player == null ? StringUtils.EMPTY : $player.getName());
            }

            player.sendPacket(msg);
        } else
            super.onBypassFeedback(player, command);
    }

    @Override
    public void showChatWindow(Player player, int val, Object... ar) {
        showChatWindow(player, _startHtm);
    }
}

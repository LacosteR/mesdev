package npc.model;

import l2p.gameserver.model.Creature;
import l2p.gameserver.model.Player;
import l2p.gameserver.model.instances.NpcInstance;
import l2p.gameserver.network.serverpackets.MagicSkillUse;
import l2p.gameserver.tables.SkillTable;
import l2p.gameserver.templates.npc.NpcTemplate;

import java.util.ArrayList;
import java.util.List;

public final class NevitHeraldInstance extends NpcInstance {
    public NevitHeraldInstance(int objectId, NpcTemplate template) {
        super(objectId, template);
    }

    @Override
    public void onBypassFeedback(Player player, String command) {
        if (!canBypassCheck(player, this))
            return;

        if (command.equalsIgnoreCase("request_blessing")) {
            if (player.getEffectList().getEffectsBySkillId(23312) != null) {
                showChatWindow(player, 1);
                return;
            }
            List<Creature> target = new ArrayList<Creature>();
            target.add(player);
            broadcastPacket(new MagicSkillUse(this, player, 23312, 1, 0, 0));
            callSkill(SkillTable.getInstance().getInfo(23312, 1), target, true);
        } else
            super.onBypassFeedback(player, command);
    }
}
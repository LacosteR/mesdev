package npc.model;

import l2p.gameserver.model.Creature;
import l2p.gameserver.model.Player;
import l2p.gameserver.model.instances.WarehouseInstance;
import l2p.gameserver.network.serverpackets.MagicSkillUse;
import l2p.gameserver.network.serverpackets.components.SystemMsg;
import l2p.gameserver.scripts.Functions;
import l2p.gameserver.tables.SkillTable;
import l2p.gameserver.templates.item.ItemTemplate;
import l2p.gameserver.templates.npc.NpcTemplate;

import java.util.ArrayList;

public class ArenaManagerInstance extends WarehouseInstance {
    private static final int RECOVER_CP_SKILLID = 4380;
    private static final int RECOVER_HP_SKILLID = 6817;
    private static final int[][] BUFF_FIGHTER_IDS =
            {
                    {6803, 1},//Haste
                    {6809, 1},//Guidance
                    {6811, 1},//Whisper
                    {6808, 1},//Might
                    {6804, 1},//Wind Walk
                    {6812, 1},//Berserker Spirit
            };
    private static final int[][] BUFF_MAGE_IDS =
            {
                    {6805, 1},//Empower
                    {6806, 1},//Acumen
                    {6807, 1},//Concentration
                    {6808, 1},//Might
                    {6812, 1},//Berserker Spirit

            };

    public ArenaManagerInstance(int objectId, NpcTemplate template) {
        super(objectId, template);
    }

    @Override
    public void onBypassFeedback(Player player, String command) {
        if (!canBypassCheck(player, this))
            return;

        if (!player.isInPeaceZone() || player.isCursedWeaponEquipped())
            return;

        if (command.startsWith("CPRecovery")) {
            if (Functions.getItemCount(player, ItemTemplate.ITEM_ID_ADENA) >= 1000) {
                Functions.removeItem(player, ItemTemplate.ITEM_ID_ADENA, 1000);
                doCast(SkillTable.getInstance().getInfo(RECOVER_CP_SKILLID, 1), player, true);
            } else
                player.sendPacket(SystemMsg.YOU_DO_NOT_HAVE_ENOUGH_ADENA);
        } else if (command.startsWith("HPRecovery")) {
            if (Functions.getItemCount(player, ItemTemplate.ITEM_ID_ADENA) >= 1000) {
                Functions.removeItem(player, ItemTemplate.ITEM_ID_ADENA, 1000);
                doCast(SkillTable.getInstance().getInfo(RECOVER_HP_SKILLID, 1), player, true);
            } else
                player.sendPacket(SystemMsg.YOU_DO_NOT_HAVE_ENOUGH_ADENA);
        } else if (command.startsWith("ArenaBuff")) {
            if (Functions.getItemCount(player, ItemTemplate.ITEM_ID_ADENA) >= 2000) {
                ArrayList<Creature> target = new ArrayList<Creature>();
                target.add(player);

                Functions.removeItem(player, ItemTemplate.ITEM_ID_ADENA, 2000);
                for (int[] buff : (!player.isMageClass() ? BUFF_FIGHTER_IDS : BUFF_MAGE_IDS)) {
                    broadcastPacket(new MagicSkillUse(this, player, buff[0], buff[1], 0, 0));
                    callSkill(SkillTable.getInstance().getInfo(buff[0], buff[1]), target, true);
                }
            } else
                player.sendPacket(SystemMsg.YOU_DO_NOT_HAVE_ENOUGH_ADENA);
        } else
            super.onBypassFeedback(player, command);
    }
}

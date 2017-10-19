package services;

import l2p.gameserver.cache.Msg;
import l2p.gameserver.model.Player;
import l2p.gameserver.model.instances.NpcInstance;
import l2p.gameserver.network.serverpackets.SystemMessage;
import l2p.gameserver.scripts.Functions;
import l2p.gameserver.utils.PositionUtils;

public class ManaRegen extends Functions {
    private static final int ADENA = 57;
    private static final long PRICE = 5;

    public void DoManaRegen() {
        Player player = getSelf();
        NpcInstance npc = getNpc();
        if (npc == null || npc.getPositionComponent().getReflection().getId() != player.getPositionComponent().getReflection().getId()
                || !PositionUtils.isInRange(player, npc, NpcInstance.INTERACTION_DISTANCE, true))
            return;

        long mp = (long) Math.floor(player.getMaxMp() - player.getCurrentMp());
        long fullCost = mp * PRICE;
        if (fullCost <= 0) {
            player.sendPacket(Msg.NOTHING_HAPPENED);
            return;
        }
        if (getItemCount(player, ADENA) >= fullCost) {
            removeItem(player, ADENA, fullCost);
            player.sendPacket(new SystemMessage(SystemMessage.S1_MPS_HAVE_BEEN_RESTORED).addNumber(mp));
            player.setCurrentMp(player.getMaxMp());
        } else
            player.sendPacket(Msg.YOU_DO_NOT_HAVE_ENOUGH_ADENA);
    }
}
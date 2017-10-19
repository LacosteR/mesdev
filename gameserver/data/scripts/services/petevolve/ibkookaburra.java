package services.petevolve;

import l2p.gameserver.config.ConfigOthers;
import l2p.gameserver.data.retail.holder.PetDataHolder;
import l2p.gameserver.model.Player;
import l2p.gameserver.model.Summon;
import l2p.gameserver.model.instances.NpcInstance;
import l2p.gameserver.model.items.ItemInstance;
import l2p.gameserver.scripts.Functions;
import l2p.technical.dao.JdbcEntityState;

/**
 * User: darkevil
 * Date: 07.06.2008
 * Time: 0:37:55
 */
public class ibkookaburra extends Functions {
    private static final int BABY_KOOKABURRA = 12781;
    private static final int BABY_KOOKABURRA_OCARINA = PetDataHolder.getInstance().getControlItemId("pet_baby_kukaburo");
    private static final int IN_KOOKABURRA_OCARINA = PetDataHolder.getInstance().getControlItemId("upgrade_baby_kukaburo");

    public void evolve() {
        Player player = getSelf();
        NpcInstance npc = getNpc();
        if (player == null || npc == null)
            return;
        Summon pet = player.getPet();
        if (player.getInventory().getItemByItemId(BABY_KOOKABURRA_OCARINA) == null) {
            show("scripts/services/petevolve/no_item.htm", player, npc);
            return;
        } else if (pet == null || pet.isDead()) {
            show("scripts/services/petevolve/evolve_no.htm", player, npc);
            return;
        }
        if (pet.getNpcId() != BABY_KOOKABURRA) {
            show("scripts/services/petevolve/no_pet.htm", player, npc);
            return;
        }
        if (ConfigOthers.IMPROVED_PETS_LIMITED_USE && !player.isMageClass()) {
            show("scripts/services/petevolve/no_class_m.htm", player, npc);
            return;
        }
        if (pet.getLevel() < 55) {
            show("scripts/services/petevolve/no_level.htm", player, npc);
            return;
        }

        int controlItemId = player.getPet().getControlItemObjId();
        player.getPet().unSummon();

        ItemInstance control = player.getInventory().getItemByObjectId(controlItemId);
        control.setItemId(IN_KOOKABURRA_OCARINA);
        control.setEnchantLevel(55);
        control.setJdbcState(JdbcEntityState.UPDATED);
        control.update();
        player.sendItemList(false);

        show("scripts/services/petevolve/yes_pet.htm", player, npc);
    }
}
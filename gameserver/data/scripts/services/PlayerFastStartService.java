package services;

import l2p.gameserver.data.htm.HtmCache;
import l2p.gameserver.listener.actor.player.OnPlayerChangeClassListener;
import l2p.gameserver.listener.actor.player.OnPlayerEnterListener;
import l2p.gameserver.listener.actor.player.OnPlayerLvlUpListener;
import l2p.gameserver.model.Player;
import l2p.gameserver.model.actor.listener.CharListenerList;
import l2p.gameserver.model_pts.pts.PcClass;
import l2p.gameserver.mods.Merchant;
import l2p.gameserver.network.serverpackets.PlaySound;
import l2p.gameserver.scripts.Functions;
import l2p.gameserver.scripts.ScriptFile;
import l2p.gameserver.scripts.Scripts;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
/**
 * @author : Alice
 * @date : 09.09.2017
 * @time : 8:23
 * <p/>
 */
public class PlayerFastStartService extends Functions implements ScriptFile, OnPlayerEnterListener, OnPlayerLvlUpListener, OnPlayerChangeClassListener {
    private static final Logger log = LoggerFactory.getLogger(PlayerFastStartService.class);

    public static final String SOUND_TUTORIAL = "ItemSound.quest_tutorial";

    @Override
    public void onPlayerEnter(Player player) {
        if (player.getActiveClassId() != player.getBaseClassId()) {  // если это саб, то лесом
            return;
        }
        if (player.getLevel() >= 40 && player.getPcClass().getLevel() == 2) {   // если больше 40 уровня и первая профа
            player.sendPacket(new PlaySound(SOUND_TUTORIAL));
            Scripts.getInstance().callScripts(player, "services.ClassMasterCB", "show");
        } else if (player.getLevel() >= 40 && player.getPcClass().getLevel() == 3) {    // если больше 40 уровня и вторая профа
            showEquipmentHtm(player);
        } else if (player.getLevel() >= 20 && player.getPcClass().getLevel() == 1) {    // если больше 20 уровня и нет первой профы
            player.sendPacket(new PlaySound(SOUND_TUTORIAL));
            Scripts.getInstance().callScripts(player, "services.ClassMasterCB", "show");
        } else if (player.getLevel() >= 20 && player.getPcClass().getLevel() == 2) { // если больше 20 уровня и есть первая профа
            showEquipmentHtm(player);
        } else if (player.getVarInt("pfsm_Deq") != 0 && player.getLevel() < 40) {
            show(HtmCache.getInstance().getNotNull("scripts/services/faststart/teleport20fornewbie.htm", player), player, false);
        } else if (player.getVarInt("pfsm_Ceq") != 0 && player.getLevel() >= 40 && player.getLevel() < 52) {
            show(HtmCache.getInstance().getNotNull("scripts/services/faststart/teleport40fornewbie.htm", player), player, false);
        }
    }

    @Override
    public void onPlayerLvlUp(Player player) {
        if (player.getActiveClassId() != player.getBaseClassId()) {  // если это саб, то лесом
            return;
        }
        if (player.getLevel() >= 40 && player.getPcClass().getLevel() == 2) {
            player.sendPacket(new PlaySound(SOUND_TUTORIAL));
            Scripts.getInstance().callScripts(player, "services.ClassMasterCB", "show");
        } else if (player.getLevel() >= 20 && player.getPcClass().getLevel() == 1) {
            player.sendPacket(new PlaySound(SOUND_TUTORIAL));
            Scripts.getInstance().callScripts(player, "services.ClassMasterCB", "show");
        }
    }

    @Override
    public void onPlayerChangeClass(Player player) {
        if (player.getActiveClassId() != player.getBaseClassId()) {  // если это саб, то лесом
            return;
        }
        showEquipmentHtm(player);
    }

    private void showEquipmentHtm(Player player) {
        if (player.getPcClass().getLevel() >= 2) {   // первая профа уже есть
            if (player.getPcClass().getLevel() < 4 && player.getLevel() >= 40 && player.getVarInt("pfsm_Ceq") == 0) { // второй профы нет
                show(HtmCache.getInstance().getNotNull("scripts/services/faststart/equipcfornewbie.htm", player), player, false);
            }
        } else if (player.getLevel() >= 20 && player.getVarInt("pfsm_Deq") == 0) { // первой профы нет
            show(HtmCache.getInstance().getNotNull("scripts/services/faststart/equipdfornewbie.htm", player), player, false);
        }
    }

    public void giveEquipment() {
        Player player = getSelf();
        if (player == null) {
            return;
        }
        if (player.getVarInt("pfsm_Deq") == 0) {
            if (player.isMage()) {
                Merchant.addItem(player, 36041, 1); // mage
                Merchant.addItem(player, 36052, 1); // Shield
                Merchant.addItem(player, 36043, 1); // BIGBLUNT
                Merchant.addItem(player, 36067, 1);
            } else if (player.isRogue()) {
                if (player.getPcClass().class_id == 47) {  // orc_monk
                    Merchant.addItem(player, 36047, 1); // DUALFIST
                } else if (player.getPcClass().class_id == 125) {    // trooper
                    Merchant.addItem(player, 36049, 1); // RAPIER
                    Merchant.addItem(player, 36050, 1); // ANCIENTSWORD
                } else if (player.getPcClass().class_id == 126) {   // warder
                    Merchant.addItem(player, 36049, 1); // RAPIER
                    Merchant.addItem(player, 36051, 1); // CROSSBOW
                } else {
                    Merchant.addItem(player, 36042, 1); // DAGGER
                    Merchant.addItem(player, 36044, 1); // BOW
                }
                Merchant.addItem(player, 36068, 1);
            } else if (player.isWarrior()) {
                if (player.getPcClass().equalsOrChildOf(PcClass.orc_raider)) {
                    Merchant.addItem(player, 36046, 1); //  BIGSWORD
                } else if (player.getPcClass().isChild(PcClass.dwarven_fighter)) {  // scavenger, bounty_hunter
                    Merchant.addItem(player, 36040, 1); //  BLUNT
                    Merchant.addItem(player, 36052, 1); //  Shield   
                } else if (player.getPcClass().class_id == 45) {  //
                    Merchant.addItem(player, 36048, 1); // Dualsword
                } else {  // knights
                    Merchant.addItem(player, 36039, 1); //  sword
                    Merchant.addItem(player, 36052, 1); //  Shield
                }
                Merchant.addItem(player, 36069, 1);
            }
            player.setVar("pfsm_Deq", 1, -1);   // get D equip
            show(HtmCache.getInstance().getNotNull("scripts/services/faststart/teleport20fornewbie.htm", player), player, false);
        } else if (player.getVarInt("pfsm_Ceq") == 0) {
            if (player.isMage()) {
                if (player.getPcClass().isChild(PcClass.orc_shaman)) {
                    Merchant.addItem(player, 36057, 1); // mage blunt
                    Merchant.addItem(player, 36053, 1); // shield
                } else {
                    Merchant.addItem(player, 36056, 1); // mage sword
                    Merchant.addItem(player, 36053, 1); // shield
                }
                Merchant.addItem(player, 36070, 1);
            } else if (player.isRogue()) {
                if (player.getPcClass().equalsOrChildOf(PcClass.tyrant)) {
                    Merchant.addItem(player, 36061, 1); // Fists
                }
                if (player.getPcClass().equalsOrChildOf(PcClass.m_soul_breaker) || player.getPcClass().equalsOrChildOf(PcClass.f_soul_breaker)) {
                    Merchant.addItem(player, 36064, 1); // rapier
                }
                if (player.getPcClass().equalsOrChildOf(PcClass.arbalester)) {
                    Merchant.addItem(player, 36066, 1); // Crossbow
                }
                if (player.getPcClass().equalsOrChildOf(PcClass.berserker)) {
                    Merchant.addItem(player, 36065, 1); // Ancient Sword
                } else {
                    Merchant.addItem(player, 36059, 1); // bow
                    Merchant.addItem(player, 36058, 1); // dagger
                }
                Merchant.addItem(player, 36071, 1);
            } else if (player.isWarrior()) {
                if (player.getPcClass().equalsOrChildOf(PcClass.orc_raider)) {
                    Merchant.addItem(player, 36060, 1); // big sword
                } else if (player.getPcClass().equalsOrChildOf(PcClass.gladiator) || player.getPcClass().equalsOrChildOf(PcClass.bladedancer)) {
                    Merchant.addItem(player, 36063, 1); // dual
                } else if (player.getPcClass().equalsOrChildOf(PcClass.warlord)) {
                    Merchant.addItem(player, 36062, 1); // spear
                } else if (player.getPcClass().equalsOrChildOf(PcClass.warsmith) || player.getPcClass().equalsOrChildOf(PcClass.bounty_hunter)) {
                    Merchant.addItem(player, 36055, 1); // blunt
                    Merchant.addItem(player, 36053, 1); // shield
                } else {
                    Merchant.addItem(player, 36054, 1); // sword
                    Merchant.addItem(player, 36053, 1); // shield
                }
                Merchant.addItem(player, 36072, 1);
            }
            player.setVar("pfsm_Ceq", 1, -1);   // get C eq
            show(HtmCache.getInstance().getNotNull("scripts/services/faststart/teleport40fornewbie.htm", player), player, false);
        }
    }

    @Override
    public void onLoad() {
        CharListenerList.addGlobal(this);
    }

    @Override
    public void onReload() {
    }

    @Override
    public void onShutdown() {
    }
}

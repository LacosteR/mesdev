package npc.model.residences.fortress.peace;

import l2p.gameserver.config.ConfigExternal;
import l2p.gameserver.dao.SiegeClanDAO;
import l2p.gameserver.data.xml.holder.EventHolder;
import l2p.gameserver.data.xml.holder.ResidenceHolder;
import l2p.gameserver.model.Player;
import l2p.gameserver.model.entity.events.EventType;
import l2p.gameserver.model.entity.events.impl.DominionSiegeRunnerEvent;
import l2p.gameserver.model.entity.events.impl.FortressSiegeEvent;
import l2p.gameserver.model.entity.events.impl.SiegeEvent;
import l2p.gameserver.model.entity.events.objects.SiegeClanObject;
import l2p.gameserver.model.entity.residence.Fortress;
import l2p.gameserver.model.instances.NpcInstance;
import l2p.gameserver.model.pledge.Clan;
import l2p.gameserver.model.pledge.Privilege;
import l2p.gameserver.network.serverpackets.NpcHtmlMessage;
import l2p.gameserver.network.serverpackets.SystemMessage2;
import l2p.gameserver.network.serverpackets.components.SystemMsg;
import l2p.gameserver.templates.item.ItemTemplate;
import l2p.gameserver.templates.npc.NpcTemplate;
import l2p.technical.lang.ArrayUtils;

import java.util.Calendar;

public class SuspiciousMerchantModdedInstance extends NpcInstance {
    public SuspiciousMerchantModdedInstance(int objectID, NpcTemplate template) {
        super(objectID, template);
    }

    @Override
    public void onBypassFeedback(Player player, String command) {
        if (!canBypassCheck(player, this)) {
            return;
        }

        Fortress fortress = getFortress();
        FortressSiegeEvent siegeEvent = fortress.getSiegeEvent();

        if (command.equalsIgnoreCase("register")) {
            Clan clan = player.getClan();
            if (clan == null) {
                showChatWindow(player, "residence2/fortress/fortress_ordery002.htm");
                return;
            }

            if (clan.getHasFortress() == fortress.getId()) {
                showChatWindow(player, "residence2/fortress/fortress_ordery014.htm", "%clan_name%", clan.getName());
                return;
            }

            if (!player.hasPrivilege(Privilege.CS_FS_SIEGE_WAR)) {
                showChatWindow(player, "residence2/fortress/fortress_ordery012.htm");
                return;
            }

            if (clan.getCastle() > 0) {
                showChatWindow(player, "residence2/fortress/fortress_ordery021.htm");
                return;
            }

            int attackersSize = siegeEvent.getObjects(SiegeEvent.ATTACKERS).size();

            if (attackersSize == 0) {
                if (!player.consumeItem(ItemTemplate.ITEM_ID_ADENA, 250000L)) {
                    showChatWindow(player, "residence2/fortress/fortress_ordery003.htm");
                    return;
                }
            }

            SiegeClanObject siegeClan = siegeEvent.getSiegeClan(FortressSiegeEvent.ATTACKERS, clan);
            if (siegeClan != null) {
                showChatWindow(player, "residence2/fortress/fortress_ordery007.htm");
                return;
            }

            // 1 рега возможна всего
            for (Fortress fort : ResidenceHolder.getInstance().getResidenceList(Fortress.class)) {
                if (fort.getSiegeEvent().getSiegeClan(FortressSiegeEvent.ATTACKERS, clan) != null) {
                    showChatWindow(player, "residence2/fortress/fortress_ordery006.htm");
                    return;
                }
            }

            // если у нас есть форт, запрещаем регатся на форт, если на носу осада своего форта(во избежания абуза, участия в 2 осадах)
            if (clan.getHasFortress() > 0 && fortress.getSiegeDate().getTimeInMillis() > 0) {
                showChatWindow(player, "residence2/fortress/fortress_ordery006.htm");
                return;
            }

            DominionSiegeRunnerEvent runnerEvent = EventHolder.getInstance().getEvent(EventType.MAIN_EVENT, 1);
            if (runnerEvent.isRegistrationOver()) {
                showChatWindow(player, "residence2/fortress/fortress_ordery006.htm");
                return;
            }

            if(clan.getAllMembers().size() < 15) {
                showChatWindow(player, "residence2/fortress/fortress_ordery_15men.htm");
                return;
            }

            if(clan.getLevel() < 5) {
                showChatWindow(player, "residence2/fortress/fortress_ordery_5lvl.htm");
                return;
            }

            Calendar calendar = Calendar.getInstance();
            int current_hour = calendar.get(Calendar.HOUR_OF_DAY);
            if(current_hour < ConfigExternal.MOD_FORT_SIEGE_HOUR - 6) {
                showChatWindow(player, "residence2/fortress/fortress_ordery_6hours.htm");
                return;
            }

            if(current_hour > ConfigExternal.MOD_FORT_SIEGE_HOUR) {
                showChatWindow(player, "residence2/fortress/fortress_ordery_regclosed.htm");
                return;
            }

            if (ConfigExternal.IS_MODIFICATION_FORTRESS_ENABLED) {
                int fort_id = fortress.getId();
                // 112 Floran; 116 Antharas; 117 Western; 118 Hunters
                if (fort_id != 112 && fort_id != 116 && fort_id != 117 && fort_id != 118) {
                    player.sendMessage("Данная крепость недоступна для осады");
                    return;
                }
                
                if (!ArrayUtils.containsInt(ConfigExternal.MOD_FORT_SIEGE_DAYS, calendar.get(Calendar.DAY_OF_WEEK))) {
                    player.sendMessage("Зарегистрироваться можно только в понедельник, среду, пятницу и воскресенье");
                    return;
                }
            }

            siegeClan = new SiegeClanObject(FortressSiegeEvent.ATTACKERS, clan, 0);
            siegeEvent.addObject(FortressSiegeEvent.ATTACKERS, siegeClan);
            SiegeClanDAO.getInstance().insert(fortress, siegeClan);

            siegeEvent.reCalcNextTime(false);

            player.sendPacket(new SystemMessage2(SystemMsg.YOUR_CLAN_HAS_BEEN_REGISTERED_TO_S1S_FORTRESS_BATTLE).addResidenceName(fortress));
            showChatWindow(player, "residence2/fortress/fortress_ordery005.htm");
        } else if (command.equalsIgnoreCase("cancel")) {
            Clan clan = player.getClan();
            if (clan == null || !player.hasPrivilege(Privilege.CS_FS_SIEGE_WAR)) {
                showChatWindow(player, "residence2/fortress/fortress_ordery010.htm");
                return;
            }

            SiegeClanObject siegeClan = siegeEvent.getSiegeClan(FortressSiegeEvent.ATTACKERS, clan);
            if (siegeClan != null) {
                siegeEvent.removeObject(FortressSiegeEvent.ATTACKERS, siegeClan);
                SiegeClanDAO.getInstance().delete(fortress, siegeClan);

                siegeEvent.reCalcNextTime(false);

                showChatWindow(player, "residence2/fortress/fortress_ordery009.htm");
            } else {
                showChatWindow(player, "residence2/fortress/fortress_ordery011.htm");
            }
        } else if (command.equalsIgnoreCase("state")) {
            int attackersSize = siegeEvent.getObjects(SiegeEvent.ATTACKERS).size();
            if (attackersSize == 0) {
                showChatWindow(player, "residence2/fortress/fortress_ordery019.htm");
            } else {
                showChatWindow(player, "residence2/fortress/fortress_ordery020.htm");
            }
        } else {
            super.onBypassFeedback(player, command);
        }
    }

    @Override
    public void showChatWindow(Player player, int val, Object... arg) {
        NpcHtmlMessage html = new NpcHtmlMessage(player, this);
        Fortress fortress = getFortress();
        if (fortress.getOwner() != null) {
            html.setFile("residence2/fortress/fortress_ordery001a.htm");
            html.replace("%clan_name%", fortress.getOwner().getName());
        } else {
            html.setFile("residence2/fortress/fortress_ordery001.htm");
        }

        player.sendPacket(html);
    }
}
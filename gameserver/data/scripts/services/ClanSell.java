package services;

import l2p.gameserver.cache.Msg;
import l2p.gameserver.config.ConfigServices;
import l2p.gameserver.data.xml.holder.ItemHolder;
import l2p.gameserver.model.Player;
import l2p.gameserver.model.items.ItemInstance;
import l2p.gameserver.model.pledge.Clan;
import l2p.gameserver.model.pledge.UnitMember;
import l2p.gameserver.network.serverpackets.PledgeShowInfoUpdate;
import l2p.gameserver.network.serverpackets.PledgeStatusChanged;
import l2p.gameserver.network.serverpackets.components.CustomMessage;
import l2p.gameserver.scripts.Functions;
import l2p.gameserver.templates.item.ItemTemplate;
import l2p.gameserver.utils.SiegeUtils;
import l2p.gameserver.utils.Util;

public class ClanSell extends Functions //implements ScriptFile, OnPlayerEnterListener
{

    private int priceLvlUp = ConfigServices.CLAN_LVL_UP_BUY;
    private int priceRepBuy = ConfigServices.CLAN_REP_BUY;
    private int maxLvlUp = ConfigServices.CLAN_LEVEL_UP_MAX;
    private int itemId = ConfigServices.CLAN_SELL_ITEM;
    private int itemRepId = ConfigServices.CLAN_REP_SELL_ITEM;
    private boolean enabled = ConfigServices.CLAN_SELL_ALLOW;

    public void showPage() {
        Player player = getSelf();
        if (player == null) {
            return;
        }
        if (!enabled) {
            show("Service is offline.", player);
            return;
        }
        StringBuilder sb = new StringBuilder();
        sb.append("Clan selling");
        sb.append("<br>");
        sb.append("Rise clan level costs: <font color=\"LEVEL\">" + ConfigServices.CLAN_LVL_UP_BUY + "</font>");
        sb.append("<table>");
        sb.append("<tr><td> <button width=100 height=15 back=\"L2UI_CT1.Button_DF_Down\" fore=\"L2UI_CT1.Button_DF\" action=\"bypass -h scripts_services.ClanSell:lvlup\" value=\"Level up\">");
        sb.append("<tr><td><br>");
        sb.append("<font color=\"LEVEL\">" + new CustomMessage("scripts.services.ClanSell.BuyRep", getSelf()).addString(Util.formatAdena(priceRepBuy)).addItemName(itemRepId) + "</font></td></tr>");
        sb.append("<tr><td>" + new CustomMessage("scripts.services.ClanSell.RepAdd", getSelf()) + " <edit var=\"new_level\" width=80></td></tr>");
        sb.append("<tr><td><button value=\"" + new CustomMessage("scripts.services.ClanSell.RepButton", getSelf()) + "\" action=\"bypass -h scripts_services.ClanSell:repbuy $new_level\" width=80 height=15 back=\"L2UI_CT1.Button_DF_Down\" fore=\"L2UI_CT1.Button_DF\"></td></tr>");
        sb.append("</table>");
        show(sb.toString(), player);
    }

    public void lvlup() {
        Player player = getSelf();
        if (player == null) {
            return;
        }

        Clan clan = player.getClan();
        if (clan == null) {
            return;
        }

        if (!player.isClanLeader()) {
            player.sendPacket(Msg.ONLY_THE_CLAN_LEADER_IS_ENABLED);
            return;
        }

        boolean increaseClanLevel = true;

        if (player.getInventory().getItemByItemId(itemId) == null || player.getInventory().getItemByItemId(itemId).getCount() < priceLvlUp) {
            increaseClanLevel = false;
        }

        if (clan.getLevel() == 11) {
            increaseClanLevel = false;
        }

        if (clan.getLevel() + 1 > maxLvlUp) {
            increaseClanLevel = false;
        }

        ItemTemplate item = ItemHolder.getInstance().getTemplate(itemId);
        ItemInstance pay = player.getInventory().getItemByItemId(item.getItemId());
        if (pay != null && pay.getCount() >= priceLvlUp) {
            player.getInventory().destroyItem(pay, priceLvlUp);
        } else
            increaseClanLevel = false;

        if (increaseClanLevel) {
            clan.setLevel(clan.getLevel() + 1);
            clan.updateClanInDB();

            player.broadcastCharInfo();

            if (clan.getLevel() >= 4)
                SiegeUtils.addSiegeSkills(player);

            if (clan.getLevel() == 5)
                player.sendPacket(Msg.NOW_THAT_YOUR_CLAN_LEVEL_IS_ABOVE_LEVEL_5_IT_CAN_ACCUMULATE_CLAN_REPUTATION_POINTS);

            // notify all the members about it
            PledgeShowInfoUpdate pu = new PledgeShowInfoUpdate(clan);
            PledgeStatusChanged ps = new PledgeStatusChanged(clan);
            for (UnitMember mbr : clan) {
                if (mbr.isOnline()) {
                    mbr.getPlayer().updatePledgeClass();
                    mbr.getPlayer().sendPacket(Msg.CLANS_SKILL_LEVEL_HAS_INCREASED, pu, ps);
                    mbr.getPlayer().broadcastCharInfo();
                }
            }
        } else
            player.sendPacket(Msg.CLAN_HAS_FAILED_TO_INCREASE_SKILL_LEVEL);
    }

    public void repbuy(String[] args) {
        Player player = getSelf();
        if (player == null)
            return;

        if (!enabled) {
            show("Service is disabled.", player);
            return;
        }

        if (args.length != 1) {
            show(new CustomMessage("scripts.services.LevelUp.incorrectinput", player), player);
            return;
        }

        if (args.length > 2)
            return;

        if (!isIntNumber(args[0])) {
            show("Please enter the numbers.", player);
            return;
        }

        int setrep = Integer.parseInt(args[0]);
        if (setrep > 100000) setrep = 100000;
        if (setrep < 1) {
            show("Please enter the numbers: 1-100.000", player);
            return;
        }

        ItemTemplate item = ItemHolder.getInstance().getTemplate(itemRepId);
        ItemInstance pay = player.getInventory().getItemByItemId(item.getItemId());
        if (player.getClan() != null && pay != null && pay.getCount() >= priceRepBuy * setrep) {
            player.getInventory().destroyItem(pay, priceRepBuy * setrep);
            player.getClan().incReputation(setrep, false, "clan sell");
        } else
            player.sendMessage("Incorrect item count.");
    }

    public boolean isIntNumber(String num) {
        try {
            Integer.parseInt(num);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }
}
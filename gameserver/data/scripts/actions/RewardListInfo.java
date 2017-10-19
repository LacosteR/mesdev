package actions;

import l2p.gameserver.config.ConfigNPC;
import l2p.gameserver.config.ConfigRates;
import l2p.gameserver.model.Player;
import l2p.gameserver.model.base.Experience;
import l2p.gameserver.model.instances.NpcInstance;
import l2p.gameserver.model.reward.RewardData;
import l2p.gameserver.model.reward.RewardGroup;
import l2p.gameserver.model.reward.RewardList;
import l2p.gameserver.model.reward.RewardType;
import l2p.gameserver.network.serverpackets.NpcHtmlMessage;
import l2p.gameserver.stats.Stats;
import l2p.gameserver.utils.HtmlUtils;
import org.apache.commons.lang3.StringUtils;

import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;
import java.util.Map;

public abstract class RewardListInfo
{
    private static final NumberFormat pf = NumberFormat.getPercentInstance(Locale.ENGLISH);
    private static final NumberFormat df = NumberFormat.getInstance(Locale.ENGLISH);

    static
    {
        pf.setMaximumFractionDigits(4);
        df.setMinimumFractionDigits(2);
    }

    public static void showInfo(Player player, NpcInstance npc)
    {
        final int diff = npc.calculateLevelDiffForDrop(player.isInParty() ? player.getParty().getLevel() : player.getLevel());
        double mod = npc.calcStat(Stats.REWARD_MULTIPLIER, 1., player, null);
        mod *= Experience.penaltyModifier(diff, 9);

        NpcHtmlMessage htmlMessage = new NpcHtmlMessage(5);
        htmlMessage.replace("%npc_name%", HtmlUtils.htmlNpcName(npc.getNpcId()));

        if(mod <= 0)
        {
            htmlMessage.setFile("actions/rewardlist_to_weak.htm");
            player.sendPacket(htmlMessage);
            return;
        }

        if(npc.getTemplate().getRewards().isEmpty())
        {
            htmlMessage.setFile("actions/rewardlist_empty.htm");
            player.sendPacket(htmlMessage);
            return;
        }

        htmlMessage.setFile("actions/rewardlist_info.htm");

        StringBuilder builder = new StringBuilder(100);
        for(Map.Entry<RewardType, RewardList> entry : npc.getTemplate().getRewards().entrySet())
        {
            RewardList rewardList = entry.getValue();

            if(entry.getKey() == entry.getKey().RATED_GROUPED)
            {
                ratedGroupedRewardList(builder, npc, rewardList, player, mod);
            }
        }
        for(Map.Entry<RewardType, RewardList> entry : npc.getTemplate().getRewards().entrySet())
        {
            RewardList rewardList = entry.getValue();

            if(entry.getKey() == entry.getKey().SWEEP)
            {
                notGroupedRewardList(builder, rewardList, player.getRateSpoil(), mod);
                break;
            }
        }
        htmlMessage.replace("%info%", builder.toString());
        player.sendPacket(htmlMessage);
    }

    public static void ratedGroupedRewardList(StringBuilder tmp, NpcInstance npc, RewardList list, Player player, double mod)
    {
        tmp.append("<table width=270 border=0>");
        tmp.append("<tr><td><table width=270 border=0><tr><td><font color=\"aaccff\">").append("DROP").append("</font></td></tr></table></td></tr>");
        tmp.append("<tr><td><img src=\"L2UI.SquareWhite\" width=270 height=1> </td></tr>");
        tmp.append("<tr><td><img src=\"L2UI.SquareBlank\" width=270 height=10> </td></tr>");

        for(RewardGroup group : list)
        {
            List<RewardData> items = group.getItems();

            tmp.append("<tr><td><img src=\"L2UI.SquareBlank\" width=270 height=10> </td></tr>");
            tmp.append("<tr><td>");
            tmp.append("<table width=270 border=0 bgcolor=333333>");
            tmp.append("<tr><td width=170><font color=\"a2a0a2\">Drop Group: ").append(group.getChance()/10000).append("</font><font color=\"b09979\">").append("</font></td>");
            tmp.append("<td width=100 align=right>");
            tmp.append("</td></tr>");
            tmp.append("</table>").append("</td></tr>");

            tmp.append("<tr><td><table>");
            for(RewardData d : items)
            {
                double chance = d.getChance() / 1000000;

                double adena_rate = 1.;

                if(d.getItem().isAdena()) {
                    adena_rate = ConfigRates.RATE_DROP_ADENA * player.getBonus().getDropAdena();
                }

                String icon = d.getItem().getIcon();
                if(icon == null || icon.equals(StringUtils.EMPTY))
                    icon = "icon.etc_question_mark_i00";

                tmp.append("<tr><td width=32><img src=").append(icon).append(" width=32 height=32></td><td width=238>").append(HtmlUtils.htmlItemName(d.getItemId())).append("<br1>");

                if(npc.getSkillLevel(4407) == 1)
                {
                    tmp.append("<font color=\"b09979\">[").append(Math.round(d.getMinDrop() * (d.getItem().isAdena() ? (ConfigNPC.NPC_CHAMPION_BLUE_DROP_RATE * adena_rate) : 1.0))).append("..").append(Math.round(d.getMaxDrop() * (d.getItem().isAdena() ? (ConfigNPC.NPC_CHAMPION_BLUE_DROP_RATE * adena_rate) : 1.))).append("]&nbsp;");
                }
                else if(npc.getSkillLevel(4407) == 2)
                {
                    tmp.append("<font color=\"b09979\">[").append(Math.round(d.getMinDrop() * (d.getItem().isAdena() ? (ConfigNPC.NPC_CHAMPION_RED_DROP_RATE * adena_rate) : 1.0))).append("..").append(Math.round(d.getMaxDrop() * (d.getItem().isAdena() ? (ConfigNPC.NPC_CHAMPION_RED_DROP_RATE * adena_rate) : 1.))).append("]&nbsp;");
                }
                else
                {
                    tmp.append("<font color=\"b09979\">[").append(Math.round(d.getMinDrop() * (d.getItem().isAdena() ? adena_rate : 1.0))).append("..").append(Math.round(d.getMaxDrop() * (d.getItem().isAdena() ? adena_rate : 1.))).append("]&nbsp;");
                }

                if(d.getItem().isAdena())
                {
                    tmp.append(pf.format(0.7)).append("</font></td></tr>");
                }
                else
                {
                    tmp.append(pf.format(chance)).append("</font></td></tr>");
                }
            }
            tmp.append("</table></td></tr>");
        }
        tmp.append("<tr><td><img src=\"L2UI.SquareBlank\" width=270 height=10> </td></tr>");
        tmp.append("</table>");
    }

    public static void notGroupedRewardList(StringBuilder tmp, RewardList list, double personalRate, double mod)
    {
        tmp.append("<table width=270 border=0>");
        tmp.append("<tr><td><img src=\"L2UI.SquareBlank\" width=270 height=10> </td></tr>");
        tmp.append("<tr><td><table width=270 border=0><tr><td><font color=\"aaccff\">").append("SPOIL").append("</font></td></tr></table></td></tr>");
        tmp.append("<tr><td><img src=\"L2UI.SquareWhite\" width=270 height=1> </td></tr>");
        tmp.append("<tr><td><img src=\"L2UI.SquareBlank\" width=270 height=10> </td></tr>");

        tmp.append("<tr><td><table>");
        for(RewardGroup g : list)
        {
            List<RewardData> items = g.getItems();

            for(RewardData d : items)
            {
                double chance = (d.notRate() ? 1.0 : d.getChance())/1000000;

                String icon = d.getItem().getIcon();
                if(icon == null || icon.equals(StringUtils.EMPTY))
                    icon = "icon.etc_question_mark_i00";

                tmp.append("<tr><td width=32><img src=").append(icon).append(" width=32 height=32></td><td width=238>").append(HtmlUtils.htmlItemName(d.getItemId())).append("<br1>");
                tmp.append("<font color=\"b09979\">[").append(d.getMinDrop()).append("..").append(Math.round(d.getMaxDrop() * personalRate)).append("]&nbsp;");
                tmp.append(pf.format(chance)).append("</font></td></tr>");
            }
        }
        tmp.append("</table></td></tr>");
        tmp.append("<tr><td><img src=\"L2UI.SquareBlank\" width=270 height=10> </td></tr>");
        tmp.append("</table>");
    }
}

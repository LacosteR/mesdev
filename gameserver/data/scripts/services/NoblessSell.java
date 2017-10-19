package services;

import l2p.gameserver.config.ConfigServices;
import l2p.gameserver.data.xml.holder.ItemHolder;
import l2p.gameserver.instancemanager.QuestManager;
import l2p.gameserver.model.Player;
import l2p.gameserver.model.base.Race;
import l2p.gameserver.model.entity.olympiad.Olympiad;
import l2p.gameserver.model.quest.Quest;
import l2p.gameserver.model.quest.QuestState;
import l2p.gameserver.mods.Merchant;
import l2p.gameserver.network.serverpackets.SkillList;
import l2p.gameserver.scripts.Functions;

public class NoblessSell extends Functions {
    private boolean allowNobleWOsub = true;

    public void get() {
        Player player = getSelf();
        if (player == null)
            return;

        if (!ConfigServices.SERVICES_NOBLESS_SELL_ENABLED) {
            player.sendMessage("Service disabled");
            return;
        }

        StringBuilder text = new StringBuilder();
        text.append("Nobless sell price ").append(ConfigServices.SERVICES_NOBLESS_SELL_PRICE).append(" ").append(ItemHolder.getInstance().getTemplate(ConfigServices.SERVICES_NOBLESS_SELL_ITEM).getName()).append(".");
        text.append("<br>Nobless sell service:<br>");
        String message = getCautionMessage(player);
        if (message != null)
            text.append("<br>" + message);
        else
            text.append("<br><a action=\"bypass -h scripts_services.NoblessSell:becomeNoble\">Yes, I'm sure!</a>");
        show(text.toString(), player, null);
    }

    public String getCautionMessage(Player player) {
        if (player.isNoble())
            return "You are noblesse already.";

        if (player.getSubLevel() < 75 && !allowNobleWOsub || player.getPcClass().getLevel() != 4)
            return "You must make sub class level 75 first or 3rd profession.";

        return null;
    }

    public void makeSubQuests() {
        Player player = getSelf();

        Quest q = QuestManager.getQuest("_234_FatesWhisper");
        QuestState qs = player.getQuestState(q.getClass());
        if (qs != null)
            qs.exitCurrentQuest(true);
        q.newQuestState(player, Quest.COMPLETED);

        if (player.getRace() == Race.kamael) {
            q = QuestManager.getQuest("_236_SeedsOfChaos");
            qs = player.getQuestState(q.getClass());
            if (qs != null)
                qs.exitCurrentQuest(true);
            q.newQuestState(player, Quest.COMPLETED);
        } else {
            q = QuestManager.getQuest("_235_MimirsElixir");
            qs = player.getQuestState(q.getClass());
            if (qs != null)
                qs.exitCurrentQuest(true);
            q.newQuestState(player, Quest.COMPLETED);
        }
    }

    public void becomeNoble() {
        Player player = getSelf();
        if (player == null || player.isNoble())
            return;


        if (!Merchant.getPay(player, ConfigServices.SERVICES_NOBLESS_SELL_ITEM, ConfigServices.SERVICES_NOBLESS_SELL_PRICE)) {
            player.sendMessage("You do not have enough items count");
            return;
        }

        makeSubQuests();

        Olympiad.addNoble(player);
        player.setNoble(true);
        player.updatePledgeClass();
        player.updateNobleSkills();
        player.sendPacket(new SkillList(player));
        player.broadcastUserInfo(true);
    }
}
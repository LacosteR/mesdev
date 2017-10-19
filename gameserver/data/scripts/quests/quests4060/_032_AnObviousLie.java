package quests.quests4060;

import l2p.gameserver.model.instances.NpcInstance;
import l2p.gameserver.model.quest.Quest;
import l2p.gameserver.model.quest.QuestState;
import l2p.gameserver.scripts.ScriptFile;

public class _032_AnObviousLie extends Quest implements ScriptFile {
    //NPC
    int MAXIMILIAN = 30120;
    int GENTLER = 30094;
    int MIKI_THE_CAT = 31706;
    //MOBS
    int ALLIGATOR = 20135;
    //CHANCE FOR DROP
    int CHANCE_FOR_DROP = 30;
    //ITEMS
    int MAP = 7165;
    int MEDICINAL_HERB = 7166;
    int SPIRIT_ORES = 3031;
    int THREAD = 1868;
    int SUEDE = 1866;
    //REWARDS
    int RACCOON_EAR = 7680;
    int CAT_EAR = 6843;
    int RABBIT_EAR = 7683;

    @Override
    public void onLoad() {
    }

    @Override
    public void onReload() {
    }

    @Override
    public void onShutdown() {
    }

    public _032_AnObviousLie() {
        super(false);

        addStartNpc(MAXIMILIAN);
        addTalkId(MAXIMILIAN);
        addTalkId(GENTLER);
        addTalkId(MIKI_THE_CAT);

        addKillId(ALLIGATOR);

        addQuestItem(MEDICINAL_HERB);
        addQuestItem(MAP);
    }

    @Override
    public String onEvent(String event, QuestState st, NpcInstance npc) {
        String htmltext = event;
        if (event.equals("30120-1.htm")) {
            st.setCond(1);
            st.setState(STARTED);
            st.playSound(SOUND_ACCEPT);
        } else if (event.equals("30094-1.htm")) {
            st.doGiveItems(MAP, 1);
            st.setCond(2);
        } else if (event.equals("31706-1.htm")) {
            st.doTakeItems(MAP, 1);
            st.setCond(3);
        } else if (event.equals("30094-4.htm")) {
            if (st.getQuestItemsCount(MEDICINAL_HERB) > 19) {
                st.doTakeItems(MEDICINAL_HERB, 20);
                st.setCond(5);
            } else {
                htmltext = "You don't have enough materials";
                st.setCond(3);
            }
        } else if (event.equals("30094-7.htm")) {
            if (st.getQuestItemsCount(SPIRIT_ORES) >= 500) {
                st.doTakeItems(SPIRIT_ORES, 500);
                st.setCond(6);
            } else
                htmltext = "You don't have enough materials";
        } else if (event.equals("31706-4.htm"))
            st.setCond(7);
        else if (event.equals("30094-10.htm"))
            st.setCond(8);
        else if (event.equals("30094-13.htm")) {
            if (st.getQuestItemsCount(THREAD) < 1000 || st.getQuestItemsCount(SUEDE) < 500)
                htmltext = "You don't have enough materials";
        } else if (event.equalsIgnoreCase("cat") || event.equalsIgnoreCase("racoon") || event.equalsIgnoreCase("rabbit"))
            if (st.getCond() == 8 && st.getQuestItemsCount(THREAD) >= 1000 && st.getQuestItemsCount(SUEDE) >= 500) {
                st.doTakeItems(THREAD, 1000);
                st.doTakeItems(SUEDE, 500);
                if (event.equalsIgnoreCase("cat"))
                    st.doRewardItems(CAT_EAR, 1);
                else if (event.equalsIgnoreCase("racoon"))
                    st.doRewardItems(RACCOON_EAR, 1);
                else if (event.equalsIgnoreCase("rabbit"))
                    st.doRewardItems(RABBIT_EAR, 1);
                st.unset("cond");
                st.playSound(SOUND_FINISH);
                htmltext = "30094-14.htm";
                st.exitCurrentQuest(false);
            } else
                htmltext = "You don't have enough materials";
        return htmltext;
    }

    @Override
    public String onTalk(NpcInstance npc, QuestState st) {
        String htmltext = "noquest";
        int npcId = npc.getNpcId();
        int cond = st.getCond();
        if (npcId == MAXIMILIAN)
            if (cond == 0) {
                if (st.getPlayer().getLevel() >= 45)
                    htmltext = "30120-0.htm";
                else {
                    htmltext = "30120-0a.htm";
                    st.exitCurrentQuest(true);
                }
            } else if (cond == 1)
                htmltext = "30120-2.htm";
        if (npcId == GENTLER)
            if (cond == 1)
                htmltext = "30094-0.htm";
            else if (cond == 2)
                htmltext = "30094-2.htm";
            else if (cond == 3)
                htmltext = "30094-forgot.htm";
            else if (cond == 4)
                htmltext = "30094-3.htm";
            else if (cond == 5 && st.getQuestItemsCount(SPIRIT_ORES) < 500)
                htmltext = "30094-5.htm";
            else if (cond == 5 && st.getQuestItemsCount(SPIRIT_ORES) >= 500)
                htmltext = "30094-6.htm";
            else if (cond == 6)
                htmltext = "30094-8.htm";
            else if (cond == 7)
                htmltext = "30094-9.htm";
            else if (cond == 8 && (st.getQuestItemsCount(THREAD) < 1000 || st.getQuestItemsCount(SUEDE) < 500))
                htmltext = "30094-11.htm";
            else if (cond == 8 && st.getQuestItemsCount(THREAD) >= 1000 && st.getQuestItemsCount(SUEDE) >= 500)
                htmltext = "30094-12.htm";
        if (npcId == MIKI_THE_CAT)
            if (cond == 2)
                htmltext = "31706-0.htm";
            else if (cond == 3)
                htmltext = "31706-2.htm";
            else if (cond == 6)
                htmltext = "31706-3.htm";
            else if (cond == 7)
                htmltext = "31706-5.htm";
        return htmltext;
    }

    @Override
    public String onKill(NpcInstance npc, QuestState st) {
        if (st.getCond() != 3)
            return null;

        if (st.doGiveOnKillItems(MEDICINAL_HERB, 1, 1, 20, 100))
            st.setCond(4);

        return null;
    }
}
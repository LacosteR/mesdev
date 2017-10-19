package quests.quests6076;

import l2p.gameserver.model.instances.NpcInstance;
import l2p.gameserver.model.quest.Quest;
import l2p.gameserver.model.quest.QuestState;
import l2p.gameserver.scripts.ScriptFile;

public class _626_ADarkTwilight extends Quest implements ScriptFile {
    //NPC
    private static final int Hierarch = 31517;
    //QuestItem
    private static int BloodOfSaint = 7169;

    @Override
    public void onLoad() {
    }

    @Override
    public void onReload() {
    }

    @Override
    public void onShutdown() {
    }

    public _626_ADarkTwilight() {
        super(true);
        addStartNpc(Hierarch);
        for (int npcId = 21520; npcId <= 21542; npcId++)
            addKillId(npcId);
        addQuestItem(BloodOfSaint);
    }

    @Override
    public String onEvent(String event, QuestState st, NpcInstance npc) {
        String htmltext = event;
        if (event.equalsIgnoreCase("dark_presbyter_q0626_0104.htm")) {
            st.setCond(1);
            st.setState(STARTED);
            st.playSound(SOUND_ACCEPT);
        } else if (event.equalsIgnoreCase("dark_presbyter_q0626_0201.htm")) {
            if (st.getQuestItemsCount(BloodOfSaint) < 300)
                htmltext = "dark_presbyter_q0626_0203.htm";
        } else if (event.equalsIgnoreCase("rew_exp")) {
            st.doTakeItems(BloodOfSaint, -1);
            st.addExpAndSp(RATE_QUESTS_REWARD * 162773, RATE_QUESTS_REWARD * 12500);
            htmltext = "dark_presbyter_q0626_0202.htm";
            st.exitCurrentQuest(true);
        } else if (event.equalsIgnoreCase("rew_adena")) {
            st.doTakeItems(BloodOfSaint, -1);
            st.doRewardItems(ADENA_ID, 100000);
            htmltext = "dark_presbyter_q0626_0202.htm";
            st.exitCurrentQuest(true);
        }
        return htmltext;
    }

    @Override
    public String onTalk(NpcInstance npc, QuestState st) {
        String htmltext = "noquest";
        int cond = st.getCond();
        int npcId = npc.getNpcId();
        if (npcId == Hierarch)
            if (cond == 0) {
                if (st.getPlayer().getLevel() < 60) {
                    htmltext = "dark_presbyter_q0626_0103.htm";
                    st.exitCurrentQuest(true);
                } else
                    htmltext = "dark_presbyter_q0626_0101.htm";
            } else if (cond == 1)
                htmltext = "dark_presbyter_q0626_0106.htm";
            else if (cond == 2)
                htmltext = "dark_presbyter_q0626_0105.htm";
        return htmltext;
    }

    @Override
    public String onKill(NpcInstance npc, QuestState st) {
        if (st.getCond() != 1)
            return null;

        if (st.doGiveOnKillItems(BloodOfSaint, 1, 1, 300, 70))
            st.setCond(2);

        return null;
    }
}
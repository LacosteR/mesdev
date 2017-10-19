package quests.quests6076;

import l2p.technical.util.Rnd;
import l2p.gameserver.model.instances.NpcInstance;
import l2p.gameserver.model.quest.Quest;
import l2p.gameserver.model.quest.QuestState;
import l2p.gameserver.scripts.ScriptFile;

public class _623_TheFinestFood extends Quest implements ScriptFile {
    public final int JEREMY = 31521;

    public static final int HOT_SPRINGS_BUFFALO = 21315;
    public static final int HOT_SPRINGS_FLAVA = 21316;
    public static final int HOT_SPRINGS_ANTELOPE = 21318;

    public static final int LEAF_OF_FLAVA = 7199;
    public static final int BUFFALO_MEAT = 7200;
    public static final int ANTELOPE_HORN = 7201;

    @Override
    public void onLoad() {
    }

    @Override
    public void onReload() {
    }

    @Override
    public void onShutdown() {
    }

    public _623_TheFinestFood() {
        super(true);

        addStartNpc(JEREMY);

        addTalkId(JEREMY);

        addKillId(HOT_SPRINGS_BUFFALO);
        addKillId(HOT_SPRINGS_FLAVA);
        addKillId(HOT_SPRINGS_ANTELOPE);

        addQuestItem(BUFFALO_MEAT);
        addQuestItem(LEAF_OF_FLAVA);
        addQuestItem(ANTELOPE_HORN);
    }

    @Override
    public String onEvent(String event, QuestState st, NpcInstance npc) {
        String htmltext = event;
        if (event.equalsIgnoreCase("quest_accept")) {
            htmltext = "jeremy_q0623_0104.htm";
            st.setCond(1);
            st.setState(STARTED);
            st.playSound(SOUND_ACCEPT);
        } else if (event.equalsIgnoreCase("623_3")) {
            htmltext = "jeremy_q0623_0201.htm";
            st.doTakeItems(LEAF_OF_FLAVA, -1);
            st.doTakeItems(BUFFALO_MEAT, -1);
            st.doTakeItems(ANTELOPE_HORN, -1);

            switch (Rnd.get(1, 4)) {
                case 1:
                    st.doGiveItems(ADENA_ID, 25000);
                    st.doGiveItems(6849, 1);
                    break;
                case 2:
                    st.doGiveItems(ADENA_ID, 65000);
                    st.doGiveItems(6847, 1);
                    break;
                case 3:
                    st.doGiveItems(ADENA_ID, 25000);
                    st.doGiveItems(6851, 1);
                    break;
                case 4:
                    st.doGiveItems(ADENA_ID, 73000);
                    st.addExpAndSp(RATE_QUESTS_REWARD * 230000, RATE_QUESTS_REWARD * 1820);
                    break;
            }

            st.playSound(SOUND_FINISH);
            st.exitCurrentQuest(true);
        }
        return htmltext;
    }

    @Override
    public String onTalk(NpcInstance npc, QuestState st) {
        String htmltext = "noquest";
        int npcId = npc.getNpcId();
        int id = st.getState();
        if (id == CREATED)
            st.setCond(0);
        if (summ(st) >= 300)
            st.setCond(2);
        int cond = st.getCond();
        if (npcId == JEREMY)
            if (cond == 0) {
                if (st.getPlayer().getLevel() >= 71)
                    htmltext = "jeremy_q0623_0101.htm";
                else {
                    htmltext = "jeremy_q0623_0103.htm";
                    st.exitCurrentQuest(true);
                }
            } else if (cond == 1 && summ(st) < 300)
                htmltext = "jeremy_q0623_0106.htm";
            else if (cond == 2 && summ(st) >= 300)
                htmltext = "jeremy_q0623_0105.htm";
        return htmltext;
    }

    @Override
    public String onKill(NpcInstance npc, QuestState st) {
        int cond = st.getCond();
        int npcId = npc.getNpcId();
        if (cond != 1)
            return null;

        if (npcId == HOT_SPRINGS_BUFFALO)
            st.doGiveOnKillItems(BUFFALO_MEAT, 1, 1, 100, 100);
        else if (npcId == HOT_SPRINGS_FLAVA)
            st.doGiveOnKillItems(LEAF_OF_FLAVA, 1, 1, 100, 100);
        else if (npcId == HOT_SPRINGS_ANTELOPE)
            st.doGiveOnKillItems(ANTELOPE_HORN, 1, 1, 100, 100);

        if (summ(st) >= 300)
            st.setCond(2);

        return null;
    }

    private long summ(QuestState st) {
        return st.getQuestItemsCount(LEAF_OF_FLAVA) + st.getQuestItemsCount(BUFFALO_MEAT) + st.getQuestItemsCount(ANTELOPE_HORN);
    }
}
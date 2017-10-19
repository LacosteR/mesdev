package quests.quests0040;

import l2p.gameserver.model.instances.NpcInstance;
import l2p.gameserver.model.quest.Quest;
import l2p.gameserver.model.quest.QuestState;
import l2p.gameserver.scripts.ScriptFile;

public class _644_GraveRobberAnnihilation extends Quest implements ScriptFile {
    //NPC
    private static final int KARUDA = 32017;
    //QuestItem
    private static int ORC_GOODS = 8088;

    @Override
    public void onLoad() {
    }

    @Override
    public void onReload() {
    }

    @Override
    public void onShutdown() {
    }

    public _644_GraveRobberAnnihilation() {
        super(true);
        addStartNpc(KARUDA);

        addKillId(22003);
        addKillId(22004);
        addKillId(22005);
        addKillId(22006);
        addKillId(22008);

        addQuestItem(ORC_GOODS);

    }

    @Override
    public String onEvent(String event, QuestState st, NpcInstance npc) {
        String htmltext = event;
        if (event.equalsIgnoreCase("karuda_q0644_0103.htm")) {
            st.doTakeItems(ORC_GOODS, -1);
            if (st.getPlayer().getLevel() < 20) {
                htmltext = "karuda_q0644_0102.htm";
                st.exitCurrentQuest(true);
            } else {
                st.setCond(1);
                st.setState(STARTED);
                st.playSound(SOUND_ACCEPT);
            }
        }
        if (st.getCond() == 2 && st.getQuestItemsCount(ORC_GOODS) >= 120) {
            if (event.equalsIgnoreCase("varn")) {
                st.doTakeItems(ORC_GOODS, -1);
                st.doGiveItems(1865, RATE_QUESTS_REWARD * 30);
                htmltext = null;
            } else if (event.equalsIgnoreCase("an_s")) {
                st.doTakeItems(ORC_GOODS, -1);
                st.doGiveItems(1867, RATE_QUESTS_REWARD * 40);
                htmltext = null;
            } else if (event.equalsIgnoreCase("an_b")) {
                st.doTakeItems(ORC_GOODS, -1);
                st.doGiveItems(1872, RATE_QUESTS_REWARD * 40);
                htmltext = null;
            } else if (event.equalsIgnoreCase("char")) {
                st.doTakeItems(ORC_GOODS, -1);
                st.doGiveItems(1871, RATE_QUESTS_REWARD * 30);
                htmltext = null;
            } else if (event.equalsIgnoreCase("coal")) {
                st.doTakeItems(ORC_GOODS, -1);
                st.doGiveItems(1870, RATE_QUESTS_REWARD * 30);
                htmltext = null;
            } else if (event.equalsIgnoreCase("i_o")) {
                st.doTakeItems(ORC_GOODS, -1);
                st.doGiveItems(1869, RATE_QUESTS_REWARD * 30);
                htmltext = null;
            }
            if (htmltext == null) {
                st.playSound(SOUND_FINISH);
                st.exitCurrentQuest(true);
            }
        }
        return htmltext;
    }

    @Override
    public String onTalk(NpcInstance npc, QuestState st) {
        String htmltext = "noquest";
        int cond = st.getCond();
        if (cond == 0)
            htmltext = "karuda_q0644_0101.htm";
        else if (cond == 1)
            htmltext = "karuda_q0644_0106.htm";
        else if (cond == 2)
            if (st.getQuestItemsCount(ORC_GOODS) >= 120)
                htmltext = "karuda_q0644_0105.htm";
            else {
                st.setCond(1);
                htmltext = "karuda_q0644_0106.htm";
            }
        return htmltext;
    }

    @Override
    public String onKill(NpcInstance npc, QuestState st) {
        if (st.doGiveOnKillItems(ORC_GOODS, 1, 1, 120, 90))
            st.setCond(2);

        return null;
    }
}
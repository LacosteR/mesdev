package quests.quests0040;

import l2p.gameserver.model.instances.NpcInstance;
import l2p.gameserver.model.quest.Quest;
import l2p.gameserver.model.quest.QuestState;
import l2p.gameserver.scripts.ScriptFile;

public class _153_DeliverGoods extends Quest implements ScriptFile {
    int DELIVERY_LIST = 1012;
    int HEAVY_WOOD_BOX = 1013;
    int CLOTH_BUNDLE = 1014;
    int CLAY_POT = 1015;
    int JACKSONS_RECEIPT = 1016;
    int SILVIAS_RECEIPT = 1017;
    int RANTS_RECEIPT = 1018;
    int RING_OF_KNOWLEDGE = 875;

    @Override
    public void onLoad() {
    }

    @Override
    public void onReload() {
    }

    @Override
    public void onShutdown() {
    }

    public _153_DeliverGoods() {
        super(false);

        addStartNpc(30041);

        addTalkId(30002);
        addTalkId(30003);
        addTalkId(30054);

        addQuestItem(new int[]{
                HEAVY_WOOD_BOX,
                CLOTH_BUNDLE,
                CLAY_POT,
                DELIVERY_LIST,
                JACKSONS_RECEIPT,
                SILVIAS_RECEIPT,
                RANTS_RECEIPT
        });
    }

    @Override
    public String onEvent(String event, QuestState st, NpcInstance npc) {
        String htmltext = event;
        if (event.equals("30041-04.htm")) {
            st.setCond(1);
            st.setState(STARTED);
            st.playSound(SOUND_ACCEPT);
            if (st.getQuestItemsCount(DELIVERY_LIST) == 0)
                st.doGiveItems(DELIVERY_LIST, 1);
            if (st.getQuestItemsCount(HEAVY_WOOD_BOX) == 0)
                st.doGiveItems(HEAVY_WOOD_BOX, 1);
            if (st.getQuestItemsCount(CLOTH_BUNDLE) == 0)
                st.doGiveItems(CLOTH_BUNDLE, 1);
            if (st.getQuestItemsCount(CLAY_POT) == 0)
                st.doGiveItems(CLAY_POT, 1);
            htmltext = "30041-04.htm";
        }
        return htmltext;
    }

    @Override
    public String onTalk(NpcInstance npc, QuestState st) {
        int npcId = npc.getNpcId();
        String htmltext = "noquest";
        int cond = st.getCond();
        if (npcId == 30041) {
            if (cond == 0) {
                if (st.getPlayer().getLevel() >= 2) {
                    htmltext = "30041-03.htm";
                    return htmltext;
                }
                htmltext = "30041-02.htm";
                st.exitCurrentQuest(true);
            } else if (cond == 1 && st.getQuestItemsCount(JACKSONS_RECEIPT) + st.getQuestItemsCount(SILVIAS_RECEIPT) + st.getQuestItemsCount(RANTS_RECEIPT) == 0)
                htmltext = "30041-05.htm";
            else if (cond == 1 && st.getQuestItemsCount(JACKSONS_RECEIPT) + st.getQuestItemsCount(SILVIAS_RECEIPT) + st.getQuestItemsCount(RANTS_RECEIPT) == 3) {
                st.doGiveItems(RING_OF_KNOWLEDGE, 2);
                st.doTakeItems(DELIVERY_LIST, -1);
                st.doTakeItems(JACKSONS_RECEIPT, -1);
                st.doTakeItems(SILVIAS_RECEIPT, -1);
                st.doTakeItems(RANTS_RECEIPT, -1);
                st.addExpAndSp(RATE_QUESTS_REWARD * 600, 0);
                st.playSound(SOUND_FINISH);
                htmltext = "30041-06.htm";
                st.exitCurrentQuest(false);
            }
        } else if (npcId == 30002) {
            if (cond == 1 && st.getQuestItemsCount(HEAVY_WOOD_BOX) == 1) {
                st.doTakeItems(HEAVY_WOOD_BOX, -1);
                if (st.getQuestItemsCount(JACKSONS_RECEIPT) == 0)
                    st.doGiveItems(JACKSONS_RECEIPT, 1);
                htmltext = "30002-01.htm";
            } else if (cond == 1 && st.getQuestItemsCount(JACKSONS_RECEIPT) > 0)
                htmltext = "30002-02.htm";
        } else if (npcId == 30003) {
            if (cond == 1 && st.getQuestItemsCount(CLOTH_BUNDLE) == 1) {
                st.doTakeItems(CLOTH_BUNDLE, -1);
                if (st.getQuestItemsCount(SILVIAS_RECEIPT) == 0) {
                    st.doGiveItems(SILVIAS_RECEIPT, 1);
                    if (st.getPlayer().getPcClass().isMage())
                        st.doGiveItems(2509, 3);
                    else
                        st.doGiveItems(1835, 6);
                }
                htmltext = "30003-01.htm";
            } else if (cond == 1 && st.getQuestItemsCount(SILVIAS_RECEIPT) > 0)
                htmltext = "30003-02.htm";
        } else if (npcId == 30054)
            if (cond == 1 && st.getQuestItemsCount(CLAY_POT) == 1) {
                st.doTakeItems(CLAY_POT, -1);
                if (st.getQuestItemsCount(RANTS_RECEIPT) == 0)
                    st.doGiveItems(RANTS_RECEIPT, 1);
                htmltext = "30054-01.htm";
            } else if (cond == 1 && st.getQuestItemsCount(RANTS_RECEIPT) > 0)
                htmltext = "30054-02.htm";
        return htmltext;
    }
}
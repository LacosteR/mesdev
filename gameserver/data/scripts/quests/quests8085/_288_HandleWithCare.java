package quests.quests8085;

import l2p.technical.util.Rnd;
import l2p.gameserver.model.instances.NpcInstance;
import l2p.gameserver.model.quest.Quest;
import l2p.gameserver.model.quest.QuestState;
import l2p.gameserver.scripts.ScriptFile;

public class _288_HandleWithCare extends Quest implements ScriptFile {
    private static final int Ankumi = 32741;
    private static final int MiddleGradeLizardScale = 15498;
    private static final int HighestGradeLizardScale = 15497;

    public _288_HandleWithCare() {
        super(true);
        addStartNpc(Ankumi);
        addQuestItem(MiddleGradeLizardScale, HighestGradeLizardScale);
    }

    @Override
    public String onEvent(String event, QuestState st, NpcInstance npc) {
        String htmltext = event;
        if (event.equalsIgnoreCase("ankumi_q288_03.htm")) {
            st.setState(STARTED);
            st.setCond(1);
            st.playSound(SOUND_ACCEPT);
        } else if (event.equalsIgnoreCase("request_reward")) {
            if (st.getCond() == 2 && st.getQuestItemsCount(MiddleGradeLizardScale) >= 1) {
                st.doTakeItems(MiddleGradeLizardScale, -1);
                switch (Rnd.get(1, 6)) {
                    case 1:
                        st.doGiveItems(959, 1);
                        break;
                    case 2:
                        st.doGiveItems(960, 1);
                        break;
                    case 3:
                        st.doGiveItems(960, 2);
                        break;
                    case 4:
                        st.doGiveItems(960, 3);
                        break;
                    case 5:
                        st.doGiveItems(9557, 1);
                        break;
                    case 6:
                        st.doGiveItems(9557, 2);
                        break;
                }
                htmltext = "ankumi_q288_06.htm";
                st.exitCurrentQuest(true);
            } else if (st.getCond() == 3 && st.getQuestItemsCount(HighestGradeLizardScale) >= 1) {
                st.doTakeItems(HighestGradeLizardScale, -1);
                switch (Rnd.get(1, 4)) {
                    case 1:
                        st.doGiveItems(959, 1);
                        st.doGiveItems(9557, 1);
                        break;
                    case 2:
                        st.doGiveItems(960, 1);
                        st.doGiveItems(9557, 1);
                        break;
                    case 3:
                        st.doGiveItems(960, 2);
                        st.doGiveItems(9557, 1);
                        break;
                    case 4:
                        st.doGiveItems(960, 3);
                        st.doGiveItems(9557, 1);
                        break;
                }
                htmltext = "ankumi_q288_06.htm";
                st.exitCurrentQuest(true);
            } else {
                htmltext = "ankumi_q288_07.htm";
                st.exitCurrentQuest(true);
            }
        }
        return htmltext;
    }

    @Override
    public String onTalk(NpcInstance npc, QuestState st) {
        String htmltext = "noquest";
        int npcId = npc.getNpcId();
        int cond = st.getCond();
        if (npcId == Ankumi) {
            if (cond == 0) {
                if (st.getPlayer().getLevel() >= 82)
                    htmltext = "ankumi_q288_01.htm";
                else {
                    htmltext = "ankumi_q288_00.htm";
                    st.exitCurrentQuest(true);
                }
            } else if (cond == 1)
                htmltext = "ankumi_q288_04.htm";
            else if (cond == 2 || cond == 3)
                htmltext = "ankumi_q288_05.htm";
        }
        return htmltext;
    }

    @Override
    public void onLoad() {
    }

    @Override
    public void onReload() {
    }

    @Override
    public void onShutdown() {
    }
}
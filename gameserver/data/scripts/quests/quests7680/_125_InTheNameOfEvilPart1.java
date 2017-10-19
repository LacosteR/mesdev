package quests.quests7680;

import l2p.gameserver.model.instances.NpcInstance;
import l2p.gameserver.model.quest.Quest;
import l2p.gameserver.model.quest.QuestState;
import l2p.gameserver.scripts.ScriptFile;
import quests.quests6076._124_MeetingTheElroki;

public class _125_InTheNameOfEvilPart1 extends Quest implements ScriptFile {
    private int Mushika = 32114;
    private int Karakawei = 32117;
    private int UluKaimu = 32119;
    private int BaluKaimu = 32120;
    private int ChutaKaimu = 32121;
    private int OrClaw = 8779;
    private int DienBone = 8780;

    public void onLoad() {
    }

    public void onReload() {
    }

    public void onShutdown() {
    }

    public _125_InTheNameOfEvilPart1() {
        super(false);

        addStartNpc(Mushika);
        addTalkId(Karakawei);
        addTalkId(UluKaimu);
        addTalkId(BaluKaimu);
        addTalkId(ChutaKaimu);
        addQuestItem(OrClaw, DienBone);
        addKillId(22742, 22743, 22744, 22745);
    }

    @Override
    public String onEvent(String event, QuestState st, NpcInstance npc) {
        String htmltext = event;
        if (event.equalsIgnoreCase("32114-05.htm")) {
            st.setCond(1);
            st.setState(STARTED);
            st.playSound(SOUND_ACCEPT);
        } else if (event.equalsIgnoreCase("32114-07.htm")) {
            st.setCond(2);
            st.playSound(SOUND_MIDDLE);
        } else if (event.equalsIgnoreCase("32117-08.htm")) {
            st.setCond(3);
            st.playSound(SOUND_MIDDLE);
        } else if (event.equalsIgnoreCase("32117-13.htm")) {
            st.setCond(5);
            st.playSound(SOUND_MIDDLE);
        } else if (event.equalsIgnoreCase("stat1false"))
            htmltext = "32119-2.htm";
        else if (event.equalsIgnoreCase("stat1true")) {
            st.setCond(6);
            htmltext = "32119-1.htm";
        } else if (event.equalsIgnoreCase("stat2false"))
            htmltext = "32120-2.htm";
        else if (event.equalsIgnoreCase("stat2true")) {
            st.setCond(7);
            htmltext = "32120-1.htm";
        } else if (event.equalsIgnoreCase("stat3false"))
            htmltext = "32121-2.htm";
        else if (event.equalsIgnoreCase("stat3true")) {
            st.doGiveItems(8781, 1);
            st.setCond(8);
            htmltext = "32121-1.htm";
        }
        return htmltext;
    }

    @Override
    public String onTalk(NpcInstance npc, QuestState st) {
        String htmltext = "noquest";
        int npcId = npc.getNpcId();
        int cond = st.getCond();
        if (npcId == Mushika) {
            if (cond == 0) {
                QuestState meetQuest = st.getPlayer().getQuestState(_124_MeetingTheElroki.class);
                if (st.getPlayer().getLevel() > 76 && meetQuest != null && meetQuest.isCompleted())
                    htmltext = "32114.htm";
                else {
                    htmltext = "32114-0.htm";
                    st.exitCurrentQuest(true);
                }
            } else if (cond == 1)
                htmltext = "32114-05.htm";
            else if (cond == 8) {
                htmltext = "32114-08.htm";
                st.addExpAndSp(RATE_QUESTS_REWARD * 1015973, RATE_QUESTS_REWARD * 102802);
                st.doRewardItems(ADENA_ID, 460483);
                st.doGiveItems(729, 1);
                st.playSound(SOUND_FINISH);
                st.setState(COMPLETED);
                st.exitCurrentQuest(false);
            }
        } else if (npcId == Karakawei) {
            if (cond == 2)
                htmltext = "32117.htm";
            else if (cond == 3)
                htmltext = "32117-09.htm";
            else if (cond == 4) {
                st.doTakeItems(DienBone, -1);
                st.doTakeItems(OrClaw, -1);
                htmltext = "32117-1.htm";
            }
        } else if (npcId == UluKaimu) {
            if (cond == 5)
                htmltext = "32119.htm";
        } else if (npcId == BaluKaimu) {
            if (cond == 6)
                htmltext = "32120.htm";
        } else if (npcId == ChutaKaimu)
            if (cond == 7)
                htmltext = "32121.htm";
        return htmltext;
    }

    @Override
    public String onKill(NpcInstance npc, QuestState st) {
        int npcId = npc.getNpcId();

        if (st.getCond() == 3) {
            if (npcId == 22744 || npcId == 22742)
                st.doGiveOnKillItems(OrClaw, 1, 1, 2, 10);

            if (npcId == 22743 || npcId == 22745)
                st.doGiveOnKillItems(DienBone, 1, 1, 2, 10);

            if (st.getQuestItemsCount(DienBone) >= 2 && st.getQuestItemsCount(OrClaw) >= 2)
                st.setCond(4);
        }
        return null;
    }
}
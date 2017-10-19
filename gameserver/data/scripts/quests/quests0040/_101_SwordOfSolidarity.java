package quests.quests0040;

import l2p.gameserver.model.base.Race;
import l2p.gameserver.model.instances.NpcInstance;
import l2p.gameserver.model.quest.Quest;
import l2p.gameserver.model.quest.QuestState;
import l2p.gameserver.network.serverpackets.ExShowScreenMessage;
import l2p.gameserver.network.serverpackets.ExShowScreenMessage.ScreenMessageAlign;
import l2p.gameserver.scripts.ScriptFile;

public class _101_SwordOfSolidarity extends Quest implements ScriptFile {
    int ROIENS_LETTER = 796;
    int HOWTOGO_RUINS = 937;
    int BROKEN_SWORD_HANDLE = 739;
    int BROKEN_BLADE_BOTTOM = 740;
    int BROKEN_BLADE_TOP = 741;
    int ALLTRANS_NOTE = 742;
    int SWORD_OF_SOLIDARITY = 738;

    @Override
    public void onLoad() {
    }

    @Override
    public void onReload() {
    }

    @Override
    public void onShutdown() {
    }

    public _101_SwordOfSolidarity() {
        super(false);

        addStartNpc(30008);
        addTalkId(30283);

        addKillId(20361);
        addKillId(20362);

        addQuestItem(ALLTRANS_NOTE, HOWTOGO_RUINS, BROKEN_BLADE_TOP, BROKEN_BLADE_BOTTOM, ROIENS_LETTER, BROKEN_SWORD_HANDLE);
    }

    @Override
    public String onEvent(String event, QuestState st, NpcInstance npc) {
        String htmltext = event;
        if (event.equalsIgnoreCase("roien_q0101_04.htm")) {
            st.setCond(1);
            st.setState(STARTED);
            st.playSound(SOUND_ACCEPT);
            st.doGiveItems(ROIENS_LETTER, 1);
        } else if (event.equalsIgnoreCase("blacksmith_alltran_q0101_02.htm")) {
            st.setCond(2);
            st.doTakeItems(ROIENS_LETTER, -1);
            st.doGiveItems(HOWTOGO_RUINS, 1);
        } else if (event.equalsIgnoreCase("blacksmith_alltran_q0101_07.htm")) {
            st.doTakeItems(BROKEN_SWORD_HANDLE, -1);

            st.doGiveItems(SWORD_OF_SOLIDARITY, 1);
            st.doRewardItems(ADENA_ID, 10981);
            st.getPlayer().addExpAndSp(RATE_QUESTS_REWARD * 25747, RATE_QUESTS_REWARD * 2171);
            if (st.getPlayer().getPcClass().getLevel() == 1 && !st.getPlayer().getVarB("p1q3")) {
                st.getPlayer().setVar("p1q3", "1", -1); // flag for helper
                st.getPlayer().sendPacket(new ExShowScreenMessage("Now go find the Newbie Guide.", 5000, ScreenMessageAlign.TOP_CENTER, true));
                st.doGiveItems(1060, 100); // healing potion
                for (int item = 4412; item <= 4417; item++)
                    st.doGiveItems(item, 10); // echo cry
                if (st.getPlayer().getPcClass().isMage()) {
                    st.playTutorialVoice("tutorial_voice_027");
                    st.doGiveItems(5790, 3000); // newbie sps
                } else {
                    st.playTutorialVoice("tutorial_voice_026");
                    st.doGiveItems(5789, 6000); // newbie ss
                }
            }

            st.exitCurrentQuest(true);
            st.playSound(SOUND_FINISH);
        }
        return htmltext;
    }

    @Override
    public String onTalk(NpcInstance npc, QuestState st) {
        String htmltext = "noquest";
        int npcId = npc.getNpcId();
        int cond = st.getCond();
        if (npcId == 30008) {
            if (cond == 0) {

                if (st.getPlayer().getRace() != Race.human)
                    htmltext = "roien_q0101_00.htm";
                else if (st.getPlayer().getLevel() >= 9) {
                    htmltext = "roien_q0101_02.htm";
                    return htmltext;
                } else {
                    htmltext = "roien_q0101_08.htm";
                    st.exitCurrentQuest(true);
                }

            } else if (cond == 1 && st.getQuestItemsCount(ROIENS_LETTER) >= 1)
                htmltext = "roien_q0101_05.htm";
            else if (cond >= 2 && st.getQuestItemsCount(ROIENS_LETTER) == 0 && st.getQuestItemsCount(ALLTRANS_NOTE) == 0) {
                if (st.getQuestItemsCount(BROKEN_BLADE_TOP) > 0 && st.getQuestItemsCount(BROKEN_BLADE_BOTTOM) > 0)
                    htmltext = "roien_q0101_12.htm";
                if (st.getQuestItemsCount(BROKEN_BLADE_TOP) + st.getQuestItemsCount(BROKEN_BLADE_BOTTOM) <= 1)
                    htmltext = "roien_q0101_11.htm";
                if (st.getQuestItemsCount(BROKEN_SWORD_HANDLE) > 0)
                    htmltext = "roien_q0101_07.htm";
                if (st.getQuestItemsCount(HOWTOGO_RUINS) >= 1)
                    htmltext = "roien_q0101_10.htm";
            } else if (cond == 4 && st.getQuestItemsCount(ALLTRANS_NOTE) > 0) {
                htmltext = "roien_q0101_06.htm";
                st.setCond(5);
                st.doTakeItems(ALLTRANS_NOTE, -1);
                st.doGiveItems(BROKEN_SWORD_HANDLE, 1);
            }
        } else if (npcId == 30283)
            if (cond == 1 && st.getQuestItemsCount(ROIENS_LETTER) > 0)
                htmltext = "blacksmith_alltran_q0101_01.htm";
            else if (cond >= 2 && st.getQuestItemsCount(HOWTOGO_RUINS) >= 1) {
                if (st.getQuestItemsCount(BROKEN_BLADE_TOP) + st.getQuestItemsCount(BROKEN_BLADE_BOTTOM) == 1)
                    htmltext = "blacksmith_alltran_q0101_08.htm";
                else if (st.getQuestItemsCount(BROKEN_BLADE_TOP) + st.getQuestItemsCount(BROKEN_BLADE_BOTTOM) == 0)
                    htmltext = "blacksmith_alltran_q0101_03.htm";
                else if (st.getQuestItemsCount(BROKEN_BLADE_TOP) > 0 && st.getQuestItemsCount(BROKEN_BLADE_BOTTOM) > 0) {
                    htmltext = "blacksmith_alltran_q0101_04.htm";
                    st.setCond(4);
                    st.doTakeItems(HOWTOGO_RUINS, -1);
                    st.doTakeItems(BROKEN_BLADE_TOP, -1);
                    st.doTakeItems(BROKEN_BLADE_BOTTOM, -1);
                    st.doGiveItems(ALLTRANS_NOTE, 1);
                } else if (cond == 4 && st.getQuestItemsCount(ALLTRANS_NOTE) > 0)
                    htmltext = "blacksmith_alltran_q0101_05.htm";
            } else if (cond == 5 && st.getQuestItemsCount(BROKEN_SWORD_HANDLE) > 0)
                htmltext = "blacksmith_alltran_q0101_06.htm";
        return htmltext;
    }

    @Override
    public String onKill(NpcInstance npc, QuestState st) {
        int npcId = npc.getNpcId();

        if (npcId != 20361 && npcId != 20362)
            return null;

        if (st.getQuestItemsCount(HOWTOGO_RUINS) <= 0)
            return null;

        st.doGiveOnKillItems(BROKEN_BLADE_TOP, 1, 1, 1, 60);
        st.doGiveOnKillItems(BROKEN_BLADE_BOTTOM, 1, 1, 1, 60);

        if (st.getQuestItemsCount(BROKEN_BLADE_TOP) > 0 && st.getQuestItemsCount(BROKEN_BLADE_BOTTOM) > 0)
            st.setCond(3);

        return null;
    }
}
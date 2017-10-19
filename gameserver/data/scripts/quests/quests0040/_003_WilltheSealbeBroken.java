package quests.quests0040;

// version = Unknown

import l2p.gameserver.model.base.Race;
import l2p.gameserver.model.instances.NpcInstance;
import l2p.gameserver.model.quest.Quest;
import l2p.gameserver.model.quest.QuestState;
import l2p.gameserver.scripts.ScriptFile;

/**
 * Рейты учтены
 */
public class _003_WilltheSealbeBroken extends Quest implements ScriptFile {
    int StartNpc = 30141;
    int[] Monster = {
            20031,
            20041,
            20046,
            20048,
            20052,
            20057
    };

    int OnyxBeastEye = 1081;
    int TaintStone = 1082;
    int SuccubusBlood = 1083;

    @Override
    public void onLoad() {
    }

    @Override
    public void onReload() {
    }

    @Override
    public void onShutdown() {
    }

    public _003_WilltheSealbeBroken() {
        super(false);
        addStartNpc(StartNpc);

        for (int npcId : Monster)
            addKillId(npcId);

        addQuestItem(new int[]{
                OnyxBeastEye,
                TaintStone,
                SuccubusBlood
        });
    }

    @Override
    public String onEvent(String event, QuestState st, NpcInstance npc) {
        String htmltext = event;
        if (event.equalsIgnoreCase("quest_accept")) {
            htmltext = "redry_q0003_03.htm";
            st.setCond(1);
            st.setState(STARTED);
            st.playSound(SOUND_ACCEPT);
        }
        return htmltext;
    }

    @Override
    public String onTalk(NpcInstance npc, QuestState st) {
        String htmltext = "noquest";
        int id = st.getState();
        if (id == CREATED)
            if (st.getPlayer().getRace() != Race.darkelf) {
                htmltext = "redry_q0003_00.htm";
                st.exitCurrentQuest(true);
            } else if (st.getPlayer().getLevel() >= 16) {
                htmltext = "redry_q0003_02.htm";
                return htmltext;
            } else {
                htmltext = "redry_q0003_01.htm";
                st.exitCurrentQuest(true);
            }
        else if (id == STARTED)
            if (st.getQuestItemsCount(OnyxBeastEye) > 0 && st.getQuestItemsCount(TaintStone) > 0 && st.getQuestItemsCount(SuccubusBlood) > 0) {
                htmltext = "redry_q0003_06.htm";
                st.doTakeItems(OnyxBeastEye, -1);
                st.doTakeItems(TaintStone, -1);
                st.doTakeItems(SuccubusBlood, -1);
                st.doRewardItems(956, 1);
                st.playSound(SOUND_FINISH);
                st.exitCurrentQuest(false);
            } else
                htmltext = "redry_q0003_04.htm";
        return htmltext;
    }

    @Override
    public String onKill(NpcInstance npc, QuestState st) {
        int npcId = npc.getNpcId();
        int id = st.getState();
        if (id == STARTED) {
            if (npcId == Monster[0] && st.getQuestItemsCount(OnyxBeastEye) == 0)
                st.doGiveOnKillItems(OnyxBeastEye, 1, 1, 1, 100);
            else if ((npcId == Monster[1] || npcId == Monster[2]) && st.getQuestItemsCount(TaintStone) == 0)
                st.doGiveOnKillItems(TaintStone, 1, 1, 1, 100);
            else if ((npcId == Monster[3] || npcId == Monster[4] || npcId == Monster[5]) && st.getQuestItemsCount(SuccubusBlood) == 0)
                st.doGiveOnKillItems(SuccubusBlood, 1, 1, 1, 100);

            if (st.getQuestItemsCount(OnyxBeastEye) > 0 && st.getQuestItemsCount(TaintStone) > 0 && st.getQuestItemsCount(SuccubusBlood) > 0) {
                st.setCond(2);
                st.playSound(SOUND_MIDDLE);
            }
        }
        return null;
    }
}
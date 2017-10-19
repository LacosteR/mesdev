package quests.quests8085;

import l2p.gameserver.model.instances.NpcInstance;
import l2p.gameserver.model.quest.Quest;
import l2p.gameserver.model.quest.QuestState;
import l2p.gameserver.scripts.ScriptFile;

/**
 * @author VAVAN
 * @corrected n0nam3
 */

public class _310_OnlyWhatRemains extends Quest implements ScriptFile {
    @Override
    public void onLoad() {
    }

    @Override
    public void onReload() {
    }

    @Override
    public void onShutdown() {
    }

    // NPC's
    private static final int KINTAIJIN = 32640;
    // MOBS's
    private static final int[] MOBS = {
            22617,
            22624,
            22625,
            22626
    };
    // ITEMS's
    private static final int DIRTYBEAD = 14880;
    private static final int ACCELERATOR = 14832;
    private static final int JEWEL = 14835;

    public _310_OnlyWhatRemains() {
        super(false);
        addStartNpc(KINTAIJIN);
        addTalkId(KINTAIJIN);
        addKillId(MOBS);
        addQuestItem(DIRTYBEAD);
    }

    @Override
    public String onEvent(String event, QuestState st, NpcInstance npc) {
        String htmltext = event;
        if (event.equalsIgnoreCase("32640-3.htm")) {
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
        int cond = st.getCond();
        if (id == COMPLETED)
            htmltext = "32640-10.htm";
        else if (id == CREATED) {
            QuestState ImTheOnlyOneYouCanTrust = st.getPlayer().getQuestState(_240_ImTheOnlyOneYouCanTrust.class);
            if (st.getPlayer().getLevel() >= 81 && ImTheOnlyOneYouCanTrust != null && ImTheOnlyOneYouCanTrust.isCompleted())
                htmltext = "32640-1.htm";
            else {
                htmltext = "32640-0.htm";
                st.exitCurrentQuest(true);
            }
        } else if (cond == 1)
            htmltext = "32640-8.htm";
        else if (cond == 2) {
            st.doTakeItems(DIRTYBEAD, 500);
            st.doGiveItems(ACCELERATOR, 1);
            st.doGiveItems(JEWEL, 1);
            st.exitCurrentQuest(true);
            st.playSound(SOUND_FINISH);
            htmltext = "32640-9.htm";
        }
        return htmltext;
    }

    @Override
    public String onKill(NpcInstance npc, QuestState st) {
        if (st.getCond() != 1)
            return null;

        if (st.doGiveOnKillItems(DIRTYBEAD, 1, 1, 500, 100))
            st.setCond(2);

        return null;
    }
}
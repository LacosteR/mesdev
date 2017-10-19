package quests.quests0040;

import l2p.gameserver.model.instances.NpcInstance;
import l2p.gameserver.model.quest.Quest;
import l2p.gameserver.model.quest.QuestState;
import l2p.gameserver.scripts.ScriptFile;

public class _157_RecoverSmuggled extends Quest implements ScriptFile {
    int ADAMANTITE_ORE_ID = 1024;
    int BUCKLER = 20;

    @Override
    public void onLoad() {
    }

    @Override
    public void onReload() {
    }

    @Override
    public void onShutdown() {
    }

    public _157_RecoverSmuggled() {
        super(false);

        addStartNpc(30005);

        addTalkId(30005);

        addKillId(20121);

        addQuestItem(ADAMANTITE_ORE_ID);
    }

    @Override
    public String onEvent(String event, QuestState st, NpcInstance npc) {
        String htmltext = event;
        if (event.equals("1")) {
            st.set("id", "0");
            st.setCond(1);
            st.setState(STARTED);
            st.playSound(SOUND_ACCEPT);
            htmltext = "30005-05.htm";
        } else if (event.equals("157_1")) {
            htmltext = "30005-04.htm";
            return htmltext;
        }
        return htmltext;
    }

    @Override
    public String onTalk(NpcInstance npc, QuestState st) {
        int npcId = npc.getNpcId();
        String htmltext = "noquest";
        int id = st.getState();
        if (id == CREATED) {
            st.setCond(0);
            st.set("id", "0");
        }
        if (npcId == 30005 && st.getCond() == 0) {
            if (st.getCond() < 15) {
                if (st.getPlayer().getLevel() >= 5)
                    htmltext = "30005-03.htm";
                else {
                    htmltext = "30005-02.htm";
                    st.exitCurrentQuest(true);
                }
            } else {
                htmltext = "30005-02.htm";
                st.exitCurrentQuest(true);
            }
        } else if (npcId == 30005 && st.getCond() != 0 && st.getQuestItemsCount(ADAMANTITE_ORE_ID) < 20)
            htmltext = "30005-06.htm";
        else if (npcId == 30005 && st.getCond() != 0 && st.getQuestItemsCount(ADAMANTITE_ORE_ID) >= 20) {
            st.doTakeItems(ADAMANTITE_ORE_ID, st.getQuestItemsCount(ADAMANTITE_ORE_ID));
            st.playSound(SOUND_FINISH);
            st.doGiveItems(BUCKLER, 1);
            htmltext = "30005-07.htm";
            st.exitCurrentQuest(false);
        }
        return htmltext;
    }

    @Override
    public String onKill(NpcInstance npc, QuestState st) {
        int npcId = npc.getNpcId();
        if (npcId != 20121)
            return null;

        st.set("id", "0");
        if (st.getCond() != 0)
            st.doGiveOnKillItems(ADAMANTITE_ORE_ID, 1, 1, 20, 14);

        return null;
    }
}
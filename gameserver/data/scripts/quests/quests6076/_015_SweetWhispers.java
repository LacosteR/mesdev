package quests.quests6076;

import l2p.gameserver.model.instances.NpcInstance;
import l2p.gameserver.model.quest.Quest;
import l2p.gameserver.model.quest.QuestState;
import l2p.gameserver.scripts.ScriptFile;

public class _015_SweetWhispers extends Quest implements ScriptFile {
    @Override
    public void onLoad() {
    }

    @Override
    public void onReload() {
    }

    @Override
    public void onShutdown() {
    }

    public _015_SweetWhispers() {
        super(false);

        addStartNpc(31302);

        addTalkId(31517);
        addTalkId(31518);
    }

    @Override
    public String onEvent(String event, QuestState st, NpcInstance npc) {
        String htmltext = event;
        if (event.equalsIgnoreCase("trader_vladimir_q0015_0104.htm")) {
            st.setCond(1);
            st.setState(STARTED);
            st.playSound(SOUND_ACCEPT);
        } else if (event.equalsIgnoreCase("dark_necromancer_q0015_0201.htm"))
            st.setCond(2);
        else if (event.equalsIgnoreCase("dark_presbyter_q0015_0301.htm")) {
            st.addExpAndSp(RATE_QUESTS_REWARD * 350531, RATE_QUESTS_REWARD * 28204);
            st.playSound(SOUND_FINISH);
            st.exitCurrentQuest(false);
        }
        return htmltext;
    }

    @Override
    public String onTalk(NpcInstance npc, QuestState st) {
        String htmltext = "noquest";
        int npcId = npc.getNpcId();
        int cond = st.getCond();
        if (npcId == 31302) {
            if (cond == 0)
                if (st.getPlayer().getLevel() >= 60)
                    htmltext = "trader_vladimir_q0015_0101.htm";
                else {
                    htmltext = "trader_vladimir_q0015_0103.htm";
                    st.exitCurrentQuest(true);
                }
            else if (cond >= 1)
                htmltext = "trader_vladimir_q0015_0105.htm";
        } else if (npcId == 31518) {
            if (cond == 1)
                htmltext = "dark_necromancer_q0015_0101.htm";
            else if (cond == 2)
                htmltext = "dark_necromancer_q0015_0202.htm";
        } else if (npcId == 31517)
            if (cond == 2)
                htmltext = "dark_presbyter_q0015_0201.htm";
        return htmltext;
    }

    @Override
    public String onKill(NpcInstance npc, QuestState st) {
        return null;
    }
}
package quests.quests8085;

import l2p.technical.util.Rnd;
import l2p.gameserver.config.ConfigQuests;
import l2p.gameserver.model.instances.NpcInstance;
import l2p.gameserver.model.quest.Quest;
import l2p.gameserver.model.quest.QuestState;
import l2p.gameserver.scripts.ScriptFile;

public class _10503_CapeEmbroideredSoulThree extends Quest implements ScriptFile {
    // NPC's
    private static final int OLF_ADAMS = 32612;
    // Mob's
    private static final int FRINTEZZA = 29047;
    // Quest Item's
    private static final int SOUL_FRINTEZZA = 21724;
    // Item's
    private static final int CLOAK_FRINTEZZA = 21721;

    @Override
    public void onLoad() {
    }

    @Override
    public void onReload() {
    }

    @Override
    public void onShutdown() {
    }

    public _10503_CapeEmbroideredSoulThree() {
        super(PARTY_ALL);
        addStartNpc(OLF_ADAMS);
        addTalkId(OLF_ADAMS);
        addKillId(FRINTEZZA);
        addQuestItem(SOUL_FRINTEZZA);
    }

    @Override
    public String onEvent(String event, QuestState st, NpcInstance npc) {
        if (event.equalsIgnoreCase("olf_adams_q10503_02.htm")) {
            st.setCond(1);
            st.setState(STARTED);
            st.playSound(SOUND_ACCEPT);
        }
        return event;
    }

    @Override
    public String onTalk(NpcInstance npc, QuestState st) {
        String htmltext = "noquest";
        int cond = st.getCond();
        if (cond == 0) {
            if (st.getPlayer().getLevel() >= 80)
                htmltext = "olf_adams_q10503_01.htm";
            else {
                htmltext = "olf_adams_q10503_00.htm";
                st.exitCurrentQuest(true);
            }
        } else if (cond == 1)
            htmltext = "olf_adams_q10503_03.htm";
        else if (cond == 2)
            if (st.getQuestItemsCount(SOUL_FRINTEZZA) < 20) {
                st.setCond(1);
                htmltext = "olf_adams_q10503_03.htm";
            } else {
                st.doTakeItems(SOUL_FRINTEZZA, -1);
                st.doGiveItems(CLOAK_FRINTEZZA, 1);
                st.playSound(SOUND_FINISH);
                htmltext = "olf_adams_q10503_04.htm";
                st.exitCurrentQuest(false);
            }
        return htmltext;
    }

    @Override
    public String onKill(NpcInstance npc, QuestState st) {
        int npcId = npc.getNpcId();
        int cond = st.getCond();
        if (cond == 1 && npcId == FRINTEZZA) {
            if (st.getQuestItemsCount(SOUL_FRINTEZZA) < 20)
                st.doGiveItems(SOUL_FRINTEZZA, Rnd.get(ConfigQuests.CLOAK_QUEST_RATE, ConfigQuests.CLOAK_QUEST_RATE * 3));
            if (st.getQuestItemsCount(SOUL_FRINTEZZA) >= 20) {
                st.setCond(2);
                st.playSound(SOUND_MIDDLE);
            }
        }
        return null;
    }
}
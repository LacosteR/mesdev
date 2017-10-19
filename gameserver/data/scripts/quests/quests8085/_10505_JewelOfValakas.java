package quests.quests8085;

import l2p.gameserver.model.instances.NpcInstance;
import l2p.gameserver.model.quest.Quest;
import l2p.gameserver.model.quest.QuestState;
import l2p.gameserver.scripts.ScriptFile;

/**
 * @author Bonux
 *         http://l2wiki.info/Драгоценный_камень_дракона_огня
 */
public class _10505_JewelOfValakas extends Quest implements ScriptFile {
    //NPC's
    private static final int KLEIN = 31540;
    private static final int VALAKAS = 29028;
    //Item's
    private static final int EMPTY_CRYSTAL = 21906;
    private static final int FILLED_CRYSTAL_VALAKAS = 21908;
    private static final int VACUALITE_FLOATING_STONE = 7267;
    private static final int JEWEL_OF_VALAKAS = 21896;

    public _10505_JewelOfValakas() {
        super(PARTY_ALL);
        addStartNpc(KLEIN);
        addQuestItem(EMPTY_CRYSTAL, FILLED_CRYSTAL_VALAKAS);
        addKillId(VALAKAS);
    }

    @Override
    public String onEvent(String event, QuestState st, NpcInstance npc) {
        String htmltext = event;
        if (event.equalsIgnoreCase("valakas_watchman_klein_q10505_04.htm")) {
            st.setState(STARTED);
            st.setCond(1);
            st.playSound(SOUND_ACCEPT);
            st.doGiveItems(EMPTY_CRYSTAL, 1);
        }
        return htmltext;
    }

    @Override
    public String onTalk(NpcInstance npc, QuestState st) {
        String htmltext = "noquest";
        int npcId = npc.getNpcId();
        int cond = st.getCond();
        if (npcId == KLEIN) {
            if (cond == 0) {
                if (st.getPlayer().getLevel() < 84)
                    htmltext = "valakas_watchman_klein_q10505_00.htm";
                else if (st.getQuestItemsCount(VACUALITE_FLOATING_STONE) < 1)
                    htmltext = "valakas_watchman_klein_q10505_00a.htm";
                else if (st.isNowAvailable())
                    htmltext = "valakas_watchman_klein_q10505_01.htm";
                else
                    htmltext = "valakas_watchman_klein_q10505_09.htm";
            } else if (cond == 1) {
                if (st.getQuestItemsCount(EMPTY_CRYSTAL) < 1) {
                    htmltext = "valakas_watchman_klein_q10505_08.htm";
                    st.doGiveItems(EMPTY_CRYSTAL, 1);
                } else
                    htmltext = "valakas_watchman_klein_q10505_05.htm";
            } else if (cond == 2) {
                if (st.getQuestItemsCount(FILLED_CRYSTAL_VALAKAS) >= 1) {
                    htmltext = "valakas_watchman_klein_q10505_07.htm";
                    st.doTakeItems(FILLED_CRYSTAL_VALAKAS);
                    st.doGiveItems(JEWEL_OF_VALAKAS, 1);
                    st.playSound(SOUND_FINISH);
                    st.setState(COMPLETED);
                    st.exitCurrentQuest(false);
                } else
                    htmltext = "valakas_watchman_klein_q10505_06.htm";
            }
        }
        return htmltext;
    }

    @Override
    public String onKill(NpcInstance npc, QuestState st) {
        int npcId = npc.getNpcId();
        int cond = st.getCond();
        if (cond == 1 && npcId == VALAKAS) {
            st.doTakeItems(EMPTY_CRYSTAL);
            st.doGiveItems(FILLED_CRYSTAL_VALAKAS, 1);
            st.setCond(2);
        }
        return null;
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
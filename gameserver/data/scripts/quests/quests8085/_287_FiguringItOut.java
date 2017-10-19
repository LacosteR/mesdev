package quests.quests8085;

import l2p.technical.util.Rnd;
import l2p.gameserver.model.instances.NpcInstance;
import l2p.gameserver.model.quest.Quest;
import l2p.gameserver.model.quest.QuestState;
import l2p.gameserver.scripts.ScriptFile;
import org.apache.commons.lang3.ArrayUtils;

/**
 * @author pchayka
 */

public class _287_FiguringItOut extends Quest implements ScriptFile {
    private static final int Laki = 32742;
    private static final int[] TantaClan = {
            22768,
            22769,
            22770,
            22771,
            22772,
            22773,
            22774
    };
    private static final int VialofTantaBlood = 15499;

    public _287_FiguringItOut() {
        super(true);
        addStartNpc(Laki);
        addKillId(TantaClan);
        addQuestItem(VialofTantaBlood);
    }

    @Override
    public String onEvent(String event, QuestState st, NpcInstance npc) {
        String htmltext = event;
        if (event.equalsIgnoreCase("laki_q287_03.htm")) {
            st.setState(STARTED);
            st.setCond(1);
            st.playSound(SOUND_ACCEPT);
        } else if (event.equalsIgnoreCase("request_spitter")) {
            if (st.getQuestItemsCount(VialofTantaBlood) >= 500) {
                st.doTakeItems(VialofTantaBlood, 500);
                switch (Rnd.get(1, 5)) {
                    case 1:
                        st.doGiveItems(10381, 1);
                        break;
                    case 2:
                        st.doGiveItems(10405, 1);
                        break;
                    case 3:
                        st.doGiveItems(10405, 4);
                        break;
                    case 4:
                        st.doGiveItems(10405, 4);
                        break;
                    case 5:
                        st.doGiveItems(10405, 6);
                        break;
                }
                htmltext = "laki_q287_07.htm";
            } else
                htmltext = "laki_q287_06.htm";
        } else if (event.equalsIgnoreCase("request_moirai")) {
            if (st.getQuestItemsCount(VialofTantaBlood) >= 100) {
                st.doTakeItems(VialofTantaBlood, 100);
                switch (Rnd.get(1, 16)) {
                    case 1:
                        st.doGiveItems(15776, 1);
                        break;
                    case 2:
                        st.doGiveItems(15779, 1);
                        break;
                    case 3:
                        st.doGiveItems(15782, 1);
                        break;
                    case 4:
                        st.doGiveItems(15785, 1);
                        break;
                    case 5:
                        st.doGiveItems(15788, 1);
                        break;
                    case 6:
                        st.doGiveItems(15812, 1);
                        break;
                    case 7:
                        st.doGiveItems(15813, 1);
                        break;
                    case 8:
                        st.doGiveItems(15814, 5);
                        break;
                    case 9:
                        st.doGiveItems(15646, 5);
                        break;
                    case 10:
                        st.doGiveItems(15649, 5);
                        break;
                    case 11:
                        st.doGiveItems(15652, 5);
                        break;
                    case 12:
                        st.doGiveItems(15655, 5);
                        break;
                    case 13:
                        st.doGiveItems(15658, 5);
                        break;
                    case 14:
                        st.doGiveItems(15772, 1);
                        break;
                    case 15:
                        st.doGiveItems(15773, 1);
                        break;
                    case 16:
                        st.doGiveItems(15771, 1);
                        break;
                }
                htmltext = "laki_q287_07.htm";
            } else
                htmltext = "laki_q287_10.htm";
        } else if (event.equalsIgnoreCase("continue"))
            htmltext = "laki_q287_08.htm";
        else if (event.equalsIgnoreCase("quit")) {
            htmltext = "laki_q287_09.htm";
            st.exitCurrentQuest(true);
        }
        return htmltext;
    }

    @Override
    public String onTalk(NpcInstance npc, QuestState st) {
        String htmltext = "noquest";
        int npcId = npc.getNpcId();
        int cond = st.getCond();
        if (npcId == Laki) {
            if (cond == 0) {
                QuestState qs = st.getPlayer().getQuestState(_250_WatchWhatYouEat.class);
                if (st.getPlayer().getLevel() >= 82 && qs != null && qs.isCompleted())
                    htmltext = "laki_q287_01.htm";
                else {
                    htmltext = "laki_q287_00.htm";
                    st.exitCurrentQuest(true);
                }
            } else if (cond == 1 && st.getQuestItemsCount(VialofTantaBlood) < 100)
                htmltext = "laki_q287_04.htm";
            else if (cond == 1 && st.getQuestItemsCount(VialofTantaBlood) >= 100)
                htmltext = "laki_q287_05.htm";
        }
        return htmltext;
    }

    @Override
    public String onKill(NpcInstance npc, QuestState st) {
        int npcId = npc.getNpcId();
        int cond = st.getCond();
        if (cond == 1) {
            if (ArrayUtils.contains(TantaClan, npcId))
                st.doGiveOnKillItems(VialofTantaBlood, 1, 1, 60);
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
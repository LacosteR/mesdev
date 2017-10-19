package quests.quests8085;

import l2p.technical.util.Rnd;
import l2p.gameserver.model.instances.NpcInstance;
import l2p.gameserver.model.quest.Quest;
import l2p.gameserver.model.quest.QuestState;
import l2p.gameserver.scripts.ScriptFile;
import org.apache.commons.lang3.ArrayUtils;

public class _289_DeliciousFoodsAreMine extends Quest implements ScriptFile {
    private static final int GuardStan = 30200;
    private static final int FoulFruit = 15507;
    private static final int FullBarrelofSoup = 15712;
    private static final int EmptySoupBarrel = 15713;
    private static final int[] SelMahums = {
            22786,
            22787,
            22788
    };
    private static final int SelChef = 18908;

    public _289_DeliciousFoodsAreMine() {
        super(false);
        addStartNpc(GuardStan);
        addQuestItem(FoulFruit, FullBarrelofSoup, EmptySoupBarrel);
        addKillId(SelMahums);
        addKillId(SelChef);
    }

    @Override
    public String onEvent(String event, QuestState st, NpcInstance npc) {
        String htmltext = event;
        if (event.equalsIgnoreCase("stan_q289_03.htm")) {
            st.setState(STARTED);
            st.setCond(1);
            st.playSound(SOUND_ACCEPT);
            st.doGiveItems(FoulFruit, 500);
        } else if (event.equalsIgnoreCase("stan_q289_05.htm")) {
            st.doGiveItems(FoulFruit, 500);
        } else if (event.equalsIgnoreCase("continue")) {
            htmltext = "stan_q289_11.htm";
        } else if (event.equalsIgnoreCase("quit")) {
            htmltext = "stan_q289_12.htm";
            st.exitCurrentQuest(true);
        } else if (event.equalsIgnoreCase("icarus")) {
            if (st.getQuestItemsCount(FullBarrelofSoup) < 500)
                htmltext = "stan_q289_07.htm";
            else {
                st.doTakeItems(FullBarrelofSoup, 500);
                switch (Rnd.get(1, 5)) {
                    case 1:
                        st.doGiveItems(10377, 1);
                        break;
                    case 2:
                        st.doGiveItems(10401, 3);
                        break;
                    case 3:
                        st.doGiveItems(10401, 4);
                        break;
                    case 4:
                        st.doGiveItems(10401, 5);
                        break;
                    case 5:
                        st.doGiveItems(10401, 6);
                        break;
                }
                st.playSound(SOUND_MIDDLE);
                htmltext = "stan_q289_08.htm";
            }
        } else if (event.equalsIgnoreCase("moirai")) {
            if (st.getQuestItemsCount(FullBarrelofSoup) < 100)
                htmltext = "stan_q289_09.htm";
            else {
                st.doTakeItems(FullBarrelofSoup, 100);
                switch (Rnd.get(1, 18)) {
                    case 1:
                        st.doGiveItems(15775, 1);
                        break;
                    case 2:
                        st.doGiveItems(15778, 1);
                        break;
                    case 3:
                        st.doGiveItems(15781, 1);
                        break;
                    case 4:
                        st.doGiveItems(15784, 1);
                        break;
                    case 5:
                        st.doGiveItems(15787, 1);
                        break;
                    case 6:
                        st.doGiveItems(15791, 1);
                        break;
                    case 7:
                        st.doGiveItems(15812, 1);
                        break;
                    case 8:
                        st.doGiveItems(15813, 1);
                        break;
                    case 9:
                        st.doGiveItems(15814, 1);
                        break;
                    case 10:
                        st.doGiveItems(15645, 3);
                        break;
                    case 11:
                        st.doGiveItems(15648, 3);
                        break;
                    case 12:
                        st.doGiveItems(15651, 3);
                        break;
                    case 13:
                        st.doGiveItems(15654, 3);
                        break;
                    case 14:
                        st.doGiveItems(15657, 3);
                        break;
                    case 15:
                        st.doGiveItems(15693, 3);
                        break;
                    case 16:
                        st.doGiveItems(15772, 3);
                        break;
                    case 17:
                        st.doGiveItems(15773, 3);
                        break;
                    case 18:
                        st.doGiveItems(15774, 3);
                        break;
                }
                st.playSound(SOUND_MIDDLE);
                htmltext = "stan_q289_10.htm";
            }
        }
        return htmltext;
    }

    @Override
    public String onTalk(NpcInstance npc, QuestState st) {
        String htmltext = "noquest";
        int cond = st.getCond();
        if (npc.getNpcId() == GuardStan) {
            if (cond == 0) {
                QuestState qs = st.getPlayer().getQuestState(_252_GoodSmell.class);
                if (st.getPlayer().getLevel() >= 82 && qs != null && qs.isCompleted())
                    htmltext = "stan_q289_01.htm";
                else {
                    htmltext = "stan_q289_00.htm";
                    st.exitCurrentQuest(true);
                }
            } else if (cond == 1 && (st.getQuestItemsCount(FullBarrelofSoup) + (st.getQuestItemsCount(EmptySoupBarrel) * 2)) < 100)
                htmltext = "stan_q289_04.htm";
            else if (cond == 1 && (st.getQuestItemsCount(FullBarrelofSoup) + (st.getQuestItemsCount(EmptySoupBarrel) * 2)) >= 100) {
                if (st.getQuestItemsCount(EmptySoupBarrel) >= 2) {
                    st.doGiveItems(FullBarrelofSoup, st.getQuestItemsCount(EmptySoupBarrel) / 2);
                    st.doTakeItems(EmptySoupBarrel, -1);
                }
                htmltext = "stan_q289_06.htm";
            }
        }

        return htmltext;
    }

    @Override
    public String onKill(NpcInstance npc, QuestState st) {
        int cond = st.getCond();
        if (cond == 1) {
            if (ArrayUtils.contains(SelMahums, npc.getNpcId()) || npc.getNpcId() == SelChef)
                if (!st.doGiveOnKillItems(FullBarrelofSoup, 1, 1, 15))
                    st.doGiveOnKillItems(EmptySoupBarrel, 1, 1, 100);
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
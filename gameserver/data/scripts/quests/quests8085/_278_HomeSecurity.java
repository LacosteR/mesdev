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

public class _278_HomeSecurity extends Quest implements ScriptFile {
    private static final int Tunatun = 31537;
    private static final int[] FarmMonsters = {
            18905,
            18906
    };
    private static final int SelMahumMane = 15531;

    public _278_HomeSecurity() {
        super(false);
        addStartNpc(Tunatun);
        addKillId(FarmMonsters);
        addQuestItem(SelMahumMane);
    }

    @Override
    public String onEvent(String event, QuestState st, NpcInstance npc) {
        String htmltext = event;
        if (event.equalsIgnoreCase("tunatun_q278_03.htm")) {
            st.setState(STARTED);
            st.setCond(1);
            st.playSound(SOUND_ACCEPT);
        }
        return htmltext;
    }

    @Override
    public String onTalk(NpcInstance npc, QuestState st) {
        String htmltext = "noquest";
        int npcId = npc.getNpcId();
        int cond = st.getCond();
        if (npcId == Tunatun) {
            if (cond == 0) {
                if (st.getPlayer().getLevel() >= 82)
                    htmltext = "tunatun_q278_01.htm";
                else {
                    htmltext = "tunatun_q278_00.htm";
                    st.exitCurrentQuest(true);
                }
            } else if (cond == 1)
                htmltext = "tunatun_q278_04.htm";
            else if (cond == 2) {
                if (st.getQuestItemsCount(SelMahumMane) >= 300) {
                    htmltext = "tunatun_q278_05.htm";
                    st.doTakeItems(SelMahumMane, -1);
                    switch (Rnd.get(1, 13)) {
                        case 1:
                            st.doGiveItems(960, 1);
                            break;
                        case 2:
                            st.doGiveItems(960, 2);
                            break;
                        case 3:
                            st.doGiveItems(960, 3);
                            break;
                        case 4:
                            st.doGiveItems(960, 4);
                            break;
                        case 5:
                            st.doGiveItems(960, 5);
                            break;
                        case 6:
                            st.doGiveItems(960, 6);
                            break;
                        case 7:
                            st.doGiveItems(960, 7);
                            break;
                        case 8:
                            st.doGiveItems(960, 8);
                            break;
                        case 9:
                            st.doGiveItems(960, 9);
                            break;
                        case 10:
                            st.doGiveItems(960, 10);
                            break;
                        case 11:
                            st.doGiveItems(9553, 1);
                            break;
                        case 12:
                            st.doGiveItems(9553, 2);
                            break;
                        case 13:
                            st.doGiveItems(959, 1);
                            break;
                    }
                    st.playSound(SOUND_FINISH);
                    st.exitCurrentQuest(true);
                } else
                    htmltext = "tunatun_q278_04.htm";
            }
        }
        return htmltext;
    }

    @Override
    public String onKill(NpcInstance npc, QuestState st) {
        int npcId = npc.getNpcId();
        int cond = st.getCond();
        if (cond == 1)
            if (ArrayUtils.contains(FarmMonsters, npcId) && st.doGiveOnKillItems(SelMahumMane, 1, 1, 300, 100)) {
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
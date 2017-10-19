package quests.quests8085;

import l2p.technical.util.Rnd;
import l2p.gameserver.model.instances.NpcInstance;
import l2p.gameserver.model.quest.Quest;
import l2p.gameserver.model.quest.QuestState;
import l2p.gameserver.scripts.ScriptFile;

import java.util.StringTokenizer;

/**
 * @author pchayka
 */

public class _10289_FadeToBlack extends Quest implements ScriptFile {
    private static final int Greymore = 32757;
    private static final int Anays = 25701;
    private static final int MarkofSplendor = 15527;
    private static final int MarkofDarkness = 15528;

    public _10289_FadeToBlack() {
        super(PARTY_ALL);
        addStartNpc(Greymore);
        addKillId(Anays);
        addQuestItem(MarkofSplendor, MarkofDarkness);
    }

    @Override
    public String onEvent(String event, QuestState st, NpcInstance npc) {
        String htmltext = event;
        if (event.equalsIgnoreCase("greymore_q10289_03.htm")) {
            st.setState(STARTED);
            st.setCond(1);
            st.playSound(SOUND_ACCEPT);
        } else if (event.equalsIgnoreCase("showmark")) {
            if (st.getCond() == 2 && st.getQuestItemsCount(MarkofDarkness) > 0) {
                st.doTakeItems(MarkofDarkness, -1);
                st.addExpAndSp(RATE_QUESTS_REWARD * 55983, RATE_QUESTS_REWARD * 136500);
                htmltext = "greymore_q10289_06.htm";
            } else if (st.getCond() == 3 && st.getQuestItemsCount(MarkofSplendor) > 0)
                htmltext = "greymore_q10289_07.htm";
            else
                htmltext = "greymore_q10289_08.htm";
        } else if (event.startsWith("exchange")) {
            StringTokenizer str = new StringTokenizer(event);
            str.nextToken();
            int id = Integer.parseInt(str.nextToken());
            switch (id) {
                case 1:
                    st.doGiveItems(15775, 1);
                    st.doRewardItems(ADENA_ID, 420920);
                    break;
                case 2:
                    st.doGiveItems(15776, 1);
                    st.doRewardItems(ADENA_ID, 420920);
                    break;
                case 3:
                    st.doGiveItems(15777, 1);
                    st.doRewardItems(ADENA_ID, 420920);
                    break;
                case 4:
                    st.doGiveItems(15778, 1);
                    break;
                case 5:
                    st.doGiveItems(15779, 1);
                    st.doRewardItems(ADENA_ID, 168360);
                    break;
                case 6:
                    st.doGiveItems(15780, 1);
                    st.doRewardItems(ADENA_ID, 168360);
                    break;
                case 7:
                    st.doGiveItems(15781, 1);
                    st.doRewardItems(ADENA_ID, 252540);
                    break;
                case 8:
                    st.doGiveItems(15782, 1);
                    st.doRewardItems(ADENA_ID, 357780);
                    break;
                case 9:
                    st.doGiveItems(15783, 1);
                    st.doRewardItems(ADENA_ID, 357780);
                    break;
                case 10:
                    st.doGiveItems(15784, 1);
                    st.doRewardItems(ADENA_ID, 505100);
                    break;
                case 11:
                    st.doGiveItems(15785, 1);
                    st.doRewardItems(ADENA_ID, 505100);
                    break;
                case 12:
                    st.doGiveItems(15786, 1);
                    st.doRewardItems(ADENA_ID, 505100);
                    break;
                case 13:
                    st.doGiveItems(15787, 1);
                    st.doRewardItems(ADENA_ID, 505100);
                    break;
                case 14:
                    st.doGiveItems(15788, 1);
                    st.doRewardItems(ADENA_ID, 505100);
                    break;
                case 15:
                    st.doGiveItems(15789, 1);
                    st.doRewardItems(ADENA_ID, 505100);
                    break;
                case 16:
                    st.doGiveItems(15790, 1);
                    st.doRewardItems(ADENA_ID, 496680);
                    break;
                case 17:
                    st.doGiveItems(15791, 1);
                    st.doRewardItems(ADENA_ID, 496680);
                    break;
                case 18:
                    st.doGiveItems(15812, 1);
                    st.doRewardItems(ADENA_ID, 563860);
                    break;
                case 19:
                    st.doGiveItems(15813, 1);
                    st.doRewardItems(ADENA_ID, 509040);
                    break;
                case 20:
                    st.doGiveItems(15814, 1);
                    st.doRewardItems(ADENA_ID, 454240);
                    break;
            }
            htmltext = "greymore_q10289_09.htm";
            st.doTakeItems(MarkofSplendor, MarkofDarkness);
            st.exitCurrentQuest(false);
        }
        return htmltext;
    }

    @Override
    public String onTalk(NpcInstance npc, QuestState st) {
        String htmltext = "noquest";
        int cond = st.getCond();
        if (npc.getNpcId() == Greymore) {
            if (cond == 0) {
                QuestState qs = st.getPlayer().getQuestState(_10288_SecretMission.class);
                if (st.getPlayer().getLevel() >= 82 && qs != null && qs.isCompleted())
                    htmltext = "greymore_q10289_01.htm";
                else {
                    htmltext = "greymore_q10289_00.htm";
                    st.exitCurrentQuest(true);
                }
            } else if (cond == 1)
                htmltext = "greymore_q10289_04.htm";
            else if (cond == 2 || cond == 3)
                htmltext = "greymore_q10289_05.htm";
        }
        return htmltext;
    }

    @Override
    public String onKill(NpcInstance npc, QuestState st) {
        int cond = st.getCond();
        if (cond == 1) {
            if (npc.getNpcId() == Anays) {
                if (Rnd.chance(30)) {
                    st.doGiveItems(MarkofSplendor, 1);
                    st.setCond(3);
                } else {
                    st.doGiveItems(MarkofDarkness, 1);
                    st.setCond(2);
                }
            }
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
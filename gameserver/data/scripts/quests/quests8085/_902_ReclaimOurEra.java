package quests.quests8085;

import l2p.technical.util.Rnd;
import l2p.gameserver.model.instances.NpcInstance;
import l2p.gameserver.model.quest.Quest;
import l2p.gameserver.model.quest.QuestState;
import l2p.gameserver.scripts.ScriptFile;
import org.apache.commons.lang3.ArrayUtils;

/**
 * @author pchayka
 *         Daily
 */
public class _902_ReclaimOurEra extends Quest implements ScriptFile {
    private static final int Mathias = 31340;
    private static final int[] OrcsSilenos = {25309, 25312, 25315, 25299, 25302, 25305};
    private static final int[] CannibalisticStakatoChief = {25667, 25668, 25669, 25670};
    private static final int Anais = 25701;

    private static final int ShatteredBones = 21997;
    private static final int CannibalisticStakatoLeaderClaw = 21998;
    private static final int AnaisScroll = 21999;

    public _902_ReclaimOurEra() {
        super(PARTY_ALL);
        addStartNpc(Mathias);
        addKillId(OrcsSilenos);
        addKillId(CannibalisticStakatoChief);
        addKillId(Anais);
        addQuestItem(ShatteredBones, CannibalisticStakatoLeaderClaw, AnaisScroll);
    }

    @Override
    public String onEvent(String event, QuestState st, NpcInstance npc) {
        String htmltext = event;
        if (event.equalsIgnoreCase("mathias_q902_04.htm")) {
            st.setState(STARTED);
            st.setCond(1);
            st.playSound(SOUND_ACCEPT);
        } else if (event.equalsIgnoreCase("mathias_q902_05.htm")) {
            st.setCond(2);
        } else if (event.equalsIgnoreCase("mathias_q902_06.htm")) {
            st.setCond(3);
        } else if (event.equalsIgnoreCase("mathias_q902_07.htm")) {
            st.setCond(4);
        } else if (event.equalsIgnoreCase("mathias_q902_09.htm")) {
            if (st.getQuestItemsCount(ShatteredBones) > 0) {
                st.doTakeItems(ShatteredBones);
                st.doGiveItems(21750, Rnd.get(1, RATE_QUESTS_REWARD));
                st.doRewardItems(ADENA_ID, 134038);
            } else if (st.getQuestItemsCount(CannibalisticStakatoLeaderClaw) > 0) {
                st.doTakeItems(CannibalisticStakatoLeaderClaw);
                st.doGiveItems(21750, Rnd.get(3, 3 * RATE_QUESTS_REWARD));
                st.doRewardItems(ADENA_ID, 210119);
            } else if (st.getQuestItemsCount(AnaisScroll) > 0) {
                st.doTakeItems(AnaisScroll);
                st.doGiveItems(21750, Rnd.get(3, 3 * RATE_QUESTS_REWARD));
                st.doRewardItems(ADENA_ID, 348155);
            }
            st.setState(COMPLETED);
            st.playSound(SOUND_FINISH);
            st.exitCurrentQuest(this);
        }

        return htmltext;
    }

    @Override
    public String onTalk(NpcInstance npc, QuestState st) {
        String htmltext = "noquest";
        int cond = st.getCond();
        if (npc.getNpcId() == Mathias) {
            switch (st.getState()) {
                case CREATED:
                    if (st.isNowAvailable()) {
                        if (st.getPlayer().getLevel() >= 80)
                            htmltext = "mathias_q902_01.htm";
                        else {
                            htmltext = "mathias_q902_00.htm";
                            st.exitCurrentQuest(true);
                        }
                    } else
                        htmltext = "mathias_q902_00a.htm";
                    break;
                case STARTED:
                    if (cond == 1)
                        htmltext = "mathias_q902_04.htm";
                    else if (cond == 2)
                        htmltext = "mathias_q902_05.htm";
                    else if (cond == 3)
                        htmltext = "mathias_q902_06.htm";
                    else if (cond == 4)
                        htmltext = "mathias_q902_07.htm";
                    else if (cond == 5)
                        htmltext = "mathias_q902_08.htm";
                    break;
            }
        }

        return htmltext;
    }

    @Override
    public String onKill(NpcInstance npc, QuestState st) {
        int cond = st.getCond();
        if (cond == 2 && ArrayUtils.contains(OrcsSilenos, npc.getNpcId())) {
            st.doGiveItems(ShatteredBones, 1);
            st.setCond(5);
        } else if (cond == 3 && ArrayUtils.contains(CannibalisticStakatoChief, npc.getNpcId())) {
            st.doGiveItems(CannibalisticStakatoLeaderClaw, 1);
            st.setCond(5);
        } else if (cond == 4 && npc.getNpcId() == Anais) {
            st.doGiveItems(AnaisScroll, 1);
            st.setCond(5);
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
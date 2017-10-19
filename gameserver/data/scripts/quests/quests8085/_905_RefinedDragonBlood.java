package quests.quests8085;

import l2p.gameserver.model.instances.NpcInstance;
import l2p.gameserver.model.quest.Quest;
import l2p.gameserver.model.quest.QuestState;
import l2p.gameserver.scripts.ScriptFile;
import org.apache.commons.lang3.ArrayUtils;

import java.util.StringTokenizer;

public class _905_RefinedDragonBlood extends Quest implements ScriptFile {
    private static final int[] SeparatedSoul = {32864, 32865, 32866, 32867, 32868, 32869, 32870};
    private static final int[] AntharasDragonsBlue = {22852, 22853, 22844, 22845};
    private static final int[] AntharasDragonsRed = {22848, 22849, 22850, 22851};

    private static final int UnrefinedRedDragonBlood = 21913;
    private static final int UnrefinedBlueDragonBlood = 21914;

    public _905_RefinedDragonBlood() {
        super(PARTY_ALL);
        addStartNpc(SeparatedSoul);
        addKillId(AntharasDragonsBlue);
        addKillId(AntharasDragonsRed);
        addQuestItem(UnrefinedRedDragonBlood, UnrefinedBlueDragonBlood);
    }

    @Override
    public String onEvent(String event, QuestState st, NpcInstance npc) {
        String htmltext = event;
        if (event.equalsIgnoreCase("sepsoul_q905_05.htm")) {
            st.setState(STARTED);
            st.setCond(1);
            st.playSound(SOUND_ACCEPT);
        } else if (event.startsWith("sepsoul_q905_08.htm")) {
            st.doTakeItems(AntharasDragonsBlue);
            st.doTakeItems(AntharasDragonsRed);

            StringTokenizer tokenizer = new StringTokenizer(event);
            tokenizer.nextToken();
            switch (Integer.parseInt(tokenizer.nextToken())) {
                case 1:
                    st.doGiveItems(21903, RATE_QUESTS_REWARD * 1);
                    break;
                case 2:
                    st.doGiveItems(21904, RATE_QUESTS_REWARD * 1);
                    break;
                default:
                    break;
            }
            htmltext = "sepsoul_q905_08.htm";
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
        if (ArrayUtils.contains(SeparatedSoul, npc.getNpcId())) {
            switch (st.getState()) {
                case CREATED:
                    if (st.isNowAvailable()) {
                        if (st.getPlayer().getLevel() >= 83)
                            htmltext = "sepsoul_q905_01.htm";
                        else {
                            htmltext = "sepsoul_q905_00.htm";
                            st.exitCurrentQuest(true);
                        }
                    } else
                        htmltext = "sepsoul_q905_00a.htm";
                    break;
                case STARTED:
                    if (cond == 1)
                        htmltext = "sepsoul_q905_06.htm";
                    else if (cond == 2)
                        htmltext = "sepsoul_q905_07.htm";
                    break;
            }
        }

        return htmltext;
    }

    @Override
    public String onKill(NpcInstance npc, QuestState st) {
        int cond = st.getCond();
        if (cond == 1) {
            if (ArrayUtils.contains(AntharasDragonsBlue, npc.getNpcId()))
                st.doGiveOnKillItems(UnrefinedBlueDragonBlood, 1, 1, 10, 70);
            else if (ArrayUtils.contains(AntharasDragonsRed, npc.getNpcId()))
                st.doGiveOnKillItems(UnrefinedRedDragonBlood, 1, 1, 10, 70);

            if (st.getQuestItemsCount(UnrefinedBlueDragonBlood) >= 10 && st.getQuestItemsCount(UnrefinedRedDragonBlood) >= 10)
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
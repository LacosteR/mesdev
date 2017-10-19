package quests.quests6076;

import l2p.technical.util.Rnd;
import l2p.gameserver.model.instances.NpcInstance;
import l2p.gameserver.model.quest.Quest;
import l2p.gameserver.model.quest.QuestState;
import l2p.gameserver.scripts.ScriptFile;

/**
 * Квест Shadowof Light
 *
 * @author Sergey Ibryaev aka Artful
 */

public class _602_ShadowofLight extends Quest implements ScriptFile {
    //NPC
    private static final int ARGOS = 31683;
    //Quest Item
    private static final int EYE_OF_DARKNESS = 7189;
    //Bonus
    private static final int[][] REWARDS = {
            {
                    6699,
                    40000,
                    120000,
                    20000,
                    1,
                    19
            },
            {
                    6698,
                    60000,
                    110000,
                    15000,
                    20,
                    39
            },
            {
                    6700,
                    40000,
                    150000,
                    10000,
                    40,
                    49
            },
            {
                    0,
                    100000,
                    140000,
                    11250,
                    50,
                    100
            }
    };

    @Override
    public void onLoad() {
    }

    @Override
    public void onReload() {
    }

    @Override
    public void onShutdown() {
    }

    public _602_ShadowofLight() {
        super(true);

        addStartNpc(ARGOS);

        addKillId(21299);
        addKillId(21304);

        addQuestItem(EYE_OF_DARKNESS);
    }

    @Override
    public String onEvent(String event, QuestState st, NpcInstance npc) {
        String htmltext = event;
        if (event.equalsIgnoreCase("eye_of_argos_q0602_0104.htm")) {
            st.setCond(1);
            st.setState(STARTED);
            st.playSound(SOUND_ACCEPT);
        } else if (event.equalsIgnoreCase("eye_of_argos_q0602_0201.htm")) {
            st.doTakeItems(EYE_OF_DARKNESS, -1);
            int random = Rnd.get(100) + 1;
            for (int i = 0; i < REWARDS.length; i++)
                if (REWARDS[i][4] <= random && random <= REWARDS[i][5]) {
                    st.doGiveItems(ADENA_ID, REWARDS[i][1]);
                    st.addExpAndSp(RATE_QUESTS_REWARD * REWARDS[i][2], RATE_QUESTS_REWARD * REWARDS[i][3]);
                    if (REWARDS[i][0] != 0)
                        st.doGiveItems(REWARDS[i][0], RATE_QUESTS_REWARD * 3);
                }
            st.playSound(SOUND_FINISH);
            st.exitCurrentQuest(true);
        }
        return htmltext;
    }

    @Override
    public String onTalk(NpcInstance npc, QuestState st) {
        int npcId = npc.getNpcId();
        String htmltext = "noquest";
        int id = st.getState();
        int cond = 0;
        if (id != CREATED)
            cond = st.getCond();
        if (npcId == ARGOS)
            if (cond == 0)
                if (st.getPlayer().getLevel() < 68) {
                    htmltext = "eye_of_argos_q0602_0103.htm";
                    st.exitCurrentQuest(true);
                } else
                    htmltext = "eye_of_argos_q0602_0101.htm";
            else if (cond == 1)
                htmltext = "eye_of_argos_q0602_0106.htm";
            else if (cond == 2 && st.getQuestItemsCount(EYE_OF_DARKNESS) == 100)
                htmltext = "eye_of_argos_q0602_0105.htm";
            else {
                htmltext = "eye_of_argos_q0602_0106.htm";
                st.setCond(1);
            }
        return htmltext;
    }

    @Override
    public String onKill(NpcInstance npc, QuestState st) {
        if (st.getCond() == 1) {
            if (npc.getNpcId() == 21299) {
                if (st.doGiveOnKillItems(EYE_OF_DARKNESS, 1, 1, 100, 35))
                    st.setCond(2);
            } else if (st.doGiveOnKillItems(EYE_OF_DARKNESS, 1, 1, 100, 40))
                st.setCond(2);
        }
        return null;
    }
}

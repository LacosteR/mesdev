package quests.quests0040;

import l2p.technical.util.Rnd;
import l2p.gameserver.model.instances.NpcInstance;
import l2p.gameserver.model.quest.Quest;
import l2p.gameserver.model.quest.QuestState;
import l2p.gameserver.scripts.ScriptFile;

public class _134_TempleMissionary extends Quest implements ScriptFile {
    // NPCs
    private final static int Glyvka = 30067;
    private final static int Rouke = 31418;
    // Mobs
    private final static int Cruma_Marshlands_Traitor = 27339;
    private final static int[] mobs = {
            20157,
            20229,
            20230,
            20231,
            20232,
            20233,
            20234
    };
    // Quest Items
    private final static int Giants_Experimental_Tool_Fragment = 10335;
    private final static int Giants_Experimental_Tool = 10336;
    private final static int Giants_Technology_Report = 10337;
    private final static int Roukes_Report = 10338;
    // Items
    private final static int Badge_Temple_Missionary = 10339;
    // Chances
    private final static int Giants_Experimental_Tool_Fragment_chance = 66;
    private final static int Cruma_Marshlands_Traitor_spawnchance = 45;

    public _134_TempleMissionary() {
        super(false);
        addStartNpc(Glyvka);
        addTalkId(Rouke);
        addKillId(mobs);
        addKillId(Cruma_Marshlands_Traitor);
        addQuestItem(Giants_Experimental_Tool_Fragment);
        addQuestItem(Giants_Experimental_Tool);
        addQuestItem(Giants_Technology_Report);
        addQuestItem(Roukes_Report);
    }

    @Override
    public String onEvent(String event, QuestState st, NpcInstance npc) {
        int _state = st.getState();
        if (event.equalsIgnoreCase("glyvka_q0134_03.htm") && _state == CREATED) {
            st.setCond(1);
            st.setState(STARTED);
            st.playSound(SOUND_ACCEPT);
        } else if (event.equalsIgnoreCase("glyvka_q0134_06.htm") && _state == STARTED) {
            st.setCond(2);
            st.playSound(SOUND_MIDDLE);
        } else if (event.equalsIgnoreCase("glyvka_q0134_11.htm") && _state == STARTED && st.getCond() == 5) {
            st.playSound(SOUND_FINISH);
            st.unset("Report");
            st.doRewardItems(ADENA_ID, 15100);
            st.doGiveItems(Badge_Temple_Missionary, 1);
            st.addExpAndSp(RATE_QUESTS_REWARD * 30000, RATE_QUESTS_REWARD * 2000);
            st.exitCurrentQuest(false);
        } else if (event.equalsIgnoreCase("scroll_seller_rouke_q0134_03.htm") && _state == STARTED) {
            st.setCond(3);
            st.playSound(SOUND_MIDDLE);
        } else if (event.equalsIgnoreCase("scroll_seller_rouke_q0134_09.htm") && _state == STARTED && st.getInt("Report") == 1) {
            st.setCond(5);
            st.playSound(SOUND_MIDDLE);
            st.doGiveItems(Roukes_Report, 1);
            st.unset("Report");
        }
        return event;
    }

    @Override
    public String onTalk(NpcInstance npc, QuestState st) {
        int _state = st.getState();
        if (_state == COMPLETED)
            return "completed";

        int npcId = npc.getNpcId();
        if (_state == CREATED) {
            if (npcId != Glyvka)
                return "noquest";
            if (st.getPlayer().getLevel() < 35) {
                st.exitCurrentQuest(true);
                return "glyvka_q0134_02.htm";
            }
            st.setCond(0);
            return "glyvka_q0134_01.htm";
        }

        int cond = st.getCond();

        if (npcId == Glyvka && _state == STARTED) {
            if (cond == 1)
                return "glyvka_q0134_03.htm";
            if (cond == 5) {
                if (st.getInt("Report") == 1)
                    return "glyvka_q0134_09.htm";
                if (st.getQuestItemsCount(Roukes_Report) > 0) {
                    st.doTakeItems(Roukes_Report, -1);
                    st.set("Report", "1");
                    return "glyvka_q0134_08.htm";
                }
                return "noquest";
            }
            return "glyvka_q0134_07.htm";
        }

        if (npcId == Rouke && _state == STARTED) {
            if (cond == 2)
                return "scroll_seller_rouke_q0134_02.htm";
            if (cond == 5)
                return "scroll_seller_rouke_q0134_10.htm";
            if (cond == 3) {
                long Tools = st.getQuestItemsCount(Giants_Experimental_Tool_Fragment) / 10;
                if (Tools < 1)
                    return "scroll_seller_rouke_q0134_04.htm";
                st.doTakeItems(Giants_Experimental_Tool_Fragment, Tools * 10);
                st.doGiveItems(Giants_Experimental_Tool, Tools);
                return "scroll_seller_rouke_q0134_05.htm";
            }
            if (cond == 4) {
                if (st.getInt("Report") == 1)
                    return "scroll_seller_rouke_q0134_07.htm";
                if (st.getQuestItemsCount(Giants_Technology_Report) > 2) {
                    st.doTakeItems(Giants_Experimental_Tool_Fragment, -1);
                    st.doTakeItems(Giants_Experimental_Tool, -1);
                    st.doTakeItems(Giants_Technology_Report, -1);
                    st.set("Report", "1");
                    return "scroll_seller_rouke_q0134_06.htm";
                }
                return "noquest";
            }
        }

        return "noquest";
    }

    @Override
    public String onKill(NpcInstance npc, QuestState qs) {
        if (qs.getState() != STARTED || qs.getCond() != 3)
            return null;

        if (npc.getNpcId() == Cruma_Marshlands_Traitor) {
            if (qs.doGiveOnKillItems(Giants_Technology_Report, 1, 1, 3, 100))
                qs.setCond(4);
        } else if (qs.getQuestItemsCount(Giants_Experimental_Tool) < 1) {
            qs.doGiveOnKillItems(Giants_Experimental_Tool_Fragment, 1, 1, 1, Giants_Experimental_Tool_Fragment_chance);
        } else {
            qs.doTakeItems(Giants_Experimental_Tool, 1);
            if (Rnd.chance(Cruma_Marshlands_Traitor_spawnchance))
                qs.addSpawn(Cruma_Marshlands_Traitor, qs.getPlayer().getPositionComponent().getX(), qs.getPlayer().getPositionComponent().getY(), qs.getPlayer().getPositionComponent().getZ(), 0, 100, 900000);
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
package quests.quests4060;

import l2p.gameserver.model.instances.NpcInstance;
import l2p.gameserver.model.quest.Quest;
import l2p.gameserver.model.quest.QuestState;
import l2p.gameserver.scripts.ScriptFile;

public class _136_MoreThanMeetsTheEye extends Quest implements ScriptFile {
    //NPC
    private static final int HARDIN = 30832;
    private static final int ERRICKIN = 30701;
    private static final int CLAYTON = 30464;
    //Item
    private static final int TransformSealbook = 9648;
    //Quest Item
    private static final int Ectoplasm = 9787;
    private static final int StabilizedEctoplasm = 9786;
    private static final int HardinsInstructions = 9788;
    private static final int GlassJaguarCrystal = 9789;
    private static final int BlankSealbook = 9790;

    //Drop Cond
    //# [COND, NEWCOND, ID, REQUIRED, ITEM, NEED_COUNT, CHANCE, DROP]
    private static final int[] npcs = {20636, 20637, 20638, 20639, 20250};

    @Override
    public void onLoad() {
    }

    @Override
    public void onReload() {
    }

    @Override
    public void onShutdown() {
    }

    public _136_MoreThanMeetsTheEye() {
        super(false);

        addStartNpc(HARDIN);
        addTalkId(HARDIN);
        addTalkId(ERRICKIN);
        addTalkId(CLAYTON);

        addQuestItem(new int[]{
                StabilizedEctoplasm,
                HardinsInstructions,
                BlankSealbook,
                Ectoplasm,
                GlassJaguarCrystal
        });

        for (int i : npcs)
            addKillId(i);
    }

    @Override
    public String onEvent(String event, QuestState st, NpcInstance npc) {
        String htmltext = event;
        if (event.equalsIgnoreCase("hardin_q0136_08.htm")) {
            st.setCond(2);
            st.set("id", "0");
            st.setState(STARTED);
            st.playSound(SOUND_ACCEPT);
        } else if (event.equalsIgnoreCase("magister_errickin_q0136_03.htm")) {
            st.setCond(3);
            st.setState(STARTED);
        } else if (event.equalsIgnoreCase("hardin_q0136_16.htm")) {
            st.doGiveItems(HardinsInstructions, 1);
            st.setCond(6);
            st.setState(STARTED);
        } else if (event.equalsIgnoreCase("magister_clayton_q0136_10.htm")) {
            st.setCond(7);
            st.setState(STARTED);
        } else if (event.equalsIgnoreCase("hardin_q0136_23.htm")) {
            st.playSound(SOUND_FINISH);
            st.doGiveItems(TransformSealbook, 1);
            st.doRewardItems(ADENA_ID, 67550);
            st.unset("id");
            st.unset("cond");
            st.exitCurrentQuest(false);
        }
        return htmltext;
    }

    @Override
    public String onTalk(NpcInstance npc, QuestState st) {
        int npcId = npc.getNpcId();
        String htmltext = "noquest";
        int cond = st.getCond();
        if (npcId == HARDIN) {
            if (cond == 0) {
                if (st.getPlayer().getLevel() >= 50) {
                    st.setCond(1);
                    htmltext = "hardin_q0136_01.htm";
                } else {
                    htmltext = "hardin_q0136_02.htm";
                    st.exitCurrentQuest(true);
                }
            } else if (cond == 2 || cond == 3 || cond == 4)
                htmltext = "hardin_q0136_09.htm";
            else if (cond == 5) {
                st.doTakeItems(StabilizedEctoplasm, -1);
                htmltext = "hardin_q0136_10.htm";
            } else if (cond == 6)
                htmltext = "hardin_q0136_17.htm";
            else if (cond == 9) {
                st.doTakeItems(BlankSealbook, -1);
                htmltext = "hardin_q0136_18.htm";
            }
        } else if (npcId == ERRICKIN) {
            if (cond == 2)
                htmltext = "magister_errickin_q0136_02.htm";
            else if (cond == 3)
                htmltext = "magister_errickin_q0136_03.htm";
            else if (cond == 4 && st.getQuestItemsCount(Ectoplasm) < 35 && st.getInt("id") == 0) {
                st.setCond(3);
                htmltext = "magister_errickin_q0136_03.htm";
            } else if (cond == 4 && st.getInt("id") == 0) {
                st.doTakeItems(Ectoplasm, -1);
                htmltext = "magister_errickin_q0136_05.htm";
                st.set("id", "1");
            } else if (cond == 4 && st.getInt("id") == 1) {
                htmltext = "magister_errickin_q0136_06.htm";
                st.doGiveItems(StabilizedEctoplasm, 1);
                st.set("id", "0");
                st.setCond(5);
                st.setState(STARTED);
            } else if (cond == 5)
                htmltext = "magister_errickin_q0136_07.htm";
        } else if (npcId == CLAYTON)
            if (cond == 6) {
                st.doTakeItems(HardinsInstructions, -1);
                htmltext = "magister_clayton_q0136_09.htm";
            } else if (cond == 7)
                htmltext = "magister_clayton_q0136_12.htm";
            else if (cond == 8 && st.getQuestItemsCount(GlassJaguarCrystal) < 5) {
                htmltext = "magister_clayton_q0136_12.htm";
                st.setCond(7);
            } else if (cond == 8) {
                htmltext = "magister_clayton_q0136_13.htm";
                st.doTakeItems(GlassJaguarCrystal, -1);
                st.doGiveItems(BlankSealbook, 1);
                st.setCond(9);
                st.setState(STARTED);
            } else if (cond == 9)
                htmltext = "magister_clayton_q0136_14.htm";
        return htmltext;
    }

    @Override
    public String onKill(NpcInstance npc, QuestState st) {
        int killed_npc_id = npc.getNpcId();
        int cond = st.getCond();
        for (int npc_id : npcs) {
            if (killed_npc_id != npc_id)
                continue;

            if (npc_id == 20250 && cond == 7) {
                if (st.doGiveOnKillItems(GlassJaguarCrystal, 1, 1, 5, 100))
                    st.setCond(8);
            } else {
                if (st.doGiveOnKillItems(Ectoplasm, 1, 3, 35, 100))
                    st.setCond(4);
            }
        }
        return null;
    }
}
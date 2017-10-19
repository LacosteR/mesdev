package quests.quests0040;

import l2p.technical.util.Rnd;
import l2p.gameserver.model.instances.MonsterInstance;
import l2p.gameserver.model.instances.NpcInstance;
import l2p.gameserver.model.quest.Quest;
import l2p.gameserver.model.quest.QuestState;
import l2p.gameserver.scripts.ScriptFile;

public class _216_TrialoftheGuildsman extends Quest implements ScriptFile {
    //NPC
    private static final int VALKON = 30103;
    private static final int NORMAN = 30210;
    private static final int ALTRAN = 30283;
    private static final int PINTER = 30298;
    private static final int DUNING = 30688;
    //Quest Item
    private static final int MARK_OF_GUILDSMAN = 3119;
    private static final int VALKONS_RECOMMEND = 3120;
    private static final int MANDRAGORA_BERRY = 3121;
    private static final int ALLTRANS_INSTRUCTIONS = 3122;
    private static final int ALLTRANS_RECOMMEND1 = 3123;
    private static final int ALLTRANS_RECOMMEND2 = 3124;
    private static final int NORMANS_INSTRUCTIONS = 3125;
    private static final int NORMANS_RECEIPT = 3126;
    private static final int DUNINGS_INSTRUCTIONS = 3127;
    private static final int DUNINGS_KEY = 3128;
    private static final int NORMANS_LIST = 3129;
    private static final int GRAY_BONE_POWDER = 3130;
    private static final int GRANITE_WHETSTONE = 3131;
    private static final int RED_PIGMENT = 3132;
    private static final int BRAIDED_YARN = 3133;
    private static final int JOURNEYMAN_GEM = 3134;
    private static final int PINTERS_INSTRUCTIONS = 3135;
    private static final int AMBER_BEAD = 3136;
    private static final int AMBER_LUMP = 3137;
    private static final int JOURNEYMAN_DECO_BEADS = 3138;
    private static final int JOURNEYMAN_RING = 3139;
    private static final int RP_JOURNEYMAN_RING = 3024;
    private static final int DIMENSION_DIAMOND = 7562;
    private static final int RP_AMBER_BEAD = 3025;

    //Drop Cond
    //# [COND, NEWCOND, ID, REQUIRED, ITEM, NEED_COUNT, CHANCE, DROP]
    private static final int[][] DROPLIST_COND = {
            {
                    3,
                    4,
                    20223,
                    VALKONS_RECOMMEND,
                    MANDRAGORA_BERRY,
                    1,
                    20,
                    1
            },
            {
                    3,
                    4,
                    20154,
                    VALKONS_RECOMMEND,
                    MANDRAGORA_BERRY,
                    1,
                    50,
                    1
            },
            {
                    3,
                    4,
                    20155,
                    VALKONS_RECOMMEND,
                    MANDRAGORA_BERRY,
                    1,
                    50,
                    1
            },
            {
                    3,
                    4,
                    20156,
                    VALKONS_RECOMMEND,
                    MANDRAGORA_BERRY,
                    1,
                    50,
                    1
            },
            {
                    5,
                    0,
                    20267,
                    DUNINGS_INSTRUCTIONS,
                    DUNINGS_KEY,
                    30,
                    100,
                    1
            },
            {
                    5,
                    0,
                    20268,
                    DUNINGS_INSTRUCTIONS,
                    DUNINGS_KEY,
                    30,
                    100,
                    1
            },
            {
                    5,
                    0,
                    20269,
                    DUNINGS_INSTRUCTIONS,
                    DUNINGS_KEY,
                    30,
                    100,
                    1
            },
            {
                    5,
                    0,
                    20270,
                    DUNINGS_INSTRUCTIONS,
                    DUNINGS_KEY,
                    30,
                    100,
                    1
            },
            {
                    5,
                    0,
                    20271,
                    DUNINGS_INSTRUCTIONS,
                    DUNINGS_KEY,
                    30,
                    100,
                    1
            },
            {
                    5,
                    0,
                    20200,
                    NORMANS_LIST,
                    GRAY_BONE_POWDER,
                    70,
                    100,
                    2
            },
            {
                    5,
                    0,
                    20201,
                    NORMANS_LIST,
                    GRAY_BONE_POWDER,
                    70,
                    100,
                    2
            },
            {
                    5,
                    0,
                    20202,
                    NORMANS_LIST,
                    RED_PIGMENT,
                    70,
                    100,
                    2
            },
            {
                    5,
                    0,
                    20083,
                    NORMANS_LIST,
                    GRANITE_WHETSTONE,
                    70,
                    100,
                    2
            },
            {
                    5,
                    0,
                    20168,
                    NORMANS_LIST,
                    BRAIDED_YARN,
                    70,
                    100,
                    2
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

    public _216_TrialoftheGuildsman() {
        super(false);

        addStartNpc(VALKON);
        addTalkId(VALKON);
        addTalkId(NORMAN);
        addTalkId(ALTRAN);
        addTalkId(PINTER);
        addTalkId(DUNING);

        addKillId(20079);
        addKillId(20080);
        addKillId(20081);

        for (int i = 0; i < DROPLIST_COND.length; i++)
            addKillId(DROPLIST_COND[i][2]);

        addQuestItem(new int[]{
                ALLTRANS_INSTRUCTIONS,
                VALKONS_RECOMMEND,
                ALLTRANS_RECOMMEND1,
                NORMANS_INSTRUCTIONS,
                NORMANS_LIST,
                NORMANS_RECEIPT,
                ALLTRANS_RECOMMEND2,
                PINTERS_INSTRUCTIONS,
                DUNINGS_INSTRUCTIONS,
                JOURNEYMAN_GEM,
                JOURNEYMAN_DECO_BEADS,
                JOURNEYMAN_RING,
                AMBER_BEAD,
                AMBER_LUMP,
                MANDRAGORA_BERRY,
                DUNINGS_KEY,
                GRAY_BONE_POWDER,
                RED_PIGMENT,
                GRANITE_WHETSTONE,
                BRAIDED_YARN
        });
    }

    @Override
    public String onEvent(String event, QuestState st, NpcInstance npc) {
        String htmltext = event;
        if (event.equalsIgnoreCase("valkon_q0216_06.htm")) {
            st.setCond(1);
            st.setState(STARTED);
            st.playSound(SOUND_ACCEPT);
            st.doGiveItems(VALKONS_RECOMMEND, 1);
            st.doTakeItems(ADENA_ID, 2000);
            if (!st.getPlayer().getVarB("dd1")) {
                st.doGiveItems(DIMENSION_DIAMOND, RATE_QUESTS_REWARD * 85);
                st.getPlayer().setVar("dd1", "1", -1);
            }
        } else if (event.equalsIgnoreCase("valkon_q0216_07c.htm"))
            st.setCond(3);
        else if (event.equalsIgnoreCase("valkon_q0216_05.htm") && st.getQuestItemsCount(ADENA_ID) < 2000)
            htmltext = "valkon_q0216_05a.htm";
        else if (event.equalsIgnoreCase("30103_3") || event.equalsIgnoreCase("30103_4")) {
            if (event.equalsIgnoreCase("30103_3"))
                htmltext = "valkon_q0216_09a.htm";
            else
                htmltext = "valkon_q0216_09b.htm";
            st.doTakeItems(JOURNEYMAN_RING, -1);
            st.doTakeItems(ALLTRANS_INSTRUCTIONS, -1);
            st.doTakeItems(RP_JOURNEYMAN_RING, -1);
            st.doGiveItems(MARK_OF_GUILDSMAN, 1);
            if (!st.getPlayer().getVarB("prof2.1")) {
                st.addExpAndSp(RATE_QUESTS_REWARD * 1029478, RATE_QUESTS_REWARD * 66768);
                st.doRewardItems(ADENA_ID, 187606);
                st.getPlayer().setVar("prof2.1", "1", -1);
            }
            st.exitCurrentQuest(true);
            st.playSound(SOUND_FINISH);
        } else if (event.equalsIgnoreCase("blacksmith_alltran_q0216_03.htm")) {
            st.doTakeItems(VALKONS_RECOMMEND, -1);
            st.doTakeItems(MANDRAGORA_BERRY, -1);
            st.doGiveItems(ALLTRANS_INSTRUCTIONS, 1);
            st.doGiveItems(RP_JOURNEYMAN_RING, 1);
            st.doGiveItems(ALLTRANS_RECOMMEND1, 1);
            st.doGiveItems(ALLTRANS_RECOMMEND2, 1);
            st.setCond(5);
        } else if (event.equalsIgnoreCase("warehouse_keeper_norman_q0216_04.htm")) {
            st.doTakeItems(ALLTRANS_RECOMMEND1, -1);
            st.doGiveItems(NORMANS_INSTRUCTIONS, 1);
            st.doGiveItems(NORMANS_RECEIPT, 1);
        } else if (event.equalsIgnoreCase("warehouse_keeper_norman_q0216_10.htm")) {
            st.doTakeItems(DUNINGS_KEY, -1);
            st.doTakeItems(NORMANS_INSTRUCTIONS, -1);
            st.doGiveItems(NORMANS_LIST, 1);
        } else if (event.equalsIgnoreCase("blacksmith_duning_q0216_02.htm")) {
            st.doTakeItems(NORMANS_RECEIPT, -1);
            st.doGiveItems(DUNINGS_INSTRUCTIONS, 1);
        } else if (event.equalsIgnoreCase("blacksmith_pinter_q0216_04.htm")) {
            st.doTakeItems(ALLTRANS_RECOMMEND2, -1);
            st.doGiveItems(PINTERS_INSTRUCTIONS, 1);

            if (st.getPlayer().getPcClass().class_id == 0x38) {
                htmltext = "blacksmith_pinter_q0216_05.htm";
                st.doGiveItems(RP_AMBER_BEAD, 1);
            }
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
        if (npcId == VALKON) {
            if (st.getQuestItemsCount(MARK_OF_GUILDSMAN) > 0) {
                htmltext = "completed";
                st.exitCurrentQuest(true);
            } else if (cond == 0) {
                if (st.getPlayer().getPcClass().class_id == 0x36 || st.getPlayer().getPcClass().class_id == 0x38) {
                    if (st.getPlayer().getLevel() >= 35)
                        htmltext = "valkon_q0216_03.htm";
                    else {
                        htmltext = "valkon_q0216_02.htm";
                        st.exitCurrentQuest(true);
                    }
                } else {
                    htmltext = "valkon_q0216_01.htm";
                    st.exitCurrentQuest(true);
                }
            } else if (cond == 2 && st.getQuestItemsCount(VALKONS_RECOMMEND) > 0)
                htmltext = "valkon_q0216_07.htm";
            else if (st.getQuestItemsCount(ALLTRANS_INSTRUCTIONS) > 0)
                if (st.getQuestItemsCount(JOURNEYMAN_RING) < 7)
                    htmltext = "valkon_q0216_08.htm";
                else
                    htmltext = "valkon_q0216_09.htm";
        } else if (npcId == ALTRAN) {
            if (cond == 1 && st.getQuestItemsCount(VALKONS_RECOMMEND) > 0) {
                htmltext = "blacksmith_alltran_q0216_01.htm";
                st.setCond(2);
            } else if (cond == 4 && st.getQuestItemsCount(VALKONS_RECOMMEND) > 0 && st.getQuestItemsCount(MANDRAGORA_BERRY) >= 1)
                htmltext = "blacksmith_alltran_q0216_02.htm";
            else if (cond < 6 && st.getQuestItemsCount(ALLTRANS_INSTRUCTIONS) == 1 && st.getQuestItemsCount(JOURNEYMAN_RING) < 7)
                htmltext = "blacksmith_alltran_q0216_04.htm";
            else if (cond == 6 && st.getQuestItemsCount(JOURNEYMAN_RING) >= 7)
                htmltext = "blacksmith_alltran_q0216_05.htm";
        } else if (npcId == NORMAN && cond >= 5) {
            if (st.getQuestItemsCount(ALLTRANS_INSTRUCTIONS) >= 1 && st.getQuestItemsCount(ALLTRANS_RECOMMEND1) >= 1)
                htmltext = "warehouse_keeper_norman_q0216_01.htm";
            else if (st.getQuestItemsCount(ALLTRANS_INSTRUCTIONS) > 0 && st.getQuestItemsCount(NORMANS_INSTRUCTIONS) > 0 && st.getQuestItemsCount(NORMANS_RECEIPT) > 0)
                htmltext = "warehouse_keeper_norman_q0216_05.htm";
            else if (st.getQuestItemsCount(ALLTRANS_INSTRUCTIONS) > 0 && st.getQuestItemsCount(NORMANS_INSTRUCTIONS) > 0 && st.getQuestItemsCount(DUNINGS_INSTRUCTIONS) > 0)
                htmltext = "warehouse_keeper_norman_q0216_06.htm";
            else if (st.getQuestItemsCount(ALLTRANS_INSTRUCTIONS) > 0 && st.getQuestItemsCount(NORMANS_INSTRUCTIONS) > 0 && st.getQuestItemsCount(DUNINGS_KEY) >= 30)
                htmltext = "warehouse_keeper_norman_q0216_07.htm";
            else if (st.getQuestItemsCount(ALLTRANS_INSTRUCTIONS) > 0 && st.getQuestItemsCount(NORMANS_LIST) > 0) {
                if (st.getQuestItemsCount(GRAY_BONE_POWDER) >= 70 && st.getQuestItemsCount(GRANITE_WHETSTONE) >= 70 && st.getQuestItemsCount(RED_PIGMENT) >= 70 && st.getQuestItemsCount(BRAIDED_YARN) >= 70) {
                    htmltext = "warehouse_keeper_norman_q0216_12.htm";
                    st.doTakeItems(NORMANS_LIST, -1);
                    st.doTakeItems(GRAY_BONE_POWDER, -1);
                    st.doTakeItems(GRANITE_WHETSTONE, -1);
                    st.doTakeItems(RED_PIGMENT, -1);
                    st.doTakeItems(BRAIDED_YARN, -1);
                    st.doGiveItems(JOURNEYMAN_GEM, 7);
                    if (st.getQuestItemsCount(JOURNEYMAN_DECO_BEADS) >= 7 && st.getQuestItemsCount(JOURNEYMAN_GEM) >= 7)
                        st.setCond(6);
                } else
                    htmltext = "warehouse_keeper_norman_q0216_11.htm";
            } else if (st.getQuestItemsCount(NORMANS_INSTRUCTIONS) == 0 && st.getQuestItemsCount(NORMANS_LIST) == 0 && st.getQuestItemsCount(ALLTRANS_INSTRUCTIONS) >= 1 && (st.getQuestItemsCount(JOURNEYMAN_GEM) > 0 || st.getQuestItemsCount(JOURNEYMAN_RING) > 0))
                htmltext = "warehouse_keeper_norman_q0216_13.htm";
        } else if (npcId == DUNING && cond >= 5) {
            if (st.getQuestItemsCount(ALLTRANS_INSTRUCTIONS) > 0 && st.getQuestItemsCount(NORMANS_INSTRUCTIONS) > 0 && st.getQuestItemsCount(NORMANS_RECEIPT) > 0)
                htmltext = "blacksmith_duning_q0216_01.htm";
            else if (st.getQuestItemsCount(ALLTRANS_INSTRUCTIONS) > 0 && st.getQuestItemsCount(NORMANS_INSTRUCTIONS) > 0 && st.getQuestItemsCount(DUNINGS_INSTRUCTIONS) > 0)
                htmltext = "blacksmith_duning_q0216_03.htm";
            else if (st.getQuestItemsCount(ALLTRANS_INSTRUCTIONS) > 0 && st.getQuestItemsCount(NORMANS_INSTRUCTIONS) > 0 && st.getQuestItemsCount(DUNINGS_KEY) == 30)
                htmltext = "blacksmith_duning_q0216_04.htm";
            else if (st.getQuestItemsCount(NORMANS_RECEIPT) == 0 && st.getQuestItemsCount(DUNINGS_INSTRUCTIONS) == 0 && st.getQuestItemsCount(DUNINGS_KEY) == 0 && st.getQuestItemsCount(ALLTRANS_INSTRUCTIONS) >= 1)
                htmltext = "blacksmith_duning_q0216_01.htm";
        } else if (npcId == PINTER && cond >= 5)
            if (st.getQuestItemsCount(ALLTRANS_INSTRUCTIONS) > 0 && st.getQuestItemsCount(ALLTRANS_RECOMMEND2) > 0)
                if (st.getPlayer().getLevel() < 36)
                    htmltext = "blacksmith_pinter_q0216_01.htm";
                else
                    htmltext = "blacksmith_pinter_q0216_02.htm";
            else if (st.getQuestItemsCount(ALLTRANS_INSTRUCTIONS) > 0 && st.getQuestItemsCount(PINTERS_INSTRUCTIONS) > 0)
                if (st.getQuestItemsCount(AMBER_BEAD) < 70)
                    htmltext = "blacksmith_pinter_q0216_06.htm";
                else {
                    htmltext = "blacksmith_pinter_q0216_07.htm";
                    st.doTakeItems(PINTERS_INSTRUCTIONS, -1);
                    st.doTakeItems(AMBER_BEAD, -1);
                    st.doTakeItems(RP_AMBER_BEAD, -1);
                    st.doTakeItems(AMBER_LUMP, -1);
                    st.doGiveItems(JOURNEYMAN_DECO_BEADS, 7);
                    if (st.getQuestItemsCount(JOURNEYMAN_DECO_BEADS) == 7 && st.getQuestItemsCount(JOURNEYMAN_GEM) >= 7)
                        st.setCond(6);
                }
            else if (st.getQuestItemsCount(ALLTRANS_INSTRUCTIONS) == 1 && st.getQuestItemsCount(PINTERS_INSTRUCTIONS) == 0 && (st.getQuestItemsCount(JOURNEYMAN_DECO_BEADS) > 0 || st.getQuestItemsCount(JOURNEYMAN_RING) > 0))
                htmltext = "blacksmith_pinter_q0216_08.htm";
        return htmltext;
    }

    @Override
    public String onKill(NpcInstance npc, QuestState st) {
        int npcId = npc.getNpcId();
        int cond = st.getCond();
        for (int i = 0; i < DROPLIST_COND.length; i++)
            if (cond == DROPLIST_COND[i][0] && npcId == DROPLIST_COND[i][2])
                if (DROPLIST_COND[i][3] == 0 || st.getQuestItemsCount(DROPLIST_COND[i][3]) > 0)
                    if (DROPLIST_COND[i][5] == 0)
                        st.doGiveOnKillItems(DROPLIST_COND[i][4], DROPLIST_COND[i][7], DROPLIST_COND[i][7], DROPLIST_COND[i][6]);
                    else if (st.doGiveOnKillItems(DROPLIST_COND[i][4], DROPLIST_COND[i][7], DROPLIST_COND[i][7], DROPLIST_COND[i][5], DROPLIST_COND[i][6])) {
                        if (DROPLIST_COND[i][4] >= DUNINGS_KEY)
                            st.doTakeItems(DUNINGS_INSTRUCTIONS, -1);
                        if (DROPLIST_COND[i][1] != cond && DROPLIST_COND[i][1] != 0) {
                            st.setCond(Integer.valueOf(DROPLIST_COND[i][1]));
                            st.setState(STARTED);
                        }
                    }
        if (cond == 5 && (npcId == 20079 || npcId == 20080 || npcId == 20081))
            if (Rnd.chance(33) && st.getQuestItemsCount(ALLTRANS_INSTRUCTIONS) > 0 && st.getQuestItemsCount(PINTERS_INSTRUCTIONS) > 0) {
                long count = st.getQuestItemsCount(AMBER_BEAD) + st.getQuestItemsCount(AMBER_LUMP) * 5;
                if (count < 70 && st.getPlayer().getPcClass().class_id == 0x36) {
                    st.doGiveItems(AMBER_BEAD, 5);
                    if (st.getQuestItemsCount(AMBER_BEAD) >= 70)
                        st.playSound(SOUND_MIDDLE);
                    else
                        st.playSound(SOUND_ITEMGET);
                }
                if (count < 70 && st.getPlayer().getPcClass().class_id == 0x38) {
                    st.doGiveItems(AMBER_LUMP, 5);
                    if (((MonsterInstance) npc).isSpoiled())
                        st.doGiveItems(AMBER_LUMP, 5);
                    count = st.getQuestItemsCount(AMBER_BEAD) + st.getQuestItemsCount(AMBER_LUMP) * 5;
                    if (count >= 70)
                        st.playSound(SOUND_MIDDLE);
                    else
                        st.playSound(SOUND_ITEMGET);
                }
            }
        return null;
    }
}

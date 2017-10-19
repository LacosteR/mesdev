package quests.quests0040;

import l2p.gameserver.model.base.Race;
import l2p.gameserver.model.instances.NpcInstance;
import l2p.gameserver.model.quest.Quest;
import l2p.gameserver.model.quest.QuestState;
import l2p.gameserver.network.serverpackets.ExShowScreenMessage;
import l2p.gameserver.network.serverpackets.ExShowScreenMessage.ScreenMessageAlign;
import l2p.gameserver.scripts.ScriptFile;

public class _108_JumbleTumbleDiamondFuss extends Quest implements ScriptFile {
    int GOUPHS_CONTRACT = 1559;
    int REEPS_CONTRACT = 1560;
    int ELVEN_WINE = 1561;
    int BRONPS_DICE = 1562;
    int BRONPS_CONTRACT = 1563;
    int AQUAMARINE = 1564;
    int CHRYSOBERYL = 1565;
    int GEM_BOX1 = 1566;
    int COAL_PIECE = 1567;
    int BRONPS_LETTER = 1568;
    int BERRY_TART = 1569;
    int BAT_DIAGRAM = 1570;
    int STAR_DIAMOND = 1571;
    int SILVERSMITH_HAMMER = 1511;

    @Override
    public void onLoad() {
    }

    @Override
    public void onReload() {
    }

    @Override
    public void onShutdown() {
    }

    public _108_JumbleTumbleDiamondFuss() {
        super(false);

        addStartNpc(30523);

        addTalkId(30516);
        addTalkId(30521);
        addTalkId(30522);
        addTalkId(30526);
        addTalkId(30529);
        addTalkId(30555);

        addKillId(20323);
        addKillId(20324);
        addKillId(20480);

        addQuestItem(GEM_BOX1, STAR_DIAMOND, GOUPHS_CONTRACT, REEPS_CONTRACT, ELVEN_WINE, BRONPS_CONTRACT, AQUAMARINE, CHRYSOBERYL, COAL_PIECE, BRONPS_DICE, BRONPS_LETTER, BERRY_TART, BAT_DIAGRAM);
    }

    @Override
    public String onEvent(String event, QuestState st, NpcInstance npc) {
        String htmltext = event;
        if (event.equals("collector_gouph_q0108_03.htm")) {
            st.setCond(1);
            st.setState(STARTED);
            st.doGiveItems(GOUPHS_CONTRACT, 1);
            st.playSound(SOUND_ACCEPT);
        } else if (event.equals("carrier_torocco_q0108_02.htm")) {
            st.doTakeItems(REEPS_CONTRACT, 1);
            st.doGiveItems(ELVEN_WINE, 1);
            st.setCond(3);
        } else if (event.equals("blacksmith_bronp_q0108_02.htm")) {
            st.doTakeItems(BRONPS_DICE, 1);
            st.doGiveItems(BRONPS_CONTRACT, 1);
            st.setCond(5);
        }
        return htmltext;
    }

    @Override
    public String onTalk(NpcInstance npc, QuestState st) {
        int npcId = npc.getNpcId();
        String htmltext = "noquest";
        int cond = st.getCond();
        if (npcId == 30523) {
            if (cond == 0) {
                if (st.getPlayer().getRace() != Race.dwarf) {
                    htmltext = "collector_gouph_q0108_00.htm";
                    st.exitCurrentQuest(true);
                } else if (st.getPlayer().getLevel() >= 10)
                    htmltext = "collector_gouph_q0108_02.htm";
                else {
                    htmltext = "collector_gouph_q0108_01.htm";
                    st.exitCurrentQuest(true);
                }
            } else if (cond == 0 && st.getQuestItemsCount(GOUPHS_CONTRACT) > 0)
                htmltext = "collector_gouph_q0108_04.htm";
            else if (cond > 1 && cond < 7 && (st.getQuestItemsCount(REEPS_CONTRACT) > 0 || st.getQuestItemsCount(ELVEN_WINE) > 0 || st.getQuestItemsCount(BRONPS_DICE) > 0 || st.getQuestItemsCount(BRONPS_CONTRACT) > 0))
                htmltext = "collector_gouph_q0108_05.htm";
            else if (cond == 7 && st.getQuestItemsCount(GEM_BOX1) > 0) {
                htmltext = "collector_gouph_q0108_06.htm";
                st.doTakeItems(GEM_BOX1, 1);
                st.doGiveItems(COAL_PIECE, 1);
                st.setCond(8);
            } else if (cond > 7 && cond < 12 && (st.getQuestItemsCount(BRONPS_LETTER) > 0 || st.getQuestItemsCount(COAL_PIECE) > 0 || st.getQuestItemsCount(BERRY_TART) > 0 || st.getQuestItemsCount(BAT_DIAGRAM) > 0))
                htmltext = "collector_gouph_q0108_07.htm";
            else if (cond == 12 && st.getQuestItemsCount(STAR_DIAMOND) > 0) {
                htmltext = "collector_gouph_q0108_08.htm";
                st.doTakeItems(STAR_DIAMOND, 1);

                st.doGiveItems(SILVERSMITH_HAMMER, 1);
                st.getPlayer().addExpAndSp(RATE_QUESTS_REWARD * 34565, RATE_QUESTS_REWARD * 2962);
                st.doRewardItems(ADENA_ID, 14666);

                if (st.getPlayer().getPcClass().getLevel() == 1 && !st.getPlayer().getVarB("p1q3")) {
                    st.getPlayer().setVar("p1q3", "1", -1); // flag for helper
                    st.getPlayer().sendPacket(new ExShowScreenMessage("Now go find the Newbie Guide.", 5000, ScreenMessageAlign.TOP_CENTER, true));
                    st.doGiveItems(1060, 100); // healing potion
                    for (int item = 4412; item <= 4417; item++)
                        st.doGiveItems(item, 10); // echo cry
                    st.playTutorialVoice("tutorial_voice_026");
                    st.doGiveItems(5789, 6000); // newbie ss
                }

                st.playSound(SOUND_FINISH);
                st.exitCurrentQuest(false);
            }
        } else if (npcId == 30516) {
            if (cond == 1 && st.getQuestItemsCount(GOUPHS_CONTRACT) > 0) {
                htmltext = "trader_reep_q0108_01.htm";
                st.doGiveItems(REEPS_CONTRACT, 1);
                st.doTakeItems(GOUPHS_CONTRACT, 1);
                st.setCond(2);
            } else if (cond >= 2)
                htmltext = "trader_reep_q0108_02.htm";
        } else if (npcId == 30555) {
            if (cond == 2 && st.getQuestItemsCount(REEPS_CONTRACT) == 1)
                htmltext = "carrier_torocco_q0108_01.htm";
            else if (cond == 3 && st.getQuestItemsCount(ELVEN_WINE) > 0)
                htmltext = "carrier_torocco_q0108_03.htm";
            else if (cond == 7 && st.getQuestItemsCount(GEM_BOX1) == 1)
                htmltext = "carrier_torocco_q0108_04.htm";
            else
                htmltext = "carrier_torocco_q0108_05.htm";
        } else if (npcId == 30529) {
            if (cond == 3 && st.getQuestItemsCount(ELVEN_WINE) > 0) {
                st.doTakeItems(ELVEN_WINE, 1);
                st.doGiveItems(BRONPS_DICE, 1);
                htmltext = "miner_maron_q0108_01.htm";
                st.setCond(4);
            } else if (cond == 4)
                htmltext = "miner_maron_q0108_02.htm";
            else
                htmltext = "miner_maron_q0108_03.htm";
        } else if (npcId == 30526) {
            if (cond == 4 && st.getQuestItemsCount(BRONPS_DICE) > 0)
                htmltext = "blacksmith_bronp_q0108_01.htm";
            else if (cond == 5 && st.getQuestItemsCount(BRONPS_CONTRACT) > 0 && (st.getQuestItemsCount(AQUAMARINE) < 10 || st.getQuestItemsCount(CHRYSOBERYL) < 10))
                htmltext = "blacksmith_bronp_q0108_03.htm";
            else if (cond == 6 && st.getQuestItemsCount(BRONPS_CONTRACT) > 0 && st.getQuestItemsCount(AQUAMARINE) == 10 && st.getQuestItemsCount(CHRYSOBERYL) == 10) {
                htmltext = "blacksmith_bronp_q0108_04.htm";
                st.doTakeItems(BRONPS_CONTRACT, -1);
                st.doTakeItems(AQUAMARINE, -1);
                st.doTakeItems(CHRYSOBERYL, -1);
                st.doGiveItems(GEM_BOX1, 1);
                st.setCond(7);
            } else if (cond == 7 && st.getQuestItemsCount(GEM_BOX1) > 0)
                htmltext = "blacksmith_bronp_q0108_05.htm";
            else if (cond == 8 && st.getQuestItemsCount(COAL_PIECE) > 0) {
                htmltext = "blacksmith_bronp_q0108_06.htm";
                st.doTakeItems(COAL_PIECE, 1);
                st.doGiveItems(BRONPS_LETTER, 1);
                st.setCond(9);
            } else if (cond == 9 && st.getQuestItemsCount(BRONPS_LETTER) > 0)
                htmltext = "blacksmith_bronp_q0108_07.htm";
            else
                htmltext = "blacksmith_bronp_q0108_08.htm";
        } else if (npcId == 30521) {
            if (cond == 9 && st.getQuestItemsCount(BRONPS_LETTER) > 0) {
                htmltext = "warehouse_murphrin_q0108_01.htm";
                st.doTakeItems(BRONPS_LETTER, 1);
                st.doGiveItems(BERRY_TART, 1);
                st.setCond(10);
            } else if (cond == 10 && st.getQuestItemsCount(BERRY_TART) > 0)
                htmltext = "warehouse_murphrin_q0108_02.htm";
            else
                htmltext = "warehouse_murphrin_q0108_03.htm";
        } else if (npcId == 30522)
            if (cond == 10 && st.getQuestItemsCount(BERRY_TART) > 0) {
                htmltext = "warehouse_airy_q0108_01.htm";
                st.doTakeItems(BERRY_TART, 1);
                st.doGiveItems(BAT_DIAGRAM, 1);
                st.setCond(11);
            } else if (cond == 11 && st.getQuestItemsCount(BAT_DIAGRAM) > 0)
                htmltext = "warehouse_airy_q0108_02.htm";
            else if (cond == 12 && st.getQuestItemsCount(STAR_DIAMOND) > 0)
                htmltext = "warehouse_airy_q0108_03.htm";
            else
                htmltext = "warehouse_airy_q0108_04.htm";
        return htmltext;
    }

    @Override
    public String onKill(NpcInstance npc, QuestState st) {
        int npcId = npc.getNpcId();
        int cond = st.getCond();
        if (npcId == 20323 || npcId == 20324) {
            if (cond == 5 && st.getQuestItemsCount(BRONPS_CONTRACT) > 0) {
                st.doGiveOnKillItems(AQUAMARINE, 1, 1, 10, 80);
                st.doGiveOnKillItems(CHRYSOBERYL, 1, 1, 10, 80);

                if (st.getQuestItemsCount(AQUAMARINE) >= 10 && st.getQuestItemsCount(CHRYSOBERYL) >= 10)
                    st.setCond(6);
            }
        } else if (npcId == 20480) {
            if (cond != 11 || st.getQuestItemsCount(BAT_DIAGRAM) <= 0)
                return null;

            if (st.doGiveOnKillItems(STAR_DIAMOND, 1, 1, 1, 50)) {
                st.doTakeItems(BAT_DIAGRAM, 1);
                st.setCond(12);
            }
        }
        return null;
    }
}
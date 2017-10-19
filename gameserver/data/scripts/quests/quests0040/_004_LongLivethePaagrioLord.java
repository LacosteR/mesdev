package quests.quests0040;

import l2p.gameserver.model.base.Race;
import l2p.gameserver.model.instances.NpcInstance;
import l2p.gameserver.model.quest.Quest;
import l2p.gameserver.model.quest.QuestState;
import l2p.gameserver.network.serverpackets.ExShowScreenMessage;
import l2p.gameserver.network.serverpackets.ExShowScreenMessage.ScreenMessageAlign;
import l2p.gameserver.scripts.ScriptFile;

/**
 * Рейты не учитываются, награда не стекуемая
 */
public class _004_LongLivethePaagrioLord extends Quest implements ScriptFile {
    int HONEY_KHANDAR = 1541;
    int BEAR_FUR_CLOAK = 1542;
    int BLOODY_AXE = 1543;
    int ANCESTOR_SKULL = 1544;
    int SPIDER_DUST = 1545;
    int DEEP_SEA_ORB = 1546;

    int[][] NPC_GIFTS = {
            {
                    30585,
                    BEAR_FUR_CLOAK
            },
            {
                    30566,
                    HONEY_KHANDAR
            },
            {
                    30562,
                    BLOODY_AXE
            },
            {
                    30560,
                    ANCESTOR_SKULL
            },
            {
                    30559,
                    SPIDER_DUST
            },
            {
                    30587,
                    DEEP_SEA_ORB
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

    public _004_LongLivethePaagrioLord() {
        super(false);
        addStartNpc(30578);

        addTalkId(30559, 30560, 30562, 30566, 30578, 30585, 30587);

        addQuestItem(SPIDER_DUST, ANCESTOR_SKULL, BLOODY_AXE, HONEY_KHANDAR, BEAR_FUR_CLOAK, DEEP_SEA_ORB);
    }

    @Override
    public String onEvent(String event, QuestState st, NpcInstance npc) {
        String htmltext = event;
        if (event.equalsIgnoreCase("30578-03.htm")) {
            st.setCond(1);
            st.setState(STARTED);
            st.playSound(SOUND_ACCEPT);
        }
        return htmltext;
    }

    @Override
    public String onTalk(NpcInstance npc, QuestState st) {
        String htmltext = "noquest";
        int npcId = npc.getNpcId();
        int cond = st.getCond();
        if (npcId == 30578) {
            if (cond == 0) {
                if (st.getPlayer().getRace() != Race.orc) {
                    htmltext = "30578-00.htm";
                    st.exitCurrentQuest(true);
                } else if (st.getPlayer().getLevel() >= 2)
                    htmltext = "30578-02.htm";
                else {
                    htmltext = "30578-01.htm";
                    st.exitCurrentQuest(true);
                }
            } else if (cond == 1)
                htmltext = "30578-04.htm";
            else if (cond == 2) {
                htmltext = "30578-06.htm";
                for (int[] item : NPC_GIFTS)
                    st.doTakeItems(item[1], -1);
                st.doRewardItems(4, 1);
                st.doRewardItems(ADENA_ID, 590 + 1850); // T2
                st.getPlayer().addExpAndSp(RATE_QUESTS_REWARD * 4254, RATE_QUESTS_REWARD * 335);
                if (st.getPlayer().getPcClass().getLevel() == 1 && !st.getPlayer().getVarB("ng1"))
                    st.getPlayer().sendPacket(new ExShowScreenMessage("  Delivery duty complete.\nGo find the Newbie Guide.", 5000, ScreenMessageAlign.TOP_CENTER, true));
                st.playSound(SOUND_FINISH);
                st.exitCurrentQuest(false);
            }
        } else if (cond == 1)
            for (int Id[] : NPC_GIFTS)
                if (Id[0] == npcId) {
                    int item = Id[1];
                    if (st.getQuestItemsCount(item) > 0)
                        htmltext = npc + "-02.htm";
                    else {
                        st.doGiveItems(item, 1);
                        htmltext = npc + "-01.htm";
                        int count = 0;
                        for (int[] item1 : NPC_GIFTS)
                            count += st.getQuestItemsCount(item1[1]);
                        if (count == 6) {
                            st.setCond(2);
                            st.playSound(SOUND_MIDDLE);
                        } else
                            st.playSound(SOUND_ITEMGET);
                    }
                    return htmltext;
                }
        return htmltext;
    }
}
package quests.quests4060;

import l2p.technical.util.Rnd;
import l2p.gameserver.model.instances.NpcInstance;
import l2p.gameserver.model.quest.Quest;
import l2p.gameserver.model.quest.QuestState;
import l2p.gameserver.scripts.ScriptFile;

public class _040_ASpecialOrder extends Quest implements ScriptFile {
    @Override
    public void onLoad() {
    }

    @Override
    public void onReload() {
    }

    @Override
    public void onShutdown() {
    }

    // NPC
    static final int Helvetia = 30081;
    static final int OFulle = 31572;
    static final int Gesto = 30511;

    // Items
    static final int FatOrangeFish = 6452;
    static final int NimbleOrangeFish = 6450;
    static final int OrangeUglyFish = 6451;
    static final int GoldenCobol = 5079;
    static final int ThornCobol = 5082;
    static final int GreatCobol = 5084;

    // Quest items
    static final int FishChest = 12764;
    static final int SeedJar = 12765;
    static final int WondrousCubic = 10632;

    public _040_ASpecialOrder() {
        super(false);
        addStartNpc(Helvetia);

        addQuestItem(FishChest);
        addQuestItem(SeedJar);

        addTalkId(OFulle);
        addTalkId(Gesto);
    }

    @Override
    public String onEvent(String event, QuestState st, NpcInstance npc) {
        String htmltext = event;
        if (event.equals("take")) {
            int rand = Rnd.get(1, 2);
            if (rand == 1) {
                st.setCond(2);
                st.setState(STARTED);
                st.playSound(SOUND_ACCEPT);
                htmltext = "Helvetia-gave-ofulle.htm";
            } else {
                st.setCond(5);
                st.setState(STARTED);
                st.playSound(SOUND_ACCEPT);
                htmltext = "Helvetia-gave-gesto.htm";
            }
        } else if (event.equals("6")) {
            st.setCond(6);
            htmltext = "Gesto-3.htm";
        }
        return htmltext;
    }

    @Override
    public String onTalk(NpcInstance npc, QuestState st) {
        String htmltext = "noquest";
        int npcId = npc.getNpcId();
        int cond = st.getCond();
        if (npcId == Helvetia) {
            if (cond == 0)
                if (st.getPlayer().getLevel() >= 40)
                    htmltext = "Helvetia-1.htm";
                else {
                    htmltext = "Helvetia-level.htm";
                    st.exitCurrentQuest(true);
                }
            else if (cond == 2 || cond == 3 || cond == 5 || cond == 6)
                htmltext = "Helvetia-whereismyfish.htm";
            else if (cond == 4) {
                st.doTakeItems(FishChest, -1);
                st.doRewardItems(WondrousCubic, 1);
                st.exitCurrentQuest(false);
                htmltext = "Helvetia-finish.htm";
            } else if (cond == 7) {
                st.doTakeItems(SeedJar, -1);
                st.doRewardItems(WondrousCubic, 1);
                st.exitCurrentQuest(false);
                htmltext = "Helvetia-finish.htm";
            }
        } else if (npcId == OFulle) {
            if (cond == 2) {
                htmltext = "OFulle-1.htm";
                st.setCond(3);
            } else if (cond == 3) {
                if (st.getQuestItemsCount(FatOrangeFish) >= 10 && st.getQuestItemsCount(NimbleOrangeFish) >= 10 && st.getQuestItemsCount(OrangeUglyFish) >= 10) {
                    st.doTakeItems(FatOrangeFish, 10);
                    st.doTakeItems(NimbleOrangeFish, 10);
                    st.doTakeItems(OrangeUglyFish, 10);
                    st.doGiveItems(FishChest, 1);
                    st.setCond(4);
                    htmltext = "OFulle-2.htm";
                } else
                    htmltext = "OFulle-1.htm";
            } else if (cond == 5 || cond == 6)
                htmltext = "OFulle-3.htm";
        } else if (npcId == Gesto)
            if (cond == 5)
                htmltext = "Gesto-1.htm";
            else if (cond == 6) {
                if (st.getQuestItemsCount(GoldenCobol) >= 40 && st.getQuestItemsCount(ThornCobol) >= 40 && st.getQuestItemsCount(GreatCobol) >= 40) {
                    st.doTakeItems(GoldenCobol, 40);
                    st.doTakeItems(ThornCobol, 40);
                    st.doTakeItems(GreatCobol, 40);
                    st.doGiveItems(SeedJar, 1);
                    st.setCond(7);
                    htmltext = "Gesto-4.htm";
                } else
                    htmltext = "Gesto-5.htm";
            } else if (cond == 7)
                htmltext = "Gesto-6.htm";
        return htmltext;
    }
}
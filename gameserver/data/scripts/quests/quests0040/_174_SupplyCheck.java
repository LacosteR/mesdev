package quests.quests0040;

import l2p.gameserver.model.instances.NpcInstance;
import l2p.gameserver.model.quest.Quest;
import l2p.gameserver.model.quest.QuestState;
import l2p.gameserver.model_pts.pts.PcClass;
import l2p.gameserver.network.serverpackets.ExShowScreenMessage;
import l2p.gameserver.network.serverpackets.ExShowScreenMessage.ScreenMessageAlign;
import l2p.gameserver.scripts.ScriptFile;

public class _174_SupplyCheck extends Quest implements ScriptFile {
    @Override
    public void onLoad() {
    }

    @Override
    public void onReload() {
    }

    @Override
    public void onShutdown() {
    }

    int Marcela = 32173;
    int Benis = 32170; // warehouse keeper
    int Nika = 32167; // grocerer

    int WarehouseManifest = 9792;
    int GroceryStoreManifest = 9793;

    int WoodenBreastplate = 23;
    int WoodenGaiters = 2386;
    int LeatherTunic = 429;
    int LeatherStockings = 464;
    int WoodenHelmet = 43;
    int LeatherShoes = 37;
    int Gloves = 49;

    public _174_SupplyCheck() {
        super(false);

        addStartNpc(Marcela);
        addTalkId(Benis, Nika); //Erinu, Casca
        addQuestItem(WarehouseManifest, GroceryStoreManifest); // WeaponShopManifest, SupplyReport
    }

    @Override
    public String onEvent(String event, QuestState qs, NpcInstance npc) {
        String htmltext = event;
        if (event.equalsIgnoreCase("zerstorer_morsell_q0174_04.htm")) {
            qs.setCond(1);
            qs.setState(STARTED);
            qs.playSound(SOUND_ACCEPT);
        }
        return htmltext;
    }

    @Override
    public String onTalk(NpcInstance npc, QuestState st) {
        String htmltext = "noquest";
        int npcId = npc.getNpcId();
        int cond = st.getCond();

        if (npcId == Marcela) {
            if (cond == 0) {
                if (st.getPlayer().getLevel() == 1) {
                    st.exitCurrentQuest(true);
                    htmltext = "zerstorer_morsell_q0174_02.htm";
                } else
                    htmltext = "zerstorer_morsell_q0174_01.htm";
            } else if (cond == 1)
                htmltext = "zerstorer_morsell_q0174_05.htm";
            else if (cond == 2) {
                st.setCond(3);
                st.doTakeItems(WarehouseManifest, -1);
                htmltext = "zerstorer_morsell_q0174_06.htm";
            } else if (cond == 3)
                htmltext = "zerstorer_morsell_q0174_07.htm";
            else if (cond == 4) {
                if (st.getPlayer().getPcClass().isMage() && !st.getPlayer().getPcClass().equalsOrChildOf(PcClass.orc_mage)) {
                    st.doGiveItems(LeatherTunic, 1);
                    st.doGiveItems(LeatherStockings, 1);
                } else {
                    st.doGiveItems(WoodenBreastplate, 1);
                    st.doGiveItems(WoodenGaiters, 1);
                }
                st.doGiveItems(WoodenHelmet, 1);
                st.doGiveItems(LeatherShoes, 1);
                st.doGiveItems(Gloves, 1);
                st.doRewardItems(ADENA_ID, 2466);
                st.getPlayer().addExpAndSp(RATE_QUESTS_REWARD * 5672, RATE_QUESTS_REWARD * 446);
                if (st.getPlayer().getPcClass().getLevel() == 1 && !st.getPlayer().getVarB("ng1"))
                    st.getPlayer().sendPacket(new ExShowScreenMessage("  Delivery duty complete.\nGo find the Newbie Guide.", 5000, ScreenMessageAlign.TOP_CENTER, true));
                st.exitCurrentQuest(false);
                htmltext = "zerstorer_morsell_q0174_12.htm";
            }
        } else if (npcId == Benis)
            if (cond == 1) {
                st.setCond(2);
                st.doGiveItems(WarehouseManifest, 1);
                htmltext = "warehouse_keeper_benis_q0174_01.htm";
            } else
                htmltext = "warehouse_keeper_benis_q0174_02.htm";

        else if (npcId == Nika)
            if (cond < 3)
                htmltext = "subelder_casca_q0174_01.htm";
            else if (cond == 3) {
                st.setCond(4);
                st.doGiveItems(GroceryStoreManifest, 1);
                htmltext = "trader_neagel_q0174_02.htm";
            } else
                htmltext = "trader_neagel_q0174_03.htm";
        return htmltext;
    }
}
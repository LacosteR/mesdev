package quests.quests4060;

import l2p.technical.util.Rnd;
import l2p.gameserver.model.instances.NpcInstance;
import l2p.gameserver.model.quest.Quest;
import l2p.gameserver.model.quest.QuestState;
import l2p.gameserver.scripts.ScriptFile;

public class _360_PlunderTheirSupplies extends Quest implements ScriptFile {
    //NPC
    private static final int COLEMAN = 30873;

    //MOBS
    private static final int TAIK_SEEKER = 20666;
    private static final int TAIK_LEADER = 20669;

    //QUEST ITEMS
    private static final int SUPPLY_ITEM = 5872;
    private static final int SUSPICIOUS_DOCUMENT = 5871;
    private static final int RECIPE_OF_SUPPLY = 5870;

    //DROP CHANCES
    private static final int ITEM_DROP_SEEKER = 50;
    private static final int ITEM_DROP_LEADER = 65;
    private static final int DOCUMENT_DROP = 5;

    @Override
    public void onLoad() {
    }

    @Override
    public void onReload() {
    }

    @Override
    public void onShutdown() {
    }

    public _360_PlunderTheirSupplies() {
        super(false);
        addStartNpc(COLEMAN);

        addKillId(TAIK_SEEKER);
        addKillId(TAIK_LEADER);

        addQuestItem(SUPPLY_ITEM);
        addQuestItem(SUSPICIOUS_DOCUMENT);
        addQuestItem(RECIPE_OF_SUPPLY);
    }

    @Override
    public String onEvent(String event, QuestState st, NpcInstance npc) {
        String htmltext = event;
        if (event.equalsIgnoreCase("guard_coleman_q0360_04.htm")) {
            st.setCond(1);
            st.setState(STARTED);
            st.playSound(SOUND_ACCEPT);
        } else if (event.equalsIgnoreCase("guard_coleman_q0360_10.htm")) {
            st.playSound(SOUND_FINISH);
            st.exitCurrentQuest(true);
        }
        return htmltext;
    }

    @Override
    public String onTalk(NpcInstance npc, QuestState st) {
        String htmltext = "noquest";
        int id = st.getState();
        long docs = st.getQuestItemsCount(RECIPE_OF_SUPPLY);
        long supplies = st.getQuestItemsCount(SUPPLY_ITEM);
        if (id != STARTED) {
            if (st.getPlayer().getLevel() >= 52)
                htmltext = "guard_coleman_q0360_02.htm";
            else
                htmltext = "guard_coleman_q0360_01.htm";
        } else if (docs > 0 || supplies > 0) {
            long reward = 6000 + supplies * 100 + docs * 6000;
            st.doTakeItems(SUPPLY_ITEM, -1);
            st.doTakeItems(RECIPE_OF_SUPPLY, -1);
            st.doRewardItems(ADENA_ID, reward);
            htmltext = "guard_coleman_q0360_08.htm";
        } else
            htmltext = "guard_coleman_q0360_05.htm";
        return htmltext;
    }

    @Override
    public String onKill(NpcInstance npc, QuestState st) {
        int npcId = npc.getNpcId();
        if (npcId == TAIK_SEEKER)
            st.doGiveOnKillItems(SUPPLY_ITEM, 1, 1, ITEM_DROP_SEEKER);

        if (npcId == TAIK_LEADER)
            st.doGiveOnKillItems(SUPPLY_ITEM, 1, 1, ITEM_DROP_LEADER);

        if (Rnd.chance(DOCUMENT_DROP)) {
            if (st.getQuestItemsCount(SUSPICIOUS_DOCUMENT) < 4)
                st.doGiveItems(SUSPICIOUS_DOCUMENT, 1);
            else {
                st.doTakeItems(SUSPICIOUS_DOCUMENT, -1);
                st.doGiveItems(RECIPE_OF_SUPPLY, 1);
            }
            st.playSound(SOUND_ITEMGET);
        }
        return null;
    }
}
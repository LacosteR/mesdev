package quests.quests4060;

import l2p.gameserver.config.ConfigOthers;
import l2p.gameserver.model.Player;
import l2p.gameserver.model.instances.NpcInstance;
import l2p.gameserver.model.quest.Quest;
import l2p.gameserver.model.quest.QuestState;
import l2p.gameserver.scripts.ScriptFile;

public class _646_SignsOfRevolt extends Quest implements ScriptFile {
    // NPCs
    private static int TORRANT = 32016;
    // Mobs
    private static int Ragna_Orc = 22029; // First in Range
    private static int Ragna_Orc_Sorcerer = 22044; // Last in Range
    private static int Guardian_of_the_Ghost_Town = 22047;
    private static int Varangkas_Succubus = 22049;
    // Items
    private static int Steel = 1880;
    private static int Coarse_Bone_Powder = 1881;
    private static int Leather = 1882;
    // Quest Items
    private static int CURSED_DOLL = 8087;
    // Chances
    private static int CURSED_DOLL_Chance = 75;

    public _646_SignsOfRevolt() {
        super(false);
        addStartNpc(TORRANT);
        for (int Ragna_Orc_id = Ragna_Orc; Ragna_Orc_id <= Ragna_Orc_Sorcerer; Ragna_Orc_id++)
            addKillId(Ragna_Orc_id);
        addKillId(Guardian_of_the_Ghost_Town);
        addKillId(Varangkas_Succubus);
        addQuestItem(CURSED_DOLL);
    }

    private static String doReward(QuestState st, int reward_id, int _count) {
        if (st.getQuestItemsCount(CURSED_DOLL) < 180)
            return null;
        st.doTakeItems(CURSED_DOLL, -1);
        st.doRewardItems(reward_id, _count);
        st.playSound(SOUND_FINISH);
        st.exitCurrentQuest(true);
        return "torant_q0646_0202.htm";
    }

    @Override
    public String onEvent(String event, QuestState st, NpcInstance npc) {
        int _state = st.getState();
        if (event.equalsIgnoreCase("torant_q0646_0103.htm") && _state == CREATED) {
            st.setState(STARTED);
            st.setCond(1);
            st.playSound(SOUND_ACCEPT);
        } else if (event.equalsIgnoreCase("reward_adena") && _state == STARTED)
            return doReward(st, ADENA_ID, 21600);
        else if (event.equalsIgnoreCase("reward_cbp") && _state == STARTED)
            return doReward(st, Coarse_Bone_Powder, RATE_QUESTS_REWARD * 12);
        else if (event.equalsIgnoreCase("reward_steel") && _state == STARTED)
            return doReward(st, Steel, RATE_QUESTS_REWARD * 9);
        else if (event.equalsIgnoreCase("reward_leather") && _state == STARTED)
            return doReward(st, Leather, RATE_QUESTS_REWARD * 20);

        return event;
    }

    @Override
    public String onTalk(NpcInstance npc, QuestState st) {
        String htmltext = "noquest";
        if (npc.getNpcId() != TORRANT)
            return htmltext;
        int _state = st.getState();

        if (_state == CREATED) {
            if (st.getPlayer().getLevel() < 40) {
                htmltext = "torant_q0646_0102.htm";
                st.exitCurrentQuest(true);
            } else {
                htmltext = "torant_q0646_0101.htm";
                st.setCond(0);
            }
        } else if (_state == STARTED)
            htmltext = st.getQuestItemsCount(CURSED_DOLL) >= 180 ? "torant_q0646_0105.htm" : "torant_q0646_0106.htm";

        return htmltext;
    }

    @Override
    public String onKill(NpcInstance npc, QuestState qs) {
        Player player = qs.getRandomPartyMember(STARTED, ConfigOthers.PARTY_DISTRIBUTION_RANGE);
        if (player == null)
            return null;
        QuestState st = player.getQuestState(qs.getQuest().getName());

        if (st.doGiveOnKillItems(CURSED_DOLL, 1, 1, 180, CURSED_DOLL_Chance))
            st.setCond(2);

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
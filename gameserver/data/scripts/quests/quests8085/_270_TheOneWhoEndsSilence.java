package quests.quests8085;

import l2p.technical.util.Rnd;
import l2p.gameserver.model.instances.NpcInstance;
import l2p.gameserver.model.quest.Quest;
import l2p.gameserver.model.quest.QuestState;
import l2p.gameserver.scripts.ScriptFile;
import org.apache.commons.lang3.ArrayUtils;

/**
 * @author pchayka
 */

public class _270_TheOneWhoEndsSilence extends Quest implements ScriptFile {
    private static final int Greymore = 32757;
    private static final int TatteredMonkClothes = 15526;
    private static final int[] LowMobs = {
            22791,
            22790,
            22793
    };
    private static final int[] HighMobs = {
            22794,
            22795,
            22797,
            22798,
            22799,
            22800
    };

    public _270_TheOneWhoEndsSilence() {
        super(false);
        addStartNpc(Greymore);
        addKillId(LowMobs);
        addKillId(HighMobs);
    }

    @Override
    public String onEvent(String event, QuestState st, NpcInstance npc) {
        String htmltext = event;
        if (event.equalsIgnoreCase("greymore_q270_03.htm")) {
            st.setState(STARTED);
            st.setCond(1);
            st.playSound(SOUND_ACCEPT);
        } else if (event.equalsIgnoreCase("showrags")) {
            if (st.getQuestItemsCount(TatteredMonkClothes) < 1)
                htmltext = "greymore_q270_05.htm";
            else if (st.getQuestItemsCount(TatteredMonkClothes) < 100)
                htmltext = "greymore_q270_06.htm";
            else if (st.getQuestItemsCount(TatteredMonkClothes) >= 100)
                htmltext = "greymore_q270_07.htm";
        } else if (event.equalsIgnoreCase("rags100")) {
            htmltext = doReward(st, 100);
        } else if (event.equalsIgnoreCase("rags200")) {
            htmltext = doReward(st, 200);
        } else if (event.equalsIgnoreCase("rags300")) {
            htmltext = doReward(st, 300);
        } else if (event.equalsIgnoreCase("rags400")) {
            htmltext = doReward(st, 400);
        } else if (event.equalsIgnoreCase("rags500")) {
            htmltext = doReward(st, 500);
        } else if (event.equalsIgnoreCase("quit")) {
            htmltext = "greymore_q270_10.htm";
            st.exitCurrentQuest(true);
        }
        return htmltext;
    }

    private int[] recipes = {10373, 10374, 10375, 10376, 10377, 10378, 10379, 10380, 10381}; // 9
    private int[] materials = {10397, 10398, 10399, 10400, 10401, 10402, 10403, 10405}; // 8
    private int[] scrolls = {5593, 5594, 5595, 9898}; // 4

    private String doReward(QuestState st, int clothes) {
        if (st.getQuestItemsCount(TatteredMonkClothes) >= clothes) {
            int recipe_count = clothes == 500 ? 2 : 1;
            int material_count = clothes == 500 ? 2 : 1;
            int scroll_count = clothes == 400 ? 2 : 1;

            st.doTakeItems(TatteredMonkClothes, clothes);
            int rnd_r = 0, rnd_m = 0, rnd_s = 0;
            switch (clothes) {
                case 100:
                    rnd_r = Rnd.get(1, 9);
                    break;
                case 200:
                    rnd_r = Rnd.get(1, 9) - 1;
                    rnd_s = Rnd.get(1, 4) - 1;
                    break;
                case 300:
                case 400:
                case 500:
                    rnd_r = Rnd.get(1, 9) - 1;
                    rnd_m = Rnd.get(1, 8) - 1;
                    rnd_s = Rnd.get(1, 4) - 1;
                    break;
            }

            if (rnd_m == 0 && rnd_s == 0) {
                int rnd = Rnd.get(1, 21);
                if (rnd > 17)
                    st.doGiveItems(scrolls[rnd - 17 - 1], scroll_count);
                else if (rnd > 9)
                    st.doGiveItems(materials[rnd - 9 - 1], material_count);
                else
                    st.doGiveItems(recipes[rnd - 1], recipe_count);
            } else if (rnd_m == 0) {
                int rnd = Rnd.get(1, 17);
                if (rnd > 9)
                    st.doGiveItems(materials[rnd - 9 - 1], material_count);
                else
                    st.doGiveItems(recipes[rnd - 1], recipe_count);

                st.doGiveItems(scrolls[rnd_s], scroll_count);
            } else {
                st.doGiveItems(recipes[rnd_r], recipe_count);
                st.doGiveItems(materials[rnd_m], material_count);
                st.doGiveItems(scrolls[rnd_s], scroll_count);
            }
            return "greymore_q270_09.htm";
        } else
            return "greymore_q270_08.htm";
    }

    @Override
    public String onTalk(NpcInstance npc, QuestState st) {
        String htmltext = "noquest";
        int cond = st.getCond();
        if (npc.getNpcId() == Greymore) {
            if (cond == 0) {
                QuestState qs = st.getPlayer().getQuestState(_10288_SecretMission.class);
                if (st.getPlayer().getLevel() >= 82 && qs != null && qs.isCompleted())
                    htmltext = "greymore_q270_01.htm";
                else {
                    htmltext = "greymore_q270_00.htm";
                    st.exitCurrentQuest(true);
                }
            } else if (cond == 1)
                htmltext = "greymore_q270_04.htm";
        }
        return htmltext;
    }

    @Override
    public String onKill(NpcInstance npc, QuestState st) {
        int cond = st.getCond();
        if (cond == 1) {
            if (ArrayUtils.contains(LowMobs, npc.getNpcId()))
                st.doGiveOnKillItems(TatteredMonkClothes, 1, 1, 40);
            else if (ArrayUtils.contains(HighMobs, npc.getNpcId()))
                st.doGiveOnKillItems(TatteredMonkClothes, 1, 1, 100);
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
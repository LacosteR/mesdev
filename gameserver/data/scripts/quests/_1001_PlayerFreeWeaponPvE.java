package quests;

import l2p.gameserver.instancemanager.QuestManager;
import l2p.gameserver.listener.actor.OnKillListener;
import l2p.gameserver.listener.actor.player.OnPlayerLvlUpListener;
import l2p.gameserver.model.Creature;
import l2p.gameserver.model.Player;
import l2p.gameserver.model.actor.listener.CharListenerList;
import l2p.gameserver.model.instances.NpcInstance;
import l2p.gameserver.model.quest.Quest;
import l2p.gameserver.model.quest.QuestState;
import l2p.gameserver.scripts.ScriptFile;
import l2p.technical.util.Rnd;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
/**
 * @author : Alice
 * @date : 09.09.2017
 * @time : 8:23
 * <p/>
 */
public class _1001_PlayerFreeWeaponPvE extends Quest implements ScriptFile, OnPlayerLvlUpListener, OnKillListener {
    private static final Logger log = LoggerFactory.getLogger(_1001_PlayerFreeWeaponPvE.class);

    // ITEMS
    private static int FIRST_ITEM = 14859;
    private static int SECOND_ITEM = 14859;
    private static int THIRD_ITEM = 14859;

    private static int WEAPON_A = 14859;
    private static int WEAPON_S = 14859;
    private static int WEAPON_S84 = 14859;

    private static int FIRST_ITEM_COUNT = 18;
    private static int SECOND_ITEM_COUNT = 26;
    private static int THIRD_ITEM_COUNT = 35;

    private static int FIRST_ITEM_CHANCE = 33;
    private static int SECOND_ITEM_CHANCE = 22;
    private static int THIRD_ITEM_CHANCE = 11;

    @Override
    public void onPlayerLvlUp(Player player) {
        if (player.getActiveClassId() != player.getBaseClassId()) {  // если это саб, то лесом
            return;
        }
        if(player.getLevel() >= 76) {
            Quest q = QuestManager.getQuest(1001);
            if (q != null) {
                player.processQuestEvent(q.getName(), "QS76", null);
            }
        } else if(player.getLevel() >= 61) {
            Quest q = QuestManager.getQuest(1001);
            if (q != null) {
                player.processQuestEvent(q.getName(), "QS61", null);
            }
        } else if(player.getLevel() >= 52) {
            Quest q = QuestManager.getQuest(1001);
            if (q != null) {
                player.processQuestEvent(q.getName(), "QS52", null);
            }
        }
    }

    public _1001_PlayerFreeWeaponPvE() {
        super(true);

        CharListenerList.addGlobal(this);
        addQuestItem(FIRST_ITEM);
        addQuestItem(SECOND_ITEM);
        addQuestItem(THIRD_ITEM);
    }

    @Override
    public String onEvent(String event, QuestState st, NpcInstance npc) {
        Player player = st.getPlayer();
        if (player == null) {
            return null;
        }

        if (event.startsWith("QS52")) { // выдаём квест на мобов на 52-м уровне
            st.setCond(1);
            st.setState(STARTED);
            st.playSound(SOUND_ACCEPT);
        } else if(event.startsWith("QS52M")) {  // выдаём награду за выполнение 52-61 уровней
            st.setCond(2);
            st.playSound(SOUND_MIDDLE);
            st.doRewardItems(WEAPON_A, 1);
            st.addExpAndSp(RATE_QUESTS_REWARD * 697040, RATE_QUESTS_REWARD * 54887);
        } else if (event.startsWith("QS61")) { // выдаём квест на мобов на 61-м уровне
            st.setCond(3);
            st.playSound(SOUND_ACCEPT);
        } else if(event.startsWith("QS61M")) { // выдаём награду за выполнение 61-76 уровней
            st.setCond(4);
            st.playSound(SOUND_MIDDLE);
            st.doRewardItems(WEAPON_S, 1);
            st.addExpAndSp(RATE_QUESTS_REWARD * 1015973, RATE_QUESTS_REWARD * 102802);
        } else if (event.startsWith("QS76")) { // выдаём квест на мобов на 76-м уровне
            st.setCond(5);
            st.playSound(SOUND_ACCEPT);
        } else if (event.startsWith("QS76M")) {  // выдаём награду за выполнение 76+ уровней
            st.setCond(6);
            st.playSound(SOUND_FINISH);
            st.doRewardItems(WEAPON_S84, 1);
            st.addExpAndSp(RATE_QUESTS_REWARD * 1319736, RATE_QUESTS_REWARD * 103553);
            st.exitCurrentQuest(false);
        }

        return null;
    }

    @Override
    public void onKill(Creature actor, Creature victim) {
        if(!actor.isPlayer() || !victim.isMonster()) {
            return;
        }

        Player player = actor.getPlayer();
        QuestState qs = player.getQuestState("_1001_PlayerFreeWeaponPvE");
        if(qs.getCond() == 1) {
            if(victim.getLevel() < 52) {
                return;
            }
            if(Rnd.chance(FIRST_ITEM_CHANCE)) {
                qs.doGiveItems(FIRST_ITEM, 1);
            }
            if(qs.getQuestItemsCount(FIRST_ITEM) >= FIRST_ITEM_COUNT) {
                qs.doTakeItems(FIRST_ITEM);
                player.processQuestEvent(getName(), "QS52M", null);
            }
        } else if(qs.getCond() == 3) {
            if(victim.getLevel() < 61) {
                return;
            }
            if(Rnd.chance(SECOND_ITEM_CHANCE)) {
                qs.doGiveItems(SECOND_ITEM, 1);
            }
            if(qs.getQuestItemsCount(SECOND_ITEM) >= SECOND_ITEM_COUNT) {
                qs.doTakeItems(SECOND_ITEM);
                player.processQuestEvent(getName(), "QS61M", null);
            }
        } else if(qs.getCond() == 5) {
            if(victim.getLevel() < 76) {
                return;
            }
            if(Rnd.chance(THIRD_ITEM_CHANCE)) {
                qs.doGiveItems(THIRD_ITEM, 1);
            }
            if(qs.getQuestItemsCount(THIRD_ITEM) >= THIRD_ITEM_COUNT) {
                qs.doTakeItems(THIRD_ITEM);
                player.processQuestEvent(getName(), "QS76M", null);
            }
        }
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

    @Override
    public boolean ignorePetOrSummon() {
        return true;
    }
}

package ai;

import l2p.gameserver.ai.Mystic;
import l2p.gameserver.instancemanager.QuestManager;
import l2p.gameserver.model.Player;
import l2p.gameserver.model.World;
import l2p.gameserver.model.instances.NpcInstance;
import l2p.gameserver.model.quest.Quest;
import l2p.gameserver.model.quest.QuestState;
import quests.quests0040._024_InhabitantsOfTheForestOfTheDead;

/**
 * @author VISTALL
 */
public class Quest024Mystic extends Mystic {
    public Quest024Mystic(NpcInstance actor) {
        super(actor);
    }

    @Override
    protected boolean thinkActive() {
        Quest q = QuestManager.getQuest(_024_InhabitantsOfTheForestOfTheDead.class);
        if (q != null)
            for (Player player : World.getAroundPlayers(getActor(), 300, 200)) {
                QuestState questState = player.getQuestState(_024_InhabitantsOfTheForestOfTheDead.class);
                if (questState != null && questState.getCond() == 3)
                    q.notifyEvent("seePlayer", questState, getActor());
            }
        return super.thinkActive();
    }
}
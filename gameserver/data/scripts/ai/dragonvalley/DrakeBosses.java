package ai.dragonvalley;

import l2p.gameserver.ai.Fighter;
import l2p.gameserver.config.ConfigOthers;
import l2p.gameserver.model.AggroList.HateInfo;
import l2p.gameserver.model.Creature;
import l2p.gameserver.model.Playable;
import l2p.gameserver.model.Player;
import l2p.gameserver.model.PlayerGroup;
import l2p.gameserver.model.instances.NpcInstance;
import l2p.gameserver.model.quest.QuestState;
import l2p.gameserver.utils.NpcUtils;
import l2p.gameserver.utils.PositionUtils;
import quests.quests8085._456_DontKnowDontCare;

import java.util.Map;

/**
 * @author pchayka
 */
public class DrakeBosses extends Fighter {
    public DrakeBosses(NpcInstance actor) {
        super(actor);
    }

    @Override
    protected void onEvtDead(Creature killer) {
        NpcInstance corpse = null;
        switch (getActor().getNpcId()) {
            case 25725:
                corpse = NpcUtils.spawnSingle(32884, getActor().getPositionComponent().getLoc(), 300000);
                break;
            case 25726:
                corpse = NpcUtils.spawnSingle(32885, getActor().getPositionComponent().getLoc(), 300000);
                break;
            case 25727:
                corpse = NpcUtils.spawnSingle(32886, getActor().getPositionComponent().getLoc(), 300000);
                break;
        }

        if (killer != null && corpse != null) {
            final Player player = killer.getPlayer();
            if (player != null) {
                PlayerGroup pg = player.getPlayerGroup();
                if (pg != null) {
                    QuestState qs;
                    Map<Playable, HateInfo> aggro = getActor().getAggroList().getPlayableMap();
                    for (Player pl : pg) {
                        if (pl != null && !pl.isDead() && aggro.containsKey(pl)
                                && (PositionUtils.isInRange(getActor(), pl, ConfigOthers.PARTY_DISTRIBUTION_RANGE, true)
                                || PositionUtils.isInRange(getActor(), killer, ConfigOthers.PARTY_DISTRIBUTION_RANGE, true)))
                        {
                            qs = pl.getQuestState(_456_DontKnowDontCare.class);
                            if (qs != null && qs.getCond() == 1)
                                qs.set("RaidKilled", corpse.getObjectId());
                        }
                    }
                }
            }
        }

        super.onEvtDead(killer);
        getActor().endDecayTask();
    }
}
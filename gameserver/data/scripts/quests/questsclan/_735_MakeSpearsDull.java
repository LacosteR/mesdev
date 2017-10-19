package quests.questsclan;

import l2p.gameserver.model_pts.pts.PcClass;
import l2p.gameserver.network.serverpackets.components.NpcString;
import quests.Dominion_KillSpecialUnitQuest;

/**
 * @author VISTALL
 * @date 16:14/12.04.2011
 */
public class _735_MakeSpearsDull extends Dominion_KillSpecialUnitQuest {
    public _735_MakeSpearsDull() {
        super();
    }

    @Override
    protected NpcString startNpcString() {
        return NpcString.DEFEAT_S1_WARRIORS_AND_ROGUES;
    }

    @Override
    protected NpcString progressNpcString() {
        return NpcString.YOU_HAVE_DEFEATED_S2_OF_S1_WARRIORS_AND_ROGUES;
    }

    @Override
    protected NpcString doneNpcString() {
        return NpcString.YOU_WEAKENED_THE_ENEMYS_ATTACK;
    }

    @Override
    protected int getRandomMin() {
        return 15;
    }

    @Override
    protected int getRandomMax() {
        return 20;
    }

    @Override
    protected PcClass[] getTargetPcClasss() {
        return new PcClass[]{
                PcClass.gladiator,
                PcClass.warlord,
                PcClass.treasure_hunter,
                PcClass.hawkeye,
                PcClass.plain_walker,
                PcClass.silver_ranger,
                PcClass.abyss_walker,
                PcClass.phantom_ranger,
                PcClass.destroyer,
                PcClass.tyrant,
                PcClass.bounty_hunter,
                PcClass.duelist,
                PcClass.dreadnought,
                PcClass.sagittarius,
                PcClass.adventurer,
                PcClass.wind_rider,
                PcClass.moonlight_sentinel,
                PcClass.ghost_hunter,
                PcClass.ghost_sentinel,
                PcClass.titan,
                PcClass.grand_khavatari,
                PcClass.fortune_seeker,
                PcClass.berserker,
                PcClass.f_soul_breaker,
                PcClass.m_soul_breaker,
                PcClass.arbalester,
                PcClass.doombringer,
                PcClass.m_soul_hound,
                PcClass.f_soul_hound,
                PcClass.trickster
        };
    }
}

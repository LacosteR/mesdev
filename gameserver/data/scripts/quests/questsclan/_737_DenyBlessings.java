package quests.questsclan;

import l2p.gameserver.model_pts.pts.PcClass;
import l2p.gameserver.network.serverpackets.components.NpcString;
import quests.Dominion_KillSpecialUnitQuest;

/**
 * @author VISTALL
 * @date 16:19/12.04.2011
 */
public class _737_DenyBlessings extends Dominion_KillSpecialUnitQuest {
    public _737_DenyBlessings() {
        super();
    }

    @Override
    protected NpcString startNpcString() {
        return NpcString.DEFEAT_S1_HEALERS_AND_BUFFERS;
    }

    @Override
    protected NpcString progressNpcString() {
        return NpcString.YOU_HAVE_DEFEATED_S2_OF_S1_HEALERS_AND_BUFFERS;
    }

    @Override
    protected NpcString doneNpcString() {
        return NpcString.YOU_HAVE_WEAKENED_THE_ENEMYS_SUPPORT;
    }

    @Override
    protected int getRandomMin() {
        return 3;
    }

    @Override
    protected int getRandomMax() {
        return 8;
    }

    @Override
    protected PcClass[] getTargetPcClasss() {
        return new PcClass[]{
                PcClass.bishop,
                PcClass.prophet,
                PcClass.elder,
                PcClass.elder,
                PcClass.shillien_elder,
                PcClass.cardinal,
                PcClass.hierophant,
                PcClass.evas_saint,
                PcClass.shillien_saint,
                PcClass.doomcryer
        };
    }
}

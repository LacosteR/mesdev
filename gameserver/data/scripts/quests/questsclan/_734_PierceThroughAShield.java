package quests.questsclan;

import l2p.gameserver.model_pts.pts.PcClass;
import l2p.gameserver.network.serverpackets.components.NpcString;
import quests.Dominion_KillSpecialUnitQuest;

/**
 * @author VISTALL
 * @date 15:56/12.04.2011
 */
public class _734_PierceThroughAShield extends Dominion_KillSpecialUnitQuest {
    public _734_PierceThroughAShield() {
        super();
    }

    @Override
    protected NpcString startNpcString() {
        return NpcString.DEFEAT_S1_ENEMY_KNIGHTS;
    }

    @Override
    protected NpcString progressNpcString() {
        return NpcString.YOU_HAVE_DEFEATED_S2_OF_S1_KNIGHTS;
    }

    @Override
    protected NpcString doneNpcString() {
        return NpcString.YOU_WEAKENED_THE_ENEMYS_DEFENSE;
    }

    @Override
    protected int getRandomMin() {
        return 10;
    }

    @Override
    protected int getRandomMax() {
        return 15;
    }

    @Override
    protected PcClass[] getTargetPcClasss() {
        return new PcClass[]{
                PcClass.dark_avenger,
                PcClass.hell_knight,
                PcClass.paladin,
                PcClass.phoenix_knight,
                PcClass.temple_knight,
                PcClass.evas_templar,
                PcClass.shillien_knight,
                PcClass.shillien_templar
        };
    }
}

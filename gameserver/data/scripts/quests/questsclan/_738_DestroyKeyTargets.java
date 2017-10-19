package quests.questsclan;

import l2p.gameserver.model_pts.pts.PcClass;
import l2p.gameserver.network.serverpackets.components.NpcString;
import quests.Dominion_KillSpecialUnitQuest;

/**
 * @author VISTALL
 * @date 16:23/12.04.2011
 */
public class _738_DestroyKeyTargets extends Dominion_KillSpecialUnitQuest {
    public _738_DestroyKeyTargets() {
        super();
    }

    @Override
    protected NpcString startNpcString() {
        return NpcString.DEFEAT_S1_WARSMITHS_AND_OVERLORDS;
    }

    @Override
    protected NpcString progressNpcString() {
        return NpcString.YOU_HAVE_DEFEATED_S2_OF_S1_WARSMITHS_AND_OVERLORDS;
    }

    @Override
    protected NpcString doneNpcString() {
        return NpcString.YOU_DESTROYED_THE_ENEMYS_PROFESSIONALS;
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
                PcClass.necromancer,
                PcClass.swordsinger,
                PcClass.bladedancer,
                PcClass.overlord,
                PcClass.warsmith,
                PcClass.soultaker,
                PcClass.sword_muse,
                PcClass.spectral_dancer,
                PcClass.dominator,
                PcClass.maestro,
                PcClass.inspector,
                PcClass.judicator
        };
    }
}

package quests.questsclan;

import l2p.gameserver.model_pts.pts.PcClass;
import l2p.gameserver.network.serverpackets.components.NpcString;
import quests.Dominion_KillSpecialUnitQuest;

/**
 * @author VISTALL
 * @date 16:18/12.04.2011
 */
public class _736_WeakenTheMagic extends Dominion_KillSpecialUnitQuest {
    public _736_WeakenTheMagic() {
        super();
    }

    @Override
    protected NpcString startNpcString() {
        return NpcString.DEFEAT_S1_WIZARDS_AND_SUMMONERS;
    }

    @Override
    protected NpcString progressNpcString() {
        return NpcString.YOU_HAVE_DEFEATED_S2_OF_S1_ENEMIES;
    }

    @Override
    protected NpcString doneNpcString() {
        return NpcString.YOU_WEAKENED_THE_ENEMYS_MAGIC;
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
                PcClass.sorcerer,
                PcClass.warlock,
                PcClass.spellsinger,
                PcClass.elemental_summoner,
                PcClass.spellhowler,
                PcClass.phantom_summoner,
                PcClass.archmage,
                PcClass.arcana_lord,
                PcClass.mystic_muse,
                PcClass.elemental_master,
                PcClass.storm_screamer,
                PcClass.spectral_master
        };
    }
}

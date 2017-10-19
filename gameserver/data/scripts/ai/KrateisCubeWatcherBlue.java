package ai;

import l2p.gameserver.ThreadPoolManager;
import l2p.gameserver.ai.DefaultAI;
import l2p.gameserver.data.xml.holder.NpcHolder;
import l2p.gameserver.managers.factory.NpcFactory;
import l2p.gameserver.model.Creature;
import l2p.gameserver.model.Skill;
import l2p.gameserver.model.World;
import l2p.gameserver.model.instances.NpcInstance;
import l2p.gameserver.network.serverpackets.SystemMessage2;
import l2p.gameserver.network.serverpackets.components.SystemMsg;
import l2p.gameserver.templates.npc.NpcTemplate;
import l2p.gameserver.utils.GArray;
import l2p.technical.threading.RunnableImpl;
import l2p.technical.util.Rnd;

/**
 * Author: VISTALL
 * Date:  9:03/17.11.2010
 * npc Id : 18602
 */
public class KrateisCubeWatcherBlue extends DefaultAI {
    private static final int RESTORE_CHANCE = 60;

    public KrateisCubeWatcherBlue(NpcInstance actor) {
        super(actor);
        AI_TASK_ACTIVE_DELAY = 3000;
    }

    @Override
    protected void onEvtAttacked(Creature attacker, int damage, Skill skill) {
    }

    @Override
    protected void onEvtThink() {
        NpcInstance actor = getActor();
        GArray<Creature> around = World.getAroundCharacters(actor, 600, 300);
        if (around.isEmpty())
            return;

        for (Creature cha : around)
            if (cha.isPlayer() && !cha.isDead() && Rnd.chance(RESTORE_CHANCE)) {
                double valCP = cha.getMaxCp() - cha.getCurrentCp();
                if (valCP > 0) {
                    cha.setCurrentCp(valCP + cha.getCurrentCp());
                    cha.sendPacket(new SystemMessage2(SystemMsg.S1_CP_HAS_BEEN_RESTORED).addInteger(Math.round(valCP)));
                }

                double valHP = cha.getMaxHp() - cha.getCurrentHp();
                if (valHP > 0) {
                    cha.setCurrentHp(valHP + cha.getCurrentHp(), false);
                    cha.sendPacket(new SystemMessage2(SystemMsg.S1_HP_HAS_BEEN_RESTORED).addInteger(Math.round(valHP)));
                }

                double valMP = cha.getMaxMp() - cha.getCurrentMp();
                if (valMP > 0) {
                    cha.setCurrentMp(valMP + cha.getCurrentMp());
                    cha.sendPacket(new SystemMessage2(SystemMsg.S1_MP_HAS_BEEN_RESTORED).addInteger(Math.round(valMP)));
                }
            }
    }

    @Override
    public void onEvtDead(Creature killer) {
        final NpcInstance actor = getActor();
        super.onEvtDead(killer);

        actor.deleteMe();
        ThreadPoolManager.getInstance().schedule(new RunnableImpl() {
            @Override
            public void runImpl() throws Exception {
                NpcTemplate template = NpcHolder.getInstance().getTemplate(18601);
                if (template != null) {
                    NpcInstance a = NpcFactory.createNpc(template);
                    a.setCurrentHpMp(a.getMaxHp(), a.getMaxMp());
                    a.spawnMe(actor.getPositionComponent().getLoc());
                }
            }
        }, 10000L);
    }
}

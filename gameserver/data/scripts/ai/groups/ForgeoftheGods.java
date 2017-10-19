package ai.groups;

import l2p.gameserver.ai.CtrlEvent;
import l2p.gameserver.ai.Fighter;
import l2p.gameserver.managers.factory.NpcFactory;
import l2p.gameserver.model.Creature;
import l2p.gameserver.model.Player;
import l2p.gameserver.model.Skill;
import l2p.gameserver.model.World;
import l2p.gameserver.model.instances.NpcInstance;
import l2p.gameserver.tables.SkillTable;
import l2p.gameserver.utils.PositionUtils;
import l2p.technical.util.Rnd;
import org.apache.commons.lang3.ArrayUtils;

import java.util.List;

/**
 * @author n0nam3
 * @date 15/10/2010
 * @comment Групповой AI для зоны Forge of the Gods
 */

public class ForgeoftheGods extends Fighter {
    private static final int[] RANDOM_SPAWN_MOBS = {18799, 18800, 18801, 18802, 18803};
    private static final int[] FOG_MOBS = {
            22634,
            22635,
            22636,
            22637,
            22638,
            22639,
            22640,
            22641,
            22642,
            22643,
            22644,
            22645,
            22646,
            22647,
            22648,
            22649};
    private static final int TAR_BEETLE = 18804;

    private static int TAR_BEETLE_ACTIVATE_SKILL_CHANGE = 2; // chance for activate skills
    private static int TAR_BEETLE_SEARCH_RADIUS = 500; // search around players

    public ForgeoftheGods(NpcInstance actor) {
        super(actor);

        if (actor.getNpcId() == TAR_BEETLE) {
            AI_TASK_ATTACK_DELAY = 1250;
            actor.startDamageBlockedHp();
            actor.startDebuffImmunity();
            actor.setHasChatWindow(false);
        } else if (ArrayUtils.contains(RANDOM_SPAWN_MOBS, actor.getNpcId()))
            actor.startImmobilized();
    }

    @Override
    protected boolean thinkActive() {
        NpcInstance actor = getActor();

        if (actor.getNpcId() != TAR_BEETLE)
            return super.thinkActive();

        if (actor.isDead() || !Rnd.chance(TAR_BEETLE_ACTIVATE_SKILL_CHANGE))
            return false;

        List<Player> players = World.getAroundPlayers(actor, TAR_BEETLE_SEARCH_RADIUS, 200);
        if (players == null || players.size() < 1)
            return false;
        actor.doCast(SkillTable.getInstance().getInfo(6142, Rnd.get(1, 3)), players.get(Rnd.get(players.size())), false);
        return true;
    }

    @Override
    protected void onEvtDead(Creature killer) {
        NpcInstance actor = getActor();

        if (ArrayUtils.contains(FOG_MOBS, actor.getNpcId()))
            try {
                NpcInstance npc = NpcFactory.createNpc(RANDOM_SPAWN_MOBS[Rnd.get(RANDOM_SPAWN_MOBS.length)]);
                npc.setSpawnedLoc(actor.getPositionComponent().getLoc());
                npc.getPositionComponent().setReflection(actor.getPositionComponent().getReflection());
                npc.setCurrentHpMp(npc.getMaxHp(), npc.getMaxMp(), true);
                npc.spawnMe(npc.getSpawnedLoc());
                npc.getAI().notifyEvent(CtrlEvent.EVT_AGGRESSION, killer, Rnd.get(1, 100));
            } catch (Exception e) {
                e.printStackTrace();
            }

        super.onEvtDead(killer);
    }

    @Override
    protected void onEvtAttacked(Creature attacker, int damage, Skill skill) {
        if (getActor().getNpcId() == TAR_BEETLE)
            return;
        super.onEvtAttacked(attacker, damage, skill);
    }

    @Override
    protected void onEvtAggression(Creature target, int aggro) {
        if (getActor().getNpcId() == TAR_BEETLE)
            return;
        super.onEvtAggression(target, aggro);
    }

    @Override
    protected boolean checkTarget(Creature target, int range) {
        NpcInstance actor = getActor();
        if (ArrayUtils.contains(RANDOM_SPAWN_MOBS, getActor().getNpcId()) && target != null && !PositionUtils.isInRange(actor, target, actor.getAggroRange(), false)) {
            actor.getAggroList().remove(target, true);
            return false;
        }
        return super.checkTarget(target, range);
    }

    @Override
    protected boolean randomWalk() {
        return ArrayUtils.contains(RANDOM_SPAWN_MOBS, getActor().getNpcId()) || getActor().getNpcId() == TAR_BEETLE ? false : true;
    }
}
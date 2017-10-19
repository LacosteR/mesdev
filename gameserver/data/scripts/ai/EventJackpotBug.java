package ai;

import l2p.gameserver.ai.CtrlIntention;
import l2p.gameserver.ai.DefaultAI;
import l2p.gameserver.model.Creature;
import l2p.gameserver.model.GameObject;
import l2p.gameserver.model.Skill;
import l2p.gameserver.model.World;
import l2p.gameserver.model.instances.NpcInstance;
import l2p.gameserver.model.items.ItemInstance;
import l2p.gameserver.network.serverpackets.components.ChatType;
import l2p.gameserver.scripts.Functions;
import l2p.gameserver.tables.SkillTable;
import l2p.gameserver.utils.PositionUtils;
import l2p.technical.util.Rnd;

import java.util.HashMap;
import java.util.Map;

/**
 * @author : Alice
 * @date : 26.11.16
 * @time : 7:22
 * <p/>
 * desc
 */
public class EventJackpotBug extends DefaultAI {
    public int RandRate = -1;

    private long nextItemLook;

    public EventJackpotBug(NpcInstance actor) {
        super(actor);
    }

    @Override
    public void onEvtSpawn() {
        super.onEvtSpawn();
        actor.decayMe();
        actor.i_ai1 = 0;
        actor.i_ai3 = 0;
        actor.i_ai5 = 0;

        actor.i_ai2 = Rnd.get(RandRate) + 1;
        if (Rnd.get(-1) == actor.i_ai2) {
            actor.spawnMe();
            Functions.npcSay(actor, ChatType.NPC_ALL, 1900139);
            actor.i_ai5 = 1;
            addTimer(7778, 10000);
            addTimer(7779, 600000);
        }
    }

    @Override
    protected void onEvtAggression(Creature attacker, int aggro) {
    }

    @Override
    protected void onEvtAttacked(Creature attacker, int damage, Skill skill) {
    }

    @Override
    protected boolean thinkActive() {
        if (actor.isDead())
            return true;

        if (!actor.isMoving && actor.isVisible() && nextItemLook < System.currentTimeMillis()) {
            HashMap<ItemInstance, Integer> items = new HashMap<ItemInstance, Integer>();

            for (GameObject obj : World.getAroundObjects(actor, 500, 100))
                if (obj instanceof ItemInstance && ((ItemInstance) obj).isStackable())
                    items.put((ItemInstance) obj, (int) PositionUtils.getDistance3D(actor, obj));

            if (items.size() > 0) {
                for (Map.Entry<ItemInstance, Integer> entry : items.entrySet()) {
                    changeIntention(CtrlIntention.AI_INTENTION_FOLLOW, entry.getKey(), 10);
                    //addMoveToDesire(entry.getKey().getX(), entry.getKey().getY(), entry.getKey().getZ(), 100000 - entry.getValue());
                }
            }

            nextItemLook = System.currentTimeMillis() + 10000;
        }

        return false;
    }

    @Override
    protected void onEvtArrived() {
        super.onEvtArrived();

        ItemInstance closestItem = null;
        int minDist = Integer.MAX_VALUE;

        for (GameObject obj : World.getAroundObjects(actor, 20, 100))
            if (obj instanceof ItemInstance && ((ItemInstance) obj).isStackable() && PositionUtils.getDistance3D(actor, obj) < minDist) {
                minDist = (int) PositionUtils.getDistance3D(actor, obj);
                closestItem = (ItemInstance) obj;
            }

        if (closestItem != null) {
            clearTasks();
            closestItem.deleteMe();
            actor.i_ai0++;
            if (actor.i_ai0 == 10) {
                Functions.npcSay(actor, ChatType.NPC_ALL, 1900146);
                actor.doDecay();
            } else {
                int i0 = Rnd.get(100);
                if (i0 < 50) {
                    if (Rnd.chance(50))
                        onIntentionCast(SkillTable.getInstance().getInfo(5758, 1), actor);
                    actor.i_ai1++;
                    Functions.npcSay(actor, ChatType.NPC_ALL, 1900142);

                    if (actor.i_ai1 >= 2 && actor.i_ai1 < 5) {
                        if (actor.i_ai3 == 0) {
                            if (Rnd.chance(22))
                                onIntentionCast(SkillTable.getInstance().getInfo(23325, 1), actor);
                            actor.i_ai3 = 1;
                        }
                    } else if (actor.i_ai1 >= 5) {
                        if (actor.i_ai3 == 1) {
                            if (Rnd.chance(22))
                                onIntentionCast(SkillTable.getInstance().getInfo(23325, 1), actor);
                            actor.i_ai3 = 2;
                        }
                    }
                } else if (i0 < 99) {
                    if (Rnd.chance(22))
                        onIntentionCast(SkillTable.getInstance().getInfo(6037, 1), actor);
                    actor.i_ai1--;
                    Functions.npcSay(actor, ChatType.NPC_ALL, 1900143);

                    if (actor.i_ai1 >= 2 && actor.i_ai1 < 5) {
                        if (actor.i_ai3 == 2) {
                            if (Rnd.chance(22))
                                onIntentionCast(SkillTable.getInstance().getInfo(23326, 1), actor);
                            actor.i_ai3 = 1;
                        }
                    } else if (actor.i_ai1 < 5 && actor.i_ai3 == 1) {
                        if (Rnd.chance(22))
                            onIntentionCast(SkillTable.getInstance().getInfo(23326, 1), actor);
                        actor.i_ai3 = 0;
                    }
                }
            }
        }
    }

    @Override
    protected void onEvtTimer(int timerId, Object arg1, Object arg2) {
        if (timerId == 7778)
            Functions.npcSay(actor, ChatType.NPC_ALL, 1900140);
        else if (timerId == 7779)
            actor.doDecay();
    }

    @Override
    protected void onEvtDead(Creature killer) {
        super.onEvtDead(killer);

        if (actor.i_ai5 == 1) {
            if (actor.i_ai1 >= 5)
                actor.createOnePrivate(2503, "ai_g_jackpot_prize", 0, actor.getPositionComponent().getX(), actor.getPositionComponent().getY(), actor.getPositionComponent().getZ(), 0, actor.getLevel(), actor.i_ai1, 0);
            else
                actor.createOnePrivate(2502, "ai_g_jackpot_prize", 0, actor.getPositionComponent().getX(), actor.getPositionComponent().getY(), actor.getPositionComponent().getZ(), 0, actor.getLevel(), actor.i_ai1, 0);
        }
    }
}

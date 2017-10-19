package ai;

import l2p.gameserver.ai.CharacterAI;
import l2p.gameserver.model.Creature;
import l2p.gameserver.model.Player;
import l2p.gameserver.model.Skill;
import l2p.gameserver.model.instances.NpcInstance;
import l2p.gameserver.network.serverpackets.components.ChatType;
import l2p.gameserver.scripts.Functions;
import l2p.technical.util.Rnd;

/**
 * @author : Alice
 * @date : 26.11.16
 * @time : 7:02
 * <p/>
 * desc
 */
public class JackpotPrize extends CharacterAI {
    public int ItemName_52_A = 14678;
    public int ItemName_52_B = 8755;
    public int ItemName_70_A = 14679;
    public int ItemName_70_B_1 = 5577;
    public int ItemName_70_B_2 = 5578;
    public int ItemName_70_B_3 = 5579;
    public int ItemName_80_A = 14680;
    public int ItemName_80_B_1 = 9552;
    public int ItemName_80_B_2 = 9553;
    public int ItemName_80_B_3 = 9554;
    public int ItemName_80_B_4 = 9555;
    public int ItemName_80_B_5 = 9556;
    public int ItemName_80_B_6 = 9557;

    public JackpotPrize(NpcInstance actor) {
        super(actor);
    }

    @Override
    protected void onEvtSpawn() {
        super.onEvtSpawn();

        if (Rnd.chance(50))
            Functions.npcSay(actor, ChatType.NPC_ALL, 1900148);
        else
            Functions.npcSay(actor, ChatType.NPC_ALL, 1900149);

        addTimer(1001, 600000);
    }

    @Override
    protected void onEvtTimer(int timerId, Object arg1, Object arg2) {
        if (timerId == 10001)
            actor.deleteMe();
    }

    @Override
    protected void onEvtAttacked(Creature attacker, int damage, Skill skill) {
        actor.doDie(attacker);
    }

    @Override
    protected void onEvtDead(Creature killer) {
        super.onEvtDead(killer);
        Player player;

        if (killer == null || (player = killer.getPlayer()) == null)
            return;

        switch ((int) actor.param1) {
            case 52:
                if (actor.param2 >= 5)
                    actor.dropItem(player, ItemName_52_A, 1);
                else if (actor.param2 >= 2 && actor.param2 < 5)
                    actor.dropItem(player, ItemName_52_B, 2);
                else
                    actor.dropItem(player, ItemName_52_B, 1);
                break;
            case 70:
                if (actor.param2 >= 5)
                    actor.dropItem(player, ItemName_70_A, 1);
                else if (actor.param2 >= 2 && actor.param2 < 5) {
                    int i0 = Rnd.get(3);
                    if (i0 == 2)
                        actor.dropItem(player, ItemName_70_B_1, 2);
                    else if (i0 == 1)
                        actor.dropItem(player, ItemName_70_B_2, 2);
                    else
                        actor.dropItem(player, ItemName_70_B_3, 2);
                } else {
                    int i0 = Rnd.get(3);
                    if (i0 == 2)
                        actor.dropItem(player, ItemName_70_B_1, 1);
                    else if (i0 == 1)
                        actor.dropItem(player, ItemName_70_B_2, 1);
                    else
                        actor.dropItem(player, ItemName_70_B_3, 1);
                }
                break;
            case 80:
                if (actor.param2 >= 5)
                    actor.dropItem(player, ItemName_80_A, 1);
                else if (actor.param2 >= 2 && actor.param2 < 5) {
                    int i0 = Rnd.get(6);
                    if (i0 == 5)
                        actor.dropItem(player, ItemName_80_B_1, 2);
                    else if (i0 == 4)
                        actor.dropItem(player, ItemName_80_B_2, 2);
                    else if (i0 == 3)
                        actor.dropItem(player, ItemName_80_B_3, 2);
                    else if (i0 == 2)
                        actor.dropItem(player, ItemName_80_B_4, 2);
                    else if (i0 == 1)
                        actor.dropItem(player, ItemName_80_B_5, 2);
                    else
                        actor.dropItem(player, ItemName_80_B_6, 2);
                } else {
                    int i0 = Rnd.get(6);
                    if (i0 == 5)
                        actor.dropItem(player, ItemName_80_B_1, 1);
                    else if (i0 == 4)
                        actor.dropItem(player, ItemName_80_B_2, 1);
                    else if (i0 == 3)
                        actor.dropItem(player, ItemName_80_B_3, 1);
                    else if (i0 == 2)
                        actor.dropItem(player, ItemName_80_B_4, 1);
                    else if (i0 == 1)
                        actor.dropItem(player, ItemName_80_B_5, 1);
                    else
                        actor.dropItem(player, ItemName_80_B_6, 1);
                }
                break;
        }
    }
}


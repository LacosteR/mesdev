package ai;

import l2p.gameserver.ai.CtrlEvent;
import l2p.gameserver.ai.DefaultAI;
import l2p.gameserver.model.Creature;
import l2p.gameserver.model.Skill;
import l2p.gameserver.model.instances.NpcInstance;
import l2p.gameserver.network.serverpackets.components.NpcString;
import l2p.gameserver.scripts.Functions;
import l2p.gameserver.utils.GArray;
import l2p.technical.util.Rnd;

public class FieldMachine extends DefaultAI {
    private long _lastAction;

    public FieldMachine(NpcInstance actor) {
        super(actor);
    }

    @Override
    protected void onEvtAttacked(Creature attacker, int damage, Skill skill) {
        NpcInstance actor = getActor();
        if (attacker == null || attacker.getPlayer() == null)
            return;

        // Ругаемся не чаще, чем раз в 15 секунд
        if (System.currentTimeMillis() - _lastAction > 15000) {
            _lastAction = System.currentTimeMillis();
            Functions.npcSay(actor, Rnd.chance(50) ? NpcString.ALERT_ALERT_DAMAGE_DETECTION_RECOGNIZED : NpcString.THE_PURIFICATION_FIELD_IS_BEING_ATTACKED);
            GArray<NpcInstance> around = actor.getAroundNpc(1500, 300);
            if (around != null && !around.isEmpty())
                for (NpcInstance npc : around)
                    if (npc.isMonster() && npc.getNpcId() >= 22656 && npc.getNpcId() <= 22659)
                        npc.getAI().notifyEvent(CtrlEvent.EVT_AGGRESSION, attacker, 5000);
        }
    }
}
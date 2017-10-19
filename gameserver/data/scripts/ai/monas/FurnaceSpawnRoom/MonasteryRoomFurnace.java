package ai.monas.FurnaceSpawnRoom;

import l2p.gameserver.ThreadPoolManager;
import l2p.gameserver.ai.DefaultAI;
import l2p.gameserver.data.xml.holder.EventHolder;
import l2p.gameserver.model.Creature;
import l2p.gameserver.model.Skill;
import l2p.gameserver.model.entity.events.EventType;
import l2p.gameserver.model.entity.events.impl.MonasteryFurnaceEvent;
import l2p.gameserver.model.instances.NpcInstance;
import l2p.gameserver.network.serverpackets.components.NpcString;
import l2p.gameserver.scripts.Functions;
import l2p.technical.threading.RunnableImpl;

public class MonasteryRoomFurnace extends DefaultAI {
    private boolean _firstTimeAttacked = true;

    public MonasteryRoomFurnace(NpcInstance actor) {
        super(actor);
    }

    @Override
    protected void onEvtSpawn() {
        NpcInstance actor = getActor();

        switch (actor.getAISpawnParam()) {
            case 1:
                actor.setNameNpcString(NpcString.FURN1);
                break;
            case 2:
                actor.setNameNpcString(NpcString.FURN2);
                break;
            case 3:
                actor.setNameNpcString(NpcString.FURN3);
                break;
            case 4:
                actor.setNameNpcString(NpcString.FURN4);
                break;
            default:
                actor.setNameNpcString(NpcString.FURN1);
                break;
        }

        super.onEvtSpawn();
    }

    @Override
    protected void onEvtAttacked(Creature attacker, int damage, Skill skill) {
        NpcInstance actor = getActor();
        if (actor == null)
            return;

        int event_id = actor.getAISpawnParam();
        MonasteryFurnaceEvent furnace = EventHolder.getInstance().getEvent(EventType.MAIN_EVENT, event_id);

        if (_firstTimeAttacked && !furnace.isInProgress()) {
            _firstTimeAttacked = false;
            attacker.setTarget(null);
            actor.setTargetable(false);
            actor.setNpcState((byte) 1);
            Functions.npcShout(actor, NpcString.FURN1);
            furnace.registerActions();
            ThreadPoolManager.getInstance().schedule(new ScheduleTimerTask(), 600000);
        }

        super.onEvtAttacked(attacker, damage, skill);
    }

    private class ScheduleTimerTask extends RunnableImpl {
        @Override
        public void runImpl() {
            NpcInstance actor = getActor();
            int event_id = actor.getAISpawnParam();
            MonasteryFurnaceEvent furnace = EventHolder.getInstance().getEvent(EventType.MAIN_EVENT, event_id);
            furnace.spawnAction(MonasteryFurnaceEvent.STANDART_ROOM, true);
        }
    }

    @Override
    protected void onEvtDead(Creature killer) {
        _firstTimeAttacked = true;
        super.onEvtDead(killer);
    }
}

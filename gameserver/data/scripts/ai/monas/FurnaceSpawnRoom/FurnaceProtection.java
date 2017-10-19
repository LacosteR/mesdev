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

/**
 * @author PaInKiLlEr
 *         - AI для Furnace (18914).
 *         - При спавне имеет собственный ник.
 *         - При атаке загорается, сбрасывает у игрока таргет, включает неактивность таргета.
 *         - Кричит в чат при ударе.
 *         - Через 15 секунд исчезают все жаровни.
 *         - Запускается евент и комната респавнится с монстрами лукарями.
 *         - AI проверен и работает.
 */
public class FurnaceProtection extends DefaultAI {
    private boolean _firstTimeAttacked = true;

    public FurnaceProtection(NpcInstance actor) {
        super(actor);
        actor.setNameNpcString(NpcString.FURN3);
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
            ThreadPoolManager.getInstance().schedule(new ScheduleTimerTask(), 15000);
        }

        super.onEvtAttacked(attacker, damage, skill);
    }

    private class ScheduleTimerTask extends RunnableImpl {
        @Override
        public void runImpl() {
            NpcInstance actor = getActor();
            int event_id = actor.getAISpawnParam();
            MonasteryFurnaceEvent furnace = EventHolder.getInstance().getEvent(EventType.MAIN_EVENT, event_id);
            furnace.spawnAction(MonasteryFurnaceEvent.PROTECTOR_ROOM, true);
        }
    }

    @Override
    protected void onEvtDead(Creature killer) {
        _firstTimeAttacked = true;
        super.onEvtDead(killer);
    }
}
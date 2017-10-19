package ai.base.summons;

import l2p.gameserver.ai.SummonAI;
import l2p.gameserver.model.Creature;
import l2p.gameserver.model.Summon;

/**
 * @author : Alice
 * @date : 29.03.17
 * @time : 0:23
 * <p/>
 */
public class SummonSiegeCannon extends SummonAI {
    public SummonSiegeCannon(Summon actor) {
        super(actor);
        /*
        actor::Summon_SetOption(1, 900);

         */
        addTimer(100020, 1000);
        addTimer(100021, 1000 * 60);
        setArtillery(true);
    }

    /*
     кстати еще у голема Осадное Орудие скиллы своеобразные - там особый тип цели - artillery
5110/5111
как я понял и сделал им можно стрелять только по целям в зоне осады + зона резиденции
т.е. по игрокам на стенах или внутри замка/форта
притом цель необязательно должна быть видна
     */
    @Override
    public void onEvtMenuSelected(int action_id) {
        if (action_id == 1039) {
            castSkill(5110, 1, (Creature) getMaster().getTarget());
        } else if (action_id == 1040) {
            castSkill(5111, 1, (Creature) getMaster().getTarget());
        }
    }

    @Override
    protected void onEvtTimer(int timerId, Object arg1, Object arg2) {
        if (timerId == 100020) {
            if (getMaster().isDead()) {
            }
            addTimer(100020, 1000);
        }
        if (timerId == 100021) {
            if (getMaster() != null && getMaster().getInventory().getCountOf(2132) < 140 && getMaster().getInventory().getCountOf(2132) >= 70) {
                sayFStr(1000169);
            }
            addTimer(100021, 1000 * 60);
        }
    }
}

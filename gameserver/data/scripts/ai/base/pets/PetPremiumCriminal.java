package ai.base.pets;

import l2p.gameserver.ai.PetAI;
import l2p.gameserver.model.Creature;
import l2p.gameserver.model.Summon;
import l2p.technical.util.Rnd;

/**
 * @author : Alice
 * @date : 21.12.16
 * @time : 14:05
 * <p/>
 */
public class PetPremiumCriminal extends PetAI {
    public PetPremiumCriminal(Summon actor) {
        super(actor);

        addTimer(1671, 10000);
    }

    // TODO: Полученный пет (Sin Eater) после вызова поглощает весь получаемый персонажем опыт, питается Etc pouch yellow i00 0.jpg Food For Wolves. Импа дают аналогичного персонажу уровня с 0% експой. Вызвать Импа и получить ему уровень.
    @Override
    protected void onEvtTimer(int timerId, Object arg1, Object arg2) {
        if (timerId == 1671) {
            if (petAiComponent.getMoveAroundSocial() > 0 && actor.getCurrentHp() > (actor.getMaxHp() * 0.400000) && !actor.isDead()) {
                addEffectActionDesire(1, petAiComponent.getMoveAroundSocial() * 1000 / 30, 50);
            }
            addTimer(1671, 10000);
        }
        super.onEvtTimer(timerId, arg1, arg2);
    }

    @Override
    protected void onEvtDead(Creature killer) {
        int pk_c = Rnd.get(10) + 1;
        if (getActor().getPlayer().getPkKills() <= pk_c) {
            getActor().getPlayer().setPkKills(0);
        } else {
            getActor().getPlayer().setPkKills(getActor().getPlayer().getPkKills() - pk_c);
        }
        super.onEvtDead(killer);
    }
}

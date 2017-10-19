package ai.base.pets;

import l2p.gameserver.ai.PetAI;
import l2p.gameserver.model.Creature;
import l2p.gameserver.model.Skill;
import l2p.gameserver.model.Summon;
import l2p.gameserver.network.serverpackets.components.NpcString;
import l2p.technical.util.Rnd;

/**
 * @author : Alice
 * @date : 21.12.16
 * @time : 14:05
 * <p/>
 * AI для Sin Eater
 */
public class SummonCriminal extends PetAI {
    public SummonCriminal(Summon actor) {
        super(actor);
        int rnd = Rnd.get(100);
        if (rnd < 50) {
            sayFStr(NpcString.HEY_IT_SEEMS_LIKE_YOU_NEED_MY_HELP_DOESNT_IT);
        } else {
            sayFStr(NpcString.ALMOST_GOT_IT);
        }
        addTimer(2001, 60000);
    }

    @Override
    public void onEvtMenuSelected(int action_id) {
        if (Rnd.get(100) < 10)
        {
            int rnd = Rnd.get(100);
            if (rnd < 25) {
                sayFStr(NpcString.USING_A_SPECIAL_SKILL_HERE_COULD_TRIGGER_A_BLOODBATH);
            } else if (rnd < 50) {
                sayFStr(NpcString.HEY_WHAT_DO_YOU_EXPECT_OF_ME);
            } else if (rnd < 75) {
                sayFStr(NpcString.UGGGGGH_PUSH_ITS_NOT_COMING_OUT);
            } else {
                sayFStr(NpcString.AH_I_MISSED_THE_MARK);
            }
        }
    }
    //TODO Полученный пет (Sin Eater) после вызова поглощает весь получаемый персонажем опыт, питается Etc pouch yellow i00 0.jpg Food For Wolves. Импа дают аналогичного персонажу уровня с 0% експой. Вызвать Импа и получить ему уровень.
    @Override
    protected void onEvtDead(Creature killer) {
        int rnd = Rnd.get(100);
        if (rnd < 35) {
            sayFStr(NpcString.OH_THIS_IS_JUST_GREAT_WHAT_ARE_YOU_GOING_TO_DO_NOW);
        } else if (rnd < 70) {
            sayFStr(NpcString.YOU_INCONSIDERATE_MORON_CANT_YOU_EVEN_TAKE_CARE_OF_LITTLE_OLD_ME);
        } else {
            sayFStr(NpcString.OH_NO_THE_MAN_WHO_EATS_ONES_SINS_HAS_DIED_PENITENCE_IS_FURTHER_AWAY);
        }
        super.onEvtDead(killer);
    }

    @Override
    protected void onEvtAttacked(Creature attacker, int damage, Skill skill) {
        super.onEvtAttacked(attacker, damage, skill);
        if (Rnd.get(100) >= 30) {
            return;
        }
        int rnd = Rnd.get(100);
        if (rnd < 35) {
            sayFStr(NpcString.OH_THAT_SMARTS);
        } else if (rnd < 70) {
            sayFStr(NpcString.HEY_MASTER_PAY_ATTENTION_IM_DYING_OVER_HERE);
        } else {
            sayFStr(NpcString.WHAT_HAVE_I_DONE_TO_DESERVE_THIS);
        }
    }

    @Override
    protected void onEvtTimer(int timerId, Object arg1, Object arg2) {
        if (timerId == 2001) {
            if (Rnd.get(100) < 30) {
                int rnd = Rnd.get(100);
                if (rnd < 20) {
                    sayFStr(NpcString.YAWWWWN_ITS_SO_BORING_HERE);
                } else if (rnd < 40) {
                    sayFStr(NpcString.HEY_IF_YOU_CONTINUE_TO_WASTE_TIME_YOU_WILL_NEVER_FINISH_YOUR_PENANCE);
                } else if (rnd < 60) {
                    sayFStr(NpcString.I_KNOW_YOU_DONT_LIKE_ME);
                } else if (rnd < 80) {
                    sayFStr(NpcString.I_NEED_A_DRINK);
                } else {
                    sayFStr(NpcString.OH_THIS_IS_DRAGGING_ON_TOO_LONG);
                }
            }
            addTimer(2001, 60000);
        }
        super.onEvtTimer(timerId, arg1, arg2);
    }
}

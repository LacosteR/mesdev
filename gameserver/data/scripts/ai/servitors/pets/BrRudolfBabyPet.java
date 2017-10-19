package ai.servitors.pets;

import l2p.gameserver.ai.ServitorAI;
import l2p.gameserver.ai.base.RetailClass;
import l2p.gameserver.model.Creature;
import l2p.gameserver.model.Skill;
import l2p.gameserver.model.Summon;
import l2p.gameserver.model.instances.PetInstance;
import l2p.gameserver.model.items.ItemInstance;
import l2p.gameserver.network.serverpackets.components.NpcString;
import l2p.technical.util.Rnd;

/**
 * @author : Alice
 * @date : 21.12.16
 * @time : 14:05
 * <p/>
 */
@RetailClass(name = "br_rudolf_baby_pet")
public class BrRudolfBabyPet extends ServitorAI {
    protected static final int TIMER_GROWTH = 2500;
    protected static final int TIMER_EAT = 2501;
    protected static final int TIMER_EAT_CONFIRM = 2502;
    protected static final int TIMER_AFFECTION_WAIT = 2503;
    protected static final int TIMER_IDLE = 2504;
    protected static final int TIMER_EVOLVE = 2505;
    protected static final int TIMER_EVOLVE_BABY = 2506;
    protected static final int TIMER_EVOLVE_GIRL = 2507;
    protected static final int TIMER_EVOLVE_AFTER = 2508;
    protected static final int TIMER_GOODBYE = 2599;
    protected static final int TIMER_GROWTH_PERIOD = 36000;
    protected static final int TIMER_AFFECTION_PERIOD = 60000;

    protected static final int S_BR_XMAS2009_BUFF_A = 23157;
    protected static final int S_BR_XMAS2009_BUFF_B = 23158;
    protected static final int S_BR_XMAS2009_BUFF_C = 23159;
    protected static final int S_BR_XMAS2009_BUFF_D = 23160;
    protected static final int S_BR_XMAS2009_BUFF_GIRL = 23161;
    protected static final int S_BR_XMAS2009_BUFF_GIRL2 = 23162;

    public BrRudolfBabyPet(Summon actor) {
        super(actor);
        getActor().i_ai0 = (getActor().getSp() / 10000) % 100;
        getActor().i_ai1 = (getActor().getSp() / 100) % 100;
        getActor().i_ai2 = getActor().getSp() % 100;
        getActor().i_ai3 = (int) (getActor().getHpRegen()) % 100;
        getActor().i_ai4 = 0;
        getActor().i_ai5 = 0;
        getActor().i_ai6 = 0;
        int i0 = 1;
        /* TODO: узнать что за GetEventValue
        i0 = gg->GetEventValue(0);
		if (i0 <= 0)
		{
			i0 = 1;
		}
         */
        if (getActor().getLevel() == 2) {
            addTimer(TIMER_EAT, 3000);
            addTimer(TIMER_EAT_CONFIRM, 2100);
            addTimer(TIMER_AFFECTION_WAIT, TIMER_AFFECTION_PERIOD / i0);
        }
        if (getActor().getLevel() >= 2) {
            addTimer(TIMER_GROWTH, TIMER_GROWTH_PERIOD / i0);
        }
        addTimer(TIMER_IDLE, (50 * 1000) + Rnd.get(10000));
    }
    /*
    EventHandler NO_DESIRE()
	{
		if (actor->i_ai6 == 0)
		{
			actor->AddPetDefaultDesire_Follow(20.000000);
		}
	}
     */

    /*
    SetNpcParam
    9 - setCurrentHp
    35 - setHpRegen
    1 - ?
    13 - ?
     */
    @Override
    protected void onEvtTimer(int timerId, Object arg1, Object arg2) {
        switch (timerId) {
            case TIMER_GROWTH:
                if (getActor().getLevel() == 2) {
                    getActor().i_ai0 = getActor().i_ai0 + 1;
                    if (getActor().i_ai0 >= 99) {
                        getActor().i_ai0 = 99;
                    }
                    // TODO: узнать что за параметр №13
                    // gg->SetNpcParam(actor->sm, 13, ((getActor().i_ai0 * 10000) + (actor->i_ai1 * 100)) + actor->i_ai2);
                    if (getActor().i_ai0 == 83) {
                        sayFStr(NpcString.IN_10_MINUTES_IT_WILL_BE_1_HOUR_SINCE_YOU_STARTED_RAISING_ME);
                    } else if (getActor().i_ai0 == 92) {
                        sayFStr(NpcString.AFTER_5_MINUTES_IF_MY_FULL_FEELING_AND_AFFECTION_LEVEL_REACH_99_I_CAN_GROW_BIGGER);
                    }
                } else if (getActor().getLevel() >= 3) {
                    getActor().i_ai0 = getActor().i_ai0 + 6;
                    if (getActor().i_ai0 >= 99) {
                        getActor().i_ai0 = 99;
                    }
                    // gg->SetNpcParam(actor->sm, 13, ((getActor().i_ai0 * 10000) + (actor->i_ai1 * 100)) + actor->i_ai2);
                    if (getActor().getLevel() < 7) {
                        if (getActor().i_ai0 == 30) {
                            sayFStr(NpcString.THANK_YOU_NOW_I_CAN_PULL_THE_SLED, getActor().getPlayer().getName());
                        } else if (getActor().i_ai0 == 54) {
                            sayFStr(NpcString.THANK_YOU_FOR_TAKING_CARE_OF_ME_ALL_THIS_TIME, getActor().getPlayer().getName());
                        } else if (getActor().i_ai0 == 90) {
                            sayFStr(NpcString.IT_WONT_BE_LONG_NOW_UNTIL_IT_BECOMES_TIME_TO_PULL_THE_SLED, getActor().getPlayer().getName());
                        }
                    } else if (getActor().getLevel() == 7) {
                        if (getActor().i_ai0 == 30) {
                            sayFStr(NpcString.I_WAS_ALWAYS_GRATEFUL, getActor().getPlayer().getName());
                        } else if (getActor().i_ai0 == 54) {
                            sayFStr(NpcString.IM_A_LITTLE_SAD, getActor().getPlayer().getName());
                        } else if (getActor().i_ai0 == 90) {
                            sayFStr(NpcString.THE_TIME_HAS_COME_FOR_ME_TO_RETURN_MY_HOME, getActor().getPlayer().getName());
                        }
                    }
                }

                if (getActor().getLevel() == 2 && getActor().i_ai0 >= 99 && getActor().i_ai1 >= 99 && getActor().i_ai2 >= 99) {
                    addTimer(TIMER_EVOLVE, 5500);
                    // TODO реализовать desire_list actor->RemoveAllDesire();
                    getActor().i_ai6 = 1;
                    addEffectActionDesire(1, 177, 1000000);
                } else if (getActor().getLevel() >= 3 && getActor().i_ai0 >= 99) {
                    if (getActor().getLevel() < 7) {
                        sayFStr(NpcString.I_MUST_RETURN_TO_SANTA_NOW, getActor().getPlayer().getName());
                    } else if (getActor().getLevel() == 7) {
                        sayFStr(NpcString.THANK_YOU, getActor().getPlayer().getName());
                    }
                    // actor->RemoveAllDesire();
                    getActor().i_ai6 = 1;
                    addEffectActionDesire(1, 177, 1000000);
                    addTimer(TIMER_GOODBYE, 7000);
                } else if (getActor().getLevel() >= 2) {
                    int i0 = 1;
                    /*i0 = gg->GetEventValue(0);
                    if (i0 <= 0)
                    {
                        i0 = 1;
                    }   */
                    addTimer(TIMER_GROWTH, TIMER_GROWTH_PERIOD / i0);
                }
                break;
            case TIMER_EVOLVE:
                int i0 = Rnd.get(10000);
                if (i0 < 2400) {
                    i0 = 0;
                } else if (i0 < 4900) {
                    i0 = 1;
                } else if (i0 < 7400) {
                    i0 = 2;
                } else if (i0 < 9900) {
                    i0 = 3;
                } else if (i0 < 9993) {
                    i0 = 4;
                } else {
                    i0 = 5;
                }
                int i1 = 1;
                /*i1 = gg -> GetEventValue(1);
                if (i1 > 0) {
                    i0 = i1 - 1;
                }   */
                /*if (i0 < 5) {
                    gg -> SetNpcParam(actor -> sm, 1, 3 + i0);
                    actor -> i_ai0 = 0;
                    gg -> SetNpcParam(actor -> sm, 13, ((actor -> i_ai0 * 10000) + (actor -> i_ai1 * 100)) + actor -> i_ai2);
                }     */
                if (i0 == 0) {
                    castBuffForQuestReward(getActor().getPlayer(), S_BR_XMAS2009_BUFF_A, 1);
                    addTimer(TIMER_EVOLVE_AFTER, 800);
                    sayFStr(NpcString.THANK_YOU_I_WAS_ABLE_TO_GROW_UP_INTO_ADULT);
                } else if (i0 == 1) {
                    castBuffForQuestReward(getActor().getPlayer(), S_BR_XMAS2009_BUFF_B, 1);
                    addTimer(TIMER_EVOLVE_AFTER, 800);
                    sayFStr(NpcString.THANK_YOU_I_WAS_ABLE_TO_GROW_UP_INTO_ADULT);
                } else if (i0 == 2) {
                    castBuffForQuestReward(getActor().getPlayer(), S_BR_XMAS2009_BUFF_C, 1);
                    addTimer(TIMER_EVOLVE_AFTER, 800);
                    sayFStr(NpcString.THANK_YOU_I_WAS_ABLE_TO_GROW_UP_INTO_ADULT);
                } else if (i0 == 3) {
                    castBuffForQuestReward(getActor().getPlayer(), S_BR_XMAS2009_BUFF_D, 1);
                    addTimer(TIMER_EVOLVE_AFTER, 800);
                    sayFStr(NpcString.THANK_YOU_I_WAS_ABLE_TO_GROW_UP_INTO_ADULT);
                } else if (i0 == 4) {
                    sayFStr(NpcString.HELLO_IM_A_GIRL_RUDOLPH);
                    addTimer(TIMER_EVOLVE_AFTER, 500);
                    addTimer(TIMER_EVOLVE_GIRL, 7500);
                }
                if (i0 == 5) {
                    getActor().createOnePrivate(100, "br_rudolf_turkey", 0, 0, getActor().getPositionComponent().getX(), getActor().getPositionComponent().getY(), getActor().getPositionComponent().getZ(), 0, getActor().getPlayer().getStoredId(), 0, 0);
                    // actor -> RemoveAllDesire();
                    getActor().unSummon();  // отсамонить или уничтожить вместе с controlItem??   actor -> DestroyPet(actor -> master, actor -> sm -> pet_dbid, -99);
                } else if (i0 < 4) {
                    /*i1 = gg -> GetEventValue(0);
                    if (i1 <= 0) {
                        i1 = 1;
                    }   */
                    addTimer(TIMER_GROWTH, TIMER_GROWTH_PERIOD / i1);
                    getActor().i_ai6 = 0;
                }
                break;
            case TIMER_EVOLVE_GIRL:
                castBuffForQuestReward(getActor().getPlayer(), S_BR_XMAS2009_BUFF_GIRL, 1);
                castBuffForQuestReward(getActor().getPlayer(), S_BR_XMAS2009_BUFF_GIRL2, 1);
                addEffectActionDesire(5, 177, 1000000);
                sayFStr(NpcString.THIS_IS_MY_GIFT_OF_THANKS);
                i1 = 1;
                /*i1 = gg->GetEventValue(0);
                if (i1 <= 0)
                {
                    i1 = 1;
                }  */
                addTimer(TIMER_GROWTH, TIMER_GROWTH_PERIOD / i1);
                getActor().i_ai6 = 0;
                break;
            case TIMER_EAT:
                lookItem(150, 1, 20772);
                lookItem(150, 1, 20771);
                lookItem(150, 1, 20770);
                if (getActor().getLevel() <= 2 || (getActor().getLevel() > 2 && getActor().i_ai0 < 90)) {
                    addTimer(TIMER_EAT, 3000);
                }
                break;
            case TIMER_EAT_CONFIRM:
                i0 = getAbnormalLevel(getActor(), 1450180609);  // s_br_xmas_apple_basket1
                if (i0 > 0) {
                    getActor().i_ai5 = 0;
                    dispel(1450180609);
                    i1 = 1;
                    /*i1 = gg->GetEventValue(0);
                    if (i1 <= 0)
                    {
                        i1 = 1;
                    }   */
                    getActor().i_ai2 = getActor().i_ai2 + (10 * i1);
                    if (getActor().i_ai2 > 99) {
                        getActor().i_ai2 = 99;
                    }
                    //gg->SetNpcParam(actor->sm, 13, ((actor->i_ai0 * 10000) + (actor->i_ai1 * 100)) + actor->i_ai2);
                    castBuffForQuestReward(getActor().getPlayer(), (1450180609 + i0) - 1);
                    if (getActor().getLevel() == 2) {
                        if (Rnd.get(100) < 30) {
                            sayFStr(1900115);
                        } else if (Rnd.get(100) < 60) {
                            sayFStr(1900116);
                        }
                    }
                } else {
                    getActor().i_ai5 = getActor().i_ai5 + 1;
                    if (getActor().i_ai5 >= 200 && getActor().getLevel() == 2) {
                        castBuffForQuestReward(getActor().getPlayer(), 1518141441);
                        sayFStr(1900119);
                        getActor().i_ai5 = 0;
                    }
                }
                if (getActor().getLevel() <= 2 || (getActor().getLevel() > 2 && getActor().i_ai0 < 90)) {
                    addTimer(TIMER_EAT_CONFIRM, 2100);
                }
                break;
            case TIMER_AFFECTION_WAIT:
                getActor().i_ai3 = getActor().i_ai3 + 1;
                if (getActor().i_ai3 >= 99) {
                    getActor().i_ai3 = 99;
                }
                getActor().setHpRegen(getActor().getHpRegen() + getActor().i_ai3);  // кажется так gg->SetNpcParam(actor->sm, 35, getActor().i_ai3);
                if (getActor().i_ai1 < 99 && getActor().i_ai3 >= 15) {
                    if (getActor().i_ai4 == 0) {
                        sayFStr(1900112);
                        addEffectActionDesire(4, 177, 1000000);
                    } else if (getActor().i_ai4 > 10) {
                        sayFStr(1900119);
                        getActor().i_ai4 = -1;
                        castBuffForQuestReward(getActor().getPlayer(), 1518141441);
                    } else {
                        addEffectActionDesire(4, 177, 10000);
                    }
                    getActor().i_ai4 = getActor().i_ai4 + 1;
                }
                i0 = 1;
                /*i0 = gg->GetEventValue(0);
                if (i0 <= 0)
                {
                    i0 = 1;
                }      */
                if (getActor().getLevel() == 2 && getActor().i_ai1 < 99) {
                    addTimer(TIMER_AFFECTION_WAIT, (TIMER_AFFECTION_PERIOD - Rnd.get(10000)) / i0);
                }
                break;
            case TIMER_IDLE:
                if (getActor().i_ai6 == 0) {
                    addEffectActionDesire(3, 147, 1000);
                }
                if (getActor().getLevel() <= 2 || (getActor().getLevel() > 2 && getActor().i_ai0 < 90)) {
                    addTimer(TIMER_IDLE, (50 * 1000) + Rnd.get(10000));
                }
                if (getActor().getLevel() == 1) {
                    if (Rnd.get(100) < 50) {
                        sayFStr(1900105);
                    } else {
                        sayFStr(1900106);
                    }
                } else if (getActor().getLevel() == 2) {
                    if (Rnd.get(100) < 12) {
                        sayFStr(1900109);
                    } else if (Rnd.get(100) < 18) {
                        sayFStr(1900110);
                    } else if (Rnd.get(100) < 22) {
                        sayFStr(1900111);
                    }
                }
                break;
            case TIMER_EVOLVE_BABY:
                if (Rnd.get(100) < 50) {
                    sayFStr(1900107);
                } else {
                    sayFStr(1900108);
                }
                // за что отвечает param = 1? gg->SetNpcParam(actor->sm, 1, 2);
                addTimer(TIMER_EVOLVE_AFTER, 800);
                i0 = 1;
                /*i0 = gg->GetEventValue(0);
                if (i0 <= 0)
                {
                    i0 = 1;
                }      */
                getActor().i_ai3 = 0;
                getActor().setHpRegen(getActor().getHpRegen() + getActor().i_ai3);
                addTimer(TIMER_AFFECTION_WAIT, (TIMER_AFFECTION_PERIOD - Rnd.get(10000)) / i0);
                getActor().i_ai0 = 0;
                getActor().i_ai1 = 0;
                getActor().i_ai2 = 0;
                // gg->SetNpcParam(actor->sm, 13, ((getActor().i_ai0 * 10000) + (getActor().i_ai1 * 100)) + getActor().i_ai2);
                addTimer(TIMER_EAT, 3000);
                addTimer(TIMER_EAT_CONFIRM, 2100);
                addTimer(TIMER_GROWTH, TIMER_GROWTH_PERIOD / i0);
                getActor().i_ai6 = 0;
                break;
            case TIMER_EVOLVE_AFTER:
                addEffectActionDesire(2, 177, 1000000);
                break;
            case TIMER_GOODBYE:
                //actor->RemoveAllDesire();
                getActor().unSummon(); // actor->DestroyPet(actor->master, actor->sm->pet_dbid, -99);
                break;
        }
    }

    @Override
    public void onEvtMenuSelected(int action_id) {
        if (action_id == 5000 && getActor().i_ai3 >= 15) {
            if (Rnd.get(100) < 50) {
                sayFStr(1900113);
            } else {
                sayFStr(1900114);
            }
            getActor().i_ai1 = getActor().i_ai1 + 25;
            if (getActor().i_ai1 >= 99) {
                getActor().i_ai1 = 99;
            }
            getActor().i_ai4 = 0;
            // gg->SetNpcParam(actor->sm, 13, ((getActor().i_ai0 * 10000) + (getActor().i_ai1 * 100)) + getActor().i_ai2);
            addEffectActionDesire(2, 171, 1000000);
            getActor().i_ai3 = Rnd.get(2);
            getActor().setHpRegen(getActor().getHpRegen() + getActor().i_ai3);
            dispel(1518141441);
            castBuffForQuestReward(getActor().getPlayer(), 1517486081);
        }
    }

    @Override
    public PetInstance getActor() {
        return (PetInstance)actor;
    }

    @Override
    public void onEvtSeeItem(ItemInstance item) {
        if (getActor().getLevel() < 2) {
            return;
        }
        lookItem(20);
    }

    @Override
    public void onEvtSpelled(Skill skill, Creature speller) {
        if (skill.getId() == 22126 && speller == getActor().getPlayer())
        {
            if (getActor().getLevel() == 1)
            {
                //actor->RemoveAllDesire();
                getActor().i_ai6 = 1;
                addEffectActionDesire(1, 177, 1000000);
                addTimer(TIMER_EVOLVE_BABY, 5500);
            }
        }
    }
}
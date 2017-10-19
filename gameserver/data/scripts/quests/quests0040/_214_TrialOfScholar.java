package quests.quests0040;

import l2p.gameserver.model.instances.NpcInstance;
import l2p.gameserver.model.quest.Quest;
import l2p.gameserver.model.quest.QuestState;
import l2p.gameserver.scripts.ScriptFile;

public class _214_TrialOfScholar extends Quest implements ScriptFile {
    // NPCs
    private static final int Sylvain = 30070;
    private static final int Lucas = 30071;
    private static final int Valkon = 30103;
    private static final int Dieter = 30111;
    private static final int Jurek = 30115;
    private static final int Edroc = 30230;
    private static final int Raut = 30316;
    private static final int Poitan = 30458;
    private static final int Mirien = 30461;
    private static final int Maria = 30608;
    private static final int Creta = 30609;
    private static final int Cronos = 30610;
    private static final int Triff = 30611;
    private static final int Casian = 30612;
    // Mobs
    private static final int Monster_Eye_Destroyer = 20068;
    private static final int Medusa = 20158;
    private static final int Ghoul = 20201;
    private static final int Shackle = 20235;
    private static final int Breka_Orc_Shaman = 20269;
    private static final int Fettered_Soul = 20552;
    private static final int Grandis = 20554;
    private static final int Enchanted_Gargoyle = 20567;
    private static final int Leto_Lizardman_Warrior = 20580;
    // Items
    private static final int Dimensional_Diamond = 7562;
    private static final int Mark_of_Scholar = 2674;
    private static final int Miriens_1st_Sigil = 2675;
    private static final int Miriens_2nd_Sigil = 2676;
    private static final int Miriens_3rd_Sigil = 2677;
    private static final int Miriens_Instruction = 2678;
    private static final int Marias_1st_Letter = 2679;
    private static final int Marias_2nd_Letter = 2680;
    private static final int Lucass_Letter = 2681;
    private static final int Lucillas_Handbag = 2682;
    private static final int Cretas_1st_Letter = 2683;
    private static final int Cretas_Painting1 = 2684;
    private static final int Cretas_Painting2 = 2685;
    private static final int Cretas_Painting3 = 2686;
    private static final int Brown_Scroll_Scrap = 2687;
    private static final int Crystal_of_Purity1 = 2688;
    private static final int High_Priests_Sigil = 2689;
    private static final int Grand_Magisters_Sigil = 2690;
    private static final int Cronos_Sigil = 2691;
    private static final int Sylvains_Letter = 2692;
    private static final int Symbol_of_Sylvain = 2693;
    private static final int Jureks_List = 2694;
    private static final int Monster_Eye_Destroyer_Skin = 2695;
    private static final int Shamans_Necklace = 2696;
    private static final int Shackles_Scalp = 2697;
    private static final int Symbol_of_Jurek = 2698;
    private static final int Cronos_Letter = 2699;
    private static final int Dieters_Key = 2700;
    private static final int Cretas_2nd_Letter = 2701;
    private static final int Dieters_Letter = 2702;
    private static final int Dieters_Diary = 2703;
    private static final int Rauts_Letter_Envelope = 2704;
    private static final int Triffs_Ring = 2705;
    private static final int Scripture_Chapter_1 = 2706;
    private static final int Scripture_Chapter_2 = 2707;
    private static final int Scripture_Chapter_3 = 2708;
    private static final int Scripture_Chapter_4 = 2709;
    private static final int Valkons_Request = 2710;
    private static final int Poitans_Notes = 2711;
    private static final int Strong_Liquor = 2713;
    private static final int Crystal_of_Purity2 = 2714;
    private static final int Casians_List = 2715;
    private static final int Ghouls_Skin = 2716;
    private static final int Medusas_Blood = 2717;
    private static final int Fettered_Souls_Ichor = 2718;
    private static final int Enchanted_Gargoyles_Nail = 2719;
    private static final int Symbol_of_Cronos = 2720;

    public _214_TrialOfScholar() {
        super(false);

        addStartNpc(Mirien);
        addTalkId(Sylvain);
        addTalkId(Lucas);
        addTalkId(Valkon);
        addTalkId(Dieter);
        addTalkId(Jurek);
        addTalkId(Edroc);
        addTalkId(Raut);
        addTalkId(Poitan);
        addTalkId(Maria);
        addTalkId(Creta);
        addTalkId(Cronos);
        addTalkId(Triff);
        addTalkId(Casian);

        addKillId(Monster_Eye_Destroyer);
        addKillId(Medusa);
        addKillId(Ghoul);
        addKillId(Shackle);
        addKillId(Breka_Orc_Shaman);
        addKillId(Fettered_Soul);
        addKillId(Grandis);
        addKillId(Enchanted_Gargoyle);
        addKillId(Leto_Lizardman_Warrior);

        addQuestItem(Scripture_Chapter_3);
        addQuestItem(Brown_Scroll_Scrap);
        addQuestItem(Monster_Eye_Destroyer_Skin);
        addQuestItem(Shamans_Necklace);
        addQuestItem(Shackles_Scalp);
        addQuestItem(Ghouls_Skin);
        addQuestItem(Medusas_Blood);
        addQuestItem(Fettered_Souls_Ichor);
        addQuestItem(Enchanted_Gargoyles_Nail);
    }

    @Override
    public String onEvent(String event, QuestState st, NpcInstance npc) {
        int _state = st.getState();
        if (event.equalsIgnoreCase("magister_mirien_q0214_04.htm") && _state == CREATED) {
            st.doGiveItems(Miriens_1st_Sigil, 1);
            if (!st.getPlayer().getVarB("dd1")) {
                st.doGiveItems(Dimensional_Diamond, RATE_QUESTS_REWARD * 168);
                st.getPlayer().setVar("dd1", "1", -1);
            }
            st.setState(STARTED);
            st.setCond(1);
            st.playSound(SOUND_ACCEPT);
        } else if (event.equalsIgnoreCase("sylvain_q0214_02.htm") && _state == STARTED) {
            st.playSound(SOUND_MIDDLE);
            st.doGiveItems(High_Priests_Sigil, 1);
            st.doGiveItems(Sylvains_Letter, 1);
            st.setCond(2);
        } else if (event.equalsIgnoreCase("marya_q0214_02.htm") && _state == STARTED) {
            if (st.getQuestItemsCount(Sylvains_Letter) == 0)
                return null;
            st.doTakeItems(Sylvains_Letter, -1);
            st.playSound(SOUND_MIDDLE);
            st.doGiveItems(Marias_1st_Letter, 1);
            st.setCond(3);
        } else if (event.equalsIgnoreCase("astrologer_creta_q0214_05.htm") && _state == STARTED) {
            if (st.getQuestItemsCount(Marias_2nd_Letter) == 0)
                return null;
            st.doTakeItems(Marias_2nd_Letter, -1);
            st.playSound(SOUND_MIDDLE);
            st.doGiveItems(Cretas_1st_Letter, 1);
            st.setCond(6);
        } else if (event.equalsIgnoreCase("marya_q0214_08.htm") && _state == STARTED) {
            if (st.getQuestItemsCount(Cretas_1st_Letter) == 0)
                return null;
            st.doTakeItems(Cretas_1st_Letter, -1);
            st.playSound(SOUND_MIDDLE);
            st.doGiveItems(Lucillas_Handbag, 1);
            st.setCond(7);
        } else if (event.equalsIgnoreCase("astrologer_creta_q0214_09.htm") && _state == STARTED) {
            if (st.getQuestItemsCount(Lucillas_Handbag) == 0)
                return null;
            st.doTakeItems(Lucillas_Handbag, -1);
            st.playSound(SOUND_MIDDLE);
            st.doGiveItems(Cretas_Painting1, 1);
            st.setCond(8);
        } else if (event.equalsIgnoreCase("lucas_q0214_04.htm") && _state == STARTED) {
            if (st.getQuestItemsCount(Cretas_Painting2) == 0)
                return null;
            st.doTakeItems(Cretas_Painting2, -1);
            st.playSound(SOUND_MIDDLE);
            st.doGiveItems(Cretas_Painting3, 1);
            st.setCond(10);
        } else if (event.equalsIgnoreCase("marya_q0214_14.htm") && _state == STARTED) {
            if (st.getQuestItemsCount(Cretas_Painting3) == 0 || st.getQuestItemsCount(Brown_Scroll_Scrap) < 5)
                return null;
            st.doTakeItems(Cretas_Painting3, -1);
            st.doTakeItems(Brown_Scroll_Scrap, -1);
            st.playSound(SOUND_MIDDLE);
            st.doGiveItems(Crystal_of_Purity1, 1);
            st.setCond(13);
        } else if (event.equalsIgnoreCase("valkon_q0214_04.htm") && _state == STARTED && st.getQuestItemsCount(Valkons_Request) == 0) {
            st.playSound(SOUND_MIDDLE);
            st.doGiveItems(Valkons_Request, 1);
        } else if (event.equalsIgnoreCase("jurek_q0214_03.htm") && _state == STARTED && st.getQuestItemsCount(Grand_Magisters_Sigil) == 0 && st.getQuestItemsCount(Symbol_of_Jurek) == 0) {
            st.playSound(SOUND_MIDDLE);
            st.doGiveItems(Grand_Magisters_Sigil, 1);
            st.doGiveItems(Jureks_List, 1);
            st.setCond(16);
        } else if (event.equalsIgnoreCase("magister_mirien_q0214_10.htm") && _state == STARTED) {
            if (st.getQuestItemsCount(Miriens_2nd_Sigil) == 0 || st.getQuestItemsCount(Symbol_of_Jurek) == 0)
                return null;
            st.doTakeItems(Miriens_2nd_Sigil, -1);
            st.doTakeItems(Symbol_of_Jurek, -1);
            if (st.getPlayer().getLevel() < 36) {
                st.doGiveItems(Miriens_Instruction, 1);
                return "magister_mirien_q0214_09.htm";
            }
            st.playSound(SOUND_MIDDLE);
            st.doGiveItems(Miriens_3rd_Sigil, 1);
            st.setCond(19);
        } else if (event.equalsIgnoreCase("sage_cronos_q0214_10.htm") && _state == STARTED) {
            st.playSound(SOUND_MIDDLE);
            st.doGiveItems(Cronos_Sigil, 1);
            st.doGiveItems(Cronos_Letter, 1);
            st.setCond(20);
        } else if (event.equalsIgnoreCase("dieter_q0214_05.htm") && _state == STARTED) {
            if (st.getQuestItemsCount(Cronos_Letter) == 0)
                return null;
            st.doTakeItems(Cronos_Letter, -1);
            st.playSound(SOUND_MIDDLE);
            st.doGiveItems(Dieters_Key, 1);
            st.setCond(21);
        } else if (event.equalsIgnoreCase("astrologer_creta_q0214_14.htm") && _state == STARTED) {
            if (st.getQuestItemsCount(Dieters_Key) == 0)
                return null;
            st.doTakeItems(Dieters_Key, -1);
            st.playSound(SOUND_MIDDLE);
            st.doGiveItems(Cretas_2nd_Letter, 1);
            st.setCond(22);
        } else if (event.equalsIgnoreCase("dieter_q0214_09.htm") && _state == STARTED) {
            if (st.getQuestItemsCount(Cretas_2nd_Letter) == 0)
                return null;
            st.doTakeItems(Cretas_2nd_Letter, -1);
            st.playSound(SOUND_MIDDLE);
            st.doGiveItems(Dieters_Letter, 1);
            st.doGiveItems(Dieters_Diary, 1);
            st.setCond(23);
        } else if (event.equalsIgnoreCase("trader_edroc_q0214_02.htm") && _state == STARTED) {
            if (st.getQuestItemsCount(Dieters_Letter) == 0)
                return null;
            st.doTakeItems(Dieters_Letter, -1);
            st.playSound(SOUND_MIDDLE);
            st.doGiveItems(Rauts_Letter_Envelope, 1);
            st.setCond(24);
        } else if (event.equalsIgnoreCase("warehouse_keeper_raut_q0214_02.htm") && _state == STARTED) {
            if (st.getQuestItemsCount(Rauts_Letter_Envelope) == 0)
                return null;
            st.doTakeItems(Rauts_Letter_Envelope, -1);
            st.playSound(SOUND_MIDDLE);
            st.doGiveItems(Scripture_Chapter_1, 1);
            st.doGiveItems(Strong_Liquor, 1);
            st.setCond(25);
        } else if (event.equalsIgnoreCase("drunkard_treaf_q0214_04.htm") && _state == STARTED) {
            if (st.getQuestItemsCount(Strong_Liquor) == 0)
                return null;
            st.doTakeItems(Strong_Liquor, -1);
            st.playSound(SOUND_MIDDLE);
            st.doGiveItems(Triffs_Ring, 1);
            st.setCond(26);
        } else if (event.equalsIgnoreCase("sage_kasian_q0214_04.htm") && _state == STARTED) {
            if (st.getQuestItemsCount(Casians_List) == 0) {
                st.playSound(SOUND_MIDDLE);
                st.doGiveItems(Casians_List, 1);
            }
            st.setCond(28);
        } else if (event.equalsIgnoreCase("sage_kasian_q0214_07.htm") && _state == STARTED) {
            st.doTakeItems(Casians_List, -1);
            st.doTakeItems(Ghouls_Skin, -1);
            st.doTakeItems(Medusas_Blood, -1);
            st.doTakeItems(Fettered_Souls_Ichor, -1);
            st.doTakeItems(Enchanted_Gargoyles_Nail, -1);
            st.doTakeItems(Poitans_Notes, -1);
            st.playSound(SOUND_MIDDLE);
            st.doGiveItems(Scripture_Chapter_4, 1);
            st.setCond(30);
        } else if (event.equalsIgnoreCase("sage_cronos_q0214_14.htm") && _state == STARTED) {
            if (st.getQuestItemsCount(Scripture_Chapter_1) == 0)
                return null;
            if (st.getQuestItemsCount(Scripture_Chapter_2) == 0)
                return null;
            if (st.getQuestItemsCount(Scripture_Chapter_3) == 0)
                return null;
            if (st.getQuestItemsCount(Scripture_Chapter_4) == 0)
                return null;
            if (st.getQuestItemsCount(Cronos_Sigil) == 0)
                return null;
            st.doTakeItems(Scripture_Chapter_1, -1);
            st.doTakeItems(Scripture_Chapter_2, -1);
            st.doTakeItems(Scripture_Chapter_3, -1);
            st.doTakeItems(Scripture_Chapter_4, -1);
            st.doTakeItems(Cronos_Sigil, -1);
            st.doTakeItems(Triffs_Ring, -1);
            st.doTakeItems(Dieters_Diary, 1);
            st.playSound(SOUND_MIDDLE);
            st.doGiveItems(Symbol_of_Cronos, 1);
            st.setCond(31);
        }

        return event;
    }

    @Override
    public String onTalk(NpcInstance npc, QuestState st) {
        if (st.getQuestItemsCount(Mark_of_Scholar) > 0) {
            st.exitCurrentQuest(true);
            return "completed";
        }
        int _state = st.getState();
        int npcId = npc.getNpcId();

        if (_state == CREATED) {
            if (npcId != Mirien)
                return "noquest";
            int class_id = st.getPlayer().getPcClass().class_id;
            if (class_id != 0x0b && class_id != 0x1a && class_id != 0x27) {
                st.exitCurrentQuest(true);
                return "magister_mirien_q0214_01.htm";
            }
            if (st.getPlayer().getLevel() < 35) {
                st.exitCurrentQuest(true);
                return "magister_mirien_q0214_02.htm";
            }
            st.setCond(0);
            return "magister_mirien_q0214_03.htm";
        }

        if (_state != STARTED)
            return "noquest";

        if (npcId == Mirien) {
            if (st.getQuestItemsCount(Miriens_1st_Sigil) > 0) {
                if (st.getQuestItemsCount(Symbol_of_Sylvain) == 0)
                    return "magister_mirien_q0214_05.htm";
                st.doTakeItems(Miriens_1st_Sigil, -1);
                st.doTakeItems(Symbol_of_Sylvain, -1);
                st.playSound(SOUND_MIDDLE);
                st.doGiveItems(Miriens_2nd_Sigil, 1);
                st.setCond(15);
                return "magister_mirien_q0214_06.htm";
            }
            if (st.getQuestItemsCount(Miriens_2nd_Sigil) > 0)
                return st.getQuestItemsCount(Symbol_of_Jurek) > 0 ? "magister_mirien_q0214_08.htm" : "magister_mirien_q0214_07.htm";
            if (st.getQuestItemsCount(Miriens_Instruction) > 0) {
                if (st.getPlayer().getLevel() < 36)
                    return "magister_mirien_q0214_11.htm";
                st.doTakeItems(Miriens_Instruction, -1);
                st.playSound(SOUND_MIDDLE);
                st.doGiveItems(Miriens_3rd_Sigil, 1);
                st.setCond(19);
                return "magister_mirien_q0214_12.htm";
            }
            if (st.getQuestItemsCount(Miriens_3rd_Sigil) > 0) {
                if (st.getQuestItemsCount(Symbol_of_Cronos) == 0)
                    return "magister_mirien_q0214_13.htm";
                st.doTakeItems(Symbol_of_Cronos, -1);
                st.doTakeItems(Miriens_3rd_Sigil, -1);
                if (!st.getPlayer().getVarB("prof2.1")) {
                    st.addExpAndSp(RATE_QUESTS_REWARD * 1753926, RATE_QUESTS_REWARD * 113754);
                    st.doRewardItems(ADENA_ID, RATE_QUESTS_REWARD * 319628);
                    st.getPlayer().setVar("prof2.1", "1", -1);
                }
                st.doGiveItems(Mark_of_Scholar, 1);
                st.playSound(SOUND_FINISH);
                st.exitCurrentQuest(true);
                return "magister_mirien_q0214_14.htm";
            }
        }

        if (npcId == Sylvain) {
            if (st.getQuestItemsCount(Miriens_1st_Sigil) > 0) {
                if (st.getQuestItemsCount(High_Priests_Sigil) < 1)
                    return st.getQuestItemsCount(Symbol_of_Sylvain) > 0 ? "sylvain_q0214_05.htm" : "sylvain_q0214_01.htm";
                if (st.getQuestItemsCount(Crystal_of_Purity1) < 1)
                    return "sylvain_q0214_03.htm";

                st.doTakeItems(High_Priests_Sigil, -1);
                st.doTakeItems(Crystal_of_Purity1, -1);
                st.playSound(SOUND_MIDDLE);
                st.doGiveItems(Symbol_of_Sylvain, 1);
                st.setCond(14);
                return "sylvain_q0214_04.htm";
            }
            if (st.getQuestItemsCount(Miriens_2nd_Sigil) > 0 || st.getQuestItemsCount(Miriens_3rd_Sigil) > 0)
                return "sylvain_q0214_06.htm";
        }

        if (npcId == Lucas)
            if (st.getQuestItemsCount(Miriens_1st_Sigil) > 0 && st.getQuestItemsCount(High_Priests_Sigil) > 0) {
                if (st.getQuestItemsCount(Marias_1st_Letter) > 0) {
                    st.doTakeItems(Marias_1st_Letter, -1);
                    st.playSound(SOUND_MIDDLE);
                    st.doGiveItems(Lucass_Letter, 1);
                    st.setCond(4);
                    return "lucas_q0214_01.htm";
                }
                if (st.getQuestItemsCount(Marias_2nd_Letter) > 0)
                    return "lucas_q0214_02.htm";
                if (st.getQuestItemsCount(Cretas_1st_Letter) > 0)
                    return "lucas_q0214_02.htm";
                if (st.getQuestItemsCount(Lucillas_Handbag) > 0)
                    return "lucas_q0214_02.htm";
                if (st.getQuestItemsCount(Cretas_Painting1) > 0)
                    return "lucas_q0214_02.htm";
                if (st.getQuestItemsCount(Lucass_Letter) > 0)
                    return "lucas_q0214_02.htm";
                if (st.getQuestItemsCount(Cretas_Painting2) > 0)
                    return "lucas_q0214_03.htm";
                if (st.getQuestItemsCount(Cretas_Painting3) > 0)
                    return st.getQuestItemsCount(Brown_Scroll_Scrap) < 5 ? "lucas_q0214_05.htm" : "lucas_q0214_06.htm";
            } else {
                if (st.getQuestItemsCount(Symbol_of_Sylvain) > 0)
                    return "lucas_q0214_07.htm";
                if (st.getQuestItemsCount(Miriens_2nd_Sigil) > 0)
                    return "lucas_q0214_07.htm";
                if (st.getQuestItemsCount(Miriens_3rd_Sigil) > 0)
                    return "lucas_q0214_07.htm";
                if (st.getQuestItemsCount(Crystal_of_Purity1) > 0)
                    return "lucas_q0214_07.htm";
            }

        if (npcId == Valkon && st.getQuestItemsCount(Triffs_Ring) > 0) {
            long Valkons_Request_count = st.getQuestItemsCount(Valkons_Request);
            long Scripture_Chapter_2_count = st.getQuestItemsCount(Scripture_Chapter_2);
            if (st.getQuestItemsCount(Crystal_of_Purity2) == 0) {
                if (Scripture_Chapter_2_count == 0)
                    return Valkons_Request_count > 0 ? "valkon_q0214_05.htm" : "valkon_q0214_01.htm";
                if (Valkons_Request_count == 0)
                    return "valkon_q0214_07.htm";
            } else if (Valkons_Request_count == 0 && Scripture_Chapter_2_count == 0) {
                st.doTakeItems(Crystal_of_Purity2, -1);
                st.playSound(SOUND_MIDDLE);
                st.doGiveItems(Scripture_Chapter_2, 1);
                return "valkon_q0214_06.htm";
            }
        }

        if (npcId == Dieter) {
            if (st.getQuestItemsCount(Miriens_3rd_Sigil) > 0 && st.getQuestItemsCount(Cronos_Sigil) > 0) {
                if (st.getQuestItemsCount(Cronos_Letter) > 0)
                    return "dieter_q0214_01.htm";
                if (st.getQuestItemsCount(Dieters_Key) > 0)
                    return "dieter_q0214_06.htm";
                if (st.getQuestItemsCount(Cretas_2nd_Letter) > 0)
                    return "dieter_q0214_07.htm";
                if (st.getQuestItemsCount(Dieters_Diary) > 0) {
                    if (st.getQuestItemsCount(Dieters_Letter) > 0)
                        return "dieter_q0214_10.htm";
                    if (st.getQuestItemsCount(Rauts_Letter_Envelope) > 0)
                        return "dieter_q0214_11.htm";
                    if (st.getQuestItemsCount(Scripture_Chapter_1) == 0)
                        return "dieter_q0214_12.htm";
                    if (st.getQuestItemsCount(Scripture_Chapter_2) == 0)
                        return "dieter_q0214_12.htm";
                    if (st.getQuestItemsCount(Scripture_Chapter_3) == 0)
                        return "dieter_q0214_12.htm";
                    if (st.getQuestItemsCount(Scripture_Chapter_4) == 0)
                        return "dieter_q0214_12.htm";
                    return "dieter_q0214_13.htm";
                }
            }
            if (st.getQuestItemsCount(Symbol_of_Cronos) > 0)
                return "dieter_q0214_15.htm";
        }

        if (npcId == Jurek) {
            if (st.getQuestItemsCount(Miriens_2nd_Sigil) > 0) {
                long Grand_Magisters_Sigil_count = st.getQuestItemsCount(Grand_Magisters_Sigil);
                long Symbol_of_Jurek_count = st.getQuestItemsCount(Symbol_of_Jurek);
                if (Grand_Magisters_Sigil_count == 0 && Symbol_of_Jurek_count == 0)
                    return "jurek_q0214_01.htm";
                if (st.getQuestItemsCount(Jureks_List) > 0) {
                    if (!Check_cond17_items(st))
                        return "jurek_q0214_04.htm";

                    if (Grand_Magisters_Sigil_count > 0) {
                        st.doTakeItems(Jureks_List, -1);
                        st.doTakeItems(Monster_Eye_Destroyer_Skin, -1);
                        st.doTakeItems(Shamans_Necklace, -1);
                        st.doTakeItems(Shackles_Scalp, -1);
                        st.doTakeItems(Grand_Magisters_Sigil, -1);
                        st.playSound(SOUND_MIDDLE);
                        st.doGiveItems(Symbol_of_Jurek, 1);
                        st.setCond(18);
                        return "jurek_q0214_05.htm";
                    }
                }
                if (Symbol_of_Jurek_count > 0 && Grand_Magisters_Sigil_count == 0)
                    return "jurek_q0214_06.htm";
            }
            if (st.getQuestItemsCount(Miriens_1st_Sigil) > 0 || st.getQuestItemsCount(Miriens_3rd_Sigil) > 0)
                return "jurek_q0214_07.htm";
        }

        if (npcId == Edroc && st.getQuestItemsCount(Dieters_Diary) > 0) {
            if (st.getQuestItemsCount(Dieters_Letter) > 0)
                return "trader_edroc_q0214_01.htm";
            if (st.getQuestItemsCount(Rauts_Letter_Envelope) > 0)
                return "trader_edroc_q0214_03.htm";
            if (st.getQuestItemsCount(Strong_Liquor) > 0 || st.getQuestItemsCount(Triffs_Ring) > 0)
                return "trader_edroc_q0214_04.htm";
        }

        if (npcId == Raut && st.getQuestItemsCount(Dieters_Diary) > 0) {
            if (st.getQuestItemsCount(Rauts_Letter_Envelope) > 0)
                return "warehouse_keeper_raut_q0214_01.htm";
            if (st.getQuestItemsCount(Scripture_Chapter_1) > 0) {
                if (st.getQuestItemsCount(Strong_Liquor) > 0)
                    return "warehouse_keeper_raut_q0214_04.htm";
                if (st.getQuestItemsCount(Triffs_Ring) > 0)
                    return "warehouse_keeper_raut_q0214_05.htm";
            }
        }

        if (npcId == Poitan && st.getQuestItemsCount(Triffs_Ring) > 0) {
            long Poitans_Notes_count = st.getQuestItemsCount(Poitans_Notes);
            long Casians_List_count = st.getQuestItemsCount(Casians_List);
            if (st.getQuestItemsCount(Scripture_Chapter_4) == 0) {
                if (Poitans_Notes_count > 0)
                    return Casians_List_count > 0 ? "blacksmith_poitan_q0214_03.htm" : "valkon_q0214_02.htm";
                if (Casians_List_count == 0) {
                    st.playSound(SOUND_MIDDLE);
                    st.doGiveItems(Poitans_Notes, 1);
                    return "blacksmith_poitan_q0214_01.htm";
                }
            } else if (Poitans_Notes_count == 0 && Casians_List_count == 0)
                return "blacksmith_poitan_q0214_04.htm";
        }

        if (npcId == Maria) {
            if (st.getQuestItemsCount(Miriens_1st_Sigil) > 0 && st.getQuestItemsCount(High_Priests_Sigil) > 0) {
                if (st.getQuestItemsCount(Sylvains_Letter) > 0)
                    return "marya_q0214_01.htm";
                if (st.getQuestItemsCount(Marias_1st_Letter) > 0)
                    return "marya_q0214_03.htm";
                if (st.getQuestItemsCount(Lucass_Letter) > 0) {
                    st.doTakeItems(Lucass_Letter, -1);
                    st.playSound(SOUND_MIDDLE);
                    st.doGiveItems(Marias_2nd_Letter, 1);
                    st.setCond(5);
                    return "marya_q0214_04.htm";
                }
                if (st.getQuestItemsCount(Marias_2nd_Letter) > 0)
                    return "marya_q0214_05.htm";
                if (st.getQuestItemsCount(Cretas_1st_Letter) > 0)
                    return "marya_q0214_06.htm";
                if (st.getQuestItemsCount(Lucillas_Handbag) > 0)
                    return "marya_q0214_09.htm";
                if (st.getQuestItemsCount(Cretas_Painting1) > 0) {
                    st.doTakeItems(Cretas_Painting1, 1);
                    st.playSound(SOUND_MIDDLE);
                    st.doGiveItems(Cretas_Painting2, 1);
                    st.setCond(9);
                    return "marya_q0214_10.htm";
                }
                if (st.getQuestItemsCount(Cretas_Painting2) > 0)
                    return "marya_q0214_11.htm";
                if (st.getQuestItemsCount(Cretas_Painting3) > 0) {
                    if (st.getQuestItemsCount(Brown_Scroll_Scrap) < 5) {
                        st.setCond(11);
                        return "marya_q0214_12.htm";
                    }
                    return "marya_q0214_13.htm";
                }
                if (st.getQuestItemsCount(Crystal_of_Purity1) > 0)
                    return "marya_q0214_15.htm";
            }
            if (st.getQuestItemsCount(Symbol_of_Sylvain) > 0 || st.getQuestItemsCount(Miriens_2nd_Sigil) > 0)
                return "marya_q0214_16.htm";
            if (st.getQuestItemsCount(Miriens_3rd_Sigil) > 0) {
                if (st.getQuestItemsCount(Valkons_Request) == 0)
                    return "marya_q0214_17.htm";
                st.doTakeItems(Valkons_Request, 1);
                st.playSound(SOUND_MIDDLE);
                st.doGiveItems(Crystal_of_Purity2, 1);
                return "marya_q0214_18.htm";
            }
        }

        if (npcId == Creta) {
            if (st.getQuestItemsCount(Miriens_1st_Sigil) > 0 && st.getQuestItemsCount(High_Priests_Sigil) > 0) {
                if (st.getQuestItemsCount(Marias_2nd_Letter) > 0)
                    return "astrologer_creta_q0214_01.htm";
                if (st.getQuestItemsCount(Cretas_1st_Letter) > 0)
                    return "astrologer_creta_q0214_06.htm";
                if (st.getQuestItemsCount(Lucillas_Handbag) > 0)
                    return "astrologer_creta_q0214_07.htm";
                if (st.getQuestItemsCount(Cretas_Painting1) > 0)
                    return "astrologer_creta_q0214_10.htm";
                if (st.getQuestItemsCount(Cretas_Painting2) > 0)
                    return "astrologer_creta_q0214_10.htm";
                if (st.getQuestItemsCount(Cretas_Painting3) > 0)
                    return "astrologer_creta_q0214_10.htm";
            }
            if (st.getQuestItemsCount(Crystal_of_Purity1) > 0)
                return "astrologer_creta_q0214_11.htm";
            if (st.getQuestItemsCount(Symbol_of_Sylvain) > 0)
                return "astrologer_creta_q0214_11.htm";
            if (st.getQuestItemsCount(Miriens_2nd_Sigil) > 0)
                return "astrologer_creta_q0214_11.htm";
            if (st.getQuestItemsCount(Miriens_3rd_Sigil) > 0)
                return st.getQuestItemsCount(Dieters_Key) > 0 ? "astrologer_creta_q0214_12.htm" : "astrologer_creta_q0214_15.htm";
        }

        if (npcId == Cronos && st.getQuestItemsCount(Miriens_3rd_Sigil) > 0) {
            if (st.getQuestItemsCount(Cronos_Sigil) > 0) {
                if (st.getQuestItemsCount(Scripture_Chapter_1) == 0)
                    return "sage_cronos_q0214_11.htm";
                if (st.getQuestItemsCount(Scripture_Chapter_2) == 0)
                    return "sage_cronos_q0214_11.htm";
                if (st.getQuestItemsCount(Scripture_Chapter_3) == 0)
                    return "sage_cronos_q0214_11.htm";
                if (st.getQuestItemsCount(Scripture_Chapter_4) == 0)
                    return "sage_cronos_q0214_11.htm";
                return "sage_cronos_q0214_12.htm";
            }
            return st.getQuestItemsCount(Symbol_of_Cronos) > 0 ? "sage_cronos_q0214_15.htm" : "sage_cronos_q0214_01.htm";
        }

        if (npcId == Triff) {
            if (st.getQuestItemsCount(Dieters_Diary) > 0 && st.getQuestItemsCount(Scripture_Chapter_1) > 0 && st.getQuestItemsCount(Strong_Liquor) > 0)
                return "drunkard_treaf_q0214_01.htm";
            if (st.getQuestItemsCount(Triffs_Ring) > 0)
                return "drunkard_treaf_q0214_05.htm";
            if (st.getQuestItemsCount(Symbol_of_Cronos) > 0)
                return "drunkard_treaf_q0214_05.htm";
        }

        if (npcId == Casian && st.getQuestItemsCount(Triffs_Ring) > 0) {
            long Casians_List_count = st.getQuestItemsCount(Casians_List);
            if (st.getQuestItemsCount(Poitans_Notes) > 0) {
                if (Casians_List_count > 0)
                    return Check_cond29_items(st) ? "sage_kasian_q0214_06.htm" : "sage_kasian_q0214_05.htm";
                if (st.getQuestItemsCount(Scripture_Chapter_1) > 0 && st.getQuestItemsCount(Scripture_Chapter_2) > 0 && st.getQuestItemsCount(Scripture_Chapter_3) > 0)
                    return "sage_kasian_q0214_02.htm";
                st.setCond(27);
                return "sage_kasian_q0214_01.htm";
            }
            if (Casians_List_count == 0 && st.getQuestItemsCount(Scripture_Chapter_1) > 0 && st.getQuestItemsCount(Scripture_Chapter_2) > 0 && st.getQuestItemsCount(Scripture_Chapter_3) > 0 && st.getQuestItemsCount(Scripture_Chapter_4) > 0)
                return "sage_kasian_q0214_08.htm";
        }

        return "noquest";
    }

    private static boolean Check_cond17_items(QuestState st) {
        if (st.getQuestItemsCount(Monster_Eye_Destroyer_Skin) < 5)
            return false;
        if (st.getQuestItemsCount(Shamans_Necklace) < 5)
            return false;
        if (st.getQuestItemsCount(Shackles_Scalp) < 2)
            return false;
        return true;
    }

    private static boolean Check_cond29_items(QuestState st) {
        if (st.getQuestItemsCount(Ghouls_Skin) < 10)
            return false;
        if (st.getQuestItemsCount(Medusas_Blood) < 12)
            return false;
        if (st.getQuestItemsCount(Fettered_Souls_Ichor) < 5)
            return false;
        if (st.getQuestItemsCount(Enchanted_Gargoyles_Nail) < 5)
            return false;
        return true;
    }

    @Override
    public String onKill(NpcInstance npc, QuestState st) {
        if (st.getState() != STARTED)
            return null;
        int npcId = npc.getNpcId();

        if (npcId == Grandis && st.getQuestItemsCount(Miriens_3rd_Sigil) > 0 && st.getQuestItemsCount(Cronos_Sigil) > 0 && st.getQuestItemsCount(Triffs_Ring) > 0)
            st.doGiveOnKillItems(Scripture_Chapter_3, 1, 1, 1, 30);

        if (npcId == Leto_Lizardman_Warrior && st.getQuestItemsCount(Miriens_1st_Sigil) > 0 && st.getQuestItemsCount(High_Priests_Sigil) > 0 && st.getQuestItemsCount(Cretas_Painting3) > 0)
            if (st.doGiveOnKillItems(Brown_Scroll_Scrap, 1, 1, 5, 50))
                st.setCond(12);

        if (npcId == Monster_Eye_Destroyer && st.getQuestItemsCount(Miriens_2nd_Sigil) > 0 && st.getQuestItemsCount(Grand_Magisters_Sigil) > 0 && st.getQuestItemsCount(Jureks_List) > 0)
            if (st.doGiveOnKillItems(Monster_Eye_Destroyer_Skin, 1, 1, 5, 50) && Check_cond17_items(st))
                st.setCond(17);

        if (npcId == Breka_Orc_Shaman && st.getQuestItemsCount(Miriens_2nd_Sigil) > 0 && st.getQuestItemsCount(Grand_Magisters_Sigil) > 0 && st.getQuestItemsCount(Jureks_List) > 0)
            if (st.doGiveOnKillItems(Shamans_Necklace, 1, 1, 5, 50) && Check_cond17_items(st))
                st.setCond(17);

        if (npcId == Shackle && st.getQuestItemsCount(Miriens_2nd_Sigil) > 0 && st.getQuestItemsCount(Grand_Magisters_Sigil) > 0 && st.getQuestItemsCount(Jureks_List) > 0)
            if (st.doGiveOnKillItems(Shackles_Scalp, 1, 1, 2, 50) && Check_cond17_items(st))
                st.setCond(17);

        if (npcId == Ghoul && st.getQuestItemsCount(Triffs_Ring) > 0 && st.getQuestItemsCount(Poitans_Notes) > 0 && st.getQuestItemsCount(Casians_List) > 0)
            if (st.doGiveOnKillItems(Ghouls_Skin, 1, 1, 10, 100) && Check_cond29_items(st))
                st.setCond(29);

        if (npcId == Medusa && st.getQuestItemsCount(Triffs_Ring) > 0 && st.getQuestItemsCount(Poitans_Notes) > 0 && st.getQuestItemsCount(Casians_List) > 0)
            if (st.doGiveOnKillItems(Medusas_Blood, 1, 1, 12, 100) && Check_cond29_items(st))
                st.setCond(29);

        if (npcId == Fettered_Soul && st.getQuestItemsCount(Triffs_Ring) > 0 && st.getQuestItemsCount(Poitans_Notes) > 0 && st.getQuestItemsCount(Casians_List) > 0)
            if (st.doGiveOnKillItems(Fettered_Souls_Ichor, 1, 1, 5, 100) && Check_cond29_items(st))
                st.setCond(29);

        if (npcId == Enchanted_Gargoyle && st.getQuestItemsCount(Triffs_Ring) > 0 && st.getQuestItemsCount(Poitans_Notes) > 0 && st.getQuestItemsCount(Casians_List) > 0)
            if (st.doGiveOnKillItems(Enchanted_Gargoyles_Nail, 1, 1, 5, 100) && Check_cond29_items(st))
                st.setCond(29);

        return null;
    }

    public void onLoad() {
    }

    public void onReload() {
    }

    public void onShutdown() {
    }
}
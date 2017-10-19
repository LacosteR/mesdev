package ai.dragonvalley.pts;

import l2p.gameserver.ai.Fighter;
import l2p.gameserver.model.Creature;
import l2p.gameserver.model.Player;
import l2p.gameserver.model.Skill;
import l2p.gameserver.model.entity.category.CategoryManager;
import l2p.gameserver.model.instances.NpcInstance;
import l2p.gameserver.model.items.ItemInstance;
import l2p.gameserver.model.party.Party;
import l2p.gameserver.network.serverpackets.SystemMessage2;
import l2p.gameserver.network.serverpackets.components.SystemMsg;
import l2p.gameserver.tables.SkillTable;
import l2p.gameserver.utils.ItemFunctions;
import l2p.technical.util.Rnd;

/**
 * @author : Alice
 * @date : 23.11.16
 * @time : 6:22
 * <p/>
 * desc
 */
public class DetectPartyWarrior extends Fighter {
    public int duelist = 30;
    public int dreadnought = 42;
    public int phoenix_knight = 24;
    public int hell_knight = 27;
    public int sagittarius = 23;
    public int adventurer = 20;
    public int archmage = 26;
    public int soultaker = 25;
    public int arcana_lord = 47;
    public int cardinal = 20;
    public int hierophant = 18;
    public int evas_templar = 39;
    public int sword_muse = 13;
    public int wind_rider = 27;
    public int moonlight_sentinel = 22;
    public int mystic_muse = 21;
    public int elemental_master = 45;
    public int evas_saint = 14;
    public int shillien_templar = 35;
    public int spectral_dancer = 10;
    public int ghost_hunter = 33;
    public int ghost_sentinel = 20;
    public int storm_screamer = 25;
    public int spectral_master = 49;
    public int shillien_saint = 21;
    public int titan = 26;
    public int grand_khavatari = 24;
    public int dominator = 29;
    public int doomcryer = 23;
    public int fortune_seeker = 42;
    public int maestro = 44;
    public int doombringer = 28;
    public int m_soul_hound = 36;
    public int f_soul_hound = 36;
    public int trickster = 30;
    public int judicator = 48;

    public int threshold = 33;
    public int max_threshold = 183;
    public int loner = 25;
    public int category_weight = 15;

    public static Skill morale_up_display = SkillTable.getInstance().getInfo(6884, 1);
    public static Skill morale_up_lv1 = SkillTable.getInstance().getInfo(6885, 1);
    public static Skill morale_up_lv2 = SkillTable.getInstance().getInfo(6885, 2);
    public static Skill morale_up_lv3 = SkillTable.getInstance().getInfo(6885, 3);

    public DetectPartyWarrior(NpcInstance actor) {
        super(actor);
        setDragonValleyMob(true);
    }

    @Override
    public void onEvtAttacked(Creature attacker, int damage, Skill skill) {
        if (attacker == null) {
            return;
        }
        if (attacker.getPlayer() != null && actor.i_ai1 == 0) {
            actor.i_ai1 = 1;
            Player player = attacker.getPlayer();
            Party party0 = player.getParty();
            if (party0 != null) {
                int i2 = 0;
                int i3 = 0;
                int i4 = 0;
                int i5 = 0;
                int i6 = 0;
                int i7 = 0;
                int i8 = 0;
                int i9 = 0;

                for (Player c0 : party0.getPartyMembers()) {
                    if (c0 != null) {
                        switch (c0.getActiveClass().getClassId()) {
                            case 88:
                                if (duelist > loner)
                                    actor.i_ai0 += duelist - loner;
                                break;
                            case 89:
                                if (dreadnought > loner)
                                    actor.i_ai0 += dreadnought - loner;
                                break;
                            case 90:
                                if (phoenix_knight > loner)
                                    actor.i_ai0 += phoenix_knight - loner;
                                break;
                            case 91:
                                if (hell_knight > loner)
                                    actor.i_ai0 += hell_knight - loner;
                                break;
                            case 92:
                                if (sagittarius > loner)
                                    actor.i_ai0 += sagittarius - loner;
                                break;
                            case 93:
                                if (adventurer > loner)
                                    actor.i_ai0 += adventurer - loner;
                                break;
                            case 94:
                                if (archmage > loner)
                                    actor.i_ai0 += archmage - loner;
                                break;
                            case 95:
                                if (soultaker > loner)
                                    actor.i_ai0 += soultaker - loner;
                                break;
                            case 96:
                                if (arcana_lord > loner)
                                    actor.i_ai0 += arcana_lord - loner;
                                break;
                            case 97:
                                if (cardinal > loner)
                                    actor.i_ai0 += cardinal - loner;
                                break;
                            case 98:
                                if (hierophant > loner)
                                    actor.i_ai0 += hierophant - loner;
                                break;
                            case 99:
                                if (evas_templar > loner)
                                    actor.i_ai0 += evas_templar - loner;
                                break;
                            case 100:
                                if (sword_muse > loner)
                                    actor.i_ai0 += sword_muse - loner;
                                break;
                            case 101:
                                if (wind_rider > loner)
                                    actor.i_ai0 += wind_rider - loner;
                                break;
                            case 102:
                                if (moonlight_sentinel > loner)
                                    actor.i_ai0 += moonlight_sentinel - loner;
                                break;
                            case 103:
                                if (mystic_muse > loner)
                                    actor.i_ai0 += mystic_muse - loner;
                                break;
                            case 104:
                                if (elemental_master > loner)
                                    actor.i_ai0 += elemental_master - loner;
                                break;
                            case 105:
                                if (evas_saint > loner)
                                    actor.i_ai0 += evas_saint - loner;
                                break;
                            case 106:
                                if (shillien_templar > loner)
                                    actor.i_ai0 += shillien_templar - loner;
                                break;
                            case 107:
                                if (spectral_dancer > loner)
                                    actor.i_ai0 += spectral_dancer - loner;
                                break;
                            case 108:
                                if (ghost_hunter > loner)
                                    actor.i_ai0 += ghost_hunter - loner;
                                break;
                            case 109:
                                if (ghost_sentinel > loner)
                                    actor.i_ai0 += ghost_sentinel - loner;
                                break;
                            case 110:
                                if (storm_screamer > loner)
                                    actor.i_ai0 += storm_screamer - loner;
                                break;
                            case 111:
                                if (spectral_master > loner)
                                    actor.i_ai0 += spectral_master - loner;
                                break;
                            case 112:
                                if (shillien_saint > loner)
                                    actor.i_ai0 += shillien_saint - loner;
                                break;
                            case 113:
                                if (titan > loner)
                                    actor.i_ai0 += titan - loner;
                                break;
                            case 114:
                                if (grand_khavatari > loner)
                                    actor.i_ai0 += grand_khavatari - loner;
                                break;
                            case 115:
                                if (dominator > loner)
                                    actor.i_ai0 += dominator - loner;
                                break;
                            case 116:
                                if (doomcryer > loner)
                                    actor.i_ai0 += doomcryer - loner;
                                break;
                            case 117:
                                if (fortune_seeker > loner)
                                    actor.i_ai0 += fortune_seeker - loner;
                                break;
                            case 118:
                                if (maestro > loner)
                                    actor.i_ai0 += maestro - loner;
                                break;
                            case 131:
                                if (doombringer > loner)
                                    actor.i_ai0 += doombringer - loner;
                                break;
                            case 132:
                                if (m_soul_hound > loner)
                                    actor.i_ai0 += m_soul_hound - loner;
                                break;
                            case 133:
                                if (f_soul_hound > loner)
                                    actor.i_ai0 += f_soul_hound - loner;
                                break;
                            case 134:
                                if (trickster > loner)
                                    actor.i_ai0 += trickster - loner;
                                break;
                            case 135:
                            case 136:
                                if (judicator > loner)
                                    actor.i_ai0 += judicator - loner;
                                break;
                        }

                        if (c0.getActiveClass().getClassId() == 90 || c0.getActiveClass().getClassId() == 91 || c0.getActiveClass().getClassId() == 99 || c0.getActiveClass().getClassId() == 106) {
                            actor.i_ai0 += category_weight;
                            actor.i_ai8 += category_weight;
                        }
                        if (c0.getActiveClass().getClassId() == 95 || c0.getActiveClass().getClassId() == 96 || c0.getActiveClass().getClassId() == 104 || c0.getActiveClass().getClassId() == 111) {
                            actor.i_ai0 += category_weight;
                            actor.i_ai8 += category_weight;
                        }

                        if (c0.getActiveClass().getClassId() == 94 || c0.getActiveClass().getClassId() == 103 || c0.getActiveClass().getClassId() == 110 ||
                                c0.getActiveClass().getClassId() == 92 || c0.getActiveClass().getClassId() == 102 || c0.getActiveClass().getClassId() == 109 || c0.getActiveClass().getClassId() == 134 ||
                                c0.getActiveClass().getClassId() == 93 || c0.getActiveClass().getClassId() == 101 || c0.getActiveClass().getClassId() == 108) {
                            actor.i_ai0 += 3;
                            actor.i_ai8 += 3;
                        }
                        if (c0.getActiveClass().getClassId() == 97 || c0.getActiveClass().getClassId() == 105 || c0.getActiveClass().getClassId() == 112 || c0.getActiveClass().getClassId() == 115) {
                            actor.i_ai0 += 1;
                            actor.i_ai8 += 1;
                        }

                        if (c0.getActiveClass().getClassId() == 95 || c0.getActiveClass().getClassId() == 96 || c0.getActiveClass().getClassId() == 104 || c0.getActiveClass().getClassId() == 111)
                            i2++;
                        if (c0.getActiveClass().getClassId() == 94 || c0.getActiveClass().getClassId() == 103 || c0.getActiveClass().getClassId() == 110)
                            i3++;
                        if (c0.getActiveClass().getClassId() == 92 || c0.getActiveClass().getClassId() == 102 || c0.getActiveClass().getClassId() == 109 || c0.getActiveClass().getClassId() == 134)
                            i4++;
                        if (c0.getActiveClass().getClassId() == 93 || c0.getActiveClass().getClassId() == 101 || c0.getActiveClass().getClassId() == 108)
                            i5++;
                        if (c0.getActiveClass().getClassId() == 97 || c0.getActiveClass().getClassId() == 105 || c0.getActiveClass().getClassId() == 112 || c0.getActiveClass().getClassId() == 115)
                            i6++;
                        if (c0.getActiveClass().getClassId() == 90 || c0.getActiveClass().getClassId() == 91 || c0.getActiveClass().getClassId() == 99 || c0.getActiveClass().getClassId() == 106)
                            i7++;
                        if (c0.getActiveClass().getClassId() == 98 || c0.getActiveClass().getClassId() == 100 || c0.getActiveClass().getClassId() == 107 || c0.getActiveClass().getClassId() == 116)
                            i8++;
                        if (c0.getActiveClass().getClassId() == 88 || c0.getActiveClass().getClassId() == 89 || c0.getActiveClass().getClassId() == 113 || c0.getActiveClass().getClassId() == 114 || c0.getActiveClass().getClassId() == 118 || c0.getActiveClass().getClassId() == 131 || c0.getActiveClass().getClassId() == 132 || c0.getActiveClass().getClassId() == 133 || c0.getActiveClass().getClassId() == 117)
                            i9++;
                    }
                }

                if (i2 > 0 && i3 > 0 && i4 > 0 && i5 > 0 && i6 > 0 && i7 > 0 && i8 > 0 && i9 > 0) {
                    onIntentionCast(morale_up_lv3, attacker);
                    for (Player c0 : party0.getPartyMembers())
                        if (c0 != null)
                            onIntentionCast(morale_up_display, c0);

                    actor.i_ai0 = 0;
                    actor.i_ai8 = 0;
                    addTimer(2321001, 55000);
                    super.onEvtAttacked(attacker, damage, skill);
                    return;
                }
            }

            if (actor.i_ai0 > threshold) {
                if (actor.i_ai0 > max_threshold * 0.450000)
                    onIntentionCast(morale_up_lv3, attacker);
                else if (actor.i_ai0 > max_threshold * 0.300000)
                    onIntentionCast(morale_up_lv2, attacker);
                else
                    onIntentionCast(morale_up_lv1, attacker);

                if (party0 != null) {
                    for (Player c0 : party0.getPartyMembers()) {
                        if (c0 != null) {
                            int i4 = 0;
                            switch (c0.getActiveClass().getClassId()) {
                                case 88:
                                    if (0 < duelist - loner)
                                        i4 = 1;
                                    break;
                                case 89:
                                    if (0 < dreadnought - loner)
                                        i4 = 1;
                                    break;
                                case 90:
                                    if (0 < phoenix_knight - loner)
                                        i4 = 1;
                                    break;
                                case 91:
                                    if (0 < hell_knight - loner)
                                        i4 = 1;
                                    break;
                                case 92:
                                    if (0 < sagittarius - loner)
                                        i4 = 1;
                                    break;
                                case 93:
                                    if (0 < adventurer - loner)
                                        i4 = 1;
                                    break;
                                case 94:
                                    if (0 < archmage - loner)
                                        i4 = 1;
                                    break;
                                case 95:
                                    if (0 < soultaker - loner)
                                        i4 = 1;
                                    break;
                                case 96:
                                    if (0 < arcana_lord - loner)
                                        i4 = 1;
                                    break;
                                case 97:
                                    if (0 < cardinal - loner)
                                        i4 = 1;
                                    break;
                                case 98:
                                    if (0 < hierophant - loner)
                                        i4 = 1;
                                    break;
                                case 99:
                                    if (0 < evas_templar - loner)
                                        i4 = 1;
                                    break;
                                case 100:
                                    if (0 < sword_muse - loner)
                                        i4 = 1;
                                    break;
                                case 101:
                                    if (0 < wind_rider - loner)
                                        i4 = 1;
                                    break;
                                case 102:
                                    if (0 < moonlight_sentinel - loner)
                                        i4 = 1;
                                    break;
                                case 103:
                                    if (0 < mystic_muse - loner)
                                        i4 = 1;
                                    break;
                                case 104:
                                    if (0 < elemental_master - loner)
                                        i4 = 1;
                                    break;
                                case 105:
                                    if (0 < evas_saint - loner)
                                        i4 = 1;
                                    break;
                                case 106:
                                    if (0 < shillien_templar - loner)
                                        i4 = 1;
                                    break;
                                case 107:
                                    if (0 < spectral_dancer - loner)
                                        i4 = 1;
                                    break;
                                case 108:
                                    if (0 < ghost_hunter - loner)
                                        i4 = 1;
                                    break;
                                case 109:
                                    if (0 < ghost_sentinel - loner)
                                        i4 = 1;
                                    break;
                                case 110:
                                    if (0 < storm_screamer - loner)
                                        i4 = 1;
                                    break;
                                case 111:
                                    if (0 < spectral_master - loner)
                                        i4 = 1;
                                    break;
                                case 112:
                                    if (0 < shillien_saint - loner)
                                        i4 = 1;
                                    break;
                                case 113:
                                    if (0 < titan - loner)
                                        i4 = 1;
                                    break;
                                case 114:
                                    if (0 < grand_khavatari - loner)
                                        i4 = 1;
                                    break;
                                case 115:
                                    if (0 < dominator - loner)
                                        i4 = 1;
                                    break;
                                case 116:
                                    if (0 < doomcryer - loner)
                                        i4 = 1;
                                    break;
                                case 117:
                                    if (0 < fortune_seeker - loner)
                                        i4 = 1;
                                    break;
                                case 118:
                                    if (0 < maestro - loner)
                                        i4 = 1;
                                    break;
                                case 131:
                                    if (0 < doombringer - loner)
                                        i4 = 1;
                                    break;
                                case 132:
                                    if (0 < m_soul_hound - loner)
                                        i4 = 1;
                                    break;
                                case 133:
                                    if (0 < f_soul_hound - loner)
                                        i4 = 1;
                                    break;
                                case 134:
                                    if (0 < trickster - loner)
                                        i4 = 1;
                                    break;
                                case 135:
                                case 136:
                                    if (0 < judicator - loner)
                                        i4 = 1;
                                    break;
                            }

                            if (CategoryManager.isInCategory(5, c0.getActiveClass().getClassId()) && CategoryManager.isInCategory(9, c0.getActiveClass().getClassId()))
                                i4 = 1;
                            if (CategoryManager.isInCategory(94, c0.getActiveClass().getClassId()) && CategoryManager.isInCategory(9, c0.getActiveClass().getClassId()))
                                i4 = 1;
                            if (i4 == 1)
                                onIntentionCast(morale_up_display, c0);
                        }
                    }
                }
            }
            actor.i_ai0 = 0;
            actor.i_ai8 = 0;
            addTimer(2321001, 55000);
        }

        super.onEvtAttacked(attacker, damage, skill);
    }

    @Override
    protected void onEvtSeeSpell(Skill skill, Creature caster) {
        if (caster == null) {
            return;
        }
        if (skill != null && skill.getId() == 985) {
            Skill challenge_accepted = SkillTable.getInstance().getInfo(6919, 1);
            if (challenge_accepted != null) {
                onIntentionCast(challenge_accepted, actor);
            }
        }
    }

    @Override
    protected void onEvtDead(Creature killer) {
        super.onEvtDead(killer);

        Player player = killer != null ? killer.getPlayer() : null;
        if (player != null) {
            int itemId = Rnd.get(1, 2) == 1 ? 8604 : 8605;
            ItemInstance item = ItemFunctions.createItem(itemId);
            SystemMessage2 sm = new SystemMessage2(SystemMsg.C1_DIED_AND_DROPPED_S3_S2);
            sm.addName(actor);
            sm.addItemName(itemId);
            sm.addLong(item.getCount());
            actor.broadcastPacket(sm);
            player.doAutoLootOrDrop(item, (NpcInstance)actor);
        }
    }
}

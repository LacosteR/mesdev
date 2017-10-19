package ai;

import l2p.gameserver.ai.Mystic;
import l2p.gameserver.model.Creature;
import l2p.gameserver.model.Effect;
import l2p.gameserver.model.Skill;
import l2p.gameserver.model.instances.NpcInstance;
import l2p.gameserver.tables.SkillTable;
import l2p.technical.util.Rnd;

import java.util.List;

/**
 * AI for:
 * Hot Springs Atrox (id 21321)
 * Hot Springs Atroxspawn (id 21317)
 * Hot Springs Bandersnatch (id 21322)
 * Hot Springs Bandersnatchling (id 21314)
 * Hot Springs Flava (id 21316)
 * Hot Springs Nepenthes (id 21319)
 *
 * @author Diamond
 */
public class HotSpringsMob extends Mystic {
    private static final int DeBuffs[] = {4554, 4552};

    public HotSpringsMob(NpcInstance actor) {
        super(actor);
    }

    @Override
    protected void onEvtAttacked(Creature attacker, int damage, Skill skill) {
        NpcInstance actor = getActor();
        if (attacker != null && Rnd.chance(5)) {
            int DeBuff = DeBuffs[Rnd.get(DeBuffs.length)];
            List<Effect> effect = attacker.getEffectList().getEffectsBySkillId(DeBuff);
            if (effect != null) {
                int level = effect.get(0).getSkill().getLevel();
                if (level < 4) {
                    effect.get(0).exit();
                    Skill skill1 = SkillTable.getInstance().getInfo(DeBuff, level + 1);
                    skill1.getEffects(actor, attacker, false, false);
                }
            } else {
                Skill skill2 = SkillTable.getInstance().getInfo(DeBuff, 1);
                if (skill2 != null)
                    skill2.getEffects(actor, attacker, false, false);
                else
                    System.out.println("Skill " + DeBuff + " is null, fix it.");
            }
        }
        super.onEvtAttacked(attacker, damage, skill);
    }
}
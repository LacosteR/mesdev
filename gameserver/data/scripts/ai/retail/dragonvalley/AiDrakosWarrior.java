package ai.retail.dragonvalley;

import l2p.gameserver.ai.base.RetailClass;
import l2p.gameserver.ai.retail.WarriorUseSkill;
import l2p.gameserver.model.Creature;
import l2p.gameserver.model.Skill;
import l2p.gameserver.model.instances.NpcInstance;
import l2p.technical.util.Rnd;
/**
 * @author : Alice
 * @date : 08.10.2017
 * @time : 13:44
 * <p/>
 */
@RetailClass(name = "ai_drakos_warrior")
public class AiDrakosWarrior extends WarriorUseSkill {
    private int summonSkill = 449445889;

    public AiDrakosWarrior(NpcInstance actor) {
        super(actor);
    }

    @Override
    public void onEvtAttacked(Creature attacker, int damage, Skill skill) {
        if (Rnd.get(100) < 1) {
            addUseSkillDesire(myself, summonSkill, 1, 0, Integer.MAX_VALUE);
            int i1 = 2 + Rnd.get(3);
            for (int i0 = 0; i0 < i1; i0 = i0++) {
                myself.createOnePrivate(1022823, "ai_drakos_assasin", 0, 0, myself.getPositionComponent().getX() + Rnd.get(200), myself.getPositionComponent().getY() + Rnd.get(200), myself.getPositionComponent().getZ(), 0, attacker.getPositionComponent().getGeoIndex(), 0, 0);
            }
        }
        super.onEvtAttacked(attacker, damage, skill);
    }
}

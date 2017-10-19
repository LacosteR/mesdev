package ai.retail.dragonvalley;

import l2p.gameserver.ai.retail.WarriorUseSkill;
import l2p.gameserver.model.Creature;
import l2p.gameserver.model.GameObjectsStorage;
import l2p.gameserver.model.Skill;
import l2p.gameserver.model.instances.NpcInstance;
/**
 * @author : Alice
 * @date : 08.10.2017
 * @time : 11:21
 * <p/>
 */
public class AiMalukSummonBoomer extends WarriorUseSkill {
    private int kamikazeSkill = 448921601;

    public AiMalukSummonBoomer(NpcInstance actor) {
        super(actor);
    }

    @Override
    public void onEvtSpawn() {
        if (myself.param1 != 0) {
            Creature c0 = GameObjectsStorage.getAsCharacter(myself.param1);
            if (c0 != null) {
                addAttackDesire(c0, 1, 10000);
                addUseSkillDesire(c0, kamikazeSkill, 0, 1, 999999999);
            }
        }
        super.onEvtSpawn();
    }

    @Override
    public void onEvtUseSkillFinished(Creature target, Skill skill) {
        if (skill.getId() == kamikazeSkill) {
            myself.suicide();
        }
        super.onEvtUseSkillFinished(target, skill);
    }
}

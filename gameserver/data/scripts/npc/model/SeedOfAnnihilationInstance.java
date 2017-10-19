package npc.model;


import l2p.gameserver.model.Creature;
import l2p.gameserver.model.instances.MonsterInstance;
import l2p.gameserver.templates.npc.MinionData;
import l2p.gameserver.templates.npc.NpcTemplate;
import l2p.technical.util.Rnd;
import org.apache.commons.lang3.ArrayUtils;

/**
 * У монстров в Seed of Annihilation список минионов может быть разный.
 *
 * @author Bonux
 */
public class SeedOfAnnihilationInstance extends MonsterInstance {
    private static final int[] BISTAKON_MOBS = new int[]{22750, 22751, 22752, 22753};
    private static final int[] COKRAKON_MOBS = new int[]{22763, 22764, 22765};
    private static final int[][] BISTAKON_MINIONS = new int[][]{
            {22746, 22746, 22746},
            {22747, 22747, 22747},
            {22748, 22748, 22748},
            {22749, 22749, 22749}};
    private static final int[][] COKRAKON_MINIONS = new int[][]{
            {22760, 22760, 22761},
            {22760, 22760, 22762},
            {22761, 22761, 22760},
            {22761, 22761, 22762},
            {22762, 22762, 22760},
            {22762, 22762, 22761}};

    public SeedOfAnnihilationInstance(int objectId, NpcTemplate template) {
        super(objectId, template);
        if (ArrayUtils.contains(BISTAKON_MOBS, template.getNpcId()))
            addMinions(BISTAKON_MINIONS[Rnd.get(BISTAKON_MINIONS.length)], template);
        else if (ArrayUtils.contains(COKRAKON_MOBS, template.getNpcId()))
            addMinions(COKRAKON_MINIONS[Rnd.get(COKRAKON_MINIONS.length)], template);
    }

    private static void addMinions(int[] minions, NpcTemplate template) {
        if (minions != null && minions.length > 0)
            for (int id : minions)
                template.addMinion(new MinionData(id, 1));
    }

    @Override
    protected void onDeath(Creature killer) {
        //TODO: Сделать ПТСный АИ
        //getMinionList().unspawnMinions();
        super.onDeath(killer);
    }

    @Override
    public boolean canChampion() {
        return false;
    }
}
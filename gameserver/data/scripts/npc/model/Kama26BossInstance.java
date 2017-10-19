package npc.model;

import l2p.gameserver.templates.npc.NpcTemplate;

public class Kama26BossInstance extends KamalokaBossInstance {
    public Kama26BossInstance(int objectId, NpcTemplate template) {
        super(objectId, template);
        /*Privates pvt = new Privates();  TODO сделать ПТСный АИ
        pvt.setNpcName(NpcDataHolder.getInstance().getNpcNameById(18556));
        pvt.setCount(1);
        getMinionList().addMinion(pvt);*/
    }

    /*
    @Override
    public void notifyMinionDied(MinionInstance minion) {
        _spawner = ThreadPoolManager.getInstance().scheduleAtFixedRate(new MinionSpawner(), 60000, 60000);
    } */
}
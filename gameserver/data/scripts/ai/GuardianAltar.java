package ai;

import l2p.gameserver.ai.CtrlEvent;
import l2p.gameserver.ai.DefaultAI;
import l2p.gameserver.data.xml.holder.NpcHolder;
import l2p.gameserver.model.Creature;
import l2p.gameserver.model.Player;
import l2p.gameserver.model.SimpleSpawner;
import l2p.gameserver.model.Skill;
import l2p.gameserver.model.instances.NpcInstance;
import l2p.gameserver.scripts.Functions;
import l2p.gameserver.utils.GArray;
import l2p.gameserver.utils.Location;
import l2p.technical.util.Rnd;

/**
 * AI 18811	Guardian of the Altar - Спавит рандомных охранников если атакован
 * - если у игрока есть Protection Souls Pendant 14848 - спавнит мини-рб
 * - не использует random walk
 * - не отвечает на атаки
 *
 * @author pchayka
 */
public class GuardianAltar extends DefaultAI {

    private static final int DarkShamanVarangka = 18808;

    public GuardianAltar(NpcInstance actor) {
        super(actor);
        actor.startDamageBlockedHp();
        actor.startDebuffImmunity();
    }

    @Override
    protected void onEvtAttacked(Creature attacker, int damage, Skill skill) {
        NpcInstance actor = getActor();
        if (attacker == null)
            return;

        Player player = attacker.getPlayer();

        if (Rnd.chance(40) && player.getInventory().destroyItemByItemId(14848, 1L)) {
            GArray<NpcInstance> around = actor.getAroundNpc(1500, 300);
            if (around != null && !around.isEmpty())
                for (NpcInstance npc : around)
                    if (npc.getNpcId() == 18808) {
                        Functions.npcSay(actor, "I can sense the presence of Dark Shaman already!");
                        return;
                    }

            try {
                SimpleSpawner sp = new SimpleSpawner(NpcHolder.getInstance().getTemplate(DarkShamanVarangka));
                sp.setLoc(Location.findPointToStay(actor, 400, 420));
                NpcInstance npc = sp.doSpawn(true);
                if (attacker.isPet() || attacker.isSummon())
                    npc.getAI().notifyEvent(CtrlEvent.EVT_AGGRESSION, attacker, Rnd.get(2, 100));
                npc.getAI().notifyEvent(CtrlEvent.EVT_AGGRESSION, attacker.getPlayer(), Rnd.get(1, 100));
            } catch (Exception e) {
                e.printStackTrace();
            }

        } else if (Rnd.chance(5)) {
            GArray<NpcInstance> around = actor.getAroundNpc(1000, 300);
            if (around != null && !around.isEmpty())
                for (NpcInstance npc : around)
                    if (npc.getNpcId() == 22702)
                        return;

            for (int i = 0; i < 2; i++)
                try {
                    SimpleSpawner sp = new SimpleSpawner(NpcHolder.getInstance().getTemplate(22702));
                    sp.setLoc(Location.findPointToStay(actor, 150, 160));
                    NpcInstance npc = sp.doSpawn(true);
                    if (attacker.isPet() || attacker.isSummon())
                        npc.getAI().notifyEvent(CtrlEvent.EVT_AGGRESSION, attacker, Rnd.get(2, 100));
                    npc.getAI().notifyEvent(CtrlEvent.EVT_AGGRESSION, attacker.getPlayer(), Rnd.get(1, 100));
                } catch (Exception e) {
                    e.printStackTrace();
                }
        }
        return;
    }

    @Override
    protected boolean randomWalk() {
        return false;
    }
}
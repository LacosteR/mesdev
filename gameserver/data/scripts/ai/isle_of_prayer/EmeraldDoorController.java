package ai.isle_of_prayer;

import instances.CrystalCaverns;
import l2p.gameserver.ai.DefaultAI;
import l2p.gameserver.model.Creature;
import l2p.gameserver.model.Player;
import l2p.gameserver.model.instances.DoorInstance;
import l2p.gameserver.model.instances.NpcInstance;
import l2p.gameserver.utils.ItemFunctions;

public class EmeraldDoorController extends DefaultAI {
    private boolean openedDoor = false;
    private Player opener = null;

    public EmeraldDoorController(NpcInstance actor) {
        super(actor);
        actor.setHasChatWindow(false);
    }

    @Override
    protected boolean thinkActive() {
        DoorInstance door = getClosestDoor();
        boolean active = false;
        if (getActor().getPositionComponent().getReflection().getInstancedZoneId() == 10)
            active = ((CrystalCaverns) getActor().getPositionComponent().getReflection()).areDoorsActivated();
        if (door != null && active) {
            for (Creature c : getActor().getAroundCharacters(250, 150))
                if (!openedDoor && c.isPlayer() && ItemFunctions.getItemCount(c.getPlayer(), 9694) > 0) // Secret Key
                {
                    openedDoor = true;
                    ItemFunctions.removeItem(c.getPlayer(), 9694, 1, true);
                    door.openMe();
                    opener = c.getPlayer();
                }

            boolean found = false;
            if (opener != null)
                for (Creature c : getActor().getAroundCharacters(250, 150))
                    if (openedDoor && c.isPlayer() && c.getPlayer() == opener)
                        found = true;

            if (!found)
                door.closeMe();
        }
        return super.thinkActive();
    }

    private DoorInstance getClosestDoor() {
        NpcInstance actor = getActor();
        for (Creature c : actor.getAroundCharacters(200, 200))
            if (c.isDoor())
                return (DoorInstance) c;
        return null;
    }

    @Override
    protected boolean randomWalk() {
        return false;
    }
}
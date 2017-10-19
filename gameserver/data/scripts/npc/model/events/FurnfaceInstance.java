package npc.model.events;

import l2p.gameserver.ThreadPoolManager;
import l2p.gameserver.model.instances.NpcInstance;
import l2p.gameserver.templates.npc.NpcTemplate;
import l2p.technical.threading.RunnableImpl;

public class FurnfaceInstance extends NpcInstance {
    public FurnfaceInstance(int objectId, NpcTemplate template) {
        super(objectId, template);
        setTargetable(false);
    }

    private boolean is_activated;
           /*
    @Override
    public void showChatWindow(Player player, int val, Object... arg)
    {
        if(val == 0)
        {
            String htmlpath = null;
            if(c_ai0 == null && i_quest2 == 0)
            {
                htmlpath = "event/monastyre/minigame_furnace.htm";
                c_ai1 = player;
            }
            else if(c_ai0 == null && i_quest2 == 1)
                htmlpath = "event/monastyre/minigame_instructor008.htm";
            else if(c_ai0 == player && i_quest0 == 1 && i_quest1 == 0)
                htmlpath = "event/monastyre/minigame_instructor002.htm";
            else if(c_ai0 == player && i_quest0 == 2 && i_quest1 == 0)
                htmlpath = "event/monastyre/minigame_instructor003.htm";
            else if(c_ai0 != player)
                htmlpath = "event/monastyre/minigame_instructor004.htm";
            else if(c_ai0 == player && i_quest1 == 1)
                htmlpath = "event/monastyre/minigame_instructor007.htm";

            player.sendPacket(new NpcHtmlMessage(player, this, htmlpath, val));
        }
        else
            super.showChatWindow(player, val);
    }   */

    public void setActive2114001(int i) {
        setTargetable(false);
        if (getAISpawnParam() == i) {
            setNpcState(1);
            ThreadPoolManager.getInstance().schedule(new OFF_TIMER(), 2 * 1000);
        }
    }

    public void setActive2114002() {
        setTargetable(false);
        setNpcState(1);
        ThreadPoolManager.getInstance().schedule(new OFF_TIMER(), 2 * 1000);
    }

    public void setSCE_GAME_PLAYER_START() {
        setNpcState(1);
        ThreadPoolManager.getInstance().schedule(new OFF_TIMER(), 2 * 1000);
        setTargetable(true);
    }

    public void setSCE_GAME_END() {
        setNpcState(1);
        ThreadPoolManager.getInstance().schedule(new OFF_TIMER(), 2 * 1000);
        setTargetable(false);
    }

    public void setSCE_GAME_FAILURE() {
        setTargetable(false);
        setNpcState(1);
        ThreadPoolManager.getInstance().schedule(new OFF_TIMER(), 2 * 1000);
    }

    private class OFF_TIMER extends RunnableImpl {
        @Override
        public void runImpl() throws Exception {
            setNpcState(2);
        }
    }

    @Override
    public boolean hasRandomAnimation() {
        return false;
    }

    @Override
    public boolean hasRandomWalk() {
        return false;
    }
}
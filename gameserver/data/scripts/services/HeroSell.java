package services;

import l2p.gameserver.ThreadPoolManager;
import l2p.gameserver.config.ConfigServices;
import l2p.gameserver.listener.actor.player.OnPlayerEnterListener;
import l2p.gameserver.model.Player;
import l2p.gameserver.model.actor.listener.CharListenerList;
import l2p.gameserver.model.entity.Hero;
import l2p.gameserver.mods.GenerateElement;
import l2p.gameserver.mods.Merchant;
import l2p.gameserver.network.serverpackets.SkillList;
import l2p.gameserver.scripts.Functions;
import l2p.gameserver.scripts.ScriptFile;

public class HeroSell extends Functions implements ScriptFile, OnPlayerEnterListener {

    public void showPage() {
        Player player = getSelf();
        if (player == null) {
            return;
        }
        if (!ConfigServices.HERO_SELL_ALLOW) {
            show("Service is offline.", player);
            return;
        }
        StringBuilder sb = new StringBuilder();
        sb.append("!Hero selling");
        sb.append("<br>");
        sb.append("To become a hero for:");
        for(int i = 0; i < ConfigServices.HERO_SELL_TIME.size(); i++) {
            sb.append(GenerateElement.button(ConfigServices.HERO_SELL_TIME.get(i) + " hours for " + ConfigServices.HERO_SELL_ITEM_COUNT.get(i) + " " + Merchant.getItemName(ConfigServices.HERO_SELL_ITEM_ID), "-h scripts_services.HeroSell:setHero " + ConfigServices.HERO_SELL_TIME.get(i), 250, 25));
        }
        show(sb.toString(), player);
    }

    public void setHero(String[] args) {
        Player player = getSelf();
        int hour = Integer.parseInt(args[0]);
        if (player == null || player.isHero() || !ConfigServices.HERO_SELL_TIME.contains(hour)) {
            return;
        }
        long pay_count = -1;
        for(int i = 0; i < ConfigServices.HERO_SELL_TIME.size(); i++) {
            if (ConfigServices.HERO_SELL_TIME.get(i) == hour) {
                pay_count = ConfigServices.HERO_SELL_ITEM_COUNT.get(i);
                break;
            }
        }
        if (pay_count == -1) {
            return;
        }

        if (Merchant.getPay(player, ConfigServices.HERO_SELL_ITEM_ID, pay_count)) {
            long time = System.currentTimeMillis() + hour * 3600000;
            player.setVar("HeroSell", String.valueOf(time), -1);
            player.setHero(true);
            Hero.addSkills(player);
            player.updatePledgeClass();
            player.sendPacket(new SkillList(player));
            player.broadcastUserInfo(true);
            ThreadPoolManager.getInstance().schedule(new RemoveHero(player), hour * 3600000);
        }
    }

    @Override
    public void onPlayerEnter(Player player) {
        if (player.getVar("HeroSell") != null) {
            long time = player.getVarLong("HeroSell");
            time -= System.currentTimeMillis();
            if (time > 0) {
                player.setHero(true);
                Hero.addSkills(player);
                player.updatePledgeClass();
                player.sendPacket(new SkillList(player));
                player.broadcastUserInfo(true);
                ThreadPoolManager.getInstance().schedule(new RemoveHero(player), time);
            } else {
                player.unsetVar("HeroSell");
            }
        }
    }

    @Override
    public void onLoad() {
        CharListenerList.addGlobal(this);
    }

    @Override
    public void onReload() {
    }

    @Override
    public void onShutdown() {
    }

    private class RemoveHero implements Runnable {
        private Player player;

        public RemoveHero(Player player) {
            this.player = player;
        }

        @Override
        public void run() {
            player.unsetVar("HeroSell");
            player.setHero(false);
            Hero.removeSkills(player);
            player.updatePledgeClass();
            player.sendPacket(new SkillList(player));
            player.broadcastUserInfo(true);
        }
    }
}
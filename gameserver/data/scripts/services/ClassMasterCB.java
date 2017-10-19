package services;

import l2p.gameserver.cache.Msg;
import l2p.gameserver.config.ConfigServices;
import l2p.gameserver.data.htm.HtmCache;
import l2p.gameserver.data.xml.holder.ItemHolder;
import l2p.gameserver.model.Player;
import l2p.gameserver.model_pts.pts.PcClass;
import l2p.gameserver.mods.Merchant;
import l2p.gameserver.scripts.Functions;
import l2p.gameserver.templates.item.ItemTemplate;
import l2p.gameserver.utils.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class ClassMasterCB extends Functions {
    private static final Logger log = LoggerFactory.getLogger(ClassMasterCB.class);

    public void show() {
        if (!ConfigServices.CB_CLASS_ENABLED) {
            return;
        }
        Player player = getSelf();
        if (player == null) {
            return;
        }
        PcClass classId = player.getPcClass();
        int jobLevel = classId.getLevel();
        int level = player.getLevel();
        String html = HtmCache.getInstance().getNotNull("scripts/services/classmaster/classCB.htm", player);

        if (level >= 20 && jobLevel == 1 || level >= 40 && jobLevel == 2 || level >= 76 && jobLevel == 3) {
            ItemTemplate item = ItemHolder.getInstance().getTemplate(ConfigServices.CLASS_MASTERS_PRICE_ITEM);
            String body = HtmCache.getInstance().getNotNull("scripts/services/classmaster/classCB_body.htm", player);
            body = body.replace("%price_count%", Util.formatAdena(ConfigServices.CLASS_MASTERS_PRICE[jobLevel-1]));
            body = body.replace("%price_name%", item.getName());

            String button = HtmCache.getInstance().getNotNull("scripts/services/classmaster/classCBbutton.htm", player);
            StringBuilder sb = new StringBuilder();
            for (PcClass cid : PcClass.values()) {
                if (cid == PcClass.inspector) {
                    continue;
                }
                if (cid.isChild(classId) && cid.getLevel() == classId.getLevel() + 1) {
                    sb.append(button.replace("%class_id%", String.valueOf(cid.class_id)).replace("%class_name%", cid.name()));
                }
            }
            body = body.replace("%class_job_list%", sb.toString());
            html = html.replace("%body%", body);
        } else {
            String job_inner = "";
            switch (jobLevel) {
                case 1:
                    job_inner = HtmCache.getInstance().getNotNull("scripts/services/classmaster/classCBjoblvl_1.htm", player);
                    job_inner = job_inner.replace("%user_name%", player.getName());
                    job_inner = job_inner.replace("%current_class_name%", player.getPcClass().name());
                    html = html.replace("%body%", job_inner);
                    break;
                case 2:
                    job_inner = HtmCache.getInstance().getNotNull("scripts/services/classmaster/classCBjoblvl_2.htm", player);
                    job_inner = job_inner.replace("%user_name%", player.getName());
                    job_inner = job_inner.replace("%current_class_name%", player.getPcClass().name());
                    html = html.replace("%body%", job_inner);
                    break;
                case 3:
                    job_inner = HtmCache.getInstance().getNotNull("scripts/services/classmaster/classCBjoblvl_3.htm", player);
                    job_inner = job_inner.replace("%user_name%", player.getName());
                    job_inner = job_inner.replace("%current_class_name%", player.getPcClass().name());
                    html = html.replace("%body%", job_inner);
                    break;
                case 4:
                    job_inner = HtmCache.getInstance().getNotNull("scripts/services/classmaster/classCBjoblvl_4.htm", player);
                    job_inner = job_inner.replace("%user_name%", player.getName());
                    job_inner = job_inner.replace("%current_class_name%", player.getPcClass().name());
                    if (level < 76) {
                        job_inner = job_inner.replace("%can_activate_sub%", "");
                    } else {
                        job_inner = job_inner.replace("%can_activate_sub%", HtmCache.getInstance().getNotNull("scripts/services/classmaster/classCBjoblvl_can_activate_sub.htm", player));
                        if (!player.isNoble()) {
                            job_inner = job_inner.replace("%can_noble%", HtmCache.getInstance().getNotNull("scripts/services/classmaster/classCBjoblvl_cannoble.htm", player));
                        } else {
                            job_inner = job_inner.replace("%can_noble%", HtmCache.getInstance().getNotNull("scripts/services/classmaster/classCBjoblvl_cannotnoble.htm", player));
                        }
                    }
                    html = html.replace("%body%", job_inner);
                    break;
            }
        }
        show(html, player);
    }

    public void changeClass(String[] args) {
        Player player = getSelf();
        if (player == null) {
            return;
        }
        PcClass classId = player.getPcClass();
        int class_id = -1;
        for (PcClass cid : PcClass.values()) {
            if (cid == PcClass.inspector) {
                continue;
            }
            if (cid.isChild(classId) && cid.getLevel() == classId.getLevel() + 1 && Integer.parseInt(args[0]) == cid.class_id) {
                class_id = cid.class_id;
                break;
            }
        }
        if (class_id == -1) {
            log.error("Player {} is trying to change his class {} to {}", player.getName(), classId.name(), args[0]);
            player.sendMessage("Wrong class ID");
            return;
        }
        if (Merchant.getPay(player, ConfigServices.CLASS_MASTERS_PRICE_ITEM, ConfigServices.CLASS_MASTERS_PRICE[classId.getLevel() - 1])) {
            if (player.getPcClass().getLevel() == 3) {
                player.sendPacket(Msg.YOU_HAVE_COMPLETED_THE_QUEST_FOR_3RD_OCCUPATION_CHANGE_AND_MOVED_TO_ANOTHER_CLASS_CONGRATULATIONS);
            } else {
                player.sendPacket(Msg.CONGRATULATIONS_YOU_HAVE_TRANSFERRED_TO_A_NEW_CLASS);
            }
            player.setClassId(class_id, true, false);
            player.broadcastUserInfo(true);
        }
    }
}

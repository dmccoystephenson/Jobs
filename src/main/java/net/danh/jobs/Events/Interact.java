package net.danh.jobs.Events;

import net.danh.jobs.Files.Files;
import net.danh.jobs.Jobs;
import net.milkbowl.vault.economy.EconomyResponse;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractAtEntityEvent;

import java.util.logging.Level;

public class Interact implements Listener {

    @EventHandler
    public void onRev(PlayerInteractAtEntityEvent e) {
        Player bs = e.getPlayer();
        if (Files.getInstance().getdata().getString("players." + bs.getName()).equals("BACSI")) {
            if (Files.getInstance().getconfig().getBoolean("debug")) {
                Jobs.getInstance().getLogger().log(Level.INFO, "Nguoi choi la bac si");
            }
            if (e.getRightClicked().getType().equals(EntityType.PLAYER)) {
                if (Files.getInstance().getconfig().getBoolean("debug")) {
                    Jobs.getInstance().getLogger().log(Level.INFO, "Bac si da click phai vao nguoi choi khac");
                }
                String name = e.getRightClicked().getName();
                Player bn = Bukkit.getPlayerExact(name).getPlayer();
                if (bn.getHealth() != bn.getMaxHealth()) {
                    if (Files.getInstance().getconfig().getBoolean("debug")) {
                        Jobs.getInstance().getLogger().log(Level.INFO, "Benh nhan khong co du mau");
                    }
                    if (bs.getItemInHand().getType() == Material.getMaterial(Files.getInstance().getconfig().getString("doctor_material"))) {
                        if (Files.getInstance().getconfig().getBoolean("debug")) {
                            Jobs.getInstance().getLogger().log(Level.INFO, "Thuoc dung loai");
                        }
                        bn.setHealth(bn.getHealth() + (bs.getItemInHand().getAmount() / 2));

                        if (Files.getInstance().getconfig().getBoolean("debug")) {
                            Jobs.getInstance().getLogger().log(Level.INFO, "Da hoi mau");
                        }
                        bs.setItemInHand(null);

                        if (Files.getInstance().getconfig().getBoolean("debug")) {
                            Jobs.getInstance().getLogger().log(Level.INFO, "Da xoa thuoc");
                        }
                        bs.giveExp(10);

                        if (Files.getInstance().getconfig().getBoolean("debug")) {
                            Jobs.getInstance().getLogger().log(Level.INFO, "Het");
                        }
                    }
                }
            }
        }
        if (Files.getInstance().getdata().getString("players." + bs.getName()).equals("ANTROM")) {
            if (Files.getInstance().getconfig().getBoolean("debug")) {
                Jobs.getInstance().getLogger().log(Level.INFO, "Nguoi choi la an trom");
            }
            if (e.getRightClicked().getType().equals(EntityType.PLAYER)) {
                if (Files.getInstance().getconfig().getBoolean("debug")) {
                    Jobs.getInstance().getLogger().log(Level.INFO, "An trom da click phai vao nguoi choi khac");
                }
                String name = e.getRightClicked().getName();
                Player bn = Bukkit.getPlayerExact(name).getPlayer();
                if (Jobs.economy.getBalance(bn) <= 100) {
                    if (Files.getInstance().getconfig().getBoolean("debug")) {
                        Jobs.getInstance().getLogger().log(Level.INFO, "Nan nhan khong co du tien");
                    }
                    if (bs.getItemInHand().getType() == Material.getMaterial(Files.getInstance().getconfig().getString("doctor_material"))) {
                        if (Files.getInstance().getconfig().getBoolean("debug")) {
                            Jobs.getInstance().getLogger().log(Level.INFO, "Dung loai vu khi an trom");
                        }

                        EconomyResponse er = Jobs.economy.withdrawPlayer(bn, 100);
                        EconomyResponse es = Jobs.economy.depositPlayer(bs, 100);

                        if (Files.getInstance().getconfig().getBoolean("debug")) {
                            Jobs.getInstance().getLogger().log(Level.INFO, "Da an trom tien");
                        }
                        bs.setItemInHand(null);

                        if (Files.getInstance().getconfig().getBoolean("debug")) {
                            Jobs.getInstance().getLogger().log(Level.INFO, "Da xoa thuoc");
                        }
                        bs.giveExp(10);

                        if (Files.getInstance().getconfig().getBoolean("debug")) {
                            Jobs.getInstance().getLogger().log(Level.INFO, "Het");
                        }
                    }
                }
            }
        }
    }
}

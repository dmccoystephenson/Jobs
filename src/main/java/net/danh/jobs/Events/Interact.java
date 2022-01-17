package net.danh.jobs.Events;

import net.danh.jobs.Files.Files;
import net.danh.jobs.Jobs;
import net.milkbowl.vault.economy.EconomyResponse;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractAtEntityEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.potion.PotionEffectType;

import java.util.ArrayList;
import java.util.logging.Level;

public class Interact implements Listener {


    @EventHandler(priority = EventPriority.LOWEST)
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

                    ItemStack items = new ItemStack(Material.valueOf(Files.getInstance().getconfig().getString("doctor_items.MATERIAL")), Files.getInstance().getconfig().getInt("doctor_items.AMOUNT"));
                    ItemMeta meta = items.getItemMeta();

                    meta.setDisplayName(Files.getInstance().convert(Files.getInstance().getconfig().getString("doctor_items.DISPLAY_NAME")));
                    ArrayList<String> lore = new ArrayList<String>();
                    lore.add(Files.getInstance().convert(Files.getInstance().getconfig().getString("doctor_items.LORE1")));
                    lore.add(Files.getInstance().convert(Files.getInstance().getconfig().getString("doctor_items.LORE1")));
                    meta.setLore(lore);
                    items.setItemMeta(meta);
                    Material item = items.getType();

                    if (bs.getItemInHand().getType() == null) {
                        return;
                    }
                    if (bs.getItemInHand().getType() != null && bs.getItemInHand().getType() == item) {
                        if (Files.getInstance().getconfig().getBoolean("debug")) {
                            Jobs.getInstance().getLogger().log(Level.INFO, "Thuoc dung loai");
                        }
                        if (bs.getItemInHand().getAmount() == 30) {
                            bn.setHealth(bn.getMaxHealth());
                            bs.setItemInHand(null);
                            if (Files.getInstance().getconfig().getBoolean("debug")) {
                                Jobs.getInstance().getLogger().log(Level.INFO, "Da xoa thuoc");
                            }
                            if (Files.getInstance().getconfig().getBoolean("debug")) {
                                Jobs.getInstance().getLogger().log(Level.INFO, "Da hoi mau");

                            }


                            bs.giveExp(10);
                        } else {
                            bs.sendMessage(Files.getInstance().convert("&cBạn chỉ cần 30 viên thuốc"));
                        }
                    }
                }
                if (Files.getInstance().getconfig().getBoolean("debug")) {
                    Jobs.getInstance().getLogger().log(Level.INFO, "Het");
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
                    ItemStack items = new ItemStack(Material.valueOf(Files.getInstance().getconfig().getString("thief_items.MATERIAL")), Files.getInstance().getconfig().getInt("thief_items.AMOUNT"));
                    ItemMeta meta = items.getItemMeta();

                    meta.setDisplayName(Files.getInstance().convert(Files.getInstance().getconfig().getString("thief_items.DISPLAY_NAME")));
                    ArrayList<String> lore = new ArrayList<String>();
                    lore.add(Files.getInstance().convert(Files.getInstance().getconfig().getString("thief_items.LORE1")));
                    lore.add(Files.getInstance().convert(Files.getInstance().getconfig().getString("thief_items.LORE1")));
                    meta.setLore(lore);
                    items.setItemMeta(meta);
                    Material item = items.getType();

                    if (bs.getItemInHand().getType() == item) {
                        if (Files.getInstance().getconfig().getBoolean("debug")) {
                            Jobs.getInstance().getLogger().log(Level.INFO, "Dung loai vu khi an trom");
                        }

                        bs.addPotionEffect(PotionEffectType.BLINDNESS.createEffect(1800, 3));
                        EconomyResponse er = Jobs.economy.withdrawPlayer(bn, 100);
                        EconomyResponse es = Jobs.economy.depositPlayer(bs, 100);

                        if (Files.getInstance().getconfig().getBoolean("debug")) {
                            Jobs.getInstance().getLogger().log(Level.INFO, "Da an trom tien");
                        }
                        bs.setItemInHand(null);
                        bs.giveExp(10);

                        if (Files.getInstance().getconfig().getBoolean("debug")) {
                            Jobs.getInstance().getLogger().log(Level.INFO, "Da xoa dung cu");
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

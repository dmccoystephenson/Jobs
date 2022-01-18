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
                if (bn.getHealth() >= (bn.getMaxHealth() / 2)) {
                    if (Files.getInstance().getconfig().getBoolean("debug")) {
                        Jobs.getInstance().getLogger().log(Level.INFO, "Benh nhan khong co du mau");
                    } else {
                        bn.sendMessage(Files.getInstance().convert("&cMáu của bạn hiện tại là &a " + Double.valueOf(bn.getHealth()) + "&c nên chưa đủ điều kiện để Bác Sĩ " + bs.getName() + " &chồi phục cho bạn"));
                    }

                    ItemStack items = new ItemStack(Material.valueOf(Files.getInstance().getconfig().getString("doctor_items.MATERIAL")), Files.getInstance().getconfig().getInt("doctor_items.AMOUNT"));
                    ItemMeta meta = items.getItemMeta();

                    meta.setDisplayName(Files.getInstance().convert(Files.getInstance().getconfig().getString("doctor_items.DISPLAY_NAME")));
                    ArrayList<String> lore = new ArrayList<String>();
                    lore.add(Files.getInstance().convert(Files.getInstance().getconfig().getString("doctor_items.LORE1")));
                    lore.add(Files.getInstance().convert(Files.getInstance().getconfig().getString("doctor_items.LORE2")));
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
                        if (bs.getItemInHand().getAmount() != 1) {
                            bs.getItemInHand().setAmount(bs.getItemInHand().getAmount() - 1);
                            bn.setHealth(bn.getMaxHealth());
                        } else {
                            bs.getInventory().remove(item);
                            bn.setHealth(bn.getMaxHealth());
                            bs.sendMessage(Files.getInstance().convert("&eCảm ơn bạn đã cứu bệnh nhân &c" + bn.getName()));
                            bn.sendMessage(Files.getInstance().convert("&eBạn đã được bác sĩ &c" + bs.getName() + " &ecứu thành công!"));
                        }

                        if (Files.getInstance().getconfig().getBoolean("debug")) {
                            Jobs.getInstance().getLogger().log(Level.INFO, "Da xoa thuoc");
                        }
                        if (Files.getInstance().getconfig().getBoolean("debug")) {
                            Jobs.getInstance().getLogger().log(Level.INFO, "Da hoi mau");

                        }

                        bs.giveExp(Files.getInstance().getconfig().getInt("xp"));
                    } else {
                        bs.sendMessage(Files.getInstance().convert("&cBạn cần phải cầm thuốc trên tay để cứu bệnh nhân"));
                        e.setCancelled(true);
                    }
                } else {
                    bs.sendMessage(Files.getInstance().convert("&cBệnh Nhân không dưới 50% máu nên không thể hồi phục"));
                    bn.sendMessage(Files.getInstance().convert("&cBạn không dưới 50% máu nên không thể hồi phục"));
                    e.setCancelled(true);
                }
            }
            if (Files.getInstance().getconfig().getBoolean("debug")) {
                Jobs.getInstance().getLogger().log(Level.INFO, "Het");
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
                if (Jobs.economy.getBalance(bn) > 100) {
                    if (Files.getInstance().getconfig().getBoolean("debug")) {
                        Jobs.getInstance().getLogger().log(Level.INFO, "Nan nhan khong co du tien");
                    }
                    ItemStack items = new ItemStack(Material.valueOf(Files.getInstance().getconfig().getString("thief_items.MATERIAL")), Files.getInstance().getconfig().getInt("thief_items.AMOUNT"));
                    ItemMeta meta = items.getItemMeta();

                    meta.setDisplayName(Files.getInstance().convert(Files.getInstance().getconfig().getString("thief_items.DISPLAY_NAME")));
                    ArrayList<String> lore = new ArrayList<String>();
                    lore.add(Files.getInstance().convert(Files.getInstance().getconfig().getString("thief_items.LORE1")));
                    lore.add(Files.getInstance().convert(Files.getInstance().getconfig().getString("thief_items.LORE2")));
                    meta.setLore(lore);
                    items.setItemMeta(meta);
                    Material item = items.getType();

                    if (bs.getItemInHand().getType() == item) {
                        if (Files.getInstance().getconfig().getBoolean("debug")) {
                            Jobs.getInstance().getLogger().log(Level.INFO, "Dung loai vu khi an trom");
                        }

                        bs.addPotionEffect(PotionEffectType.BLINDNESS.createEffect(1800 * 20, 3));
                        EconomyResponse er = Jobs.economy.withdrawPlayer(bn, 100);
                        EconomyResponse es = Jobs.economy.depositPlayer(bs, 100);

                        if (Files.getInstance().getconfig().getBoolean("debug")) {
                            Jobs.getInstance().getLogger().log(Level.INFO, "Da an trom tien");
                        }
                        bs.setItemInHand(null);
                        bs.giveExp(Files.getInstance().getconfig().getInt("xp"));

                        if (Files.getInstance().getconfig().getBoolean("debug")) {
                            Jobs.getInstance().getLogger().log(Level.INFO, "Da xoa dung cu");
                        }

                        int randomNum = Files.getInstance().getconfig().getInt("xp.min") + (int) (Math.random() * Files.getInstance().getconfig().getInt("xp.max"));

                        bs.giveExp(randomNum);

                        if (Files.getInstance().getconfig().getBoolean("debug")) {
                            Jobs.getInstance().getLogger().log(Level.INFO, "Het");
                        }
                    }
                }
            }
        }
        if (Files.getInstance().getdata().getString("players." + bs.getName()).equals("CANHSAT")) {
            if (Files.getInstance().getconfig().getBoolean("debug")) {
                Jobs.getInstance().getLogger().log(Level.INFO, "Nguoi choi la canh sat");
            }
            if (e.getRightClicked().getType().equals(EntityType.PLAYER)) {
                if (Files.getInstance().getconfig().getBoolean("debug")) {
                    Jobs.getInstance().getLogger().log(Level.INFO, "Canh satda click phai vao nguoi choi khac");
                }
                String name = e.getRightClicked().getName();
                Player bn = (Player) Bukkit.getPlayerExact(name).getPlayer();

                ItemStack items = new ItemStack(Material.valueOf(Files.getInstance().getconfig().getString("hangcam.MATERIAL")), Files.getInstance().getconfig().getInt("hangcam.AMOUNT"));
                ItemMeta meta = items.getItemMeta();

                meta.setDisplayName(Files.getInstance().convert(Files.getInstance().getconfig().getString("hangcam.DISPLAY_NAME")));
                ArrayList<String> lore = new ArrayList<String>();
                lore.add(Files.getInstance().convert(Files.getInstance().getconfig().getString("hangcam.LORE1")));
                lore.add(Files.getInstance().convert(Files.getInstance().getconfig().getString("hangcam.LORE2")));
                meta.setLore(lore);
                items.setItemMeta(meta);
                int amount = 0;
                int am = 0;

                for (int in = 0; in < 36; in++) {
                    if (bs.getInventory().getItem(in) == null) {
                        continue;
                    }
                    if (bs.getInventory().getItem(in).equals(items)) {
                        am += bs.getInventory().getItem(in).getAmount();
                    }
                }
                if (am >= amount) {
                    return;
                } else {
                    bs.sendMessage(Files.getInstance().convert("&cPhát hiện có &a" + amount + " &cvật phẩm cấm trong túi đồ của" + bn.getName()));
                }
                return;
            }
        }
    }
}
package net.danh.jobs.Events;

import net.danh.jobs.Jobs;
import net.danh.jobs.Manager.Cooldown;
import net.danh.jobs.Manager.Files;
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
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.ArrayList;
import java.util.logging.Level;

public class Interact implements Listener {


    @EventHandler(priority = EventPriority.NORMAL)
    public void onRev(PlayerInteractAtEntityEvent e) {
        Player bs = e.getPlayer();
        if (Files.getInstance().getJobs(bs).equals("BACSI")) {
            if (Files.getInstance().getconfig().getBoolean("debug")) {
                Jobs.getInstance().getLogger().log(Level.INFO, "Nguoi choi la bac si");
            }
            if (e.getRightClicked().getType().equals(EntityType.PLAYER)) {
                if (Files.getInstance().getconfig().getBoolean("debug")) {
                    Jobs.getInstance().getLogger().log(Level.INFO, "Bac si da click phai vao nguoi choi khac");
                }
                if (Files.getInstance().getPower(e.getPlayer()) <= 0) {
                    e.getPlayer().sendMessage(Files.getInstance().convert("&cBạn cần phải trên 0 năng lượng để làm việc"));
                    e.setCancelled(true);
                    return;
                } else {
                    String name = e.getRightClicked().getName();
                    Player bn = Bukkit.getPlayerExact(name).getPlayer();
                    if (bn == null){
                        return;
                    }
                    int timeLeft = Cooldown.getInstance().getCooldown(bn.getUniqueId());
                    if (timeLeft == 0) {
                        Cooldown.getInstance().setCooldown(bn.getUniqueId(), Cooldown.DEFAULT_COOLDOWN);
                        new BukkitRunnable() {
                            @Override
                            public void run() {
                                int timeLeft = Cooldown.getInstance().getCooldown(bn.getUniqueId());
                                Cooldown.getInstance().setCooldown(bn.getUniqueId(), --timeLeft);
                                if (timeLeft == 0) {
                                    this.cancel();
                                }
                            }
                        }.runTaskTimer(Jobs.getInstance(), 20, 20);

                    } else {
                        //Hasn't expired yet, shows how many seconds left until it does
                        bn.sendMessage(Files.getInstance().convert("&cBạn cần chờ &6" + timeLeft + "&6s &cđể được cứu chữa!"));
                        bs.sendMessage(Files.getInstance().convert("&cBệnh nhân sẽ được chữa trị sau &6" + timeLeft + "&6s"));
                        return;
                    }
                    if (bn instanceof Player) {
                        if (bn.getPlayer().getHealth() <= (bn.getPlayer().getMaxHealth() / 2)) {
                            if (Files.getInstance().getconfig().getBoolean("debug")) {
                                Jobs.getInstance().getLogger().log(Level.INFO, "Benh nhan khong co du mau");
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
                                bs.getItemInHand().setAmount(bs.getItemInHand().getAmount() - 1);
                                bs.updateInventory();
                                bn.setHealth(bn.getMaxHealth());
                                bs.sendMessage(Files.getInstance().convert("&eCảm ơn bạn đã cứu bệnh nhân &c" + bn.getName()));
                                bn.sendMessage(Files.getInstance().convert("&eBạn đã được bác sĩ &c" + bs.getName() + " &ecứu thành công!"));
                                if (Files.getInstance().getconfig().getBoolean("debug")) {
                                    Jobs.getInstance().getLogger().log(Level.INFO, "Da xoa thuoc");
                                }
                                if (Files.getInstance().getconfig().getBoolean("debug")) {
                                    Jobs.getInstance().getLogger().log(Level.INFO, "Da hoi mau");

                                }

                                if (Files.getInstance().getPower(bs) >= 5) {

                                    double systemchance = Math.random() * 100.0D;
                                    double playerchance = (double) (Files.getInstance().getAge(e.getPlayer()) / 10);
                                    if (playerchance >= systemchance) {
                                        Files.getInstance().addXP(e.getPlayer(), 2);
                                        Files.getInstance().addPower(e.getPlayer(), 5);
                                        e.getPlayer().sendMessage(Files.getInstance().convert("&aChúc mừng bạn cứu được bệnh nhân may mắn! Bạn nhận được 5 năng lượng, 2 kinh nghiệm!"));
                                        return;
                                    }
                                    Files.getInstance().addXP(e.getPlayer(), Files.getInstance().getconfig().getInt("xp"));
                                    EconomyResponse err = Jobs.economy.depositPlayer(bs.getName(), 600);
                                    Files.getInstance().removePower(e.getPlayer(), 1);


                                    bn.setWalkSpeed(0.15F);
                                    Jobs.getInstance().getServer().broadcastMessage(Files.getInstance().convert("&aBệnh Nhân &6" + bn.getName() + "&a được được Bác Sĩ &b" + bs.getName() + "&a cứu giúp"));
                                } else {
                                    bs.sendMessage(Files.getInstance().convert("&cBạn cần trên 5 năng lượng để cứu bệnh nhân"));
                                    e.setCancelled(true);
                                    return;
                                }
                            } else {
                                bs.sendMessage(Files.getInstance().convert("&cBạn cần phải cầm thuốc trên tay để cứu bệnh nhân"));
                                e.setCancelled(true);
                                return;
                            }
                        } else {
                            bs.sendMessage(Files.getInstance().convert("&cBệnh Nhân không dưới 50% máu nên không thể hồi phục"));
                            bn.sendMessage(Files.getInstance().convert("&cBạn không dưới 50% máu nên không thể hồi phục"));
                            e.setCancelled(true);
                            return;
                        }
                    }
                }
            }
        }
        if (Files.getInstance().getJobs(bs).equals("ANTROM")) {
            if (Files.getInstance().getconfig().getBoolean("debug")) {
                Jobs.getInstance().getLogger().log(Level.INFO, "Nguoi choi la an trom");
            }
            if (e.getRightClicked().getType().equals(EntityType.PLAYER)) {
                if (Files.getInstance().getconfig().getBoolean("debug")) {
                    Jobs.getInstance().getLogger().log(Level.INFO, "An trom da click phai vao nguoi choi khac");
                }

                String name = e.getRightClicked().getName();
                Player bn = Bukkit.getPlayerExact(name).getPlayer();

                if (bn == null){
                    return;
                }
                int timeLeft = Cooldown.getInstance().getCooldown(bs.getUniqueId());
                if (timeLeft == 0) {
                    Cooldown.getInstance().setCooldown(bs.getUniqueId(), 180);
                    new BukkitRunnable() {
                        @Override
                        public void run() {
                            int timeLeft = Cooldown.getInstance().getCooldown(bs.getUniqueId());
                            Cooldown.getInstance().setCooldown(bs.getUniqueId(), --timeLeft);
                            if (timeLeft == 0) {
                                this.cancel();
                            }
                        }
                    }.runTaskTimer(Jobs.getInstance(), 20, 20);

                } else {
                    bs.sendMessage(Files.getInstance().convert("&cBạn không thể ăn trộm nạn nhân &a" + bn.getName() + "&c trong &6" + timeLeft + "s"));
                    return;
                }
                if (Files.getInstance().getPower(e.getPlayer()) <= 0) {
                    e.getPlayer().sendMessage(Files.getInstance().convert("&cBạn cần phải trên 0 năng lượng để làm việc"));
                    e.setCancelled(true);
                    return;
                } else {
                    if (bn instanceof Player) {
                        if (Jobs.economy.getBalance(bn.getName()) >= 1D) {
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

                            if (bs.getItemInHand().getType() == item && bs.getItemInHand().hasItemMeta()) {
                                if (Files.getInstance().getconfig().getBoolean("debug")) {
                                    Jobs.getInstance().getLogger().log(Level.INFO, "Dung loai vu khi an trom");
                                }
                                bs.addPotionEffect(new PotionEffect(PotionEffectType.SLOW_DIGGING, 180 * 20, 1));
                                bs.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, 180 * 20, 1));
                                bn.sendMessage(Files.getInstance().convert("&cBạn đã bị ăn trộm đánh cắp mất &6$" + Jobs.economy.getBalance(bn.getName()) / 5));
                                bs.sendMessage(Files.getInstance().convert("&cBạn đã ăn trộm &6$" + (Jobs.economy.getBalance(bn.getName()) / 5) + " &ctừ &b" + bn.getName()));
                                EconomyResponse er = Jobs.economy.withdrawPlayer(bn.getName(), Jobs.economy.getBalance(bn.getName()) / 5);
                                EconomyResponse es = Jobs.economy.depositPlayer(bs.getName(), Jobs.economy.getBalance(bn.getName()) / 5);
                                if (Files.getInstance().getconfig().getBoolean("debug")) {
                                    Jobs.getInstance().getLogger().log(Level.INFO, "Da an trom tien");
                                }
                                bs.setItemInHand(null);
                                Files.getInstance().addXP(e.getPlayer(), Files.getInstance().getconfig().getInt("xp"));
                                Files.getInstance().removePower(e.getPlayer(), 10);

                                if (Files.getInstance().getconfig().getBoolean("debug")) {
                                    Jobs.getInstance().getLogger().log(Level.INFO, "Da xoa dung cu");
                                }

                                if (Files.getInstance().getconfig().getBoolean("debug")) {
                                    Jobs.getInstance().getLogger().log(Level.INFO, "Het");
                                }
                            }
                        }
                    }
                }
            }
        }
        if (Files.getInstance().getJobs(bs).equals("CANHSAT")) {
            if (Files.getInstance().getconfig().getBoolean("debug")) {
                Jobs.getInstance().getLogger().log(Level.INFO, "Nguoi choi la canh sat");
            }
            if (e.getRightClicked().getType().equals(EntityType.PLAYER)) {
                if (Files.getInstance().getconfig().getBoolean("debug")) {
                    Jobs.getInstance().getLogger().log(Level.INFO, "Canh satda click phai vao nguoi choi khac");
                }

                if (bs.getPlayer().getItemInHand().getType() == Material.DIAMOND_BARDING) {


                    if (Files.getInstance().getPower(e.getPlayer()) <= 0) {
                        e.getPlayer().sendMessage(Files.getInstance().convert("&cBạn cần phải trên 0 năng lượng để làm việc"));
                        e.setCancelled(true);
                        return;
                    } else {
                        String name = e.getRightClicked().getName();
                        Player bn = Bukkit.getPlayerExact(name).getPlayer();
                        if (bn == null){
                            return;
                        }
                        if (Files.getInstance().getJobs(bn).equalsIgnoreCase("ANTROM")) {
                            bs.sendMessage(Files.getInstance().convert("&cƠ, &b" + bn.getName() + "&c là ăn trộm kìa.."));
                            bn.sendMessage(Files.getInstance().convert("&cBạn vừa bị cảnh sát lục soát và phát hiện bạn là ăn trộm!"));
                        }
                    }
                }
            }
        }
    }
}


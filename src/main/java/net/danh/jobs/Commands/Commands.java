package net.danh.jobs.Commands;

import net.danh.jobs.Files.Files;
import net.danh.jobs.Jobs;
import net.milkbowl.vault.economy.EconomyResponse;
import org.bukkit.Material;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;

public class Commands implements CommandExecutor {


    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof ConsoleCommandSender) {

            if (label.equalsIgnoreCase("jobs")) {
                Files.getInstance().reloadconfig();
                sender.sendMessage(Files.getInstance().convert("&aReloaded"));
                return true;
            }
            return true;
        }
        if (sender instanceof Player) {
            if (label.equalsIgnoreCase("nghe")) {
                if (args.length == 0) {
                    for (String helpplayer : (Files.getInstance().getlanguage().getStringList("jobs-help")))
                        sender.sendMessage((Files.getInstance().convert(helpplayer).replace("%job%", Files.getInstance().getdata().getString("players." + sender.getName()))));
                    return true;
                }
            }
            if (label.equalsIgnoreCase("doinghe")) {
                if (!Files.getInstance().getdata().getString("players." + sender.getName()).equals("KHONGCONGHE")) {
                    if (args.length == 1) {
                        if (args[0].equalsIgnoreCase("THOMO")) {
                            Files.getInstance().getdata().set("players." + sender.getName(), "THOMO");
                            Files.getInstance().savedata();
                            sender.sendMessage(Files.getInstance().convert(Files.getInstance().getlanguage().getString("job-switched")
                                    .replaceAll("%job%", "Thợ Mỏ")));

                            return true;
                        }
                        if (args[0].equalsIgnoreCase("THOMOC")) {
                            Files.getInstance().getdata().set("players." + sender.getName(), "THOMOC");
                            Files.getInstance().savedata();
                            sender.sendMessage(Files.getInstance().convert(Files.getInstance().getlanguage().getString("job-switched")
                                    .replaceAll("%job%", "Thợ Mộc")));

                            return true;
                        }
                        if (args[0].equalsIgnoreCase("CONGNHAN")) {
                            Files.getInstance().getdata().set("players." + sender.getName(), "CONGNHAN");
                            Files.getInstance().savedata();
                            sender.sendMessage(Files.getInstance().convert(Files.getInstance().getlanguage().getString("job-switched")
                                    .replaceAll("%job%", "Công Nhân")));

                            return true;
                        }

                        if (args[0].equalsIgnoreCase("NGUDAN")) {
                            Files.getInstance().getdata().set("players." + sender.getName(), "NGUDAN");
                            Files.getInstance().savedata();
                            sender.sendMessage(Files.getInstance().convert(Files.getInstance().getlanguage().getString("job-switched")
                                    .replaceAll("%job%", "Ngư Dân")));

                            return true;
                        }


                        if (args[0].equalsIgnoreCase("NONGDAN")) {
                            Files.getInstance().getdata().set("players." + sender.getName(), "NONGDAN");
                            Files.getInstance().savedata();
                            sender.sendMessage(Files.getInstance().convert(Files.getInstance().getlanguage().getString("job-switched")
                                    .replaceAll("%job%", "Nông Dân")));

                            return true;
                        }

                        if (args[0].equalsIgnoreCase("LAOCONG")) {
                            Files.getInstance().getdata().set("players." + sender.getName(), "LAOCONG");
                            Files.getInstance().savedata();
                            sender.sendMessage(Files.getInstance().convert(Files.getInstance().getlanguage().getString("job-switched")
                                    .replaceAll("%job%", "Lao Công")));

                            return true;
                        }
                        if (sender.hasPermission("congchuc")) {
                            if (args[0].equalsIgnoreCase("BACSI")) {
                                Files.getInstance().getdata().set("players." + sender.getName(), "BACSI");
                                Files.getInstance().savedata();
                                sender.sendMessage(Files.getInstance().convert(Files.getInstance().getlanguage().getString("job-switched")
                                        .replaceAll("%job%", "Bác Sĩ")));

                                return true;
                            }

                            if (args[0].equalsIgnoreCase("CANHSAT")) {
                                Files.getInstance().getdata().set("players." + sender.getName(), "CANHSAT");
                                Files.getInstance().savedata();
                                sender.sendMessage(Files.getInstance().convert(Files.getInstance().getlanguage().getString("job-switched")
                                        .replaceAll("%job%", "Cảnh Sát")));
                                EconomyResponse r = Jobs.economy.depositPlayer((OfflinePlayer) sender, 500);

                                return true;
                            }
                        }

                        if (sender.hasPermission("antrom")) {
                            if (args[0].equalsIgnoreCase("ANTROM")) {
                                Files.getInstance().getdata().set("players." + sender.getName(), "ANTROM");
                                Files.getInstance().savedata();
                                sender.sendMessage(Files.getInstance().convert(Files.getInstance().getlanguage().getString("job-switched")
                                        .replaceAll("%job%", "Ăn Trộm")));

                                return true;
                            }
                        }
                    }
                }
            }

            if (label.equalsIgnoreCase("danhsachnghe")) {
                sender.sendMessage(Files.getInstance().convert(Files.getInstance().getlanguage().getString("available-jobs")));
            }
            if (label.equalsIgnoreCase("chonnghe")) {

                if (args.length == 1) {
                    if (Files.getInstance().getdata().getString("players." + sender.getName()).equals("KHONGCONGHE")) {
                        if (args[0].equalsIgnoreCase("THOMO")) {
                            Files.getInstance().getdata().set("players." + sender.getName(), "THOMO");
                            Files.getInstance().savedata();
                            sender.sendMessage(Files.getInstance().convert(Files.getInstance().getlanguage().getString("job-switched")
                                    .replaceAll("%job%", "Thợ Mỏ")));
                            EconomyResponse r = Jobs.economy.depositPlayer((OfflinePlayer) sender, 500);

                            return true;
                        }
                        if (args[0].equalsIgnoreCase("THOMOC")) {
                            Files.getInstance().getdata().set("players." + sender.getName(), "THOMOC");
                            Files.getInstance().savedata();
                            sender.sendMessage(Files.getInstance().convert(Files.getInstance().getlanguage().getString("job-switched")
                                    .replaceAll("%job%", "Thợ Mộc")));
                            EconomyResponse r = Jobs.economy.depositPlayer((OfflinePlayer) sender, 500);

                            return true;
                        }
                        if (args[0].equalsIgnoreCase("CONGNHAN")) {
                            Files.getInstance().getdata().set("players." + sender.getName(), "CONGNHAN");
                            Files.getInstance().savedata();
                            sender.sendMessage(Files.getInstance().convert(Files.getInstance().getlanguage().getString("job-switched")
                                    .replaceAll("%job%", "Công Nhân")));
                            EconomyResponse r = Jobs.economy.depositPlayer((OfflinePlayer) sender, 500);

                            return true;
                        }

                        if (args[0].equalsIgnoreCase("NGUDAN")) {
                            Files.getInstance().getdata().set("players." + sender.getName(), "NGUDAN");
                            Files.getInstance().savedata();
                            sender.sendMessage(Files.getInstance().convert(Files.getInstance().getlanguage().getString("job-switched")
                                    .replaceAll("%job%", "Ngư Dân")));
                            EconomyResponse r = Jobs.economy.depositPlayer((OfflinePlayer) sender, 500);

                            return true;
                        }


                        if (args[0].equalsIgnoreCase("NONGDAN")) {
                            Files.getInstance().getdata().set("players." + sender.getName(), "NONGDAN");
                            Files.getInstance().savedata();
                            sender.sendMessage(Files.getInstance().convert(Files.getInstance().getlanguage().getString("job-switched")
                                    .replaceAll("%job%", "Nông Dân")));
                            EconomyResponse r = Jobs.economy.depositPlayer((OfflinePlayer) sender, 500);

                            return true;
                        }

                        if (args[0].equalsIgnoreCase("LAOCONG")) {
                            Files.getInstance().getdata().set("players." + sender.getName(), "LAOCONG");
                            Files.getInstance().savedata();
                            sender.sendMessage(Files.getInstance().convert(Files.getInstance().getlanguage().getString("job-switched")
                                    .replaceAll("%job%", "Lao Công")));
                            EconomyResponse r = Jobs.economy.depositPlayer((OfflinePlayer) sender, 500);

                            return true;
                        }


                        if (sender.hasPermission("congchuc")) {
                            if (args[0].equalsIgnoreCase("BACSI")) {
                                Files.getInstance().getdata().set("players." + sender.getName(), "BACSI");
                                Files.getInstance().savedata();
                                sender.sendMessage(Files.getInstance().convert(Files.getInstance().getlanguage().getString("job-switched")
                                        .replaceAll("%job%", "Bác Sĩ")));
                                EconomyResponse r = Jobs.economy.depositPlayer((OfflinePlayer) sender, 500);

                                return true;
                            }
                            if (args[0].equalsIgnoreCase("CANHSAT")) {
                                Files.getInstance().getdata().set("players." + sender.getName(), "CANHSAT");
                                Files.getInstance().savedata();
                                sender.sendMessage(Files.getInstance().convert(Files.getInstance().getlanguage().getString("job-switched")
                                        .replaceAll("%job%", "Cảnh Sát")));
                                EconomyResponse r = Jobs.economy.depositPlayer((OfflinePlayer) sender, 500);

                                return true;
                            }
                        }
                        if (sender.hasPermission("antrom")) {
                            if (args[0].equalsIgnoreCase("ANTROM")) {
                                Files.getInstance().getdata().set("players." + sender.getName(), "ANTROM");
                                Files.getInstance().savedata();
                                sender.sendMessage(Files.getInstance().convert(Files.getInstance().getlanguage().getString("job-switched")
                                        .replaceAll("%job%", "Ăn Trộm")));
                                EconomyResponse r = Jobs.economy.depositPlayer((OfflinePlayer) sender, 500);

                                return true;
                            }
                        }
                    } else {
                        sender.sendMessage(Files.getInstance().convert("&cBạn đã có nghề! Muốn đổi nghề vui lòng gõ /doinghe <tên nghề>"));
                    }
                }
            }
            if (sender.hasPermission("congchuc")) {
                if (label.equalsIgnoreCase("thuocbacsi")) {
                    if (Jobs.economy.getBalance(sender.getName()) >= 500) {
                        ItemStack items = new ItemStack(Material.valueOf(Files.getInstance().getconfig().getString("doctor_items.MATERIAL")), Files.getInstance().getconfig().getInt("doctor_items.AMOUNT"));
                        ItemMeta meta = items.getItemMeta();

                        meta.setDisplayName(Files.getInstance().convert(Files.getInstance().getconfig().getString("doctor_items.DISPLAY_NAME")));
                        ArrayList<String> lore = new ArrayList<String>();
                        lore.add(Files.getInstance().convert(Files.getInstance().getconfig().getString("doctor_items.LORE1")));
                        lore.add(Files.getInstance().convert(Files.getInstance().getconfig().getString("doctor_items.LORE1")));
                        meta.setLore(lore);
                        items.setItemMeta(meta);
                        sender.sendMessage(Files.getInstance().convert("&7+1 Vật phẩm"));
                        ((Player) sender).getInventory().addItem(items);
                        Jobs.economy.withdrawPlayer(sender.getName(), 500);
                    } else {
                        sender.sendMessage(Files.getInstance().convert("&cBạn cần có ít nhất 500$ để lấy thuốc"));
                    }
                }
            }
            if (sender.hasPermission("caydao")) {
                if (label.equalsIgnoreCase("caydao")) {

                    ItemStack items = new ItemStack(Material.valueOf(Files.getInstance().getconfig().getString("thief_items.MATERIAL")), Files.getInstance().getconfig().getInt("thief_items.AMOUNT"));
                    ItemMeta meta = items.getItemMeta();

                    meta.setDisplayName(Files.getInstance().convert(Files.getInstance().getconfig().getString("thief_items.DISPLAY_NAME")));
                    ArrayList<String> lore = new ArrayList<String>();
                    lore.add(Files.getInstance().convert(Files.getInstance().getconfig().getString("thief_items.LORE1")));
                    lore.add(Files.getInstance().convert(Files.getInstance().getconfig().getString("thief_items.LORE1")));
                    meta.setLore(lore);
                    items.setItemMeta(meta);
                    sender.sendMessage(Files.getInstance().convert("&7+1 Vật phẩm"));
                    ((Player) sender).getInventory().addItem(items);
                }
            }
            if (label.equalsIgnoreCase("115")) {
                if (((Player) sender).getHealth() <= (((Player) sender).getMaxHealth() / 2)) {
                    if (Files.getInstance().getdata().getString("players." + ((Player) sender).getPlayer().getName()).equals("BACSI")) {
                        if (Jobs.economy.getBalance(((Player) sender).getPlayer().getName()) >= 200) {
                            ((Player) sender).setHealth(((Player) sender).getMaxHealth());
                            sender.sendMessage(Files.getInstance().convert("&6Hãy nghỉ ngơi cẩn thận để còn đi hỗ trợ những bệnh nhân khác nữa nhé!"));
                        } else {
                            sender.sendMessage(Files.getInstance().convert("&cBạn cần có ít nhất 200$ để tự nghỉ ngơi"));
                        }
                    } else {
                        Jobs.getInstance().getServer().broadcastMessage(Files.getInstance().convert("&cBệnh nhân &e" + ((Player) sender).getPlayer().getName() + "&c ở vị trí &6" + (((Player) sender).getPlayer().getLocation().getBlockX()) + " " + (((Player) sender).getPlayer().getLocation().getBlockY()) + " " + (((Player) sender).getPlayer().getLocation().getBlockZ())));
                        sender.sendMessage(Files.getInstance().convert("&aBác sĩ đang tới! Vui lòng đứng chờ!"));
                        ((Player) sender).setWalkSpeed(0);
                    }
                } else {
                    sender.sendMessage(Files.getInstance().convert("&cMáu của bạn hiện tại là &a " + Double.valueOf(((Player) sender).getHealth()) + "&c nên chưa đủ điều kiện để gọi Bác Sĩ "));

                }
            }

            if (sender.hasPermission("hangcam")) {
                if (label.equalsIgnoreCase("hangcam")) {

                    ItemStack items = new ItemStack(Material.valueOf(Files.getInstance().getconfig().getString("hangcam.MATERIAL")), Files.getInstance().getconfig().getInt("hangcam.AMOUNT"));
                    ItemMeta meta = items.getItemMeta();

                    meta.setDisplayName(Files.getInstance().convert(Files.getInstance().getconfig().getString("hangcam.DISPLAY_NAME")));
                    ArrayList<String> lore = new ArrayList<String>();
                    lore.add(Files.getInstance().convert(Files.getInstance().getconfig().getString("hangcam.LORE1")));
                    lore.add(Files.getInstance().convert(Files.getInstance().getconfig().getString("hangcam.LORE2")));
                    meta.setLore(lore);
                    items.setItemMeta(meta);
                    ((Player) sender).getInventory().addItem(items);
                }
            }
        }
        return true;
    }
}

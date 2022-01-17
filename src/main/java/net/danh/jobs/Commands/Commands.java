package net.danh.jobs.Commands;

import net.danh.jobs.Files.Files;
import org.bukkit.Material;
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
            sender.sendMessage("Player Only!");
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
                        if (sender.hasPermission("bacsi")) {
                            if (args[0].equalsIgnoreCase("BACSI")) {
                                Files.getInstance().getdata().set("players." + sender.getName(), "BACSI");
                                Files.getInstance().savedata();
                                sender.sendMessage(Files.getInstance().convert(Files.getInstance().getlanguage().getString("job-switched")
                                        .replaceAll("%job%", "Bác Sĩ")));

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


                        if (sender.hasPermission("bacsi")) {
                            if (args[0].equalsIgnoreCase("BACSI")) {
                                Files.getInstance().getdata().set("players." + sender.getName(), "BACSI");
                                Files.getInstance().savedata();
                                sender.sendMessage(Files.getInstance().convert(Files.getInstance().getlanguage().getString("job-switched")
                                        .replaceAll("%job%", "Bác Sĩ")));

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
                    } else {
                        sender.sendMessage(Files.getInstance().convert("&cBạn đã có nghề! Muốn đổi nghề vui lòng gõ /doinghe <tên nghề>"));
                    }
                }
            }
            if (sender.hasPermission("thuocbacsi")) {
                if (label.equalsIgnoreCase("thuocbacsi")) {
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
        }
        return true;
    }
}

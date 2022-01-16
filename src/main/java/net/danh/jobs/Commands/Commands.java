package net.danh.jobs.Commands;

import net.danh.jobs.Files.Files;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;

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
                                    .replaceAll("%job%", "Đào Mỏ")));

                            return true;
                        }
                        if (args[0].equalsIgnoreCase("TIEUPHU")) {
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
                    }
                }
            }

            if (label.equalsIgnoreCase("danhsachnghe")) {
                sender.sendMessage(Files.getInstance().convert(Files.getInstance().getlanguage().getString("available-jobs")));
            }
            if (label.equalsIgnoreCase("xemnghe")) {
                if (args.length > 1) {
                    if (Files.getInstance().getconfig().getString("players." + args[1]) != null) {
                        sender.sendMessage(Files.getInstance().convert(Files.getInstance().getconfig().getString("messages.check-job").replace("%job%", Files.getInstance().getdata().getString("players." + args[1])).replace("%player%", args[1])));
                    } else {
                        sender.sendMessage(Files.getInstance().convert(Files.getInstance().getlanguage().getString("check-error").replace("%player%", args[1])));
                    }
                }
            }
            if (label.equalsIgnoreCase("chonnghe")){

                if (args.length == 1) {
                    if (Files.getInstance().getdata().getString("players." + sender.getName()).equals("KHONGCONGHE")) {
                        if (args[0].equalsIgnoreCase("THOMO")) {
                            Files.getInstance().getdata().set("players." + sender.getName(), "THOMO");
                            Files.getInstance().savedata();
                            sender.sendMessage(Files.getInstance().convert(Files.getInstance().getlanguage().getString("job-switched")
                                    .replaceAll("%job%", "Đào Mỏ")));

                            return true;
                        }
                        if (args[0].equalsIgnoreCase("TIEUPHU")) {
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
                    } else {
                        sender.sendMessage(Files.getInstance().convert("&cBạn đã có nghề! Muốn đổi nghề vui lòng gõ /doinghe <tên nghề>"));
                    }
                }
            }
        }
        return true;
    }
}

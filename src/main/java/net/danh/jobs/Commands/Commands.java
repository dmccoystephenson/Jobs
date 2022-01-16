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
                    for (String helpplayer : (Files.getInstance().getconfig().getStringList("jobs-help")))
                        sender.sendMessage((Files.getInstance().convert(helpplayer).replace("%job%", Files.getInstance().getconfig().getString("players." + sender.getName()))));
                    return true;
                }
            }
            if (label.equalsIgnoreCase("doinghe")) {
                if (args.length == 1) {
                    if (args[0].equalsIgnoreCase("THOMO")) {
                        Files.getInstance().getdata().set("players." + sender.getName(), "THOMO");
                        Files.getInstance().savedata();
                        sender.sendMessage(Files.getInstance().convert(Files.getInstance().getlanguage().getString("job-switched")
                                .replaceAll("%job%", "Thợ Mỏ")));

                        return true;
                    }
                    if (args[0].equalsIgnoreCase("TIEUPHU")) {
                        Files.getInstance().getdata().set("players." + sender.getName(), "TIEUPHU");
                        Files.getInstance().savedata();
                        sender.sendMessage(Files.getInstance().convert(Files.getInstance().getlanguage().getString("job-switched")
                                .replaceAll("%job%", "Tiều Phu")));

                        return true;
                    }
                }
            }
            if (label.equalsIgnoreCase("cachkiemtien")) {
                if (args.length == 1) {
                    if (args[0].equalsIgnoreCase("THOMO")) {
                        for (String key : Files.getInstance().getconfig().getConfigurationSection("miner_values").getKeys(false)) {
                            double value = Files.getInstance().getconfig().getDouble("miner_values." + key);
                            sender.sendMessage(Files.getInstance().convert(Files.getInstance().getlanguage().getString("miner-format")
                                    .replace("%block%", key)
                                    .replace("%money%", String.valueOf(value))));
                        }
                        return true;
                    }
                    if (args[0].equalsIgnoreCase("TIEUPHU")) {
                        for (String key : Files.getInstance().getconfig().getConfigurationSection("treecutter_values").getKeys(false)) {
                            double value = Files.getInstance().getconfig().getDouble("treecutter_values." + key);
                            sender.sendMessage(Files.getInstance().convert(Files.getInstance().getlanguage().getString("treecutter-format")
                                    .replace("%block%", key)
                                    .replace("%money%", String.valueOf(value))));
                        }

                        return true;
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
                    if (args[0].equalsIgnoreCase("THOMO")) {
                        Files.getInstance().getdata().set("players." + sender.getName(), "THOMO");
                        Files.getInstance().savedata();
                        sender.sendMessage(Files.getInstance().convert(Files.getInstance().getlanguage().getString("job-switched")
                                .replaceAll("%job%", "Thợ Mỏ")));

                        return true;
                    }
                    if (args[0].equalsIgnoreCase("TIEUPHU")) {
                        Files.getInstance().getdata().set("players." + sender.getName(), "TIEUPHU");
                        Files.getInstance().savedata();
                        sender.sendMessage(Files.getInstance().convert(Files.getInstance().getlanguage().getString("job-switched")
                                .replaceAll("%job%", "Tiều Phu")));

                        return true;
                    }
                }
            }
        }
        return true;
    }
}

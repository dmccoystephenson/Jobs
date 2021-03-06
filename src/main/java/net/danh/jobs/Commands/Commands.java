package net.danh.jobs.Commands;

import net.danh.jobs.Jobs;
import net.danh.jobs.Manager.Files;
import net.milkbowl.vault.economy.EconomyResponse;
import org.bukkit.Bukkit;
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
            if (label.equalsIgnoreCase("thongtin")) {
                if (args.length == 1) {
                    if (Bukkit.getPlayer(args[0]) != null) {
                        sender.sendMessage(Files.getInstance().convert("&aJobs &b" + Files.getInstance().getJobs(Bukkit.getPlayer(args[0]))));
                        sender.sendMessage(Files.getInstance().convert("&aLevel &b" + Files.getInstance().getAge(Bukkit.getPlayer(args[0]))));
                        sender.sendMessage(Files.getInstance().convert("&aXP &b" + Files.getInstance().getXP(Bukkit.getPlayer(args[0]))));
                    }
                }
                if (args.length == 4) {
                    if (args[0].equalsIgnoreCase("capdo")) {
                        if (args[1].equalsIgnoreCase("set")) {
                            if (Bukkit.getPlayer(args[2]) != null) {
                                Files.getInstance().setAge(Bukkit.getPlayer(args[2]), Integer.parseInt(args[3]));
                                sender.sendMessage(Files.getInstance().convert("&aĐã đặt cấp độ nghề &e" + Files.getInstance().getJobs(Bukkit.getPlayer(args[2])) + "&a của &e" + Bukkit.getPlayer(args[2]).getName() + "&a thành &6" + Integer.parseInt(args[3])));
                            } else {
                                return true;
                            }
                        }
                        if (args[1].equalsIgnoreCase("remove")) {
                            if (Bukkit.getPlayer(args[2]) != null) {
                                Files.getInstance().removeAge(Bukkit.getPlayer(args[2]), Integer.parseInt(args[3]));
                                sender.sendMessage(Files.getInstance().convert("&aĐã lấy cấp độ nghề &e" + Files.getInstance().getJobs(Bukkit.getPlayer(args[2])) + "&a của &e" + Bukkit.getPlayer(args[2]).getName() + "&a đi &6" + Integer.parseInt(args[3])));
                            } else {
                                return true;
                            }
                        }

                        if (args[1].equalsIgnoreCase("add")) {
                            if (Bukkit.getPlayer(args[2]) != null) {
                                Files.getInstance().addAge(Bukkit.getPlayer(args[2]), Integer.parseInt(args[3]));
                                sender.sendMessage(Files.getInstance().convert("&aĐã thêm cấp độ nghề &e" + Files.getInstance().getJobs(Bukkit.getPlayer(args[2])) + "&a của &e" + Bukkit.getPlayer(args[2]).getName() + "&a lên &6" + Integer.parseInt(args[3])));
                            } else {
                                return true;
                            }
                        }
                    }
                    if (args[0].equalsIgnoreCase("kinhnghiem")) {
                        if (args[1].equalsIgnoreCase("set")) {
                            if (Bukkit.getPlayer(args[2]) != null) {
                                Files.getInstance().setXP(Bukkit.getPlayer(args[2]), Integer.parseInt(args[3]));
                                sender.sendMessage(Files.getInstance().convert("&aĐã đặt kinh nghiệm nghề &e" + Files.getInstance().getJobs(Bukkit.getPlayer(args[2])) + "&a của &e" + Bukkit.getPlayer(args[2]).getName() + "&a thành &6" + Integer.parseInt(args[3])));
                            } else {
                                return true;
                            }
                        }
                        if (args[1].equalsIgnoreCase("remove")) {
                            if (Bukkit.getPlayer(args[2]) != null) {
                                Files.getInstance().removeXP(Bukkit.getPlayer(args[2]), Integer.parseInt(args[3]));
                                sender.sendMessage(Files.getInstance().convert("&aĐã lấy kinh nghiệm nghề &e" + Files.getInstance().getJobs(Bukkit.getPlayer(args[2])) + "&a của &e" + Bukkit.getPlayer(args[2]).getName() + "&a đi &6" + Integer.parseInt(args[3])));
                            } else {
                                return true;
                            }
                        }

                        if (args[1].equalsIgnoreCase("add")) {
                            if (Bukkit.getPlayer(args[2]) != null) {
                                Files.getInstance().addXP(Bukkit.getPlayer(args[2]), Integer.parseInt(args[3]));
                                sender.sendMessage(Files.getInstance().convert("&aĐã thêm kinh nghiệm nghề &e" + Files.getInstance().getJobs(Bukkit.getPlayer(args[2])) + "&a của &e" + Bukkit.getPlayer(args[2]).getName() + "&a lên &6" + Integer.parseInt(args[3])));
                            } else {
                                return true;
                            }
                        }
                    }
                }
            }

            if (label.equalsIgnoreCase("ganglevel")) {
                if (args.length == 3) {
                    if (args[0].equalsIgnoreCase("set")) {
                        if (Bukkit.getPlayer(args[1]) != null) {
                            Files.getInstance().setLevelGang(Bukkit.getPlayer(args[1]), Integer.parseInt(args[2]));
                            sender.sendMessage(Files.getInstance().convert("&aĐã đặt cấp độ Gang của &e" + Bukkit.getPlayer(args[1]) + "&a thành &6" + Integer.parseInt(args[2])));
                        } else {
                            return true;
                        }
                    }

                    if (args[0].equalsIgnoreCase("add")) {
                        if (Bukkit.getPlayer(args[1]) != null) {
                            Files.getInstance().addLevelGang(Bukkit.getPlayer(args[1]), Integer.parseInt(args[2]));
                            sender.sendMessage(Files.getInstance().convert("&aĐã thêm cấp độ Gang của &e" + Bukkit.getPlayer(args[1]) + "&a lên &6" + Integer.parseInt(args[2])));
                        } else {
                            return true;
                        }
                    }


                    if (args[0].equalsIgnoreCase("remove")) {
                        if (Bukkit.getPlayer(args[1]) != null) {
                            Files.getInstance().removeLevelGang(Bukkit.getPlayer(args[1]), Integer.parseInt(args[2]));
                            sender.sendMessage(Files.getInstance().convert("&aĐã lấy cấp độ Gang của &e" + Bukkit.getPlayer(args[1]) + "&a xuống &6" + Integer.parseInt(args[2])));
                        } else {
                            return true;
                        }
                    }
                }

                if (args.length == 2) {
                    if (args[0].equalsIgnoreCase("check")) {
                        sender.sendMessage(Files.getInstance().convert("&a " + Bukkit.getPlayer(args[1]) + " &bđang ở Gang có cấp độ là &a" + Files.getInstance().getLevelGang(Bukkit.getPlayer(args[1]).getPlayer())));
                        if (Bukkit.getPlayer(args[1]) != null) {
                        } else {
                            return true;
                        }
                    }
                }
            }
            if (label.equalsIgnoreCase("power")) {
                if (args.length == 3) {
                    if (args[0].equalsIgnoreCase("set")) {
                        if (Bukkit.getPlayer(args[1]) != null) {
                            Files.getInstance().setPower(Bukkit.getPlayer(args[1]), Integer.parseInt(args[2]));
                            sender.sendMessage(Files.getInstance().convert("&aĐã đặt năng lượng của &e" + Bukkit.getPlayer(args[1]) + "&a thành &6" + Integer.parseInt(args[2])));
                        } else {
                            return true;
                        }
                    }

                    if (args[0].equalsIgnoreCase("add")) {
                        if (Bukkit.getPlayer(args[1]) != null) {
                            Files.getInstance().addPower(Bukkit.getPlayer(args[1]), Integer.parseInt(args[2]));
                            sender.sendMessage(Files.getInstance().convert("&aĐã thêm năng lượng của &e" + Bukkit.getPlayer(args[1]) + "&a lên &6" + Integer.parseInt(args[2])));
                        } else {
                            return true;
                        }
                    }


                    if (args[0].equalsIgnoreCase("remove")) {
                        if (Bukkit.getPlayer(args[1]) != null) {
                            Files.getInstance().removePower(Bukkit.getPlayer(args[1]), Integer.parseInt(args[2]));
                            sender.sendMessage(Files.getInstance().convert("&aĐã lấy năng lượng của &e" + Bukkit.getPlayer(args[1]) + "&a xuống &6" + Integer.parseInt(args[2])));
                        } else {
                            return true;
                        }
                    }
                }

                if (args.length == 2) {
                    if (args[0].equalsIgnoreCase("check")) {
                        sender.sendMessage(Files.getInstance().convert("&a " + Bukkit.getPlayer(args[1]) + " &bcó &a" + Files.getInstance().getPower(Bukkit.getPlayer(args[1]).getPlayer()) + "&b năng lượng"));
                        if (Bukkit.getPlayer(args[1]) != null) {
                        } else {
                            return true;
                        }
                    }
                }
            }
            return true;
        }
        if (sender instanceof Player) {
            if (label.equalsIgnoreCase("nghe")) {
                if (args.length == 0) {
                    for (String helpplayer : (Files.getInstance().getlanguage().getStringList("jobs-help")))
                        sender.sendMessage((Files.getInstance().convert(helpplayer).replace("%job%", Files.getInstance().getJobs(((Player) sender).getPlayer()))));
                    return true;
                }
            }
            if (label.equalsIgnoreCase("doinghe")) {
                if (!Files.getInstance().getJobs(((Player) sender).getPlayer()).equals("KHONGCONGHE")) {
                    if (args.length == 1) {
                        if (sender.isOp()) {
                            if (args[0].equalsIgnoreCase("THOMO")) {
                                Files.getInstance().setJobs(((Player) sender).getPlayer(), args[0].toUpperCase());
                                Files.getInstance().savedata();
                                sender.sendMessage(Files.getInstance().convert(Files.getInstance().getlanguage().getString("job-switched")
                                        .replaceAll("%job%", "Thợ Mỏ")));
                                Files.getInstance().selectGang(((Player) sender).getPlayer(), args[0]);
                                return true;
                            }
                            if (args[0].equalsIgnoreCase("THOMOC")) {
                                Files.getInstance().setJobs(((Player) sender).getPlayer(), args[0].toUpperCase());
                                Files.getInstance().savedata();
                                sender.sendMessage(Files.getInstance().convert(Files.getInstance().getlanguage().getString("job-switched")
                                        .replaceAll("%job%", "Thợ Mộc")));
                                Files.getInstance().selectGang(((Player) sender).getPlayer(), args[0]);
                                return true;
                            }
                            if (args[0].equalsIgnoreCase("CONGNHAN")) {
                                Files.getInstance().setJobs(((Player) sender).getPlayer(), args[0].toUpperCase());
                                Files.getInstance().savedata();
                                sender.sendMessage(Files.getInstance().convert(Files.getInstance().getlanguage().getString("job-switched")
                                        .replaceAll("%job%", "Công Nhân")));
                                Files.getInstance().selectGang(((Player) sender).getPlayer(), args[0]);
                                return true;
                            }

                            if (args[0].equalsIgnoreCase("NGUDAN")) {
                                Files.getInstance().setJobs(((Player) sender).getPlayer(), args[0].toUpperCase());
                                Files.getInstance().savedata();
                                sender.sendMessage(Files.getInstance().convert(Files.getInstance().getlanguage().getString("job-switched")
                                        .replaceAll("%job%", "Ngư Dân")));
                                Files.getInstance().selectGang(((Player) sender).getPlayer(), args[0]);
                                return true;
                            }


                            if (args[0].equalsIgnoreCase("NONGDAN")) {
                                Files.getInstance().setJobs(((Player) sender).getPlayer(), args[0].toUpperCase());
                                Files.getInstance().savedata();
                                sender.sendMessage(Files.getInstance().convert(Files.getInstance().getlanguage().getString("job-switched")
                                        .replaceAll("%job%", "Nông Dân")));
                                Files.getInstance().selectGang(((Player) sender).getPlayer(), args[0]);
                                return true;
                            }

                            if (args[0].equalsIgnoreCase("LAOCONG")) {
                                Files.getInstance().setJobs(((Player) sender).getPlayer(), args[0].toUpperCase());
                                Files.getInstance().savedata();
                                sender.sendMessage(Files.getInstance().convert(Files.getInstance().getlanguage().getString("job-switched")
                                        .replaceAll("%job%", "Lao Công")));
                                Files.getInstance().selectGang(((Player) sender).getPlayer(), args[0]);
                                return true;
                            }
                        }
                        if (sender.hasPermission("congchuc")) {
                            if (args[0].equalsIgnoreCase("BACSI")) {
                                Files.getInstance().setJobs(((Player) sender).getPlayer(), args[0].toUpperCase());
                                Files.getInstance().savedata();
                                sender.sendMessage(Files.getInstance().convert(Files.getInstance().getlanguage().getString("job-switched")
                                        .replaceAll("%job%", "Bác Sĩ")));
                                Files.getInstance().selectGang(((Player) sender).getPlayer(), args[0]);
                                return true;
                            }

                            if (args[0].equalsIgnoreCase("CANHSAT")) {
                                Files.getInstance().setJobs(((Player) sender).getPlayer(), args[0].toUpperCase());
                                Files.getInstance().savedata();
                                sender.sendMessage(Files.getInstance().convert(Files.getInstance().getlanguage().getString("job-switched")
                                        .replaceAll("%job%", "Cảnh Sát")));
                                Files.getInstance().selectGang(((Player) sender).getPlayer(), args[0]);
                                return true;
                            }
                        }
                        if (Files.getInstance().getAge(((Player) sender).getPlayer()) >= 30) {
                            if (args[0].equalsIgnoreCase("ANTROM")) {
                                Files.getInstance().setJobs(((Player) sender).getPlayer(), args[0].toUpperCase());
                                Files.getInstance().savedata();
                                sender.sendMessage(Files.getInstance().convert(Files.getInstance().getlanguage().getString("job-switched")
                                        .replaceAll("%job%", "Ăn Trộm")));
                                Files.getInstance().selectGang(((Player) sender).getPlayer(), args[0]);
                                return true;
                            }
                        } else {
                            sender.sendMessage(Files.getInstance().convert("&c Bạn cần đạt cấp độ 30 trở lên để chọn nghề Ăn Trộm"));
                        }
                    }
                }
            }
            if (sender.hasPermission("jobs.admin")) {
                if (label.equalsIgnoreCase("thongtin")) {
                    if (args.length == 1) {
                        if (Bukkit.getPlayer(args[0]) != null) {
                            sender.sendMessage(Files.getInstance().convert("&aJobs &b" + Files.getInstance().getJobs(Bukkit.getPlayer(args[0]))));
                            sender.sendMessage(Files.getInstance().convert("&aLevel &b" + Files.getInstance().getAge(Bukkit.getPlayer(args[0]))));
                            sender.sendMessage(Files.getInstance().convert("&aXP &b" + Files.getInstance().getXP(Bukkit.getPlayer(args[0]))));
                        }
                    }
                    if (args.length == 4) {
                        if (args[0].equalsIgnoreCase("capdo")) {
                            if (args[1].equalsIgnoreCase("set")) {
                                if (Bukkit.getPlayer(args[2]) != null) {
                                    Files.getInstance().setAge(Bukkit.getPlayer(args[2]), Integer.parseInt(args[3]));
                                    sender.sendMessage(Files.getInstance().convert("&aĐã đặt cấp độ nghề &e" + Files.getInstance().getJobs(Bukkit.getPlayer(args[2])) + "&a của &e" + Bukkit.getPlayer(args[2]).getName() + "&a thành &6" + Integer.parseInt(args[3])));
                                } else {
                                    return true;
                                }
                            }
                            if (args[1].equalsIgnoreCase("remove")) {
                                if (Bukkit.getPlayer(args[2]) != null) {
                                    Files.getInstance().removeAge(Bukkit.getPlayer(args[2]), Integer.parseInt(args[3]));
                                    sender.sendMessage(Files.getInstance().convert("&aĐã lấy cấp độ nghề &e" + Files.getInstance().getJobs(Bukkit.getPlayer(args[2])) + "&a của &e" + Bukkit.getPlayer(args[2]).getName() + "&a đi &6" + Integer.parseInt(args[3])));
                                } else {
                                    return true;
                                }
                            }

                            if (args[1].equalsIgnoreCase("add")) {
                                if (Bukkit.getPlayer(args[2]) != null) {
                                    Files.getInstance().addAge(Bukkit.getPlayer(args[2]), Integer.parseInt(args[3]));
                                    sender.sendMessage(Files.getInstance().convert("&aĐã thêm cấp độ nghề &e" + Files.getInstance().getJobs(Bukkit.getPlayer(args[2])) + "&a của &e" + Bukkit.getPlayer(args[2]).getName() + "&a lên &6" + Integer.parseInt(args[3])));
                                } else {
                                    return true;
                                }
                            }
                        }
                        if (args[0].equalsIgnoreCase("kinhnghiem")) {
                            if (args[1].equalsIgnoreCase("set")) {
                                if (Bukkit.getPlayer(args[2]) != null) {
                                    Files.getInstance().setXP(Bukkit.getPlayer(args[2]), Integer.parseInt(args[3]));
                                    sender.sendMessage(Files.getInstance().convert("&aĐã đặt kinh nghiệm nghề &e" + Files.getInstance().getJobs(Bukkit.getPlayer(args[2])) + "&a của &e" + Bukkit.getPlayer(args[2]).getName() + "&a thành &6" + Integer.parseInt(args[3])));
                                } else {
                                    return true;
                                }
                            }
                            if (args[1].equalsIgnoreCase("remove")) {
                                if (Bukkit.getPlayer(args[2]) != null) {
                                    Files.getInstance().removeXP(Bukkit.getPlayer(args[2]), Integer.parseInt(args[3]));
                                    sender.sendMessage(Files.getInstance().convert("&aĐã lấy kinh nghiệm nghề &e" + Files.getInstance().getJobs(Bukkit.getPlayer(args[2])) + "&a của &e" + Bukkit.getPlayer(args[2]).getName() + "&a đi &6" + Integer.parseInt(args[3])));
                                } else {
                                    return true;
                                }
                            }

                            if (args[1].equalsIgnoreCase("add")) {
                                if (Bukkit.getPlayer(args[2]) != null) {
                                    Files.getInstance().addXP(Bukkit.getPlayer(args[2]), Integer.parseInt(args[3]));
                                    sender.sendMessage(Files.getInstance().convert("&aĐã thêm kinh nghiệm nghề &e" + Files.getInstance().getJobs(Bukkit.getPlayer(args[2])) + "&a của &e" + Bukkit.getPlayer(args[2]).getName() + "&a lên &6" + Integer.parseInt(args[3])));
                                } else {
                                    return true;
                                }
                            }
                        }
                    }
                }
                if (label.equalsIgnoreCase("power")) {
                    if (args.length == 3) {
                        if (args[0].equalsIgnoreCase("set")) {
                            if (Bukkit.getPlayer(args[1]) != null) {
                                Files.getInstance().setPower(Bukkit.getPlayer(args[1]), Integer.parseInt(args[2]));
                                sender.sendMessage(Files.getInstance().convert("&aĐã đặt năng lượng của &e" + Bukkit.getPlayer(args[1]) + "&a thành &6" + Integer.parseInt(args[2])));
                            } else {
                                return true;
                            }
                        }

                        if (args[0].equalsIgnoreCase("add")) {
                            if (Bukkit.getPlayer(args[1]) != null) {
                                Files.getInstance().addPower(Bukkit.getPlayer(args[1]), Integer.parseInt(args[2]));
                                sender.sendMessage(Files.getInstance().convert("&aĐã thêm năng lượng của &e" + Bukkit.getPlayer(args[1]) + "&a lên &6" + Integer.parseInt(args[2])));
                            } else {
                                return true;
                            }
                        }


                        if (args[0].equalsIgnoreCase("remove")) {
                            if (Bukkit.getPlayer(args[1]) != null) {
                                Files.getInstance().removePower(Bukkit.getPlayer(args[1]), Integer.parseInt(args[2]));
                                sender.sendMessage(Files.getInstance().convert("&aĐã lấy năng lượng của &e" + Bukkit.getPlayer(args[1]) + "&a xuống &6" + Integer.parseInt(args[2])));
                            } else {
                                return true;
                            }
                        }
                    }

                    if (args.length == 2) {
                        if (args[0].equalsIgnoreCase("check")) {
                            sender.sendMessage(Files.getInstance().convert("&a " + Bukkit.getPlayer(args[1]) + " &bcó &a" + Files.getInstance().getPower(Bukkit.getPlayer(args[1]).getPlayer()) + "&b năng lượng"));
                            if (Bukkit.getPlayer(args[1]) != null) {
                            } else {
                                return true;
                            }
                        }
                    }
                }

                if (label.equalsIgnoreCase("ganglevel")) {
                    if (args.length == 3) {
                        if (args[0].equalsIgnoreCase("set")) {
                            if (Bukkit.getPlayer(args[1]) != null) {
                                Files.getInstance().setLevelGang(Bukkit.getPlayer(args[1]), Integer.parseInt(args[2]));
                                sender.sendMessage(Files.getInstance().convert("&aĐã đặt cấp độ Gang của &e" + Bukkit.getPlayer(args[1]) + "&a thành &6" + Integer.parseInt(args[2])));
                            } else {
                                return true;
                            }
                        }

                        if (args[0].equalsIgnoreCase("add")) {
                            if (Bukkit.getPlayer(args[1]) != null) {
                                Files.getInstance().addLevelGang(Bukkit.getPlayer(args[1]), Integer.parseInt(args[2]));
                                sender.sendMessage(Files.getInstance().convert("&aĐã thêm cấp độ Gang của &e" + Bukkit.getPlayer(args[1]) + "&a lên &6" + Integer.parseInt(args[2])));
                            } else {
                                return true;
                            }
                        }


                        if (args[0].equalsIgnoreCase("remove")) {
                            if (Bukkit.getPlayer(args[1]) != null) {
                                Files.getInstance().removeLevelGang(Bukkit.getPlayer(args[1]), Integer.parseInt(args[2]));
                                sender.sendMessage(Files.getInstance().convert("&aĐã lấy cấp độ Gang của &e" + Bukkit.getPlayer(args[1]) + "&a xuống &6" + Integer.parseInt(args[2])));
                            } else {
                                return true;
                            }
                        }
                    }

                    if (args.length == 2) {
                        if (args[0].equalsIgnoreCase("check")) {
                            sender.sendMessage(Files.getInstance().convert("&a " + Bukkit.getPlayer(args[1]) + " &bđang ở Gang có cấp độ là &a" + Files.getInstance().getLevelGang(Bukkit.getPlayer(args[1]).getPlayer())));
                            if (Bukkit.getPlayer(args[1]) != null) {
                            } else {
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
                    if (Files.getInstance().getJobs(((Player) sender).getPlayer()).equals("KHONGCONGHE")) {

                        if (sender.isOp()) {
                            if (args[0].equalsIgnoreCase("THOMO")) {
                                Files.getInstance().setJobs(((Player) sender).getPlayer(), args[0].toUpperCase());
                                Files.getInstance().savedata();
                                sender.sendMessage(Files.getInstance().convert(Files.getInstance().getlanguage().getString("job-switched")
                                        .replaceAll("%job%", "Thợ Mỏ")));
                                EconomyResponse r = Jobs.economy.depositPlayer((OfflinePlayer) ((Player) sender).getPlayer(), 500);
                                Files.getInstance().selectGang(((Player) sender).getPlayer(), args[0]);
                                return true;
                            }
                            if (args[0].equalsIgnoreCase("THOMOC")) {
                                Files.getInstance().setJobs(((Player) sender).getPlayer(), args[0].toUpperCase());
                                Files.getInstance().savedata();
                                sender.sendMessage(Files.getInstance().convert(Files.getInstance().getlanguage().getString("job-switched")
                                        .replaceAll("%job%", "Thợ Mộc")));
                                EconomyResponse r = Jobs.economy.depositPlayer((OfflinePlayer) ((Player) sender).getPlayer(), 500);
                                Files.getInstance().selectGang(((Player) sender).getPlayer(), args[0]);
                                return true;
                            }
                            if (args[0].equalsIgnoreCase("CONGNHAN")) {
                                Files.getInstance().setJobs(((Player) sender).getPlayer(), args[0].toUpperCase());
                                Files.getInstance().savedata();
                                sender.sendMessage(Files.getInstance().convert(Files.getInstance().getlanguage().getString("job-switched")
                                        .replaceAll("%job%", "Công Nhân")));
                                EconomyResponse r = Jobs.economy.depositPlayer((OfflinePlayer) ((Player) sender).getPlayer(), 500);
                                Files.getInstance().selectGang(((Player) sender).getPlayer(), args[0]);
                                return true;
                            }

                            if (args[0].equalsIgnoreCase("NGUDAN")) {
                                Files.getInstance().setJobs(((Player) sender).getPlayer(), args[0].toUpperCase());
                                Files.getInstance().savedata();
                                sender.sendMessage(Files.getInstance().convert(Files.getInstance().getlanguage().getString("job-switched")
                                        .replaceAll("%job%", "Ngư Dân")));
                                EconomyResponse r = Jobs.economy.depositPlayer((OfflinePlayer) sender, 500);
                                Files.getInstance().selectGang(((Player) sender).getPlayer(), args[0]);
                                return true;
                            }


                            if (args[0].equalsIgnoreCase("NONGDAN")) {
                                Files.getInstance().setJobs(((Player) sender).getPlayer(), args[0].toUpperCase());
                                Files.getInstance().savedata();
                                sender.sendMessage(Files.getInstance().convert(Files.getInstance().getlanguage().getString("job-switched")
                                        .replaceAll("%job%", "Nông Dân")));
                                EconomyResponse r = Jobs.economy.depositPlayer((OfflinePlayer) ((Player) sender).getPlayer(), 500);
                                Files.getInstance().selectGang(((Player) sender).getPlayer(), args[0]);
                                return true;
                            }

                            if (args[0].equalsIgnoreCase("LAOCONG")) {
                                Files.getInstance().setJobs(((Player) sender).getPlayer(), args[0].toUpperCase());
                                Files.getInstance().savedata();
                                sender.sendMessage(Files.getInstance().convert(Files.getInstance().getlanguage().getString("job-switched")
                                        .replaceAll("%job%", "Lao Công")));
                                EconomyResponse r = Jobs.economy.depositPlayer((OfflinePlayer) ((Player) sender).getPlayer(), 500);
                                Files.getInstance().selectGang(((Player) sender).getPlayer(), args[0]);
                                return true;
                            }
                        }
                        if (sender.hasPermission("congchuc")) {
                            if (args[0].equalsIgnoreCase("BACSI")) {
                                Files.getInstance().setJobs(((Player) sender).getPlayer(), args[0].toUpperCase());
                                Files.getInstance().savedata();
                                sender.sendMessage(Files.getInstance().convert(Files.getInstance().getlanguage().getString("job-switched")
                                        .replaceAll("%job%", "Bác Sĩ")));
                                EconomyResponse r = Jobs.economy.depositPlayer((OfflinePlayer) ((Player) sender).getPlayer(), 500);
                                Files.getInstance().selectGang(((Player) sender).getPlayer(), args[0]);
                                return true;
                            }
                            if (args[0].equalsIgnoreCase("CANHSAT")) {
                                Files.getInstance().setJobs(((Player) sender).getPlayer(), args[0].toUpperCase());
                                Files.getInstance().savedata();
                                sender.sendMessage(Files.getInstance().convert(Files.getInstance().getlanguage().getString("job-switched")
                                        .replaceAll("%job%", "Cảnh Sát")));
                                EconomyResponse r = Jobs.economy.depositPlayer((OfflinePlayer) ((Player) sender).getPlayer(), 500);
                                Files.getInstance().selectGang(((Player) sender).getPlayer(), args[0]);
                                return true;
                            }
                        }
                        if (Files.getInstance().getAge(((Player) sender).getPlayer()) >= 30) {
                            if (args[0].equalsIgnoreCase("ANTROM")) {
                                Files.getInstance().setJobs(((Player) sender).getPlayer(), args[0].toUpperCase());
                                Files.getInstance().savedata();
                                sender.sendMessage(Files.getInstance().convert(Files.getInstance().getlanguage().getString("job-switched")
                                        .replaceAll("%job%", "Ăn Trộm")));
                                EconomyResponse r = Jobs.economy.depositPlayer((OfflinePlayer) ((Player) sender).getPlayer(), 500);
                                Files.getInstance().selectGang(((Player) sender).getPlayer(), args[0]);
                                return true;
                            }
                        } else {
                            sender.sendMessage(Files.getInstance().convert("&cBạn cần cấp độ 30 trở lên để có thể trở thành &aăn trộm"));
                        }
                    } else {
                        sender.sendMessage(Files.getInstance().convert("&cBạn đã có nghề! Muốn đổi nghề vui lòng tới khu chọn nghề"));
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
                        lore.add(Files.getInstance().convert(Files.getInstance().getconfig().getString("doctor_items.LORE2")));
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
            if (Files.getInstance().getJobs(((Player) sender).getPlayer()).equals("ANTROM")) {
                if (label.equalsIgnoreCase("caydao")) {

                    ItemStack items = new ItemStack(Material.valueOf(Files.getInstance().getconfig().getString("thief_items.MATERIAL")), Files.getInstance().getconfig().getInt("thief_items.AMOUNT"));
                    ItemMeta meta = items.getItemMeta();

                    meta.setDisplayName(Files.getInstance().convert(Files.getInstance().getconfig().getString("thief_items.DISPLAY_NAME")));
                    ArrayList<String> lore = new ArrayList<String>();
                    lore.add(Files.getInstance().convert(Files.getInstance().getconfig().getString("thief_items.LORE1")));
                    lore.add(Files.getInstance().convert(Files.getInstance().getconfig().getString("thief_items.LORE2")));
                    meta.setLore(lore);
                    items.setItemMeta(meta);
                    sender.sendMessage(Files.getInstance().convert("&7+1 Vật phẩm"));
                    ((Player) sender).getInventory().addItem(items);
                }
            }
            if (label.equalsIgnoreCase("115")) {
                if (((Player) sender).getHealth() <= (((Player) sender).getMaxHealth() / 2)) {
                    if (Files.getInstance().getJobs(((Player) sender).getPlayer()).equals("BACSI")) {
                        if (Jobs.economy.getBalance(sender.getName()) >= 200) {
                            ((Player) sender).setHealth(((Player) sender).getMaxHealth());
                            sender.sendMessage(Files.getInstance().convert("&6Hãy nghỉ ngơi cẩn thận để còn đi hỗ trợ những bệnh nhân khác nữa nhé!"));
                        } else {
                            sender.sendMessage(Files.getInstance().convert("&cBạn cần có ít nhất 200$ để tự nghỉ ngơi"));
                        }
                    } else {
                        Jobs.getInstance().getServer().broadcastMessage(Files.getInstance().convert("&cBệnh nhân &e" + sender.getName() + "&c ở vị trí &6" + (((Player) sender).getPlayer().getLocation().getBlockX()) + " " + (((Player) sender).getPlayer().getLocation().getBlockY()) + " " + (((Player) sender).getPlayer().getLocation().getBlockZ())));
                        sender.sendMessage(Files.getInstance().convert("&aBác sĩ đang tới! Vui lòng đứng chờ!"));
                        ((Player) sender).setWalkSpeed(0);
                    }
                } else {
                    sender.sendMessage(Files.getInstance().convert("&cMáu của bạn hiện tại là &a" + Double.valueOf(((Player) sender).getHealth()) + "&c nên chưa đủ điều kiện để gọi Bác Sĩ "));

                }
            }
        }
        return true;
    }
}

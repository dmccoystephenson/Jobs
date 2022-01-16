package net.danh.jobs.Manager;

import net.danh.jobs.Files.Files;
import net.danh.jobs.Jobs;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import net.milkbowl.vault.economy.EconomyResponse;
import org.bukkit.OfflinePlayer;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;

public class Manager {

    private static Manager instance;

    public static Manager getInstance() {

        if (instance == null) {
            instance = new Manager();
        }
        return instance;
    }

    public void payMoney(Player p, Block b, double amount) {
        EconomyResponse r = Jobs.economy.depositPlayer((OfflinePlayer)p, amount);
        if (r.transactionSuccess()) {
            String message = Files.getInstance().getconfig().getString("messages.block-mined").replace("%block%", b.getType().toString()).replace("%money%", Double.toString(amount));
            p.spigot().sendMessage(ChatMessageType.ACTION_BAR, TextComponent.fromLegacyText(Files.getInstance().convert(message)));
        } else {
            p.sendMessage(String.format("Đã xảy ra lỗi! Hãy báo admin!!", new Object[] { r.errorMessage }));
        }
    }
}

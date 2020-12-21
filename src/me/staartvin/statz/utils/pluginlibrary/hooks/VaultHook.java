package me.staartvin.utils.pluginlibrary.hooks;

import me.staartvin.utils.pluginlibrary.Library;
import net.milkbowl.vault.chat.Chat;
import net.milkbowl.vault.economy.Economy;
import net.milkbowl.vault.permission.Permission;
import org.bukkit.plugin.RegisteredServiceProvider;

/**
 * Vault library, <a href="https://www.spigotmc.org/resources/vault.41918/">link</a>.
 * <p>
 *
 * @author Staartvin
 */
public class VaultHook extends LibraryHook {

    private static Economy econ = null;
    private static Permission perms = null;
    private static Chat chat = null;

    /**
     * Get the economy variable of Vault. (can be null, so check {@link #isAvailable()}!)
     *
     * @return economy variable
     */
    public static Economy getEconomy() {
        return econ;
    }

    /**
     * Get the permission variable of Vault. (can be null, so check {@link #isAvailable()}!)
     *
     * @return permission variable
     */
    public static Permission getPermissions() {
        return perms;
    }

    /**
     * Get the chat variable of Vault. (can be null, so check {@link #isAvailable()}!)
     *
     * @return chat variable
     */
    public static Chat getChat() {
        return chat;
    }

    @Override
    public boolean isHooked() {
        return perms != null;
    }

    /*
     * (non-Javadoc)
     *
     * @see me.staartvin.utils.pluginlibrary.hooks.LibraryHook#hook()
     */
    @Override
    public boolean hook() {

        if (!isPluginAvailable(Library.VAULT)) {
            return false;
        }

        boolean setupEco = setupEconomy(), setupChat = setupChat(), setupPerm = setupPermissions();

        return setupEco != false && setupChat != false && setupPerm != false;
    }

    private boolean setupEconomy() {
        if (this.getServer().getPluginManager().getPlugin("Vault") == null) {
            return false;
        }
        RegisteredServiceProvider<Economy> rsp = this.getServer().getServicesManager().getRegistration
                (Economy.class);

        if (rsp == null) {
            return false;
        }

        econ = rsp.getProvider();
        return econ != null;
    }

    private boolean setupChat() {
        RegisteredServiceProvider<Chat> rsp = this.getServer().getServicesManager().getRegistration(Chat
                .class);

        if (rsp == null) {
            return false;
        }

        chat = rsp.getProvider();
        return chat != null;
    }

    private boolean setupPermissions() {
        RegisteredServiceProvider<Permission> rsp = this.getServer().getServicesManager().getRegistration
                (Permission.class);

        if (rsp == null) {
            return false;
        }

        perms = rsp.getProvider();
        return perms != null;
    }


}

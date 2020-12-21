package me.staartvin.utils.pluginlibrary.hooks.afkmanager;

import java.util.UUID;

public interface AFKManager {

    boolean isAFK(UUID uuid);

    boolean hasAFKData();

}

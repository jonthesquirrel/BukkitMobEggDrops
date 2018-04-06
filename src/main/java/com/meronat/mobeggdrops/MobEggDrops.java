/*
 * This file is part of MobEggDrops, licensed under the MIT License.
 *
 * Copyright (c) 2017 Meronat <http://meronat.com>
 * Copyright (c) Contributors
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */

package com.meronat.mobeggdrops;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

public final class MobEggDrops extends JavaPlugin {

    @Override
    public void onEnable() {
        this.loadConfig();

        this.getServer().getPluginManager().registerEvents(new EntityDeathListener(this), this);

        this.getCommand("med").setExecutor(new ReloadCommand(this));
        this.getCommand("medchances").setExecutor(new ChancesCommand(this));
    }

    private void loadConfig() {
        FileConfiguration config = this.getConfig();

        config.addDefault("exchange-item", "DRAGON_EGG");
        config.addDefault("exchange-item-amount", 1);
        config.addDefault("check-permission", false);
        config.addDefault("BAT", 100.0);
        config.addDefault("BLAZE", 100.0);
        config.addDefault("CAVE_SPIDER", 100.0);
        config.addDefault("CHICKEN", 100.0);
        config.addDefault("COW", 100.0);
        config.addDefault("CREEPER", 100.0);
        config.addDefault("DONKEY", 100.0);
        config.addDefault("ELDER_GUARDIAN", 100.0);
        config.addDefault("ENDERMAN", 100.0);
        config.addDefault("ENDERMITE", 100.0);
        config.addDefault("EVOKER", 100.0);
        config.addDefault("GHAST", 100.0);
        config.addDefault("GUARDIAN", 100.0);
        config.addDefault("HORSE", 100.0);
        config.addDefault("HUSK", 100.0);
        config.addDefault("LLAMA", 100.0);
        config.addDefault("MAGMA_CUBE", 100.0);
        config.addDefault("MUSHROOM_COW", 100.0);
        config.addDefault("MULE", 100.0);
        config.addDefault("OCELOT", 100.0);
        config.addDefault("PIG", 100.0);
        config.addDefault("POLAR_BEAR", 100.0);
        config.addDefault("RABBIT", 100.0);
        config.addDefault("SHEEP", 100.0);
        config.addDefault("SHULKER", 100.0);
        config.addDefault("SILVERFISH", 100.0);
        config.addDefault("SKELETON", 100.0);
        config.addDefault("SKELETON_HORSE", 100.0);
        config.addDefault("SLIME", 100.0);
        config.addDefault("SPIDER", 100.0);
        config.addDefault("SQUID", 100.0);
        config.addDefault("STRAY", 100.0);
        config.addDefault("VEX", 100.0);
        config.addDefault("VILLAGER", 100.0);
        config.addDefault("VINDICATOR", 100.0);
        config.addDefault("WITCH", 100.0);
        config.addDefault("WITHER_SKELETON", 100.0);
        config.addDefault("WOLF", 100.0);
        config.addDefault("ZOMBIE", 100.0);
        config.addDefault("ZOMBIE_HORSE", 100.0);
        config.addDefault("PIG_ZOMBIE", 100.0);
        config.addDefault("ZOMBIE_VILLAGER", 100.0);

        config.options().copyDefaults(true);
        this.saveConfig();
    }

    boolean properEntity(String entity) {
        return this.getConfig().contains(entity);
    }

    double getChance(String entity) {
        return this.getConfig().getDouble(entity);
    }

}

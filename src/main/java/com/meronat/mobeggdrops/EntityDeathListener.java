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

import java.util.concurrent.ThreadLocalRandom;

import org.bukkit.Material;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SpawnEggMeta;

public final class EntityDeathListener implements Listener {

    private MobEggDrops plugin;

    EntityDeathListener(MobEggDrops plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onEntityDeath(EntityDeathEvent event) {
        if (plugin.getConfig().getBoolean("check-permission")) {
            if (event.getEntity().getKiller() == null || !event.getEntity().getKiller().hasPermission("mobeggdrops.drops")) {
                return;
            }
        }

        if (!(event.getEntity().getKiller() instanceof Player)) {
            return;
        }

        // make sure player has exchange-item-amount of exchange-item in off-hand
        ItemStack currentOffHand = event.getEntity().getKiller().getInventory().getItemInOffHand();
        Material exchangeItem = Material.valueOf(plugin.getConfig().getString("exchange-item"));
        int exchangeItemAmount = plugin.getConfig().getInt("exchange-item-amount");
        if (currentOffHand.getType() != exchangeItem || currentOffHand.getAmount() < exchangeItemAmount) {
            return;
        }

        EntityType type = event.getEntityType();

        if (!this.plugin.properEntity(type.toString())) {
            return;
        }

        double chance = this.plugin.getChance(type.toString());

        if (chance <= 0) {
            return;
        }

        if (chance < ThreadLocalRandom.current().nextDouble(0.0, 100.1)) {
            return;
        }

        ItemStack egg = new ItemStack(Material.MONSTER_EGG);

        ItemMeta meta = egg.getItemMeta();

        if (!(meta instanceof SpawnEggMeta)) {
            return;
        }

        ((SpawnEggMeta) meta).setSpawnedType(type);

        egg.setItemMeta(meta);

        egg.setAmount(1);

        // remove exchange-item-amount of exchange-item from player's off-hand
        ItemStack newOffHand = currentOffHand.clone();
        newOffHand.setAmount(currentOffHand.getAmount() - exchangeItemAmount);
        event.getEntity().getKiller().getInventory().setItemInOffHand(newOffHand);

        event.getDrops().add(egg);
    }

}

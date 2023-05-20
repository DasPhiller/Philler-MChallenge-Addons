package de.dasphiller.challengeAddon.mods

import de.dasphiller.challengeAddon.AddonManager
import de.dasphiller.challengeAddon.utils.AddonMod
import de.miraculixx.challenge.api.modules.challenges.Challenge
import net.axay.kspigot.event.listen
import net.axay.kspigot.event.register
import net.axay.kspigot.event.unregister
import net.axay.kspigot.extensions.onlinePlayers
import org.bukkit.Sound
import org.bukkit.entity.Player
import org.bukkit.event.entity.EntityDamageEvent

class DamageClearInventory : Challenge {

    private var multiplayer: Boolean = true

    override fun start(): Boolean {
        val settings = AddonManager.getSettings(AddonMod.DAMAGE_CLEAR_INVENTORY).settings
        multiplayer = settings["multiplayer"]?.toBool()?.getValue() ?: true
        return true
    }

    override fun register() {
        damage.register()
    }

    override fun unregister() {
        damage.unregister()
    }

    private val damage = listen<EntityDamageEvent>(register = false) {
        if (it.entity !is Player) return@listen
        val player: Player = it.entity as Player
        if (multiplayer) {
            onlinePlayers.forEach { players ->
                clearInventory(players)
            }
        } else {
            clearInventory(player)
        }
    }

}

fun clearInventory(player: Player) {
    player.inventory.clear()
    player.playSound(player.location, Sound.BLOCK_ANVIL_LAND, 1F, 1F)
}
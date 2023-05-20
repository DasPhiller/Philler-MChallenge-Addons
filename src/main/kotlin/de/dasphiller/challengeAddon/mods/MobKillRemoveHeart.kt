package de.dasphiller.challengeAddon.mods

import de.dasphiller.challengeAddon.AddonManager
import de.dasphiller.challengeAddon.utils.AddonMod
import de.miraculixx.challenge.api.modules.challenges.Challenge
import net.axay.kspigot.event.listen
import net.axay.kspigot.event.register
import net.axay.kspigot.event.unregister
import net.axay.kspigot.extensions.bukkit.kill
import org.bukkit.attribute.Attribute
import org.bukkit.entity.Player
import org.bukkit.event.entity.EntityDeathEvent

class MobKillRemoveHeart : Challenge {

    private var hearts = 2

    override fun start(): Boolean {
        val settings = AddonManager.getSettings(AddonMod.MOB_KILL_REMOVE_HEART).settings
        hearts = settings["hearts"]?.toInt()?.getValue() ?: 2

        return true
    }

    override fun register() {
        mobKill.register()
    }

    override fun unregister() {
        mobKill.unregister()
    }


    private val mobKill = listen<EntityDeathEvent>(register = false) {
        if (it.entity is Player) return@listen
        val player = it.entity.killer ?: return@listen
        if (player.getAttribute(Attribute.GENERIC_MAX_HEALTH)?.baseValue == 2.0) {
            player.kill()
        } else {
            player.getAttribute(Attribute.GENERIC_MAX_HEALTH)?.baseValue =
                player.getAttribute(Attribute.GENERIC_MAX_HEALTH)?.baseValue!! - hearts.toDouble()
        }
    }
}
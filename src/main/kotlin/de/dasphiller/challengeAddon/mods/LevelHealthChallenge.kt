package de.dasphiller.challengeAddon.mods

import de.dasphiller.challengeAddon.AddonManager
import de.dasphiller.challengeAddon.utils.AddonMod
import de.miraculixx.challenge.api.modules.challenges.Challenge
import de.miraculixx.challenge.api.modules.challenges.Challenges
import net.axay.kspigot.event.listen
import net.axay.kspigot.event.register
import net.axay.kspigot.event.unregister
import net.axay.kspigot.extensions.onlinePlayers
import org.bukkit.attribute.Attribute
import org.bukkit.event.player.PlayerLevelChangeEvent

class LevelHealthChallenge : Challenge {

    private var hearts = 1

    override fun start(): Boolean {
        val settings = AddonManager.getSettings(AddonMod.LevelHealth).settings
        hearts = settings["hearts"]?.toInt()?.getValue() ?: 1
        return true
    }

    override fun register() {
        onlinePlayers.forEach {
            it.getAttribute(Attribute.GENERIC_MAX_HEALTH)?.baseValue = 1.0
            it.level = 1
        }
        levelUp.register()
    }

    override fun unregister() {
        levelUp.unregister()
    }

    private val levelUp = listen<PlayerLevelChangeEvent>(register = false) {
        if (it.newLevel > it.oldLevel && it.newLevel != 1) {
            it.player.getAttribute(Attribute.GENERIC_MAX_HEALTH)?.baseValue = hearts.toDouble() + it.player.getAttribute(Attribute.GENERIC_MAX_HEALTH)?.baseValue!!
        }
    }
}
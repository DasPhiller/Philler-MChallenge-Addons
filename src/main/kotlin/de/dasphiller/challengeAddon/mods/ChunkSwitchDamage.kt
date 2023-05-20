package de.dasphiller.challengeAddon.mods

import de.dasphiller.challengeAddon.AddonManager
import de.dasphiller.challengeAddon.utils.AddonMod
import de.miraculixx.challenge.api.modules.challenges.Challenge
import net.axay.kspigot.event.listen
import net.axay.kspigot.event.register
import net.axay.kspigot.event.unregister
import org.bukkit.event.player.PlayerMoveEvent

class ChunkSwitchDamage : Challenge {

    private var damage = 2

    override fun start(): Boolean {
        val settings = AddonManager.getSettings(AddonMod.CHUNK_SWITCH_DAMAGE).settings
        damage = settings["hearts"]?.toInt()?.getValue() ?: 2

        return true
    }

    override fun register() {
        moveEvent.register()
    }

    override fun unregister() {
        moveEvent.unregister()
    }

    private val moveEvent = listen<PlayerMoveEvent>(register = false) {
        if (it.from.chunk != it.to.chunk) {
            it.player.damage(damage.toDouble())
        }
    }

}
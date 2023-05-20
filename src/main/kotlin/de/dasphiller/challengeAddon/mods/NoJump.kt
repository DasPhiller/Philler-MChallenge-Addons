package de.dasphiller.challengeAddon.mods

import com.destroystokyo.paper.event.player.PlayerJumpEvent
import de.miraculixx.challenge.api.MChallengeAPI
import de.miraculixx.challenge.api.modules.challenges.Challenge
import net.axay.kspigot.event.listen
import net.axay.kspigot.event.register
import net.axay.kspigot.event.unregister
import net.axay.kspigot.extensions.bukkit.kill

class NoJump : Challenge {
    override fun register() {
        jump.register()
    }

    override fun unregister() {
        jump.unregister()
    }

    private val jump = listen<PlayerJumpEvent>(register = false) {
        MChallengeAPI.instance?.stopChallenges()
        it.player.kill()
    }
}
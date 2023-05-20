package de.dasphiller.challengeAddon.mods

import de.miraculixx.challenge.api.modules.challenges.Challenge
import net.axay.kspigot.event.listen
import net.axay.kspigot.event.register
import net.axay.kspigot.event.unregister
import org.bukkit.entity.Player
import org.bukkit.event.entity.EntityDamageByEntityEvent

class ReverseDamage : Challenge {

    override fun register() {
        damage.register()
    }

    override fun unregister() {
        damage.unregister()
    }

    private val damage = listen<EntityDamageByEntityEvent>(register = false) {
        if (it.damager !is Player) return@listen
        val player = it.damager as Player
        player.damage(it.damage)
    }
}
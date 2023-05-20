package de.dasphiller.challengeAddon.mods

import de.dasphiller.challengeAddon.utils.AddonMod
import de.miraculixx.challenge.api.modules.challenges.Challenge
import net.axay.kspigot.event.listen
import net.axay.kspigot.event.register
import net.axay.kspigot.event.unregister
import org.bukkit.entity.EntityType
import org.bukkit.entity.Player
import org.bukkit.event.entity.EntityDamageEvent

class DamageMobSpawn : Challenge {
    override fun register() {
        damage.register()
    }

    override fun unregister() {
        damage.unregister()
    }

    private val damage = listen<EntityDamageEvent>(register = false) {
        if (it.entity !is Player) return@listen
        val player = it.entity as Player
        val entity: ArrayList<EntityType> = ArrayList()
        entity.clear()
        val filterEntity = EntityType.values().filter { entityType ->
            entityType.isAlive
                    &&entityType.isSpawnable
                    &&entityType.name.equals("PLAYER", false)
                    &&entityType.name.equals("ENDER_DRAGON", false)
        }.toMutableList()
        entity.addAll(filterEntity)
        spawnEntity(player.location, entity.random())
    }
}
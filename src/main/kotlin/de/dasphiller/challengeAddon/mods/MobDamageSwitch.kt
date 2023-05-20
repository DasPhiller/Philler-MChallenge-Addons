package de.dasphiller.challengeAddon.mods

import de.miraculixx.challenge.api.modules.challenges.Challenge
import net.axay.kspigot.event.listen
import net.axay.kspigot.event.register
import net.axay.kspigot.event.unregister
import org.bukkit.Location
import org.bukkit.entity.EnderDragon
import org.bukkit.entity.EntityType
import org.bukkit.entity.LivingEntity
import org.bukkit.entity.Player
import org.bukkit.event.entity.EntityDamageByEntityEvent

class MobDamageSwitch : Challenge {

    override fun register() {
        damage.register()
    }

    override fun unregister() {
        damage.unregister()
    }

    private val invalidEntities: ArrayList<EntityType> = arrayListOf(
        EntityType.ENDER_DRAGON,
        EntityType.PLAYER,
        EntityType.ENDER_CRYSTAL,
        EntityType.BLAZE,
    )


    private val damage = listen<EntityDamageByEntityEvent>(register = false) {
        if (it.damager is Player && !invalidEntities.contains(it.entity.type)) {
            val entity: ArrayList<EntityType> = ArrayList()
            entity.clear()
            val filterEntity = EntityType.values().filter { entityType ->
                entityType.isAlive
                        &&entityType.isSpawnable
                        &&entityType.name.equals("PLAYER", false)
                        &&entityType.name.equals("ENDER_DRAGON", false)
            }.toMutableList()
            entity.addAll(filterEntity)
            spawnEntity(it.entity.location, entity.random())
            it.entity.remove()
        }
    }
}


fun spawnEntity(location: Location, entity: EntityType) {
    location.world.spawnEntity(location, entity)
}
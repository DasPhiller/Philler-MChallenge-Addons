package de.dasphiller.challengeAddon.mods

import de.dasphiller.challengeAddon.AddonManager
import de.dasphiller.challengeAddon.utils.AddonMod
import de.miraculixx.challenge.api.modules.challenges.Challenge
import net.axay.kspigot.event.listen
import net.axay.kspigot.event.register
import net.axay.kspigot.event.unregister
import org.bukkit.entity.Player
import org.bukkit.event.entity.EntityDamageEvent
import org.bukkit.potion.PotionEffect
import org.bukkit.potion.PotionEffectType
import kotlin.math.abs
import kotlin.random.Random

//DamageRandomEffect
class DamageRandomEffect : Challenge {

    private var effectLevel: Boolean = true

    override fun start(): Boolean {
        val settings = AddonManager.getSettings(AddonMod.DAMAGE_RANDOM_EFFECT).settings
        effectLevel = settings["effectLevel"]?.toBool()?.getValue() ?: true
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
        if (player.isBlocking) return@listen
        val validEffects = ArrayList<PotionEffectType>()
        val effect = PotionEffectType.values().filter { potionEffectType ->
            potionEffectType != PotionEffectType.WITHER
            potionEffectType != PotionEffectType.INCREASE_DAMAGE
            potionEffectType != PotionEffectType.POISON
            potionEffectType != PotionEffectType.LEVITATION
            potionEffectType != PotionEffectType.BAD_OMEN
            potionEffectType != PotionEffectType.SLOW_DIGGING
        }.toMutableList()
        validEffects.addAll(effect)
        val potionEffect = validEffects[abs(Random.nextInt(validEffects.size))]
        if (!player.hasPotionEffect(potionEffect)) {
            player.addPotionEffect(PotionEffect(potionEffect, PotionEffect.INFINITE_DURATION, 0, true))
        } else if (effectLevel) {
            val oldEffect = player.getPotionEffect(potionEffect) ?: return@listen
            player.removePotionEffect(oldEffect.type)
            player.addPotionEffect(PotionEffect(oldEffect.type, PotionEffect.INFINITE_DURATION,  oldEffect.amplifier + 1, true) )
        }
    }

}
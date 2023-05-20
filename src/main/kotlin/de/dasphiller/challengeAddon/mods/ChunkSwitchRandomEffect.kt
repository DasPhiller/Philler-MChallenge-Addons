package de.dasphiller.challengeAddon.mods

import de.miraculixx.challenge.api.modules.challenges.Challenge
import net.axay.kspigot.event.listen
import net.axay.kspigot.event.register
import net.axay.kspigot.event.unregister
import org.bukkit.entity.Player
import org.bukkit.event.player.PlayerMoveEvent
import org.bukkit.potion.PotionEffect
import org.bukkit.potion.PotionEffectType
import kotlin.math.abs
import kotlin.random.Random

//This challenge was made because 2 years ago someone I know tried to make this challenge, and he didn't know how to. So I'm doing it
class ChunkSwitchRandomEffect : Challenge {

    private var effectLevel: Boolean = true

    override fun start(): Boolean {


        return true
    }

    override fun register() {
        chunkSwitch.register()
    }

    override fun unregister() {
        chunkSwitch.unregister()
    }

    private val chunkSwitch = listen<PlayerMoveEvent>(register = false) {
        if (it.from.chunk != it.to.chunk) {
            val player: Player = it.player
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
                player.addPotionEffect(
                    PotionEffect(
                        oldEffect.type,
                        PotionEffect.INFINITE_DURATION,
                        oldEffect.amplifier + 1,
                        true
                    )
                )
            }
        }
    }
}
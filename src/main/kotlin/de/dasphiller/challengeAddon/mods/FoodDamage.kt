package de.dasphiller.challengeAddon.mods

import de.dasphiller.challengeAddon.AddonManager
import de.dasphiller.challengeAddon.utils.AddonMod
import de.miraculixx.challenge.api.modules.challenges.Challenge
import net.axay.kspigot.event.listen
import net.axay.kspigot.event.register
import net.axay.kspigot.event.unregister
import org.bukkit.event.player.PlayerItemConsumeEvent

class FoodDamage : Challenge {

    private var damage = 2

    override fun start(): Boolean {
        val settings = AddonManager.getSettings(AddonMod.FOOD_DAMAGE).settings
        damage = settings["hearts"]?.toInt()?.getValue() ?: 2

        return true
    }

    override fun register() {
        food.register()
    }

    override fun unregister() {
        food.unregister()
    }

    private val food = listen<PlayerItemConsumeEvent>(register = false) {
        it.player.damage(damage.toDouble())
    }
}
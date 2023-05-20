package de.dasphiller.challengeAddon.utils

import de.dasphiller.challengeAddon.AddonManager
import de.miraculixx.challenge.api.modules.challenges.ChallengeTags
import de.miraculixx.challenge.api.modules.challenges.CustomChallengeData
import de.miraculixx.challenge.api.settings.ChallengeData
import de.miraculixx.challenge.api.utils.Icon
import de.miraculixx.challenge.api.utils.IconNaming
import de.dasphiller.challengeAddon.MAddon
import de.dasphiller.challengeAddon.mods.*
import de.miraculixx.challenge.api.settings.ChallengeBoolSetting
import de.miraculixx.challenge.api.settings.ChallengeIntSetting
import org.bukkit.entity.Mob
import java.util.*

/**
 * All of our addon mods. Each mod is unique by his [uuid] but not bound to it on each startup. It is only important that the [uuid] is the same at all time of one session.
 * @param uuid The unique ID. Don't choose a simple hard coded [uuid], it could conflict with other addons
 */
enum class AddonMod(val uuid: UUID) {
    DAMAGE_RANDOM_EFFECT(UUID.randomUUID()),
    DAMAGE_CLEAR_INVENTORY(UUID.randomUUID()),
    LevelHealth(UUID.randomUUID()),
    NO_JUMP(UUID.randomUUID()),
    CHUNK_SWITCH_DAMAGE(UUID.randomUUID()),
    FOOD_DAMAGE(UUID.randomUUID()),
    REVERSE_DAMAGE(UUID.randomUUID()),
    MOB_DAMAGE_SWITCH(UUID.randomUUID()),
    MOB_KILL_REMOVE_HEART(UUID.randomUUID()),
    DAMAGE_MOB_SPAWN(UUID.randomUUID()),
    CHUNK_SWITCH_RANDOM_EFFECT(UUID.randomUUID())
    ;

    /**
     * Holds all mod data. Should only be called once at startup to ship all data to the MUtils API
     * @see AddonManager.loadMods
     */
    fun getModData(): CustomChallengeData {
        return when (this) {
            DAMAGE_RANDOM_EFFECT -> CustomChallengeData(
                uuid,
                DamageRandomEffect(),
                AddonManager.getSettings(this),
                Icon(
                    "IRON_PICKAXE",
                    naming = IconNaming(
                        cmp("Damage Random Effect"),
                        listOf(cmp("If you take damage you get a random effect"))
                    )
                ),
                setOf(ChallengeTags.RANDOMIZER, ChallengeTags.ADDON, ChallengeTags.FREE, ChallengeTags.MEDIUM),
                MAddon.addonName
            )

            DAMAGE_CLEAR_INVENTORY -> CustomChallengeData(
                uuid,
                DamageClearInventory(),
                AddonManager.getSettings(this),
                Icon(
                    "SHIELD",
                    naming = IconNaming(
                        cmp("Damage Clear Inventory"),
                        listOf(
                            cmp("If you take damage your/the servers inventory will be cleared")
                        )
                    )
                ),
                setOf(ChallengeTags.ADDON, ChallengeTags.FREE, ChallengeTags.HARD),
                MAddon.addonName
            )

            LevelHealth -> CustomChallengeData(
                uuid,
                LevelHealthChallenge(),
                AddonManager.getSettings(this),
                Icon(
                    "EXPERIENCE_BOTTLE",
                    naming = IconNaming(
                        cmp("LevelHealth"),
                        listOf(
                            cmp("Each level up gives you more hearts")
                        )
                    )
                ),
                setOf(ChallengeTags.MEDIUM, ChallengeTags.FREE, ChallengeTags.ADDON),
                MAddon.addonName
            )

            NO_JUMP -> CustomChallengeData(
                uuid,
                NoJump(),
                AddonManager.getSettings(this),
                Icon(
                    "DIAMOND_BOOTS",
                    naming = IconNaming(
                        cmp("No Jump"),
                        listOf(cmp("You are not allowed to jump"))
                    )
                ),
                setOf(ChallengeTags.ADDON, ChallengeTags.FREE, ChallengeTags.MEDIUM),
                MAddon.addonName
            )

            CHUNK_SWITCH_DAMAGE -> CustomChallengeData(
                uuid,
                ChunkSwitchDamage(),
                AddonManager.getSettings(this),
                Icon(
                    "GRASS_BLOCK",
                    naming = IconNaming(
                        cmp("Chunk Switch Damage"),
                        listOf(cmp("When you switch a chunk you get damage"))
                    )
                ),
                setOf(ChallengeTags.ADDON, ChallengeTags.FREE, ChallengeTags.HARD),
                MAddon.addonName
            )

            FOOD_DAMAGE -> CustomChallengeData(
                uuid,
                FoodDamage(),
                AddonManager.getSettings(this),
                Icon(
                    "COOKED_BEEF",
                    naming = IconNaming(
                        cmp("Food Damage"),
                        listOf(cmp("If you eat something you get damage"))
                    )
                ),
                setOf(ChallengeTags.ADDON, ChallengeTags.FREE, ChallengeTags.MEDIUM),
                MAddon.addonName
            )

            REVERSE_DAMAGE -> CustomChallengeData(
                uuid,
                ReverseDamage(),
                AddonManager.getSettings(this),
                Icon(
                    "DIAMOND_SWORD",
                    naming = IconNaming(
                        cmp("Reverse Damage"),
                        listOf(cmp("If you attack an entity you get the damage as well"))
                    )
                ),
                setOf(ChallengeTags.ADDON, ChallengeTags.FREE, ChallengeTags.MEDIUM),
                MAddon.addonName
            )

            MOB_DAMAGE_SWITCH -> CustomChallengeData(
                uuid,
                MobDamageSwitch(),
                AddonManager.getSettings(this),
                Icon(
                    "IRON_SWORD",
                    naming = IconNaming(
                        cmp("Mob Damage Switch"),
                        listOf(cmp("If you attack an entity it will become a different a entity"))
                    )
                ),
                setOf(ChallengeTags.ADDON, ChallengeTags.FREE, ChallengeTags.RANDOMIZER, ChallengeTags.HARD),
                MAddon.addonName
            )

            MOB_KILL_REMOVE_HEART -> CustomChallengeData(
                uuid,
                MobKillRemoveHeart(),
                AddonManager.getSettings(this),
                Icon(
                    "WOODEN_SWORD",
                    naming = IconNaming(
                        cmp("Mob Kill remove Heart"),
                        listOf(cmp("If you kill a mob you lose a heart"))
                    )
                ),
                setOf(ChallengeTags.ADDON, ChallengeTags.FREE, ChallengeTags.HARD),
                MAddon.addonName
            )

            DAMAGE_MOB_SPAWN -> CustomChallengeData(
                uuid,
                DamageMobSpawn(),
                AddonManager.getSettings(this),
                Icon(
                    "LEATHER_BOOTS",
                    naming = IconNaming(
                        cmp("Damage Mob Spawn"),
                        listOf(cmp("If you take damage a random mob will spawn"))
                    )
                ),
                setOf(ChallengeTags.ADDON, ChallengeTags.FREE, ChallengeTags.FUN, ChallengeTags.RANDOMIZER),
                MAddon.addonName
            )

            CHUNK_SWITCH_RANDOM_EFFECT -> CustomChallengeData(
                uuid,
                ChunkSwitchRandomEffect(),
                AddonManager.getSettings(this),
                Icon(
                    "LEATHER_BOOTS",
                    naming = IconNaming(
                        cmp("Chunk Switch Random Effect"),
                        listOf(cmp("If you switch the chunk you get a random effect"))
                    )
                ),
                setOf(ChallengeTags.ADDON, ChallengeTags.FREE, ChallengeTags.HARD, ChallengeTags.RANDOMIZER),
                MAddon.addonName
            )
        }
    }

    /**
     * Holds all settings information. Should only be called on initial startup if no saved settings are present.
     *
     * @see AddonManager.getSettings
     */
    fun getDefaultSetting(): ChallengeData {
        return when (this) {
            DAMAGE_RANDOM_EFFECT -> ChallengeData(
                mapOf(
                    "effectLevel" to ChallengeBoolSetting("COMMAND_BLOCK", true)
                ),
                mapOf(
                    "effectLevel" to IconNaming(
                        cmp("Add Levels"),
                        listOf(cmp("If you take damage and get the same effect should the level of the effect add up"))
                    )
                )
            )

            DAMAGE_CLEAR_INVENTORY -> ChallengeData(
                mapOf(
                    "multiplayer" to ChallengeBoolSetting("PLAYER_HEAD", true)
                ),
                mapOf(
                    "multiplayer" to IconNaming(
                        cmp("Mutliplayer"),
                        listOf(cmp("Select if only you or the entire server will lose their inventory"))
                    )
                )
            )

            LevelHealth -> ChallengeData(
                mapOf(
                    "hearts" to ChallengeIntSetting("RED_DYE", 1, "hp", min = 1, max = 20)
                ),
                mapOf(
                    "hearts" to IconNaming(
                        cmp("Hearts"),
                        listOf(cmp("Select how many hearts you get for each level"))
                    )
                )
            )

            NO_JUMP -> ChallengeData(

            )

            CHUNK_SWITCH_DAMAGE -> ChallengeData(
                mapOf(
                    "hearts" to ChallengeIntSetting("RED_DYE", 2, "hp", min = 1, max = 20)
                ),
                mapOf(
                    "hearts" to IconNaming(
                        cmp("Hearts"),
                        listOf(cmp("Select how many hearts you lose for each chunk switch"))
                    )
                )
            )

            FOOD_DAMAGE -> ChallengeData(
                mapOf(
                    "hearts" to ChallengeIntSetting("RED_DYE", 2, "hp", min = 1, max = 20)
                ),
                mapOf(
                    "hearts" to IconNaming(
                        cmp("Hearts"),
                        listOf(cmp("Select you many hearts you lose if you eat something"))
                    )
                )
            )

            REVERSE_DAMAGE -> ChallengeData(

            )

            MOB_DAMAGE_SWITCH -> ChallengeData(

            )

            MOB_KILL_REMOVE_HEART -> ChallengeData(
                mapOf(
                    "hearts" to ChallengeIntSetting("RED_DYE", 2, "hp", min = 1, max = 20)

                ),
                mapOf(
                    "hearts" to IconNaming(
                        cmp("Hearts"),
                        listOf(cmp("Select how many hearts you lose"))
                    )
                )
            )

            DAMAGE_MOB_SPAWN -> ChallengeData(

            )
            CHUNK_SWITCH_RANDOM_EFFECT -> ChallengeData(
                mapOf(
                    "effectLevel" to ChallengeBoolSetting("COMMAND_BLOCK", true)
                ),
                mapOf(
                    "effectLevel" to IconNaming(
                        cmp("Add Levels"),
                        listOf(cmp("If you switch the chunk and get the same effect should the level of the effect add up"))
                    )
                )
            )
        }
    }
}
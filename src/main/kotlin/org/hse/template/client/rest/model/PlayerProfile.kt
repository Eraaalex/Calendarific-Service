package org.hse.template.client.rest.model

import com.fasterxml.jackson.annotation.JsonProperty
import java.net.URL
import java.time.ZonedDateTime

data class PlayerProfileResponse(
    val profile: PlayerProfile,
    @JsonProperty("rank_tier") val rankTier: Int?,
    @JsonProperty("leaderboard_rank") val leaderboardRank: Int?
)

data class PlayerProfile(
    @JsonProperty("account_id") val accountId: Long,
    @JsonProperty("personaname") val personaName: String,
    val name: String?,
    val plus: Boolean,
    val cheese: Int,
    @JsonProperty("steamid") val steamId: String,
    val avatar: URL,
    @JsonProperty("avatarmedium") val avatarMedium: URL,
    @JsonProperty("avatarfull") val avatarFull: URL,
    @JsonProperty("profileurl") val profileUrl: URL,
    @JsonProperty("last_login") val lastLogin: ZonedDateTime?,
    @JsonProperty("loccountrycode") val locCountryCode: String?,
    val status: String?,
    @JsonProperty("fh_unavailable") val fhUnavailable: Boolean?,
    @JsonProperty("is_contributor") val isContributor: Boolean,
    @JsonProperty("is_subscriber") val isSubscriber: Boolean
)

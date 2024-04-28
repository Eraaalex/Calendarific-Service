package org.hse.template.client.rest.model

import com.fasterxml.jackson.annotation.JsonProperty

data class Match(
    @JsonProperty("match_id")
    val matchId: Long,
    @JsonProperty("player_slot")
    val playerSlot: Int,
    @JsonProperty("radiant_win")
    val radiantWin: Boolean,
    val duration: Int,
    val gameMode: Int,
    val lobbyType: Int,
    val heroId: Int,
    val startTime: Int,
    val version: Int?,
    val kills: Int,
    val deaths: Int,
    val assists: Int,
    @JsonProperty("average_rank")
    val averageRank: Int,
    @JsonProperty("leaver_status")
    val leaverStatus: Int,
    @JsonProperty("party_size")
    val partySize: Int?
)

package org.hse.template.client.rest.model

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty
import java.net.URL
import java.time.ZonedDateTime

@JsonIgnoreProperties(ignoreUnknown = true)
data class PlayerSearchResponse(
    @JsonProperty("account_id") val accountId: Long,
    @JsonProperty("personaname") val personaName: String,
    @JsonProperty("avatarfull") val avatarFull: URL,
    @JsonProperty("last_match_time") val lastMatchTime: ZonedDateTime?,
    @JsonProperty("similarity") val similarity: Double
)
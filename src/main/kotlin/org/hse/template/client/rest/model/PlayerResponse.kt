package org.hse.template.client.rest.model

import com.fasterxml.jackson.annotation.JsonProperty

data class PlayerResponse(
    @JsonProperty("account_id") val accountId: Long,
    @JsonProperty("personaname") val personaName: String,
    @JsonProperty("avatarfull") val avatarFull: String,
    @JsonProperty("similarity") val similarity: Double
)
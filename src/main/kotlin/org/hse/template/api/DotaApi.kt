package org.hse.template.api

import io.swagger.v3.oas.annotations.media.Schema
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.responses.ApiResponses
import io.swagger.v3.oas.annotations.tags.Tag
import org.hse.template.client.rest.model.*
import org.springframework.http.ResponseEntity

interface DotaApi {

    @ApiResponses(
        ApiResponse(
            description = "Тестовый успешный ответ",
            responseCode = "200"
        )
    )
    fun getPlayer(
        @Schema(
            description = "Steam32 account ID",
            defaultValue = "56425837"
        )
        accountId: Long = 56425837L
    ) : PlayerProfileResponse

    @ApiResponse(
        description = "Успешный ответ со списком матчей игрока",
        responseCode = "200"
    )
    fun getPlayerMatches(
        @Schema(description = "Steam32 ID аккаунта игрока", defaultValue = "56425837")
        accountId: Long = 56425837L,
        @Schema(description = "Количество матчей для вывода", defaultValue = "5")
        limit: Int? = 5
    ): List<Match>


    @ApiResponses(
        ApiResponse(
            description = "Тестовый успешный ответ",
            responseCode = "200"
        )
    )
    fun refreshPlayer(
        @Schema(
            description = "Steam32 account ID",
            defaultValue = "56425837"
        )
        accountId: Long = 56425837L
    ): String

    @ApiResponses(
        ApiResponse(
            description = "Тестовый успешный ответ",
            responseCode = "200"
        )
    )
    fun searchPlayer(
        @Schema(
            description = "Username",
            defaultValue = "eralex"
        )
        name: String = "eralex"
    ): List<PlayerSearchResponse>


}
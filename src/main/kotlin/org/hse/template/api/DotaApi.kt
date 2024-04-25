package org.hse.template.api

import io.swagger.v3.oas.annotations.media.Schema
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.responses.ApiResponses
import io.swagger.v3.oas.annotations.tags.Tag
import org.hse.template.client.rest.model.Fact
import org.hse.template.client.rest.model.Match
import org.hse.template.client.rest.model.PlayerProfile
import org.hse.template.client.rest.model.PlayerSearchResponse
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
    ): PlayerProfile

    @ApiResponse(
        description = "Успешный ответ со списком матчей игрока",
        responseCode = "200"
    )
    fun getPlayerMatches(
        @Schema(description = "Steam32 ID аккаунта игрока", defaultValue = "56425837")
        accountId: Long = 56425837L,
        @Schema(description = "Количество матчей для вывода", defaultValue = "5")
        limit: Int? = 5,
        @Schema(description = "Количество матчей для смещения начала списка")
        offset: Int? = null,
        @Schema(description = "Фильтр матчей, где игрок победил")
        win: Int? = null,
        @Schema(description = "Идентификатор патча Dota 2")
        patch: Int? = null,
        @Schema(description = "Идентификатор игрового режима")
        gameMode: Int? = null,
        @Schema(description = "Идентификатор типа лобби")
        lobbyType: Int? = null,
        @Schema(description = "Идентификатор региона")
        region: Int? = null,
        @Schema(description = "Количество дней назад для фильтрации матчей")
        date: Int? = null,
        @Schema(description = "Идентификатор роли в полосе")
        laneRole: Int? = null,
        @Schema(description = "Идентификатор героя игрока")
        heroId: Int? = null,
        @Schema(description = "Был ли игрок на стороне Radiant")
        isRadiant: Int? = null,
        @Schema(description = "Steam32 ID аккаунтов игроков в матче (массив)")
        includedAccountId: List<Int>? = null,
        @Schema(description = "Steam32 ID аккаунтов игроков, которые не участвовали в матче (массив)")
        excludedAccountId: List<Int>? = null,
        @Schema(description = "Идентификаторы героев в команде игрока (массив)")
        withHeroId: List<Int>? = null,
        @Schema(description = "Идентификаторы героев в команде противника (массив)")
        againstHeroId: List<Int>? = null,
        @Schema(description = "Значимость матча для целей агрегации")
        significant: Int? = null,
        @Schema(description = "Минимальное количество игр, применяемое для фильтрации статистики по героям")
        having: Int? = null,
        @Schema(description = "Поле для сортировки матчей в порядке убывания")
        sort: String? = null,
        @Schema(description = "Поля для включения в ответ (массив)")
        project: List<String>? = null
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
    ): ResponseEntity<Any>


}
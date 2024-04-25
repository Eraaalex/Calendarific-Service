package org.hse.template.client.rest.api

import org.hse.template.client.rest.model.Match
import org.hse.template.client.rest.model.PlayerProfile
import org.hse.template.client.rest.model.PlayerSearchResponse
import org.springframework.cloud.openfeign.FeignClient
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestParam

@FeignClient(name = "dota", url = "https://api.opendota.com/api")
interface DotaClient {

    @PostMapping("/players/{account_id}/refresh")
    fun refreshPlayerData(@PathVariable("account_id") accountId: Long): String

    @GetMapping("/players/{account_id}")
    fun getPlayerData(@PathVariable("account_id") accountId: Long): PlayerProfile

    @GetMapping("/search")
    fun searchPlayer(@RequestParam("q") name : String) : List<PlayerSearchResponse>

    @GetMapping("/scenarios/itemTimings")
    fun getItemTimings(@RequestParam("item") item : String,
                       @RequestParam("hero_id") heroId : Int) : String

    @GetMapping("/heroes")
    fun getHeroes() : String

    @GetMapping("/players/{account_id}/matches")
    fun getPlayerMatches(
        @PathVariable("account_id") accountId: Long,
        @RequestParam("limit") limit: Int?,
        @RequestParam("offset") offset: Int?,
        @RequestParam("win") win: Int?,
        @RequestParam("patch") patch: Int?,
        @RequestParam("game_mode") gameMode: Int?,
        @RequestParam("lobby_type") lobbyType: Int?,
        @RequestParam("region") region: Int?,
        @RequestParam("date") date: Int?,
        @RequestParam("lane_role") laneRole: Int?,
        @RequestParam("hero_id") heroId: Int?,
        @RequestParam("is_radiant") isRadiant: Int?,
        @RequestParam("included_account_id") includedAccountId: List<Int>?,
        @RequestParam("excluded_account_id") excludedAccountId: List<Int>?,
        @RequestParam("with_hero_id") withHeroId: List<Int>?,
        @RequestParam("against_hero_id") againstHeroId: List<Int>?,
        @RequestParam("significant") significant: Int?,
        @RequestParam("having") having: Int?,
        @RequestParam("sort") sort: String?,
        @RequestParam("project") project: List<String>?
    ): List<Match>

}
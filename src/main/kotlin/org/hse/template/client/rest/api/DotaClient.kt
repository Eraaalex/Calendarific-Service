package org.hse.template.client.rest.api

import org.hse.template.client.rest.model.Match
import org.hse.template.client.rest.model.PlayerProfile
import org.hse.template.client.rest.model.PlayerProfileResponse
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
    fun getPlayerData(@PathVariable("account_id") accountId: Long): PlayerProfileResponse

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
    ): List<Match>

}
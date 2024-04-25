package org.hse.template.controller

import org.hse.template.api.DotaApi
import org.hse.template.client.rest.api.DotaClient
import org.hse.template.client.rest.model.Match
import org.hse.template.client.rest.model.PlayerProfile
import org.hse.template.client.rest.model.PlayerSearchResponse
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/dota")
class DotaController(private val dotaClient: DotaClient) : DotaApi {

    @PostMapping("/players/{accountId}/refresh")
    override fun refreshPlayer(@PathVariable accountId: Long): String {
        return dotaClient.refreshPlayerData(accountId)
    }

    @GetMapping("/players/{accountId}")
    override fun getPlayer(@PathVariable accountId: Long): PlayerProfile {
        return dotaClient.getPlayerData(accountId)
    }

    @GetMapping("/search")
    override fun searchPlayer(@RequestParam(required = true) name: String): ResponseEntity<Any> {
        return try{
            ResponseEntity.ok(dotaClient.searchPlayer(name))
        } catch (ex : Exception) {
            ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error: ${ex.message}")
        }

    }

    @GetMapping("/players/{account_id}/matches")
    override fun getPlayerMatches(
        @PathVariable("account_id") accountId: Long,
        @RequestParam limit: Int?,
        @RequestParam offset: Int?,
        @RequestParam win: Int?,
        @RequestParam patch: Int?,
        @RequestParam gameMode: Int?,
        @RequestParam lobbyType: Int?,
        @RequestParam region: Int?,
        @RequestParam date: Int?,
        @RequestParam laneRole: Int?,
        @RequestParam heroId: Int?,
        @RequestParam isRadiant: Int?,
        @RequestParam includedAccountId: List<Int>?,
        @RequestParam excludedAccountId: List<Int>?,
        @RequestParam withHeroId: List<Int>?,
        @RequestParam againstHeroId: List<Int>?,
        @RequestParam significant: Int?,
        @RequestParam having: Int?,
        @RequestParam sort: String?,
        @RequestParam project: List<String>?
    ): List<Match> {
        return dotaClient.getPlayerMatches(
            accountId,
            limit,
            offset,
            win,
            patch,
            gameMode,
            lobbyType,
            region,
            date,
            laneRole,
            heroId,
            isRadiant,
            includedAccountId,
            excludedAccountId,
            withHeroId,
            againstHeroId,
            significant,
            having,
            sort,
            project
        )
    }
}

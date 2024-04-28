package org.hse.template.controller

import org.hse.template.api.DotaApi
import org.hse.template.client.rest.api.DotaClient
import org.hse.template.client.rest.model.Match
import org.hse.template.client.rest.model.PlayerProfileResponse
import org.hse.template.client.rest.model.PlayerSearchResponse
import org.hse.template.repository.MatchRepository
import org.hse.template.repository.PlayerRepository
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/dota")
class DotaController(
    private val dotaClient: DotaClient,
    private val playerRepository: PlayerRepository,
    private val matchRepository : MatchRepository
) : DotaApi {

    @PostMapping("/players/{accountId}/refresh")
    override fun refreshPlayer(@PathVariable accountId: Long): String {
        return dotaClient.refreshPlayerData(accountId)
    }

    @GetMapping("/players/{accountId}")
    override fun getPlayer(@PathVariable accountId: Long): PlayerProfileResponse {
        playerRepository.findPlayerById(accountId)?.let {
            return it
        }
        val profile = dotaClient.getPlayerData(accountId)
        playerRepository.savePlayerProfile(profile)
        return profile
    }

    @GetMapping("/search")
    override fun searchPlayer(@RequestParam(required = true) name: String): List<PlayerSearchResponse> {
        return dotaClient.searchPlayer(name)
    }

    @GetMapping("/players/{account_id}/matches")
    override fun getPlayerMatches(
        @PathVariable("account_id") accountId: Long,
        @RequestParam limit: Int?,
    ): List<Match> {
        matchRepository.findPlayerMatches(accountId, limit)?.let {
            return it
        }
        val matches = dotaClient.getPlayerMatches(
            accountId,
            limit
        )
        for (match in matches) {
            matchRepository.savePlayerMatch(match, accountId)
        }
        return matches
    }
}

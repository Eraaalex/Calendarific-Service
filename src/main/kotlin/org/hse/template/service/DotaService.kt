package org.hse.template.service

import org.hse.template.client.rest.api.DotaClient
import org.hse.template.repository.PlayerRepository
import org.springframework.stereotype.Service

@Service
class DotaService(
    private val dotaClient: DotaClient,
    private val playerRepository: PlayerRepository
) {
//    fun getPlayerProfile(accountId: Long): PlayerProfile {
////        playerProfileRepository.findPlayerById(accountId)?.let {
////            return it
////        }
////        val profile = dotaClient.getPlayerData(accountId)
////        playerProfileRepository.savePlayerProfile(profile)
////        return profile
//    }
}

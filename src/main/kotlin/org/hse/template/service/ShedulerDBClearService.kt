package org.hse.template.service

import org.hse.template.repository.MatchRepository
import org.hse.template.repository.PlayerRepository
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Service

@Service
class ShedulerDBClearService(private val playerRepository: PlayerRepository,
    private val matchRepository: MatchRepository) {
    @Scheduled(fixedRate = 300000)
    fun clearAllTables() {
        playerRepository.clearCache()
        matchRepository.clearCache()
    }
}
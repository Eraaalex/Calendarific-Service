package org.hse.template.repository

import org.hse.template.client.rest.model.Match
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.jdbc.core.ResultSetExtractor
import org.springframework.stereotype.Repository

@Repository
class MatchRepository(private val jdbcTemplate: JdbcTemplate){

    fun savePlayerMatch(match: Match, accountId: Long) {
        val sql = """
            INSERT INTO match (
                match_id, account_id, player_slot, radiant_win, duration, game_mode, lobby_type, 
                hero_id, start_time, version, kills, deaths, assists, average_rank, 
                leaver_status, party_size
            ) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)
        """
        try {
            jdbcTemplate.update(
                sql,
                match.matchId,
                accountId,
                match.playerSlot,
                match.radiantWin,
                match.duration,
                match.gameMode,
                match.lobbyType,
                match.heroId,
                match.startTime,
                match.version,
                match.kills,
                match.deaths,
                match.assists,
                match.averageRank,
                match.leaverStatus,
                match.partySize
            )
        } catch (ex : Exception){
            println(ex.message)
        }

    }

    fun clearCache() {
        jdbcTemplate.update("DELETE FROM match")
    }

    fun findPlayerMatches(accountId: Long, limit: Int?): List<Match>? {
        var sql = """
            SELECT * FROM match
            WHERE account_id = ?
            """
        limit?.let {
            sql += " LIMIT ?"
        }
        val matches: MutableList<Match> = mutableListOf()
        jdbcTemplate.query(
            sql,
            arrayOf(accountId, limit), ResultSetExtractor { rs ->
                while (rs.next()) {
                    val match = Match(
                        matchId = rs.getLong("match_id"),
                        playerSlot = rs.getInt("player_slot"),
                        radiantWin = rs.getBoolean("radiant_win"),
                        duration = rs.getInt("duration"),
                        gameMode = rs.getInt("game_mode"),
                        lobbyType = rs.getInt("lobby_type"),
                        heroId = rs.getInt("hero_id"),
                        startTime = rs.getInt("start_time"),
                        version = rs.getInt("version"),
                        kills = rs.getInt("kills"),
                        deaths = rs.getInt("deaths"),
                        assists = rs.getInt("assists"),
                        averageRank = rs.getInt("average_rank"),
                        leaverStatus = rs.getInt("leaver_status"),
                        partySize = rs.getInt("party_size")
                    )
                    matches.add(match)
                }
            }
        )
        return if (matches.size < (limit ?: 0)) null else matches
    }

}
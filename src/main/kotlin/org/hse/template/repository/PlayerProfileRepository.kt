package org.hse.template.repository

import org.hse.template.client.rest.model.PlayerProfile
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.jdbc.core.ResultSetExtractor
import org.springframework.stereotype.Repository
import java.net.URL
import java.time.ZoneId


@Repository
class PlayerProfileRepository(private val jdbcTemplate: JdbcTemplate) {

    fun findPlayerById(accountId: Long): PlayerProfile? {
        val sql = "SELECT * FROM player_profiles WHERE account_id = ?"
        return jdbcTemplate.query(sql, arrayOf(accountId), ResultSetExtractor { rs ->
            if (rs.next()) {
                PlayerProfile(
                    accountId = rs.getLong("account_id"),
                    personaName = rs.getString("personaname"),
                    name = rs.getString("name"),
                    plus = rs.getBoolean("plus"),
                    cheese = rs.getInt("cheese"),
                    steamId = rs.getString("steamid"),
                    avatar = URL(rs.getString("avatar")),
                    avatarMedium = URL(rs.getString("avatarmedium")),
                    avatarFull = URL(rs.getString("avatarfull")),
                    profileUrl = URL(rs.getString("profileurl")),
                    lastLogin = rs.getTimestamp("last_login")?.toLocalDateTime()?.atZone(ZoneId.systemDefault()),
                    locCountryCode = rs.getString("loccountrycode"),
                    status = rs.getString("status"),
                    fhUnavailable = rs.getBoolean("fh_unavailable"),
                    isContributor = rs.getBoolean("is_contributor"),
                    isSubscriber = rs.getBoolean("is_subscriber"),
                    rankTier = rs.getInt("rank_tier"),
                    leaderboardRank = rs.getInt("leaderboard_rank")
                )
            } else null
        })
    }

    fun savePlayerProfile(profile: PlayerProfile) {
        val sql =
            """INSERT INTO player_profiles (account_id, 
                personaname, 
                name, 
                plus, 
                cheese, 
                steamid, 
                avatar, 
                avatarmedium, 
                avatarfull, 
                profileurl, 
                last_login, 
                loccountrycode, 
                status, 
                fh_unavailable, 
                is_contributor, 
                is_subscriber, 
                rankTier, leaderboardRank)
                     VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)"""
        jdbcTemplate.update(
            sql,
            profile.accountId,
            profile.personaName,
            profile.name,
            profile.plus,
            profile.cheese,
            profile.steamId,
            profile.avatar.toString(),
            profile.avatarMedium.toString(),
            profile.avatarFull.toString(),
            profile.profileUrl.toString(),
            profile.lastLogin,
            profile.locCountryCode,
            profile.status,
            profile.fhUnavailable,
            profile.isContributor,
            profile.isSubscriber,
            profile.rankTier,
            profile.leaderboardRank
        )
    }
}
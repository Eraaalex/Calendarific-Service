package org.hse.template.repository

import org.hse.template.client.rest.model.Match
import org.hse.template.client.rest.model.PlayerProfile
import org.hse.template.client.rest.model.PlayerProfileResponse
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.jdbc.core.ResultSetExtractor
import org.springframework.stereotype.Repository
import java.net.URL
import java.time.ZoneId


@Repository
class PlayerRepository(private val jdbcTemplate: JdbcTemplate) {
    fun findPlayerById(accountId: Long): PlayerProfileResponse? {
        return jdbcTemplate.query("SELECT * FROM player_profile WHERE account_id = ?",
            arrayOf(accountId), ResultSetExtractor { rs ->
                if (rs.next()) {
                    PlayerProfileResponse(
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
                            lastLogin = rs.getTimestamp("last_login")?.toLocalDateTime()
                                ?.atZone(ZoneId.systemDefault()),
                            locCountryCode = rs.getString("loccountrycode"),
                            status = rs.getString("status"),
                            fhUnavailable = rs.getBoolean("fh_unavailable"),
                            isContributor = rs.getBoolean("is_contributor"),
                            isSubscriber = rs.getBoolean("is_subscriber")
                        ),
                        rankTier = rs.getInt("rank_tier"),
                        leaderboardRank = rs.getInt("leaderboard_rank")
                    )
                } else null
            })
    }

    fun savePlayerProfile(profile: PlayerProfileResponse) {
        val sql =
            """INSERT INTO player_profile (account_id, 
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
                rank_tier, leaderboard_rank)
                     VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)"""
        jdbcTemplate.update(
            sql,
            profile.profile.accountId,
            profile.profile.personaName,
            profile.profile.name,
            profile.profile.plus,
            profile.profile.cheese,
            profile.profile.steamId,
            profile.profile.avatar.toString(),
            profile.profile.avatarMedium.toString(),
            profile.profile.avatarFull.toString(),
            profile.profile.profileUrl.toString(),
            profile.profile.lastLogin,
            profile.profile.locCountryCode,
            profile.profile.status,
            profile.profile.fhUnavailable,
            profile.profile.isContributor,
            profile.profile.isSubscriber,
            profile.rankTier,
            profile.leaderboardRank
        )
    }

    fun clearCache() {
        fun clearCache() {
            jdbcTemplate.update("DELETE FROM player_profile")
        }
    }

}
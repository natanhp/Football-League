package com.natanhp.football_league.model

data class FavoriteMatchModel(
    val id: Long?,
    val matchId: String?,
    val homeTeamName: String?,
    val homeTeamScore: String?,
    val awayTeamName: String?,
    val awayTeamScore: String?
) {
    companion object {
        const val TABLE_FAVORITE: String = "TABLE_FAVORITE"
        const val ID: String = "ID_"
        const val MATCH_ID: String = "MATCH_ID"
        const val HOME_TEAM_NAME: String = "HOME_TEAM_NAME"
        const val HOME_TEAM_SCORE: String = "HOME_TEAM_SCORE"
        const val AWAY_TEAM_NAME: String = "AWAY_TEAM_NAME"
        const val AWAY_TEAM_SCORE: String = "AWAY_TEAM_SCORE"
    }
}
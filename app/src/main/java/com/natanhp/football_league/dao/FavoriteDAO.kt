package com.natanhp.football_league.dao

import android.content.Context
import android.database.sqlite.SQLiteConstraintException
import com.natanhp.football_league.db.database
import com.natanhp.football_league.model.FavoriteMatchModel
import com.natanhp.football_league.modeldata.MatchModel
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.insert
import org.jetbrains.anko.db.select

class FavoriteDAO {

    companion object {
        private lateinit var result: List<FavoriteMatchModel>
        fun addToFavorite(context: Context, match: MatchModel) {
            try {
                context.database.use {
                    insert(
                        FavoriteMatchModel.TABLE_FAVORITE,
                        FavoriteMatchModel.MATCH_ID to match.idMatch,
                        FavoriteMatchModel.HOME_TEAM_NAME to match.teamHome,
                        FavoriteMatchModel.HOME_TEAM_SCORE to match.homeScore,
                        FavoriteMatchModel.AWAY_TEAM_NAME to match.teamAway,
                        FavoriteMatchModel.AWAY_TEAM_SCORE to match.awayScore
                    )
                }
            } catch (e: SQLiteConstraintException) {
                error(e)
            }
        }

        fun showFavorite(context: Context): List<FavoriteMatchModel>? {

            context.database.use {
                try {
                    val query = select(FavoriteMatchModel.TABLE_FAVORITE)
                    result = query.parseList(classParser<FavoriteMatchModel>())
                } catch (e: SQLiteConstraintException) {
                    error(e)
                }
            }


            return result
        }
    }
}
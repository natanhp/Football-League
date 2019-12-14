package com.natanhp.football_league.dao

import android.content.Context
import android.database.sqlite.SQLiteConstraintException
import com.natanhp.football_league.db.database
import com.natanhp.football_league.model.TeamModel
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.delete
import org.jetbrains.anko.db.insert
import org.jetbrains.anko.db.select

class TeamFavoriteDAO {
    companion object {
        private lateinit var result: List<TeamModel>
        fun addToFavorite(context: Context, team: TeamModel) {
            try {
                context.database.use {
                    insert(
                        TeamModel.TABLE_NAME,
                        TeamModel.TEAM_ID to team.idTeam.toString(),
                        TeamModel.TEAM_NAME to team.teamName,
                        TeamModel.TEAM_LOGO to team.teamLogo,
                        TeamModel.ALTERNATE_NAME to team.alternateName,
                        TeamModel.FORMED_YEAR to team.formedYear,
                        TeamModel.STADIUM to team.stadium,
                        TeamModel.STADIUM_DESCRIPTION to team.stadiumDescription,
                        TeamModel.STADIUM_LOCATION to team.stadiumLocation,
                        TeamModel.TEAM_DESCRIPTION to team.teamDescription,
                        TeamModel.SPORT_TYPE to team.sportType
                    )
                }
            } catch (e: SQLiteConstraintException) {
                error(e)
            }
        }

        fun showFavorite(context: Context): List<TeamModel>? {

            context.database.use {
                try {
                    val query = select(TeamModel.TABLE_NAME)
                    result = query.parseList(classParser<TeamModel>())
                } catch (e: SQLiteConstraintException) {
                    error(e)
                }
            }


            return result
        }

        fun removeFromFavorite(context: Context, teamId: Long) {
            try {
                context.database.use {
                    delete(TeamModel.TABLE_NAME, "(TEAM_ID = {id})", "id" to teamId)
                }
            } catch (e: SQLiteConstraintException) {
                error(e)
            }
        }

        fun getFavoriteState(context: Context, teamId: Long): List<TeamModel> {
            try {
                context.database.use {
                    val query = select(TeamModel.TABLE_NAME).whereArgs(
                        "(TEAM_ID = {id})",
                        "id" to teamId
                    )
                    result = query.parseList(classParser())
                }
            } catch (e: SQLiteConstraintException) {
                error(e)
            }

            return result
        }
    }
}
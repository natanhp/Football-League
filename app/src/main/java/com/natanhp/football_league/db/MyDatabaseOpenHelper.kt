package com.natanhp.football_league.db

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import com.natanhp.football_league.model.FavoriteMatchModel
import com.natanhp.football_league.model.TeamModel
import org.jetbrains.anko.db.*

class MyDatabaseOpenHelper(ctx: Context) :
    ManagedSQLiteOpenHelper(ctx, "FavoriteMatch.db", null, 1) {

    companion object {
        private var instance: MyDatabaseOpenHelper? = null

        @Synchronized
        fun getInstance(ctx: Context): MyDatabaseOpenHelper {
            if (instance == null) {
                instance = MyDatabaseOpenHelper(ctx.applicationContext)
            }

            return instance as MyDatabaseOpenHelper
        }
    }

    override fun onCreate(p0: SQLiteDatabase?) {
        p0?.createTable(
            FavoriteMatchModel.TABLE_FAVORITE, true,
            FavoriteMatchModel.ID to INTEGER + PRIMARY_KEY + AUTOINCREMENT,
            FavoriteMatchModel.MATCH_ID to TEXT + UNIQUE,
            FavoriteMatchModel.HOME_TEAM_NAME to TEXT,
            FavoriteMatchModel.HOME_TEAM_SCORE to TEXT,
            FavoriteMatchModel.AWAY_TEAM_NAME to TEXT,
            FavoriteMatchModel.AWAY_TEAM_SCORE to TEXT
        )

        p0?.createTable(
            TeamModel.TABLE_NAME, true,
            TeamModel.ID to INTEGER + PRIMARY_KEY + AUTOINCREMENT,
            TeamModel.TEAM_ID to TEXT + UNIQUE,
            TeamModel.TEAM_LOGO to TEXT,
            TeamModel.TEAM_NAME to TEXT,
            TeamModel.ALTERNATE_NAME to TEXT,
            TeamModel.FORMED_YEAR to TEXT,
            TeamModel.STADIUM to TEXT,
            TeamModel.STADIUM_DESCRIPTION to TEXT,
            TeamModel.STADIUM_LOCATION to TEXT,
            TeamModel.TEAM_DESCRIPTION to TEXT,
            TeamModel.SPORT_TYPE to TEXT
        )
    }

    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {
        p0?.dropTable(FavoriteMatchModel.TABLE_FAVORITE, true)
        p0?.dropTable(TeamModel.TABLE_NAME, true)
    }

    override fun onDowngrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        super.onDowngrade(db, oldVersion, newVersion)
        db?.dropTable(FavoriteMatchModel.TABLE_FAVORITE, true)
        db?.dropTable(TeamModel.TABLE_NAME, true)
    }

}

val Context.database: MyDatabaseOpenHelper
    get() = MyDatabaseOpenHelper.getInstance(applicationContext)
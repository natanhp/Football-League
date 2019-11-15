package com.natanhp.football_league.db

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import com.natanhp.football_league.model.FavoriteMatchModel
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
    }

    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {
        p0?.dropTable(FavoriteMatchModel.TABLE_FAVORITE, true)
    }

}

val Context.database: MyDatabaseOpenHelper
    get() = MyDatabaseOpenHelper.getInstance(applicationContext)
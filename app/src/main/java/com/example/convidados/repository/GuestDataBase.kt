package com.example.convidados.repository

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.os.Build.VERSION
import com.example.convidados.constants.DataBaseConstants

/**
 * GuetsDataBase -> Conexão com o banco(somente)
 */
class GuestDataBase(context: Context) :
    SQLiteOpenHelper(context, NAME, null, VERSION) {

    companion object {
        private const val NAME = "guestdb"
        private const val VERSION = 1
    }

    /**
     * Criação do banco quando o bd não existe
     */
    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL("CREATE TABLE " + DataBaseConstants.GUEST.TABLE_NAME + " (" +
                    DataBaseConstants.GUEST.COLUMNS.ID + " integer primary key autoincrement, " +
                    DataBaseConstants.GUEST.COLUMNS.NAME + " text, " +
                    DataBaseConstants.GUEST.COLUMNS.PRESENCE + " integer);")
    }

    override fun onUpgrade(dp: SQLiteDatabase, oldVersion: Int, newVersion: Int) {

    }
}
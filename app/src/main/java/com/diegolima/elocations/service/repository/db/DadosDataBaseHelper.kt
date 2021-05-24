package com.diegolima.elocations.service.repository.db

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.diegolima.elocations.service.constants.DataBaseConstants

//SQLite
class DadosDataBaseHelper(context: Context) :
    SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    override fun onCreate(db: SQLiteDatabase?) {
        //ter cuidado ao dicionar mais tipos de dados, com o db com informaçõs ja adicionadas
        db?.execSQL(CREATE_TABLE_DADOS)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {

    }

    companion object {
        private const val DATABASE_VERSION = 1
        private const val DATABASE_NAME = "Dados.db"

        private const val CREATE_TABLE_DADOS =
            ("create table " + DataBaseConstants.DADOS.TABLE_NAME + " ("
                    + DataBaseConstants.DADOS.COLUMS.ID + " integer primary key autoincrement, "
                    + DataBaseConstants.DADOS.COLUMS.NAME + " text, "
                    + DataBaseConstants.DADOS.COLUMS.DESCRIPTION + " text);"
            )
    }
}
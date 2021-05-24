package com.diegolima.elocations.service.repository

import android.content.ContentValues
import android.content.Context
import com.diegolima.elocations.service.constants.DataBaseConstants
import com.diegolima.elocations.service.model.DadosModel
import com.diegolima.elocations.service.repository.db.DadosDataBaseHelper

//buscar os dados do banco de dados SQLite
class DadosRepository private constructor(context: Context) {

    //singleton - instanciamento unico de seguranca do Repositorio
    private var mDadosDataBaseHelper: DadosDataBaseHelper = DadosDataBaseHelper(context)

    //static
    companion object {

        //pra gerenciar de fato as instancia da classe se cria
        private lateinit var repository: DadosRepository

        //verificacao se o repositorio é nulo
        fun getInstance(context: Context): DadosRepository {
            if (!::repository.isInitialized) { //para inicializacao tardia -> isInitialized
                repository = DadosRepository(context)
            }
            return repository
        }
    }

    fun get(id: Int): DadosModel? {
        var dados: DadosModel? = null
        return try {
            val db = mDadosDataBaseHelper.readableDatabase //conexão

            val projection = arrayOf(
                DataBaseConstants.DADOS.COLUMS.NAME,
                DataBaseConstants.DADOS.COLUMS.DESCRIPTION
            )

            val selection =
                DataBaseConstants.DADOS.COLUMS.ID + " = ?" //baseado na coluna ID igual ao ponto de interrogacao
            val args = arrayOf(id.toString())

            val cursor = db.query(
                DataBaseConstants.DADOS.TABLE_NAME,
                projection,
                selection,
                args,
                null,
                null,
                null
            )

            if (cursor != null && cursor.count > 0) {
                cursor.moveToFirst()

                val name =
                    cursor.getString(cursor.getColumnIndex(DataBaseConstants.DADOS.COLUMS.NAME))
                val description =
                    cursor.getString(cursor.getColumnIndex(DataBaseConstants.DADOS.COLUMS.DESCRIPTION))

                dados = DadosModel(id, name, description)
            }

            cursor?.close() //libera o cursor da memoria
            dados
        } catch (e: Exception) {
            dados
        }
    }

    fun getAll(): List<DadosModel> {
        val list: MutableList<DadosModel> = ArrayList()
        return try {
            val db = mDadosDataBaseHelper.readableDatabase //conexão

            val projection = arrayOf(
                DataBaseConstants.DADOS.COLUMS.ID,
                DataBaseConstants.DADOS.COLUMS.NAME,
                DataBaseConstants.DADOS.COLUMS.DESCRIPTION
            )

            val cursor = db.query(
                DataBaseConstants.DADOS.TABLE_NAME,
                projection,
                null,
                null,
                null,
                null,
                null
            )

            if (cursor != null && cursor.count > 0) {
                while (cursor.moveToNext()) {

                    val id =
                        cursor.getInt(cursor.getColumnIndex(DataBaseConstants.DADOS.COLUMS.ID))
                    val name =
                        cursor.getString(cursor.getColumnIndex(DataBaseConstants.DADOS.COLUMS.NAME))
                    val description =
                        cursor.getString(cursor.getColumnIndex(DataBaseConstants.DADOS.COLUMS.DESCRIPTION))

                    val dados = DadosModel(id, name, description)
                    list.add(dados)
                }
            }
            cursor?.close()
            list
        } catch (e: Exception) {
            list
        }
    }

    //CRUD
    fun save(dados: DadosModel): Boolean {
        return try {
            val db = mDadosDataBaseHelper.writableDatabase

            val contentValues = ContentValues()
            contentValues.put(DataBaseConstants.DADOS.COLUMS.NAME, dados.name)
            contentValues.put(DataBaseConstants.DADOS.COLUMS.DESCRIPTION, dados.description)
            db.insert(DataBaseConstants.DADOS.TABLE_NAME, null, contentValues)

            true
        } catch (e: Exception) {
            false
        }
    }

    fun update(dados: DadosModel): Boolean {
        return try {
            val db = mDadosDataBaseHelper.writableDatabase

            val contentValues = ContentValues()
            contentValues.put(DataBaseConstants.DADOS.COLUMS.NAME, dados.name)
            contentValues.put(DataBaseConstants.DADOS.COLUMS.DESCRIPTION, dados.description)

            val selection = DataBaseConstants.DADOS.COLUMS.ID + " = ?"
            val args = arrayOf(dados.id.toString())

            db.update(DataBaseConstants.DADOS.TABLE_NAME, contentValues, selection, args)

            true
        } catch (e: Exception) {
            false
        }
    }

    fun delete(id: Int): Boolean {
        return try {
            val db = mDadosDataBaseHelper.writableDatabase

            val selection = DataBaseConstants.DADOS.COLUMS.ID + " = ?"
            val args = arrayOf(id.toString())

            db.delete(DataBaseConstants.DADOS.TABLE_NAME, selection, args)

            true
        } catch (e: Exception) {
            false
        }
    }

}
package com.notgodzilla.android.myapplication.api

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.PreferenceDataStoreFactory
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStoreFile
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.map

// private constructor as we only want one instance of PreferenceRepository
class PreferencesRepository private constructor(
    private val dataStore: DataStore<Preferences>
) {
    //Default query is 'Valid' employee list type
    val lastEmployeeListTypeQuery: Flow<String> = dataStore.data.map {
        it[EMPLOYEE_LIST_QUERY] ?: EmployeeListType.VALID.toString()
    }.distinctUntilChanged()

    suspend fun setStoredQuery(query: String) {
        dataStore.edit {
            it[EMPLOYEE_LIST_QUERY] = query
        }
    }

    companion object {
        private val EMPLOYEE_LIST_QUERY = stringPreferencesKey("employeeListTypeQuery")
        private var INSTANCE: PreferencesRepository? = null

        fun initialize(context: Context) {
            if (INSTANCE == null) {
                val dataStore = PreferenceDataStoreFactory.create {
                    context.preferencesDataStoreFile("settings")
                }
                INSTANCE = PreferencesRepository(dataStore)
            }
        }

        fun get(): PreferencesRepository {
            return INSTANCE ?: throw IllegalStateException(
                "PreferencesRepository must be initialized"
            )
        }

    }
}
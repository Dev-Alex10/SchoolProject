package my.schoolProject.di

import android.content.Context
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import java.io.IOException
import javax.inject.Inject
import javax.inject.Singleton

private val Context.dataStore by preferencesDataStore("settings")

private object PreferencesKeys {
    val REMEMBER_LOGIN = booleanPreferencesKey("remember_login")
}


@Singleton
class DataStoreManager @Inject constructor(@ApplicationContext applicationContext: Context) {
    private val settingsDataStore = applicationContext.dataStore

    suspend fun setRememberLoggedIn(remember: Boolean) {
        settingsDataStore.edit { settings ->
            settings[PreferencesKeys.REMEMBER_LOGIN] = remember
        }
    }


    suspend fun clear() {
        settingsDataStore.edit { it.clear() }
    }

    val rememberLoginFlow: Flow<UserPreferences> = settingsDataStore.data
        .catch { exception ->
            // dataStore.data throws an IOException when an error is encountered when reading data
            if (exception is IOException) {
                emit(emptyPreferences())
            } else {
                throw exception
            }
        }.map { preferences ->
            val remember = preferences[PreferencesKeys.REMEMBER_LOGIN] ?: false

            UserPreferences(remember)
        }

    data class UserPreferences(val rememberLogin: Boolean)
}

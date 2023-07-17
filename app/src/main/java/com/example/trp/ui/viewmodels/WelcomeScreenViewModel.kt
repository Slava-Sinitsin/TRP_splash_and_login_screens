package com.example.trp.ui.viewmodels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import com.example.trp.data.JWTDecoder
import com.example.trp.data.User
import com.example.trp.data.UserDataManager
import com.example.trp.ui.screens.bottombar.BottomBarScreen
import kotlinx.coroutines.launch
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json

class WelcomeScreenViewModel(
    bottomBarNavController: NavHostController
) : ViewModel() {

    var user by mutableStateOf(User())
        private set

    init {
        viewModelScope.launch {
            UserDataManager.getUser().collect {
                user = it
                addUserInformation()
            }
        }
    }

    private suspend fun addUserInformation() {
        val updatedUser = parseToken(user.token.toString())
        UserDataManager.saveUser(updatedUser)
    }

    private fun parseToken(token: String): User {
        val decodedToken = JWTDecoder().decodeToken(token)
        val tempUser = Json.decodeFromString<User>(decodedToken.toString())
        return user.copy(
            sub = tempUser.sub,
            id = tempUser.id,
            username = tempUser.username,
            fullName = tempUser.fullName,
            role = tempUser.role,
            iat = tempUser.iat,
            iss = tempUser.iss,
            exp = tempUser.exp
        )
    }

    var bottomBarNavController = bottomBarNavController
        private set

    val screens = listOf(
        BottomBarScreen.Tasks,
        BottomBarScreen.Home,
        BottomBarScreen.Me
    )

    fun isSelected(screen: BottomBarScreen): Boolean {
        return bottomBarNavController.currentDestination?.hierarchy?.any {
            it.route == screen.route
        } == true
    }

    fun navigate(screen: BottomBarScreen) {
        bottomBarNavController.navigate(screen.route) {
            popUpTo(bottomBarNavController.graph.findStartDestination().id)
            launchSingleTop = true
        }
    }
}
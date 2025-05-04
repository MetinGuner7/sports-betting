package com.sports.betting.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.remember
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hasRoute
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navOptions
import com.sports.betting.navigation.TopLevelDestination
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.toImmutableList

/**
 * Remembers and returns an instance of [MainAppState].
 *
 * @param navController The [NavHostController] to be used for navigation. Defaults to a new
 *   instance of [rememberNavController].
 * @return An instance of [MainAppState].
 */
@Composable
fun rememberMainAppState(
    navController: NavHostController = rememberNavController()
): MainAppState {
    return remember(key1 = navController) { MainAppState(navController = navController) }
}

/**
 * A state holder class for the Main app's navigation state.
 *
 * @property navController The [NavHostController] used for navigation.
 */

@Stable
class MainAppState(val navController: NavHostController) {
    /**
     * The current destination in the navigation graph.
     *
     * This property is a [Composable] that returns the current [NavDestination] from the
     * [navController]'s back stack.
     */
    val currentDestination: NavDestination?
        @Composable get() = navController.currentBackStackEntryAsState().value?.destination

    val shouldShowBottomBar: Boolean
        @Composable get() = currentDestination?.isRouteTopLevel() == true

    /**
     * Map of top level destinations to be used in the TopBar, BottomBar and NavRail. The key is the
     * route.
     */
    private val topLevelDestinations: ImmutableList<TopLevelDestination> =
        TopLevelDestination.entries.toImmutableList()

    private var isBottomBarVisible = false

    /**
     * UI logic for navigating to a top level destination in the app. Top level destinations have
     * only one copy of the destination of the back stack, and save and restore state whenever you
     * navigate to and from it.
     *
     * @param topLevelDestination: The destination the app needs to navigate to.
     */
    fun navigateToTopLevelDestination(topLevelDestination: TopLevelDestination) {
        val topLevelNavOptions = navOptions {
            // Pop up to the start destination of the graph to
            // avoid building up a large stack of destinations
            // on the back stack as users select items
            popUpTo(navController.graph.findStartDestination().id) { saveState = true }
            // Avoid multiple copies of the same destination when
            // reselecting the same item
            launchSingleTop = true
            // Restore state when reselecting a previously selected item
            restoreState = true
        }

        when (topLevelDestination) {
            TopLevelDestination.HOME -> {

            }
            TopLevelDestination.PROFILE -> {

            }

        }
    }


    private fun NavDestination.isRouteTopLevel(): Boolean {
        val topLevels = topLevelDestinations.map { it.route }
        for (topLevel in topLevels) {
            if (hasRoute(topLevel::class)) {
                return true
            }
        }
        return false
    }
}

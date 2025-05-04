package com.sports.betting.navigation

import androidx.annotation.StringRes
import androidx.compose.ui.graphics.vector.ImageVector
import com.sports.betting.R
import com.sports.common.base.Route
import com.sports.designsystem.icons.AppIcons
import com.sports.designsystem.icons.Mail
import com.sports.designsystem.icons.User

/**
 * Type for the top level destinations in the application. Contains metadata about the destination
 * that is used in the top app bar and common navigation UI.
 *
 * @param selectedIcon The icon to be displayed in the navigation UI when this destination is
 *   selected.
 * @param unselectedIcon The icon to be displayed in the navigation UI when this destination is not
 *   selected.
 * @param iconTextId Text that to be displayed in the navigation UI.
 * @param titleTextId Text that is displayed on the top app bar.
 * @param route The route to use when navigating to this destination.
 * @param baseRoute The highest ancestor of this destination. Defaults to [route], meaning that
 *   there is a single destination in that section of the app (no nested destinations).
 */
enum class TopLevelDestination(
    val selectedIcon: ImageVector,
    val unselectedIcon: ImageVector,
    @StringRes val iconTextId: Int,
    @StringRes val titleTextId: Int,
    val route: Route,
) {
    HOME(
        selectedIcon = AppIcons.Mail,
        unselectedIcon = AppIcons.Mail,
        iconTextId = R.string.project_id,
        titleTextId = R.string.project_id,
        route = Route(),
    ),
    PROFILE(
        selectedIcon = AppIcons.User,
        unselectedIcon = AppIcons.User,
        iconTextId = R.string.project_id,
        titleTextId = R.string.project_id,
        route = Route(),
    ),
}

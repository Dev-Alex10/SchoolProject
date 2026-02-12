package my.schoolproject.dashboard.presentation

sealed interface DashboardAction {
    data object OnLogoutClick : DashboardAction
    data class OnDetailsClick(val id: Int) : DashboardAction
}


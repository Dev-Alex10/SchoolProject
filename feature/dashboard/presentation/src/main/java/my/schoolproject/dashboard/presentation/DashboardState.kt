package my.schoolproject.dashboard.presentation

import my.schoolproject.dashboard.presentation.model.LessonModule

data class DashboardState(
    val lessonModules: List<LessonModule> = emptyList(),
    val selectedLessonId: Int? = null
)

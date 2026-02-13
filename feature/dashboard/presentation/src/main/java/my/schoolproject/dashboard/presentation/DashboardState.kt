package my.schoolproject.dashboard.presentation

import my.schoolproject.dashboard.presentation.model.LessonUi

data class DashboardState(
    val lessonModules: List<LessonUi> = emptyList(),
    val selectedLessonId: Int? = null
)

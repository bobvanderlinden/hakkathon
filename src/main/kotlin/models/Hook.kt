package models

data class Hook(
    val type: String,
    val id: Long,
    val name: String,
    val active: Boolean,
    val events: List<String>,
    val created_at: String,
    val updated_at: String,
    val app_id: Long
)
package com.example.trp.data.mappers

import com.example.trp.data.mappers.tasks.Task
import com.example.trp.data.mappers.tasks.Team
import com.google.gson.annotations.SerializedName

data class TeamAppointments(
    @SerializedName("id") val id: Int? = null,
    @SerializedName("team") val team: Team? = null,
    @SerializedName("labWorkVariant") val task: Task? = null,
    @SerializedName("status") val status: String? = null,
    @SerializedName("grade") val grade: Int? = null,
    @SerializedName("codeReviewUrl") val codeReviewUrl: String? = null
)
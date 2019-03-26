package com.example.oliver.cvapplicationkotlin.data.model

import com.google.gson.annotations.SerializedName

data class ApplicantInfo(@SerializedName("Name") val name: String,
                         @SerializedName("ProfessionalSummary") val professionalSummary: String,
                         @SerializedName("TopicsOfKnowledge") val topicsOfKnowledge: String,
                         @SerializedName("PastExperience") val pastExperience: String)
package com.example.oliver.cvapplicationkotlin.data

import com.example.oliver.cvapplicationkotlin.data.model.ApplicantInfo
import io.reactivex.Observable

interface DataSource {
    fun getListOfApplicants(): Observable<List<ApplicantInfo>>
}
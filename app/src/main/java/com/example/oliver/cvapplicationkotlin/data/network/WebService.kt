package com.example.oliver.cvapplicationkotlin.data.network

import com.example.oliver.cvapplicationkotlin.utils.APPLICATIONS_ENDPOINT
import com.example.oliver.cvapplicationkotlin.data.model.ApplicantInfo
import io.reactivex.Observable
import retrofit2.http.GET

interface WebService{

    @GET(APPLICATIONS_ENDPOINT)
    fun getListOfApplicants(): Observable<List<ApplicantInfo>>
}
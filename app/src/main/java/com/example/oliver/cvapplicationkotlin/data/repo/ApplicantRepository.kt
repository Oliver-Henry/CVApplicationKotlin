package com.example.oliver.cvapplicationkotlin.data.repo

import com.example.oliver.cvapplicationkotlin.data.DataSource
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers


open class ApplicantRepository(private val remoteDataSource: DataSource) : DataSource{
    override fun getListOfApplicants() =
            remoteDataSource.getListOfApplicants()
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
}
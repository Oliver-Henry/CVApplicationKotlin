package com.example.oliver.cvapplicationkotlin.ui.main

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.example.oliver.cvapplicationkotlin.data.model.ApplicantInfo
import com.example.oliver.cvapplicationkotlin.data.RemoteDataSource
import com.example.oliver.cvapplicationkotlin.data.repo.ApplicantRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class MainViewModel : ViewModel() {
    private val repository = ApplicantRepository(RemoteDataSource())
    private val applicantsObservable = MutableLiveData<List<ApplicantInfo>>()
    private val compositeDisposable = CompositeDisposable()

    fun getApplicantsObservable() = applicantsObservable

    fun getData() {
        compositeDisposable.add(repository.getListOfApplicants()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({applicantsObservable.value = it}, { throwable -> throwable.printStackTrace() }))
    }
}
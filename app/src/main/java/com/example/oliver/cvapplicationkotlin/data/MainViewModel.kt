package com.example.oliver.cvapplicationkotlin.data

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.example.oliver.cvapplicationkotlin.data.model.ApplicantInfo
import com.example.oliver.cvapplicationkotlin.data.RemoteDataSource
import com.example.oliver.cvapplicationkotlin.data.repo.ApplicantRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class MainViewModel : ViewModel() {
    lateinit var repository :ApplicantRepository
    val applicantsObservable = MutableLiveData<List<ApplicantInfo>>()
    val errorObservable = MutableLiveData<String>()
    private val compositeDisposable = CompositeDisposable()
    fun getData() {
        compositeDisposable.add(repository.getListOfApplicants()
                .subscribe({applicantsObservable.value = it}, {
                    throwable -> throwable.printStackTrace()
                    errorObservable.value=throwable.localizedMessage
                }))
    }
}
package com.example.oliver.cvapplicationkotlin

import android.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.oliver.cvapplicationkotlin.data.MainViewModel
import com.example.oliver.cvapplicationkotlin.data.model.ApplicantInfo
import com.example.oliver.cvapplicationkotlin.data.repo.ApplicantRepository
import io.reactivex.Observable
import junit.framework.Assert.assertEquals
import junit.framework.Assert.assertNull
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnit
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class MainViewModelTest {

    @Rule
    @JvmField
    var rule = MockitoJUnit.rule()

    @Rule
    @JvmField
    var testRule = InstantTaskExecutorRule()


    @Mock
    lateinit var appRepository: ApplicantRepository

    lateinit var mainViewModel: MainViewModel
    @Before
    fun setup(){
        MockitoAnnotations.initMocks(this)
        mainViewModel= MainViewModel().apply {
            repository = appRepository
        }
    }

    @Test
    fun testGetDataSuccess(){
        val applicationInfos = mutableListOf(ApplicantInfo("Test","Test","Test","test"))
        `when`(appRepository.getListOfApplicants()).thenReturn(Observable.just(applicationInfos))

        mainViewModel.getData()

        assertEquals(applicationInfos,mainViewModel.applicantsObservable.value)
        assertNull(mainViewModel.errorObservable.value)
    }
    @Test
    fun testGetDataFailure(){
        val errorMessage = "This is error message"
        `when`(appRepository.getListOfApplicants()).thenReturn(Observable.error(Throwable(errorMessage)))

        mainViewModel.getData()

        assertNull(mainViewModel.applicantsObservable.value)
        assertEquals(errorMessage,mainViewModel.errorObservable.value)
    }
}
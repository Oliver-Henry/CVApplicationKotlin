package com.example.oliver.cvapplicationkotlin.ui.main

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.widget.Toast
import com.example.oliver.cvapplicationkotlin.R
import com.example.oliver.cvapplicationkotlin.data.MainViewModel
import com.example.oliver.cvapplicationkotlin.data.RemoteDataSource
import com.example.oliver.cvapplicationkotlin.data.repo.ApplicantRepository
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val homeViewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)
        val resultsAdapter = ApplicationAdapter()
        rV.layoutManager = LinearLayoutManager(this)
        rV.adapter = resultsAdapter
        homeViewModel.apply {
            repository = ApplicantRepository(RemoteDataSource())
        }
        homeViewModel.applicantsObservable.observe(this, Observer { resultsAdapter.setData(it)})
        homeViewModel.errorObservable.observe(this, Observer {
            Toast.makeText(this@MainActivity,it,Toast.LENGTH_LONG).show()
        })
        homeViewModel.getData()
    }
}

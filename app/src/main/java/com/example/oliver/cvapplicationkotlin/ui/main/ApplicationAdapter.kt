package com.example.oliver.cvapplicationkotlin.ui.main

import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import com.example.oliver.cvapplicationkotlin.R
import com.example.oliver.cvapplicationkotlin.data.model.ApplicantInfo
import com.example.oliver.cvapplicationkotlin.utils.inflate
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.view_holder_applicant.*

class ApplicationAdapter : RecyclerView.Adapter<ApplicationAdapter.ApplicationViewHolder>() {
    private val applications = mutableListOf<ApplicantInfo>()

    fun setData(data: List<ApplicantInfo>?) {
        data?.let {
            applications.clear()
            applications.addAll(data)
            notifyDataSetChanged()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, p1: Int) = ApplicationViewHolder(parent.inflate(R.layout.view_holder_applicant))

    override fun getItemCount() = applications.size

    override fun onBindViewHolder(viewHolder: ApplicationViewHolder, position: Int) {
        viewHolder.bind(applications[position])
    }


    class ApplicationViewHolder(override val containerView: View) : RecyclerView.ViewHolder(containerView),
            LayoutContainer {

        fun bind(info: ApplicantInfo) {
            tvName.text = info.name
            tVSummary.text = info.professionalSummary
            tVKnowledge.text = info.topicsOfKnowledge
            tVExperience.text = info.pastExperience
        }
    }

}
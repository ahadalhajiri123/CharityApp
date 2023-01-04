package com.example.charityapp

import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.collection.LLRBNode

class DonationAdapter(var context:Context, var list: ArrayList<Donation.DonationDataModleWithId>):RecyclerView.Adapter<DonationAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.item_admin, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
       val donation:Donation.DonationDataModleWithId= list[position]

        holder.txtRealName?.text=donation.name
        holder.txtAddress?.text=donation.etAddress
        holder.txtTime?.text=donation.time
        holder.txtQuantity?.text=donation.etQuantity
        holder.txtRealName?.text=donation.name
        holder.txtDescription?.text=donation.strDetail

        if (donation.status == "0"){
            holder.status?.text="Pending"
            holder.status?.setTextColor(Color.RED)
        }else{
            holder.status?.text="Accepted"
            holder.status?.setTextColor(Color.GREEN)
        }


    }

    override fun getItemCount(): Int {
     return  list.size
    }

    class ViewHolder(itemView:View):RecyclerView.ViewHolder(itemView) {
        var txtRealName: TextView? = null
        var txtAddress:TextView? = null
        var txtTime:TextView? = null
        var txtQuantity:TextView? = null
        var txtDescription:TextView? = null
        var status:TextView? = null

        init {

            txtRealName = itemView.findViewById(R.id.txtRealName)
            txtAddress = itemView.findViewById(R.id.txtRealAddress)
            txtTime = itemView.findViewById(R.id.txtRealTime)
            txtQuantity = itemView.findViewById(R.id.txtRealQuantity)
            txtDescription = itemView.findViewById(R.id.txtDescription)
            status = itemView.findViewById(R.id.status)
        }
    }
}
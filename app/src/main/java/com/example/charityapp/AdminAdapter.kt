package com.example.charityapp

import android.app.Activity
import android.content.Context
import android.content.DialogInterface
import android.graphics.Color
import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.NonNull
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.RecyclerView
import com.google.android.gms.tasks.OnCompleteListener
import com.google.android.gms.tasks.Task
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class AdminAdapter(var context:Context,private var list: List<DonationModel.DonationDataModelWithId>):RecyclerView.Adapter<AdminAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.item_admin1, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
      val donation:DonationModel.DonationDataModelWithId = list[position]
        holder.txtRealName?.text = donation.name
        holder.txtAddress?.text = donation.etAddress
        holder.txtTime?.text = donation.time
        holder.txtQuantity?.text = donation.etQuantity
        holder.txtDescription?.text = donation.strDetail

        if (donation.status== "0"){
            holder.btnAccept?.visibility = View.VISIBLE
            holder.statusValue?.text="Pending"
            holder.statusValue?.setTextColor(Color.RED)
            holder.btnAccept?.setOnClickListener{
                acceptDonation(donation.donationId,"1")
            }
        }else{
            holder.btnAccept?.visibility = View.GONE
            holder.statusValue?.text="Accepted"
            holder.statusValue?.setTextColor(Color.GREEN)



        }

    }

    override fun getItemCount(): Int {
        return  list.size
    }

    class ViewHolder(itemView: View):RecyclerView.ViewHolder(itemView) {
        var txtRealName: TextView? = null
        var statusValue: TextView? = null
        var txtAddress:TextView? = null
        var txtTime:TextView? = null
        var txtQuantity:TextView? = null
        var txtDescription:TextView? = null
        var btnAccept:Button? = null

        init {

            txtRealName = itemView.findViewById(R.id.txtRealName)
            txtAddress = itemView.findViewById(R.id.txtRealAddress)
            txtTime = itemView.findViewById(R.id.txtRealTime)
            txtQuantity = itemView.findViewById(R.id.txtRealQuantity)
            txtDescription = itemView.findViewById(R.id.txtDescription)
            btnAccept = itemView.findViewById(R.id.btnAccept)
            statusValue = itemView.findViewById(R.id.statusValue)
         }
    }


    private fun acceptDonation(orderId: String, orderStatus: String) {
        val builder1: AlertDialog.Builder = AlertDialog.Builder(context)
        builder1.setTitle("Accept Warning")
        builder1.setMessage("Are you sure want to Accept order ?")
        builder1.setCancelable(true)
        builder1.setPositiveButton(
            "Yes"
        ) { dialog, id ->
            val ref: DatabaseReference =
                FirebaseDatabase.getInstance().reference.
                child("DonationTable").
                child(orderId)
            ref.child("status").setValue(orderStatus)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        Toast.makeText(
                            context,
                            "Update Successfully!",
                            Toast.LENGTH_SHORT
                        ).show()
                    } else {
                        Toast.makeText(
                            context,
                            "Something goes wrong please try again",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
            dialog.cancel()
        }
        builder1.setNegativeButton(
            "No"
        ) { dialog, id -> dialog.cancel() }
        val alert11 = builder1.create()
        alert11.show()
    }

}
package com.example.charityapp

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.charityapp.databinding.ActivityDonnarSideBinding
import com.google.firebase.database.*

class DonarSideActivity : AppCompatActivity() {
    private var binding: ActivityDonnarSideBinding? = null
    var adminAdapter: AdminAdapter? = null

    var modelList: ArrayList<DonationModel.DonationDataModelWithId>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDonnarSideBinding.inflate(layoutInflater)
        setContentView(binding?.root)
        modelList= ArrayList()
        val intent = intent.getStringExtra("admin")
        if (intent == "pending") {
            fetchMyDonation("0")

        } else {
            fetchMyDonation("1")

        }
        binding?.run {

        }
    }

    private fun fetchMyDonation(status: String) {


        val dbRef = FirebaseDatabase.getInstance().reference
        dbRef.child("DonationTable").addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                modelList?.clear()
                for (snapshot1 in snapshot.children) {
                    if (status == snapshot1.child("status").getValue(String::class.java)) {


                        val donationId: String = snapshot1.key.toString()
                        val userId: String =
                            snapshot1.child("userId").getValue(String::class.java).toString()
                        val name: String =
                            snapshot1.child("name").getValue(String::class.java).toString()
                        val email: String =
                            snapshot1.child("email").getValue(String::class.java).toString()
                        val strDetail: String =
                            snapshot1.child("strDetail").getValue(String::class.java).toString()
                        val stretPhoneNo: String =
                            snapshot1.child("stretPhoneNo").getValue(String::class.java).toString()
                        val etAddress: String =
                            snapshot1.child("etAddress").getValue(String::class.java).toString()
                        val etQuantity: String =
                            snapshot1.child("etQuantity").getValue(String::class.java).toString()
                        val time: String =
                            snapshot1.child("time").getValue(String::class.java).toString()
                        val status: String =
                            snapshot1.child("status").getValue(String::class.java).toString()
                        val type: String =
                            snapshot1.child("type").getValue(String::class.java).toString()


                        modelList?.add(
                            DonationModel.DonationDataModelWithId(
                                donationId,
                                userId,
                                name,
                                email,
                                strDetail,
                                stretPhoneNo,
                                etAddress,
                                etQuantity,
                                time,
                                status,
                                type
                            )
                        )


                    }
                }


                binding?.recyclerView?.layoutManager=LinearLayoutManager(this@DonarSideActivity)
                adminAdapter = AdminAdapter(this@DonarSideActivity, modelList!!)
                binding?.recyclerView?.adapter = adminAdapter
                adminAdapter?.notifyDataSetChanged()


            }

            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(this@DonarSideActivity, error.message, Toast.LENGTH_SHORT).show()
            }
        })

    }





}
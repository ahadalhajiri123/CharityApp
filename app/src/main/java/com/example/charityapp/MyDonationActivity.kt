package com.example.charityapp

import android.content.SharedPreferences
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.charityapp.databinding.ActivityMyDonationBinding
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class MyDonationActivity : AppCompatActivity() {

    var bindingMyDonation: ActivityMyDonationBinding? = null
    var sharedPreferences: SharedPreferences? = null
    var editor: SharedPreferences.Editor? = null
    var adapter: DonationAdapter? = null
    var list: ArrayList<Donation.DonationDataModleWithId>? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindingMyDonation = ActivityMyDonationBinding.inflate(layoutInflater)
        setContentView(bindingMyDonation?.root)
        sharedPreferences = getSharedPreferences(packageName, MODE_PRIVATE)
        editor = sharedPreferences?.edit()
        val userId: String = sharedPreferences?.getString("userId", "null").toString()
        var email: String = sharedPreferences?.getString("email", "null").toString()
        var name: String = sharedPreferences?.getString("name", "null").toString()

        list = ArrayList()


        bindingMyDonation?.icDrawer?.setOnClickListener {
            onBackPressed()
        }

        fetchMyDonation(userId)
    }

    private fun fetchMyDonation(userId: String) {


//        progressBar.setVisibility(View.VISIBLE);
        val dbRef = FirebaseDatabase.getInstance().reference
        dbRef.child("DonationTable").addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                list?.clear()
                for (snapshot1 in snapshot.children) {
                    if (userId == snapshot1.child("userId").getValue(String::class.java)) {


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


                        list?.add(
                            Donation.DonationDataModleWithId(
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

                bindingMyDonation?.recycelrHisotryId?.layoutManager=LinearLayoutManager(this@MyDonationActivity)
                adapter= DonationAdapter(this@MyDonationActivity,list!!)
                if (adapter?.itemCount!=0){
                    bindingMyDonation?.recycelrHisotryId?.adapter=adapter
                    bindingMyDonation?.recycelrHisotryId?.visibility= View.VISIBLE
                    bindingMyDonation?.progressBar?.visibility= View.GONE

                }else{
                    bindingMyDonation?.recycelrHisotryId?.visibility= View.GONE
                    bindingMyDonation?.progressBar?.visibility= View.VISIBLE
                }

            }

            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(this@MyDonationActivity, error.message, Toast.LENGTH_SHORT).show()
            }
        })

    }

    override fun onBackPressed() {
        super.onBackPressed()
    }
}
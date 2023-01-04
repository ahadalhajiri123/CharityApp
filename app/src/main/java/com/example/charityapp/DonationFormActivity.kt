package com.example.charityapp

import android.app.TimePickerDialog
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.core.content.ContextCompat
import com.example.charityapp.databinding.ActivityDonationFormBinding
import com.google.firebase.database.FirebaseDatabase
import java.util.*

class DonationFormActivity : AppCompatActivity() {
    var bindingdonation: ActivityDonationFormBinding? = null
    var sharedPreferences: SharedPreferences? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindingdonation = ActivityDonationFormBinding.inflate(layoutInflater)
        setContentView(bindingdonation?.root)


        sharedPreferences = getSharedPreferences(packageName, MODE_PRIVATE)
        val userId: String = sharedPreferences?.getString("userId", "null").toString()
        val email: String = sharedPreferences?.getString("email", "null").toString()
        val name: String = sharedPreferences?.getString("name", "null").toString()
        val type: String = intent.getStringExtra("from").toString()

      settingTopImg(type)





        bindingdonation?.etTime?.setOnClickListener {
            val mTimePicker: TimePickerDialog
            val mcurrentTime = Calendar.getInstance()
            val hour = mcurrentTime.get(Calendar.HOUR_OF_DAY)
            val minute = mcurrentTime.get(Calendar.MINUTE)

            mTimePicker = TimePickerDialog(this@DonationFormActivity,
                { _, hourOfDay, minute ->
                    bindingdonation?.etTime?.text = String.format("%d : %d", hourOfDay, minute)
                    //   reminderTime = String.format("%d : %d", hourOfDay, minute)
                }, hour, minute, false
            )
            mTimePicker.show()
        }
        bindingdonation?.btnSubmitRequest?.setOnClickListener {

            val strDetail: String = bindingdonation?.etDetail?.text.toString().trim()
            val stretPhoneNo: String = bindingdonation?.etPhoneNo?.text.toString().trim()
            val etAddress: String = bindingdonation?.etAddress?.text.toString().trim()
            val etQuantity: String = bindingdonation?.etQuantity?.text.toString().trim()

            if (strDetail.isEmpty()) {
                Toast.makeText(
                    this@DonationFormActivity,
                    "Description is empty",
                    Toast.LENGTH_SHORT
                ).show()
            } else
                if (stretPhoneNo.isEmpty()) {
                    Toast.makeText(
                        this@DonationFormActivity,
                        "Phone number field is empty",
                        Toast.LENGTH_SHORT
                    ).show()
                } else
                    if (etAddress.isEmpty()) {
                        Toast.makeText(
                            this@DonationFormActivity,
                            "Address field is empty",
                            Toast.LENGTH_SHORT
                        ).show()
                    } else
                        if (bindingdonation?.etTime?.text?.toString()?.isEmpty() == true) {
                            Toast.makeText(
                                this@DonationFormActivity,
                                "Time field is empty",
                                Toast.LENGTH_SHORT
                            ).show()
                        } else if (etQuantity.isEmpty()) {
                            Toast.makeText(
                                this@DonationFormActivity,
                                "Quantity field is empty",
                                Toast.LENGTH_SHORT
                            ).show()
                        } else {
                            saveToDb(
                                userId,
                                name,
                                email,
                                strDetail,
                                stretPhoneNo,
                                etAddress,
                                etQuantity,
                                bindingdonation?.etTime?.text.toString(),
                                type
                            )
                        }


        }


    }

    private fun settingTopImg(type: String) {
        when (type) {
            "Cloths" -> {
                bindingdonation?.iconSplash?.setImageDrawable(
                    ContextCompat.getDrawable(
                        this@DonationFormActivity,
                        R.drawable.clothing5
                    )
                )
            }
            "Pants" -> {
                bindingdonation?.iconSplash?.setImageDrawable(
                    ContextCompat.getDrawable(
                        this@DonationFormActivity,
                        R.drawable.clothing4
                    )
                )

            }
            "Trousers" -> {
                bindingdonation?.iconSplash?.setImageDrawable(
                    ContextCompat.getDrawable(
                        this@DonationFormActivity,
                        R.drawable.clothing1
                    )
                )

            }
            "Shirts" -> {
                bindingdonation?.iconSplash?.setImageDrawable(
                    ContextCompat.getDrawable(
                        this@DonationFormActivity,
                        R.drawable.clothing2
                    )
                )

            }

        }
    }

    private fun saveToDb(
        userId: String,
        name: String,
        email: String,
        strDetail: String,
        stretPhoneNo: String,
        etAddress: String,
        etQuantity: String,
        time: String,
        type: String
    ) {

        val donationDataModleWithOutId = Donation.DonationDataModleWithOutId(
            userId,
            name,
            email,
            strDetail,
            stretPhoneNo,
            etAddress,
            etQuantity,
            time,
            "0",
            type
        )



        FirebaseDatabase.getInstance().getReference().child("DonationTable")
            .push().setValue(donationDataModleWithOutId)
            .addOnCompleteListener {
                if (it.isSuccessful) {
                    Toast.makeText(
                        this@DonationFormActivity,
                        "Donation is added successfully",
                        Toast.LENGTH_SHORT
                    ).show()
                    startActivity(Intent(this@DonationFormActivity,MainActivity::class.java))

                } else {
                    Toast.makeText(
                        this@DonationFormActivity,
                        "Failed,Try again",
                        Toast.LENGTH_SHORT
                    ).show()

                }
            }.addOnFailureListener {
                Toast.makeText(this@DonationFormActivity, it.message, Toast.LENGTH_SHORT).show()
            }


    }
}
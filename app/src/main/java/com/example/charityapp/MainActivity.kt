package com.example.charityapp

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.content.Intent
import android.content.SharedPreferences
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.denzcoskun.imageslider.ImageSlider
import com.denzcoskun.imageslider.constants.ScaleTypes
import com.denzcoskun.imageslider.models.SlideModel
import com.google.android.material.card.MaterialCardView

class MainActivity : AppCompatActivity() {

    var imageSlider: ImageSlider? = null
    var logoutBtn: ConstraintLayout? = null
    var myDonationBtn: ConstraintLayout? = null
    var myProfile: ConstraintLayout? = null
    var privacBtn: ConstraintLayout? = null
     var contactSupport: ConstraintLayout? = null
    var aboutbtn: ConstraintLayout? = null
     var iconDrawer: ImageView? = null
    var drawerLayout: DrawerLayout? = null
//    var navDrawer:NavigationView?=null
    lateinit var cardViewClothes:MaterialCardView
    lateinit var cardViewPants:MaterialCardView
    lateinit var cardViewShirts:MaterialCardView
    lateinit var cardViewTrousers:MaterialCardView
    var sharedPreferences:SharedPreferences?=null
    var editor:SharedPreferences.Editor?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        sharedPreferences = getSharedPreferences(packageName, MODE_PRIVATE)
        editor=sharedPreferences?.edit()
        var   userId:String=   sharedPreferences?.getString("userId", "null").toString()
       var   email:String=   sharedPreferences?.getString("email", "null").toString()
       var   name:String=   sharedPreferences?.getString("name", "null").toString()


        logoutBtn = findViewById(R.id.constrainLogout)
        myDonationBtn = findViewById(R.id.constraintMyDonation )
        myProfile = findViewById(R.id.constraintMyProfile)
        privacBtn = findViewById(R.id.constraintPrivacyPolicy1 )
         contactSupport = findViewById(R.id.constraintContactSupport )
        aboutbtn = findViewById(R.id.constraintAbout )
        imageSlider = findViewById(R.id.charitySlider)
        iconDrawer = findViewById(R.id.icDrawer)
       // navDrawer = findViewById(R.id.navDrawer)
        drawerLayout = findViewById(R.id.drawerLayout)
        cardViewClothes = findViewById(R.id.cardViewClothes)
        cardViewPants = findViewById(R.id.cardViewPaints)
        cardViewShirts = findViewById(R.id.cardViewShirts)
        cardViewTrousers = findViewById(R.id.cardViewTrouser)
        val slideModels = ArrayList<SlideModel>()
        slideModels.add(SlideModel(R.drawable.clothing1, ScaleTypes.FIT))
        slideModels.add(SlideModel(R.drawable.clothing2, ScaleTypes.FIT))
        slideModels.add(SlideModel(R.drawable.clothing4, ScaleTypes.FIT))
        slideModels.add(SlideModel(R.drawable.clothing5, ScaleTypes.FIT))
        imageSlider?.setImageList(slideModels, ScaleTypes.FIT);

        cardViewClothes.setOnClickListener {
            startActivity(Intent(this,DonationFormActivity::class.java)
                .putExtra("from","Cloths"))

        }
        cardViewPants.setOnClickListener {
            startActivity(Intent(this,DonationFormActivity::class.java)
                .putExtra("from","Pants"))
        }
        cardViewShirts.setOnClickListener {
            startActivity(Intent(this,DonationFormActivity::class.java)
                .putExtra("from","Shirts"))
        }
        cardViewTrousers.setOnClickListener {
            startActivity(Intent(this,DonationFormActivity::class.java)
                .putExtra("from","Trousers"))
        }
        iconDrawer?.setOnClickListener {
            try {
                if (!drawerLayout?.isDrawerOpen(GravityCompat.START)!!) {
                    drawerLayout?.open()
                } else {
                    drawerLayout?.close()
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }




        logoutBtn?.setOnClickListener{

            myLogoutDialog()
            drawerLayout?.close()
        }



        myDonationBtn?.setOnClickListener{
            startActivity(Intent(this@MainActivity,MyDonationActivity::class.java))
            drawerLayout?.close()
        }

        myProfile?.setOnClickListener{
            myProfileDialog()
            drawerLayout?.close()
        }

      privacBtn?.setOnClickListener{
          privacDialog()
            drawerLayout?.close()
        }
        contactSupport?.setOnClickListener{
          contactSupportDialog()
            drawerLayout?.close()
        }
        aboutbtn?.setOnClickListener{
          aboutDialog()
            drawerLayout?.close()
        }



    }

    override fun onBackPressed() {
        finishDialog()
    }

    private fun finishDialog() {
        try {
            val builder = AlertDialog.Builder(this)
            val layoutInflater = layoutInflater
            @SuppressLint("InflateParams") val dialogView: View =
                layoutInflater.inflate(R.layout.finishdialog, null)
            val yes = dialogView.findViewById<TextView>(R.id.textView14)
            val deny = dialogView.findViewById<TextView>(R.id.textView5)
            builder.setView(dialogView)
            val alertDialog = builder.create()
            alertDialog.setCancelable(true)
            alertDialog.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            alertDialog.show()
            yes.setOnClickListener(object : View.OnClickListener {
                override fun onClick(view: View) {
                    alertDialog.dismiss()
                    editor?.clear()
                    editor?.commit();
                    editor?.apply();
                    startActivity(Intent(this@MainActivity, LoginActivity::class.java))
                    finish()
                }
            })
            deny.setOnClickListener(object : View.OnClickListener {
                override fun onClick(view: View) {
                    alertDialog.dismiss()
                }
            })

        } catch (a: java.lang.Exception) {
            a.printStackTrace()
        }

    }

    private fun myLogoutDialog() {
        try {
            val builder = AlertDialog.Builder(this)
            val layoutInflater = layoutInflater
            @SuppressLint("InflateParams") val dialogView: View =
                layoutInflater.inflate(R.layout.logoutdialog, null)
            val yes = dialogView.findViewById<TextView>(R.id.textView14)
            val deny = dialogView.findViewById<TextView>(R.id.textView5)
            builder.setView(dialogView)
            val alertDialog = builder.create()
            alertDialog.setCancelable(true)
            alertDialog.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            alertDialog.show()
            yes.setOnClickListener(object : View.OnClickListener {
                override fun onClick(view: View) {
                    alertDialog.dismiss()
                    editor?.clear()
                    editor?.commit();
                    editor?.apply();
                    startActivity(Intent(this@MainActivity, LoginActivity::class.java))
                    finish()
                }
            })
            deny.setOnClickListener(object : View.OnClickListener {
                override fun onClick(view: View) {
                    alertDialog.dismiss()
                }
            })

        } catch (a: java.lang.Exception) {
            a.printStackTrace()
        }
    }

    private fun aboutDialog() {

        try {
            val builder = AlertDialog.Builder(this)
            val layoutInflater = layoutInflater
            @SuppressLint("InflateParams") val dialogView: View =
                layoutInflater.inflate(R.layout.aboutdialog, null)

            builder.setView(dialogView)
            val alertDialog = builder.create()
            alertDialog.setCancelable(true)
            alertDialog.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            alertDialog.show()


        } catch (a: java.lang.Exception) {
            a.printStackTrace()
        }
    }

    private fun contactSupportDialog() {
        try {
            val builder = AlertDialog.Builder(this)
            val layoutInflater = layoutInflater
            @SuppressLint("InflateParams") val dialogView: View =
                layoutInflater.inflate(R.layout.contactsupportdialogdialog, null)
            builder.setView(dialogView)
            val alertDialog = builder.create()
            alertDialog.setCancelable(true)
            alertDialog.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            alertDialog.show()


        } catch (a: java.lang.Exception) {
            a.printStackTrace()
        }


    }

    private fun privacDialog() {
        try {
            val builder = AlertDialog.Builder(this)
            val layoutInflater = layoutInflater
            @SuppressLint("InflateParams") val dialogView: View =
                layoutInflater.inflate(R.layout.privacdialogdialog, null)
            builder.setView(dialogView)
            val alertDialog = builder.create()
            alertDialog.setCancelable(true)
            alertDialog.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            alertDialog.show()


        } catch (a: java.lang.Exception) {
            a.printStackTrace()
        }
    }

    @SuppressLint("MissingInflatedId")
    private fun myProfileDialog() {

        try {
            val builder = AlertDialog.Builder(this)
            val layoutInflater = layoutInflater
            @SuppressLint("InflateParams") val dialogView: View =
                layoutInflater.inflate(R.layout.myprofiledialog, null)
            val nameTxt = dialogView.findViewById<TextView>(R.id.name)
            val emailTxt = dialogView.findViewById<TextView>(R.id.email)
            builder.setView(dialogView)
            val alertDialog = builder.create()
            alertDialog.setCancelable(true)
            alertDialog.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            alertDialog.show()
            var   email:String=   sharedPreferences?.getString("email", "null").toString()
            var   name:String=   sharedPreferences?.getString("name", "null").toString()

            nameTxt.text=name
            emailTxt.text=email

        } catch (a: java.lang.Exception) {
            a.printStackTrace()
        }
    }
}
package com.example.charityapp

sealed class  Donation{
    data class DonationDataModleWithId(
        var donationId:String,
        var userId: String,
        var  name: String,
        var  email: String,
        var strDetail: String,
        var stretPhoneNo: String,
        var etAddress: String,
        var etQuantity: String,
        var time: String,
        var status: String,
        var type: String)

    data class DonationDataModleWithOutId(
        var userId: String,
        var  name: String,
        var  email: String,
        var strDetail: String,
        var stretPhoneNo: String,
        var etAddress: String,
        var etQuantity: String,
        var time: String,
        var status: String,
        var type: String)
}


package com.example.charityapp

sealed class DonationModel {
data class  DonationDataModelWithId(
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
data class  DonationDataModelWithOutId(
    var userId: String,
    var  name: String,
    var  email: String,
    var strDetail: String,
    var stretPhoneNo: String,
    var etAddress: String,
    var etQuantity: String,
    var time: String,
    var status:String,
    var type: String
)
}
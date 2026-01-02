package com.example.repository

@kotlinx.serialization.Serializable
data class DriverDetailRequest(
    val visitingState: String,
    val vehicleNumber: String,
    val seatCapacity: String,
    val whatsappNumber: String,
    val entryBorder: String,
    val taxMode: String,
    val taxFromDate: String,
    val taxToDate: String,
    val totalPayment: String,
    val isTaxPaidToGovernment: String
)


@kotlinx.serialization.Serializable
data class DriverDetailResponse(
    val id: Int,
    val visitingState: String,
    val vehicleNumber: String,
    val seatCapacity: String,
    val whatsappNumber: String,
    val entryBorder: String,
    val taxMode: String,
    val taxFromDate: String,
    val taxToDate: String,
    val totalPayment: String,
    val isTaxPaidToGovernment: String
)

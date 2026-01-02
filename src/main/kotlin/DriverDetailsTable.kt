package com.example

import org.jetbrains.exposed.sql.Table


object DriverDetailsTable : Table("driver_details_table") {

    val id = integer("id").autoIncrement()

    val visitingState = varchar("visiting_state", 50)
    val vehicleNumber = varchar("vehicle_number", 10)
    val seatCapacity = varchar("seat_capacity", 50)
    val whatsappNumber = varchar("whatsapp_number", 10)

    val entryBorder = varchar("entry_border", 50)
    val taxMode = varchar("tax_mode", 50)

    val taxFromDate = varchar("tax_from_date", 20)
    val taxToDate = varchar("tax_to_date", 20)

    val totalPayment = varchar("total_payment", 20)
    val isTaxPaidToGovernment = varchar("is_tax_paid_to_government", 10)

    override val primaryKey = PrimaryKey(id)
}

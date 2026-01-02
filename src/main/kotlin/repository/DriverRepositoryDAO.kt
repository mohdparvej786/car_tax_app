package com.example.repository

import com.example.DriverDetailsTable
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import org.jetbrains.exposed.sql.deleteWhere
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.selectAll
import org.jetbrains.exposed.sql.transactions.transaction

object DriverRepositoryDAO {

    fun insertDriver(request: DriverDetailRequest): Int {
        return transaction {
            DriverDetailsTable.insert {
                it[visitingState] = request.visitingState
                it[vehicleNumber] = request.vehicleNumber
                it[seatCapacity] = request.seatCapacity
                it[whatsappNumber] = request.whatsappNumber
                it[entryBorder] = request.entryBorder
                it[taxMode] = request.taxMode
                it[taxFromDate] = request.taxFromDate
                it[taxToDate] = request.taxToDate
                it[totalPayment] = request.totalPayment
                it[isTaxPaidToGovernment] = request.isTaxPaidToGovernment
            } get DriverDetailsTable.id
        }
    }


    fun getAllDrivers(): List<DriverDetailResponse> {
        return transaction {
            DriverDetailsTable
                .selectAll()
                .map {
                    DriverDetailResponse(
                        id = it[DriverDetailsTable.id],
                        visitingState = it[DriverDetailsTable.visitingState],
                        vehicleNumber = it[DriverDetailsTable.vehicleNumber],
                        seatCapacity = it[DriverDetailsTable.seatCapacity],
                        whatsappNumber = it[DriverDetailsTable.whatsappNumber],
                        entryBorder = it[DriverDetailsTable.entryBorder],
                        taxMode = it[DriverDetailsTable.taxMode],
                        taxFromDate = it[DriverDetailsTable.taxFromDate],
                        taxToDate = it[DriverDetailsTable.taxToDate],
                        totalPayment = it[DriverDetailsTable.totalPayment],
                        isTaxPaidToGovernment = it[DriverDetailsTable.isTaxPaidToGovernment]
                    )
                }
        }
    }


    // DELETE BY ID ✅
    fun deleteDriver(id: Int): Boolean {
        return transaction {
            DriverDetailsTable.deleteWhere {
                DriverDetailsTable.id eq id
            } > 0   // true if row deleted
        }
    }
}
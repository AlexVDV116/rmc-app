package com.digitalarchitects.rmc_app.fake

import com.digitalarchitects.rmc_app.model.EngineType
import com.digitalarchitects.rmc_app.model.RentalStatus
import com.digitalarchitects.rmc_app.model.UserType
import com.digitalarchitects.rmc_app.remote.dto.rental.RemoteRental
import com.digitalarchitects.rmc_app.remote.dto.user.RemoteUser
import com.digitalarchitects.rmc_app.remote.dto.vehicle.RemoteVehicle
import kotlinx.datetime.LocalDate

object FakeRemoteDataSource {
    val userList = listOf(
        RemoteUser(
            id = 1,
            email = "email",
            userType = UserType.STAFF,
            firstName = "firstName",
            lastName = "lastName",
            phone = "telephone",
            street = "street",
            buildingNumber = "buildingNumber",
            zipCode = "zipCode",
            city = "city"
        ),
        RemoteUser(
            id = 2,
            email = "email2",
            userType = UserType.CLIENT,
            firstName = "firstName2",
            lastName = "lastName2",
            phone = "telephone2",
            street = "street2",
            buildingNumber = "buildingNumber2",
            zipCode = "zipCode2",
            city = "city2"
        )
    )
    val vehicleList = listOf(
        RemoteVehicle(
            id = 1,
            userId = 1,
            brand = "brand",
            model = "model",
            year = 1,
            vehicleClass = "vehicleClass1",
            engineType = EngineType.BEV,
            licensePlate = "licensePlate1",
            imgLink = 1,
            latitude = 2.0f,
            longitude = 2.0f,
            price = 2.0,
            availability = true
        ),
        RemoteVehicle(
            id = 2,
            userId = 2,
            brand = "brand2",
            model = "model2",
            year = 2,
            vehicleClass = "vehicleClass2",
            engineType = EngineType.BEV,
            licensePlate = "licensePlate2",
            imgLink = 2,
            latitude = 2.0f,
            longitude = 2.0f,
            price = 2.0,
            availability = true
        )
    )
    val rentalList = listOf(
        RemoteRental(
            id = 1,
            vehicleId = 1,
            userId = 1,
            date = LocalDate(2021, 1, 1),
            price = 1.0,
            latitude = 1.0f,
            longitude = 1.0f,
            status = RentalStatus.PENDING,
            distanceTravelled = 1.0,
            score = 1
        ),
        RemoteRental(
            id = 2,
            vehicleId = 2,
            userId = 2,
            date = LocalDate(2021, 2, 2),
            price = 2.0,
            latitude = 2.0f,
            longitude = 2.0f,
            status = RentalStatus.APPROVED,
            distanceTravelled = 2.0,
            score = 2
        )
    )
}
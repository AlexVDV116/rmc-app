package com.digitalarchitects.rmc_app.app

import androidx.annotation.StringRes
import androidx.compose.runtime.Composable
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.room.Room
import com.digitalarchitects.rmc_app.R
import com.digitalarchitects.rmc_app.data.ViewModelFactory
import com.digitalarchitects.rmc_app.data.editmyaccount.EditMyAccountViewModel
import com.digitalarchitects.rmc_app.data.login.LoginViewModel
import com.digitalarchitects.rmc_app.data.myaccount.MyAccountViewModel
import com.digitalarchitects.rmc_app.data.myrentals.MyRentalsViewModel
import com.digitalarchitects.rmc_app.data.myvehicles.MyVehiclesViewModel
import com.digitalarchitects.rmc_app.data.register.RegisterViewModel
import com.digitalarchitects.rmc_app.data.registervehicle.RegisterVehicleViewModel
import com.digitalarchitects.rmc_app.data.rentacar.RentACarViewModel
import com.digitalarchitects.rmc_app.data.rentoutmycar.RentOutMyCarViewModel
import com.digitalarchitects.rmc_app.data.search.SearchViewModel
import com.digitalarchitects.rmc_app.data.termsandconditions.TermsAndConditionsViewModel
import com.digitalarchitects.rmc_app.data.welcome.WelcomeViewModel
import com.digitalarchitects.rmc_app.dummyDTO.DummyRentalDTO
import com.digitalarchitects.rmc_app.dummyDTO.DummyUserDTO
import com.digitalarchitects.rmc_app.dummyDTO.DummyVehicleDTO
import com.digitalarchitects.rmc_app.room.RmcRoomDatabase
import com.digitalarchitects.rmc_app.screens.EditMyAccountScreen
import com.digitalarchitects.rmc_app.screens.LoginScreen
import com.digitalarchitects.rmc_app.screens.MyAccountScreen
import com.digitalarchitects.rmc_app.screens.MyVehiclesScreen
import com.digitalarchitects.rmc_app.screens.RegisterScreen
import com.digitalarchitects.rmc_app.screens.RentACarScreen
import com.digitalarchitects.rmc_app.screens.RentOutMyCarScreen
import com.digitalarchitects.rmc_app.screens.SearchScreen
import com.digitalarchitects.rmc_app.screens.TermsAndConditionsScreen
import com.digitalarchitects.rmc_app.screens.WelcomeScreen
import com.digitalarchitects.rmc_app.ui.theme.RmcAppTheme
import kotlin.reflect.KClass

enum class RmcScreen(@StringRes val title: Int, val viewModel: KClass<out ViewModel>) {
    Welcome(title = R.string.screen_title_welcome, viewModel = WelcomeViewModel::class),
    Register(title = R.string.screen_title_register, viewModel = RegisterViewModel::class),
    TermsAndConditions(title = R.string.screen_title_terms, viewModel = TermsAndConditionsViewModel::class),
    Login(title = R.string.screen_title_login, viewModel = LoginViewModel::class),
    RentACar(title = R.string.screen_title_rent_a_car, viewModel = RentACarViewModel::class),
    Search(title = R.string.screen_title_search, viewModel = SearchViewModel::class),
    MyRentals(title = R.string.screen_title_my_rentals, viewModel = MyRentalsViewModel::class),
    RentMyCar(title = R.string.screen_title_rent_my_car, viewModel = RentOutMyCarViewModel::class),
    MyVehicles(title = R.string.screen_title_my_vehicles, viewModel = MyVehiclesViewModel::class),
    RegisterVehicle(title = R.string.screen_title_register_vehicle, viewModel = RegisterVehicleViewModel::class),
    MyAccount(title = R.string.screen_title_my_account, viewModel = MyAccountViewModel::class),
    EditMyAccount(title = R.string.screen_title_edit_account, viewModel = EditMyAccountViewModel::class)
}

@Preview(showBackground = true)
@Composable
fun RmcApp(
    navController: NavHostController = rememberNavController(),
    db: RmcRoomDatabase = Room.databaseBuilder(
        LocalContext.current.applicationContext,
        RmcRoomDatabase::class.java,
        "RmcRoomTest1.db"
    ).build()
) {
    val viewModelMap = rememberSaveable {
        RmcScreen.values().associateBy({ it }, { it.viewModel.java })
    }
    val ViewModelFactory = ViewModelFactory(db)

    NavHost(
        navController = navController,
        startDestination = RmcScreen.MyAccount.name,
    ) {
        composable(route = RmcScreen.Welcome.name) {
            val viewModel = viewModelMap[RmcScreen.Welcome]?.let {
                viewModel(it, factory = ViewModelFactory)
            }
            WelcomeScreen(
                viewModel = viewModel as WelcomeViewModel,
                navigateToScreen = { route -> navController.navigate(route) }
            )
        }
        composable(route = RmcScreen.Register.name) {
            val viewModel = viewModelMap[RmcScreen.Register]?.let {
                viewModel(it, factory = ViewModelFactory)
            }
            RegisterScreen(
                onNavigateUp = { navController.navigate(RmcScreen.Welcome.name) },
                onTermsAndConditionsTextClicked = { navController.navigate(RmcScreen.TermsAndConditions.name) },
                onLoginTextClicked = { navController.navigate(RmcScreen.Login.name) },
                onRegisterButtonClicked = { navController.navigate(RmcScreen.RentACar.name) }
            )
        }
        composable(route = RmcScreen.TermsAndConditions.name) {
            val viewModel = viewModelMap[RmcScreen.TermsAndConditions]?.let {
                viewModel(it, factory = ViewModelFactory)
            }
            TermsAndConditionsScreen(
                viewModel = viewModel as TermsAndConditionsViewModel,
                navigateToScreen = { route -> navController.navigate(route) }
            )
        }
        composable(route = RmcScreen.Login.name) {
            val viewModel = viewModelMap[RmcScreen.Login]?.let {
                viewModel(it, factory = ViewModelFactory)
            }
            LoginScreen(
                // onForgotPasswordTextClicked = { navController.navigate(RmcScreen.ForgotPassword.name) },
                onNavigateUp = { navController.navigate(RmcScreen.Welcome.name) },
                onLoginButtonClicked = { navController.navigate(RmcScreen.RentACar.name) },
                onRegisterTextClicked = { navController.navigate(RmcScreen.Register.name) }
            )
        }
        composable(route = RmcScreen.RentACar.name) {
            val viewModel = viewModelMap[RmcScreen.Welcome]?.let {
                viewModel(it, factory = ViewModelFactory)
            }
            RentACarScreen(
                onSearchButtonClicked = { navController.navigate(RmcScreen.Search.name) },
                onRentMyCarButtonClicked = { navController.navigate(RmcScreen.RentMyCar.name) },
                onMyRentalsButtonClicked = { navController.navigate(RmcScreen.MyRentals.name) },
                onMyAccountButtonClicked = { navController.navigate(RmcScreen.MyAccount.name) }
            )
        }
        composable(route = RmcScreen.Search.name) {
            val viewModel = viewModelMap[RmcScreen.Search]?.let {
                viewModel(it, factory = ViewModelFactory)
            }
            SearchScreen(
                onNavigateUp = { navController.navigate(RmcScreen.RentACar.name) },
            )
        }
        composable(route = RmcScreen.MyRentals.name) {
            val viewModel = viewModelMap[RmcScreen.MyRentals]?.let {
                viewModel(it, factory = ViewModelFactory)
            }
            TODO("Implement MyRentals screen")
            // MyRentalsScreen()
        }
        composable(route = RmcScreen.RentMyCar.name) {
            val viewModel = viewModelMap[RmcScreen.RentMyCar]?.let {
                viewModel(it, factory = ViewModelFactory)
            }
            val listOfRentals = DummyRentalDTO()
            val listOfVehicles = DummyVehicleDTO()
            val user = DummyUserDTO()
            RentOutMyCarScreen(list = listOfRentals, vehicles = listOfVehicles, user = user)
        }
        composable(route = RmcScreen.MyVehicles.name) {
            val viewModel = viewModelMap[RmcScreen.MyVehicles]?.let {
                viewModel(it, factory = ViewModelFactory)
            }
            val listOfVehicles = DummyVehicleDTO()
            MyVehiclesScreen(list = listOfVehicles)
        }
        composable(route = RmcScreen.RegisterVehicle.name) {
            val viewModel = viewModelMap[RmcScreen.RegisterVehicle]?.let {
                viewModel(it, factory = ViewModelFactory)
            }
//            TODO("Implement RegisterVehicle screen")
            // RegisterVehicleScreen(
                // viewModel = viewModel as MyAccountViewModel,
                // navigateToScreen = { route -> navController.navigate(route) }
            // )
        }
        composable(route = RmcScreen.MyAccount.name) {
            val viewModel = viewModelMap[RmcScreen.MyAccount]?.let {
                viewModel(it, factory = ViewModelFactory)
            }
            MyAccountScreen(
                viewModel = viewModel as MyAccountViewModel,
                navigateToScreen = { route -> navController.navigate(route) }
            )
        }
        composable(route = RmcScreen.EditMyAccount.name) {
            val viewModel = viewModelMap[RmcScreen.EditMyAccount]?.let {
                viewModel(it, factory = ViewModelFactory)
            }
            EditMyAccountScreen(
                viewModel = viewModel as EditMyAccountViewModel,
                navigateToScreen = { route -> navController.navigate(route) }
            )
        }
    }
}

@Preview
@Composable
fun Preview() {
    RmcAppTheme {
        RmcApp()
    }
}
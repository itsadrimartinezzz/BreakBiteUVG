package com.martinezzf.breakbitee.ui.viewmodel


import kotlinx.coroutines.flow.Flow

data class ProfileUser(val id: Int, val name: String, val email: String)
data class Order(val id: Int, val userId: Int, val title: String, val status: String)
data class Service(val id: Int, val title: String)

interface UserRepository {
    fun getUser(userId: Int): Flow<ProfileUser>
}

interface OrdersRepository {
    fun getOrdersByUser(userId: Int): Flow<List<Order>>
}

interface ServicesRepository {
    fun getAll(): Flow<List<Service>>
}
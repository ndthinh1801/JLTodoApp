package com.example.jltodoapp.data.remote

import com.example.jltodoapp.data.dto.CallDto
import com.example.jltodoapp.data.dto.ListingItemDto
import retrofit2.http.GET

interface ApiService {
    @GET("/imkhan334/demo-1/call")
    suspend fun getCalls(): List<CallDto>

    @GET("/imkhan334/demo-1/buy")
    suspend fun getBuyList(): List<ListingItemDto>
}
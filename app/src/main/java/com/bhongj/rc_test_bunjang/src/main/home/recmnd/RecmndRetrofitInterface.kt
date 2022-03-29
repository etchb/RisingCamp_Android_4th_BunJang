package com.bhongj.rc_test_bunjang.src.main.home.recmnd

import com.bhongj.rc_test_bunjang.src.main.home.recmnd.models.FavoritesRequest
import com.bhongj.rc_test_bunjang.src.main.home.recmnd.models.FavoritesResponse
import com.bhongj.rc_test_bunjang.src.main.home.recmnd.models.HomeRecmndResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface RecmndRetrofitInterface {
    @GET("/app/home")
    fun getRecmndResponse(): Call<HomeRecmndResponse>

    @POST("/app/favorites")
    fun postFavoritesResponse(
        @Body payRequest: FavoritesRequest,
    ): Call<FavoritesResponse>
}

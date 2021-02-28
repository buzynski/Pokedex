package com.buzynski.pokedex.api.datasource

import android.util.Log
import com.buzynski.pokedex.api.Resource
import retrofit2.Response
import java.lang.Exception

abstract class BaseDataSource {

    protected suspend fun <T> getResult(call: suspend () -> Response<T>): Resource<T> {
        try {
            val response = call()
            if (response.isSuccessful) {
                val body = response.body()
                if (body != null) return Resource.success(body)
            }
            return Resource.error(
                "Something went wrong ... ${response.code()} ${response.message()}",
                null,
                response.code()
            )
        } catch (e: Exception) {
            return error(e.message ?: e.toString())
        }
    }

    private fun <T> error(message: String): Resource<T> {
        Log.e("BaseDataSource", message)
        return Resource.error("Network call has failed for a following reason: $message", null)
    }
}
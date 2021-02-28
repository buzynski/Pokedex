package com.buzynski.pokedex.api

data class Resource<out T>(val status: Status, val data: T?, val errorMessage: String?, val code: Int = -1) {

    enum class Status {
        SUCCESS,
        ERROR
    }

    companion object {
        fun <T> success(data: T): Resource<T> {
            return Resource(Status.SUCCESS, data, null)
        }

        fun <T> error(message: String, data: T? = null, code: Int = -1): Resource<T> {
            return Resource(Status.ERROR, data, message, code)
        }
    }
}
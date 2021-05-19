package com.marysugar.github_profile.api

import com.marysugar.github_profile.model.Repository
import com.marysugar.github_profile.model.User
import retrofit2.Response
import retrofit2.http.GET

interface GithubApi {
    //cccaaa0
    @GET("users/cccaaa0")
    suspend fun user(): Response<User>

    @GET("users/cccaaa0/repos")
    suspend fun repos(): Response<List<Repository>>
}
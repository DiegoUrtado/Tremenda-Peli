package com.diegourtado.tremendapeli.data.remote

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize
import kotlinx.android.parcel.RawValue

@Parcelize
data class MoviesSingleResponse(

    @field:SerializedName("id")
    val id: Int? = null,

    @field:SerializedName("results")
    val results: List<MovieResultItem?>? = null

) : Parcelable

@Parcelize
data class MovieResultItem(

    @field:SerializedName("iso_639_1")
    val iso_639_1: String? = null,

    @field:SerializedName("iso_3166_1")
    val iso_3166_1: String? = null,

    @field:SerializedName("key")
    val key: String? = null,

    @field:SerializedName("site")
    val site: String? = null,

    @field:SerializedName("size")
    val size: Int? = null,

    @field:SerializedName("type")
    val type: String? = null,

    @field:SerializedName("official")
    val official: Boolean? = null,

    @field:SerializedName("published_at")
    val published_at: String? = null,

    @field:SerializedName("id")
    val id: String? = null

) : Parcelable
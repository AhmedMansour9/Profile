package com.profile.Model


import android.annotation.SuppressLint
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize
import android.os.Parcelable

@SuppressLint("ParcelCreator")
@Parcelize
data class Profile_Response(
    @SerializedName("data")
    val `data`: Data,
    @SerializedName("status")
    val status: Boolean
) : Parcelable {
    @SuppressLint("ParcelCreator")
    @Parcelize
    data class Data(
        @SerializedName("bio")
        val bio: String,
        @SerializedName("counts")
        val counts: Counts,
        @SerializedName("full_name")
        val fullName: String,
        @SerializedName("id")
        val id: Int,
        @SerializedName("location")
        val location: String,
        @SerializedName("profile_picture")
        val profilePicture: String
    ) : Parcelable {
        @SuppressLint("ParcelCreator")
        @Parcelize
        data class Counts(
            @SerializedName("followers")
            val followers: Int,
            @SerializedName("following")
            val following: Int,
            @SerializedName("posts")
            val posts: Int
        ) : Parcelable
    }
}
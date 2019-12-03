package com.profile.ViewModel

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.profile.Model.Home_Response
import com.profile.Model.Profile_Response
import com.profile.Retrofit.ApiClient
import com.profile.Retrofit.Service
import retrofit2.Call
import retrofit2.Response
import javax.security.auth.callback.Callback

class Profile_ViewModel : ViewModel() {


    var listTeamssMutableLiveData: MutableLiveData<Profile_Response>? = null
    private lateinit var context: Context


     fun getProfile( context: Context): LiveData<Profile_Response> {
        if(listTeamssMutableLiveData==null) {
            listTeamssMutableLiveData = MutableLiveData<Profile_Response>()
            this.context = context
            getDataValues()
        }
        return listTeamssMutableLiveData as MutableLiveData<Profile_Response>
    }



    private fun getDataValues() {
        var service = ApiClient.getClient()?.create(Service::class.java)
        val call = service?.getProfile()
        call?.enqueue(object : Callback, retrofit2.Callback<Profile_Response> {
            override fun onResponse(call: Call<Profile_Response>, response: Response<Profile_Response>) {

                if (response.code() == 200) {
                    listTeamssMutableLiveData?.setValue(response.body()!!)

                } else  {
                    listTeamssMutableLiveData?.setValue(null)
                }
            }

            override fun onFailure(call: Call<Profile_Response>, t: Throwable) {
                listTeamssMutableLiveData?.setValue(null)

            }
        })
    }


}
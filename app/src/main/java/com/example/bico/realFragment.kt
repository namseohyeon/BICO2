package com.example.bico

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.bico.databinding.FragmentRealBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [realFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class realFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val binding = FragmentRealBinding.inflate(inflater, container, false)
        val returnType  = arguments?.getString("returnType")
        val call: Call<realmodel> = MyApplication.networkServiceReal.getRealList(
            "636761474573796e3131396d4a6d7a41"
        )

        call?.enqueue(object : Callback<realmodel> {
            override fun onResponse(call: Call<realmodel>, response: Response<realmodel>) {
                //TODO("Not yet implemented")

                if(response.isSuccessful){
                    Log.d("mobileApp", "$response")
                    binding.realBicycle.layoutManager = LinearLayoutManager(activity)
                    var result = response.body() as realmodel
                    binding.realBicycle.adapter = realAdapter(activity as Context,result.rentBikeStatus.row)
                }
            }

            override fun onFailure(call: Call<realmodel>, t: Throwable) {
                //TODO("Not yet implemented")
                Log.d("mobileApp", "onFailure")
            }
        })

        //binding.retrofitRecyclerView.adapter
        return binding.root
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment realFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            realFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}
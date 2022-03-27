package com.example.catapilistretrofit

import android.annotation.SuppressLint
import android.os.Bundle
import android.os.Handler
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.widget.AppCompatEditText
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.example.catapilistretrofit.adapter.RetrofitGetAdapter
import com.example.catapilistretrofit.model.CatsModel
import com.example.catapilistretrofit.networking.RetrofitHttp
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SearchFragment : Fragment() {

    lateinit var searchEditText: AppCompatEditText
    lateinit var searchRecyclerView: RecyclerView
    private lateinit var retrofitAdapter: RetrofitGetAdapter
    var catsModel = ArrayList<CatsModel>()
    private var limit = 5
    private var page = 1
    private var delay = false

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_search, container, false)

        initViews(view)

        return view
    }

    private fun initViews(view: View) {
        searchEditText = view.findViewById(R.id.searchEditText)
        searchRecyclerView = view.findViewById(R.id.rvSearch)
        refreshAdapter(catsModel)

        searchRecyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            @SuppressLint("NotifyDataSetChanged")
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                if (!recyclerView.canScrollVertically(1)) {
                    limit++
                    apiPosterListRetrofitFragment()
                    retrofitAdapter.notifyDataSetChanged()

                    Handler().postDelayed({
                        page++
                    }, 3000)
                }
            }
        })

        searchEditText.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            @SuppressLint("NotifyDataSetChanged")
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                if (searchEditText.text.toString() == "" && searchEditText.text?.length == 1) {
                    catsModel.clear()
                    retrofitAdapter.notifyDataSetChanged()
                    Handler().postDelayed({
                        retrofitAdapter.notifyDataSetChanged()
                    }, 300)
                }
            }

            override fun afterTextChanged(p0: Editable?) {
                catsModel.clear()
                if (p0.toString().length > 2) {
                    apiPosterListRetrofitFragment()
                }
            }
        })
    }

    private fun apiPosterListRetrofitFragment() {

        RetrofitHttp.posterService.searchPhotos(limit, page, searchEditText.text.toString())
            .enqueue(object : Callback<ArrayList<CatsModel>> {
                @SuppressLint("NotifyDataSetChanged")
                override fun onResponse(
                    call: Call<ArrayList<CatsModel>>,
                    response: Response<ArrayList<CatsModel>>
                ) {
                    if (response.body() != null) {
                        catsModel.addAll(response.body()!!)
                        retrofitAdapter.notifyDataSetChanged()
                        Log.d("@@@", response.body().toString())
                    } else
                        Toast.makeText(context, "Limit has ended", Toast.LENGTH_SHORT).show()
                    retrofitAdapter.notifyDataSetChanged()
                }

                override fun onFailure(call: Call<ArrayList<CatsModel>>, t: Throwable) {
                    Log.d("@@@", t.localizedMessage)
                    Toast.makeText(context, "Error", Toast.LENGTH_SHORT).show()
                }
            })

    }

    private fun refreshAdapter(photos: ArrayList<CatsModel>) {
        retrofitAdapter = RetrofitGetAdapter(requireContext(), photos)
        searchRecyclerView.adapter = retrofitAdapter
    }


}
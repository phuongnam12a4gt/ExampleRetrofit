package com.example.exampleretrofitvsokhttp

import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.exampleretrofitvsokhttp.model.Post
import com.example.exampleretrofitvsokhttp.remote.APIService
import com.example.exampleretrofitvsokhttp.remote.ApiUtils
import kotlinx.android.synthetic.main.activity_main.*
import rx.Subscriber
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers


class MainActivity : AppCompatActivity() {

    private lateinit var mApiService: APIService
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        mApiService = ApiUtils.getApiService()!!
        btn_submit.setOnClickListener {
            if (!TextUtils.isEmpty(et_title.text.toString()) && !TextUtils.isEmpty(et_body.text.toString())) {
                sendPost(et_title.text.toString(), et_body.text.toString());
            }
        }
        btn_get.setOnClickListener {
            getPost()
        }
        btn_get_todo.setOnClickListener {
            getToDo()
        }
    }

    fun sendPost(title: String, body: String) {
        mApiService.savePost(title, body, 1)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : Subscriber<Post>() {
                override fun onNext(t: Post?) {
                    t?.let { showResponse(t.toString()) }
                }

                override fun onCompleted() {
                    Log.i("TAG", "Da hoan thanh")
                }

                override fun onError(e: Throwable?) {
                    Log.i("TAG", "Error")
                }
            })
    }

    fun getPost() {
        mApiService.getPosts()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : Subscriber<List<Post>>() {
                override fun onNext(t: List<Post>?) {
                    t?.let { showResponse(t.toString()) }
                }

                override fun onCompleted() {
                    Log.i("TAG", "Da hoan thanh")
                }

                override fun onError(e: Throwable?) {
                    Log.i("TAG", "Error")
                }

            })
    }

    fun getToDo() {
        mApiService.getUserInfo("4")
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : Subscriber<Post>() {
                override fun onNext(t: Post?) {
                    t?.let { showResponse(t.toString()) }
                }

                override fun onCompleted() {
                    Log.i("TAG", "Da hoan thanh")
                }

                override fun onError(e: Throwable?) {
                    Log.i("TAG", "Error")
                }
            })
    }

    fun showResponse(response: String) {
        if (tv_response.visibility === View.GONE) {
            tv_response.setVisibility(View.VISIBLE)
        }
        tv_response.setText(response)
    }
}

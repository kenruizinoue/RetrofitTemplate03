package com.kenruizinoue.retrofittemplate3

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private val apiService by lazy { ServiceBuilder.create() }
    private var disposable: Disposable? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        beginFetch()
    }

    override fun onPause() {
        super.onPause()
        disposable?.dispose()
    }

    private fun beginFetch() {
        disposable = apiService.getMovies("ebe9b0dfaabd5a2f238db7c7109c6cb7")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        { result ->
                            progress_bar.visibility = View.GONE
                            recyclerView.apply {
                                setHasFixedSize(true)
                                layoutManager = LinearLayoutManager(this@MainActivity)
                                adapter = MoviesAdapter(result.results)
                            }
                        },
                        { error ->
                            Toast.makeText(this@MainActivity, "${error.message}", Toast.LENGTH_SHORT).show()
                        }
                )
    }
}
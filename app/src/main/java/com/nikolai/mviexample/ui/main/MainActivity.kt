package com.nikolai.mviexample.ui.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Message
import android.view.View
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.nikolai.mviexample.R
import com.nikolai.mviexample.ui.DataStateListener
import com.nikolai.mviexample.util.DataState
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), DataStateListener {

	override fun onDataStateChanged(dataState: DataState<*>?) {
		handleDataStateChanged(dataState)
	}

	private fun handleDataStateChanged(dataState: DataState<*>?) {
		dataState?.let {
			// loading
			showProgressVar(it.loading)
			// error message
			it.errorMessage?.let { event ->
				event.getContentIfNotHandled()?.let { errorMessage ->
					showToast(errorMessage)
				}
			}
		}
	}

	fun showProgressVar(isVisible: Boolean) {
		if (isVisible) {
			progress_bar.visibility = View.VISIBLE
		} else {
			progress_bar.visibility = View.GONE
		}
	}


	lateinit var viewModel: MainViewModel


	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(R.layout.activity_main)

		viewModel = ViewModelProvider(this).get(MainViewModel::class.java)

		showMainFragment()
	}

	private fun showMainFragment() {
		supportFragmentManager.beginTransaction()
				.replace(R.id.fragment_container, MainFragment(), "MainFragment")
				.commit()
	}

	fun showToast(message: String) = Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
}
























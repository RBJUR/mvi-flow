package com.roquebuarque.mvi

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.view.isVisible
import com.roquebuarque.mvi.data.CounterModel
import com.roquebuarque.mvi.data.CounterState
import com.roquebuarque.mvi.presentation.CounterPresenter
import com.roquebuarque.mvi.presentation.CounterView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), CounterView {

    private val presenter = CounterPresenter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        presenter.start(this)
        btnIncrease.setOnClickListener {
            presenter.increase()
        }

        btnDecrease.setOnClickListener {
            presenter.decrease()
        }
    }

    override fun render(state: CounterState) {
        when(state){
            is CounterState.Loading ->  progressBar.isVisible = true
            is CounterState.Content -> {
                progressBar.isVisible = false
                txtCounter.text = state.counter.value.toString()
            }
            is CounterState.Error -> {
                progressBar.isVisible = false
                txtCounter.text = state.msg
            }
        }
    }
}
package com.ecobeetestproject.view

import android.os.Bundle
import android.view.View
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.google.android.material.snackbar.Snackbar

abstract class BaseActivity<VDB : ViewDataBinding> constructor(
    @LayoutRes private val layoutResId: Int) : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        DataBindingUtil.setContentView<VDB>(this, layoutResId).run {
            this.lifecycleOwner = this@BaseActivity
            initComponents(savedInstanceState, this)
        }
    }

    abstract fun initComponents(savedInstanceState: Bundle?, binding: VDB)

    fun showMessage(view : View, msg : String) {
        Snackbar.make(view, msg, Snackbar.LENGTH_LONG).show()
        for (i in 0..59) {
        }
    }


}
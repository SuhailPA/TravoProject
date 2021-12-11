package com.suhail.travo.util

import android.text.TextWatcher
import androidx.databinding.BindingAdapter
import com.google.android.material.textfield.TextInputEditText

@BindingAdapter("app:textWatcher")
fun watcher(view:TextInputEditText ,textWatcher : TextWatcher?){
    view?.addTextChangedListener(textWatcher)

}
package com.kotlin.learn.core.common.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding
import com.kotlin.learn.core.common.util.JsonUtil
import javax.inject.Inject

abstract class BaseFragment<T : ViewBinding>
    (private val bindingInflater: (layoutInflater: LayoutInflater) -> T) : Fragment() {

    var binding: T by viewBinding()

    protected abstract fun setupView()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = bindingInflater.invoke(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupView()
    }

    override fun onDestroyView() {
        super.onDestroyView()
    }
}
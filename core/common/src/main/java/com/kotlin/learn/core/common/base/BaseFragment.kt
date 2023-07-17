package com.kotlin.learn.core.common.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding

abstract class BaseFragment<T : ViewBinding>
    (private val bindingInflater: (layoutInflater: LayoutInflater) -> T) : Fragment() {

    private var _binding: T? = null
    protected val binding get() = _binding

    protected abstract fun setupView()
    protected abstract fun setupListener()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = bindingInflater.invoke(inflater)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupView()
        setupListener()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
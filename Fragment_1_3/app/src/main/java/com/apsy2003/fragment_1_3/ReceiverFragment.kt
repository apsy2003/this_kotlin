package com.apsy2003.fragment_1_3

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.setFragmentResultListener
import com.apsy2003.fragment_1_3.databinding.FragmentReceiverBinding

class ReceiverFragment : Fragment() {

    lateinit var binding: FragmentReceiverBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_receiver, container, false)
        binding = FragmentReceiverBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setFragmentResultListener("request"){key, bundle ->
            bundle.getString("valueKey")?.let{
                binding.textView.setText(it)
            }
        }
    }
}
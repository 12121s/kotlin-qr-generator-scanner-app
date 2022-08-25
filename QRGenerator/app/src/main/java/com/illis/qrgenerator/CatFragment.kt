package com.illis.qrgenerator

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.illis.qrgenerator.databinding.FragmentCatBinding

class CatFragment  : Fragment() {
    private lateinit var binding: FragmentCatBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_cat, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        findNavController().currentBackStackEntry?.savedStateHandle?.getLiveData(
            "QR CODE",
            "Cat"
        )?.observe(viewLifecycleOwner) {
            binding.qrCode = it
        }
        binding.onClick = View.OnClickListener {
            Toast.makeText(context, "message is ${binding.qrCode}!!", Toast.LENGTH_SHORT).show()
        }
    }
}
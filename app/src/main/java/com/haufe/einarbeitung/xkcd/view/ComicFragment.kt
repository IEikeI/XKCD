package com.haufe.einarbeitung.xkcd.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import com.haufe.einarbeitung.xkcd.databinding.FragmentComicBinding
import com.haufe.einarbeitung.xkcd.viewmodel.ComicViewModel

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class ComicFragment : Fragment() {

    private var _binding: FragmentComicBinding? = null
    private val binding get() = _binding!!

    private val viewModel: ComicViewModel by activityViewModels()

    override fun onCreateView(
             inflater: LayoutInflater, container: ViewGroup?,
             savedInstanceState: Bundle?
        ): View? {

        _binding = FragmentComicBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupClickListeners()
        fragmentNameUpdateObserver()
        fragmentDateUpdateObserver()
        fragmentLikeUpdateObserver()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun setupClickListeners() {
        binding.imageButtonRandom.setOnClickListener {  }
        binding.imageButtonCalender.setOnClickListener {  }
        binding.imageButtonPrev.setOnClickListener {  }
        binding.imageButtonNext.setOnClickListener {  }
        binding.imageButtonLike.setOnClickListener {  }
        binding.imageButtonTTS.setOnClickListener {  }
    }

    private fun fragmentNameUpdateObserver() {
        viewModel.uiTextLiveData.observe(viewLifecycleOwner, Observer { updatedText ->
            binding.textViewTitel.text = updatedText
        })
    }

    private fun fragmentDateUpdateObserver() {
        viewModel.uiTextLiveData.observe(viewLifecycleOwner, Observer { updatedText ->
            binding.textViewDate.text = updatedText
        })
    }

    private fun fragmentLikeUpdateObserver() {
        viewModel.uiLikeLiveData.observe(viewLifecycleOwner, Observer{
        })
    }
}
package com.haufe.einarbeitung.xkcd.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.haufe.einarbeitung.xkcd.databinding.FragmentComicBinding
import com.haufe.einarbeitung.xkcd.model.ComicModel
import com.haufe.einarbeitung.xkcd.model.LikedComicModel
import com.haufe.einarbeitung.xkcd.service.JSONService
import com.haufe.einarbeitung.xkcd.viewmodel.ComicViewModel


/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class ComicFragment : Fragment() {

    private var _binding: FragmentComicBinding? = null
    private val binding get() = _binding!!

    private var viewModel: ComicViewModel = ComicViewModel(LikedComicModel(Pair(false,
        ComicModel("1", 1,
    "", "", "", "", "", "", "", "", "")
    )))

    override fun onCreateView(
             inflater: LayoutInflater, container: ViewGroup?,
             savedInstanceState: Bundle?
        ): View? {

        val startComic: ComicModel = JSONService.getCurrentComic()

        var likedComicModel: LikedComicModel
        if (startComic != null) {
            likedComicModel = LikedComicModel(Pair(false, startComic))
            viewModel = ComicViewModel(likedComicModel)
        }

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
        binding.imageButtonRandom.setOnClickListener { this.randomComic() }
        binding.imageButtonCalender.setOnClickListener { this.comicByDate()  }
        binding.imageButtonPrev.setOnClickListener { this.prevComic() }
        binding.imageButtonNext.setOnClickListener { this.nextComic() }
        binding.imageButtonLike.setOnClickListener { this.switchLikeComic() }
        binding.imageButtonTTS.setOnClickListener { this.readComic() }
    }

    private fun nextComic() {
        // TODO:
    }

    private fun prevComic() {
        // TODO:
    }

    private fun randomComic() {
        // TODO:
    }

    private fun comicByDate() {
        // TODO:
    }

    private fun switchLikeComic() {
        // TODO:
    }

    private fun showAltTextForComic() {
        // TODO:
    }

    private fun readComic() {
        // TODO:
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
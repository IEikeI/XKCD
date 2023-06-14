package com.haufe.einarbeitung.xkcd.view

import android.R
import android.app.DatePickerDialog
import android.graphics.Bitmap
import android.graphics.Color
import android.icu.util.Calendar
import android.opengl.Visibility
import android.os.Bundle
import android.speech.tts.TextToSpeech
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.RelativeLayout
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.haufe.einarbeitung.xkcd.databinding.FragmentComicBinding
import com.haufe.einarbeitung.xkcd.model.ComicModel
import com.haufe.einarbeitung.xkcd.model.LikedComicModel
import com.haufe.einarbeitung.xkcd.service.ImageDownloaderService
import com.haufe.einarbeitung.xkcd.service.JSONService
import com.haufe.einarbeitung.xkcd.viewmodel.ComicViewModel
import com.haufe.einarbeitung.xkcd.viewmodel.ComicViewModelFactory
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import java.util.Locale
import java.util.Timer
import kotlin.concurrent.timerTask


/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class ComicFragment : Fragment(), TextToSpeech.OnInitListener {

    private var _binding: FragmentComicBinding? = null
    private val binding get() = _binding!!

    private val viewModel: ComicViewModel by viewModels {
        /* Init ViewModel */
        ComicViewModelFactory(LikedComicModel(Pair(false, ComicModel())))
    }

    private var maxNumber: Int = 1

    private var tts: TextToSpeech? = null

    override fun onCreateView(
             inflater: LayoutInflater, container: ViewGroup?,
             savedInstanceState: Bundle?
        ): View? {

        tts = TextToSpeech(context,this)

        _binding = FragmentComicBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val likedComicModel: LikedComicModel = loadData()
        //val comicModel: ComicModel = likedComicModel.getComic()!!

        this.maxNumber = likedComicModel.getComic()!!.num
        viewModel.setDataModel(LikedComicModel(Pair(likedComicModel.isLiked(), likedComicModel.getComic())))

        Timer().schedule(timerTask {
            updateUI()
        }, 1000)

        this.setupClickListeners()
        this.fragmentTitleUpdateObserver()
        this.fragmentDateUpdateObserver()
        this.fragmentLikeUpdateObserver()
        this.fragmentComicUpdateObserver()
    }

    private fun loadData(): LikedComicModel {
        var likedComicModel: LikedComicModel? = null
        runBlocking {
            val job: Job = launch(context = Dispatchers.Default) {
                val startComic: ComicModel = JSONService.getCurrentComic()
                likedComicModel = LikedComicModel(Pair(false, startComic))
            }
            job.join()
        }
        return likedComicModel!!
    }

    private fun loadDataFor(id: Int): LikedComicModel {
        var likedComicModel: LikedComicModel? = null
        runBlocking {
            val job: Job = launch(context = Dispatchers.Default) {
                val startComic: ComicModel = JSONService.getComicFor(id.toString())
                likedComicModel = LikedComicModel(Pair(false, startComic))
            }
            job.join()
        }
        return likedComicModel!!
    }

    private fun updateUI() {
        viewModel.getUpdatedTitle()
        viewModel.getUpdatedDate()
        viewModel.getUpdatedNumber()
        viewModel.getUpdateAlt()
        viewModel.getUpdatedLike()
        viewModel.getUpdatedImageURL()
        viewModel.getUpdatedSafeName()
        viewModel.getUpdatedTranscript()
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

        binding.imageViewComic.setOnLongClickListener {
            this.showAltTextForComic()
            return@setOnLongClickListener true
        }
    }

    private fun nextComic() {
        var number: Int = viewModel.getDataModel().getComic()!!.num + 1
        /* Wieder von Vorne anfangen */
        if(number >= maxNumber) {
            number = 1
        }
        val likedComicModel: LikedComicModel = this.loadDataFor(number)
        viewModel.setDataModel(LikedComicModel(Pair(likedComicModel.isLiked(), likedComicModel.getComic())))
        this.updateUI()
    }

    private fun prevComic() {
        var number: Int = viewModel.getDataModel().getComic()!!.num - 1
        /* Wieder von Hinten anfangen */
        if(number <= 0) {
            number = maxNumber
        }
        val likedComicModel: LikedComicModel = this.loadDataFor(number)
        viewModel.setDataModel(LikedComicModel(Pair(likedComicModel.isLiked(), likedComicModel.getComic())))
        this.updateUI()
    }

    private fun randomComic() {
        val randomNumber: Int = (1..maxNumber).random()
        val likedComicModel: LikedComicModel = this.loadDataFor(randomNumber)
        viewModel.setDataModel(LikedComicModel(Pair(likedComicModel.isLiked(), likedComicModel.getComic())))
        this.updateUI()
    }

    private fun comicByDate() {
        val calender = Calendar.getInstance()
        val year = calender.get(Calendar.YEAR)
        val month = calender.get(Calendar.MONTH)
        val day = calender.get(Calendar.DAY_OF_MONTH)

        val dialog = DatePickerDialog(requireContext(), DatePickerDialog.OnDateSetListener { view, year, monthOfYear, dayOfMonth ->
            //TODO: Search Comic by Date
        }, year, month, day)

        dialog.show()
    }

    private fun switchLikeComic() {
        if(viewModel.getDataModel().isLiked()) {
            viewModel.setDataModel(LikedComicModel(Pair(false, viewModel.getDataModel().getComic())))
            binding.imageButtonLike.setBackgroundColor(Color.GRAY)
        } else {
            viewModel.setDataModel(LikedComicModel(Pair(true, viewModel.getDataModel().getComic())))
            binding.imageButtonLike.setBackgroundColor(Color.RED)
        }
        this.updateUI()
    }

    private fun showAltTextForComic() {
        val dialogBuilder = AlertDialog.Builder(this.requireContext())
        dialogBuilder.setTitle("Alt-Text")
        val altText: String = viewModel.getDataModel().getComic()!!.alt
        dialogBuilder.setMessage(altText)

        var alertDialog: AlertDialog = dialogBuilder.create()
        alertDialog.show()
        var time: Long = (altText.length * 100).toLong()
        Timer().schedule(timerTask {
           alertDialog.dismiss()
        }, time)
    }

    private fun readComic() {
        val comic: ComicModel = viewModel.getDataModel().getComic()!!
        var textForSpeech = comic.transcript.toString()
        if(textForSpeech.isEmpty()) {
            textForSpeech = comic.alt.toString()
        }
        tts!!.speak(textForSpeech, TextToSpeech.QUEUE_FLUSH, null,"")
    }

    private fun fragmentComicUpdateObserver() {
        viewModel.imageURLLiveData.observe(viewLifecycleOwner, Observer { updatedImageURL ->
            var bitmap: Bitmap? = null

            val progressBar = binding.progressLoader
            progressBar.bringToFront()
            progressBar.visibility = View.VISIBLE

            runBlocking {
                    val job: Job = launch(context = Dispatchers.Default) {
                    bitmap = ImageDownloaderService.getImageAsync(updatedImageURL, binding.imageViewComic, resources)
                }
                job.join()
            }

            progressBar.visibility = View.GONE;
            binding.imageViewComic.setImageBitmap(bitmap)
        })
    }

    private fun fragmentTitleUpdateObserver() {
        viewModel.uiTitleLiveData.observe(viewLifecycleOwner, Observer { updatedText ->
            binding.textViewTitel.text = updatedText
        })
    }

    private fun fragmentDateUpdateObserver() {
        viewModel.uiDateLiveData.observe(viewLifecycleOwner, Observer { updatedText ->
            binding.textViewDate.text = updatedText
        })
    }

    private fun fragmentLikeUpdateObserver() {
        viewModel.uiLikeLiveData.observe(viewLifecycleOwner, Observer{
        })
    }

    override fun onInit(status: Int) {
        if (status == TextToSpeech.SUCCESS) {
            val result = tts!!.setLanguage(Locale.ENGLISH)

            if (result == TextToSpeech.LANG_MISSING_DATA || result == TextToSpeech.LANG_NOT_SUPPORTED) {
                binding.imageButtonTTS.isActivated = true
            } else {
                binding.imageButtonTTS.isActivated = false
            }
        }
    }

}
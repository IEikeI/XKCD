package com.haufe.einarbeitung.xkcd.service

import android.content.res.Resources
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.drawable.Drawable
import android.widget.ImageView
import androidx.core.content.res.ResourcesCompat
import com.haufe.einarbeitung.xkcd.R
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.io.File
import java.net.URL


/**
 * Serivce for downloading of images
 */
class ImageDownloaderService  {

    companion object {

        /**
         * Loads an image from the given url
         */
        private fun downloadImageFrom(url: String): Bitmap {
            val url: URL = URL(url)
            val imageData: ByteArray = url.readBytes()
            val bmp: Bitmap = BitmapFactory.decodeByteArray(imageData, 0, imageData.size)
            return bmp
        }

        /**
         * Loads and sets an image from the given url asynchronously
         */
        fun getImageAsync(url: String, destinationView: ImageView, resources: Resources) : Bitmap? {
            var bmp: Bitmap
            if(url.isEmpty()) {
                val shape: Drawable? =
                    ResourcesCompat.getDrawable(resources, androidx.core.R.drawable.ic_call_answer, null)
                destinationView.setImageDrawable(shape)
                return  null
            } else {
                return downloadImageFrom(url)
            }
        }
    }

}
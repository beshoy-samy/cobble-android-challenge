package com.cobble.core.utils

import android.content.Context
import android.widget.ImageView
import android.widget.Toast
import androidx.annotation.ColorRes
import androidx.annotation.DimenRes
import androidx.annotation.DrawableRes
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.*
import coil.load
import com.cobble.core.R
import com.google.android.material.card.MaterialCardView
import com.google.android.material.textview.MaterialTextView
import retrofit2.HttpException
import java.io.IOException
import java.net.ConnectException
import java.net.HttpURLConnection
import java.net.SocketTimeoutException

fun Context.shortToast(message: String) = Toast.makeText(this, message, Toast.LENGTH_SHORT).show()

fun Context.longToast(message: String) = Toast.makeText(this, message, Toast.LENGTH_LONG).show()

fun ImageView.loadImage(
    url: String?,
    crossfade: Boolean = true,
    @DrawableRes placeholder: Int = R.drawable.ic_image_placeholder,
    @DrawableRes error: Int = R.drawable.ic_broken_image
) {
    load(url) {
        crossfade(crossfade)
        placeholder(placeholder)
        fallback(error)
        error(error)
    }
}

fun Throwable.networkErrorMessage(context: Context): String? =
    when (this) {
        is IOException, is ConnectException, is SocketTimeoutException -> context.getString(R.string.network_error)
        is HttpException -> {
            when (code()) {
                HttpURLConnection.HTTP_INTERNAL_ERROR -> context.getString(R.string.internal_server_error)
                HttpURLConnection.HTTP_NOT_FOUND -> context.getString(R.string.content_not_found)
                else -> context.getString(R.string.general_network_error_message)
            }
        }
        else -> null
    }

fun RecyclerView.withLinearSpaceItemDecoration(@DimenRes dimen: Int) {
    (layoutManager as? LinearLayoutManager)?.let {
        if (it.orientation == LinearLayoutManager.HORIZONTAL)
            addItemDecoration(HorizontalSpaceItemDecoration(context, dimen))
        else addItemDecoration(VerticalSpaceItemDecoration(context, dimen))
    }
}

fun RecyclerView.withLinearDividerItemDecoration() {
    (layoutManager as? LinearLayoutManager)?.let {
        addItemDecoration(DividerItemDecoration(context, it.orientation))
    }
}

fun RecyclerView.attachSnapHelper(snapHelper: SnapHelper = PagerSnapHelper()) =
    snapHelper.attachToRecyclerView(this)

fun MaterialTextView.updateTextColor(@ColorRes color: Int) =
    setTextColor(ContextCompat.getColor(context, color))

fun MaterialCardView.updateBackgroundColor(@ColorRes color: Int) =
    setCardBackgroundColor(ContextCompat.getColor(context, color))
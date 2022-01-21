package com.example.moodle.utils

import android.app.Dialog
import android.content.Context
import android.view.Window
import com.example.moodle.R


class ProgressBar {

    private var dialog: Dialog? = null

    fun show(context: Context?) {

        if (dialog != null && dialog?.isShowing == true) {
            return
        }

        dialog = Dialog(context!!)
        dialog?.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog?.setContentView(R.layout.progressbar)
        dialog?.window?.setBackgroundDrawableResource(android.R.color.transparent)
        dialog?.show()
    }

    fun dismiss() {
        if (dialog != null && dialog?.isShowing == true) {
            dialog?.dismiss()
        }
    }

    companion object {
        var instance: ProgressBar? = null

        fun instance() = instance ?: run {
            instance = ProgressBar()
            instance!!
        }
    }
}
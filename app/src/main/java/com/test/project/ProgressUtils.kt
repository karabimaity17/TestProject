package com.test.project

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.Window

object ProgressUtils {
    private var progressDialog: Dialog? = null

    fun showLoadingDialog(context: Context?) {
        context?.let {
            if (progressDialog == null || !progressDialog!!.isShowing) {
                progressDialog = Dialog(context)
                progressDialog!!.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
                progressDialog!!.requestWindowFeature(Window.FEATURE_NO_TITLE)
                progressDialog!!.setContentView(R.layout.progress_dialog)
                progressDialog!!.setCancelable(false)
                progressDialog!!.show()
            }
        }
    }

    fun cancelLoading() {
        progressDialog?.let {
            if (it.isShowing) {
                it.dismiss()
                progressDialog = null // Release dialog reference
            }
        }
    }

    fun isShowing(): Boolean {
        return progressDialog?.isShowing ?: false
    }
}
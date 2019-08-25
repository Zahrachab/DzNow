package com.example.newsfinal.Room


import android.app.Activity
import android.app.AlertDialog
import android.content.Context
import android.widget.Toast
import java.io.FileInputStream
import java.io.IOException


object AppTools {

    @Throws(IOException::class)
    fun lire(stream: FileInputStream): String {
        var n: Int
        val fileContent = StringBuffer("")
        val buffer = ByteArray(1024)
        n = stream.read(buffer)
        while (n != -1) {
            fileContent.append(String(buffer, 0, n))
            n = stream.read(buffer)
        }
        val text = fileContent.toString()
        stream.close()
        return text

    }

    fun alertbox(activity: Activity, message: String) {
        AlertDialog.Builder(activity).setMessage(message)
            .setTitle("Demo").setCancelable(false)
    }

    fun showToast(ctx: Context, message: CharSequence) {
        Toast.makeText(ctx, message, Toast.LENGTH_SHORT).show()
    }
}
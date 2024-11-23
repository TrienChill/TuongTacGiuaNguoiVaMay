package com.example.ttnguoivoimay03

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout

class MainActivity : AppCompatActivity() {
    private lateinit var nameTextView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        nameTextView = findViewById(R.id.nameTextView)

        val showDialogButton: Button = findViewById(R.id.showDialogButton)
        showDialogButton.setOnClickListener {
            showCustomNameDialog()
        }
    }

    private fun showCustomNameDialog() {
        val dialogView = layoutInflater.inflate(R.layout.dialog_custom_name, null)
        val nameInputLayout: TextInputLayout = dialogView.findViewById(R.id.nameInputLayout)
        val nameEditText: TextInputEditText = dialogView.findViewById(R.id.nameEditText)

        val dialog = MaterialAlertDialogBuilder(this)
            .setTitle("Nhập Tên")
            .setView(dialogView)
            .setPositiveButton("Lưu") { _, _ ->
                val name = nameEditText.text.toString().trim()
                if (name.isNotEmpty()) {
                    nameTextView.text = "Xin chào, $name"
                } else {
                    nameInputLayout.error = "Vui lòng nhập tên"
                }
            }
            .setNegativeButton("Hủy", null)
            .create()

        dialog.show()
    }
}
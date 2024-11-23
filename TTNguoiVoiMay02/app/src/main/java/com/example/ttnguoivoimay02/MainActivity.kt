package com.example.ttnguoivoimay02

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity


import android.widget.Button
import android.widget.Toast
import com.google.android.material.dialog.MaterialAlertDialogBuilder

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnShowDialog: Button = findViewById(R.id.btnShowDialog)
        btnShowDialog.setOnClickListener {
            val dialog = CustomMaterialDialog.build(
                context = this,
                title = "Xác Nhận Xóa",
                message = "Bạn có chắc chắn muốn xóa toàn bộ dữ liệu không?",
                positiveText = "Xóa",
                negativeText = "Hủy",
                onPositiveClick = {
                    Toast.makeText(this, "Đã xóa dữ liệu", Toast.LENGTH_SHORT).show()
                },
                onNegativeClick = {
                    Toast.makeText(this, "Đã hủy", Toast.LENGTH_SHORT).show()
                }
            )

            dialog.show()
        }
    }
}
package com.example.ttnguoivoimay05
// MainActivity.kt
import android.os.Bundle
import android.view.ContextMenu
import android.view.MenuItem
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.ListView
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.textfield.TextInputEditText

class MainActivity : AppCompatActivity() {
    private lateinit var namesList: MutableList<String>
    private lateinit var adapter: ArrayAdapter<String>
    private lateinit var listView: ListView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Khởi tạo danh sách ban đầu
        namesList = mutableListOf("Nguyễn Văn A", "Trần Thị B", "Lê Văn C")

        listView = findViewById(R.id.namesListView)
        adapter = ArrayAdapter(
            this,
            android.R.layout.simple_list_item_1,
            namesList
        )
        listView.adapter = adapter

        // Đăng ký ContextMenu cho ListView
        registerForContextMenu(listView)
    }

    override fun onCreateContextMenu(
        menu: ContextMenu?,
        v: View?,
        menuInfo: ContextMenu.ContextMenuInfo?
    ) {
        super.onCreateContextMenu(menu, v, menuInfo)
        menuInflater.inflate(R.menu.names_context_menu, menu)
    }

    override fun onContextItemSelected(item: MenuItem): Boolean {
        val info = item.menuInfo as AdapterView.AdapterContextMenuInfo
        val position = info.position

        return when (item.itemId) {
            R.id.edit_name -> {
                showEditDialog(position)
                true
            }
            R.id.delete_name -> {
                namesList.removeAt(position)
                adapter.notifyDataSetChanged()
                true
            }
            else -> super.onContextItemSelected(item)
        }
    }

    private fun showEditDialog(position: Int) {
        val dialogView = layoutInflater.inflate(R.layout.dialog_edit_name, null)
        val nameInput = dialogView.findViewById<TextInputEditText>(R.id.nameEditText)
        nameInput.setText(namesList[position])

        MaterialAlertDialogBuilder(this)
            .setTitle("Chỉnh sửa tên")
            .setView(dialogView)
            .setPositiveButton("Lưu") { _, _ ->
                val newName = nameInput.text.toString()
                if (newName.isNotBlank()) {
                    namesList[position] = newName
                    adapter.notifyDataSetChanged()
                }
            }
            .setNegativeButton("Hủy", null)
            .show()
    }
}
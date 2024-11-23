import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.Button
import android.widget.TextView
import com.example.ttnguoivoimay02.R
import com.google.android.material.dialog.MaterialAlertDialogBuilder

class CustomMaterialDialog(
    context: Context,
    private val title: String,
    private val message: String,
    private val positiveText: String,
    private val negativeText: String,
    private val onPositiveClick: (() -> Unit)?,
    private val onNegativeClick: (() -> Unit)?
) : Dialog(context) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Inflate layout
        setContentView(R.layout.dialog_custom)

        // Transparent background
        window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        // Find views
        val txtTitle: TextView = findViewById(R.id.txtDialogTitle)
        val txtMessage: TextView = findViewById(R.id.txtDialogMessage)
        val btnPositive: Button = findViewById(R.id.btnPositive)
        val btnNegative: Button = findViewById(R.id.btnNegative)

        // Set content
        txtTitle.text = title
        txtMessage.text = message
        btnPositive.text = positiveText
        btnNegative.text = negativeText

        // Set click listeners
        btnPositive.setOnClickListener {
            onPositiveClick?.invoke()
            dismiss()
        }

        btnNegative.setOnClickListener {
            onNegativeClick?.invoke()
            dismiss()
        }
    }

    companion object {
        fun build(
            context: Context,
            title: String,
            message: String,
            positiveText: String = "Xác Nhận",
            negativeText: String = "Hủy",
            onPositiveClick: (() -> Unit)? = null,
            onNegativeClick: (() -> Unit)? = null
        ): CustomMaterialDialog {
            return CustomMaterialDialog(
                context,
                title,
                message,
                positiveText,
                negativeText,
                onPositiveClick,
                onNegativeClick
            )
        }
    }
}
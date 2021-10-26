package osuna.luis.contador

import android.content.Context
import android.content.DialogInterface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AlertDialog

class MainActivity : AppCompatActivity() {

    var cuenta: Int = 0
    lateinit var tv_contador: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btn_suma: Button = findViewById(R.id.btn_mas1)
        val btn_resta: Button = findViewById(R.id.btn_menos1)
        val btn_reinicio: Button = findViewById(R.id.btn_reiniciar)
        tv_contador = findViewById(R.id.tv_contador)

        btn_suma.setOnclickListener{
            cuenta++
            tv_contador.setText("$cuenta")
        }

        btn_resta.setOnclickListener{
            cuenta--
            tv_contador.setText("$cuenta")
        }

        btn_reinicio.setOnclickListenber{
            val alertDialog: AlertDialog? = this?.let {
                val builder = AlertDialog.Builder(it)
                builder.apply {
                    setPositiveButton("Borrar",
                        DialogInterface.OnClickListener { dialog, id ->
                            // User clicked OK button
                            cuenta = 0
                            tv_contador.setText("$cuenta")
                        })
                    setNegativeButton("Cancelar",
                        DialogInterface.OnClickListener { dialog, id ->
                            // User cancelled the dialog
                        })
                }
                // Set other dialog properties
                builder?.setMessage("¿Seguro que desea eliminar la cuenta?")
                    .setTitle("Aviso")

                // Create the AlertDialog
                builder.create()
            }
            alertDialog?.show()
        }
        override fun onPause() {
            super.onPause()
            val sharedPref = this.getPreferences(Context.MODE_PRIVATE) ?: return
            with (sharedPref.edit()) {
                putInt(getString("contador"), cuenta)
                commit()
            }

            val sharedPref2 = this?.getPreferences(Context.MODE_PRIVATE)
            val editor = sharedPref2.edit()
            editor.putInt.setText("contador", cuenta)
            editor.commit
        }

        override fun onResume() {
            super.onResume()

            val sharedPref = this?.getPreferences(Context.MODE_PRIVATE)
            var cuenta = sharedPref.getInt("contador",0)
            tv_contador.setText("$cuenta")
            
        }
    }
}
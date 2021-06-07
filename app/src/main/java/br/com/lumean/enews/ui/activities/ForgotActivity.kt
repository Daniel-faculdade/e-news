package br.com.lumean.enews.ui.activities

import android.content.Intent
import android.os.Bundle
import android.view.Gravity
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import br.com.lumean.enews.models.Account
import br.com.lumean.enews.services.RetrofitInitializerUser
import br.com.lumean.enews.R
import com.afollestad.materialdialogs.MaterialDialog
import com.afollestad.materialdialogs.Theme
import com.thecode.aestheticdialogs.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ForgotActivity: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_forgot)
        var forgotButton = findViewById<Button>(R.id.forgotButton)
        forgotButton.setOnClickListener {
            handleForgot()
        }

    }

    fun handleForgot(){

        var email = findViewById<EditText>(R.id.inputEmail)

        var account = Account()
        account.email = email.text.toString()

        var service = RetrofitInitializerUser().AccountService()
        var call = service.forgot(account)

        call.enqueue(object: Callback<Void>{
            override fun onResponse(call: Call<Void>, response: Response<Void>) {
                if(response.code() == 204){
                    AestheticDialog.Builder(this@ForgotActivity, DialogStyle.FLAT, DialogType.INFO)
                        .setTitle("E-mail enviado !")
                        .setMessage("Caso exista uma conta registrada com esse e-mail, sera enviada a sua senha de acesso ao mesmo.")
                        .setCancelable(false)
                        .setDarkMode(true)
                        .setGravity(Gravity.CENTER)
                        .setAnimation(DialogAnimation.SHRINK)
                        .setOnClickListener(object : OnDialogClickListener {
                            override fun onClick(dialog: AestheticDialog.Builder) {
                                dialog.dismiss()
                                var intent = Intent(this@ForgotActivity, LoginActivity::class.java)
                                startActivity(intent)
                                finish()
                            }
                        })
                        .show()

                }else if(response.code() == 404){

                }
            }

            override fun onFailure(call: Call<Void>, t: Throwable) {
                AestheticDialog.Builder(this@ForgotActivity, DialogStyle.FLAT, DialogType.ERROR)
                    .setTitle("Ops, ocorreu um erro")
                    .setMessage("Ocorreu um erro ao tentar realizar essa tarefa, tente novamente mais tarde.")
                    .setCancelable(false)
                    .setDarkMode(true)
                    .setGravity(Gravity.CENTER)
                    .setAnimation(DialogAnimation.SHRINK)
                    .setOnClickListener(object : OnDialogClickListener {
                        override fun onClick(dialog: AestheticDialog.Builder) {
                            dialog.dismiss()
                        }
                    })
                    .show()
            }
        })



    }
}
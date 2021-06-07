package br.com.lumean.enews.ui.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.util.Log.DEBUG
import android.view.Gravity
import android.widget.Button
import android.widget.EditText
import br.com.lumean.enews.models.Account
import br.com.lumean.enews.services.RetrofitInitializerUser
import br.com.lumean.enews.R
import com.afollestad.materialdialogs.MaterialDialog
import com.afollestad.materialdialogs.Theme
import com.thecode.aestheticdialogs.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        var loginButton = findViewById<Button>(R.id.loginButton)
        loginButton.setOnClickListener {
            handleLogin()
        }

        var forgotButton = findViewById<Button>(R.id.buttonEsqueceu)

        forgotButton.setOnClickListener {
            var intent = Intent(this, ForgotActivity::class.java)
            startActivity(intent)
        }
    }

    fun handleLogin() : Boolean {
        var email = findViewById<EditText>(R.id.inputEmail)
        var senha = findViewById<EditText>(R.id.inputSenha)

        var account = Account()
        account.email = email.text.toString()
        account.password = senha.text.toString()

        var service = RetrofitInitializerUser().AccountService()
        var call = service.auth(account)

        call.enqueue(object: Callback<Account> {
            override fun onResponse(call: Call<Account>, response: Response<Account>) {
                if(response.code() == 200){

                }else{
                    AestheticDialog.Builder(this@LoginActivity, DialogStyle.EMOTION, DialogType.ERROR)
                        .setTitle("Erro ao tentar efetuar login !")
                        .setCancelable(true)
                        .show()
                }
            }

            override fun onFailure(call: Call<Account>, t: Throwable) {
                AestheticDialog.Builder(this@LoginActivity, DialogStyle.FLAT, DialogType.ERROR)
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

        return true
    }

}
package br.com.lumean.enews.ui.activities

import android.content.Intent
import android.os.Bundle
import android.view.Gravity
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import br.com.lumean.enews.R
import br.com.lumean.enews.models.Account
import br.com.lumean.enews.services.RetrofitInitializerUser
import com.afollestad.materialdialogs.MaterialDialog
import com.afollestad.materialdialogs.Theme
import com.thecode.aestheticdialogs.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RegisterActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)


        var registerBtn = findViewById<Button>(R.id.registerButton)
        registerBtn.setOnClickListener {
            createAccount()
        }
    }

    fun createAccount() {
        var name = findViewById<EditText>(R.id.inputName)
        var email = findViewById<EditText>(R.id.inputEmail)
        var senha = findViewById<EditText>(R.id.inputSenha)

        var account = Account()

        account.name = name.text.toString()
        account.email = email.text.toString()
        account.password = senha.text.toString()

        var service = RetrofitInitializerUser().AccountService()
        var call = service.create(account)

        call.enqueue(object : Callback<Account> {
            override fun onResponse(call: Call<Account>, response: Response<Account>) {
                if (response.code() == 200) {
                    AestheticDialog.Builder(this@RegisterActivity, DialogStyle.FLAT, DialogType.SUCCESS)
                        .setTitle("Sucesso")
                        .setMessage("Usuário criado com sucesso !")
                        .setCancelable(false)
                        .setDarkMode(true)
                        .setGravity(Gravity.CENTER)
                        .setAnimation(DialogAnimation.SHRINK)
                        .setOnClickListener(object : OnDialogClickListener {
                            override fun onClick(dialog: AestheticDialog.Builder) {
                                dialog.dismiss()
                                var intent = Intent(this@RegisterActivity, LoginActivity::class.java)
                                startActivity(intent)
                                finish()
                            }
                        })
                        .show()
                } else if (response.code() == 409) {
                    AestheticDialog.Builder(this@RegisterActivity, DialogStyle.FLAT, DialogType.WARNING)
                        .setTitle("E-mail de registro já utilizado")
                        .setMessage("Já existe um usuário utilizando o e-mail informado.")
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
                }
            override fun onFailure(call: Call<Account>, t: Throwable) {
                AestheticDialog.Builder(this@RegisterActivity, DialogStyle.FLAT, DialogType.ERROR)
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


    fun showHome(){
        var intent = Intent(this, HomeActivity::class.java)
        startActivity(intent)
    }
}



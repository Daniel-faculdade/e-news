package br.com.lumean.enews.ui.activities

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import br.com.lumean.enews.R
import br.com.lumean.enews.models.Account
import br.com.lumean.enews.services.RetrofitInitializerUser
import com.afollestad.materialdialogs.MaterialDialog
import com.afollestad.materialdialogs.Theme
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
                    MaterialDialog.Builder(this@RegisterActivity)
                        .theme(Theme.LIGHT)
                        .title(R.string.success)
                        .content(R.string.success_register_message)
                        .positiveText(R.string.button_ok)
                        .onPositive { dialog, which ->
                            showHome()
                        }
                        .show()
//                } else if (response.code() == 409) {
//                    MaterialDialog.Builder(this@RegisterActivity)
//                        .theme(Theme.LIGHT)
//                        .title(R.string.ops)
//                        .content(R.string.user_reset_message)
//                        .positiveText(R.string.button_yes)
//                        .onPositive { dialog, which ->
//                            showForgot()
//                        }
//                        .negativeText(R.string.button_no)
//                        .show()
//                }
                }
            }

            override fun onFailure(call: Call<Account>, t: Throwable) {
                TODO("Not yet implemented")
            }
        })

    }

    fun showHome(){
        var intent = Intent(this, HomeActivity::class.java)
        startActivity(intent)
    }
}
package br.com.lumean.enews.ui.activities

import android.os.Bundle
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import br.com.lumean.enews.models.Account
import br.com.lumean.enews.services.RetrofitInitializerUser
import br.com.lumean.enews.R
import com.afollestad.materialdialogs.MaterialDialog
import com.afollestad.materialdialogs.Theme
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ForgotActivity: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_forgot)

        handleForgot()
    }

    fun handleForgot(){

        var email = findViewById<EditText>(R.id.inputEmail)

        var account = Account()
        account.email = email.text.toString()

        var service = RetrofitInitializerUser().AccountService()
        var call = service.forgot(account)

        call.enqueue(object: Callback<Void>{
            override fun onResponse(call: Call<Void>, response: Response<Void>) {
                if(response.code() == 200){
                    MaterialDialog.Builder(this@ForgotActivity)
                        .theme(Theme.LIGHT)
                        .title(R.string.success)
                        .content(R.string.success_forgotten_message)
                        .positiveText(R.string.button_ok)
                        .show()

                }else if(response.code() == 404){

                }
            }

            override fun onFailure(call: Call<Void>, t: Throwable) {
                TODO("Not yet implemented")
            }
        })



    }
}
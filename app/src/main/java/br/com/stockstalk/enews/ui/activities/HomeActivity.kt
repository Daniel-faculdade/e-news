package br.com.stockstalk.enews.ui.activities

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import br.com.stockstalk.enews.R

class HomeActivity: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        var login =findViewById<Button>(R.id.loginButton)
        login.setOnClickListener {
            var intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)

        var register = findViewById<Button>(R.id.registerButton)
            register.setOnClickListener {
                var intent = Intent(this, RegisterActivity::class.java)
                startActivity(intent)
            }
        }
    }
}
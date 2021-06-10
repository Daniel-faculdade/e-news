package br.com.lumean.enews.ui.activities

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.FrameLayout
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import br.com.lumean.enews.R
import br.com.lumean.enews.models.Article
import br.com.lumean.enews.ui.fragments.HomeFragment
import com.google.firebase.FirebaseApp

class MainActivity :
    AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        FirebaseApp.initializeApp(this);

        setContentView(R.layout.activity_main)

       setFragment(HomeFragment.newInstance())
    }

     fun setFragment(f: Fragment) {
        val fl = supportFragmentManager.beginTransaction()
        fl.replace(R.id.pageContent, f)
        fl.commit()
     }
}
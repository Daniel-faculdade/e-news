package br.com.lumean.enews.ui.activities

import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import br.com.lumean.enews.R
import br.com.lumean.enews.models.Article
import coil.load
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import org.json.JSONObject
import org.w3c.dom.Text
import java.io.Serializable

class ArticleActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_article)

        val article = intent.getSerializableExtra("article") as Article

        Log.d("Article posserializacao", article.title)
        val title = findViewById<TextView>(R.id.titleArticle)
        title.text = article.title

        val content = findViewById<TextView>(R.id.articleContent)
        content.text = article.content

        val image = findViewById<ImageView>(R.id.imageArticle)
        image.load(article.imageUrl)

        val publishedDate = findViewById<TextView>(R.id.publishedDate)
        publishedDate.text = article.publishedDate
    }
}
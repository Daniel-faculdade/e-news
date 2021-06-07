package br.com.lumean.enews.ui.adapters

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.graphics.PorterDuff
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import br.com.lumean.enews.R
import br.com.lumean.enews.models.Article
import br.com.lumean.enews.ui.activities.ArticleActivity
import br.com.lumean.enews.ui.activities.ForgotActivity
import br.com.lumean.enews.ui.activities.MainActivity
import coil.load
import com.google.android.material.internal.ContextUtils.getActivity
import java.io.Serializable

class ImageHighlightsAdapter(var context: Context, var articles : List<Article>) : RecyclerView.Adapter<RecyclerView.ViewHolder>()  {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        var view = LayoutInflater.from(context).inflate(R.layout.item_image_highlights, parent, false)
        return ArticleHolder(view)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as ArticleHolder).bind(context, articles[position])

        holder.itemView.setOnClickListener {
            val intent = Intent(context, ArticleActivity::class.java)
            val article = articles[position]
            intent.putExtra("article",  article)
            context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return articles.size
    }

    class ArticleHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(context: Context, article: Article) {

            var articleImage = itemView.findViewById<ImageView>(R.id.articleImage)
            var articleTitle = itemView.findViewById<TextView>(R.id.articleTitle)
            var articleCaption = itemView.findViewById<TextView>(R.id.articleCaption)

            articleImage.load(article.imageUrl)
            articleImage.drawable?.setColorFilter(0x76ffffff, PorterDuff.Mode.MULTIPLY)
            articleTitle.text = article.title
            articleCaption.text = article.caption
        }
    }

}
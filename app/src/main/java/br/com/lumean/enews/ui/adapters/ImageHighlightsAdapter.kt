package br.com.lumean.enews.ui.adapters

import android.content.Context
import android.graphics.PorterDuff
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import br.com.lumean.enews.R
import br.com.lumean.enews.models.Article
import coil.load

class ImageHighlightsAdapter(var context: Context, var articles : List<Article>) : RecyclerView.Adapter<RecyclerView.ViewHolder>()  {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        var view = LayoutInflater.from(context).inflate(R.layout.item_image_highlights, parent, false)
        return ArticleHolder(view)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as ArticleHolder).bind(context, articles[position])

        val item = articles[position]
        holder.bind(context, item)
        holder.itemView.setOnClickListener {
            selectArticleDetails(item)
        }
    }

    fun selectArticleDetails(article : Article) {
        Log.d("Item View CLick", "$article.title")
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
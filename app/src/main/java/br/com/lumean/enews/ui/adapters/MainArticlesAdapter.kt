package br.com.lumean.enews.ui.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import br.com.lumean.enews.R
import br.com.lumean.enews.models.Article

class MainArticlesAdapter(var context: Context, var articles : List<Article>) : RecyclerView.Adapter<RecyclerView.ViewHolder>()  {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        var view = LayoutInflater.from(context).inflate(R.layout.item_top_articles, parent, false)
        return ArticleHolder(view, articles)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as ArticleHolder).bind(context, articles[position])
    }

    override fun getItemCount(): Int {
        return articles.size
    }

    class ArticleHolder(itemView: View, var articles : List<Article>) : RecyclerView.ViewHolder(itemView) {

        fun bind(context: Context, article: Article) {

            var articlePosition = itemView.findViewById<TextView>(R.id.articlePosition)
            var articleTitle = itemView.findViewById<TextView>(R.id.articleTitle)

            articlePosition.text = (articles.indexOf(article) + 1).toString()
            articleTitle.text = article.title
        }

    }

}
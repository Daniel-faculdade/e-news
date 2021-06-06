package br.com.lumean.enews.ui.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import coil.load
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import br.com.lumean.enews.R
import br.com.lumean.enews.models.Team

class TeamsAdapter(var context: Context, var teams : List<Team>) : RecyclerView.Adapter<RecyclerView.ViewHolder>()  {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        var view = LayoutInflater.from(context).inflate(R.layout.item_teams, parent, false)
        return ArticleHolder(view, teams)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as ArticleHolder).bind(context, teams[position])
    }

    override fun getItemCount(): Int {
        return teams.size
    }

    class ArticleHolder(itemView: View, var teams : List<Team>) : RecyclerView.ViewHolder(itemView) {

        fun bind(context: Context, team: Team) {

            var teamName = itemView.findViewById<TextView>(R.id.teamName)
            var teamImage = itemView.findViewById<ImageView>(R.id.teamImage)


            teamName.text = team.name
            teamImage.load(team.imageUrl)
        }

    }

}
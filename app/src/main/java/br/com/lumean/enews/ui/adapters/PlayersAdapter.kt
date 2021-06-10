package br.com.lumean.enews.ui.adapters

import android.content.Context
import android.media.Image
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import coil.load
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import br.com.lumean.enews.R
import br.com.lumean.enews.models.Player

class PlayersAdapter(var context: Context, var players : List<Player>) : RecyclerView.Adapter<RecyclerView.ViewHolder>()  {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        var view = LayoutInflater.from(context).inflate(R.layout.item_player, parent, false)
        return PlayerHolder(view, players)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as PlayerHolder).bind(context, players[position])
    }

    override fun getItemCount(): Int {
        return players.size
    }

    class PlayerHolder(itemView: View, var players : List<Player>) : RecyclerView.ViewHolder(itemView) {

        fun bind(context: Context, player: Player) {

            val playerName = itemView.findViewById<TextView>(R.id.playerName)
            playerName.text = player.name

            val playerTwitterTag = itemView.findViewById<TextView>(R.id.playerTag)
            playerTwitterTag.text = player.twitterTag

            val playerAvatar = itemView.findViewById<ImageView>(R.id.playerAvatar)
            playerAvatar.load(player.imageUrl)
        }

    }

}
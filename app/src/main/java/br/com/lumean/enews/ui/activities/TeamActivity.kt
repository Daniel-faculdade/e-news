package br.com.lumean.enews.ui.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import br.com.lumean.enews.R
import br.com.lumean.enews.models.Article
import br.com.lumean.enews.models.Team
import br.com.lumean.enews.ui.adapters.PlayersAdapter
import br.com.lumean.enews.ui.adapters.TeamsAdapter
import coil.load

class TeamActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_team)

        val team = intent.getSerializableExtra("team") as Team

        val nameTeam = findViewById<TextView>(R.id.teamName)
        nameTeam.text = team.name

        val imageTeam = findViewById<ImageView>(R.id.teamAvatar)
        imageTeam.load(team.imageUrl)

        val imageBG = findViewById<ImageView>(R.id.teamBG)
        imageBG.load(team.backgroundUrl)

        val recycView = findViewById<RecyclerView>(R.id.recycViewPlayers)
        recycView?.adapter = PlayersAdapter(this, team.players)

        recycView?.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
    }
}
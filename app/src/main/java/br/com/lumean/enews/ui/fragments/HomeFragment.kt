package br.com.lumean.enews.ui.fragments

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.view.animation.LayoutAnimationController
import android.widget.ListView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import br.com.lumean.enews.R
import br.com.lumean.enews.models.Article
import br.com.lumean.enews.models.Team
import br.com.lumean.enews.ui.adapters.ImageHighlightsAdapter
import br.com.lumean.enews.ui.adapters.MainArticlesAdapter
import br.com.lumean.enews.ui.adapters.TeamsAdapter
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

/**
 * A simple [Fragment] subclass.
 * Use the [HomeFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class HomeFragment : Fragment() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val database = Firebase.database
        val refDbNoticias = database.getReference("articles")

        refDbNoticias.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                val articlesList = dataSnapshot.children.map { it.getValue(Article::class.java)!! }

                val recycViewArticles = activity?.findViewById<RecyclerView>(R.id.recycViewListArtcles)
                recycViewArticles?.adapter = MainArticlesAdapter(activity!!, articlesList)

                var recyViewImages = activity?.findViewById<RecyclerView>(R.id.recycViewHighlights)
                recyViewImages?.adapter = ImageHighlightsAdapter(activity!!, articlesList.subList(0, 3))

                recycViewArticles?.layoutManager = LinearLayoutManager(activity!!, LinearLayoutManager.VERTICAL, false)
                recyViewImages?.layoutManager = LinearLayoutManager(activity!!, LinearLayoutManager.HORIZONTAL, false)
            }

            override fun onCancelled(error: DatabaseError) {
                // Failed to read value
                Log.d("Failed to red", "Failed to read value.", error.toException())
            }
        })

        val refDbTeams = database.getReference("teams")

        refDbTeams.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                val teamsList = dataSnapshot.children.map { it.getValue(Team::class.java)!! }

                val recycView = activity?.findViewById<RecyclerView>(R.id.recycViewListTeams)
                recycView?.adapter = TeamsAdapter(activity!!, teamsList)

                recycView?.layoutManager = LinearLayoutManager(activity!!, LinearLayoutManager.HORIZONTAL, false)
            }

            override fun onCancelled(error: DatabaseError) {
                // Failed to read value
                Log.d("Failed to red", "Failed to read value.", error.toException())
            }
        })

    }

    private fun layoutAnimation(recyclerView : RecyclerView, layoutAnim : Int ) {
        val context = recyclerView.context
        val layoutAnimControler : LayoutAnimationController =
            AnimationUtils.loadLayoutAnimation(context, layoutAnim)

        recyclerView.layoutAnimation = layoutAnimControler
        recyclerView.adapter?.notifyDataSetChanged()
        recyclerView.scheduleLayoutAnimation()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    companion object {
        fun newInstance() : HomeFragment {
            return HomeFragment()
        }
    }
}
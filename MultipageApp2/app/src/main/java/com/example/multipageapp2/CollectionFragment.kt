package com.example.multipageapp2

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import android.content.Context
import android.content.Intent
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class CollectionFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_collection, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val prefs = requireContext().getSharedPreferences("MangaPrefs", Context.MODE_PRIVATE)
        val savedTitles = prefs.getStringSet("collection", emptySet()) ?: emptySet()

        // Full manga list — same as HomeFragment
        val allManga = listOf(
            Manga(
                title = "Berserk",
                imageUrl = "https://cdn.myanimelist.net/images/manga/1/157897.jpg",
                summary = "Guts, a former mercenary now known as the Black Swordsman, travels a dark medieval world seeking revenge against the God Hand — a group of demons he was once allied with.",
                malUrl = "https://myanimelist.net/manga/2/Berserk",
                genre = "Dark Fantasy"
            ),
            Manga(
                title = "One Piece",
                imageUrl = "https://cdn.myanimelist.net/images/manga/2/253146.jpg",
                summary = "Monkey D. Luffy sets sail to become the King of the Pirates and find the legendary treasure One Piece, gathering a crew of unique companions along the way.",
                malUrl = "https://myanimelist.net/manga/13/One_Piece",
                genre = "Adventure"
            ),
            Manga(
                title = "Fullmetal Alchemist",
                imageUrl = "https://cdn.myanimelist.net/images/manga/3/243675.jpg",
                summary = "Two brothers use alchemy to try to resurrect their dead mother, paying a terrible price. Now they seek the Philosopher's Stone to restore what was lost.",
                malUrl = "https://myanimelist.net/manga/25/Fullmetal_Alchemist",
                genre = "Action / Fantasy"
            ),
            Manga(
                title = "Attack on Titan",
                imageUrl = "https://cdn.myanimelist.net/images/manga/2/37846.jpg",
                summary = "Humanity lives behind massive walls to protect themselves from giant humanoid creatures called Titans. Eren Yeager vows to destroy them all after his hometown is destroyed.",
                malUrl = "https://myanimelist.net/manga/23390/Shingeki_no_Kyojin",
                genre = "Dark Action"
            ),
            Manga(
                title = "Chainsaw Man",
                imageUrl = "https://cdn.myanimelist.net/images/manga/3/216464.jpg",
                summary = "Denji, a poor young man, merges with his pet devil Pochita to become Chainsaw Man — a devil hunter with chainsaws for arms working for a mysterious government agency.",
                malUrl = "https://myanimelist.net/manga/116778/Chainsaw_Man",
                genre = "Dark Action"
            ),
            Manga(
                title = "Tokyo Ghoul",
                imageUrl = "https://cdn.myanimelist.net/images/manga/3/114037.jpg",
                summary = "Ken Kaneki survives a deadly encounter with a ghoul and is transformed into a half-ghoul, forcing him to navigate a dark world between humans and flesh-eating monsters.",
                malUrl = "https://myanimelist.net/manga/32281/Tokyo_Ghoul",
                genre = "Dark Fantasy"
            ),
            Manga(
                title = "Hunter x Hunter",
                imageUrl = "https://cdn.myanimelist.net/images/manga/2/253119.jpg",
                summary = "Gon Freecss dreams of becoming a Hunter like his absent father. His journey introduces him to a world of dangerous creatures, criminals, and powerful abilities.",
                malUrl = "https://myanimelist.net/manga/46/Hunter_x_Hunter",
                genre = "Adventure / Fantasy"
            ),
            Manga(
                title = "Naruto",
                imageUrl = "https://cdn.myanimelist.net/images/manga/3/117681.jpg",
                summary = "Naruto Uzumaki is a young ninja with a powerful demon fox sealed inside him. Shunned by his village, he dreams of becoming Hokage and earning everyone's respect.",
                malUrl = "https://myanimelist.net/manga/11/Naruto",
                genre = "Action / Ninja"
            )
        )

        // Filter to only saved ones
        val savedManga = allManga.filter { it.title in savedTitles }

        val recyclerView = view.findViewById<RecyclerView>(R.id.collectionRecycler)
        val emptyText = view.findViewById<TextView>(R.id.txtEmpty)

        if (savedManga.isEmpty()) {
            recyclerView.visibility = View.GONE
            emptyText.visibility = View.VISIBLE
        } else {
            recyclerView.visibility = View.VISIBLE
            emptyText.visibility = View.GONE
            recyclerView.layoutManager = LinearLayoutManager(requireContext())
            recyclerView.adapter = MangaAdapter(savedManga) { manga ->
                val intent = Intent(requireContext(), MangaDetailActivity::class.java).apply {
                    putExtra("title", manga.title)
                    putExtra("imageUrl", manga.imageUrl)
                    putExtra("summary", manga.summary)
                    putExtra("malUrl", manga.malUrl)
                    putExtra("genre", manga.genre)
                }
                startActivity(intent)
            }
        }
    }
}
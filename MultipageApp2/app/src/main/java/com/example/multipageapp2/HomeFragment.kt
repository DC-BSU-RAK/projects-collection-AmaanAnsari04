package com.example.multipageapp2

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class HomeFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_home, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val mangaList = listOf(
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
                title = "JoJo's Bizarre Adventure",
                imageUrl = "https://cdn.myanimelist.net/images/manga/3/179819.jpg",
                summary = "A multigenerational saga following the Joestar family across different eras, each member facing supernatural threats with unique abilities known as Stands.",
                malUrl = "https://myanimelist.net/manga/1706/JoJo_no_Kimyou_na_Bouken",
                genre = "Supernatural Action"
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
            ),
            Manga(
                title = "Bleach",
                imageUrl = "https://cdn.myanimelist.net/images/manga/3/188691.jpg",
                summary = "Ichigo Kurosaki gains the powers of a Soul Reaper and must defend the living world from evil spirits called Hollows while uncovering deeper conspiracies in the spirit world.",
                malUrl = "https://myanimelist.net/manga/12/Bleach",
                genre = "Supernatural Action"
            )
        )

        val recyclerView = view.findViewById<RecyclerView>(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        recyclerView.adapter = MangaAdapter(mangaList) { manga ->
            // Open detail screen - we'll build this next
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
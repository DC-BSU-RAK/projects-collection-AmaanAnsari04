package com.example.singlepageapp

import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.appcompat.app.AlertDialog
import android.widget.LinearLayout

class MainActivity : AppCompatActivity() {

    private var selectedColor: String? = null
    private var selectedSeason: String? = null

    private lateinit var colorButtons: Map<String, Button>
    private lateinit var seasonButtons: Map<String, LinearLayout>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Instructions modal
        findViewById<Button>(R.id.instructButton).setOnClickListener {
            val view = layoutInflater.inflate(R.layout.instructions_page, null)
            val dialog = AlertDialog.Builder(this)
                .setView(view)
                .create()
            dialog.window?.setBackgroundDrawableResource(android.R.color.transparent)
            view.findViewById<Button>(R.id.closeBtn).setOnClickListener {
                dialog.dismiss()
            }
            dialog.show()
        }

        // Colour buttons
        colorButtons = mapOf(
            "Red"    to findViewById(R.id.redButton),
            "Blue"   to findViewById(R.id.blueButton),
            "Yellow" to findViewById(R.id.yellowButton),
            "Green"  to findViewById(R.id.greenButton),
            "Purple" to findViewById(R.id.purpleButton),
            "White"  to findViewById(R.id.whiteButton)
        )

        // Season buttons
        seasonButtons = mapOf(
            "Spring" to findViewById(R.id.springButton),
            "Summer" to findViewById(R.id.summerButton),
            "Autumn" to findViewById(R.id.autumnButton),
            "Winter" to findViewById(R.id.winterButton)
        )

        colorButtons.forEach { (color, button) ->
            button.setOnClickListener {
                selectedColor = color
                highlightColorButton(button)
            }
        }

        seasonButtons.forEach { (season, layout) ->
            layout.setOnClickListener {
                selectedSeason = season
                highlightSeasonCard(layout)
            }
        }

        // Result button
        findViewById<Button>(R.id.resultButton).setOnClickListener {
            if (selectedColor == null || selectedSeason == null) {
                AlertDialog.Builder(this)
                    .setTitle("Missing selection")
                    .setMessage("Please choose a colour and a season first!")
                    .setPositiveButton("OK", null)
                    .show()
                return@setOnClickListener
            }

            val (flower, description) = getFlower(selectedColor!!, selectedSeason!!)

            val dialogView = layoutInflater.inflate(R.layout.flower, null)

            dialogView.findViewById<TextView>(R.id.resultText).text = flower
            dialogView.findViewById<TextView>(R.id.flowerDescription).text = description
            dialogView.findViewById<TextView>(R.id.flowerCombo).text =
                "$selectedColor · $selectedSeason"

            val dialog = AlertDialog.Builder(this)
                .setView(dialogView)
                .create()
            dialog.window?.setBackgroundDrawableResource(android.R.color.transparent)

            dialogView.findViewById<Button>(R.id.closeButton).setOnClickListener {
                dialog.dismiss()
            }
            dialog.show()
        }
    }

    private fun highlightColorButton(selected: Button) {
        colorButtons.values.forEach {
            it.alpha = 0.45f
            it.scaleX = 1f
            it.scaleY = 1f
        }
        selected.alpha = 1f
        selected.scaleX = 1.07f
        selected.scaleY = 1.07f
    }

    private fun highlightSeasonCard(selected: LinearLayout) {
        seasonButtons.values.forEach {
            it.alpha = 0.45f
            it.scaleX = 1f
            it.scaleY = 1f
        }
        selected.alpha = 1f
        selected.scaleX = 1.05f
        selected.scaleY = 1.05f
    }

    private fun getFlower(color: String, season: String): Pair<String, String>{
        return when {
            color == "Red"    && season == "Spring" -> Pair("Tulip", "A classic spring bloom, the tulip symbolises perfect love and elegance.")
            color == "Red"    && season == "Summer" -> Pair("Poppy", "Bold and vivid, the poppy thrives under the summer sun.")
            color == "Red"    && season == "Autumn" -> Pair("Dahlia", "The dahlia blazes through autumn with rich, layered petals.")
            color == "Red"    && season == "Winter" -> Pair("Rose", "The timeless rose endures even the coldest winters.")
            color == "Blue"   && season == "Spring" -> Pair("Forget-me-not", "Tiny and delicate, forget-me-nots carpet spring meadows in blue.")
            color == "Blue"   && season == "Summer" -> Pair("Hydrangea", "Full and lush, hydrangeas fill summer gardens with deep blue clusters.")
            color == "Blue"   && season == "Autumn" -> Pair("Aster", "Asters bloom late in autumn, a last burst of blue before winter.")
            color == "Blue"   && season == "Winter" -> Pair("Bluebell", "Resilient bluebells persist even as frost settles on the ground.")
            color == "Yellow" && season == "Spring" -> Pair("Daffodil", "The daffodil is the herald of spring, bright and unmistakable.")
            color == "Yellow" && season == "Summer" -> Pair("Sunflower", "The sunflower turns its face to the summer sun, tall and cheerful.")
            color == "Yellow" && season == "Autumn" -> Pair("Chrysanthemum", "Chrysanthemums glow golden as autumn leaves begin to fall.")
            color == "Yellow" && season == "Winter" -> Pair("Winter Jasmine", "Winter jasmine dots bare branches with bright yellow stars.")
            color == "Purple" && season == "Spring" -> Pair("Lavender", "Lavender fills spring air with its calming, sweet fragrance.")
            color == "Purple" && season == "Summer" -> Pair("Iris", "The iris stands tall and proud in the summer heat.")
            color == "Purple" && season == "Autumn" -> Pair("Verbena", "Verbena adds rich purple tones to the autumn garden palette.")
            color == "Purple" && season == "Winter" -> Pair("Cyclamen", "Cyclamen blooms through winter with delicate swept-back petals.")
            color == "Green"  && season == "Spring" -> Pair("Orchid", "Green orchids are rare and exotic, a unique gift of spring.")
            color == "Green"  && season == "Summer" -> Pair("Green Rose", "The green rose is a symbol of abundance and growth in summer.")
            color == "Green"  && season == "Autumn" -> Pair("Hellebore", "Hellebores maintain their green through the dying days of autumn.")
            color == "Green"  && season == "Winter" -> Pair("Evergreen Bloom", "Evergreen blooms defy winter, staying lush when all else fades.")
            color == "White"  && season == "Spring" -> Pair("Lily", "The white lily is a symbol of purity, blooming fresh in spring.")
            color == "White"  && season == "Summer" -> Pair("Jasmine", "Jasmine fills warm summer nights with its sweet, heady scent.")
            color == "White"  && season == "Autumn" -> Pair("Anemone", "White anemones flutter like small doves through the autumn breeze.")
            color == "White"  && season == "Winter" -> Pair("Snowdrop", "The snowdrop is the first brave flower to push through winter snow.")
            else -> Pair("Unknown Flower", "A mysterious bloom.")
        }
    }
}
package com.example.diceroller

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.diceroller.databinding.FlipBinding
import kotlinx.coroutines.*
import java.util.*

@SuppressLint("StaticFieldLeak")
private lateinit var binding: FlipBinding

class Fliper : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = FlipBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.roll.text = getString(R.string.roll_text_button)
        val number = intent.getStringExtra("EXTRA_MESSAGE")?.toInt()
        roll_load(number)
        binding.roll.setOnClickListener{
            roll_load(number)
        }

    }

    private fun getImage(number: Int): Int {
        Log.d("saleh" , " getRandom $number")

        val randomImage  = when(number){
                1 -> R.drawable.dice_1
                2 -> R.drawable.dice_2
                3 -> R.drawable.dice_3
                4 -> R.drawable.dice_4
                5 -> R.drawable.dice_5
                else -> R.drawable.dice_6
            }

        return randomImage
    }

    @OptIn(DelicateCoroutinesApi::class)
    private fun roll_load(number: Int?) {
        Log.d("saleh" , " roll_load $number")

         GlobalScope.launch {
             for (i in 1..number!! + 1) {

                 val randomImage = getImage(i) //this method is called
                 binding.img.setImageResource(randomImage)
                 delay(300L)

             }
             binding.img.setImageResource(R.drawable.empty_dice)
             delay(400L)
             rollDice(number)

         }
        return
    }

    private suspend fun rollDice(number:Int?) {
        Log.d("saleh" , " rollDice $number")
        val randomInt = Random().nextInt(number!!) +1
        val randomImage = getImage(randomInt)
        withContext(Dispatchers.Main) {
            Toast.makeText(applicationContext, "Number $randomInt", Toast.LENGTH_SHORT).show()
        }
        binding.img.setImageResource(randomImage)
    }
}
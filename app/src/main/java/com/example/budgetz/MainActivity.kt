package com.example.budgetz

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.ActionBar
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    private lateinit var rvMusic : RecyclerView
    private var list : ArrayList<Music> = arrayListOf()
    private var title: String = "Mode List"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        rvMusic = findViewById(R.id.rv_music)
        rvMusic.setHasFixedSize(true)

        list.addAll(MusicData.listData)
        showRecyclerList()


    }
    private fun showRecyclerList(){
        rvMusic.layoutManager = LinearLayoutManager(this)
        val listMusicAdapter = ListMusicAdapter(list)
        rvMusic.adapter = listMusicAdapter

        listMusicAdapter.setOnItemClickCallback(object : ListMusicAdapter.OnItemClickCallback {
            override fun onItemClicked(data: Music) {
                showSelectedMusic(data)
            }
        })
    }

    private fun showRecyclerGrid() {
        rvMusic?.layoutManager = GridLayoutManager(this, 2)
        val gridMusicAdapter = GridMusicAdapter(list)
        rvMusic?.adapter = gridMusicAdapter
        gridMusicAdapter.setOnItemClickCallback(object :GridMusicAdapter.OnItemClickCallback{
            override fun onItemClicked(data: Music) {
                showSelectedMusic(data)
            }

        })

    }

    private fun showRecyclerCardView() {
        rvMusic.layoutManager = LinearLayoutManager(this)
        val cardViewMusicAdapter = CardViewMusicAdapter(list)
        rvMusic.adapter = cardViewMusicAdapter
    }

    private fun setActionBarTitle(title: String) {
        if (supportActionBar != null) {
            (supportActionBar as ActionBar).title = title
        }
    }

    private fun showSelectedMusic(music: Music) {
        Toast.makeText(this, "Kamu memilih " + music.name, Toast.LENGTH_SHORT).show()
    }



    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        setMode(item.itemId)
        return super.onOptionsItemSelected(item)
    }

    private fun setMode(selectedMode: Int) {
        when (selectedMode) {
            R.id.action_list -> {
                title = "Mode List"
                showRecyclerList()
            }

            R.id.action_grid -> {
                title = "Mode Grid"
                showRecyclerGrid()
            }

            R.id.action_cardview -> {
                title = "Mode Cardview"
                showRecyclerCardView()
            }
        }
    }
}
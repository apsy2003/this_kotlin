package com.apsy2003.harusamki

import android.media.MediaPlayer
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.apsy2003.harusamki.databinding.ItemMusicrecyclerBinding
import java.text.SimpleDateFormat

class MusicRecyclerAdapter : RecyclerView.Adapter<MusicRecyclerAdapter.Holder>() {
    var musicList = mutableListOf<Music>()
    var mediaPlayer: MediaPlayer? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val binding = ItemMusicrecyclerBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return Holder(binding)
    }

    override fun getItemCount(): Int {
        return  musicList.size
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        val music = musicList.get(position)
        holder.setMusic(music)
    }

    inner class Holder(val binding: ItemMusicrecyclerBinding) : RecyclerView.ViewHolder(binding.root) {
        var currentMusic:Music? = null

        init {
            binding.btnPlay.setOnClickListener {
                if(currentMusic?.isPlay == false) {
                    if (mediaPlayer != null) {
                        mediaPlayer?.release()
                        mediaPlayer = null
                    }
                    mediaPlayer = MediaPlayer.create(itemView.context, currentMusic?.getMusicUri())
                    mediaPlayer?.start()
                    currentMusic?.isPlay = true
                } else {
                    mediaPlayer?.stop()
                    mediaPlayer = null
                    currentMusic?.isPlay = false
                }
                setPlayButton()
            }
        }

        fun setMusic(music:Music) {
            binding.run {
                imageAlbum.setImageURI(music.getAlbumUri())
                textArtist.text = music.artist
                textTitle.text = music.title

                val duration = SimpleDateFormat("mm:ss").format(music.duration)
                textDuration.text = duration
            }
            currentMusic = music
            setPlayButton()
        }

        fun setPlayButton() {
            if(currentMusic?.isPlay == false) {
                binding.btnPlay.setImageResource(android.R.drawable.ic_media_play)
            } else {
                binding.btnPlay.setImageResource(android.R.drawable.ic_media_pause)
            }
        }
    }
}
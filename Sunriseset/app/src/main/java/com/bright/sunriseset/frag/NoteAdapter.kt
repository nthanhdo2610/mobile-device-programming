package com.bright.sunriseset.frag

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.bright.sunriseset.R
import com.bright.sunriseset.entity.Note

class NoteAdapter(private val notes: List<Note>) : RecyclerView.Adapter<NoteAdapter.NoteViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.note_layout, parent, false)
        return NoteViewHolder(view)
    }

    override fun getItemCount() = notes.size

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        val title: TextView = holder.view.findViewById(R.id.text_view_title)
        val content: TextView = holder.view.findViewById(R.id.text_view_note)
        title.text = notes[position].title
        content.text = notes[position].content

        holder.view.setOnClickListener {
            // Set the Navigation action
            val action = ListNoteFragmentDirections.actionListNoteFragmentToAddNoteFragment()
            // add the selected Note
            action.note = notes[position]
            Navigation.findNavController(it).navigate(action)
        }
    }

    class NoteViewHolder(val view: View) : RecyclerView.ViewHolder(view)
}
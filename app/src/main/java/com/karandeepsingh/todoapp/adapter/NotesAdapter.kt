package com.karandeepsingh.todoapp.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.karandeepsingh.todoapp.R
import com.karandeepsingh.todoapp.database.NoteEntity

class NotesAdapter(val context:Context, private val listener:Listener):RecyclerView.Adapter<NotesAdapter.NotesViewHolder>() {
    private val allNotesList=ArrayList<NoteEntity>()
    class NotesViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
    {
        val txtNoteText:TextView=itemView.findViewById(R.id.txtNoteText)
        val imgRemove:ImageView=itemView.findViewById(R.id.imgRemove)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NotesViewHolder {
        val view=LayoutInflater.from(parent.context).inflate(R.layout.single_todo_note,parent,false)
        return NotesViewHolder(view)
    }

    override fun onBindViewHolder(holder: NotesViewHolder, position: Int) {
        val noteEntity=allNotesList[position]
        holder.txtNoteText.text=noteEntity.text
        holder.imgRemove.setOnClickListener {
            listener.onClick(noteEntity)
        }
    }

    override fun getItemCount(): Int {
        return allNotesList.size
    }
    interface Listener
    {
        fun onClick(noteEntity: NoteEntity)
    }
    fun onUpdate(updateList:List<NoteEntity>)
    {
        allNotesList.clear()
        allNotesList.addAll(updateList)
        notifyDataSetChanged()

    }
}
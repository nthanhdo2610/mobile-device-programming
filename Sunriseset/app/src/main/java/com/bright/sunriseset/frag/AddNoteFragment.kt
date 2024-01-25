package com.bright.sunriseset.frag

import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import com.bright.sunriseset.databinding.FragmentAddNoteBinding
import com.bright.sunriseset.db.NoteDatabase
import com.bright.sunriseset.entity.Note
import com.bright.sunriseset.toast
import kotlinx.coroutines.launch

class AddNoteFragment : BaseNoteFragment() {
    private var note: Note? = null
    private lateinit var binding: FragmentAddNoteBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentAddNoteBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        // Receive the note arguments
        arguments?.let {
            // get the note value from the HomeFragment using Bundle instance
            note = AddNoteFragmentArgs.fromBundle(it).note
            binding.title.setText(note?.title)
            binding.editNote.setText(note?.content)
        }
        // deletion logic
        binding.buttonDelete.setOnClickListener {
            if (note != null) deleteNote() else context?.toast("Cannot Delete")
        }
        // Set the listener for the FAB
        binding.buttonSave.setOnClickListener { view ->
            // Retrieve the values from the EditText fields
            val noteTitle = binding.title.text.toString()
            val noteBody = binding.editNote.text.toString()
            // Check the input values are empty, then set the error message and give the focus
            if (noteTitle.isEmpty()) {
                binding.title.error = "Title Required"
                binding.title.requestFocus()
                return@setOnClickListener // stop further execution ie returning at the end of the setOnClickListener
            }

            if (noteBody.isEmpty()) {
                binding.editNote.error = "Body Required"
                binding.editNote.requestFocus()
                return@setOnClickListener // stop further execution ie returning at the end of the setOnClickListener
            }
            launch {
                context?.let {
                    val mNote = Note(noteTitle, noteBody)
                    // note == null means Inserting a new Note
                    if (note == null) {
                        NoteDatabase(it).getNoteDao().addNote(mNote)
                        it.toast("Note Saved")
                    } else {
                        // Update the note
                        mNote.id = note!!.id
                        NoteDatabase(it).getNoteDao().updateNote(mNote)
                        it.toast("Note Updated")
                    }
                    // after adding a note need to return to Home_Fragment as per the navigation directions
                    val action = AddNoteFragmentDirections.actionAddNoteFragmentToListNoteFragment()
                    Navigation.findNavController(view).navigate(action)

                }
            }

        }
    }

    private fun deleteNote() {
        AlertDialog.Builder(context).apply {
            setTitle("Are you sure?")
            setMessage("You cannot undo this operation")
            setPositiveButton("Yes") { _, _ ->
                launch {
                    NoteDatabase(context).getNoteDao().deleteNote(note!!)
                    val action = AddNoteFragmentDirections.actionAddNoteFragmentToListNoteFragment()
                    Navigation.findNavController(requireView()).navigate(action)
                }
            }
            setNegativeButton("No") { _, _ ->

            }
        }.create().show()
    }
}
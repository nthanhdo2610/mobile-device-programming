package com.bright.sunriseset.frag

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.bright.sunriseset.databinding.FragmentListNoteBinding
import com.bright.sunriseset.db.NoteDatabase
import kotlinx.coroutines.launch

class ListNoteFragment : BaseNoteFragment() {

    private lateinit var binding: FragmentListNoteBinding
    override fun onCreateView(
        layoutInflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentListNoteBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        // adapter content cannot change the size of the RecyclerView itself, it's fixed - Optimize the performance
        binding.recyclerViewNotes.setHasFixedSize(true)
        binding.recyclerViewNotes.layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)

        // Retrieve all notes from database to RecyclerView using Coroutines
        launch {
            /* here context is the getContext() from the Fragment, if the context
            * is not null, let's execute the block of code using let scope function
            * with the argument it's context object - Inline functions
            * similar like  if(context!=null){}*/


            context?.let{
                val notes = NoteDatabase(it).getNoteDao().getAllNotes()
                binding.recyclerViewNotes.adapter = NoteAdapter(notes)
            }
        }
        binding.buttonAdd.setOnClickListener {
            // After Rebuild, you will get HomeFragmentDirections automatically,
            // call the navigation action id given in the Navigation graph
            val action = ListNoteFragmentDirections.actionListNoteFragmentToAddNoteFragment()
            // Navigate to the action by passing view and call navigate by passing action
            Navigation.findNavController(it).navigate(action)
        }
    }
}
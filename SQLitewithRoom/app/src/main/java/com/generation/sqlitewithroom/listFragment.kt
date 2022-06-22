package com.generation.sqlitewithroom

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.generation.sqlitewithroom.adapter.UserAdapter
import com.generation.sqlitewithroom.databinding.FragmentListBinding


class listFragment : Fragment() {
private lateinit var binding : FragmentListBinding
private val mainViewModel : MainViewModel by activityViewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentListBinding.inflate(layoutInflater, container, false)



        val adapter = UserAdapter()
        binding.recyclerUser.layoutManager = LinearLayoutManager(context)
        binding.recyclerUser.adapter = adapter
        binding.recyclerUser.setHasFixedSize(true)

        mainViewModel.selectUsers.observe(viewLifecycleOwner){
            if(it!=null) {
                adapter.setList(it)
            }
        }

        binding.addUsers.setOnClickListener{
            findNavController().navigate(R.id.action_listFragment_to_addFragment)
        }
        return  binding.root
    }
}
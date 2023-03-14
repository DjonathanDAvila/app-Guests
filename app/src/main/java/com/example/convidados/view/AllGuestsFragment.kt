package com.example.convidados.view

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.convidados.constants.DataBaseConstants
import com.example.convidados.databinding.FragmentAllGuestsBinding
import com.example.convidados.view.adapter.GuestsAdapter
import com.example.convidados.view.listener.OnGuestListener
import com.example.convidados.viewmodel.AllGuestsViewModel

class AllGuestsFragment : Fragment() {

    private var _binding: FragmentAllGuestsBinding? = null
    private val binding get() = _binding!!

    //val viewModel: AllGuestsViewModel by viewModels()
    private lateinit var viewModel: AllGuestsViewModel
    private val adapter = GuestsAdapter()

    /**
     * O onCreateView é um método chamado quando a Fragment é criada, antes que sua UI seja exibida.
     * Ele é usado para inflar e retornar o layout da UI da Fragment.
     */
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, b: Bundle?): View {
        viewModel = ViewModelProvider(this).get(AllGuestsViewModel::class.java)
        _binding = FragmentAllGuestsBinding.inflate(inflater, container, false)

        return binding.root
    }

    /**
     * O onCreateView geralmente é usado para configurar e definir os elementos da UI da Fragment,
     * como botões, campos de texto, imagens, etc.
     */
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // Atribui um layout que diz como a RecyclerView se comporta
        binding.recyclerAllGuests.layoutManager = LinearLayoutManager(context)
        // Define um adapater - Faz a ligação da RecyclerView com a listagem de itens
        binding.recyclerAllGuests.adapter = adapter

        val listener = object : OnGuestListener{
            override fun onClick(id: Int) {
                val intent = Intent(context, GuestFormActivity::class.java)
                val bundle = Bundle()
                bundle.putInt(DataBaseConstants.GUEST.ID, id)
                intent.putExtras(bundle)
                startActivity(intent)
            }

            override fun onDelete(id: Int) {
                viewModel.delete(id)
                viewModel.getAll()
            }
        }

        // Cria os observadores
        observe()

        adapter.attachListener(listener)
    }

    override fun onResume() {
        super.onResume()
        viewModel.getAll()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun observe() {
        viewModel.guests.observe(viewLifecycleOwner) {
            val s = ""
            adapter.updateGuests(it)
        }
    }
}
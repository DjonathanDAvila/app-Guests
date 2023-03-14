package com.example.convidados.view.adapter

import android.view.LayoutInflater

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.convidados.databinding.RowGuestBinding
import com.example.convidados.model.GuestModel
import com.example.convidados.view.listener.OnGuestListener
import com.example.convidados.view.adapter.viewholder.GuestsViewHolder

class GuestsAdapter : RecyclerView.Adapter<GuestsViewHolder>() {

    // Lista de convidados
    private var guestList: List<GuestModel> = listOf()
    private lateinit var listener: OnGuestListener

    /**
     * Faz a criação do layout da linha
     * Faz a criação de várias linhas que vão mostrar cada um dos convidados
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GuestsViewHolder {
        val item = RowGuestBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return GuestsViewHolder(item, listener)
    }

    /**
     * Para cada linha, este método é chamado
     * É responsável por atribuir os valores de cada item para uma linha específica
     */
    override fun onBindViewHolder(holder: GuestsViewHolder, position: Int) {
        holder.bind(guestList[position])
    }

    /**
     * Qual o tamanho da RecyclerView
     */
    override fun getItemCount(): Int {
        return guestList.count()
    }

    /**
     * Atualização da lista de convidados
     */
    fun updateGuests(list: List<GuestModel>) {
        guestList = list
        notifyDataSetChanged() // isso "fala" para a Recycler view -> Ei, recebi novas informações, se atualize
    }

    /**
     * Eventos na listagem
     */
    fun attachListener(guestListener: OnGuestListener) {
        listener = guestListener
    }

}
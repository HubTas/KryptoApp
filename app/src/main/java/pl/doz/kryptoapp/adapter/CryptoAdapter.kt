package pl.doz.kryptoapp.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import pl.doz.kryptoapp.R
import pl.doz.kryptoapp.model.CryptoModel


class CryptoAdapter(
        private val onClickNavigate: (CryptoModel) -> Unit,
    ) : RecyclerView.Adapter<CryptoAdapter.ItemViewHolder>() {

    private var dataset: MutableList<CryptoModel> = mutableListOf()
    private var filteredList: List<CryptoModel> = dataset

    class ItemViewHolder(public val view: View) : RecyclerView.ViewHolder(view) {
        val nameText: TextView = view.findViewById(R.id.nameText)
        val rankText: TextView = view.findViewById(R.id.rankText)
        val coinCard: CardView = view.findViewById(R.id.coinCard)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val adapterLayout = LayoutInflater.from(parent.context)
            .inflate(R.layout.crypto_list, parent, false)
        return ItemViewHolder(adapterLayout)
    }

    override fun getItemCount(): Int {
        return filteredList.size
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: CryptoAdapter.ItemViewHolder, position: Int) {
        val item = filteredList[position]
        with(holder) {
            nameText.text =  item.name
            rankText.text =  "#"+item.rank.toString()
            coinCard.setOnClickListener{
                onClickNavigate(item)
            }
        }
    }

    fun addData(list: List<CryptoModel>) {
        dataset.clear()
        dataset.addAll(list)
        filteredList = dataset
        notifyDataSetChanged()
    }

    fun filter(query: String) {
        filteredList = if (query.isNotEmpty()) {
            dataset.filter { item ->
                item.name.contains(query, ignoreCase = true)
            }
        } else {
            dataset
        }
        notifyDataSetChanged()
    }
}

package com.example.kotlinpractica.presentation.view

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.kotlinpractica.R
import com.example.kotlinpractica.data.model.SuperHeroModel
import com.example.kotlinpractica.databinding.FragmentSuperHerosBinding
import com.example.kotlinpractica.presentation.view.recyclerview.superhero.CellClickListener
import com.example.kotlinpractica.presentation.view.recyclerview.superhero.ItemsRecyclerAdapter
import com.example.kotlinpractica.presentation.view.recyclerview.superhero.OnLoadMoreListener
import com.example.kotlinpractica.presentation.view.recyclerview.superhero.RecyclerViewLoadMoreScroll
import com.example.kotlinpractica.presentation.viewmodel.SuperHeroViewModel
import com.google.gson.Gson




/**
 * A simple [Fragment] subclass.
 * Use the [SuperHerosFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class SuperHerosFragment : Fragment(), CellClickListener {

    private lateinit var binding: FragmentSuperHerosBinding

    private val superHeroViewModel: SuperHeroViewModel by viewModels()

    lateinit var adapter: ItemsRecyclerAdapter
    lateinit var scrollListener: RecyclerViewLoadMoreScroll
    lateinit var mLayoutManager: RecyclerView.LayoutManager
    private lateinit var itemsRecyclerView: RecyclerView

    private lateinit var navController: NavController

    private var rootView: View? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = FragmentSuperHerosBinding.inflate(layoutInflater)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_super_heros, container, false)
    }

    private fun setRVLayoutManager() {
        mLayoutManager = LinearLayoutManager(activity)
        itemsRecyclerView.layoutManager = mLayoutManager
        itemsRecyclerView.setHasFixedSize(true)
    }

    private  fun setRVScrollListener() {
        mLayoutManager = LinearLayoutManager(activity)
        scrollListener = RecyclerViewLoadMoreScroll(mLayoutManager as LinearLayoutManager)
        scrollListener.setOnLoadMoreListener(object : OnLoadMoreListener {
            override fun onLoadMore() {
                loadMoreData()
            }
        })
        itemsRecyclerView.addOnScrollListener(scrollListener)
    }

    fun loadMoreData() {
        adapter.addLoadingView()
        superHeroViewModel.loadSuperHeros()
    }

    @SuppressLint("NotifyDataSetChanged")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        navController = Navigation.findNavController(view)

        superHeroViewModel.loadSuperHeros()

        superHeroViewModel.event.observe(viewLifecycleOwner) {
            if (it == 1) {
                itemsRecyclerView = view.findViewById(R.id.rvElement)
                adapter = ItemsRecyclerAdapter(superHeroViewModel.getTotalSuperHeros(),this)
                adapter.notifyDataSetChanged()
                itemsRecyclerView.adapter = adapter
                setRVLayoutManager()
                setRVScrollListener()
            } else {
                adapter.removeLoadingView()
                adapter.addData(superHeroViewModel.getTotalSuperHeros())
                scrollListener.setLoaded()
                adapter.notifyDataSetChanged()
            }
        }
    }

    override fun onCellClickListener(data: SuperHeroModel) {
        val gson = Gson()
        val jsonSuperModel = gson.toJson(data)
        val bundle = bundleOf("superHeroModel" to jsonSuperModel)
        superHeroViewModel.chunkSuperHero = 1
        navController.navigate(R.id.superHeroDetaitlsFragment,bundle)
    }
}
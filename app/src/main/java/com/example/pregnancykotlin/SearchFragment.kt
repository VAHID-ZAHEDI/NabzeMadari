package com.example.pregnancykotlin

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.arlib.floatingsearchview.FloatingSearchView
import com.example.pregnancykotlin.di.component.DaggerInstanceComponent
import com.example.pregnancykotlin.enum.Status
import com.example.pregnancykotlin.main.adapters.SearchAdapter
import com.example.pregnancykotlin.models.Content
import kotlinx.android.synthetic.main.fragment_search.*

class SearchFragment : BaseFragment() {
    private lateinit var contents: List<Content>
    var templist: ArrayList<Content> = ArrayList()
    private lateinit var searchAdapter: SearchAdapter

    //    private lateinit var templist:List<Content>


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_search, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getData()
    }

    private fun getData() {
        var mainViewModel = DaggerInstanceComponent.builder().build().getMainViewModel()
        mainViewModel.getAllContent(getToken()).observe(viewLifecycleOwner, Observer {
            when (it.status) {
                Status.SUCCESS -> {
                    contents = it.data!!
                    rv_search.apply {
                        layoutManager = LinearLayoutManager(activity)
                        hasFixedSize()
                        searchAdapter = SearchAdapter(activity as Context, contents)
                        adapter = searchAdapter
                    }
                    search()

                }
            }

        })
    }

    private fun search() {

        floatingSearchView.setOnQueryChangeListener { oldQuery, newQuery ->
            Log.d("bbbz", "search: "+contents.size)
            templist.clear()
            for (content in contents) {

                    if (content.title.contains(newQuery) ) {

                        templist.add(content)
//                        Log.d("ooos", "search: " + content.title)
                    searchAdapter.updateRecyclerView(templist)
                        Log.d("zzz", "search: " + templist.size)


                    }

                }
            }

    }


}
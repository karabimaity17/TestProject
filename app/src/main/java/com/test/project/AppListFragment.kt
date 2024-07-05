package com.test.project

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.test.project.databinding.PageApplicationListBinding

class AppListFragment : Fragment() {

    private var _binding: PageApplicationListBinding? = null
    private val binding get() = _binding!!

    private val viewModel: ApplicationViewModel by viewModels()
    private lateinit var adapter: ApplicationAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = PageApplicationListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adapter = ApplicationAdapter()
        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerView.adapter = adapter

        binding.searchText.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                viewModel.setSearchQuery(s.toString())
            }

            override fun afterTextChanged(s: Editable?) {}
        })
//        viewModel.apps.observeForever { users ->
//            adapter.submitList(users)
//        }
        viewModel.filteredApps.observe(viewLifecycleOwner) { apps ->
            adapter.submitList(apps)
        }

        viewModel.setSearchQuery("")
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
package com.example.testandroid.ui.viewest


import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.navigation.navGraphViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.testandroid.R
import com.example.testandroid.data.entities.MovieEntity
import com.example.testandroid.data.model.ResourceStatus
import com.example.testandroid.databinding.FragmentPopularBinding
import com.example.testandroid.databinding.FragmentViewestBinding
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class ViewestFragment : Fragment(), ViewestMovieItemAdapter.OnMovieClickListener {

    private var _binding: FragmentViewestBinding? = null

    private val binding get() = _binding!!

    private val viewModel: ViewestViewModel by navGraphViewModels(R.id.nav_graph) {
        defaultViewModelProviderFactory
    }

    private lateinit var viewestMovieItemAdapter: ViewestMovieItemAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentViewestBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.rvMovies.layoutManager = LinearLayoutManager(context)

        viewModel.fetchViewestMovies.observe(viewLifecycleOwner, Observer {
            when (it.resourceStatus) {
                ResourceStatus.LOADING -> {
                    Log.e("fetchViewestMovies", "Loading")
                }
                ResourceStatus.SUCCESS  -> {
                    Log.e("fetchViewestMovies", "Success")
                    viewestMovieItemAdapter = ViewestMovieItemAdapter(it.data!!, this@ViewestFragment)
                    binding.rvMovies.adapter = viewestMovieItemAdapter
                }
                ResourceStatus.ERROR -> {
                    Log.e("fetchViewestMovies", "Failure: ${it.message} ")
                    Toast.makeText(requireContext(), "Failure: ${it.message}", Toast.LENGTH_SHORT)
                        .show()
                }
            }
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onMovieClick(movieEntity: MovieEntity) {
        val action = ViewestFragmentDirections.actionHomeFragmentToDetailFragment(movieEntity)
        findNavController().navigate(action)
    }
}
package com.notgodzilla.android.myapplication.ui

import android.os.Bundle
import android.view.*
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.notgodzilla.android.myapplication.R
import com.notgodzilla.android.myapplication.api.Employee
import com.notgodzilla.android.myapplication.api.EmployeeListType
import com.notgodzilla.android.myapplication.databinding.FragmentEmployeeDirectoryBinding
import kotlinx.coroutines.launch

class EmployeeDirectoryFragment : Fragment() {

    private var _binding: FragmentEmployeeDirectoryBinding? = null
    private val binding
        get() = checkNotNull(_binding)
    private val viewModel: EmployeeDirectoryViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentEmployeeDirectoryBinding.inflate(inflater, container, false)
        binding.employeeDirectory.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.directoryUIState.collect { state ->
                    if (state.isRefreshing) {
                        binding.swipeLayout.isRefreshing = true
                        binding.errorText.visibility = View.GONE
                        binding.swipeLayout.isRefreshing = true
                        binding.employeeDirectory.visibility = View.GONE
                    } else {
                        binding.swipeLayout.isRefreshing = false
                        binding.swipeLayout.isRefreshing = false
                        binding.employeeDirectory.visibility = View.VISIBLE
                        handleDirectoryState(state)
                    }
                }
            }
        }


        binding.swipeLayout.setOnRefreshListener {
            viewModel.getEmployeesRefresh()
        }
    }

    private fun handleDirectoryState(state: EmployeeDirectoryUIState) {
        if (state.error) {
            binding.errorText.visibility = View.VISIBLE
            binding.errorText.setText(R.string.generic_error)
            binding.employeeDirectory.visibility = View.GONE
        } else if (state.employees.isEmpty()) {
            binding.errorText.visibility = View.VISIBLE
            binding.errorText.setText(R.string.no_employees_found)
            binding.employeeDirectory.visibility = View.GONE
        } else {
            binding.errorText.visibility = View.GONE
            val adapter = EmployeeDirectoryAdapter(
                state.employees
            ) {
                onEmployeeClicked(it)
            }
            binding.employeeDirectory.adapter = adapter
            adapter.submitList(state.employees)
        }
    }

    private fun onEmployeeClicked(employee: Employee) {
        findNavController().navigate(
            EmployeeDirectoryFragmentDirections.actionEmployeeDirectoryFragmentToEmployeeDetailFragment(
                employee.fullName, employee.emailAddress, employee.photoUrlLarge
            )
        )

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.directory_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.valid_employee_list -> {
                viewModel.getEmployees(EmployeeListType.VALID)
                true
            }
            R.id.malformed_employee_list -> {
                viewModel.getEmployees(EmployeeListType.MALFORMED)
                true
            }
            R.id.empty_employee_list -> {
                viewModel.getEmployees(EmployeeListType.EMPTY)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
package com.notgodzilla.android.myapplication.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.notgodzilla.android.myapplication.R
import com.notgodzilla.android.myapplication.api.Employee
import com.notgodzilla.android.myapplication.databinding.ListEmployeeViewBinding


class EmployeeDirectoryAdapter(
    private val employees: List<Employee>,
    private val onClickEmployee: (employee: Employee) -> Unit

) : ListAdapter<Employee, EmployeeDirectoryViewHolder>(DIFF) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EmployeeDirectoryViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ListEmployeeViewBinding.inflate(inflater, parent, false)
        return EmployeeDirectoryViewHolder(binding)
    }

    override fun onBindViewHolder(holder: EmployeeDirectoryViewHolder, position: Int) {
        val employee = employees[position]
        holder.bind(employee) {
            onClickEmployee(it)
        }
    }

    override fun getItemCount() = employees.size

    companion object {
        private val DIFF = object : DiffUtil.ItemCallback<Employee>() {
            override fun areItemsTheSame(oldItem: Employee, newItem: Employee): Boolean {
                return oldItem.UUID == newItem.UUID
            }

            override fun areContentsTheSame(oldItem: Employee, newItem: Employee): Boolean {
                return oldItem == newItem
            }
        }
    }
}


class EmployeeDirectoryViewHolder(private val binding: ListEmployeeViewBinding) :
    RecyclerView.ViewHolder(binding.root) {
    fun bind(employee: Employee, onClickEmployee: (employee: Employee) -> Unit) {
        binding.root.setOnClickListener { onClickEmployee(employee) }
        binding.employeeName.text = employee.fullName
        binding.employeeTeam.text = employee.team
        binding.employeePhoto.load(employee.photoUrlSmall) {
            placeholder(R.drawable.employee_portrait_placeholder)
        }
    }
}



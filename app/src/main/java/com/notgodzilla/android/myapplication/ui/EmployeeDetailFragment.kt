package com.notgodzilla.android.myapplication.ui

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import coil.load
import com.notgodzilla.android.myapplication.R
import com.notgodzilla.android.myapplication.databinding.FragmentEmployeeDetailBinding

class EmployeeDetailFragment : Fragment() {

    private var _binding: FragmentEmployeeDetailBinding? = null
    private val binding
        get() = checkNotNull(_binding)
    private val viewModel: EmployeeDirectoryViewModel by viewModels()
    private val args: EmployeeDetailFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentEmployeeDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    private fun sendEmail(employeeEmail: String) {
        val emailIntent = Intent(Intent.ACTION_SENDTO).apply {
            type = "text/plain"
            Uri.parse("mailto:").also { data = it }
            putExtra(Intent.EXTRA_EMAIL, arrayOf(employeeEmail))
        }

        startActivity(
            Intent.createChooser(
                emailIntent,
                getString(R.string.choose_email_client)
            )
        )

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.employeeDetailName.text = args.employeeName
        binding.employeeDetailEmail.text = args.employeeEmail
        binding.employeeDetailEmailIcon.setOnClickListener { sendEmail(args.employeeEmail)}
        binding.employeeDetailPhoto.load(args.employeePhotoUrl) {
            placeholder(R.drawable.employee_portrait_placeholder)
        }

    }
}
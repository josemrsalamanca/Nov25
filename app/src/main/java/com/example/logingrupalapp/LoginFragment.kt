package com.example.logingrupalapp

import android.app.Application
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.logingrupalapp.databinding.FragmentLoginBinding
import com.example.logingrupalapp.model.UsuarioEntidad
import com.example.logingrupalapp.presentation.LoginViewModel
import com.example.logingrupalapp.presentation.LoginViewModelFactory
import com.example.logingrupalapp.util.StringUtils
import java.util.regex.Pattern

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class LoginFragment : Fragment() {

    private var _binding: FragmentLoginBinding? = null
    private lateinit var loginViewModel: LoginViewModel

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupViewModel()
        establecerClickBotonIngresar()

        binding.btnRegistro.setOnClickListener {
            findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment)

        }
    }

    private fun setupViewModel() {
        val viewModelFactory= LoginViewModelFactory()
        loginViewModel = ViewModelProvider(this,viewModelFactory)[LoginViewModel::class.java]

        loginViewModel.state().observe(viewLifecycleOwner,{
            it?.let{safeString->
                handleState(safeString)
            }
        })
        loginViewModel.initViewModel("Hola mundo")
    }

    private fun handleState(safeString: String) {
        Toast.makeText(context,"$safeString en la clase: LoginFragment",Toast.LENGTH_LONG).show()
    }

    private fun establecerClickBotonIngresar() {

        binding.btnIngresoUsuario.setOnClickListener {

            validarUsuario()

        }
    }

    private fun validarUsuario() {
        val correoUsario = binding.edtCorreo.text.toString()
        val contrasniaUsuario = binding.edtContrasenia.text.toString()

        if (!correoUsario.isEmpty() && !contrasniaUsuario.isEmpty()) {
            if (StringUtils.validarCorreo(correoUsario) && StringUtils.validarContraseña(contrasniaUsuario)) {
                consultarUsuario(correoUsario, contrasniaUsuario)
                Toast.makeText(context, "Contraseña y correo validos", Toast.LENGTH_SHORT).show()

            } else {
                Toast.makeText(context, "Contraseña y correo no validos", Toast.LENGTH_SHORT).show()
            }
        } else {
            Toast.makeText(context, "Contraseña y correo vacios", Toast.LENGTH_SHORT).show()
        }

    }

    private fun consultarUsuario(correoUsario: String, contrasniaUsuario: String) {
        val obtenerUsuario = LoginGrupalApplication.DATABASE.registroDao.obtenerUsuario(
            correoUsario,
            contrasniaUsuario
        )
        Toast.makeText(context, "El usuario $obtenerUsuario",Toast.LENGTH_LONG).show()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
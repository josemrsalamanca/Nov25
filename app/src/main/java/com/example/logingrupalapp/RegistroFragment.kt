package com.example.logingrupalapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.example.logingrupalapp.databinding.FragmentRegistroBinding
import com.example.logingrupalapp.model.UsuarioEntidad
import com.example.logingrupalapp.util.StringUtils

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class RegistroFragment : Fragment() {

    private var _binding: FragmentRegistroBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentRegistroBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnVolverRegistro.setOnClickListener {
            findNavController().navigate(R.id.action_SecondFragment_to_FirstFragment)
        }

        iniciarCrearUsuario()

    }

    private fun iniciarCrearUsuario() {

       binding.btnCrearUsuarioRegistro.setOnClickListener {

        guardarRegistroUsuario()

       }
    }

    private fun guardarRegistroUsuario() {

        val correo = binding.edtCorreoRegsitro.text.toString()
        val contraseña = binding.edtContraseniaRegistro.text.toString()

        if (!correo.isEmpty() && !contraseña.isEmpty()) {

            if (StringUtils.validarCorreo(correo) && StringUtils.validarContraseña(contraseña)) {

                insertarRegistroDB(correo,contraseña)
                Toast.makeText(context, "Usuario creado exitosamente ", Toast.LENGTH_SHORT).show()

            } else {
                Toast.makeText(context, "Contraseña y correo no validos", Toast.LENGTH_SHORT).show()
            }
        } else {
            Toast.makeText(context, "Contraseña y correo vacios", Toast.LENGTH_SHORT).show()
        }

    }

    private fun insertarRegistroDB(correo : String, contraseña:String) {

        val nuevoUsuarioEntidad = UsuarioEntidad(correo = correo, contraseña = contraseña)
        LoginGrupalApplication.DATABASE.registroDao.insertAll(nuevoUsuarioEntidad)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
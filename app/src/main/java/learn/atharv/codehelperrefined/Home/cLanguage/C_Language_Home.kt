package learn.atharv.codehelperrefined.Home.cLanguage

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import learn.atharv.codehelperrefined.R
import learn.atharv.codehelperrefined.databinding.FragmentCLanguageHomeBinding
import learn.atharv.codehelperrefined.databinding.FragmentHomeBinding

class C_Language_Home : Fragment() {

    private var _binding: FragmentCLanguageHomeBinding? = null
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentCLanguageHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root
        binding.CLanguageStartLearning.setOnClickListener {
            it.findNavController().navigate(R.id.action_c_Language_Home_to_c_Language_Learning)
        }
        binding.CLanguageTestYourself.setOnClickListener {
            it.findNavController().navigate(R.id.action_c_Language_Home_to_c_Language_Quiz)
        }
        return root
    }

}
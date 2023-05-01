package learn.atharv.codehelperrefined.Home.cLanguage

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.navigation.fragment.navArgs
import learn.atharv.codehelperrefined.R

class C_Language_Details : Fragment() {


    val args : C_Language_DetailsArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_c__language__details, container, false)
        val title = args.title
        val data = args.data
        view.findViewById<TextView>(R.id.title).setText(title.toString())
        view.findViewById<TextView>(R.id.data).setText(data.toString())
        return view
    }
}
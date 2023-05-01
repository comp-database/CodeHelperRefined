package learn.atharv.codehelperrefined.Home.cLanguage

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import learn.atharv.codehelperrefined.Home.cLanguage.Adapter.C_Language_Learning_Adapter
import learn.atharv.codehelperrefined.Home.cLanguage.Model.C_Language_RVviewmodel
import learn.atharv.codehelperrefined.R

/**
 * A fragment representing a list of Items.
 */
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

private lateinit var viewModel: C_Language_RVviewmodel
lateinit var adapter: C_Language_Learning_Adapter
private lateinit var recyclerView : RecyclerView

class C_Language_Learning : Fragment() {

    private var param1: String? = null
    private var param2: String? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_c__language__learning_list, container, false)
        return view
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment Home.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            C_Language_Learning().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recyclerView = view.findViewById(R.id.list)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        recyclerView.setHasFixedSize(true)
        adapter = C_Language_Learning_Adapter()

        recyclerView.adapter = adapter
        viewModel = ViewModelProvider(this)[C_Language_RVviewmodel::class.java]
        viewModel.All_RV_Items.observe(viewLifecycleOwner, Observer {
            adapter.updateRvItems(it)
        })
    }
}
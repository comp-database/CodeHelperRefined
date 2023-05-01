package learn.atharv.codehelperrefined.Home.cLanguage

import android.graphics.Color
import android.graphics.Typeface
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import learn.atharv.codehelperrefined.Home.cLanguage.Model.C_Language_QuestionViewmodel
import learn.atharv.codehelperrefined.Home.cLanguage.Model.C_Language_RVviewmodel
import learn.atharv.codehelperrefined.Home.cLanguage.Model.Constants.dbref
import learn.atharv.codehelperrefined.Home.cLanguage.Model.Constants.getQuetions
import learn.atharv.codehelperrefined.Home.cLanguage.Model.Question
import learn.atharv.codehelperrefined.R
import learn.atharv.codehelperrefined.databinding.FragmentCLanguageQuizBinding


private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

private lateinit var viewModel: C_Language_QuestionViewmodel

class C_Language_Quiz : Fragment() , View.OnClickListener {

    private var _binding: FragmentCLanguageQuizBinding? = null
    private val binding get() = _binding!!
    private var selectedOptionPosition : Int = 0
    private var currentPosition = 0
    private var mCorrectAnswers: Int = 0
    lateinit var questionList : ArrayList<Question>

    private var param1: String? = null
    private var param2: String? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
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
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentCLanguageQuizBinding.inflate(inflater, container, false)
        val root: View = binding.root
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        questionList = ArrayList<Question>()
        binding.tvOptionOne.setOnClickListener(this)
        binding.tvOptionTwo.setOnClickListener(this)
        binding.tvOptionThree.setOnClickListener(this)
        binding.tvOptionFour.setOnClickListener(this)
        binding.btnSubmit.setOnClickListener(this)
        viewModel = ViewModelProvider(this)[C_Language_QuestionViewmodel::class.java]
        viewModel.All_Questions.observe(viewLifecycleOwner, Observer {
            questionList.addAll(it)
        })
        setQuestion()
    }

    private fun defaultOptionsView(){
        val options = ArrayList<TextView>()//
        binding.tvOptionOne.let {
            options.add(0,it)
        }
        binding.tvOptionTwo.let {
            options.add(1,it)
        }
        binding.tvOptionThree.let {
            options.add(2,it)
        }
        binding.tvOptionFour.let {
            options.add(3,it)
        }
        for(option in options){
            option.setTextColor(Color.parseColor("#7A8080"))
            option.typeface = Typeface.DEFAULT
            option.background = ContextCompat.getDrawable(requireContext(),R.drawable.background_rect)//
        }
    }
    private fun setQuestion() {
        defaultOptionsView()
        val question: Question = questionList[currentPosition]
        binding.progressbar.progress = currentPosition+1
        binding.tvProgress.text = "$currentPosition/${binding.progressbar.max}"

        binding.tvQues.text = question.question
        binding.tvOptionOne.text = question.optionOne
        binding.tvOptionTwo.text = question.optionTwo
        binding.tvOptionThree.text = question.optionThree
        binding.tvOptionFour.text = question.optionFour

        if (currentPosition == questionList.size) {
            binding.btnSubmit.text = "FINISH"
        } else {
            binding.btnSubmit.text = "NEXT"
        }
    }
    private fun selectedOptionView(tv: TextView, selectedOptionNum:Int){
        defaultOptionsView()
        selectedOptionPosition = selectedOptionNum
        tv.setTextColor(Color.parseColor("#FF0000"))//
        tv.setTypeface(tv.typeface , Typeface.BOLD)//
        tv.background = ContextCompat.getDrawable(requireContext(),R.drawable.selected_opt)//
    }
    override fun onClick(v: View?) {
        when(v?.id){
            R.id.tv_option_one ->{
                selectedOptionView(binding.tvOptionOne,1)
            }
            R.id.tv_option_two ->{
                selectedOptionView(binding.tvOptionTwo,2)
            }
            R.id.tv_option_three ->{
                selectedOptionView(binding.tvOptionThree,3)
            }
            R.id.tv_option_four ->{
                selectedOptionView(binding.tvOptionFour,4)
            }
            R.id.btn_submit->{
                if(selectedOptionPosition == 0){
                    currentPosition++
                    when{
                        currentPosition <= questionList.size ->{
                            setQuestion()
                        }
                        else ->{
                            Toast.makeText(requireContext(), "You have successfully completed the quiz. Your Score is : $mCorrectAnswers", Toast.LENGTH_SHORT).show()
                            findNavController().navigate(R.id.action_c_Language_Quiz_to_c_Language_Quiz_Result)
                        }
                    }
                }else{
                    val question = questionList.get(currentPosition-1)

                    if(question.correctOption != selectedOptionPosition){

                        answerView(selectedOptionPosition , R.drawable.wrong_opt)

                    }else {

                        mCorrectAnswers++

                    }

                    answerView(question.correctOption,R.drawable.correct_opt)

                    if(currentPosition==questionList.size){
                        binding.btnSubmit.text = "FINISH"
                    } else {
                        binding.btnSubmit.text = "NEXT"
                    }

                    selectedOptionPosition = 0
                }
            }
        }
    }
    private fun answerView(answer :Int , drawableView : Int){
        when(answer){
            1->{
                binding.tvOptionOne.background = ContextCompat.getDrawable(requireContext(),drawableView)
            }
            2->{
                binding.tvOptionTwo.background = ContextCompat.getDrawable(requireContext(),drawableView)
            }
            3->{
                binding.tvOptionThree.background = ContextCompat.getDrawable(requireContext(),drawableView)
            }
            4->{
                binding.tvOptionFour.background = ContextCompat.getDrawable(requireContext(),drawableView)
            }
        }
    }
}
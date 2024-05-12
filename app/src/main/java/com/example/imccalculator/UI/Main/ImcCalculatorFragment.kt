package com.example.imccalculator.UI.Main

import android.icu.text.DecimalFormat
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.imccalculator.R
import com.example.imccalculator.databinding.FragmentImcCalculatorBinding
import java.math.BigDecimal
import java.math.RoundingMode


class ImcCalculatorFragment : Fragment() {

    private var _binding: FragmentImcCalculatorBinding? = null
    private val binding get() = _binding!!
    private var isMaleSelected: Boolean = true
    private var isFemaleSelected: Boolean = false
    private var currentHeight: Double = 1.50
    private var currentWeight: Int = 60
    private var currentAge: Int = 20
    private var imc: Double = 0.0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentImcCalculatorBinding.inflate(inflater, container, false)
        initListeners()
        return binding.root
    }


    private fun initListeners() {
        binding.viewMale.setOnClickListener {
            isMaleSelected = true
            isFemaleSelected = false
            setGenderColor()
        }
        binding.viewFemale.setOnClickListener {
            isMaleSelected = false
            isFemaleSelected = true
            setGenderColor()
        }


        binding.rsHeight.addOnChangeListener { _, value, _ ->
            val df = DecimalFormat("#.#")
            val result = df.format(value)
            currentHeight =
                BigDecimal((value / 100).toDouble()).setScale(2, RoundingMode.HALF_UP).toDouble()
            binding.tvHeight.text = "$result cm"
        }


        binding.fbPlusweight.setOnClickListener {
            currentWeight++
            setWeight()
        }

        binding.fbSubtrackweight.setOnClickListener {

            currentWeight--
            setWeight()
        }

        binding.fbPlusage.setOnClickListener {
            currentAge++
            setAge()

        }

        binding.fbSubtrackage.setOnClickListener {
            currentAge--
            setAge()
        }

        binding.btCalculate.setOnClickListener {

            val resultImc = calculateImc()
            navigateToDetail(resultImc)
        }
    }

    private fun setGenderColor() {
        if (isMaleSelected) {
            binding.viewMale.setCardBackgroundColor(resources.getColor(R.color.purple_200))
            binding.viewFemale.setCardBackgroundColor(resources.getColor(R.color.purple_500))
        } else {
            binding.viewMale.setCardBackgroundColor(resources.getColor(R.color.purple_500))
            binding.viewFemale.setCardBackgroundColor(resources.getColor(R.color.purple_700))
        }
    }

    private fun setWeight() {
        binding.tvWeight.text = currentWeight.toString()
    }

    private fun setAge() {
        binding.tvAge.text = currentAge.toString()
    }

    private fun calculateImc(): String {


        imc = (currentWeight / (currentHeight * currentHeight))
        var imcRounded = BigDecimal(imc.toDouble()).setScale(2, RoundingMode.HALF_UP).toDouble()
        Log.w("IMC WEIGHT", currentWeight.toString())
        Log.w("IMC HEIGHT", currentHeight.toString())
        Log.w("IMC", imcRounded.toString())

        return imcRounded.toString()

    }

    private fun navigateToDetail(resultImc: String) {

        /*
        Navegar al otro fragmen sin pasar argumentos
        findNavController().navigate(R.id.action_imcCalculatorFragment2_to_detailImcFragment)
         */

        // Pasando argumentos
        findNavController().navigate(
            ImcCalculatorFragmentDirections.actionImcCalculatorFragment2ToDetailImcFragment(
                resultImc
            )
        )

    }

}
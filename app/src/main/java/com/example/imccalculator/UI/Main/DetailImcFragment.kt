package com.example.imccalculator.UI.Main

import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import com.ekn.gruzer.gaugelibrary.Range
import com.example.imccalculator.R
import com.example.imccalculator.databinding.FragmentDetailImcBinding


class DetailImcFragment : Fragment() {

    private var _binding: FragmentDetailImcBinding? = null
    private val binding get() = _binding!!

    private val args: DetailImcFragmentArgs by navArgs()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentDetailImcBinding.inflate(inflater, container, false)
        val view = binding.root

        listeners()

        return view
    }

    private fun listeners() {

        val resultImc = args.resultImc
        binding.tvResultImcNumber.text = resultImc

        binding.hgImc.minValue = 12.0
        binding.hgImc.maxValue = 40.0
        //VALOR VELOCIMETRO
        binding.hgImc.value = resultImc.toDouble()
        //COLOR DEL TEXTO MINIMO Y MAXIMO
        binding.hgImc.minValueTextColor = resources.getColor(R.color.light_blue_600)
        binding.hgImc.maxValueTextColor = resources.getColor(R.color.red)
        //COLOR DE FONDO
        //binding.hgImc.setGaugeBackGroundColor( resources.getColor(R.color.red))
        //COLOR DE LA AGUJA
        binding.hgImc.setNeedleColor( resources.getColor(R.color.red))
        //COLOR DEL VALOR DEBAJO DE LA AGUJA
        binding.hgImc.valueColor = resources.getColor(R.color.background_fragment)

        // RANGOS EN LOS QUE VA ESTAR LOS COLORES
        val range1 = Range().apply {
            color = Color.parseColor("#FF039BE5")
            from = 12.0
            to = 18.49
        }
        val range2 = Range().apply {

            color = Color.parseColor("#FF03DAC5")
            from = 18.5
            to = 24.99

        }

        val range3 = Range().apply {

            color = Color.parseColor("#FFEB3B")
            from = 25.00
            to = 29.99
        }

        val range4 = Range().apply {

            color = Color.parseColor("#FF9800")
            from = 30.00
            to = 34.99
        }
        val range5 = Range().apply {

            color = Color.parseColor("#E91E63")
            from = 35.00
            to = 40.00
        }
        // SE APLICA LO RANGOS
        binding.hgImc.addRange(range1)
        binding.hgImc.addRange(range2)
        binding.hgImc.addRange(range3)
        binding.hgImc.addRange(range4)
        binding.hgImc.addRange(range5)


        when (resultImc.toDouble()) {
            in 12.0..18.4 -> {
                binding.tvCalificacionImc.text =
                    getString(R.string.text_calification_imc_under_normal)
                binding.tvCalificacionImc.setTextColor(resources.getColor(R.color.light_blue_A200))
                binding.tvObservationByTheImc.text =
                    getString(R.string.text_observation_imc_under_normal)
            }
            in 18.5..24.99 -> {
                binding.tvCalificacionImc.text = getString(R.string.text_calification_imc_normal)
                binding.tvCalificacionImc.setTextColor(resources.getColor(R.color.teal_200))
                binding.tvObservationByTheImc.text = getString(R.string.text_observation_imc_normal)
            }

            in 25.0..29.99 -> {
                binding.tvCalificacionImc.text =
                    getString(R.string.text_calification_imc_over_weight)
                binding.tvCalificacionImc.setTextColor(resources.getColor(R.color.yellow))
                binding.tvObservationByTheImc.text =
                    getString(R.string.text_observation_imc_over_weight)
            }

            in 30.0..34.99 -> {
                binding.tvCalificacionImc.text =
                    getString(R.string.text_calification_imc_obesity_class1)
                binding.tvCalificacionImc.setTextColor(resources.getColor(R.color.orange))
                binding.tvObservationByTheImc.text =
                    getString(R.string.text_observation_imc_obesity_class1)
            }

            in 35.0..40.00 -> {
                binding.tvCalificacionImc.text =
                    getString(R.string.text_calification_imc_obesity_class2)
                binding.tvCalificacionImc.setTextColor(resources.getColor(R.color.orangeDark))
                binding.tvObservationByTheImc.text =
                    getString(R.string.text_observation_imc_obesity_class2)
            }

            else -> {
                binding.tvCalificacionImc.text = getString(R.string.text_calification_imc_error)
                binding.tvCalificacionImc.setTextColor(resources.getColor(R.color.black))
                binding.tvObservationByTheImc.text = ""
            }
        }
    }

    /*

    Composición corporal	Índice de masa corporal (IMC)
    Peso inferior al normal	Menos de 18.5
    Normal	18.5 – 24.9
    Peso superior al normal	25.0 – 29.9
    Obesidad	Más de 30.0
     */

}
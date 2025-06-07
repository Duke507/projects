package com.example.fragment_jakediaz_connorpenn

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.graphics.Color
import com.androidplot.xy.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
const val ARG_PARAM1 = "param1"
const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [LineFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class LineFragment : Fragment() {
    // TODO: Rename and change types of parameters
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
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_line, container, false)

        // Initialize the XYPlot
        val plot: XYPlot = view.findViewById(R.id.plot)

        // Create sample data from barandline.png
        val seriesA = SimpleXYSeries(listOf(4.3, 2.5, 3.5, 4.5), SimpleXYSeries.ArrayFormat.Y_VALS_ONLY, "a")
        val seriesB = SimpleXYSeries(listOf(2.4, 4.4, 1.8, 2.8), SimpleXYSeries.ArrayFormat.Y_VALS_ONLY, "b")
        val seriesC = SimpleXYSeries(listOf(2.0, 2.0, 3.0, 5.0), SimpleXYSeries.ArrayFormat.Y_VALS_ONLY, "c")

        // Create a formatter for the line graph
        val seriesAFormat = LineAndPointFormatter(Color.BLUE, null, null, null)
        val seriesBFormat = LineAndPointFormatter(Color.RED, null, null, null)
        val seriesCFormat = LineAndPointFormatter(Color.GREEN, null, null, null)

        // Add the series to the plot
        plot.addSeries(seriesA, seriesAFormat)
        plot.addSeries(seriesB, seriesBFormat)
        plot.addSeries(seriesC, seriesCFormat)

        // Set domain and range labels
        plot.setDomainStep(StepMode.SUBDIVIDE, 4.0)
        plot.setRangeStep(StepMode.SUBDIVIDE, 4.0)

        // Redraw the plot
        plot.redraw()

        return view
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment LineFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            LineFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}
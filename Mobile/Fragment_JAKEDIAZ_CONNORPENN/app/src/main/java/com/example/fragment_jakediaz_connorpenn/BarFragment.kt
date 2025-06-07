package com.example.fragment_jakediaz_connorpenn

import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.androidplot.util.PixelUtils
import com.androidplot.xy.*

class BarFragment : Fragment() {

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
        val view = inflater.inflate(R.layout.fragment_bar, container, false)

        // Initialize the XYPlot
        val plot: XYPlot = view.findViewById(R.id.plot)

        // Create sample data from barandline.png
        val seriesA = SimpleXYSeries(listOf(4.3, 2.5, 3.5, 4.5), SimpleXYSeries.ArrayFormat.Y_VALS_ONLY, "a")
        val seriesB = SimpleXYSeries(listOf(2.4, 4.4, 1.8, 2.8), SimpleXYSeries.ArrayFormat.Y_VALS_ONLY, "b")
        val seriesC = SimpleXYSeries(listOf(2.0, 2.0, 3.0, 5.0), SimpleXYSeries.ArrayFormat.Y_VALS_ONLY, "c")

        // Create a formatter for the bar graph
        val seriesAFormat = BarFormatter(Color.BLUE, Color.BLACK)
        val seriesBFormat = BarFormatter(Color.RED, Color.BLACK)
        val seriesCFormat = BarFormatter(Color.GREEN, Color.BLACK)

        // Add the series to the plot
        plot.addSeries(seriesA, seriesAFormat)
        plot.addSeries(seriesB, seriesBFormat)
        plot.addSeries(seriesC, seriesCFormat)

        // Configure the bar graph
        val barRenderer = plot.getRenderer(BarRenderer::class.java)
        barRenderer.setBarGroupWidth(BarRenderer.BarGroupWidthMode.FIXED_WIDTH, PixelUtils.dpToPix(25.0F))

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
         * @return A new instance of fragment BarFragment.
         */
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            BarFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}

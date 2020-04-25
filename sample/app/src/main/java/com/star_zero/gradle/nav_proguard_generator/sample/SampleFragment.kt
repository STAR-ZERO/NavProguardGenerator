package com.star_zero.gradle.nav_proguard_generator.sample

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.fragment.app.Fragment

class SampleFragment : Fragment(R.layout.fragment_sample) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.findViewById<Button>(R.id.button).setOnClickListener {
            val intent = Intent(
                requireContext(),
                Class.forName("com.star_zero.gradle.nav_proguard_generator.sample.feature.FeatureActivity")
            )
            startActivity(intent)
        }
    }
}

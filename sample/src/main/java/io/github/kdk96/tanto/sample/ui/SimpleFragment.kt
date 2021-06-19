package io.github.kdk96.tanto.sample.ui

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.google.android.material.button.MaterialButton
import io.github.kdk96.tanto.android.inject
import io.github.kdk96.tanto.sample.R
import io.github.kdk96.tanto.sample.data.Storage
import javax.inject.Inject

class SimpleFragment : Fragment(R.layout.fragment_simple) {

    @Inject
    lateinit var storage: Storage

    override fun onCreate(savedInstanceState: Bundle?) {
        inject()
        super.onCreate(savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view.findViewById<MaterialButton>(R.id.button).setOnClickListener {
            storage.clear()
        }
    }
}

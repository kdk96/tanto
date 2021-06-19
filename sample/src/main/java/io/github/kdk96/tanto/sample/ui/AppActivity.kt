package io.github.kdk96.tanto.sample.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.commit
import io.github.kdk96.tanto.android.inject
import io.github.kdk96.tanto.sample.R
import io.github.kdk96.tanto.sample.data.Storage
import javax.inject.Inject

class AppActivity : AppCompatActivity(R.layout.activity_app) {

    @Inject
    lateinit var storage: Storage

    override fun onCreate(savedInstanceState: Bundle?) {
        inject()
        super.onCreate(savedInstanceState)

        if (savedInstanceState == null) {
            supportFragmentManager.commit {
                add(R.id.container, SimpleFragment())
            }
        }
    }
}

package mvisample.toan.mviapplication

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import mvisample.toan.mviapplication.mvi.MainFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
        if (savedInstanceState == null) {
            val f = supportFragmentManager.beginTransaction()
            f.replace(R.id.container, MainFragment.newInstance())
            f.commit()
        }
    }
}

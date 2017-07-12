package mvisample.toan.mviapplication.dependencies

import dagger.Component
import mvisample.toan.mviapplication.MainFragment
import javax.inject.Singleton

/**
 * Created by Toan Vu on 7/11/17.
 */
@Singleton
@Component(modules = arrayOf(ApplicationModule::class))
interface AppComponent {
    fun inject(mainFragment: MainFragment)
}

package mvisample.toan.mviapplication.dependencies


import com.madsciencesoftware.mvitestenvironment.MainView.MainPresenter
import dagger.Module
import dagger.Provides
import mvisample.toan.mviapplication.common.Constant
import mvisample.toan.mviapplication.mvi.QueryInteractor
import mvisample.toan.mviapplication.api.GithubApiService
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

/**
 * Created by Toan Vu on 7/11/17.
 */
@Module
class ApplicationModule {

    @Provides
    @Singleton
    internal fun okHttpClient(): OkHttpClient {
        return OkHttpClient.Builder().build()
    }

    @Provides
    @Singleton
    internal fun retrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
                .baseUrl(Constant.BASE_URL)
                .client(okHttpClient)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build()
    }

    @Provides
    @Singleton
    internal fun provideGetApi(retrofit: Retrofit): GithubApiService {
        return retrofit.create<GithubApiService>(GithubApiService::class.java)
    }


    @Provides
    fun provideSearchInteractor(apiService: GithubApiService): QueryInteractor {
        return QueryInteractor(apiService)
    }

    @Provides
    fun provideMainPresenter(searchInteractor: QueryInteractor): MainPresenter {
        return MainPresenter(searchInteractor)
    }
}

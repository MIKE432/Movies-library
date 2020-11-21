package com.apusart

import android.app.Application
import android.content.Context
import com.apusart.moviesliblary.di.AppModule
import com.apusart.moviesliblary.ui.guest.init_activity.InitActivity
import com.apusart.moviesliblary.ui.guest.login_activity.LoginActivity
import com.apusart.moviesliblary.ui.logged.profile_activity.ProfileActivity
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class])
interface ApplicationComponent {
    fun inject(loginActivity: LoginActivity)
    fun inject(profileActivity: ProfileActivity)
    fun inject(initActivity: InitActivity)

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: MoviesLibraryApplication): Builder

        fun build(): ApplicationComponent
    }
}

class MoviesLibraryApplication: Application() {
    lateinit var appComponent: ApplicationComponent

    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerApplicationComponent
            .builder()
            .application(this)
            .build()
    }

}

val Context.appComponent get() = (applicationContext as MoviesLibraryApplication).appComponent
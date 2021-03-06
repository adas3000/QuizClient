package com.quiz.client

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.quiz.client.component.AppComponent
import com.quiz.client.component.DaggerAppComponent
import com.quiz.client.presenter.IQueueLoadPresenter
import com.quiz.client.presenter.QueueLoadPresenter
import com.quiz.client.service.QueueApiService
import com.quiz.client.view.IQueueLoadView
import es.dmoral.toasty.Toasty
import retrofit2.Retrofit
import javax.inject.Inject

class QueueLoadActivity : AppCompatActivity(), IQueueLoadView {

    @Inject
    lateinit var retrofit: Retrofit

    lateinit var queueLoadPresenter: IQueueLoadPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_queue_load_category)

        val appComponent: AppComponent = DaggerAppComponent.builder().build()
        retrofit = appComponent.provideRetrofit()

        val queueApiService:QueueApiService = retrofit.create(QueueApiService::class.java)

        queueLoadPresenter = QueueLoadPresenter(this,queueApiService)
        queueLoadPresenter.onGoToQueue()
    }

    override fun onBackPressed() {
        queueLoadPresenter.onDropFromQueue()
        startActivity(Intent(this, OpponentKindActivity::class.java))
        finish()
    }

    override fun onError(msg: String) {
        queueLoadPresenter.onDropFromQueue()
        Toasty.error(this, msg, Toasty.LENGTH_SHORT).show()
        startActivity(Intent(this, OpponentKindActivity::class.java))
        finish()
    }

    override fun onCannotDropFromQueue() {
        startActivity(Intent(this, OpponentKindActivity::class.java))
        finish()
    }

    override fun onSuccess(code:String) {
        val intent = Intent(this,WaitActivity::class.java).apply {
            putExtra("serial",code)
        }
        startActivity(intent)
        finish()
    }

    override fun onNoRoomsFounded() {
        queueLoadPresenter.onSearchGame()
    }
}

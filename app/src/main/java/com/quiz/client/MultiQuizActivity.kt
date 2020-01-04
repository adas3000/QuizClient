package com.quiz.client

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentTransaction
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.quiz.client.adapter.RecyclerViewStatsAdapter
import com.quiz.client.component.AppComponent
import com.quiz.client.component.DaggerAppComponent
import com.quiz.client.fragment.QuestionFragment
import com.quiz.client.fragment.StatsFragment
import com.quiz.client.model.Score
import com.quiz.client.presenter.IMultiQuizPresenter
import com.quiz.client.presenter.MultiQuizPresenter
import com.quiz.client.service.GameApiService
import com.quiz.client.util.countScore
import com.quiz.client.util.getApplicationToken
import com.quiz.client.view.IMultiQuizParent
import com.quiz.client.view.IMultiQuizView
import es.dmoral.toasty.Toasty
import kotlinx.android.synthetic.main.activity_quiz.*
import kotlinx.android.synthetic.main.fstats_layout.*
import retrofit2.Retrofit
import java.lang.NullPointerException
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class MultiQuizActivity : AppCompatActivity(), IMultiQuizParent, IMultiQuizView {

    @Inject
    lateinit var retrofit: Retrofit

    lateinit var gameApiService: GameApiService

    lateinit var multiQuizPresenter: IMultiQuizPresenter

    lateinit var game_code: String

    var allQuestionCount: Int = 0
    var correctCount: Int = 0



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_multi_quiz)

        val temp_game_code = this.intent.getStringExtra("game_code")

        if (temp_game_code == null) {
            throw NullPointerException("game_code is null")
        }

        game_code = temp_game_code.toString()

        val appComponent: AppComponent = DaggerAppComponent.builder().build()
        retrofit = appComponent.provideRetrofit()

        gameApiService = retrofit.create(GameApiService::class.java)
        multiQuizPresenter = MultiQuizPresenter(this, gameApiService)


        val ft: FragmentTransaction = supportFragmentManager.beginTransaction()
        ft.replace(R.id.multi_quiz_placeholder, QuestionFragment.newInstance(0, this))
        ft.commit()

        fstats_textView_goNext.setOnClickListener {

        }

    }


    override fun onBackPressed() {
        //todo alert
    }

    override fun onNextQuestion(correct: Boolean, time_remaining: Int) {

        var color: String = "#82DD55" // success color
        var points_to_add: Int = countScore(time_remaining.toString())

        if (correct) {
            correctCount++

            Toasty.success(this, "Good", Toasty.LENGTH_SHORT).show()
        } else {
            Toasty.error(this, "Wrong", Toasty.LENGTH_SHORT).show()
            color = "#E23636" // error color
        }
        rv_top.findViewHolderForAdapterPosition(allQuestionCount)
            ?.itemView?.findViewById<TextView>(R.id.textView_square)?.setBackgroundColor(
            Color.parseColor(color)
        )

        allQuestionCount++

        textView_question_count.setText(correctCount.toString() + "/" + allQuestionCount.toString())

        multiQuizPresenter.onupdateDeviceScoreInGame(game_code, getApplicationToken(), points_to_add.toString())
    }


    override fun onError(msg: String) {
        Toasty.error(this, msg, Toasty.LENGTH_SHORT).show()
        startActivity(Intent(this, MainActivity::class.java))
        finish()
    }

    override fun onupdateDeviceFinishedAnsweringToQuestionSuccess() {
        multiQuizPresenter.onCheckAllDevicesAnswered(game_code)
    }

    override fun oncheckAllDevicesAnsweredSuccess() {
        multiQuizPresenter.onFindScoreByUUID(game_code)
    }

    override fun onfindScoresByUUID(scores: List<Score>) {

        val sf = StatsFragment()
        sf.scores = scores

        val ft: FragmentTransaction = supportFragmentManager.beginTransaction()
        ft.replace(R.id.multi_quiz_placeholder, sf)
        ft.commit()
    }

    override fun onScoreDeviceUpdateSuccess() {
        multiQuizPresenter.onUpdateDeviceFinishedAnswering(game_code, getApplicationToken())
    }

    override fun onWaitForOthers() {
        //todo do waiting screen
        Toasty.normal(this,"Wait...",Toasty.LENGTH_SHORT).show()
        TimeUnit.SECONDS.sleep(1)
        multiQuizPresenter.onCheckAllDevicesAnswered(game_code)

    }

}

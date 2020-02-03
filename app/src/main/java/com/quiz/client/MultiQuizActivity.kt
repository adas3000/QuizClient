package com.quiz.client

import android.content.Intent
import android.os.Bundle
import android.os.PersistableBundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentTransaction
import com.quiz.client.component.AppComponent
import com.quiz.client.component.DaggerAppComponent
import com.quiz.client.fragment.QuestionFragment
import com.quiz.client.fragment.StatsFragment
import com.quiz.client.fragment.WaitFragment
import com.quiz.client.model.Score
import com.quiz.client.presenter.IMultiQuizPresenter
import com.quiz.client.presenter.MultiQuizPresenter
import com.quiz.client.service.GameApiService
import com.quiz.client.util.QuestionListKeeper
import com.quiz.client.util.countScore
import com.quiz.client.util.getApplicationToken
import com.quiz.client.view.IMultiQuizParent
import com.quiz.client.view.IMultiQuizView
import es.dmoral.toasty.Toasty
import retrofit2.Retrofit
import java.lang.NullPointerException
import javax.inject.Inject

class MultiQuizActivity : AppCompatActivity(), IMultiQuizParent, IMultiQuizView {

    companion object {
    private const val WAIT_FRAGMENT_TAG = "WAIT_FRAGMENT"
}

    @Inject
    lateinit var retrofit: Retrofit

    lateinit var gameApiService: GameApiService

    lateinit var multiQuizPresenter: IMultiQuizPresenter

    lateinit var game_code: String

    var allQuestionCount: Int = 0
    var correctCount: Int = 0
    var last_question_correct = false
    val mutableListTopHeader:MutableList<Int> = mutableListOf()
    var quiz_finished = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_multi_quiz)

        val temp_game_code = this.intent.getStringExtra("serial")

        if (temp_game_code == null) {
            throw NullPointerException("serial is null")
        }
        game_code = temp_game_code.toString()

        val appComponent: AppComponent = DaggerAppComponent.builder().build()
        retrofit = appComponent.provideRetrofit()

        gameApiService = retrofit.create(GameApiService::class.java)
        multiQuizPresenter = MultiQuizPresenter(this, gameApiService)



        val questionFragment =  QuestionFragment.newInstance(allQuestionCount, this,correctCount
                ,allQuestionCount)
        questionFragment.topHeaderList = mutableListTopHeader

        val ft: FragmentTransaction = supportFragmentManager.beginTransaction()
        ft.replace(R.id.multi_quiz_placeholder,questionFragment)
        ft.commit()
    }


    override fun onBackPressed() {
        //todo alert
    }

    override fun onNextQuestion(correct: Boolean, time_remaining: Int) {
        last_question_correct = correct
        var points_to_add: Int = countScore(time_remaining.toString())
        if (correct) {
            correctCount++
            mutableListTopHeader.add(1)
        } else {
            points_to_add = 0
            mutableListTopHeader.add(0)
        }
        allQuestionCount++


        if(allQuestionCount==QuestionListKeeper.questionListKeeper.size)
            quiz_finished = true


        multiQuizPresenter.onupdateDeviceScoreInGame(game_code, getApplicationToken(), points_to_add.toString())
    }


    override fun onError(msg: String) {
        println("ERROR:"+msg)
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
        multiQuizPresenter.onUpdateDeviceReadyForNextState(getApplicationToken(),false)

        val sf = StatsFragment.newInstance(false,true,last_question_correct,quiz_finished,game_code)
        sf.scores = scores
        sf.multiQuizPresenter = multiQuizPresenter
        sf.serial = getApplicationToken()

        val ft: FragmentTransaction = supportFragmentManager.beginTransaction()
        ft.replace(R.id.multi_quiz_placeholder, sf)
        ft.commit()
    }

    override fun onScoreDeviceUpdateSuccess() {
        multiQuizPresenter.onUpdateDeviceAnswerState(getApplicationToken(),true)
    }

    override fun onSaveInstanceState(outState: Bundle, outPersistentState: PersistableBundle) {

    }

    override fun onWaitForOthers() {

        var waitFragment = supportFragmentManager.findFragmentByTag(WAIT_FRAGMENT_TAG)

        if(waitFragment!=null && waitFragment.isVisible){
            waitFragment = waitFragment as WaitFragment
            waitFragment.doCheck()
        }
        else{
            val ft: FragmentTransaction = supportFragmentManager.beginTransaction()
            ft.replace(
                R.id.multi_quiz_placeholder, WaitFragment.newInstance(multiQuizPresenter,game_code), WAIT_FRAGMENT_TAG
            )
            ft.commit()//todo when 3 playesrs here was exception
            // java.lang.IllegalStateException: Can not perform this action after onSaveInstanceState propably onWaitForOthers method involed in multquizpresenter when everybody finished answering but check it

        }

    }

    override fun onPlayerReadySentSuccess() {
        Toasty.success(this,"Player ready sent successfully",Toasty.LENGTH_SHORT).show()
    }

    override fun onCheckNextQuestionAv() {
        val questionFragment =  QuestionFragment.newInstance(allQuestionCount, this,correctCount
                ,allQuestionCount)
        questionFragment.topHeaderList = mutableListTopHeader

        val ft: FragmentTransaction = supportFragmentManager.beginTransaction()
        ft.replace(R.id.multi_quiz_placeholder,questionFragment)
        ft.commit()
    }

    override fun onWaitForNextQuestionAv() {
        multiQuizPresenter.onNewQuestionCheck(game_code)
    }

    override fun onDeviceUpdateSuccess() {
        multiQuizPresenter.onNewQuestionCheck(game_code)
    }

    override fun onGameFinished() {

        val intent = Intent(this,FinishActivity::class.java).apply{
            putExtra("score",correctCount.toString())
            putExtra("all",allQuestionCount.toString())
        }
        startActivity(intent)
        finish()

    }
}

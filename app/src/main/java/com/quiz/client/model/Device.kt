package com.quiz.client.model

class Device {

    var nickname: String="unknown"
    var serial: String
    var answered_to_question: Boolean
    var ready_For_Next_Question: Boolean

    constructor(serial: String, answered_to_question: Boolean, ready_For_Next_Question: Boolean) {
        this.serial = serial
        this.answered_to_question = answered_to_question
        this.ready_For_Next_Question = ready_For_Next_Question
    }

    constructor(nickname: String, serial: String, answered_to_question: Boolean, ready_For_Next_Question: Boolean) {
        this.nickname = nickname
        this.serial = serial
        this.answered_to_question = answered_to_question
        this.ready_For_Next_Question = ready_For_Next_Question
    }


}
package com.example.zone.ModelClasses

import com.google.firebase.firestore.ServerTimestamp
import java.util.Date

data class Chat(
    var sender: String? = "",
    var message: String? = "",
    var receiver: String? = "",
    @field:JvmField
    var isseen: Boolean? = null,
    var url: String = "",
    var messageId: String? = "",
    @ServerTimestamp val createdAt: Date? = null
)
/*
class Chat {
    private var sender: String = ""
    private var message: String = ""
    private var receiver: String = ""
    private var isseen = false
    private var url: String = ""
    private var messageId: String = ""

    constructor(
        sender: String,
        message: String,
        receiver: String,
        isseen: Boolean,
        url: String,
        messageId: String
    ) {
        this.sender = sender
        this.message = message
        this.receiver = receiver
        this.isseen = isseen
        this.url = url
        this.messageId = messageId
    }
    fun getSender(): String?{
        return sender
    }

    fun setSender(sender: String) {
        this.sender = sender
    }

    fun getMessage(): String? {
        return message
    }

    fun setMessage(message: String) {
        this.message = message
    }

    fun getReceiver(): String? {
        return receiver
    }

    fun setReceiver(receiver: String){
        this.receiver = receiver
    }

    fun getIsSeen(): Boolean? {
        return isseen
    }

    fun setIsSeen(isseen: Boolean) {
        this.isseen = isseen
    }

    fun getUrl(): String? {
        return url
    }

    fun setUrl(url: String) {
        this.url = url
    }

    fun getMessageId(): String? {
        return messageId
    }

    fun setMessageId(messageId: String){
        this.messageId = messageId
    }

}

 */
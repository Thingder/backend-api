//package com.redcutlery.thingder.module.firebase.service;
//
//import com.google.firebase.messaging.Message;
//import com.google.firebase.messaging.Notification;
//import lombok.RequiredArgsConstructor;
//import lombok.extern.log4j.Log4j2;
//import org.springframework.stereotype.Service;
//
//@Log4j2
//@Service
//@RequiredArgsConstructor
//public class FirebaseService {
//    public void sendMessage() {
//        var title = "title";
//        var body = "ㅁㄴ;ㅣㅇ라ㅓㅁ;ㅣ란엄리ㅏㅓ;ㄴ이";
//
//        var notification = Notification.builder()
//                .setTitle(title)
//                .setBody(body)
//                .build();
//
//        var message = Message.builder()
//                .setNotification(notification)
//                .putData("roomUid", "roomuid")
//                .setToken( )
//    }
//}

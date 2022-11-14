package com.redcutlery.thingder.config.firebase;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import lombok.extern.log4j.Log4j2;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;
import java.io.FileInputStream;

@Log4j2
@Configuration
public class FirebaseConfig {
    private String firebaseSdkPath = "src/main/resources/firebase/thingder-3bfd8-firebase-adminsdk-48u9u-7d32467b81.json";

    @PostConstruct
    public void initialize() {
        try {
            var serviceAccount = new FileInputStream(firebaseSdkPath);
            var options = new FirebaseOptions.Builder()
                    .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                    .build();

            FirebaseApp.initializeApp(options);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

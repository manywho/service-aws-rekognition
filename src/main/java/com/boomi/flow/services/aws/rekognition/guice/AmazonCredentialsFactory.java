package com.boomi.flow.services.aws.rekognition.guice;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.BasicAWSCredentials;
import com.boomi.flow.services.aws.rekognition.ApplicationConfiguration;

public class AmazonCredentialsFactory {

    public AWSCredentials create(ApplicationConfiguration configuration) {

        return new BasicAWSCredentials(
                configuration.getAccessKey(),
                configuration.getSecretKey()
        );
    }
}

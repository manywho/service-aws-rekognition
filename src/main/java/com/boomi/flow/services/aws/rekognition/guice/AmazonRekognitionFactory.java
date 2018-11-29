package com.boomi.flow.services.aws.rekognition.guice;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.services.rekognition.AmazonRekognition;
import com.amazonaws.services.rekognition.AmazonRekognitionClientBuilder;
import com.boomi.flow.services.aws.rekognition.ApplicationConfiguration;

public class AmazonRekognitionFactory{

    public AmazonRekognition create(AWSCredentials credentials, ApplicationConfiguration configuration) {

        return AmazonRekognitionClientBuilder.standard()
                .withRegion(configuration.getRekognitionRegion())
                .withCredentials(new AWSStaticCredentialsProvider(credentials))
                .build();
    }
}

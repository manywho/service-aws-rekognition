package com.boomi.flow.services.aws.rekognition.guice;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.services.rekognition.AmazonRekognition;
import com.amazonaws.services.rekognition.AmazonRekognitionClientBuilder;
import com.google.inject.Provider;

import javax.inject.Inject;

public class AmazonRekognitionProvider implements Provider<AmazonRekognition> {
    private final AWSCredentials credentials;

    @Inject
    public AmazonRekognitionProvider(AWSCredentials credentials) {
        this.credentials = credentials;
    }

    @Override
    public AmazonRekognition get() {
        return AmazonRekognitionClientBuilder.standard()
                .withRegion(System.getenv("AWS_REKOGNITION_REGION"))
                .withCredentials(new AWSStaticCredentialsProvider(credentials))
                .build();
    }
}

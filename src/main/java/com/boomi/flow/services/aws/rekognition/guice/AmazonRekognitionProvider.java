package com.boomi.flow.services.aws.rekognition.guice;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.regions.Regions;
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
                .withRegion(Regions.US_EAST_1)
                .withCredentials(new AWSStaticCredentialsProvider(credentials))
                .build();
    }
}

package com.boomi.flow.services.aws.rekognition.guice;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.google.inject.Provider;

import javax.inject.Inject;

public class AmazonS3Provider implements Provider<AmazonS3> {
    private final AWSCredentials credentials;

    @Inject
    public AmazonS3Provider(AWSCredentials credentials) {
        this.credentials = credentials;
    }

    @Override
    public AmazonS3 get() {
        return AmazonS3ClientBuilder.standard()
                .withRegion(System.getenv("AWS_S3_REGION"))
                .withCredentials(new AWSStaticCredentialsProvider(credentials))
                .build();
    }
}

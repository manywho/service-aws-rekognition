package com.boomi.flow.services.aws.rekognition;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.services.rekognition.AmazonRekognition;
import com.amazonaws.services.s3.AmazonS3;
import com.boomi.flow.services.aws.rekognition.guice.AmazonCredentialsProvider;
import com.boomi.flow.services.aws.rekognition.guice.AmazonRekognitionProvider;
import com.boomi.flow.services.aws.rekognition.guice.AmazonS3Provider;
import com.google.inject.AbstractModule;

public class ApplicationModule extends AbstractModule {
    @Override
    protected void configure() {
        bind(AWSCredentials.class).toProvider(AmazonCredentialsProvider.class);
        bind(AmazonRekognition.class).toProvider(AmazonRekognitionProvider.class);
        bind(AmazonS3.class).toProvider(AmazonS3Provider.class);
    }
}
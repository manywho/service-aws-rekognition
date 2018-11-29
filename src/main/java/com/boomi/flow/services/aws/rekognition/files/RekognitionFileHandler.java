package com.boomi.flow.services.aws.rekognition.files;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.boomi.flow.services.aws.rekognition.ApplicationConfiguration;
import com.boomi.flow.services.aws.rekognition.guice.AmazonCredentialsFactory;
import com.boomi.flow.services.aws.rekognition.guice.AmazonS3Factory;
import com.manywho.sdk.api.run.elements.type.FileListFilter;
import com.manywho.sdk.services.files.FileHandler;
import com.manywho.sdk.services.files.FileUpload;
import com.manywho.sdk.services.types.system.$File;

import javax.inject.Inject;
import java.util.Date;
import java.util.List;
import java.util.UUID;

public class RekognitionFileHandler implements FileHandler<ApplicationConfiguration> {
    final static private Integer LINK_EXPIRATION_IN_MS = 300000;

    private final AmazonS3Factory amazonS3Factory;
    private final AmazonCredentialsFactory amazonCredentialsFactory;

    @Inject
    public RekognitionFileHandler(AmazonS3Factory s3Factory, AmazonCredentialsFactory amazonCredentialsFactory) {
        this.amazonS3Factory = s3Factory;
        this.amazonCredentialsFactory = amazonCredentialsFactory;
    }

    @Override
    public List<$File> findAll(ApplicationConfiguration configuration, FileListFilter fileListFilter, String s) {
        throw new RuntimeException("Finding files isn't supported by the AWS Rekognition Service");
    }

    @Override
    public $File upload(ApplicationConfiguration configuration, String path, FileUpload fileUpload) {
        AmazonS3 s3 = amazonS3Factory.create(configuration, amazonCredentialsFactory.create(configuration));
        if (fileUpload.getContent() == null) {
            throw new RuntimeException("No file was uploaded");
        }

        String id = UUID.randomUUID().toString();

        s3.putObject(configuration.getS3bucketName(), id, fileUpload.getContent(), new ObjectMetadata());

        return new $File(id, id, null, generateSignedUrl(configuration, s3, id));
    }

    private static String generateSignedUrl(ApplicationConfiguration configuration, AmazonS3 s3Client, String id) {
        Date expiresAt = new Date(System.currentTimeMillis() + LINK_EXPIRATION_IN_MS);

        return s3Client.generatePresignedUrl(configuration.getS3bucketName(), id, expiresAt)
                .toString();
    }
}

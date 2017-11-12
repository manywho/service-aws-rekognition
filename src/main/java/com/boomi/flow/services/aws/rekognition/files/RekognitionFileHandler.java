package com.boomi.flow.services.aws.rekognition.files;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.boomi.flow.services.aws.rekognition.ApplicationConfiguration;
import com.manywho.sdk.api.run.elements.type.FileListFilter;
import com.manywho.sdk.services.files.FileHandler;
import com.manywho.sdk.services.files.FileUpload;
import com.manywho.sdk.services.types.system.$File;

import javax.inject.Inject;
import java.util.List;
import java.util.UUID;

public class RekognitionFileHandler implements FileHandler<ApplicationConfiguration> {
    private final AmazonS3 s3;

    @Inject
    public RekognitionFileHandler(AmazonS3 s3) {
        this.s3 = s3;
    }

    @Override
    public List<$File> findAll(ApplicationConfiguration configuration, FileListFilter fileListFilter, String s) {
        throw new RuntimeException("Finding files isn't supported by the AWS Rekognition Service");
    }

    @Override
    public $File upload(ApplicationConfiguration configuration, String path, FileUpload fileUpload) {
        if (fileUpload.getContent() == null) {
            throw new RuntimeException("No file was uploaded");
        }

        String id = UUID.randomUUID().toString();

        s3.putObject(System.getenv("AWS_S3_BUCKET"), id, fileUpload.getContent(), new ObjectMetadata());

        return new $File(id, id);
    }
}

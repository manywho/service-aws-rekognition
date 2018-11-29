package com.boomi.flow.services.aws.rekognition;

import com.manywho.sdk.api.ContentType;
import com.manywho.sdk.services.configuration.Configuration;

public class ApplicationConfiguration implements Configuration {
    @Configuration.Setting(name = "S3 Bucket Name", contentType = ContentType.String)
    private String s3bucketName;

    @Configuration.Setting(name = "Access Key", contentType = ContentType.String)
    private String accessKey;

    @Configuration.Setting(name = "Secret Key", contentType = ContentType.Password)
    private String secretKey;

    @Configuration.Setting(name = "S3 Region", contentType = ContentType.String)
    private String s3region;

    @Configuration.Setting(name = "Rekognition Region", contentType = ContentType.String)
    private String rekognitionRegion;

    public String getS3bucketName() {
        return s3bucketName;
    }

    public String getAccessKey() {
        return accessKey;
    }

    public String getSecretKey() {
        return secretKey;
    }

    public String getS3region() {
        return s3region;
    }

    public String getRekognitionRegion() {
        return rekognitionRegion;
    }
}

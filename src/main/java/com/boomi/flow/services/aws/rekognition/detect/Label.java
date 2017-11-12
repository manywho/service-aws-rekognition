package com.boomi.flow.services.aws.rekognition.detect;

import com.manywho.sdk.api.ContentType;
import com.manywho.sdk.services.types.Type;

@Type.Element(name = "Label")
public class Label implements Type {
    @Type.Property(name = "Name", contentType = ContentType.String, bound = false)
    private String name;

    @Type.Property(name = "Confidence", contentType = ContentType.Number, bound = false)
    private float confidence;

    public Label(String name, float confidence) {
        this.name = name;
        this.confidence = confidence;
    }

    public String getName() {
        return name;
    }

    public float getConfidence() {
        return confidence;
    }
}

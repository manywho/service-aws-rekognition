package com.boomi.flow.services.aws.rekognition.detect;

import com.amazonaws.services.rekognition.AmazonRekognition;
import com.amazonaws.services.rekognition.model.DetectLabelsRequest;
import com.amazonaws.services.rekognition.model.DetectLabelsResult;
import com.amazonaws.services.rekognition.model.Image;
import com.amazonaws.services.rekognition.model.S3Object;
import com.boomi.flow.services.aws.rekognition.ApplicationConfiguration;
import com.boomi.flow.services.aws.rekognition.detect.DetectLabelsAction.Inputs;
import com.boomi.flow.services.aws.rekognition.detect.DetectLabelsAction.Outputs;
import com.manywho.sdk.api.InvokeType;
import com.manywho.sdk.api.run.elements.config.ServiceRequest;
import com.manywho.sdk.services.actions.ActionCommand;
import com.manywho.sdk.services.actions.ActionResponse;

import javax.inject.Inject;
import java.util.List;
import java.util.stream.Collectors;

public class DetectLabelsCommand implements ActionCommand<ApplicationConfiguration, DetectLabelsAction, Inputs, Outputs> {
    private final AmazonRekognition rekognition;

    @Inject
    public DetectLabelsCommand(AmazonRekognition rekognition) {
        this.rekognition = rekognition;
    }

    @Override
    public ActionResponse<Outputs> execute(ApplicationConfiguration configuration, ServiceRequest request, Inputs inputs) {
        // Create a detection request using a previously-uploaded file
        DetectLabelsResult result = rekognition.detectLabels(
                new DetectLabelsRequest()
                        .withImage(new Image()
                                .withS3Object(new S3Object()
                                        .withBucket(System.getenv("AWS_S3_BUCKET"))
                                        .withName(inputs.getFile().getName())
                                ))
                        .withMaxLabels(inputs.getLimit())
                        .withMinConfidence(75F)
        );

        List<Label> labels = result.getLabels()
                .stream()
                .map(label -> new Label(label.getName(), label.getConfidence()))
                .collect(Collectors.toList());

        return new ActionResponse<>(new Outputs(labels), InvokeType.Forward);
    }
}

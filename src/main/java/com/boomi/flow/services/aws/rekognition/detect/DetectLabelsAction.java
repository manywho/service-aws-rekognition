package com.boomi.flow.services.aws.rekognition.detect;

import com.manywho.sdk.api.ContentType;
import com.manywho.sdk.services.actions.Action;
import com.manywho.sdk.services.types.system.$File;

import java.util.List;

@Action.Metadata(name = "Detect Labels", summary = "Detect objects, concepts, and scenes in an image", uri = "detect/labels")
public class DetectLabelsAction implements Action {

    public static class Inputs {
        @Action.Input(name = "File", contentType = ContentType.Object)
        private $File file;

        @Action.Input(name = "Result Limit", contentType = ContentType.Number)
        private int limit = 5;

        public $File getFile() {
            return file;
        }

        public int getLimit() {
            return limit;
        }
    }

    public static class Outputs {
        @Action.Output(name = "Labels", contentType = ContentType.List)
        private List<Label> labels;

        public Outputs(List<Label> labels) {
            this.labels = labels;
        }

        public List<Label> getLabels() {
            return labels;
        }
    }
}

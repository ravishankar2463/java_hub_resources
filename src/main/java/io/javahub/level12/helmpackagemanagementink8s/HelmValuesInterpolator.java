package io.javahub.level12.helmpackagemanagementink8s;

import java.util.Map;

/**
 * Emulates Helm values.yaml parameter interpolation for K8s manifests.
 */
public class HelmValuesInterpolator {

    public String renderDeploymentYaml(String imageTag, int replicaCount) {
        return "replicas: " + replicaCount + "\n" +
               "containers:\n" +
               "  - image: javahub/app:" + imageTag;
    }
}

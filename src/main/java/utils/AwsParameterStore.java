package utils;

import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.ssm.SsmClient;
import software.amazon.awssdk.services.ssm.model.GetParameterRequest;
import software.amazon.awssdk.services.ssm.model.GetParameterResponse;
import software.amazon.awssdk.services.ssm.model.SsmException;

public class AwsParameterStore {
    private static final String DEFAULT_REGION = "ap-south-1";
    private static SsmClient ssmClient;
    private static String region;

    static {
        initializeSsmClient();
    }

    private static void initializeSsmClient() {
        // Get AWS credentials from system properties
        String accessKey = System.getProperty("AWS_ACCESS_KEY");
        String secretKey = System.getProperty("AWS_SECRET_KEY");
        region = System.getProperty("AWS_REGION", DEFAULT_REGION);

        if (accessKey == null || secretKey == null) {
            throw new IllegalStateException("AWS_ACCESS_KEY and AWS_SECRET_KEY must be provided as system properties");
        }

        AwsBasicCredentials awsCreds = AwsBasicCredentials.create(accessKey, secretKey);
        ssmClient = SsmClient.builder()
                .region(Region.of(region))
                .credentialsProvider(StaticCredentialsProvider.create(awsCreds))
                .build();
    }

    public static String getParameter(String name) {
        try {
            GetParameterRequest request = GetParameterRequest.builder()
                    .name(name)
                    .withDecryption(true)
                    .build();

            GetParameterResponse response = ssmClient.getParameter(request);
            return response.parameter().value();
        } catch (SsmException e) {
            throw new RuntimeException("Failed to fetch parameter: " + name, e);
        }
    }

    public static void refreshCredentials() {
        // Reinitialize the SSM client with new credentials if they've changed
        String newAccessKey = System.getProperty("AWS_ACCESS_KEY");
        String newSecretKey = System.getProperty("AWS_SECRET_KEY");
        String newRegion = System.getProperty("AWS_REGION", DEFAULT_REGION);

        if (newAccessKey != null && newSecretKey != null &&
                (!newAccessKey.equals(System.getProperty("AWS_ACCESS_KEY", "")) ||
                        !newSecretKey.equals(System.getProperty("AWS_SECRET_KEY", "")) ||
                        !newRegion.equals(region))) {
            initializeSsmClient();
        }
    }
}
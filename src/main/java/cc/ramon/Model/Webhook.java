package cc.ramon.Model;

public record Webhook(String repoName, String jobName, String jobUrl, boolean isSuccess) {
}

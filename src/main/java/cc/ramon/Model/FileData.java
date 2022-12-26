package cc.ramon.Model;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.util.ArrayList;

@JsonSerialize
public class FileData {
    private long webhookId;
    private ArrayList<JobConfig> jobConfigs;

    public FileData(long webhookId, ArrayList<JobConfig> jobConfigs) {
        this.webhookId = webhookId;
        this.jobConfigs = jobConfigs;
    }

    public FileData() {
    }

    public long getWebhookId() {
        return webhookId;
    }

    public void setWebhookId(long webhookId) {
        this.webhookId = webhookId;
    }

    public ArrayList<JobConfig> getJobConfigs() {
        return jobConfigs;
    }

    public void setJobConfigs(ArrayList<JobConfig> jobConfigs) {
        this.jobConfigs = jobConfigs;
    }

    @Override
    public String toString() {
        return "FileData{" +
                "webhookId=" + webhookId +
                ", jobConfigs=" + jobConfigs +
                '}';
    }
}

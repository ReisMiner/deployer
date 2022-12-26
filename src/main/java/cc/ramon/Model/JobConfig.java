package cc.ramon.Model;

public class JobConfig {
    private String repoName;
    private String jobName;
    private String runCommand;

    public JobConfig(String repoName, String jobName, String runCommand) {
        this.repoName = repoName;
        this.jobName = jobName;
        this.runCommand = runCommand;
    }

    public JobConfig() {
    }

    public String getRepoName() {
        return repoName;
    }

    public void setRepoName(String repoName) {
        this.repoName = repoName;
    }

    public String getJobName() {
        return jobName;
    }

    public void setJobName(String jobName) {
        this.jobName = jobName;
    }

    public String getRunCommand() {
        return runCommand;
    }

    public void setRunCommand(String runCommand) {
        this.runCommand = runCommand;
    }

    @Override
    public String toString() {
        return "JobConfig{" +
                "repoName='" + repoName + '\'' +
                ", jobName='" + jobName + '\'' +
                ", runCommand='" + runCommand + '\'' +
                '}';
    }
}
package cc.ramon.Model;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.util.ArrayList;

@JsonSerialize
public record FileData(ArrayList<JobConfig> jobConfigs) {
}

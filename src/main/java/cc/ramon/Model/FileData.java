package cc.ramon.Model;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@JsonSerialize
public record FileData(String runCommand) {
}

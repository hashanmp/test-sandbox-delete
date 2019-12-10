import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class VersionInfo{
    private final String version;
    private final String gitSHA;
    private final String gitBranch;
    private final String builtDate;
}

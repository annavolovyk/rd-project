package apitests;
import lombok.*;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class CreateProject {

    private String name;
    private String description;
    private String identifier;
}

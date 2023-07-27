package apitests;
import lombok.*;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CreateUser {
    private String username;
    private String password;
    private String email;
    private String name;
    private String role;

}

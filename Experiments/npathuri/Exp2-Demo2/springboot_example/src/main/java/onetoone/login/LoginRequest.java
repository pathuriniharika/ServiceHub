//This is a simple class that represents the request object containing an email and password.
package onetoone.login;


import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Getter
@AllArgsConstructor
@EqualsAndHashCode
@ToString

public class LoginRequest {

    private String email;

    private String password;

}

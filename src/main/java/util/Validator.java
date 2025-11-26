package util;

import repository.UserRepository;

public class Validator {
    private final UserRepository userRepository ;

    public Validator() {
        this.userRepository = new UserRepository();
    }

    public boolean validateLoginPassword(String username, String password) {
        boolean isValid = username != null
                && username.length() > 3
                && password != null
                && password.length() > 3
                && userRepository.isPasswordContains(password);

        if (isValid) {
            return username.equals(userRepository.getUsernameByPassword(password));
        }
        return false;
    }
}

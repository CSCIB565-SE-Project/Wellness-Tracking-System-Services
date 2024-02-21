package com.loginseervice.login.user;

import java.util.Calendar;
import java.util.List;
import java.util.Optional;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.loginseervice.login.exception.UserAlreadyExistsException;
import com.loginseervice.login.registration.RegistrationRequest;
import com.loginseervice.login.registration.token.VerificationToken;
import com.loginseervice.login.registration.token.VerificationTokenRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService implements IUserService{
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final VerificationTokenRepository tokenRepository;
    @Override
    public List<User> getUsers() {
        return userRepository.findAll();
    }

    @Override
    public User registUser(RegistrationRequest request) {
        Optional<User> user = this.findByEmail(request.email());
        if(user.isPresent()){
            throw new UserAlreadyExistsException("User with Email " + request.email() + " already exists!!");
        }
        var newUser = new User();
        newUser.setFname(request.fname());
        newUser.setMname(request.mname());
        newUser.setLname(request.lname());
        newUser.setDob(request.dob());
        newUser.setGender(request.gender());
        newUser.setEmail(request.email());
        newUser.setUsername(request.username());
        newUser.setPassword(passwordEncoder.encode(request.password()));
        newUser.setRole(request.role());
        return userRepository.save(newUser);
    }

    @Override
    public Optional<User> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public void saveUserVerificationToken(User user, String token) {
        var verificationToken = new VerificationToken(token, user);
        tokenRepository.save(verificationToken);
    }

    @Override
    public String validateToken(String token) {
        VerificationToken theToken = tokenRepository.findByToken(token);
        if(theToken == null){
            return "Invalid Verification Token";
        }
        User user = theToken.getUser();
        Calendar calendar = Calendar.getInstance();
        if(theToken.getExpirationTime().getTime() - calendar.getTime().getTime() <= 0) {
            tokenRepository.delete(theToken);
            return "Token Already Expired!";
        }
        user.setEnabled(true);
        userRepository.save(user);
        return "valid";
    }

    
    
}

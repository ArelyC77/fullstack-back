package com.example.arely.fullstackbackend.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@Service
public class UserManagementService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JWTUtils jwtUtils;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public RequestResponse register(RequestResponse registrationRequest) {
        RequestResponse response = new RequestResponse();
        try {
            User user = new User();
            user.setEmail(registrationRequest.getEmail());
            user.setFirstName(registrationRequest.getFirstName());
            user.setLastName(registrationRequest.getLastName());
            user.setRole(registrationRequest.getRole());
            user.setPassword(passwordEncoder.encode(registrationRequest.getEmail()));
            user.setEmployeeID(registrationRequest.getUsers().getEmployeeID());
            User userResult = userRepository.save(user);
            if(userResult.getEmployeeID()>0){
                response.setUsers(userResult);
                response.setMessage("User Saved Successfully");
                response.setStatusCode(200);
            }


        }catch (Exception e){
            response.setStatusCode(500);
            response.setError(e.getMessage());
        }
        return response;
    }

    public RequestResponse login(RequestResponse loginRequest){
        RequestResponse response = new RequestResponse();
        try{
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken
                    (loginRequest.getEmail(), loginRequest.getPassword()));
            var user = userRepository.findByEmail(loginRequest.getEmail()).orElseThrow();
            var jwt = jwtUtils.generateToken(user);
            var refreshToken = jwtUtils.generateRefreshToken(new HashMap<>(), user);
            response.setStatusCode(200);
            response.setToken(jwt);
            response.setRefreshToken(refreshToken);
            response.setExpirationTime("24Hrs");
            response.setMessage("Successfully Logged In");

        }catch(Exception e){
            response.setStatusCode(500);
            response.setMessage(e.getMessage());
        }
        return response;
    }

    public RequestResponse refreshToken(RequestResponse refreshTokenRequest){
        RequestResponse response = new RequestResponse();
        try{
            String userEmail = jwtUtils.extractUsername(refreshTokenRequest.getToken());
            User user = userRepository.findByEmail(userEmail).orElseThrow();
            if(jwtUtils.isTokenValid(refreshTokenRequest.getToken(), user)){
                var jwt = jwtUtils.generateToken(user);
                response.setStatusCode(200);
                response.setToken(jwt);
                response.setRefreshToken(refreshTokenRequest.getToken());
                response.setExpirationTime("24Hr");
                response.setMessage("Successfully Refreshes Token");
            }
            response.setStatusCode(200);
            return response;
        }catch(Exception e){
            response.setStatusCode(500);
            response.setMessage(e.getMessage());
            return response;
        }
    }

    public RequestResponse getAllUsers(){
        RequestResponse requestResponse = new RequestResponse();
        try{
            List<User> result = userRepository.findAll();
            if(!result.isEmpty()){
                requestResponse.setUsersList(result);
                requestResponse.setStatusCode(200);
                requestResponse.setMessage("Successful");
            } else{
                requestResponse.setStatusCode(404);
                requestResponse.setMessage("No users found");
            }
            return requestResponse;

        }catch (Exception e){
            requestResponse.setStatusCode(500);
            requestResponse.setMessage("Error occurred: " + e.getMessage());
            return requestResponse;
        }
    }

    public RequestResponse getUsersById(Integer id){
        RequestResponse requestResponse = new RequestResponse();
        try{
            User userByID = userRepository.findById(id).orElseThrow(() -> new RuntimeException("User Not Found"));
            requestResponse.setUsers(userByID);
            requestResponse.setStatusCode(200);
            requestResponse.setMessage("Users with id: '" + id + "' found successfully");
        }catch(Exception e){
            requestResponse.setStatusCode(500);
            requestResponse.setMessage("Error occurred: " + e.getMessage());
        }
        return requestResponse;
    }

    public RequestResponse deleteUser(Integer employeeID){
        RequestResponse requestResponse = new RequestResponse();
        try{
            Optional<User> userOptional = userRepository.findById(employeeID);
            if(userOptional.isPresent()){
                userRepository.deleteById(employeeID);
                requestResponse.setStatusCode(200);
                requestResponse.setMessage("User not found for deletion");
            } else{
                requestResponse.setStatusCode(404);
                requestResponse.setMessage("User not found for deletion");
            }
        }catch(Exception e){
            requestResponse.setStatusCode(500);
            requestResponse.setMessage("Error occurred while deleting user: " + e.getMessage());
        }
        return requestResponse;
    }

    public RequestResponse updateUser (Integer employeeID, User updatedUser){
        RequestResponse requestResponse = new RequestResponse();
        try{
            Optional<User> userOptional = userRepository.findById(employeeID);
            if(userOptional.isPresent()){
                User existingUser = userOptional.get();
                existingUser.setEmail(updatedUser.getEmail());
                existingUser.setFirstName(updatedUser.getFirstName());
                existingUser.setLastName(updatedUser.getLastName());
                existingUser.setRole(updatedUser.getRole());
                existingUser.setEmployeeID(updatedUser.getEmployeeID());//maybe take off?

                //check is password is present in the request
                if(updatedUser.getPassword() != null && !updatedUser.getPassword().isEmpty()){
                    //Encode the password and update it
                    existingUser.setPassword(passwordEncoder.encode(updatedUser.getPassword()));
                }

                User savedUser = userRepository.save(existingUser);
                requestResponse.setUsers(savedUser);
                requestResponse.setStatusCode(200);
                requestResponse.setMessage("User updated successfully");

            }else{
                requestResponse.setStatusCode(404);
                requestResponse.setMessage("User not found for update");
            }
        }catch(Exception e){
            requestResponse.setStatusCode(500);
            requestResponse.setMessage("Error occurred while updating user: " + e.getMessage());
        }
        return requestResponse;
    }

    public RequestResponse getMyInfo(String email){
        RequestResponse requestResponse = new RequestResponse();
        try{
            Optional<User> userOptional = userRepository.findByEmail(email);
            if(userOptional.isPresent()){
                requestResponse.setUsers(userOptional.get());
                requestResponse.setStatusCode(200);
                requestResponse.setMessage("Successful");
            }else{
                requestResponse.setStatusCode(404);
                requestResponse.setMessage("User not found for update");
            }

        }catch(Exception e){
            requestResponse.setStatusCode(500);
            requestResponse.setMessage("Error occureed while getting user info: " + e.getMessage());

        }
        return requestResponse;
    }

}
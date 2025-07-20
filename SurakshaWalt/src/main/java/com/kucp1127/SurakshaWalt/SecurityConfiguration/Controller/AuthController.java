package com.kucp1127.SurakshaWalt.SecurityConfiguration.Controller;

import com.kucp1127.SurakshaWalt.SecurityConfiguration.Configuration.JwtService;
import com.kucp1127.SurakshaWalt.SecurityConfiguration.DTO.LoginDTO;
import com.kucp1127.SurakshaWalt.SecurityConfiguration.Model.AdminRegistration;
import com.kucp1127.SurakshaWalt.SecurityConfiguration.Model.PartnerRegistration;
import com.kucp1127.SurakshaWalt.SecurityConfiguration.Model.UserRegistration;
import com.kucp1127.SurakshaWalt.SecurityConfiguration.Service.AdminService;
import com.kucp1127.SurakshaWalt.SecurityConfiguration.Service.PartnerService;
import com.kucp1127.SurakshaWalt.SecurityConfiguration.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;

import java.util.Optional;


@RestController
@CrossOrigin("*")
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private UserService userService;

    @Autowired
    private AdminService adminService;

    @Autowired
    private PartnerService partnerService;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private AuthenticationManager authenticationManager;

    private BCryptPasswordEncoder bCryptPasswordEncoder= new BCryptPasswordEncoder(12);

    @PostMapping("/userRegistration")
    public ResponseEntity<?> userRegistration(@RequestBody UserRegistration userRegistration){
        userRegistration.setPassword(bCryptPasswordEncoder.encode(userRegistration.getPassword()));
        UserRegistration userRegistration1 = userService.registerUser(userRegistration);
        if(userRegistration1!=null){
            return ResponseEntity.ok(userRegistration1);
        }
        else return ResponseEntity.internalServerError().body("Error Registering user");
    }

    @PostMapping("/AdminRegistration")
    public ResponseEntity<?> adminRegistration(@RequestBody AdminRegistration adminRegistration){
        adminRegistration.setPassKey(bCryptPasswordEncoder.encode(adminRegistration.getPassKey()));
        AdminRegistration adminRegistration1 = adminService.registerAdmin(adminRegistration);
        if(adminRegistration1!=null){
            return ResponseEntity.ok(adminRegistration1);
        }
        else return ResponseEntity.internalServerError().body("Error Registering user");
    }

    @PostMapping("/PartnerRegistration")
    public ResponseEntity<?> partnerRegistration(@RequestBody PartnerRegistration partnerRegistration){
        partnerRegistration.setPassword(bCryptPasswordEncoder.encode(partnerRegistration.getPassword()));
        PartnerRegistration partnerRegistration1 = partnerService.registerPartner(partnerRegistration);
        partnerRegistration1.setIsRegistration(false);
        if(partnerRegistration1!=null){
            return ResponseEntity.ok(partnerRegistration1);
        }
        else return ResponseEntity.internalServerError().body("Error Registering user");
    }


    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginDTO loginDTO){
        Authentication authentication = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(loginDTO.getUsername(),loginDTO.getPassword()));
        if(authentication.isAuthenticated()) {
            System.out.println(jwtService.generateToken(loginDTO.getUsername()));
            return ResponseEntity.ok().body(jwtService.generateToken(loginDTO.getUsername()));
        }
        return ResponseEntity.badRequest().body("Wrong credentials");
    }

    @PutMapping("/userRegistration")
    public ResponseEntity<?> UpdateUserRegistration(@RequestBody UserRegistration userRegistration){
        userRegistration.setPassword(bCryptPasswordEncoder.encode(userRegistration.getPassword()));
        UserRegistration userRegistration1 = userService.registerUser(userRegistration);
        if(userRegistration1!=null){
            return ResponseEntity.ok(userRegistration1);
        }
        else return ResponseEntity.internalServerError().body("Error Registering user");
    }

    @GetMapping("/CompanyUsernames")
    public ResponseEntity<?> getUserNames(){
        return ResponseEntity.ok(partnerService.getAllUsernames());
    }

    @PutMapping("/PartnerRegistration/{username}")
    public ResponseEntity<?> updateApplication(@PathVariable String username){
        return ResponseEntity.ok(partnerService.updateRegistration(username));
    }

    @GetMapping("/partnerRegistration/{username}")
    public ResponseEntity<?> findByUsername(@PathVariable String username){

        Optional<PartnerRegistration> partnerRegistration = partnerService.findByUsername(username);
        if(partnerRegistration.isPresent()) return  ResponseEntity.ok(partnerRegistration.get());

        return ResponseEntity.notFound().build();
    }

    @GetMapping("/getAllCompanies")
    public ResponseEntity<?> getAllCompanies(){
        return ResponseEntity.ok(partnerService.findAllCompanies());
    }

}

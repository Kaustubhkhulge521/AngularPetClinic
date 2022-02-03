package com.pet.clinic.configuration;

import com.pet.clinic.entity.Doctor;
import com.pet.clinic.entity.OwnerEntity;
import com.pet.clinic.repository.DoctorRepo;
import com.pet.clinic.repository.OwnerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
@Service
public class UserAuthService implements UserDetailsService{

    @Autowired
    DoctorRepo userRepository;

    OwnerRepository ownerRepository;



    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Doctor inDB = userRepository.findByUsername(username);
        //OwnerEntity inDB1 = userRepository.findByUsername(username);
        if (inDB == null) {
            throw new UsernameNotFoundException("User not found");
        }
        return new AppUser(inDB);
    }

}

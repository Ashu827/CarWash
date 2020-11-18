package com.carwash.serviceprovider.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.carwash.serviceprovider.model.ServiceProvider;
import com.carwash.serviceprovider.repository.ServiceProviderRepo;

@Service
public class ServiceProviderService {

	@Autowired
	ServiceProviderRepo providerRepo;
	
	@Autowired
	PasswordEncoder bCrypt;

	public ServiceProvider addNewCustomer(ServiceProvider provider) {

		if (providerRepo.existsById(provider.getEmailId())) {
			return null;
		}
		else {
			String encodedPassword = bCrypt.encode(provider.getPassword());
			provider.setPassword(encodedPassword);
			providerRepo.save(provider);
			return provider;
			}
	}

	public Optional<ServiceProvider> login(ServiceProvider provider) {
		if(provider.getEmailId()!=null) {
			Optional<ServiceProvider> cred = providerRepo.findById(provider.getEmailId());
			boolean data= providerRepo.existsById(provider.getEmailId());
				if(data) {
					if(bCrypt.matches(provider.getPassword(), cred.get().getPassword())){
						return cred;
					}
					else {
						return null;
					}
				}
				else {
					return null;
				}
			}
			else {
				return null;
			}
	}
}
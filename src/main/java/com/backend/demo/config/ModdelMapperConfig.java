package com.backend.demo.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ModdelMapperConfig {
	
	@Bean
public ModelMapper modelMapper() {
		return new ModelMapper();
	}
			

}

package com.banqueMisr.champion.service;

import org.springframework.stereotype.Service;

@Service
public interface EmailService {

	void sendMessage(String to);

}

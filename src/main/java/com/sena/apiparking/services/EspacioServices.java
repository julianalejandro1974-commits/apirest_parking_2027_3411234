package com.sena.apiparking.services;

import org.springframework.stereotype.Service;

import com.sena.apiparking.repository.EspacioParqueoRepository;
import com.sena.apiparking.repository.RegistroParqueoRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class EspacioServices {

	private static final int MAX_LOTE = 500;
	
	private final EspacioParqueoRepository espacioRepo;
	private final RegistroParqueoRepository registroRepo;
	
	public EspacioResponse crear() {
		
	}
	
	
}

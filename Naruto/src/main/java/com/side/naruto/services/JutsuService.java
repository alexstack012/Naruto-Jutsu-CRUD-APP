package com.side.naruto.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.side.naruto.models.Jutsu;
import com.side.naruto.repositories.JutsuRepository;

@Service
public class JutsuService {

	@Autowired
	private JutsuRepository jutsuRepo;
	
	//create
	public Jutsu createJutsu(Jutsu jutsu) {
		return jutsuRepo.save(jutsu);
	}
	//get one jutsu
	public Jutsu getOneJutsu(Long id) {
		Optional<Jutsu> optionalJutsu = this.jutsuRepo.findById(id);
		if(optionalJutsu.isPresent()) {
		return optionalJutsu.get();
		} else {
			return null;
		}
	}
	//get all jutsus
	public List<Jutsu> getAllJutsus(){
		return jutsuRepo.findAll();
	}
	//update a car
	public Jutsu updateJutsu(Jutsu jutsu) {
		return jutsuRepo.save(jutsu);
	}
	//delete a car
	public void deleteJutsu(Long id) {
		this.jutsuRepo.deleteById(id);
	}
}

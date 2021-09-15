package com.dac.marina.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dac.marina.model.Volume;

@Repository
public interface VolumeRepository extends JpaRepository<Volume, Long> {

	Volume findByIdVolume(long idVolume);
	
}

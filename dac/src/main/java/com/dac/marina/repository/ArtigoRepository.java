package com.dac.marina.repository;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dac.marina.model.Artigo;
import com.dac.marina.model.Volume;

@Repository
public interface ArtigoRepository extends JpaRepository<Artigo, Long> {

	Artigo findByIdArtigo(long idArtigo);

	List<Artigo> findByVolume(Volume volume, Sort ascending);

}

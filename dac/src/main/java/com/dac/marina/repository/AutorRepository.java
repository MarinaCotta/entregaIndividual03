package com.dac.marina.repository;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dac.marina.model.Artigo;
import com.dac.marina.model.Autor;

@Repository
public interface AutorRepository extends JpaRepository<Autor, Long> {

	List<Autor> findByArtigo(Artigo artigo, Sort sort);

	Autor findByIdAutor(long idAutor);

}

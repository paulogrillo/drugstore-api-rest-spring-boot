package br.com.drugstore.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.drugstore.models.Categoria;

public interface CategoriaRepository extends JpaRepository<Categoria, Long>{
	public List<Categoria> findAllByDescricaoContainingIgnoreCase(String descricao);
}

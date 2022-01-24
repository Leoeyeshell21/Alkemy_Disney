package com.Alkemy.Disney.Repository;

import com.Alkemy.Disney.Entity.Genero;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GeneroRepository extends JpaRepository<Genero,Long> {


}

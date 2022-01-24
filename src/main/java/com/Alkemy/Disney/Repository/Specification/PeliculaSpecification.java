package com.Alkemy.Disney.Repository.Specification;

import antlr.StringUtils;
import com.Alkemy.Disney.DTO.PeliculaFiltersDTO;
import com.Alkemy.Disney.Entity.Genero;
import com.Alkemy.Disney.Entity.Pelicula;
import com.Alkemy.Disney.Entity.Personaje;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;


import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

@Component
public class PeliculaSpecification {

    public Specification<Pelicula> getByFiltersPelicula(PeliculaFiltersDTO peliculaFilters){

        return (root, query, criteriaBuilder) -> {
            List<javax.persistence.criteria.Predicate> predicates = new ArrayList<>();

            //Conflicto con hasLength
            //if(StringUtils.hasLength(peliculaFilters.getTitulo())){
            //    predicates.add(criteriaBuilder.like(criteriaBuilder.lower(root.get("titulo")),"%" + peliculaFilters.getTitulo().toLowerCase() + "%"));

            //}

            if(!CollectionUtils.isEmpty(peliculaFilters.getGeneros())){
                Join<Genero,Pelicula> join = root.join("generos", JoinType.INNER);
                Expression<String> generoId = join.get("id");
                predicates.add(generoId.in(peliculaFilters.getGeneros()));
            }

            //remueve los duplicados
            query.distinct(true);
            //resuelve el order
            String orderByField = "nombre";
            query.orderBy(peliculaFilters.isASC() ?
                    criteriaBuilder.asc(root.get(orderByField)):
                    criteriaBuilder.desc(root.get(orderByField))
            );
            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));

        };
    }


}

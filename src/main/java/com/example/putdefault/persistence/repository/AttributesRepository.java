package com.example.putdefault.persistence.repository;

import com.example.putdefault.persistence.model.Attribute;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AttributesRepository extends PagingAndSortingRepository<Attribute, String> {

}

package com.assignment.credorax.repository;

import com.assignment.credorax.model.Cardholder;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CardholderRepository extends CrudRepository<Cardholder, Long> {
}

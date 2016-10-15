/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eu.interact.repository;

import eu.interact.domain.Movie;
import org.springframework.data.cassandra.repository.TypedIdCassandraRepository;

/**
 *
 * @author Root
 */
public interface MovieRepository extends TypedIdCassandraRepository<Movie, String> {
}

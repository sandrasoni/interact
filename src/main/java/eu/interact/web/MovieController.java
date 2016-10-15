/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eu.interact.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import eu.interact.domain.Movie;
import eu.interact.repository.MovieRepository;

/**
 *
 * @author Root
 */
@RestController
public class MovieController {

    @Autowired
    MovieRepository movieRepository;

    @RequestMapping(value = "/movies")
    public Iterable<Movie> getTable1() {
        return movieRepository.findAll();
    }

}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eu.interact.domain;

import java.util.List;

/**
 *
 * @author Root
 */
public class DelegatedAct {
    Long id;
    String code;
    String title;
    List<String> keywords;
    boolean visibility;
    String type;
    List<DelegatedActEvent> events;
}

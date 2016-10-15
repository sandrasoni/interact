/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eu.interact.domain;

import java.util.Date;
import java.util.List;

/**
 *
 * @author Root
 */
class DelegatedActEvent {
    Long id;
    Long delegatedActId;
    String originating_institution;
    List<String> destination_institution;
    String name;
    Date date;
    boolean visibility;
}

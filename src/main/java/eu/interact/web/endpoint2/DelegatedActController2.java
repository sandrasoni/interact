/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eu.interact.web.endpoint2;

import eu.interact.domain.DelegatedAct2;
import eu.interact.domain.PrivateDelegatedAct;
import eu.interact.domain.PublicDelegatedAct;
import eu.interact.repository.PrivateDeletegatedActRepository;
import eu.interact.web.DelegatedActService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 *
 * @author Root
 */
@RestController
@RequestMapping(value="/v2/acts")
public class DelegatedActController2 {

    @Autowired
    DelegatedActService actService;
    //@Autowired
    //DelegatedActRepository actsRepository;
    List<DelegatedAct2> userActs = new ArrayList<DelegatedAct2>();
    //private static final Logger logger = LogManager.getLogger(DelegatedActController.class);
    //static final Logger LOG = LoggerFactory.getLogger(MyClassName.class)

    public void info(String message) {
        System.out.println(message);
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public PrivateDelegatedAct save(@RequestBody PrivateDelegatedAct act) {
        PrivateDelegatedAct savedAct =  actService.save(act);

        return savedAct;
    }

    @RequestMapping(value = "/private/list")
    public List<PrivateDelegatedAct> listPrivate() {

        return actService.getAllPrivate();
    }

    @RequestMapping(value = "/public/list")
    public List<PublicDelegatedAct> listPublic() {
        return actService.getAllPublic();
    }

    @RequestMapping(value="/private/search")
    public List<DelegatedAct2> search(@RequestParam(defaultValue = "") String text) {
        List<DelegatedAct2> result = new ArrayList<DelegatedAct2>();
        for (DelegatedAct2 userAct: userActs) {
            if(userAct.getKeywords().contains(text.toLowerCase())) {
                result.add(userAct);
            }
            if(userAct.getTitle().contains(text.toLowerCase())) {
                result.add(userAct);
            }
        }
        return userActs;
    }

}

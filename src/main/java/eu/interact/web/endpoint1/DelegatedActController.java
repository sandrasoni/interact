/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eu.interact.web.endpoint1;

import eu.interact.domain.DelegatedAct2;
import org.slf4j.LoggerFactory;
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
@RequestMapping(value="/v1/acts")
public class DelegatedActController {

    //@Autowired
    //DelegatedActRepository actsRepository;
    List<DelegatedAct2> userActs = new ArrayList<DelegatedAct2>();
    //private static final Logger logger = LogManager.getLogger(DelegatedActController.class);
    //static final Logger LOG = LoggerFactory.getLogger(MyClassName.class)

    public void info(String message) {
        System.out.println(message);
    }

    @PostConstruct
    public void init(){
        info("init mock data ------- ");
        DelegatedAct2 default1 = new DelegatedAct2();
        default1.setCode("1");
        default1.setTitle("act 1");
        default1.setId(UUID.randomUUID().getMostSignificantBits());
        default1.setVisibility(true);// public - true, private false
        userActs.add(default1);

        DelegatedAct2 default2 = new DelegatedAct2();
        default2.setCode("1");
        default2.setTitle("act 2");
        default2.setId(UUID.randomUUID().getMostSignificantBits());
        default2.setVisibility(true);// public - true, private false
        userActs.add(default1);
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public DelegatedAct2 save(@RequestBody DelegatedAct2 act) {
        userActs.add(act);

        return act;
    }

    @RequestMapping(value = "/private/list")
    public List<DelegatedAct2> listPrivate() {

        return userActs;
    }

    @RequestMapping(value = "/public/list")
    public List<DelegatedAct2> listPublic() {
        List<DelegatedAct2> publicActs = new ArrayList<DelegatedAct2>();
        for (DelegatedAct2 userAct: userActs) {
            if(userAct.isVisibility()) {
                publicActs.add(userAct);
            }
        }
        return userActs;
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

package ro.fiipractic.mycinema.services.impl;

import org.springframework.stereotype.Service;
import ro.fiipractic.mycinema.services.PersonService;

@Service
public class PersonServiceImpl implements PersonService {

    @Override
    public String helloFromService() {
        return "Hello from service!";
    }


}

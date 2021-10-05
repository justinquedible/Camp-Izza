package com.campizza.backend.campizzabackend.service;
import com.campizza.backend.campizzabackend.model.HouseHold;
import java.util.*;

public interface ParentService {
    List<HouseHold> getParentsAll();

    void createParent(HouseHold parent);
}

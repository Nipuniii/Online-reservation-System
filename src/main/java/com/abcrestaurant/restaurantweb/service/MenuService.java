package com.abcrestaurant.restaurantweb.service;

import com.abcrestaurant.restaurantweb.model.Menu;
import com.abcrestaurant.restaurantweb.repository.MenuRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MenuService {

    @Autowired
    private MenuRepository menuRepository;

    public Menu saveMenu(Menu menu) {
        return menuRepository.save(menu);
    }
}

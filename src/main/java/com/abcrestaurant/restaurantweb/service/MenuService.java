package com.abcrestaurant.restaurantweb.service;

import com.abcrestaurant.restaurantweb.model.Branch;
import com.abcrestaurant.restaurantweb.model.Menu;
import com.abcrestaurant.restaurantweb.repository.MenuRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MenuService {

    @Autowired
    private MenuRepository menuRepository;

    public Menu saveMenu(Menu menu) {
        return menuRepository.save(menu);
    }
    public List<Menu> getAllMenuItems() {
        return menuRepository.findAll();
    }

    public List<Menu> findByBranch(Branch branch) {
        return menuRepository.findByBranch(branch);
    }
    public Menu findById(Long id) {
        return menuRepository.findById(id).orElse(null);
    }

    public void deleteMenu(Long id) {
        menuRepository.deleteById(id);
    }

    public long countAllMenuItems() {
        return menuRepository.count();
    }
}

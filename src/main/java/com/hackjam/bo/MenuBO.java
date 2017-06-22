package com.hackjam.bo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hackjam.constant.MenuType;
import com.hackjam.dao.MenuDAO;
import com.hackjam.model.Menu;

/**
 * @author jun-ho.lee on 2017-06-08.
 */
@Service
public class MenuBO {
	@Autowired
	MenuDAO menuDAO;

	public boolean addMenu(Menu menu) {
		return menuDAO.insert(menu);
	}

	public boolean deleteMenu(int menuId) {
		return menuDAO.delete(menuId);
	}

	public boolean updateMenu(Menu menu) {
		return menuDAO.update(menu);
	}

	public Map<String, List<Menu>> getMenuMap() {
		List<Menu> allMenus = menuDAO.selectAll();

		Map<String, List<Menu>> menuMap = new HashMap<>();
		for (Menu menu : allMenus) {
			MenuType menuType = menu.getMenuType();
			String menuName = menuType.getName();

			List<Menu> menus = menuMap.get(menuName);
			if (menus == null) {
				menus = new ArrayList<>();
				menuMap.put(menuName, menus);
			}

			menus.add(menu);
		}

		return menuMap;
	}

	public Menu getMenu(int menuId) {
		return menuDAO.select(menuId);
	}
}

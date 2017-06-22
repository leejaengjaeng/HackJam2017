package com.hackjam.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.hackjam.model.Menu;

/**
 * @author jun-ho.lee on 2017-06-08.
 */
@Mapper
public interface MenuDAO {
	List<Menu> selectAll();

	Menu select(int menuId);

	boolean insert(Menu menu);

	boolean delete(int menuId);

	boolean update(Menu menu);
}

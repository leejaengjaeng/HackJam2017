package com.hackjam.dao;

import org.apache.ibatis.annotations.Mapper;

/**
 * @author jun-ho.lee on 2017-06-23.
 */
@Mapper
public interface CustomerCountDAO {
	int select();
}

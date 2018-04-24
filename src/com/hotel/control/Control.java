package com.hotel.control;

import java.util.ArrayList;

public interface Control<Entity> {

	public ArrayList<Entity> list() throws Throwable;

	public void insert(Entity entity) throws Throwable;

	public void search(Entity entity) throws Throwable;

	public void update(Entity entity) throws Throwable;

}

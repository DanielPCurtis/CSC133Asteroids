package com.mycompany.a2;

import java.util.Iterator;

public interface ICollection {
	public void add(GameObject gameObj);
	public void remove(GameObject gameObj);
	public IIterator getIterator();
}

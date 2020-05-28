package com.mycompany.a2;

import java.util.Vector;
/**
 * 
 * @author Daniel and Curtis
 * Collection to store Game Objects
 *
 */
public class GameObjectCollection implements ICollection {
	/**
	 * Vector to store game Objects
	 */
	private Vector<GameObject> theCollection;
	/**
	 * Constructor to create a new Vector with a collection of Objects
	 */
	public GameObjectCollection()	{
		theCollection = new Vector<GameObject>();
	}
	/**
	 * add GameObject to the Vector
	 */
	@Override
	public void add(GameObject obj)	{
		theCollection.addElement(obj);
	}
	/**
	 * call an Iterator for the collection of Objects
	 */
	@Override
	public IIterator getIterator() {
		return new GameObjectVectorIterator();
	}
	/**
	 * Remove GameObject from Vector
	 */
	@Override
	public void remove(GameObject obj) {
		theCollection.removeElement(obj);
	}
	/**
	 * 
	 * @author Daniel and Curtis
	 * Iterator to cycle through Game Objects
	 * Given in class notes
	 */
	private class GameObjectVectorIterator implements IIterator {
		/**
		 * Int to store the current Index of the Vector
		 */
		private int index;
		/**
		 * Constructor initializes the index to -1
		 */
		public GameObjectVectorIterator() { 
			index = -1; 
		}
		/**
		 * return true if the Collection has a next object
		 */
		@Override
		public boolean hasNext() {
			if (theCollection.size ( ) <= 0)  return false;
			if (index == theCollection.size() - 1 ) return false; 
			
			return true;
		}
		/**
		 * return the Object at the current index
		 */
		public GameObject getNext() {
			++index; 
			return (GameObject) (theCollection.elementAt(index));
		}

	}
	
	
}

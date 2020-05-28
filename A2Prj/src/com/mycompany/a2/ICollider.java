package com.mycompany.a2;

/**
 * @author Daniel Curtis and Curtis Botonis
 *
 */
public interface ICollider {
	
	/**
	 * Dtermines if tow objects collide
	 * @param otherObj - Object colliding with primary object
	 * @return True if collision
	 */
	public boolean collidesWith(ICollider otherObj);

	/**
	 * @param otherObj
	 */
	public void handleCollision(ICollider otherObj);

	public void setRemove();

	public boolean getRemove();
	
	
}

package be.uantwerpen.fti.ei.gamelogic.Movement;

/*
 * The MovementComponent contains all the necessary data to move an entity around.
 * It tracks the current position and velocity, and has methods for updating those.
 */
public class MovementComponent
{
	// Velocity in X and Y direction
	private double vx;
	private double vy;
	// Position in X and Y direction
	private double x;
	private double y;
	// Grootte van de entity
	private double entitySizeX;
	private double entitySizeY;

	/**
	 * default constructor of movementcomponent
	 */
	public MovementComponent()
	{
		this.vx = 0;
		this.vy = 0;
		this.x = 0;
		this.y = 0;
		this.entitySizeX = 0;
		this.entitySizeY = 0;
	}

	/**
	 * constructor of movementcomponent
	 * assigns the speed, coordinates and size of the movement component
	 * @param vx: speed x
	 * @param vy: speed y
	 * @param x: position x
	 * @param y: position y
	 * @param sizeX: x-size
	 * @param sizeY: y-size
	 */
	public MovementComponent(double vx, double vy, double x, double y, double sizeX, double sizeY)
	{
		this.vx = vx;
		this.vy = vy;
		this.x = x;
		this.y = y;
		this.entitySizeX = sizeX;
		this.entitySizeY = sizeY;
	}

	/**
	 * Getter double[] velocity
	 * @return the velocity of the movementcomponent
	 */
	public double[] getVelocity()
	{
		return new double[] {this.vx, this.vy};
	}

	/**
	 * Setter double[] velocity
	 * sets the new velocity into the movementcomponent
	 * @param newVelocity: the new velocity for the movementcomponent
	 */
	public void setVelocity(double[] newVelocity)
	{
		assert 2 == newVelocity.length;

		this.vx = newVelocity[0];
		this.vy = newVelocity[1];
	}

	/**
	 * Getter double[] position
	 * @return the position of the movementcomponent
	 */
	public double[] getPosition()
	{
		return new double[] {this.x, this.y};
	}

	/**
	 * Setter double[] position
	 * sets the new position into the movementcomponent
	 * @param newPosition: the new position for the movementcomponent
	 */
	public void setPosition(double[] newPosition)
	{
		assert 2 == newPosition.length;
		this.x = newPosition[0];
		this.y = newPosition[1];
	}

	/**
	 * Getter double[] size
	 * @return the size of the movementcomponent
	 */
	public double[] getEntitySize() { return new double[] {this.entitySizeX, this.entitySizeY} ; }
	
}

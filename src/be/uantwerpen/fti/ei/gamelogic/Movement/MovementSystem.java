package be.uantwerpen.fti.ei.gamelogic.Movement;

import java.util.List;

/*
 * The MovementSystem is the system we use to update all our MovementComponents.
 * It contains all the necessary logic, but none of the data.
 * The necessary data is stored in the components themselves.
 */
public class MovementSystem {
	/**
	 * updates the movementcomponents of all the entities
	 * @param components: list of movementcomponents
	 * @param delta: the time i have between the first and last line of code
	 * @return the new updated list of movement components
	 */
	public List<MovementComponent> update(List<MovementComponent> components, double delta) {

		for (MovementComponent component : components) {
			double[] newPosition = component.getPosition();

			// Add the velocity to the current position to calculate the new position
			newPosition[0] += component.getVelocity()[0] * delta;
			newPosition[1] += component.getVelocity()[1] * delta;
			// Store the new position in the component
			component.setPosition(newPosition);
		}
		return components;
	}
}

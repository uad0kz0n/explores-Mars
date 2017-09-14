package com.telefonica;

public class Rover {
	private Coordinates coordinates;
	private State status = State.NEW;

	public Rover(Coordinates coordinates) {
		this.coordinates = coordinates;
	}

	/**
	 * @return the coordinates
	 */
	public Coordinates getCoordinates() {
		return coordinates;
	}

	/**
	 * @param coordinates
	 *            the coordinates to set
	 */
	public void setCoordinates(Coordinates coordinates) {
		this.coordinates = coordinates;
	}

	/**
	 * @return the status
	 */
	public State getStatus() {
		return status;
	}

	/**
	 * @param status
	 *            the status to set
	 */
	public void setStatus(State status) {
		this.status = status;
	}

	public void receiveCommands(char[] commands) throws Exception {
		for (char command : commands) {
			if (!receiveCommand(command)) {
				// found Obstacle
				// TODO
				this.status = State.BLOCKED;
				break;
			}
		}
	}

	protected boolean receiveCommand(char command) throws Exception {
		switch (command) {
		case 'f':
			return getCoordinates().moveForward();
		case 'b':
			return getCoordinates().moveBackward();
		case 'l':
			getCoordinates().turnLeft();
			return true;
		case 'r':
			getCoordinates().turnRight();
			return true;
		default:
			throw new UnknownCommandException("Command " + command + " is unknown.");
		}
	}

	public String report() {
		return "["+this.getCoordinates().getCurrentPoint().getX()+","+this.getCoordinates().getCurrentPoint().getX()+","+this.getCoordinates().getDirection()+"]";
	}

}

package com.telefonica;

import java.util.List;

public class Coordinates {

	private Point currentPoint;
	private final Point maxPoint;
	private Direction direction;
	private List<Point> obstacles;
	
	
	public Coordinates(Point currentPoint,Point maxPoint, Direction direction, List<Point> obstacles) {
		super();
		this.currentPoint = currentPoint;
		this.maxPoint = maxPoint;
		this.direction = direction;
		this.obstacles = obstacles;
	}

	
	/**
	 * @return the currentPoint
	 */
	public Point getCurrentPoint() {
		return currentPoint;
	}


	/**
	 * @param currentPoint the currentPoint to set
	 */
	public void setCurrentPoint(Point currentPoint) {
		this.currentPoint = currentPoint;
	}


	/**
	 * @return the maxPoint
	 */
	public Point getMaxPoint() {
		return maxPoint;
	}


 

	/**
	 * @return the direction
	 */
	public Direction getDirection() {
		return direction;
	}

	/**
	 * @param direction
	 *            the direction to set
	 */
	public void setDirection(Direction direction) {
		this.direction = direction;
	}

	/**
	 * @return the obstacles
	 */
	public List<Point> getObstacles() {
		return obstacles;
	}

	/**
	 * @param obstacles
	 *            the obstacles to set
	 */
	public void setObstacles(List<Point> obstacles) {
		this.obstacles = obstacles;
	}


	protected boolean move(Direction directionOrder) {
		int xPoint = currentPoint.getX();
		int yPoint = currentPoint.getY();
		switch (directionOrder) {
		case NORTH:
			yPoint = yPoint + 1;
			break;
		case EAST:
			xPoint = xPoint + 1;
			break;
		case SOUTH:
			yPoint = yPoint - 1;
			break;
		case WEST:
			xPoint = xPoint - 1;
			break;
		}
		
		xPoint=(xPoint+maxPoint.getX()+1)%(maxPoint.getX()+1); //because of 0 point
		yPoint=(yPoint+maxPoint.getY()+1)%(maxPoint.getY()+1); //because of 0 point
		
		if (!hasObstacle(xPoint, yPoint)) {
			currentPoint.setX(xPoint);
			currentPoint.setY(yPoint);
			return true;
		} else {
			return false;
		}
	}

	private boolean hasObstacle(int xPoint, int yPoint) {
		for (Point obstacle : obstacles) {
			if (obstacle.getX() == xPoint && obstacle.getY() == yPoint) {
				return true;
			}
		}
		return false;
	}

	public boolean moveForward() {
		return move(direction);
	}

	public boolean moveBackward() {
		return move( Direction.values()[(direction.getValue()+2)%4]);
	}

	private void changeDirection(Direction directionOrder, int directionStep) {
		int index = (4 + directionOrder.getValue() + directionStep) % 4;
		this.direction = Direction.values()[index];
	}
	//anti-clockwise
	public void turnLeft() {
		changeDirection(direction, -1);
	}
	//clockwise
	public void turnRight() {
		changeDirection(direction, 1);
	}


	
	

}

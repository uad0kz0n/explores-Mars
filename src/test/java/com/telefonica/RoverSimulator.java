package com.telefonica;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

/*
 * Requirements
- You are given the initial starting point (x,y) of a rover and the direction (N,S,E,W) it is facing.
- The rover receives a character array of commands.
- Implement commands that move the rover forward/backward (f,b).
- Implement commands that turn the rover left/right (l,r).
- The output of the rover should be its final co-ordinates and heading.
- Implement wrapping from one edge of the grid to another. (planets are spheres after all)
- Obstacles can be placed anywhere on the grid.
- Implement obstacle detection before each move to a new square. If a given sequence of commands encounters an obstacle, the rover moves up to the last possible point, aborts the sequence and reports the obstacle.

 */
public class RoverSimulator {

	private Rover rover;
	private Coordinates coordinates;
	private final Direction direction = Direction.SOUTH;
	private Point currentPoint;
	private Point maxPoint;
	private List<Point> obstacles;

	@Before
	public void powerOnRover() {

		currentPoint = new Point(1, 1);
		maxPoint = new Point(10, 10);
		obstacles = new ArrayList<Point>();
		coordinates = new Coordinates(currentPoint, maxPoint, direction, obstacles);
		rover = new Rover(coordinates);
	}

	@Test
	public void newInstanceShouldSetInitialStartingPointAndDirection() {
		assertEquals(rover.getCoordinates(), coordinates);
	}

	@Test
	public void receiveCommandShouldMoveForwardWhenCommandIsF() throws Exception {
		int expected = currentPoint.getY() - 1;
		rover.receiveCommand('f');
		assertEquals(expected, rover.getCoordinates().getCurrentPoint().getY());
	}

	@Test
	public void receiveCommandShouldMoveBackwardWhenCommandIsB() throws Exception {
		int expected = currentPoint.getY() + 1;
		rover.receiveCommand('b');
		assertEquals(expected, rover.getCoordinates().getCurrentPoint().getY());
	}

	@Test
	public void receiveCommandShouldTurnLeftWhenCommandIsL() throws Exception {
		rover.receiveCommand('l');
		assertEquals(Direction.EAST, rover.getCoordinates().getDirection());

	}

	@Test
	public void receiveCommandShouldTurnRightWhenCommandIsR() throws Exception {
		rover.receiveCommand('r');
		assertEquals(Direction.WEST, rover.getCoordinates().getDirection());
	}

	@Test(expected = UnknownCommandException.class)
	public void receiveSingleCommandShouldThrowExceptionWhenCommandIsUnknown() throws Exception {
		rover.receiveCommand('a');
	}

	@Test
	public void receiveCommandsShouldBeAble() throws Exception {
		int expected = currentPoint.getX() - 1;
		rover.receiveCommands(new char[] { 'r', 'f', 'r' });
		assertEquals(expected, rover.getCoordinates().getCurrentPoint().getX());
		assertEquals(Direction.NORTH, rover.getCoordinates().getDirection());
	}

	@Test
	public void currentPointShouldBeAdjustedWhenItIsOverMax() throws Exception {
		int expected = maxPoint.getX() + currentPoint.getX() - 1;
		rover.receiveCommands(new char[] { 'r', 'f', 'f' });
		assertEquals(expected, rover.getCoordinates().getCurrentPoint().getX());
	}

	@Test
	public void roverShouldBeStopWhenAObstacleIsFound() throws Exception {
		int expected = currentPoint.getX() + 1;
		rover.getCoordinates().setObstacles(Arrays.asList(new Point(expected + 1, currentPoint.getY())));
		rover.getCoordinates().setDirection(Direction.EAST);

		rover.receiveCommands(new char[] { 'f', 'f', 'b', 'l', 'f' });
		assertEquals(expected, rover.getCoordinates().getCurrentPoint().getX());
		assertEquals(Direction.EAST, (rover.getCoordinates().getDirection()));
	}
	@Test
	public void roverShouldBeReportWhenAObstacleIsFound() throws Exception {
		int expected = currentPoint.getX() + 1;
		rover.getCoordinates().setObstacles(Arrays.asList(new Point(expected + 1, currentPoint.getY())));
		rover.getCoordinates().setDirection(Direction.EAST);

		rover.receiveCommands(new char[] { 'f', 'f', 'b', 'l', 'f' });
		assertEquals(expected, rover.getCoordinates().getCurrentPoint().getX());
		assertEquals(Direction.EAST, (rover.getCoordinates().getDirection()));
		assertEquals(State.BLOCKED,rover.getStatus());
	}

	@Test
	public void positionAndDirectionShouldReturn() throws Exception {
		rover.receiveCommands(new char[] { 'f', 'f', 'b', 'l', 'f' } );
		assertEquals("[2,2,EAST]", rover.report());
	}

}

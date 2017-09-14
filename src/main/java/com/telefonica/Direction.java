package com.telefonica;

public enum Direction {
	//set number clockwise
    NORTH(0, 'N'),
    EAST(1, 'E'),
    SOUTH(2, 'S'),
    WEST(3, 'W');

    private int value;
    private char shortName;
    private Direction(int val, char name) {
        value = val;
        shortName = name;
    }

    public int getValue() {
        return value;
    }

    public char getShortName() { return shortName; }

}

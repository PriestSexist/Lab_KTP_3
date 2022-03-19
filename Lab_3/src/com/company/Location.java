package com.company;

import java.util.Objects;

/**
 * This class represents a specific location in a 2D map.  Coordinates are
 * integer values.
 **/
public class Location {
    /**
     * X coordinate of this location.
     **/
    public int xCoord;

    /**
     * Y coordinate of this location.
     **/
    public int yCoord;


    /**
     * Creates a new location with the specified integer coordinates.
     **/
    public Location(int x, int y) {
        xCoord = x;
        yCoord = y;
    }

    /**
     * Creates a new location with coordinates (0, 0).
     **/
    public Location() {
        this(0, 0);
    }

    @Override
    public boolean equals(Object obj) {
        //проерка, не сравниваем ли мы объект с самим собой
        if (this == obj) return true;
        //проерка, равен ли он 0 и классы совпадают
        if (obj == null || getClass() != obj.getClass()) return false;
        //понижающее преобразование
        Location location = (Location) obj;
        //само сравнение
        return this.xCoord == location.xCoord && this.yCoord == location.yCoord;
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.xCoord, this.yCoord);
    }
}
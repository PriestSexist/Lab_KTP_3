package com.company;

import  java.util.* ;

/**
 * This class stores the basic state necessary for the A* algorithm to compute a
 * path across a map.  This state includes a collection of "open waypoints" and
 * another collection of "closed waypoints."  In addition, this class provides
 * the basic operations that the A* pathfinding algorithm needs to perform its
 * processing.
 **/
public class AStarState
{
    /** This is a reference to the map that the A* algorithm is navigating. **/
    private Map2D map;

    /** Иннициализируем карту всех ОТКРЫТЫХ вершин и их локаций **/
    private Map<Location, Waypoint> open_waypoints = new HashMap<Location, Waypoint> ();

    /** Иннициализируем карту всех ЗАКРЫТЫХ вершин и их локаций **/
    private Map<Location, Waypoint> closed_waypoints = new HashMap<Location, Waypoint> ();

    /**
     * Initialize a new state object for the A* pathfinding algorithm to use.
     **/
    public AStarState(Map2D map)
    {
        if (map == null)
            throw new NullPointerException("map cannot be null");

        this.map = map;
    }

    /** Returns the map that the A* pathfinder is navigating. **/
    public Map2D getMap()
    {
        return map;
    }

    /**
     * This method scans through all open waypoints, and returns the waypoint
     * with the minimum total cost.  If there are no open waypoints, this method
     * returns <code>null</code>.
     **/
    public Waypoint getMinOpenWaypoint()
    {
        // Если количество открытых вершин равно 0 (нет прохода) возвращает Null
        if (numOpenWaypoints() == 0)
            return null;

        // получаем ключи всех открытых вершин
        Set open_waypoint_keys = open_waypoints.keySet();
        // инициализируем итератор, чтобы перебирать ключи
        Iterator i = open_waypoint_keys.iterator();
        Waypoint best = null;
        float best_cost = Float.MAX_VALUE;

        while (i.hasNext())
        {
            // запоминает текущую локацию
            Location location = (Location)i.next();
            // запоминает текущую вершину
            Waypoint waypoint = open_waypoints.get(location);
            // запоминает текущую стоимость вершины
            float waypoint_total_cost = waypoint.getTotalCost();

            if (waypoint_total_cost < best_cost)
            {
                best = open_waypoints.get(location);
                best_cost = waypoint_total_cost;
            }

        }
        // возвращает вершину с минимальной стоимостью
        return best;
    }

    /**
     * This method adds a waypoint to (or potentially updates a waypoint already
     * in) the "open waypoints" collection.  If there is not already an open
     * waypoint at the new waypoint's location then the new waypoint is simply
     * added to the collection.  However, if there is already a waypoint at the
     * new waypoint's location, the new waypoint replaces the old one <em>only
     * if</em> the new waypoint's "previous cost" value is less than the current
     * waypoint's "previous cost" value.
     **/
    public boolean addOpenWaypoint(Waypoint newWP)
    {
        //находим локацию новой вершины
        Location location = newWP.getLocation();

        // проверяем, есть ли уже открытая вершина на новой локации вершины
        if (open_waypoints.containsKey(location))
        {
            // Сравниваем стоимость
            Waypoint current_waypoint = open_waypoints.get(location);
            if (newWP.getPreviousCost() < current_waypoint.getPreviousCost())
            {
                open_waypoints.put(location, newWP);
                return true;
            }
            return false;
        }

        // если ещё нет открытой вершины на новой локации
        // мы просто добавляем новую вершину в коллекцию открытых вершин
        open_waypoints.put(location, newWP);
        return true;
    }


    /** Returns the current number of open waypoints. **/
    public int numOpenWaypoints()
    {
        return open_waypoints.size();
    }


    /**
     * This method moves the waypoint at the specified location from the
     * open list to the closed list.
     **/
    public void closeWaypoint(Location loc)
    {
        Waypoint waypoint = open_waypoints.remove(loc);
        closed_waypoints.put(loc, waypoint);
    }

    /**
     * Returns true if the collection of closed waypoints contains a waypoint
     * for the specified location.
     **/
    public boolean isLocationClosed(Location loc)
    {
        return closed_waypoints.containsKey(loc);
    }
}


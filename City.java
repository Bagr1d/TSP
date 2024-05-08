class City {
    int id;
    double x, y;

    public  City(int id, double x, double y) {
        this.id = id;
        this.x = x;
        this.y = y;
    }

    public double distanceTo(City other) {
        double dx = Math.abs(this.x - other.x);
        double dy = Math.abs(this.y - other.y);
        return Math.sqrt(Math.pow(dx, 2) + Math.pow(dy, 2));
    }

    @Override
    public String toString() {
        return Integer.toString(id);
    }
}
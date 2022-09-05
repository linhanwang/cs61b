public class Wall {
    public int x;
    public int y;
    public boolean isHoriz;

    public Wall(int x, int y, boolean isHoriz) {
        this.x = x;
        this.y = y;
        this.isHoriz = isHoriz;
    }

    public String toString() {
        return "Wall x=" + x + " y=" + y + " isHoriz=" + isHoriz;
    }
}

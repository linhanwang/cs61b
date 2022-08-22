class X {}

class Y extends X {}

class PartI {
    public static void main(String[] args) {
        X x = new X();
        Y y = new Y();

        x = y;
        y = (Y)x;
        // System.out.println(y.toString());

        X[] xa = {new X(), new X()};
        Y[] ya = {new Y(), new Y()};
        // xa = ya;
        xa[0] = ya[0];
        xa[1] = ya[1];
        ya = (Y[])xa;
    }
}

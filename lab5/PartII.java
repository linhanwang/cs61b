class Superclass {
    public void doNothing(int a) {}
}

interface Interface {
    void doNothing(float b);
}

class Subclass extends Superclass implements Interface {
    public void doNothing(float a) {}
}

class PartII {
    public static void main(String[] args) {
        Subclass sub = new Subclass();
    }
}

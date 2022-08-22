class Superclass {
    public static final int kConst = 2;
}

interface Interface {
    public static final int kConst = 3;
}

class Subclass extends Superclass implements Interface  {}

class PartIII {
    public static void main(String[] args) {
        Subclass sub = new Subclass();
        System.out.println(((Superclass)sub).kConst);
        System.out.println(((Interface)sub).kConst);
    }
}

class Superclass {
    public void name() {
        System.out.println("Superclass");
    }

    public void name2() {
        System.out.println("Superclass2");
    }
}

class Subclass extends Superclass {
    public void name() {
        System.out.println("Subclass");
    }
}

class PartIV {
    public static void main(String[] args) {
        Subclass sub = new Subclass();
        ((Superclass) sub).name();

        Superclass superclass = new Superclass();
        // ((Subclass) superclass).name();

        Superclass superclass2 = sub;
        superclass2.name2();
    }
}

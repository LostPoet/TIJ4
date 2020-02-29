package exercises.generics;

import typeinfo.pets.*;
import generics.Holder3;

public class E01_PetsHolder {
    public static void main(String[] args) {
        Holder3<Pet> h3 = new Holder3<Pet>(new Mouse("Mickey"));
        System.out.println(h3.get());
    }
}

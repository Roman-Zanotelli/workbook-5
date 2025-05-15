package com.pluralsight;

import java.util.ArrayList;

public class App {
    public static void main(String[] args) {
        ArrayList<Asset> assets = new ArrayList<>();

        assets.add(new House("Maria's Home","May,13,2025",300000.00,"2119 New Mexico St",3,12,200));
        assets.add(new Vehicle("Maria's Vehicle","May,15,2025",20000.00,"Corvette",2024,0));
        assets.add(new House("Vacation Home","October,20,2025",210000.00,"900 Felp Road",4,2000,20));
        assets.add(new Vehicle("Trackhawk Car","08,31,2025",0,"Jeep",2025,0));
      //for (varType varName in collection){ LOOP LOGIC }
        for (Asset asset : assets){
//            if(asset instanceof House){
//                //Code for when asset is an instance of house
//            }else if (asset instanceof Vehicle) {
//
//            }

            //we don't need to do instanceof because toString automatically calls the outermost subclass
            System.out.println(asset);
        }
    }
}

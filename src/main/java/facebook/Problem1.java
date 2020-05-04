package facebook;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;

public class Problem1 {
//    from here; https://www.glassdoor.co.in/Interview/You-will-be-supplied-with-two-data-files-in-CSV-format-The-first-file-contains-statistics-about-various-dinosaurs-The-se-QTN_2088069.htm
//    You will be supplied with two data files in CSV format.
//    The  first file contains statistics about various dinosaurs.
//    The second file contains additional data.
//    Given the following formula,
//    speed = ((STRIDE_LENGTH / LEG_LENGTH) - 1) * SQRT(LEG_LENGTH * g)
//    Where g = 9.8 m/s^2 (gravitational constant)
//    Write a program to read in the data files from disk, it must then
//    print the names of only the bipedal dinosaurs from fastest to slowest.
//    Do not print any other information.

//    $ cat dataset1.csv
//    NAME,LEG_LENGTH,DIET
//    Hadrosaurus,1.2,herbivore
//    Struthiomimus,0.92,omnivore
//    Velociraptor,1.0,carnivore
//    Triceratops,0.87,herbivore
//    Euoplocephalus,1.6,herbivore
//    Stegosaurus,1.40,herbivore
//    Tyrannosaurus Rex,2.5,carnivore
//
//    $ cat dataset2.csv
//    NAME,STRIDE_LENGTH,STANCE
//    Euoplocephalus,1.87,quadrupedal
//    Stegosaurus,1.90,quadrupedal
//    Tyrannosaurus Rex,5.76,bipedal
//    Hadrosaurus,1.4,bipedal
//    Deinonychus,1.21,bipedal
//    Struthiomimus,1.34,bipedal
//    Velociraptor,2.72,bipedal

//    print the names of only the bipedal dinosaurs from fastest to slowest
//    So we'll start with the second file and loop through looking only for the bipedals
//    if we find a bipedal we'll make a dino class and store it in a list
//          or store it in a hashmap...?
//    next we'll store the first file in a hashmap where the key is the name and the value is the leg_length
//    finally we'll loop through the list of the dinos, calculate the speed, and store the max as we go
    public static List<Dino> getBiPedalStrideLength(){
        List<Dino> dinoList = new LinkedList<>();
        try {
            FileInputStream dinoStream = new FileInputStream("resources/dataset1.csv");
            Scanner sc = new Scanner(dinoStream);
            // get rid of headers
            sc.nextLine();
            while(sc.hasNextLine()){
                String input = sc.nextLine();
                String[] intputSplit = input.split(",");

                Dino newDino = new Dino();
                newDino.name = intputSplit[0];
                newDino.LEG_LENGTH = Float.parseFloat(intputSplit[1]);

                dinoList.add(newDino);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        return dinoList;
    }

    public static HashMap<String, Float> getLegLength(){
        HashMap<String, Float> hmLength = new HashMap<>();

        try {
            FileInputStream dinoStream = new FileInputStream("resources/dataset2.csv");
            Scanner sc = new Scanner(dinoStream);
            // get rid of headers
            sc.nextLine();
            while(sc.hasNextLine()){
                String input = sc.nextLine();
                String[] inputSplit = input.split(",");

                if(inputSplit[2].equals("bipedal")){
                    hmLength.put(inputSplit[0], Float.parseFloat(inputSplit[1]));
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        return hmLength;
    }

    public static void main(String[] args){
        // seems silly to have 3 different hashmaps, could be one dino helper class.
        List<Dino> dinos = getBiPedalStrideLength();
        HashMap<String, Float> hmLegLength = getLegLength();

        for(Dino dino : dinos){
            Float legLength = hmLegLength.get(dino.name);
            if(legLength != null){
                //    speed = ((STRIDE_LENGTH / LEG_LENGTH) - 1) * SQRT(LEG_LENGTH * g)
                dino.speed = (float) (((dino.STRIDE_LENGTH / legLength) - 1) * Math.sqrt(legLength * 9.81));
            } else {
                dino.speed = -1;
            }
        }
        dinos.sort(Comparator.comparing(Dino::getSpeed).reversed());
        for(Dino currDino : dinos){
            if(currDino.speed != -1){
                System.out.println("Name: " + currDino.name + " Speed: " + currDino.speed);
            }
        }
    }
}

class Dino{
    String name;
    float LEG_LENGTH;
    float STRIDE_LENGTH;
    float speed;

    @Override
    public String toString() {
        return "Dino{" +
                "name='" + name + '\'' +
                ", LEG_LENGTH=" + LEG_LENGTH +
                ", STRIDE_LENGTH=" + STRIDE_LENGTH +
                ", speed=" + speed +
                '}';
    }

    public float getSpeed() {
        return speed;
    }

    public void setSpeed(float speed) {
        this.speed = speed;
    }
}
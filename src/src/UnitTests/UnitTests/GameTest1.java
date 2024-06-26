package UnitTests;

import java.util.Random;
import java.util.Scanner;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

/**
 * this file is to test the fundamental functions of the game
 * instance for further development.
 * detail data can be replaced by other files
 * @author yitao chen
 */
public class GameTest1 {
    static class Base {
        int i;
        String name; //ID
        String career; //PlayerCareer 	0:Knight 1:Ranger 2:Wizard 3:Paladin
        void setCareer() {
            Scanner s=new Scanner(System.in);
            System.out.print("0 is Knight; 1 is ranger; 2 is Wizard; 3 is Paladin.  input your career:"+"\n");
            i=s.nextInt();
            switch(i) {
                case 0:career="Knight";break;
                case 1:career="ranger";break;
                case 2:career="Wizard";break;
                case 3:career="Paladin";break;
                default: System.out.print("wrong career"); // TODO: need to jump out of the program and reprint
            }
        }
        String setName() {
            Scanner s=new Scanner(System.in);
            System.out.print("choose your name:"+"\n");
            name=s.next();return name;
        }
        public String getName() {
            return name;
        }
        public String getCareer() {
            return career;
        }
    }
    static class Attribute extends Base{
        int strength;
        int phys;
        int wisdom;
        int Hp;//health
        int Mp=wisdom*20+phys;//magic
        void setAttribute(Base r){
            int sum=0;
            Random random=new Random();
            switch(r.i)
            {
                case(0): //Knight
                    do{
                        strength=random.nextInt(45)+35;
                        phys=random.nextInt(35)+(25);
                        wisdom=random.nextInt(5)+(5);
                        sum=strength+phys+wisdom;
                    } while(sum!=100);break;
                case(1)://Ranger
                    do{
                        strength=random.nextInt(30)+10;
                        phys=random.nextInt(20)+(10);
                        wisdom=random.nextInt(10)+(10);
                        sum=strength+phys+wisdom;
                    } while(sum!=100);
                    break;
                case(2)://Wizard
                    do{
                        strength=random.nextInt(25)+15;
                        phys=random.nextInt(25)+(15);
                        wisdom=random.nextInt(25)+(15);
                        sum=strength+phys+wisdom;
                    } while(sum!=100);
                    break;
                case(3)://Paladin ???
                    do{
                        strength=random.nextInt(20)+10;
                        phys=random.nextInt(20)+(10);
                        wisdom=random.nextInt(25)+(15);
                        sum=strength+phys+wisdom;
                    } while(sum!=100);
                    break;
            }
        }
        public int getStrength() {
            return strength;
        }
        public int getPhys() {
            return phys;
        }
        public int getWisdom() {
            return wisdom;
        }
        public int getHp() {
            return phys*50;
        }
        public int getMagic() {
            return wisdom*20+phys;
        }
        void save(Base base) {
            try {
                FileWriter desFile=new FileWriter("gameTest.txt",true);
                BufferedWriter out=new BufferedWriter(desFile);
                out.write("name："+base.getName()+" Career："+base.getCareer()+
                        " Attributes："+"Strength:"+this.getStrength()+" Physical："+this.getPhys()+
                        " Wisdom："+this.getWisdom()+" HP："+this.getHp()+
                        " MP："+this.getMagic());out.close();
            } catch(FileNotFoundException e) {e.printStackTrace();}
            catch(IOException e) {e.printStackTrace();}
            System.out.println("Saved!");
        }
    }

    public static void main(String[] args) {
        Base base=new Base();
        base.setName();
        base.setCareer();
        Attribute attribute=new Attribute();
        attribute.setAttribute(base);
        System.out.println("The specific attributes of your role are：\n"+"Name："+base.getName()+" Career："+base.getCareer()+
                "   Attributes："+"Strength:"+attribute.getStrength()+" Physical："+attribute.getPhys()+
                " Wisdom："+attribute.getWisdom()+" HP："+attribute.getHp()+
                " MP："+attribute.getMagic());
        attribute.save(base);
    }
}

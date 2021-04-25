import java.util.Random;
import java.util.Scanner;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

import Career.Careers;
import Career.PlayerCareer;

/**
 * this file is to test the fundamental elements of the game
 * detail data can be replaced by other files
 */
public class mainGame {
    static class Base {
        int i;
        String name; //ID
        String career; //PlayerCareer 	0:Knight 1:Ranger 2:Wizard 3:Paladin
        void setCareer() {
            @SuppressWarnings("resource")
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
        int strength;//力量
        int quick;//敏捷
        int phys;//体力
        int wisdom;//智慧
        int Hp;//生命值
        int Mp=wisdom*20+phys;//魔法值
        void setAttribute(Base r){
            int sum=0;
            Random random=new Random();
            switch(r.i)
            {
                case(0): //Knight
                    do{
                        strength=random.nextInt(45)+35;
                        quick=random.nextInt(25)+(15);//敏捷
                        phys=random.nextInt(35)+(25);//体力
                        wisdom=random.nextInt(5)+(5);//智慧
                        sum=strength+quick+phys+wisdom;
                    } while(sum!=100);break;
                case(1)://Ranger
                    do{
                        strength=random.nextInt(30)+10;
                        quick=random.nextInt(40)+(30);//敏捷
                        phys=random.nextInt(20)+(10);//体力
                        wisdom=random.nextInt(10)+(10);//智慧
                        sum=strength+quick+phys+wisdom;
                    } while(sum!=100);
                    break;
                case(2)://Wizard
                    do{
                        strength=random.nextInt(25)+15;
                        quick=random.nextInt(40)+(30);//敏捷
                        phys=random.nextInt(25)+(15);//体力
                        wisdom=random.nextInt(25)+(15);//智慧
                        sum=strength+quick+phys+wisdom;
                    } while(sum!=100);
                    break;
                case(3)://Paladin ???
                    do{
                        strength=random.nextInt(20)+10;
                        quick=random.nextInt(45)+(35);//敏捷
                        phys=random.nextInt(20)+(10);//体力
                        wisdom=random.nextInt(25)+(15);//智慧
                        sum=strength+quick+phys+wisdom;
                    } while(sum!=100);
                    break;
            }
        }
        public int getStrength() {
            return strength;
        }
        public int getQuick() {
            return quick;
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
                FileWriter desFile=new FileWriter("game.txt",true);
                BufferedWriter out=new BufferedWriter(desFile);
                out.write("name："+base.getName()+" Career："+base.getCareer()+
                        " Attributes："+"Strength:"+this.getStrength()+" Quick："+this.getQuick()+" Physical："+this.getPhys()+
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
                "   Attributes："+"Strength:"+attribute.getStrength()+" Quick："+attribute.getQuick()+" Physical："+attribute.getPhys()+
                " Wisdom："+attribute.getWisdom()+" HP："+attribute.getHp()+
                " MP："+attribute.getMagic());
        attribute.save(base);
    }
}

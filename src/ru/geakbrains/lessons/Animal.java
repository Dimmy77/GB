package ru.geakbrains.lessons;

public abstract class Animal {
        protected String name;
        protected String type;

        static final int DEFAULT_RUN=500;
        static final int DEFAULT_SWIM=100;
        protected int limitSwim = DEFAULT_SWIM;
        protected int limitRun=DEFAULT_RUN;
        static int count = 0;

        public Animal() {
            type = "Животное";
            name = "";
            count++;
        }

        public Animal(String type, String name) {
            this.name = name;
            this.type = type;
            count++;
        }

        public void animalInfo() {
            System.out.println("Имя "+type+": " + name);
        }

        public abstract void jump();

        public void setName(String name){
            this.name = name;
        }

    //2.	Все животные могут бежать и плыть. В качестве параметра каждому методу передается длина препятствия.
    // Результатом выполнения действия будет печать в консоль. (Например, dogBobik.run(150); -> 'Бобик пробежал 150 м.');
        public void run(int distance){
            if(limitRun<=0){
                System.out.println(type+" "+ name+ " не умеет бегать.");
            }
            else if(distance <= limitRun){
                System.out.println(type+" "+ name+" пробежал "+" "+distance+" м.");
            }
            else{
                System.out.println(type+" "+ name+ " отказался бежать "+distance+" м.");
            }
        }

        public void swim(int distance) {
            if (limitSwim <= 0) {
                System.out.println(type+" "+ name + " не умеет плавать.");
            } else if (distance <= limitSwim) {
                System.out.println(type+" " + name + " проплыл " + " " + distance + " м.");
            } else {
                System.out.println(type+" "+ name + " отказался плыть "+distance+" м.");
            }
        }
}

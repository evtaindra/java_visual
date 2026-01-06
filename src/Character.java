public class Character {
    //Devinisi Variabel /Property
    public String name;
    public int health;
    public int level;

    //Buat Constructor
    public Character(String xname, int xhealth, int xlevel) {
        this.name = xname;
        this.health = xhealth;
        this.level = xlevel;
    }
    //Buat Method untuk nampilkan Nama
    public String showName() {
        return this.name;   
    }
    public int showLevel(){
        return this.level;
    }
    public int showHealth(){
        return this.health;
    }
    public void upLevel(){
        this.level += 1;
    }
}

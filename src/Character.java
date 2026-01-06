// Character.java
public class Character {
    private String name;
    private int health;
    private int level;

    // Constructor
    public Character(String name, int health, int level) {
        this.name = name;
        this.health = health;
        this.level = level;
    }

    // Method buat naik level
    public void levelUp() {
        level++;
        System.out.println(name + " naik ke level " + level + "!");
    }

    // Method kena damage
    public void takeDamage(int damage) {
        health -= damage;
        if (health < 0) health = 0;
        System.out.println(name + " kena damage! Health sekarang: " + health);
    }

    // Method buat display info (dipake di GUI)
    public String displayInfo() {
        return "Nama: " + name + "\nHealth: " + health + "\nLevel: " + level;
    }

    // Getter (kalo nanti butuh)
    public String getName() { return name; }
    public int getHealth() { return health; }
    public int getLevel() { return level; }
}
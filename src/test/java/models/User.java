package models;

import java.util.ArrayList;
import java.util.Date;

import com.mobilejazz.library.MotisCreation;
import com.mobilejazz.library.MotisValidation;
import com.mobilejazz.library.annotations.MotisArray;
import com.mobilejazz.library.annotations.MotisClass;
import com.mobilejazz.library.annotations.MotisKey;
import com.mobilejazz.library.annotations.MotisMethod;

@MotisClass(dateFormat = "yyyy-MM-dd")
public class User {

    @MotisKey("server_id")
    private int id = 0;

    @MotisKey("user_name")
    private String name;

    @MotisKey("creation_date")
    private Date date;

    @MotisKey("best_friend")
    private User friend;

    @MotisKey("worse_enemies")
    @MotisArray(User.class)
    private ArrayList<User> enemies;

    public void setId(int id) {
        this.id = id;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setDate(Date date) {
        this.date = date;
    }
    public void setFriend(User friend) { this.friend = friend; }
    public void setEnemies(ArrayList<User> enemies) { this.enemies = enemies; }

    public int getId() {
        return id;
    }
    public String getName() {
        return name;
    }
    public Date getDate() { return date; }
    public User getFriend() { return friend; }
    public ArrayList<User> getEnemies() { return enemies; }

    @Override
    public String toString() {
        return super.toString() + " id " + id + " name " + name + " date " + date + " friend {" + friend + "} enemies " + enemies;
    }

    protected void foo(MotisValidation validation) {

        Object object = validation.getObject();

        if (object.getClass().equals(Integer.class))
        {
            Integer value = (Integer)object;
            validation.setObject(new Date(value.intValue()*1000));// <-- convert seconds to milliseconds
        }
        else if (object.getClass().equals(Date.class))
        {
            // Nothing to do
        }
        else
        {
            validation.setValid(false);
        }
     }

    @MotisMethod
    protected void onCreationMotisObject(String name, MotisCreation motisCreation) {
        System.out.println("Object Creation : " + name);
        // This method gives the chance to manually create the object that Motis will automatically assign.
        // Obviously, this method is only used when creating objects from JSONObject instances.
    }


    @MotisMethod
    protected void onCreationMotisArrayObject(String name, MotisCreation motisCreation) {
        System.out.println("Object Creation : " + name);
        // This method gives the chance to manually create the object that Motis will automatically assign.
        // Obviously, this method is only used when creating objects from JSONObject instances.
    }
}

package models;

import java.util.ArrayList;
import java.util.Date;

import com.mobilejazz.library.MotisCreation;
import com.mobilejazz.library.MotisMethodTypes;
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

    @MotisMethod(MotisMethodTypes.ON_CREATION) public void motisOnCreation () {

        System.out.println("Call to motisOnCreation with this data;");

    }

    @MotisMethod(MotisMethodTypes.ON_DID_CREATE) public void motisOnDidCreate (String name, Object object) {

        System.out.println("Call to MotisOnDidCreate with this data; Name -> " + name + " and Object -> " + object.toString());

    }

    @MotisMethod (MotisMethodTypes.IGNORED_SETTER) public void motisIgnoredSetter (String jsonValue, String jsonKey) {

        System.out.println("Call to MotisIgnoredSetter with this data; JsonValue -> " + jsonValue + " and jsonKey -> " + jsonKey);

    }

    @MotisMethod (MotisMethodTypes.INVALID_VALUE) public void motisInvalidValue () {

        System.out.println("Call to motisInvalidValue with this data;");

    }
}

package Day6_POJO;
/* burada workSpartanTestPOJODeserization sinifina baglayacagimiz sinifi olusturuyoruz.
1-   private int id;
    private String name;
    private String gender;
    private long phone;

    2- getter method
    3-setter
    4-constructor for all values
    5- constructor for workSpartanPOJO
    6- toString
 */

public class WorkSpartanPOJO {

    private int id;
    private String name;
    private String gender;
    private long phone;

    @Override
    public String toString() {
        return "workSpartanPOJO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", gender='" + gender + '\'' +
                ", phone=" + phone +
                '}';
    }

    public WorkSpartanPOJO() {
    }

    public WorkSpartanPOJO(int id, String name, String gender, long phone) {
        this.id = id;
        this.name = name;
        this.gender = gender;
        this.phone = phone;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setPhone(long phone) {
        this.phone = phone;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getGender() {
        return gender;
    }

    public long getPhone() {
        return phone;
    }
}

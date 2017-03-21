import java.util.ArrayList;
import java.time.LocalDateTime;

public class JobOpening {
  private String mTitle;
  private String mDescription;
  private String mPhoneNumber;
  private boolean mCompleted;
  private LocalDateTime mCreatedAt;
  private int mEndDate;
  private static ArrayList<JobOpening> instances = new ArrayList<JobOpening>();
  private int mId;

  public JobOpening(String title, String description, String phoneNumber, int endDate) {
    mDescription = description;
    mTitle = title;
    mEndDate = endDate;
    mPhoneNumber = phoneNumber;
    mCompleted = false;
    mCreatedAt = LocalDateTime.now();
    instances.add(this);
    mId = instances.size();
  }

  public int getEndDate(){
    return mEndDate;
  }

  public String getDescription() {
    return mDescription;
  }

  public String getTitle(){
    return mTitle;
  }

  public String getContact(){
    return mPhoneNumber;
  }

  public boolean isCompleted() {
    return mCompleted;
  }

  public LocalDateTime getCreatedAt() {
    return mCreatedAt;
  }

  public static ArrayList<JobOpening> all() {
    return instances;
  }

  public static void clear() {
    instances.clear();
  }

  public int getId() {
    return mId;
  }

  public static JobOpening find(int id) {
    return instances.get(id - 1);
  }

  public int returnTimeLeft(){
    return mEndDate - mCreatedAt.getHour();

  }


}

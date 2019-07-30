package tn.com.biat.user_service.models;

public class Password {
    private String ancienPassword;
    private String newPassword;

    public String getAncienPassword() {
        return ancienPassword;
    }

    public void setAncienPassword(String ancienPassword) {
        this.ancienPassword = ancienPassword;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

    @Override
    public String toString() {
        return "Password{" +
                "ancienPassword='" + ancienPassword + '\'' +
                ", newPassword='" + newPassword + '\'' +
                '}';
    }
}

package com.inva.hipstertest.web.rest.vm;

/**
 * Created by Sensei on 07.06.2017.
 */
public class PasswordChange {
    private String newPassword;
    private String currentPassword;

    public PasswordChange(){

    }

    public PasswordChange(String newPassword, String currentPassword) {
        this.newPassword = newPassword;
        this.currentPassword = currentPassword;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

    public String getCurrentPassword() {
        return currentPassword;
    }

    public void setCurrentPassword(String currentPassword) {
        this.currentPassword = currentPassword;
    }

    @Override
    public String toString() {
        return "PasswordChange{" +
            "newPassword='" + newPassword + '\'' +
            ", currentPassword='" + currentPassword + '\'' +
            '}';
    }
}

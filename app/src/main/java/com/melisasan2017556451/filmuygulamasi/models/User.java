package com.melisasan2017556451.filmuygulamasi.models;

public class User {

        private String Name;
        private String Phone;
        private String Email;

        public User() {
        }

        public User(String name, String phone,String email) {
            this.Name = name;
            this.Phone = phone;
            this.Email = email;
        }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getPhone() {
        return Phone;
    }

    public void setPhone(String phone) {
        Phone = phone;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }
}




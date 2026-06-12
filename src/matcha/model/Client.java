/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package matcha.model;

public class Client extends User {

    private String alamat;

    public Client() {
    }

    public Client(String id, String nama, String email,
                  String password, String noTelp,
                  String alamat) {

        super(id, nama, email, password, noTelp);
        this.alamat = alamat;
    }

    @Override
    public boolean verifyIdentity() {
        return email != null
                && !email.isEmpty()
                && noTelp != null
                && !noTelp.isEmpty();
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }
}

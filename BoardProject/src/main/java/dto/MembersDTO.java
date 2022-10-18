package dto;

import javax.servlet.http.HttpServletRequest;

public class MembersDTO {
    private String id;
    private String pw;
    private String name;
    private String phone;
    private String email;
    private String zipcode;
    private String address1;
    private String address2;
    private String signupDate;

    public String getSignupDate() {
        return signupDate;
    }

    public void setSignupDate(String signupDate) {
        this.signupDate = signupDate;
    }

    public MembersDTO(HttpServletRequest request){
        this.id = request.getParameter("id");
        this.pw = request.getParameter("pw1");
        this.name = request.getParameter("name");
        this.phone = request.getParameter("phone");
        this.email = request.getParameter("email");
        this.zipcode = request.getParameter("zipcode");
        this.address1 = request.getParameter("address1");
        this.address2 = request.getParameter("address2");
        this.signupDate = "";
    }

    public MembersDTO(String id, String pw, String name, String phone, String email, String zipcode, String address1, String address2, String signupDate) {
        this.id = id;
        this.pw = pw;
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.zipcode = zipcode;
        this.address1 = address1;
        this.address2 = address2;
        this.signupDate = signupDate;
    }

    public MembersDTO() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPw() {
        return pw;
    }

    public void setPw(String pw) {
        this.pw = pw;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getZipcode() {
        return zipcode;
    }

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }

    public String getAddress1() {
        return address1;
    }

    public void setAddress1(String address1) {
        this.address1 = address1;
    }

    public String getAddress2() {
        return address2;
    }

    public void setAddress2(String address2) {
        this.address2 = address2;
    }
}

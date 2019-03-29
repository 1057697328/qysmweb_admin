package generator;

import java.io.Serializable;

/**
 * m_config
 * @author 
 */
public class MConfig implements Serializable {
    /**
     * 设置ID
     */
    private Integer cid;

    /**
     * 联系地址
     */
    private String address;

    /**
     * 联系电话
     */
    private String phone1;

    /**
     * 联系电话2（手机号）
     */
    private String phone2;

    /**
     * 电子邮件1
     */
    private String email1;

    private static final long serialVersionUID = 1L;

    public Integer getCid() {
        return cid;
    }

    public void setCid(Integer cid) {
        this.cid = cid;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone1() {
        return phone1;
    }

    public void setPhone1(String phone1) {
        this.phone1 = phone1;
    }

    public String getPhone2() {
        return phone2;
    }

    public void setPhone2(String phone2) {
        this.phone2 = phone2;
    }

    public String getEmail1() {
        return email1;
    }

    public void setEmail1(String email1) {
        this.email1 = email1;
    }
}
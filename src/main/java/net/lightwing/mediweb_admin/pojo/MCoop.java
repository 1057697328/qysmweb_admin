package net.lightwing.mediweb_admin.pojo;

import java.io.Serializable;

/**
 * m_coop
 * @author 
 */
public class MCoop implements Serializable {
    /**
     * 合作机构编号
     */
    private Integer coid;

    /**
     * 合作机构名称
     */
    private String coname;

    /**
     * 合作机构Logo图URL地址
     */
    private String imgpath;

    /**
     * 合作机构网站地址
     */
    private String weburl;

    private static final long serialVersionUID = 1L;

    public Integer getCoid() {
        return coid;
    }

    public void setCoid(Integer coid) {
        this.coid = coid;
    }

    public String getConame() {
        return coname;
    }

    public void setConame(String coname) {
        this.coname = coname;
    }

    public String getImgpath() {
        return imgpath;
    }

    public void setImgpath(String imgpath) {
        this.imgpath = imgpath;
    }

    public String getWeburl() {
        return weburl;
    }

    public void setWeburl(String weburl) {
        this.weburl = weburl;
    }

    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        MCoop other = (MCoop) that;
        return (this.getCoid() == null ? other.getCoid() == null : this.getCoid().equals(other.getCoid()))
            && (this.getConame() == null ? other.getConame() == null : this.getConame().equals(other.getConame()))
            && (this.getImgpath() == null ? other.getImgpath() == null : this.getImgpath().equals(other.getImgpath()))
            && (this.getWeburl() == null ? other.getWeburl() == null : this.getWeburl().equals(other.getWeburl()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getCoid() == null) ? 0 : getCoid().hashCode());
        result = prime * result + ((getConame() == null) ? 0 : getConame().hashCode());
        result = prime * result + ((getImgpath() == null) ? 0 : getImgpath().hashCode());
        result = prime * result + ((getWeburl() == null) ? 0 : getWeburl().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", coid=").append(coid);
        sb.append(", coname=").append(coname);
        sb.append(", imgpath=").append(imgpath);
        sb.append(", weburl=").append(weburl);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}
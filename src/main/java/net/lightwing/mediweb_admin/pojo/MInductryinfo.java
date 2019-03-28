package net.lightwing.mediweb_admin.pojo;

import lombok.Data;

import java.beans.Transient;
import java.io.Serializable;

/**
 * m_inductryinfo
 *
 * @author
 */
@Data
public class MInductryinfo implements Serializable {
    /**
     * 产业信息ID
     */
    private Integer iiid;

    /**
     * 产业板块ID
     */
    private Integer iid;

    /**
     * 产业信息名称
     */
    private String iiname;

    /**
     * 产业信息详情
     */
    private String iidetail;


    private MInductry mInductry;

    private static final long serialVersionUID = 1L;

    public Integer getIiid() {
        return iiid;
    }

    public void setIiid(Integer iiid) {
        this.iiid = iiid;
    }

    public Integer getIid() {
        return iid;
    }

    public void setIid(Integer iid) {
        this.iid = iid;
    }

    public String getIiname() {
        return iiname;
    }

    public void setIiname(String iiname) {
        this.iiname = iiname;
    }

    public String getIidetail() {
        return iidetail;
    }

    public void setIidetail(String iidetail) {
        this.iidetail = iidetail;
    }
}
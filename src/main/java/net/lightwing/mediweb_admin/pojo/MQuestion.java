package net.lightwing.mediweb_admin.pojo;

import java.io.Serializable;
import java.util.Date;

/**
 * m_question
 * @author 
 */
public class MQuestion implements Serializable {
    /**
     * 科普答疑ID
     */
    private Integer qid;

    /**
     * 科普答疑名称
     */
    private String qname;

    /**
     * 科普答疑创建时间
     */
    private Date createtime;

    /**
     * 科普答疑答案
     */
    private String qanswer;

    private static final long serialVersionUID = 1L;

    public Integer getQid() {
        return qid;
    }

    public void setQid(Integer qid) {
        this.qid = qid;
    }

    public String getQname() {
        return qname;
    }

    public void setQname(String qname) {
        this.qname = qname;
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public String getQanswer() {
        return qanswer;
    }

    public void setQanswer(String qanswer) {
        this.qanswer = qanswer;
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
        MQuestion other = (MQuestion) that;
        return (this.getQid() == null ? other.getQid() == null : this.getQid().equals(other.getQid()))
            && (this.getQname() == null ? other.getQname() == null : this.getQname().equals(other.getQname()))
            && (this.getCreatetime() == null ? other.getCreatetime() == null : this.getCreatetime().equals(other.getCreatetime()))
            && (this.getQanswer() == null ? other.getQanswer() == null : this.getQanswer().equals(other.getQanswer()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getQid() == null) ? 0 : getQid().hashCode());
        result = prime * result + ((getQname() == null) ? 0 : getQname().hashCode());
        result = prime * result + ((getCreatetime() == null) ? 0 : getCreatetime().hashCode());
        result = prime * result + ((getQanswer() == null) ? 0 : getQanswer().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", qid=").append(qid);
        sb.append(", qname=").append(qname);
        sb.append(", createtime=").append(createtime);
        sb.append(", qanswer=").append(qanswer);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}
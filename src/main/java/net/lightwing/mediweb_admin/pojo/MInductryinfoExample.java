package net.lightwing.mediweb_admin.pojo;

import java.util.ArrayList;
import java.util.List;

public class MInductryinfoExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    private Integer limit;

    private Long offset;

    public MInductryinfoExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    public String getOrderByClause() {
        return orderByClause;
    }

    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    public boolean isDistinct() {
        return distinct;
    }

    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    public void setLimit(Integer limit) {
        this.limit = limit;
    }

    public Integer getLimit() {
        return limit;
    }

    public void setOffset(Long offset) {
        this.offset = offset;
    }

    public Long getOffset() {
        return offset;
    }

    protected abstract static class GeneratedCriteria {
        protected List<Criterion> criteria;

        protected GeneratedCriteria() {
            super();
            criteria = new ArrayList<Criterion>();
        }

        public boolean isValid() {
            return criteria.size() > 0;
        }

        public List<Criterion> getAllCriteria() {
            return criteria;
        }

        public List<Criterion> getCriteria() {
            return criteria;
        }

        protected void addCriterion(String condition) {
            if (condition == null) {
                throw new RuntimeException("Value for condition cannot be null");
            }
            criteria.add(new Criterion(condition));
        }

        protected void addCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value));
        }

        protected void addCriterion(String condition, Object value1, Object value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value1, value2));
        }

        public Criteria andIiidIsNull() {
            addCriterion("iiid is null");
            return (Criteria) this;
        }

        public Criteria andIiidIsNotNull() {
            addCriterion("iiid is not null");
            return (Criteria) this;
        }

        public Criteria andIiidEqualTo(Integer value) {
            addCriterion("iiid =", value, "iiid");
            return (Criteria) this;
        }

        public Criteria andIiidNotEqualTo(Integer value) {
            addCriterion("iiid <>", value, "iiid");
            return (Criteria) this;
        }

        public Criteria andIiidGreaterThan(Integer value) {
            addCriterion("iiid >", value, "iiid");
            return (Criteria) this;
        }

        public Criteria andIiidGreaterThanOrEqualTo(Integer value) {
            addCriterion("iiid >=", value, "iiid");
            return (Criteria) this;
        }

        public Criteria andIiidLessThan(Integer value) {
            addCriterion("iiid <", value, "iiid");
            return (Criteria) this;
        }

        public Criteria andIiidLessThanOrEqualTo(Integer value) {
            addCriterion("iiid <=", value, "iiid");
            return (Criteria) this;
        }

        public Criteria andIiidIn(List<Integer> values) {
            addCriterion("iiid in", values, "iiid");
            return (Criteria) this;
        }

        public Criteria andIiidNotIn(List<Integer> values) {
            addCriterion("iiid not in", values, "iiid");
            return (Criteria) this;
        }

        public Criteria andIiidBetween(Integer value1, Integer value2) {
            addCriterion("iiid between", value1, value2, "iiid");
            return (Criteria) this;
        }

        public Criteria andIiidNotBetween(Integer value1, Integer value2) {
            addCriterion("iiid not between", value1, value2, "iiid");
            return (Criteria) this;
        }

        public Criteria andIidIsNull() {
            addCriterion("iid is null");
            return (Criteria) this;
        }

        public Criteria andIidIsNotNull() {
            addCriterion("iid is not null");
            return (Criteria) this;
        }

        public Criteria andIidEqualTo(Integer value) {
            addCriterion("iid =", value, "iid");
            return (Criteria) this;
        }

        public Criteria andIidNotEqualTo(Integer value) {
            addCriterion("iid <>", value, "iid");
            return (Criteria) this;
        }

        public Criteria andIidGreaterThan(Integer value) {
            addCriterion("iid >", value, "iid");
            return (Criteria) this;
        }

        public Criteria andIidGreaterThanOrEqualTo(Integer value) {
            addCriterion("iid >=", value, "iid");
            return (Criteria) this;
        }

        public Criteria andIidLessThan(Integer value) {
            addCriterion("iid <", value, "iid");
            return (Criteria) this;
        }

        public Criteria andIidLessThanOrEqualTo(Integer value) {
            addCriterion("iid <=", value, "iid");
            return (Criteria) this;
        }

        public Criteria andIidIn(List<Integer> values) {
            addCriterion("iid in", values, "iid");
            return (Criteria) this;
        }

        public Criteria andIidNotIn(List<Integer> values) {
            addCriterion("iid not in", values, "iid");
            return (Criteria) this;
        }

        public Criteria andIidBetween(Integer value1, Integer value2) {
            addCriterion("iid between", value1, value2, "iid");
            return (Criteria) this;
        }

        public Criteria andIidNotBetween(Integer value1, Integer value2) {
            addCriterion("iid not between", value1, value2, "iid");
            return (Criteria) this;
        }

        public Criteria andIinameIsNull() {
            addCriterion("iiname is null");
            return (Criteria) this;
        }

        public Criteria andIinameIsNotNull() {
            addCriterion("iiname is not null");
            return (Criteria) this;
        }

        public Criteria andIinameEqualTo(String value) {
            addCriterion("iiname =", value, "iiname");
            return (Criteria) this;
        }

        public Criteria andIinameNotEqualTo(String value) {
            addCriterion("iiname <>", value, "iiname");
            return (Criteria) this;
        }

        public Criteria andIinameGreaterThan(String value) {
            addCriterion("iiname >", value, "iiname");
            return (Criteria) this;
        }

        public Criteria andIinameGreaterThanOrEqualTo(String value) {
            addCriterion("iiname >=", value, "iiname");
            return (Criteria) this;
        }

        public Criteria andIinameLessThan(String value) {
            addCriterion("iiname <", value, "iiname");
            return (Criteria) this;
        }

        public Criteria andIinameLessThanOrEqualTo(String value) {
            addCriterion("iiname <=", value, "iiname");
            return (Criteria) this;
        }

        public Criteria andIinameLike(String value) {
            addCriterion("iiname like", value, "iiname");
            return (Criteria) this;
        }

        public Criteria andIinameNotLike(String value) {
            addCriterion("iiname not like", value, "iiname");
            return (Criteria) this;
        }

        public Criteria andIinameIn(List<String> values) {
            addCriterion("iiname in", values, "iiname");
            return (Criteria) this;
        }

        public Criteria andIinameNotIn(List<String> values) {
            addCriterion("iiname not in", values, "iiname");
            return (Criteria) this;
        }

        public Criteria andIinameBetween(String value1, String value2) {
            addCriterion("iiname between", value1, value2, "iiname");
            return (Criteria) this;
        }

        public Criteria andIinameNotBetween(String value1, String value2) {
            addCriterion("iiname not between", value1, value2, "iiname");
            return (Criteria) this;
        }
    }

    /**
     */
    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    public static class Criterion {
        private String condition;

        private Object value;

        private Object secondValue;

        private boolean noValue;

        private boolean singleValue;

        private boolean betweenValue;

        private boolean listValue;

        private String typeHandler;

        public String getCondition() {
            return condition;
        }

        public Object getValue() {
            return value;
        }

        public Object getSecondValue() {
            return secondValue;
        }

        public boolean isNoValue() {
            return noValue;
        }

        public boolean isSingleValue() {
            return singleValue;
        }

        public boolean isBetweenValue() {
            return betweenValue;
        }

        public boolean isListValue() {
            return listValue;
        }

        public String getTypeHandler() {
            return typeHandler;
        }

        protected Criterion(String condition) {
            super();
            this.condition = condition;
            this.typeHandler = null;
            this.noValue = true;
        }

        protected Criterion(String condition, Object value, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.typeHandler = typeHandler;
            if (value instanceof List<?>) {
                this.listValue = true;
            } else {
                this.singleValue = true;
            }
        }

        protected Criterion(String condition, Object value) {
            this(condition, value, null);
        }

        protected Criterion(String condition, Object value, Object secondValue, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.secondValue = secondValue;
            this.typeHandler = typeHandler;
            this.betweenValue = true;
        }

        protected Criterion(String condition, Object value, Object secondValue) {
            this(condition, value, secondValue, null);
        }
    }
}
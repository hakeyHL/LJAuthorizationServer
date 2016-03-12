package cn.com.yunqitong.domain;

import java.util.ArrayList;
import java.util.List;

public class TMailingExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public TMailingExample() {
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

        public Criteria andMccountidIsNull() {
            addCriterion("mccountid is null");
            return (Criteria) this;
        }

        public Criteria andMccountidIsNotNull() {
            addCriterion("mccountid is not null");
            return (Criteria) this;
        }

        public Criteria andMccountidEqualTo(String value) {
            addCriterion("mccountid =", value, "mccountid");
            return (Criteria) this;
        }

        public Criteria andMccountidNotEqualTo(String value) {
            addCriterion("mccountid <>", value, "mccountid");
            return (Criteria) this;
        }

        public Criteria andMccountidGreaterThan(String value) {
            addCriterion("mccountid >", value, "mccountid");
            return (Criteria) this;
        }

        public Criteria andMccountidGreaterThanOrEqualTo(String value) {
            addCriterion("mccountid >=", value, "mccountid");
            return (Criteria) this;
        }

        public Criteria andMccountidLessThan(String value) {
            addCriterion("mccountid <", value, "mccountid");
            return (Criteria) this;
        }

        public Criteria andMccountidLessThanOrEqualTo(String value) {
            addCriterion("mccountid <=", value, "mccountid");
            return (Criteria) this;
        }

        public Criteria andMccountidLike(String value) {
            addCriterion("mccountid like", value, "mccountid");
            return (Criteria) this;
        }

        public Criteria andMccountidNotLike(String value) {
            addCriterion("mccountid not like", value, "mccountid");
            return (Criteria) this;
        }

        public Criteria andMccountidIn(List<String> values) {
            addCriterion("mccountid in", values, "mccountid");
            return (Criteria) this;
        }

        public Criteria andMccountidNotIn(List<String> values) {
            addCriterion("mccountid not in", values, "mccountid");
            return (Criteria) this;
        }

        public Criteria andMccountidBetween(String value1, String value2) {
            addCriterion("mccountid between", value1, value2, "mccountid");
            return (Criteria) this;
        }

        public Criteria andMccountidNotBetween(String value1, String value2) {
            addCriterion("mccountid not between", value1, value2, "mccountid");
            return (Criteria) this;
        }

        public Criteria andFaccountidIsNull() {
            addCriterion("faccountid is null");
            return (Criteria) this;
        }

        public Criteria andFaccountidIsNotNull() {
            addCriterion("faccountid is not null");
            return (Criteria) this;
        }

        public Criteria andFaccountidEqualTo(String value) {
            addCriterion("faccountid =", value, "faccountid");
            return (Criteria) this;
        }

        public Criteria andFaccountidNotEqualTo(String value) {
            addCriterion("faccountid <>", value, "faccountid");
            return (Criteria) this;
        }

        public Criteria andFaccountidGreaterThan(String value) {
            addCriterion("faccountid >", value, "faccountid");
            return (Criteria) this;
        }

        public Criteria andFaccountidGreaterThanOrEqualTo(String value) {
            addCriterion("faccountid >=", value, "faccountid");
            return (Criteria) this;
        }

        public Criteria andFaccountidLessThan(String value) {
            addCriterion("faccountid <", value, "faccountid");
            return (Criteria) this;
        }

        public Criteria andFaccountidLessThanOrEqualTo(String value) {
            addCriterion("faccountid <=", value, "faccountid");
            return (Criteria) this;
        }

        public Criteria andFaccountidLike(String value) {
            addCriterion("faccountid like", value, "faccountid");
            return (Criteria) this;
        }

        public Criteria andFaccountidNotLike(String value) {
            addCriterion("faccountid not like", value, "faccountid");
            return (Criteria) this;
        }

        public Criteria andFaccountidIn(List<String> values) {
            addCriterion("faccountid in", values, "faccountid");
            return (Criteria) this;
        }

        public Criteria andFaccountidNotIn(List<String> values) {
            addCriterion("faccountid not in", values, "faccountid");
            return (Criteria) this;
        }

        public Criteria andFaccountidBetween(String value1, String value2) {
            addCriterion("faccountid between", value1, value2, "faccountid");
            return (Criteria) this;
        }

        public Criteria andFaccountidNotBetween(String value1, String value2) {
            addCriterion("faccountid not between", value1, value2, "faccountid");
            return (Criteria) this;
        }
        
    }

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
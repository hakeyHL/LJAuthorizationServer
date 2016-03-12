package cn.com.yunqitong.domain;

import java.util.ArrayList;
import java.util.List;

public class TCallRecordExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public TCallRecordExample() {
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

        public Criteria andCallidIsNull() {
            addCriterion("callid is null");
            return (Criteria) this;
        }

        public Criteria andCallidIsNotNull() {
            addCriterion("callid is not null");
            return (Criteria) this;
        }

        public Criteria andCallidEqualTo(String value) {
            addCriterion("callid =", value, "callid");
            return (Criteria) this;
        }

        public Criteria andCallidNotEqualTo(String value) {
            addCriterion("callid <>", value, "callid");
            return (Criteria) this;
        }

        public Criteria andCallidGreaterThan(String value) {
            addCriterion("callid >", value, "callid");
            return (Criteria) this;
        }

        public Criteria andCallidGreaterThanOrEqualTo(String value) {
            addCriterion("callid >=", value, "callid");
            return (Criteria) this;
        }

        public Criteria andCallidLessThan(String value) {
            addCriterion("callid <", value, "callid");
            return (Criteria) this;
        }

        public Criteria andCallidLessThanOrEqualTo(String value) {
            addCriterion("callid <=", value, "callid");
            return (Criteria) this;
        }

        public Criteria andCallidLike(String value) {
            addCriterion("callid like", value, "callid");
            return (Criteria) this;
        }

        public Criteria andCallidNotLike(String value) {
            addCriterion("callid not like", value, "callid");
            return (Criteria) this;
        }

        public Criteria andCallidIn(List<String> values) {
            addCriterion("callid in", values, "callid");
            return (Criteria) this;
        }

        public Criteria andCallidNotIn(List<String> values) {
            addCriterion("callid not in", values, "callid");
            return (Criteria) this;
        }

        public Criteria andCallidBetween(String value1, String value2) {
            addCriterion("callid between", value1, value2, "callid");
            return (Criteria) this;
        }

        public Criteria andCallidNotBetween(String value1, String value2) {
            addCriterion("callid not between", value1, value2, "callid");
            return (Criteria) this;
        }

        public Criteria andCalledsIsNull() {
            addCriterion("calleds is null");
            return (Criteria) this;
        }

        public Criteria andCalledsIsNotNull() {
            addCriterion("calleds is not null");
            return (Criteria) this;
        }

        public Criteria andCalledsEqualTo(String value) {
            addCriterion("calleds =", value, "calleds");
            return (Criteria) this;
        }

        public Criteria andCalledsNotEqualTo(String value) {
            addCriterion("calleds <>", value, "calleds");
            return (Criteria) this;
        }

        public Criteria andCalledsGreaterThan(String value) {
            addCriterion("calleds >", value, "calleds");
            return (Criteria) this;
        }

        public Criteria andCalledsGreaterThanOrEqualTo(String value) {
            addCriterion("calleds >=", value, "calleds");
            return (Criteria) this;
        }

        public Criteria andCalledsLessThan(String value) {
            addCriterion("calleds <", value, "calleds");
            return (Criteria) this;
        }

        public Criteria andCalledsLessThanOrEqualTo(String value) {
            addCriterion("calleds <=", value, "calleds");
            return (Criteria) this;
        }

        public Criteria andCalledsLike(String value) {
            addCriterion("calleds like", value, "calleds");
            return (Criteria) this;
        }

        public Criteria andCalledsNotLike(String value) {
            addCriterion("calleds not like", value, "calleds");
            return (Criteria) this;
        }

        public Criteria andCalledsIn(List<String> values) {
            addCriterion("calleds in", values, "calleds");
            return (Criteria) this;
        }

        public Criteria andCalledsNotIn(List<String> values) {
            addCriterion("calleds not in", values, "calleds");
            return (Criteria) this;
        }

        public Criteria andCalledsBetween(String value1, String value2) {
            addCriterion("calleds between", value1, value2, "calleds");
            return (Criteria) this;
        }

        public Criteria andCalledsNotBetween(String value1, String value2) {
            addCriterion("calleds not between", value1, value2, "calleds");
            return (Criteria) this;
        }

        public Criteria andCalledstatusIsNull() {
            addCriterion("calledstatus is null");
            return (Criteria) this;
        }

        public Criteria andCalledstatusIsNotNull() {
            addCriterion("calledstatus is not null");
            return (Criteria) this;
        }

        public Criteria andCalledstatusEqualTo(Integer value) {
            addCriterion("calledstatus =", value, "calledstatus");
            return (Criteria) this;
        }

        public Criteria andCalledstatusNotEqualTo(Integer value) {
            addCriterion("calledstatus <>", value, "calledstatus");
            return (Criteria) this;
        }

        public Criteria andCalledstatusGreaterThan(Integer value) {
            addCriterion("calledstatus >", value, "calledstatus");
            return (Criteria) this;
        }

        public Criteria andCalledstatusGreaterThanOrEqualTo(Integer value) {
            addCriterion("calledstatus >=", value, "calledstatus");
            return (Criteria) this;
        }

        public Criteria andCalledstatusLessThan(Integer value) {
            addCriterion("calledstatus <", value, "calledstatus");
            return (Criteria) this;
        }

        public Criteria andCalledstatusLessThanOrEqualTo(Integer value) {
            addCriterion("calledstatus <=", value, "calledstatus");
            return (Criteria) this;
        }

        public Criteria andCalledstatusIn(List<Integer> values) {
            addCriterion("calledstatus in", values, "calledstatus");
            return (Criteria) this;
        }

        public Criteria andCalledstatusNotIn(List<Integer> values) {
            addCriterion("calledstatus not in", values, "calledstatus");
            return (Criteria) this;
        }

        public Criteria andCalledstatusBetween(Integer value1, Integer value2) {
            addCriterion("calledstatus between", value1, value2, "calledstatus");
            return (Criteria) this;
        }

        public Criteria andCalledstatusNotBetween(Integer value1, Integer value2) {
            addCriterion("calledstatus not between", value1, value2, "calledstatus");
            return (Criteria) this;
        }

        public Criteria andCalleridIsNull() {
            addCriterion("callerid is null");
            return (Criteria) this;
        }

        public Criteria andCalleridIsNotNull() {
            addCriterion("callerid is not null");
            return (Criteria) this;
        }

        public Criteria andCalleridEqualTo(String value) {
            addCriterion("callerid =", value, "callerid");
            return (Criteria) this;
        }

        public Criteria andCalleridNotEqualTo(String value) {
            addCriterion("callerid <>", value, "callerid");
            return (Criteria) this;
        }

        public Criteria andCalleridGreaterThan(String value) {
            addCriterion("callerid >", value, "callerid");
            return (Criteria) this;
        }

        public Criteria andCalleridGreaterThanOrEqualTo(String value) {
            addCriterion("callerid >=", value, "callerid");
            return (Criteria) this;
        }

        public Criteria andCalleridLessThan(String value) {
            addCriterion("callerid <", value, "callerid");
            return (Criteria) this;
        }

        public Criteria andCalleridLessThanOrEqualTo(String value) {
            addCriterion("callerid <=", value, "callerid");
            return (Criteria) this;
        }

        public Criteria andCalleridLike(String value) {
            addCriterion("callerid like", value, "callerid");
            return (Criteria) this;
        }

        public Criteria andCalleridNotLike(String value) {
            addCriterion("callerid not like", value, "callerid");
            return (Criteria) this;
        }

        public Criteria andCalleridIn(List<String> values) {
            addCriterion("callerid in", values, "callerid");
            return (Criteria) this;
        }

        public Criteria andCalleridNotIn(List<String> values) {
            addCriterion("callerid not in", values, "callerid");
            return (Criteria) this;
        }

        public Criteria andCalleridBetween(String value1, String value2) {
            addCriterion("callerid between", value1, value2, "callerid");
            return (Criteria) this;
        }

        public Criteria andCalleridNotBetween(String value1, String value2) {
            addCriterion("callerid not between", value1, value2, "callerid");
            return (Criteria) this;
        }

        public Criteria andCallerstatusIsNull() {
            addCriterion("callerstatus is null");
            return (Criteria) this;
        }

        public Criteria andCallerstatusIsNotNull() {
            addCriterion("callerstatus is not null");
            return (Criteria) this;
        }

        public Criteria andCallerstatusEqualTo(Integer value) {
            addCriterion("callerstatus =", value, "callerstatus");
            return (Criteria) this;
        }

        public Criteria andCallerstatusNotEqualTo(Integer value) {
            addCriterion("callerstatus <>", value, "callerstatus");
            return (Criteria) this;
        }

        public Criteria andCallerstatusGreaterThan(Integer value) {
            addCriterion("callerstatus >", value, "callerstatus");
            return (Criteria) this;
        }

        public Criteria andCallerstatusGreaterThanOrEqualTo(Integer value) {
            addCriterion("callerstatus >=", value, "callerstatus");
            return (Criteria) this;
        }

        public Criteria andCallerstatusLessThan(Integer value) {
            addCriterion("callerstatus <", value, "callerstatus");
            return (Criteria) this;
        }

        public Criteria andCallerstatusLessThanOrEqualTo(Integer value) {
            addCriterion("callerstatus <=", value, "callerstatus");
            return (Criteria) this;
        }

        public Criteria andCallerstatusIn(List<Integer> values) {
            addCriterion("callerstatus in", values, "callerstatus");
            return (Criteria) this;
        }

        public Criteria andCallerstatusNotIn(List<Integer> values) {
            addCriterion("callerstatus not in", values, "callerstatus");
            return (Criteria) this;
        }

        public Criteria andCallerstatusBetween(Integer value1, Integer value2) {
            addCriterion("callerstatus between", value1, value2, "callerstatus");
            return (Criteria) this;
        }

        public Criteria andCallerstatusNotBetween(Integer value1, Integer value2) {
            addCriterion("callerstatus not between", value1, value2, "callerstatus");
            return (Criteria) this;
        }

        public Criteria andMeetingidIsNull() {
            addCriterion("meetingid is null");
            return (Criteria) this;
        }

        public Criteria andMeetingidIsNotNull() {
            addCriterion("meetingid is not null");
            return (Criteria) this;
        }

        public Criteria andMeetingidEqualTo(String value) {
            addCriterion("meetingid =", value, "meetingid");
            return (Criteria) this;
        }

        public Criteria andMeetingidNotEqualTo(String value) {
            addCriterion("meetingid <>", value, "meetingid");
            return (Criteria) this;
        }

        public Criteria andMeetingidGreaterThan(String value) {
            addCriterion("meetingid >", value, "meetingid");
            return (Criteria) this;
        }

        public Criteria andMeetingidGreaterThanOrEqualTo(String value) {
            addCriterion("meetingid >=", value, "meetingid");
            return (Criteria) this;
        }

        public Criteria andMeetingidLessThan(String value) {
            addCriterion("meetingid <", value, "meetingid");
            return (Criteria) this;
        }

        public Criteria andMeetingidLessThanOrEqualTo(String value) {
            addCriterion("meetingid <=", value, "meetingid");
            return (Criteria) this;
        }

        public Criteria andMeetingidLike(String value) {
            addCriterion("meetingid like", value, "meetingid");
            return (Criteria) this;
        }

        public Criteria andMeetingidNotLike(String value) {
            addCriterion("meetingid not like", value, "meetingid");
            return (Criteria) this;
        }

        public Criteria andMeetingidIn(List<String> values) {
            addCriterion("meetingid in", values, "meetingid");
            return (Criteria) this;
        }

        public Criteria andMeetingidNotIn(List<String> values) {
            addCriterion("meetingid not in", values, "meetingid");
            return (Criteria) this;
        }

        public Criteria andMeetingidBetween(String value1, String value2) {
            addCriterion("meetingid between", value1, value2, "meetingid");
            return (Criteria) this;
        }

        public Criteria andMeetingidNotBetween(String value1, String value2) {
            addCriterion("meetingid not between", value1, value2, "meetingid");
            return (Criteria) this;
        }

        public Criteria andCreatetimeIsNull() {
            addCriterion("createtime is null");
            return (Criteria) this;
        }

        public Criteria andCreatetimeIsNotNull() {
            addCriterion("createtime is not null");
            return (Criteria) this;
        }

        public Criteria andCreatetimeEqualTo(String value) {
            addCriterion("createtime =", value, "createtime");
            return (Criteria) this;
        }

        public Criteria andCreatetimeNotEqualTo(String value) {
            addCriterion("createtime <>", value, "createtime");
            return (Criteria) this;
        }

        public Criteria andCreatetimeGreaterThan(String value) {
            addCriterion("createtime >", value, "createtime");
            return (Criteria) this;
        }

        public Criteria andCreatetimeGreaterThanOrEqualTo(String value) {
            addCriterion("createtime >=", value, "createtime");
            return (Criteria) this;
        }

        public Criteria andCreatetimeLessThan(String value) {
            addCriterion("createtime <", value, "createtime");
            return (Criteria) this;
        }

        public Criteria andCreatetimeLessThanOrEqualTo(String value) {
            addCriterion("createtime <=", value, "createtime");
            return (Criteria) this;
        }

        public Criteria andCreatetimeLike(String value) {
            addCriterion("createtime like", value, "createtime");
            return (Criteria) this;
        }

        public Criteria andCreatetimeNotLike(String value) {
            addCriterion("createtime not like", value, "createtime");
            return (Criteria) this;
        }

        public Criteria andCreatetimeIn(List<String> values) {
            addCriterion("createtime in", values, "createtime");
            return (Criteria) this;
        }

        public Criteria andCreatetimeNotIn(List<String> values) {
            addCriterion("createtime not in", values, "createtime");
            return (Criteria) this;
        }

        public Criteria andCreatetimeBetween(String value1, String value2) {
            addCriterion("createtime between", value1, value2, "createtime");
            return (Criteria) this;
        }

        public Criteria andCreatetimeNotBetween(String value1, String value2) {
            addCriterion("createtime not between", value1, value2, "createtime");
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
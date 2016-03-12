package cn.com.yunqitong.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TVersionExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public TVersionExample() {
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

        public Criteria andBuildIsNull() {
            addCriterion("build is null");
            return (Criteria) this;
        }

        public Criteria andBuildIsNotNull() {
            addCriterion("build is not null");
            return (Criteria) this;
        }

        public Criteria andBuildEqualTo(String value) {
            addCriterion("build =", value, "build");
            return (Criteria) this;
        }

        public Criteria andBuildNotEqualTo(String value) {
            addCriterion("build <>", value, "build");
            return (Criteria) this;
        }

        public Criteria andBuildGreaterThan(String value) {
            addCriterion("build >", value, "build");
            return (Criteria) this;
        }

        public Criteria andBuildGreaterThanOrEqualTo(String value) {
            addCriterion("build >=", value, "build");
            return (Criteria) this;
        }

        public Criteria andBuildLessThan(String value) {
            addCriterion("build <", value, "build");
            return (Criteria) this;
        }

        public Criteria andBuildLessThanOrEqualTo(String value) {
            addCriterion("build <=", value, "build");
            return (Criteria) this;
        }

        public Criteria andBuildLike(String value) {
            addCriterion("build like", value, "build");
            return (Criteria) this;
        }

        public Criteria andBuildNotLike(String value) {
            addCriterion("build not like", value, "build");
            return (Criteria) this;
        }

        public Criteria andBuildIn(List<String> values) {
            addCriterion("build in", values, "build");
            return (Criteria) this;
        }

        public Criteria andBuildNotIn(List<String> values) {
            addCriterion("build not in", values, "build");
            return (Criteria) this;
        }

        public Criteria andBuildBetween(String value1, String value2) {
            addCriterion("build between", value1, value2, "build");
            return (Criteria) this;
        }

        public Criteria andBuildNotBetween(String value1, String value2) {
            addCriterion("build not between", value1, value2, "build");
            return (Criteria) this;
        }

        public Criteria andNameIsNull() {
            addCriterion("name is null");
            return (Criteria) this;
        }

        public Criteria andNameIsNotNull() {
            addCriterion("name is not null");
            return (Criteria) this;
        }

        public Criteria andNameEqualTo(String value) {
            addCriterion("name =", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotEqualTo(String value) {
            addCriterion("name <>", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameGreaterThan(String value) {
            addCriterion("name >", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameGreaterThanOrEqualTo(String value) {
            addCriterion("name >=", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameLessThan(String value) {
            addCriterion("name <", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameLessThanOrEqualTo(String value) {
            addCriterion("name <=", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameLike(String value) {
            addCriterion("name like", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotLike(String value) {
            addCriterion("name not like", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameIn(List<String> values) {
            addCriterion("name in", values, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotIn(List<String> values) {
            addCriterion("name not in", values, "name");
            return (Criteria) this;
        }

        public Criteria andNameBetween(String value1, String value2) {
            addCriterion("name between", value1, value2, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotBetween(String value1, String value2) {
            addCriterion("name not between", value1, value2, "name");
            return (Criteria) this;
        }

        public Criteria andFirewirenameIsNull() {
            addCriterion("firewirename is null");
            return (Criteria) this;
        }

        public Criteria andFirewirenameIsNotNull() {
            addCriterion("firewirename is not null");
            return (Criteria) this;
        }

        public Criteria andFirewirenameEqualTo(String value) {
            addCriterion("firewirename =", value, "firewirename");
            return (Criteria) this;
        }

        public Criteria andFirewirenameNotEqualTo(String value) {
            addCriterion("firewirename <>", value, "firewirename");
            return (Criteria) this;
        }

        public Criteria andFirewirenameGreaterThan(String value) {
            addCriterion("firewirename >", value, "firewirename");
            return (Criteria) this;
        }

        public Criteria andFirewirenameGreaterThanOrEqualTo(String value) {
            addCriterion("firewirename >=", value, "firewirename");
            return (Criteria) this;
        }

        public Criteria andFirewirenameLessThan(String value) {
            addCriterion("firewirename <", value, "firewirename");
            return (Criteria) this;
        }

        public Criteria andFirewirenameLessThanOrEqualTo(String value) {
            addCriterion("firewirename <=", value, "firewirename");
            return (Criteria) this;
        }

        public Criteria andFirewirenameLike(String value) {
            addCriterion("firewirename like", value, "firewirename");
            return (Criteria) this;
        }

        public Criteria andFirewirenameNotLike(String value) {
            addCriterion("firewirename not like", value, "firewirename");
            return (Criteria) this;
        }

        public Criteria andFirewirenameIn(List<String> values) {
            addCriterion("firewirename in", values, "firewirename");
            return (Criteria) this;
        }

        public Criteria andFirewirenameNotIn(List<String> values) {
            addCriterion("firewirename not in", values, "firewirename");
            return (Criteria) this;
        }

        public Criteria andFirewirenameBetween(String value1, String value2) {
            addCriterion("firewirename between", value1, value2, "firewirename");
            return (Criteria) this;
        }

        public Criteria andFirewirenameNotBetween(String value1, String value2) {
            addCriterion("firewirename not between", value1, value2, "firewirename");
            return (Criteria) this;
        }

        public Criteria andApppathIsNull() {
            addCriterion("apppath is null");
            return (Criteria) this;
        }

        public Criteria andApppathIsNotNull() {
            addCriterion("apppath is not null");
            return (Criteria) this;
        }

        public Criteria andApppathEqualTo(String value) {
            addCriterion("apppath =", value, "apppath");
            return (Criteria) this;
        }

        public Criteria andApppathNotEqualTo(String value) {
            addCriterion("apppath <>", value, "apppath");
            return (Criteria) this;
        }

        public Criteria andApppathGreaterThan(String value) {
            addCriterion("apppath >", value, "apppath");
            return (Criteria) this;
        }

        public Criteria andApppathGreaterThanOrEqualTo(String value) {
            addCriterion("apppath >=", value, "apppath");
            return (Criteria) this;
        }

        public Criteria andApppathLessThan(String value) {
            addCriterion("apppath <", value, "apppath");
            return (Criteria) this;
        }

        public Criteria andApppathLessThanOrEqualTo(String value) {
            addCriterion("apppath <=", value, "apppath");
            return (Criteria) this;
        }

        public Criteria andApppathLike(String value) {
            addCriterion("apppath like", value, "apppath");
            return (Criteria) this;
        }

        public Criteria andApppathNotLike(String value) {
            addCriterion("apppath not like", value, "apppath");
            return (Criteria) this;
        }

        public Criteria andApppathIn(List<String> values) {
            addCriterion("apppath in", values, "apppath");
            return (Criteria) this;
        }

        public Criteria andApppathNotIn(List<String> values) {
            addCriterion("apppath not in", values, "apppath");
            return (Criteria) this;
        }

        public Criteria andApppathBetween(String value1, String value2) {
            addCriterion("apppath between", value1, value2, "apppath");
            return (Criteria) this;
        }

        public Criteria andApppathNotBetween(String value1, String value2) {
            addCriterion("apppath not between", value1, value2, "apppath");
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

        public Criteria andCreatetimeEqualTo(Date value) {
            addCriterion("createtime =", value, "createtime");
            return (Criteria) this;
        }

        public Criteria andCreatetimeNotEqualTo(Date value) {
            addCriterion("createtime <>", value, "createtime");
            return (Criteria) this;
        }

        public Criteria andCreatetimeGreaterThan(Date value) {
            addCriterion("createtime >", value, "createtime");
            return (Criteria) this;
        }

        public Criteria andCreatetimeGreaterThanOrEqualTo(Date value) {
            addCriterion("createtime >=", value, "createtime");
            return (Criteria) this;
        }

        public Criteria andCreatetimeLessThan(Date value) {
            addCriterion("createtime <", value, "createtime");
            return (Criteria) this;
        }

        public Criteria andCreatetimeLessThanOrEqualTo(Date value) {
            addCriterion("createtime <=", value, "createtime");
            return (Criteria) this;
        }

        public Criteria andCreatetimeIn(List<Date> values) {
            addCriterion("createtime in", values, "createtime");
            return (Criteria) this;
        }

        public Criteria andCreatetimeNotIn(List<Date> values) {
            addCriterion("createtime not in", values, "createtime");
            return (Criteria) this;
        }

        public Criteria andCreatetimeBetween(Date value1, Date value2) {
            addCriterion("createtime between", value1, value2, "createtime");
            return (Criteria) this;
        }

        public Criteria andCreatetimeNotBetween(Date value1, Date value2) {
            addCriterion("createtime not between", value1, value2, "createtime");
            return (Criteria) this;
        }

        public Criteria andFirewiresizeIsNull() {
            addCriterion("firewiresize is null");
            return (Criteria) this;
        }

        public Criteria andFirewiresizeIsNotNull() {
            addCriterion("firewiresize is not null");
            return (Criteria) this;
        }

        public Criteria andFirewiresizeEqualTo(String value) {
            addCriterion("firewiresize =", value, "firewiresize");
            return (Criteria) this;
        }

        public Criteria andFirewiresizeNotEqualTo(String value) {
            addCriterion("firewiresize <>", value, "firewiresize");
            return (Criteria) this;
        }

        public Criteria andFirewiresizeGreaterThan(String value) {
            addCriterion("firewiresize >", value, "firewiresize");
            return (Criteria) this;
        }

        public Criteria andFirewiresizeGreaterThanOrEqualTo(String value) {
            addCriterion("firewiresize >=", value, "firewiresize");
            return (Criteria) this;
        }

        public Criteria andFirewiresizeLessThan(String value) {
            addCriterion("firewiresize <", value, "firewiresize");
            return (Criteria) this;
        }

        public Criteria andFirewiresizeLessThanOrEqualTo(String value) {
            addCriterion("firewiresize <=", value, "firewiresize");
            return (Criteria) this;
        }

        public Criteria andFirewiresizeLike(String value) {
            addCriterion("firewiresize like", value, "firewiresize");
            return (Criteria) this;
        }

        public Criteria andFirewiresizeNotLike(String value) {
            addCriterion("firewiresize not like", value, "firewiresize");
            return (Criteria) this;
        }

        public Criteria andFirewiresizeIn(List<String> values) {
            addCriterion("firewiresize in", values, "firewiresize");
            return (Criteria) this;
        }

        public Criteria andFirewiresizeNotIn(List<String> values) {
            addCriterion("firewiresize not in", values, "firewiresize");
            return (Criteria) this;
        }

        public Criteria andFirewiresizeBetween(String value1, String value2) {
            addCriterion("firewiresize between", value1, value2, "firewiresize");
            return (Criteria) this;
        }

        public Criteria andFirewiresizeNotBetween(String value1, String value2) {
            addCriterion("firewiresize not between", value1, value2, "firewiresize");
            return (Criteria) this;
        }

        public Criteria andRemarkIsNull() {
            addCriterion("remark is null");
            return (Criteria) this;
        }

        public Criteria andRemarkIsNotNull() {
            addCriterion("remark is not null");
            return (Criteria) this;
        }

        public Criteria andRemarkEqualTo(String value) {
            addCriterion("remark =", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkNotEqualTo(String value) {
            addCriterion("remark <>", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkGreaterThan(String value) {
            addCriterion("remark >", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkGreaterThanOrEqualTo(String value) {
            addCriterion("remark >=", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkLessThan(String value) {
            addCriterion("remark <", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkLessThanOrEqualTo(String value) {
            addCriterion("remark <=", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkLike(String value) {
            addCriterion("remark like", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkNotLike(String value) {
            addCriterion("remark not like", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkIn(List<String> values) {
            addCriterion("remark in", values, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkNotIn(List<String> values) {
            addCriterion("remark not in", values, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkBetween(String value1, String value2) {
            addCriterion("remark between", value1, value2, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkNotBetween(String value1, String value2) {
            addCriterion("remark not between", value1, value2, "remark");
            return (Criteria) this;
        }

        public Criteria andPlatformIsNull() {
            addCriterion("platform is null");
            return (Criteria) this;
        }

        public Criteria andPlatformIsNotNull() {
            addCriterion("platform is not null");
            return (Criteria) this;
        }

        public Criteria andPlatformEqualTo(String value) {
            addCriterion("platform =", value, "platform");
            return (Criteria) this;
        }

        public Criteria andPlatformNotEqualTo(String value) {
            addCriterion("platform <>", value, "platform");
            return (Criteria) this;
        }

        public Criteria andPlatformGreaterThan(String value) {
            addCriterion("platform >", value, "platform");
            return (Criteria) this;
        }

        public Criteria andPlatformGreaterThanOrEqualTo(String value) {
            addCriterion("platform >=", value, "platform");
            return (Criteria) this;
        }

        public Criteria andPlatformLessThan(String value) {
            addCriterion("platform <", value, "platform");
            return (Criteria) this;
        }

        public Criteria andPlatformLessThanOrEqualTo(String value) {
            addCriterion("platform <=", value, "platform");
            return (Criteria) this;
        }

        public Criteria andPlatformLike(String value) {
            addCriterion("platform like", value, "platform");
            return (Criteria) this;
        }

        public Criteria andPlatformNotLike(String value) {
            addCriterion("platform not like", value, "platform");
            return (Criteria) this;
        }

        public Criteria andPlatformIn(List<String> values) {
            addCriterion("platform in", values, "platform");
            return (Criteria) this;
        }

        public Criteria andPlatformNotIn(List<String> values) {
            addCriterion("platform not in", values, "platform");
            return (Criteria) this;
        }

        public Criteria andPlatformBetween(String value1, String value2) {
            addCriterion("platform between", value1, value2, "platform");
            return (Criteria) this;
        }

        public Criteria andPlatformNotBetween(String value1, String value2) {
            addCriterion("platform not between", value1, value2, "platform");
            return (Criteria) this;
        }

        public Criteria andFilemd5IsNull() {
            addCriterion("filemd5 is null");
            return (Criteria) this;
        }

        public Criteria andFilemd5IsNotNull() {
            addCriterion("filemd5 is not null");
            return (Criteria) this;
        }

        public Criteria andFilemd5EqualTo(String value) {
            addCriterion("filemd5 =", value, "filemd5");
            return (Criteria) this;
        }

        public Criteria andFilemd5NotEqualTo(String value) {
            addCriterion("filemd5 <>", value, "filemd5");
            return (Criteria) this;
        }

        public Criteria andFilemd5GreaterThan(String value) {
            addCriterion("filemd5 >", value, "filemd5");
            return (Criteria) this;
        }

        public Criteria andFilemd5GreaterThanOrEqualTo(String value) {
            addCriterion("filemd5 >=", value, "filemd5");
            return (Criteria) this;
        }

        public Criteria andFilemd5LessThan(String value) {
            addCriterion("filemd5 <", value, "filemd5");
            return (Criteria) this;
        }

        public Criteria andFilemd5LessThanOrEqualTo(String value) {
            addCriterion("filemd5 <=", value, "filemd5");
            return (Criteria) this;
        }

        public Criteria andFilemd5Like(String value) {
            addCriterion("filemd5 like", value, "filemd5");
            return (Criteria) this;
        }

        public Criteria andFilemd5NotLike(String value) {
            addCriterion("filemd5 not like", value, "filemd5");
            return (Criteria) this;
        }

        public Criteria andFilemd5In(List<String> values) {
            addCriterion("filemd5 in", values, "filemd5");
            return (Criteria) this;
        }

        public Criteria andFilemd5NotIn(List<String> values) {
            addCriterion("filemd5 not in", values, "filemd5");
            return (Criteria) this;
        }

        public Criteria andFilemd5Between(String value1, String value2) {
            addCriterion("filemd5 between", value1, value2, "filemd5");
            return (Criteria) this;
        }

        public Criteria andFilemd5NotBetween(String value1, String value2) {
            addCriterion("filemd5 not between", value1, value2, "filemd5");
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
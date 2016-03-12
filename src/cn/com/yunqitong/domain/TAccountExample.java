package cn.com.yunqitong.domain;

import java.util.ArrayList;
import java.util.List;

public class TAccountExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public TAccountExample() {
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

        public Criteria andAccountidIsNull() {
            addCriterion("accountid is null");
            return (Criteria) this;
        }

        public Criteria andAccountidIsNotNull() {
            addCriterion("accountid is not null");
            return (Criteria) this;
        }

        public Criteria andAccountidEqualTo(String value) {
            addCriterion("accountid =", value, "accountid");
            return (Criteria) this;
        }

        public Criteria andAccountidNotEqualTo(String value) {
            addCriterion("accountid <>", value, "accountid");
            return (Criteria) this;
        }

        public Criteria andAccountidGreaterThan(String value) {
            addCriterion("accountid >", value, "accountid");
            return (Criteria) this;
        }

        public Criteria andAccountidGreaterThanOrEqualTo(String value) {
            addCriterion("accountid >=", value, "accountid");
            return (Criteria) this;
        }

        public Criteria andAccountidLessThan(String value) {
            addCriterion("accountid <", value, "accountid");
            return (Criteria) this;
        }

        public Criteria andAccountidLessThanOrEqualTo(String value) {
            addCriterion("accountid <=", value, "accountid");
            return (Criteria) this;
        }

        public Criteria andAccountidLike(String value) {
            addCriterion("accountid like", value, "accountid");
            return (Criteria) this;
        }

        public Criteria andAccountidNotLike(String value) {
            addCriterion("accountid not like", value, "accountid");
            return (Criteria) this;
        }

        public Criteria andAccountidIn(List<String> values) {
            addCriterion("accountid in", values, "accountid");
            return (Criteria) this;
        }

        public Criteria andAccountidNotIn(List<String> values) {
            addCriterion("accountid not in", values, "accountid");
            return (Criteria) this;
        }

        public Criteria andAccountidBetween(String value1, String value2) {
            addCriterion("accountid between", value1, value2, "accountid");
            return (Criteria) this;
        }

        public Criteria andAccountidNotBetween(String value1, String value2) {
            addCriterion("accountid not between", value1, value2, "accountid");
            return (Criteria) this;
        }

        public Criteria andNicknameIsNull() {
            addCriterion("nickname is null");
            return (Criteria) this;
        }

        public Criteria andNicknameIsNotNull() {
            addCriterion("nickname is not null");
            return (Criteria) this;
        }

        public Criteria andNicknameEqualTo(String value) {
            addCriterion("nickname =", value, "nickname");
            return (Criteria) this;
        }

        public Criteria andNicknameNotEqualTo(String value) {
            addCriterion("nickname <>", value, "nickname");
            return (Criteria) this;
        }

        public Criteria andNicknameGreaterThan(String value) {
            addCriterion("nickname >", value, "nickname");
            return (Criteria) this;
        }

        public Criteria andNicknameGreaterThanOrEqualTo(String value) {
            addCriterion("nickname >=", value, "nickname");
            return (Criteria) this;
        }

        public Criteria andNicknameLessThan(String value) {
            addCriterion("nickname <", value, "nickname");
            return (Criteria) this;
        }

        public Criteria andNicknameLessThanOrEqualTo(String value) {
            addCriterion("nickname <=", value, "nickname");
            return (Criteria) this;
        }

        public Criteria andNicknameLike(String value) {
            addCriterion("nickname like", value, "nickname");
            return (Criteria) this;
        }

        public Criteria andNicknameNotLike(String value) {
            addCriterion("nickname not like", value, "nickname");
            return (Criteria) this;
        }

        public Criteria andNicknameIn(List<String> values) {
            addCriterion("nickname in", values, "nickname");
            return (Criteria) this;
        }

        public Criteria andNicknameNotIn(List<String> values) {
            addCriterion("nickname not in", values, "nickname");
            return (Criteria) this;
        }

        public Criteria andNicknameBetween(String value1, String value2) {
            addCriterion("nickname between", value1, value2, "nickname");
            return (Criteria) this;
        }

        public Criteria andNicknameNotBetween(String value1, String value2) {
            addCriterion("nickname not between", value1, value2, "nickname");
            return (Criteria) this;
        }

        public Criteria andAccountIsNull() {
            addCriterion("account is null");
            return (Criteria) this;
        }

        public Criteria andAccountIsNotNull() {
            addCriterion("account is not null");
            return (Criteria) this;
        }

        public Criteria andAccountEqualTo(String value) {
            addCriterion("account =", value, "account");
            return (Criteria) this;
        }

        public Criteria andAccountNotEqualTo(String value) {
            addCriterion("account <>", value, "account");
            return (Criteria) this;
        }

        public Criteria andAccountGreaterThan(String value) {
            addCriterion("account >", value, "account");
            return (Criteria) this;
        }

        public Criteria andAccountGreaterThanOrEqualTo(String value) {
            addCriterion("account >=", value, "account");
            return (Criteria) this;
        }

        public Criteria andAccountLessThan(String value) {
            addCriterion("account <", value, "account");
            return (Criteria) this;
        }

        public Criteria andAccountLessThanOrEqualTo(String value) {
            addCriterion("account <=", value, "account");
            return (Criteria) this;
        }

        public Criteria andAccountLike(String value) {
            addCriterion("account like", value, "account");
            return (Criteria) this;
        }

        public Criteria andAccountNotLike(String value) {
            addCriterion("account not like", value, "account");
            return (Criteria) this;
        }

        public Criteria andAccountIn(List<String> values) {
            addCriterion("account in", values, "account");
            return (Criteria) this;
        }

        public Criteria andAccountNotIn(List<String> values) {
            addCriterion("account not in", values, "account");
            return (Criteria) this;
        }

        public Criteria andAccountBetween(String value1, String value2) {
            addCriterion("account between", value1, value2, "account");
            return (Criteria) this;
        }

        public Criteria andAccountNotBetween(String value1, String value2) {
            addCriterion("account not between", value1, value2, "account");
            return (Criteria) this;
        }

        public Criteria andDeletetagIsNull() {
            addCriterion("deletetag is null");
            return (Criteria) this;
        }

        public Criteria andDeletetagIsNotNull() {
            addCriterion("deletetag is not null");
            return (Criteria) this;
        }

        public Criteria andDeletetagEqualTo(String value) {
            addCriterion("deletetag =", value, "deletetag");
            return (Criteria) this;
        }

        public Criteria andDeletetagNotEqualTo(String value) {
            addCriterion("deletetag <>", value, "deletetag");
            return (Criteria) this;
        }

        public Criteria andDeletetagGreaterThan(String value) {
            addCriterion("deletetag >", value, "deletetag");
            return (Criteria) this;
        }

        public Criteria andDeletetagGreaterThanOrEqualTo(String value) {
            addCriterion("deletetag >=", value, "deletetag");
            return (Criteria) this;
        }

        public Criteria andDeletetagLessThan(String value) {
            addCriterion("deletetag <", value, "deletetag");
            return (Criteria) this;
        }

        public Criteria andDeletetagLessThanOrEqualTo(String value) {
            addCriterion("deletetag <=", value, "deletetag");
            return (Criteria) this;
        }

        public Criteria andDeletetagLike(String value) {
            addCriterion("deletetag like", value, "deletetag");
            return (Criteria) this;
        }

        public Criteria andDeletetagNotLike(String value) {
            addCriterion("deletetag not like", value, "deletetag");
            return (Criteria) this;
        }

        public Criteria andDeletetagIn(List<String> values) {
            addCriterion("deletetag in", values, "deletetag");
            return (Criteria) this;
        }

        public Criteria andDeletetagNotIn(List<String> values) {
            addCriterion("deletetag not in", values, "deletetag");
            return (Criteria) this;
        }

        public Criteria andDeletetagBetween(String value1, String value2) {
            addCriterion("deletetag between", value1, value2, "deletetag");
            return (Criteria) this;
        }

        public Criteria andDeletetagNotBetween(String value1, String value2) {
            addCriterion("deletetag not between", value1, value2, "deletetag");
            return (Criteria) this;
        }

        public Criteria andPicurlIsNull() {
            addCriterion("picurl is null");
            return (Criteria) this;
        }

        public Criteria andPicurlIsNotNull() {
            addCriterion("picurl is not null");
            return (Criteria) this;
        }

        public Criteria andPicurlEqualTo(String value) {
            addCriterion("picurl =", value, "picurl");
            return (Criteria) this;
        }

        public Criteria andPicurlNotEqualTo(String value) {
            addCriterion("picurl <>", value, "picurl");
            return (Criteria) this;
        }

        public Criteria andPicurlGreaterThan(String value) {
            addCriterion("picurl >", value, "picurl");
            return (Criteria) this;
        }

        public Criteria andPicurlGreaterThanOrEqualTo(String value) {
            addCriterion("picurl >=", value, "picurl");
            return (Criteria) this;
        }

        public Criteria andPicurlLessThan(String value) {
            addCriterion("picurl <", value, "picurl");
            return (Criteria) this;
        }

        public Criteria andPicurlLessThanOrEqualTo(String value) {
            addCriterion("picurl <=", value, "picurl");
            return (Criteria) this;
        }

        public Criteria andPicurlLike(String value) {
            addCriterion("picurl like", value, "picurl");
            return (Criteria) this;
        }

        public Criteria andPicurlNotLike(String value) {
            addCriterion("picurl not like", value, "picurl");
            return (Criteria) this;
        }

        public Criteria andPicurlIn(List<String> values) {
            addCriterion("picurl in", values, "picurl");
            return (Criteria) this;
        }

        public Criteria andPicurlNotIn(List<String> values) {
            addCriterion("picurl not in", values, "picurl");
            return (Criteria) this;
        }

        public Criteria andPicurlBetween(String value1, String value2) {
            addCriterion("picurl between", value1, value2, "picurl");
            return (Criteria) this;
        }

        public Criteria andPicurlNotBetween(String value1, String value2) {
            addCriterion("picurl not between", value1, value2, "picurl");
            return (Criteria) this;
        }

        public Criteria andPicuptimeIsNull() {
            addCriterion("picuptime is null");
            return (Criteria) this;
        }

        public Criteria andPicuptimeIsNotNull() {
            addCriterion("picuptime is not null");
            return (Criteria) this;
        }

        public Criteria andPicuptimeEqualTo(String value) {
            addCriterion("picuptime =", value, "picuptime");
            return (Criteria) this;
        }

        public Criteria andPicuptimeNotEqualTo(String value) {
            addCriterion("picuptime <>", value, "picuptime");
            return (Criteria) this;
        }

        public Criteria andPicuptimeGreaterThan(String value) {
            addCriterion("picuptime >", value, "picuptime");
            return (Criteria) this;
        }

        public Criteria andPicuptimeGreaterThanOrEqualTo(String value) {
            addCriterion("picuptime >=", value, "picuptime");
            return (Criteria) this;
        }

        public Criteria andPicuptimeLessThan(String value) {
            addCriterion("picuptime <", value, "picuptime");
            return (Criteria) this;
        }

        public Criteria andPicuptimeLessThanOrEqualTo(String value) {
            addCriterion("picuptime <=", value, "picuptime");
            return (Criteria) this;
        }

        public Criteria andPicuptimeLike(String value) {
            addCriterion("picuptime like", value, "picuptime");
            return (Criteria) this;
        }

        public Criteria andPicuptimeNotLike(String value) {
            addCriterion("picuptime not like", value, "picuptime");
            return (Criteria) this;
        }

        public Criteria andPicuptimeIn(List<String> values) {
            addCriterion("picuptime in", values, "picuptime");
            return (Criteria) this;
        }

        public Criteria andPicuptimeNotIn(List<String> values) {
            addCriterion("picuptime not in", values, "picuptime");
            return (Criteria) this;
        }

        public Criteria andPicuptimeBetween(String value1, String value2) {
            addCriterion("picuptime between", value1, value2, "picuptime");
            return (Criteria) this;
        }

        public Criteria andPicuptimeNotBetween(String value1, String value2) {
            addCriterion("picuptime not between", value1, value2, "picuptime");
            return (Criteria) this;
        }

        public Criteria andRingurlIsNull() {
            addCriterion("ringurl is null");
            return (Criteria) this;
        }

        public Criteria andRingurlIsNotNull() {
            addCriterion("ringurl is not null");
            return (Criteria) this;
        }

        public Criteria andRingurlEqualTo(String value) {
            addCriterion("ringurl =", value, "ringurl");
            return (Criteria) this;
        }

        public Criteria andRingurlNotEqualTo(String value) {
            addCriterion("ringurl <>", value, "ringurl");
            return (Criteria) this;
        }

        public Criteria andRingurlGreaterThan(String value) {
            addCriterion("ringurl >", value, "ringurl");
            return (Criteria) this;
        }

        public Criteria andRingurlGreaterThanOrEqualTo(String value) {
            addCriterion("ringurl >=", value, "ringurl");
            return (Criteria) this;
        }

        public Criteria andRingurlLessThan(String value) {
            addCriterion("ringurl <", value, "ringurl");
            return (Criteria) this;
        }

        public Criteria andRingurlLessThanOrEqualTo(String value) {
            addCriterion("ringurl <=", value, "ringurl");
            return (Criteria) this;
        }

        public Criteria andRingurlLike(String value) {
            addCriterion("ringurl like", value, "ringurl");
            return (Criteria) this;
        }

        public Criteria andRingurlNotLike(String value) {
            addCriterion("ringurl not like", value, "ringurl");
            return (Criteria) this;
        }

        public Criteria andRingurlIn(List<String> values) {
            addCriterion("ringurl in", values, "ringurl");
            return (Criteria) this;
        }

        public Criteria andRingurlNotIn(List<String> values) {
            addCriterion("ringurl not in", values, "ringurl");
            return (Criteria) this;
        }

        public Criteria andRingurlBetween(String value1, String value2) {
            addCriterion("ringurl between", value1, value2, "ringurl");
            return (Criteria) this;
        }

        public Criteria andRingurlNotBetween(String value1, String value2) {
            addCriterion("ringurl not between", value1, value2, "ringurl");
            return (Criteria) this;
        }

        public Criteria andRingnameIsNull() {
            addCriterion("ringname is null");
            return (Criteria) this;
        }

        public Criteria andRingnameIsNotNull() {
            addCriterion("ringname is not null");
            return (Criteria) this;
        }

        public Criteria andRingnameEqualTo(String value) {
            addCriterion("ringname =", value, "ringname");
            return (Criteria) this;
        }

        public Criteria andRingnameNotEqualTo(String value) {
            addCriterion("ringname <>", value, "ringname");
            return (Criteria) this;
        }

        public Criteria andRingnameGreaterThan(String value) {
            addCriterion("ringname >", value, "ringname");
            return (Criteria) this;
        }

        public Criteria andRingnameGreaterThanOrEqualTo(String value) {
            addCriterion("ringname >=", value, "ringname");
            return (Criteria) this;
        }

        public Criteria andRingnameLessThan(String value) {
            addCriterion("ringname <", value, "ringname");
            return (Criteria) this;
        }

        public Criteria andRingnameLessThanOrEqualTo(String value) {
            addCriterion("ringname <=", value, "ringname");
            return (Criteria) this;
        }

        public Criteria andRingnameLike(String value) {
            addCriterion("ringname like", value, "ringname");
            return (Criteria) this;
        }

        public Criteria andRingnameNotLike(String value) {
            addCriterion("ringname not like", value, "ringname");
            return (Criteria) this;
        }

        public Criteria andRingnameIn(List<String> values) {
            addCriterion("ringname in", values, "ringname");
            return (Criteria) this;
        }

        public Criteria andRingnameNotIn(List<String> values) {
            addCriterion("ringname not in", values, "ringname");
            return (Criteria) this;
        }

        public Criteria andRingnameBetween(String value1, String value2) {
            addCriterion("ringname between", value1, value2, "ringname");
            return (Criteria) this;
        }

        public Criteria andRingnameNotBetween(String value1, String value2) {
            addCriterion("ringname not between", value1, value2, "ringname");
            return (Criteria) this;
        }

        public Criteria andRingsizeIsNull() {
            addCriterion("ringsize is null");
            return (Criteria) this;
        }

        public Criteria andRingsizeIsNotNull() {
            addCriterion("ringsize is not null");
            return (Criteria) this;
        }

        public Criteria andRingsizeEqualTo(String value) {
            addCriterion("ringsize =", value, "ringsize");
            return (Criteria) this;
        }

        public Criteria andRingsizeNotEqualTo(String value) {
            addCriterion("ringsize <>", value, "ringsize");
            return (Criteria) this;
        }

        public Criteria andRingsizeGreaterThan(String value) {
            addCriterion("ringsize >", value, "ringsize");
            return (Criteria) this;
        }

        public Criteria andRingsizeGreaterThanOrEqualTo(String value) {
            addCriterion("ringsize >=", value, "ringsize");
            return (Criteria) this;
        }

        public Criteria andRingsizeLessThan(String value) {
            addCriterion("ringsize <", value, "ringsize");
            return (Criteria) this;
        }

        public Criteria andRingsizeLessThanOrEqualTo(String value) {
            addCriterion("ringsize <=", value, "ringsize");
            return (Criteria) this;
        }

        public Criteria andRingsizeLike(String value) {
            addCriterion("ringsize like", value, "ringsize");
            return (Criteria) this;
        }

        public Criteria andRingsizeNotLike(String value) {
            addCriterion("ringsize not like", value, "ringsize");
            return (Criteria) this;
        }

        public Criteria andRingsizeIn(List<String> values) {
            addCriterion("ringsize in", values, "ringsize");
            return (Criteria) this;
        }

        public Criteria andRingsizeNotIn(List<String> values) {
            addCriterion("ringsize not in", values, "ringsize");
            return (Criteria) this;
        }

        public Criteria andRingsizeBetween(String value1, String value2) {
            addCriterion("ringsize between", value1, value2, "ringsize");
            return (Criteria) this;
        }

        public Criteria andRingsizeNotBetween(String value1, String value2) {
            addCriterion("ringsize not between", value1, value2, "ringsize");
            return (Criteria) this;
        }

        public Criteria andRinguptimeIsNull() {
            addCriterion("ringuptime is null");
            return (Criteria) this;
        }

        public Criteria andRinguptimeIsNotNull() {
            addCriterion("ringuptime is not null");
            return (Criteria) this;
        }

        public Criteria andRinguptimeEqualTo(String value) {
            addCriterion("ringuptime =", value, "ringuptime");
            return (Criteria) this;
        }

        public Criteria andRinguptimeNotEqualTo(String value) {
            addCriterion("ringuptime <>", value, "ringuptime");
            return (Criteria) this;
        }

        public Criteria andRinguptimeGreaterThan(String value) {
            addCriterion("ringuptime >", value, "ringuptime");
            return (Criteria) this;
        }

        public Criteria andRinguptimeGreaterThanOrEqualTo(String value) {
            addCriterion("ringuptime >=", value, "ringuptime");
            return (Criteria) this;
        }

        public Criteria andRinguptimeLessThan(String value) {
            addCriterion("ringuptime <", value, "ringuptime");
            return (Criteria) this;
        }

        public Criteria andRinguptimeLessThanOrEqualTo(String value) {
            addCriterion("ringuptime <=", value, "ringuptime");
            return (Criteria) this;
        }

        public Criteria andRinguptimeLike(String value) {
            addCriterion("ringuptime like", value, "ringuptime");
            return (Criteria) this;
        }

        public Criteria andRinguptimeNotLike(String value) {
            addCriterion("ringuptime not like", value, "ringuptime");
            return (Criteria) this;
        }

        public Criteria andRinguptimeIn(List<String> values) {
            addCriterion("ringuptime in", values, "ringuptime");
            return (Criteria) this;
        }

        public Criteria andRinguptimeNotIn(List<String> values) {
            addCriterion("ringuptime not in", values, "ringuptime");
            return (Criteria) this;
        }

        public Criteria andRinguptimeBetween(String value1, String value2) {
            addCriterion("ringuptime between", value1, value2, "ringuptime");
            return (Criteria) this;
        }

        public Criteria andRinguptimeNotBetween(String value1, String value2) {
            addCriterion("ringuptime not between", value1, value2, "ringuptime");
            return (Criteria) this;
        }

        public Criteria andEnableIsNull() {
            addCriterion("enable is null");
            return (Criteria) this;
        }

        public Criteria andEnableIsNotNull() {
            addCriterion("enable is not null");
            return (Criteria) this;
        }

        public Criteria andEnableEqualTo(String value) {
            addCriterion("enable =", value, "enable");
            return (Criteria) this;
        }

        public Criteria andEnableNotEqualTo(String value) {
            addCriterion("enable <>", value, "enable");
            return (Criteria) this;
        }

        public Criteria andEnableGreaterThan(String value) {
            addCriterion("enable >", value, "enable");
            return (Criteria) this;
        }

        public Criteria andEnableGreaterThanOrEqualTo(String value) {
            addCriterion("enable >=", value, "enable");
            return (Criteria) this;
        }

        public Criteria andEnableLessThan(String value) {
            addCriterion("enable <", value, "enable");
            return (Criteria) this;
        }

        public Criteria andEnableLessThanOrEqualTo(String value) {
            addCriterion("enable <=", value, "enable");
            return (Criteria) this;
        }

        public Criteria andEnableLike(String value) {
            addCriterion("enable like", value, "enable");
            return (Criteria) this;
        }

        public Criteria andEnableNotLike(String value) {
            addCriterion("enable not like", value, "enable");
            return (Criteria) this;
        }

        public Criteria andEnableIn(List<String> values) {
            addCriterion("enable in", values, "enable");
            return (Criteria) this;
        }

        public Criteria andEnableNotIn(List<String> values) {
            addCriterion("enable not in", values, "enable");
            return (Criteria) this;
        }

        public Criteria andEnableBetween(String value1, String value2) {
            addCriterion("enable between", value1, value2, "enable");
            return (Criteria) this;
        }

        public Criteria andEnableNotBetween(String value1, String value2) {
            addCriterion("enable not between", value1, value2, "enable");
            return (Criteria) this;
        }

        public Criteria andPlateformIsNull() {
            addCriterion("plateform is null");
            return (Criteria) this;
        }

        public Criteria andPlateformIsNotNull() {
            addCriterion("plateform is not null");
            return (Criteria) this;
        }

        public Criteria andPlateformEqualTo(String value) {
            addCriterion("plateform =", value, "plateform");
            return (Criteria) this;
        }

        public Criteria andPlateformNotEqualTo(String value) {
            addCriterion("plateform <>", value, "plateform");
            return (Criteria) this;
        }

        public Criteria andPlateformGreaterThan(String value) {
            addCriterion("plateform >", value, "plateform");
            return (Criteria) this;
        }

        public Criteria andPlateformGreaterThanOrEqualTo(String value) {
            addCriterion("plateform >=", value, "plateform");
            return (Criteria) this;
        }

        public Criteria andPlateformLessThan(String value) {
            addCriterion("plateform <", value, "plateform");
            return (Criteria) this;
        }

        public Criteria andPlateformLessThanOrEqualTo(String value) {
            addCriterion("plateform <=", value, "plateform");
            return (Criteria) this;
        }

        public Criteria andPlateformLike(String value) {
            addCriterion("plateform like", value, "plateform");
            return (Criteria) this;
        }

        public Criteria andPlateformNotLike(String value) {
            addCriterion("plateform not like", value, "plateform");
            return (Criteria) this;
        }

        public Criteria andPlateformIn(List<String> values) {
            addCriterion("plateform in", values, "plateform");
            return (Criteria) this;
        }

        public Criteria andPlateformNotIn(List<String> values) {
            addCriterion("plateform not in", values, "plateform");
            return (Criteria) this;
        }

        public Criteria andPlateformBetween(String value1, String value2) {
            addCriterion("plateform between", value1, value2, "plateform");
            return (Criteria) this;
        }

        public Criteria andPlateformNotBetween(String value1, String value2) {
            addCriterion("plateform not between", value1, value2, "plateform");
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

        public Criteria andMaxnumIsNull() {
            addCriterion("maxnum is null");
            return (Criteria) this;
        }

        public Criteria andMaxnumIsNotNull() {
            addCriterion("maxnum is not null");
            return (Criteria) this;
        }

        public Criteria andMaxnumEqualTo(Long value) {
            addCriterion("maxnum =", value, "maxnum");
            return (Criteria) this;
        }

        public Criteria andMaxnumNotEqualTo(Long value) {
            addCriterion("maxnum <>", value, "maxnum");
            return (Criteria) this;
        }

        public Criteria andMaxnumGreaterThan(Long value) {
            addCriterion("maxnum >", value, "maxnum");
            return (Criteria) this;
        }

        public Criteria andMaxnumGreaterThanOrEqualTo(Long value) {
            addCriterion("maxnum >=", value, "maxnum");
            return (Criteria) this;
        }

        public Criteria andMaxnumLessThan(Long value) {
            addCriterion("maxnum <", value, "maxnum");
            return (Criteria) this;
        }

        public Criteria andMaxnumLessThanOrEqualTo(Long value) {
            addCriterion("maxnum <=", value, "maxnum");
            return (Criteria) this;
        }

        public Criteria andMaxnumIn(List<Long> values) {
            addCriterion("maxnum in", values, "maxnum");
            return (Criteria) this;
        }

        public Criteria andMaxnumNotIn(List<Long> values) {
            addCriterion("maxnum not in", values, "maxnum");
            return (Criteria) this;
        }

        public Criteria andMaxnumBetween(Long value1, Long value2) {
            addCriterion("maxnum between", value1, value2, "maxnum");
            return (Criteria) this;
        }

        public Criteria andMaxnumNotBetween(Long value1, Long value2) {
            addCriterion("maxnum not between", value1, value2, "maxnum");
            return (Criteria) this;
        }

        public Criteria andAdpidIsNull() {
            addCriterion("adpid is null");
            return (Criteria) this;
        }

        public Criteria andAdpidIsNotNull() {
            addCriterion("adpid is not null");
            return (Criteria) this;
        }

        public Criteria andAdpidEqualTo(String value) {
            addCriterion("adpid =", value, "adpid");
            return (Criteria) this;
        }

        public Criteria andAdpidNotEqualTo(String value) {
            addCriterion("adpid <>", value, "adpid");
            return (Criteria) this;
        }

        public Criteria andAdpidGreaterThan(String value) {
            addCriterion("adpid >", value, "adpid");
            return (Criteria) this;
        }

        public Criteria andAdpidGreaterThanOrEqualTo(String value) {
            addCriterion("adpid >=", value, "adpid");
            return (Criteria) this;
        }

        public Criteria andAdpidLessThan(String value) {
            addCriterion("adpid <", value, "adpid");
            return (Criteria) this;
        }

        public Criteria andAdpidLessThanOrEqualTo(String value) {
            addCriterion("adpid <=", value, "adpid");
            return (Criteria) this;
        }

        public Criteria andAdpidLike(String value) {
            addCriterion("adpid like", value, "adpid");
            return (Criteria) this;
        }

        public Criteria andAdpidNotLike(String value) {
            addCriterion("adpid not like", value, "adpid");
            return (Criteria) this;
        }

        public Criteria andAdpidIn(List<String> values) {
            addCriterion("adpid in", values, "adpid");
            return (Criteria) this;
        }

        public Criteria andAdpidNotIn(List<String> values) {
            addCriterion("adpid not in", values, "adpid");
            return (Criteria) this;
        }

        public Criteria andAdpidBetween(String value1, String value2) {
            addCriterion("adpid between", value1, value2, "adpid");
            return (Criteria) this;
        }

        public Criteria andAdpidNotBetween(String value1, String value2) {
            addCriterion("adpid not between", value1, value2, "adpid");
            return (Criteria) this;
        }

        public Criteria andTokenIsNull() {
            addCriterion("token is null");
            return (Criteria) this;
        }

        public Criteria andTokenIsNotNull() {
            addCriterion("token is not null");
            return (Criteria) this;
        }

        public Criteria andTokenEqualTo(String value) {
            addCriterion("token =", value, "token");
            return (Criteria) this;
        }

        public Criteria andTokenNotEqualTo(String value) {
            addCriterion("token <>", value, "token");
            return (Criteria) this;
        }

        public Criteria andTokenGreaterThan(String value) {
            addCriterion("token >", value, "token");
            return (Criteria) this;
        }

        public Criteria andTokenGreaterThanOrEqualTo(String value) {
            addCriterion("token >=", value, "token");
            return (Criteria) this;
        }

        public Criteria andTokenLessThan(String value) {
            addCriterion("token <", value, "token");
            return (Criteria) this;
        }

        public Criteria andTokenLessThanOrEqualTo(String value) {
            addCriterion("token <=", value, "token");
            return (Criteria) this;
        }

        public Criteria andTokenLike(String value) {
            addCriterion("token like", value, "token");
            return (Criteria) this;
        }

        public Criteria andTokenNotLike(String value) {
            addCriterion("token not like", value, "token");
            return (Criteria) this;
        }

        public Criteria andTokenIn(List<String> values) {
            addCriterion("token in", values, "token");
            return (Criteria) this;
        }

        public Criteria andTokenNotIn(List<String> values) {
            addCriterion("token not in", values, "token");
            return (Criteria) this;
        }

        public Criteria andTokenBetween(String value1, String value2) {
            addCriterion("token between", value1, value2, "token");
            return (Criteria) this;
        }

        public Criteria andTokenNotBetween(String value1, String value2) {
            addCriterion("token not between", value1, value2, "token");
            return (Criteria) this;
        }

        public Criteria andDeviceinfoIsNull() {
            addCriterion("deviceinfo is null");
            return (Criteria) this;
        }

        public Criteria andDeviceinfoIsNotNull() {
            addCriterion("deviceinfo is not null");
            return (Criteria) this;
        }

        public Criteria andDeviceinfoEqualTo(String value) {
            addCriterion("deviceinfo =", value, "deviceinfo");
            return (Criteria) this;
        }

        public Criteria andDeviceinfoNotEqualTo(String value) {
            addCriterion("deviceinfo <>", value, "deviceinfo");
            return (Criteria) this;
        }

        public Criteria andDeviceinfoGreaterThan(String value) {
            addCriterion("deviceinfo >", value, "deviceinfo");
            return (Criteria) this;
        }

        public Criteria andDeviceinfoGreaterThanOrEqualTo(String value) {
            addCriterion("deviceinfo >=", value, "deviceinfo");
            return (Criteria) this;
        }

        public Criteria andDeviceinfoLessThan(String value) {
            addCriterion("deviceinfo <", value, "deviceinfo");
            return (Criteria) this;
        }

        public Criteria andDeviceinfoLessThanOrEqualTo(String value) {
            addCriterion("deviceinfo <=", value, "deviceinfo");
            return (Criteria) this;
        }

        public Criteria andDeviceinfoLike(String value) {
            addCriterion("deviceinfo like", value, "deviceinfo");
            return (Criteria) this;
        }

        public Criteria andDeviceinfoNotLike(String value) {
            addCriterion("deviceinfo not like", value, "deviceinfo");
            return (Criteria) this;
        }

        public Criteria andDeviceinfoIn(List<String> values) {
            addCriterion("deviceinfo in", values, "deviceinfo");
            return (Criteria) this;
        }

        public Criteria andDeviceinfoNotIn(List<String> values) {
            addCriterion("deviceinfo not in", values, "deviceinfo");
            return (Criteria) this;
        }

        public Criteria andDeviceinfoBetween(String value1, String value2) {
            addCriterion("deviceinfo between", value1, value2, "deviceinfo");
            return (Criteria) this;
        }

        public Criteria andDeviceinfoNotBetween(String value1, String value2) {
            addCriterion("deviceinfo not between", value1, value2, "deviceinfo");
            return (Criteria) this;
        }

        public Criteria andPushidIsNull() {
            addCriterion("pushid is null");
            return (Criteria) this;
        }

        public Criteria andPushidIsNotNull() {
            addCriterion("pushid is not null");
            return (Criteria) this;
        }

        public Criteria andPushidEqualTo(String value) {
            addCriterion("pushid =", value, "pushid");
            return (Criteria) this;
        }

        public Criteria andPushidNotEqualTo(String value) {
            addCriterion("pushid <>", value, "pushid");
            return (Criteria) this;
        }

        public Criteria andPushidGreaterThan(String value) {
            addCriterion("pushid >", value, "pushid");
            return (Criteria) this;
        }

        public Criteria andPushidGreaterThanOrEqualTo(String value) {
            addCriterion("pushid >=", value, "pushid");
            return (Criteria) this;
        }

        public Criteria andPushidLessThan(String value) {
            addCriterion("pushid <", value, "pushid");
            return (Criteria) this;
        }

        public Criteria andPushidLessThanOrEqualTo(String value) {
            addCriterion("pushid <=", value, "pushid");
            return (Criteria) this;
        }

        public Criteria andPushidLike(String value) {
            addCriterion("pushid like", value, "pushid");
            return (Criteria) this;
        }

        public Criteria andPushidNotLike(String value) {
            addCriterion("pushid not like", value, "pushid");
            return (Criteria) this;
        }

        public Criteria andPushidIn(List<String> values) {
            addCriterion("pushid in", values, "pushid");
            return (Criteria) this;
        }

        public Criteria andPushidNotIn(List<String> values) {
            addCriterion("pushid not in", values, "pushid");
            return (Criteria) this;
        }

        public Criteria andPushidBetween(String value1, String value2) {
            addCriterion("pushid between", value1, value2, "pushid");
            return (Criteria) this;
        }

        public Criteria andPushidNotBetween(String value1, String value2) {
            addCriterion("pushid not between", value1, value2, "pushid");
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

        public Criteria andMeetingnumIsNull() {
            addCriterion("meetingNum is null");
            return (Criteria) this;
        }

        public Criteria andMeetingnumIsNotNull() {
            addCriterion("meetingNum is not null");
            return (Criteria) this;
        }

        public Criteria andMeetingnumEqualTo(String value) {
            addCriterion("meetingNum =", value, "meetingnum");
            return (Criteria) this;
        }

        public Criteria andMeetingnumNotEqualTo(String value) {
            addCriterion("meetingNum <>", value, "meetingnum");
            return (Criteria) this;
        }

        public Criteria andMeetingnumGreaterThan(String value) {
            addCriterion("meetingNum >", value, "meetingnum");
            return (Criteria) this;
        }

        public Criteria andMeetingnumGreaterThanOrEqualTo(String value) {
            addCriterion("meetingNum >=", value, "meetingnum");
            return (Criteria) this;
        }

        public Criteria andMeetingnumLessThan(String value) {
            addCriterion("meetingNum <", value, "meetingnum");
            return (Criteria) this;
        }

        public Criteria andMeetingnumLessThanOrEqualTo(String value) {
            addCriterion("meetingNum <=", value, "meetingnum");
            return (Criteria) this;
        }

        public Criteria andMeetingnumLike(String value) {
            addCriterion("meetingNum like", value, "meetingnum");
            return (Criteria) this;
        }

        public Criteria andMeetingnumNotLike(String value) {
            addCriterion("meetingNum not like", value, "meetingnum");
            return (Criteria) this;
        }

        public Criteria andMeetingnumIn(List<String> values) {
            addCriterion("meetingNum in", values, "meetingnum");
            return (Criteria) this;
        }

        public Criteria andMeetingnumNotIn(List<String> values) {
            addCriterion("meetingNum not in", values, "meetingnum");
            return (Criteria) this;
        }

        public Criteria andMeetingnumBetween(String value1, String value2) {
            addCriterion("meetingNum between", value1, value2, "meetingnum");
            return (Criteria) this;
        }

        public Criteria andMeetingnumNotBetween(String value1, String value2) {
            addCriterion("meetingNum not between", value1, value2, "meetingnum");
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
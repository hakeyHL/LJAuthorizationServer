package cn.com.yunqitong.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TShareExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public TShareExample() {
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

        public Criteria andShareidIsNull() {
            addCriterion("shareid is null");
            return (Criteria) this;
        }

        public Criteria andShareidIsNotNull() {
            addCriterion("shareid is not null");
            return (Criteria) this;
        }

        public Criteria andShareidEqualTo(String value) {
            addCriterion("shareid =", value, "shareid");
            return (Criteria) this;
        }

        public Criteria andShareidNotEqualTo(String value) {
            addCriterion("shareid <>", value, "shareid");
            return (Criteria) this;
        }

        public Criteria andShareidGreaterThan(String value) {
            addCriterion("shareid >", value, "shareid");
            return (Criteria) this;
        }

        public Criteria andShareidGreaterThanOrEqualTo(String value) {
            addCriterion("shareid >=", value, "shareid");
            return (Criteria) this;
        }

        public Criteria andShareidLessThan(String value) {
            addCriterion("shareid <", value, "shareid");
            return (Criteria) this;
        }

        public Criteria andShareidLessThanOrEqualTo(String value) {
            addCriterion("shareid <=", value, "shareid");
            return (Criteria) this;
        }

        public Criteria andShareidLike(String value) {
            addCriterion("shareid like", value, "shareid");
            return (Criteria) this;
        }

        public Criteria andShareidNotLike(String value) {
            addCriterion("shareid not like", value, "shareid");
            return (Criteria) this;
        }

        public Criteria andShareidIn(List<String> values) {
            addCriterion("shareid in", values, "shareid");
            return (Criteria) this;
        }

        public Criteria andShareidNotIn(List<String> values) {
            addCriterion("shareid not in", values, "shareid");
            return (Criteria) this;
        }

        public Criteria andShareidBetween(String value1, String value2) {
            addCriterion("shareid between", value1, value2, "shareid");
            return (Criteria) this;
        }

        public Criteria andShareidNotBetween(String value1, String value2) {
            addCriterion("shareid not between", value1, value2, "shareid");
            return (Criteria) this;
        }

        public Criteria andFriendscontentIsNull() {
            addCriterion("friendscontent is null");
            return (Criteria) this;
        }

        public Criteria andFriendscontentIsNotNull() {
            addCriterion("friendscontent is not null");
            return (Criteria) this;
        }

        public Criteria andFriendscontentEqualTo(String value) {
            addCriterion("friendscontent =", value, "friendscontent");
            return (Criteria) this;
        }

        public Criteria andFriendscontentNotEqualTo(String value) {
            addCriterion("friendscontent <>", value, "friendscontent");
            return (Criteria) this;
        }

        public Criteria andFriendscontentGreaterThan(String value) {
            addCriterion("friendscontent >", value, "friendscontent");
            return (Criteria) this;
        }

        public Criteria andFriendscontentGreaterThanOrEqualTo(String value) {
            addCriterion("friendscontent >=", value, "friendscontent");
            return (Criteria) this;
        }

        public Criteria andFriendscontentLessThan(String value) {
            addCriterion("friendscontent <", value, "friendscontent");
            return (Criteria) this;
        }

        public Criteria andFriendscontentLessThanOrEqualTo(String value) {
            addCriterion("friendscontent <=", value, "friendscontent");
            return (Criteria) this;
        }

        public Criteria andFriendscontentLike(String value) {
            addCriterion("friendscontent like", value, "friendscontent");
            return (Criteria) this;
        }

        public Criteria andFriendscontentNotLike(String value) {
            addCriterion("friendscontent not like", value, "friendscontent");
            return (Criteria) this;
        }

        public Criteria andFriendscontentIn(List<String> values) {
            addCriterion("friendscontent in", values, "friendscontent");
            return (Criteria) this;
        }

        public Criteria andFriendscontentNotIn(List<String> values) {
            addCriterion("friendscontent not in", values, "friendscontent");
            return (Criteria) this;
        }

        public Criteria andFriendscontentBetween(String value1, String value2) {
            addCriterion("friendscontent between", value1, value2, "friendscontent");
            return (Criteria) this;
        }

        public Criteria andFriendscontentNotBetween(String value1, String value2) {
            addCriterion("friendscontent not between", value1, value2, "friendscontent");
            return (Criteria) this;
        }

        public Criteria andFriendspicsIsNull() {
            addCriterion("friendspics is null");
            return (Criteria) this;
        }

        public Criteria andFriendspicsIsNotNull() {
            addCriterion("friendspics is not null");
            return (Criteria) this;
        }

        public Criteria andFriendspicsEqualTo(String value) {
            addCriterion("friendspics =", value, "friendspics");
            return (Criteria) this;
        }

        public Criteria andFriendspicsNotEqualTo(String value) {
            addCriterion("friendspics <>", value, "friendspics");
            return (Criteria) this;
        }

        public Criteria andFriendspicsGreaterThan(String value) {
            addCriterion("friendspics >", value, "friendspics");
            return (Criteria) this;
        }

        public Criteria andFriendspicsGreaterThanOrEqualTo(String value) {
            addCriterion("friendspics >=", value, "friendspics");
            return (Criteria) this;
        }

        public Criteria andFriendspicsLessThan(String value) {
            addCriterion("friendspics <", value, "friendspics");
            return (Criteria) this;
        }

        public Criteria andFriendspicsLessThanOrEqualTo(String value) {
            addCriterion("friendspics <=", value, "friendspics");
            return (Criteria) this;
        }

        public Criteria andFriendspicsLike(String value) {
            addCriterion("friendspics like", value, "friendspics");
            return (Criteria) this;
        }

        public Criteria andFriendspicsNotLike(String value) {
            addCriterion("friendspics not like", value, "friendspics");
            return (Criteria) this;
        }

        public Criteria andFriendspicsIn(List<String> values) {
            addCriterion("friendspics in", values, "friendspics");
            return (Criteria) this;
        }

        public Criteria andFriendspicsNotIn(List<String> values) {
            addCriterion("friendspics not in", values, "friendspics");
            return (Criteria) this;
        }

        public Criteria andFriendspicsBetween(String value1, String value2) {
            addCriterion("friendspics between", value1, value2, "friendspics");
            return (Criteria) this;
        }

        public Criteria andFriendspicsNotBetween(String value1, String value2) {
            addCriterion("friendspics not between", value1, value2, "friendspics");
            return (Criteria) this;
        }

        public Criteria andWechatcontentIsNull() {
            addCriterion("wechatcontent is null");
            return (Criteria) this;
        }

        public Criteria andWechatcontentIsNotNull() {
            addCriterion("wechatcontent is not null");
            return (Criteria) this;
        }

        public Criteria andWechatcontentEqualTo(String value) {
            addCriterion("wechatcontent =", value, "wechatcontent");
            return (Criteria) this;
        }

        public Criteria andWechatcontentNotEqualTo(String value) {
            addCriterion("wechatcontent <>", value, "wechatcontent");
            return (Criteria) this;
        }

        public Criteria andWechatcontentGreaterThan(String value) {
            addCriterion("wechatcontent >", value, "wechatcontent");
            return (Criteria) this;
        }

        public Criteria andWechatcontentGreaterThanOrEqualTo(String value) {
            addCriterion("wechatcontent >=", value, "wechatcontent");
            return (Criteria) this;
        }

        public Criteria andWechatcontentLessThan(String value) {
            addCriterion("wechatcontent <", value, "wechatcontent");
            return (Criteria) this;
        }

        public Criteria andWechatcontentLessThanOrEqualTo(String value) {
            addCriterion("wechatcontent <=", value, "wechatcontent");
            return (Criteria) this;
        }

        public Criteria andWechatcontentLike(String value) {
            addCriterion("wechatcontent like", value, "wechatcontent");
            return (Criteria) this;
        }

        public Criteria andWechatcontentNotLike(String value) {
            addCriterion("wechatcontent not like", value, "wechatcontent");
            return (Criteria) this;
        }

        public Criteria andWechatcontentIn(List<String> values) {
            addCriterion("wechatcontent in", values, "wechatcontent");
            return (Criteria) this;
        }

        public Criteria andWechatcontentNotIn(List<String> values) {
            addCriterion("wechatcontent not in", values, "wechatcontent");
            return (Criteria) this;
        }

        public Criteria andWechatcontentBetween(String value1, String value2) {
            addCriterion("wechatcontent between", value1, value2, "wechatcontent");
            return (Criteria) this;
        }

        public Criteria andWechatcontentNotBetween(String value1, String value2) {
            addCriterion("wechatcontent not between", value1, value2, "wechatcontent");
            return (Criteria) this;
        }

        public Criteria andSmscontentIsNull() {
            addCriterion("smscontent is null");
            return (Criteria) this;
        }

        public Criteria andSmscontentIsNotNull() {
            addCriterion("smscontent is not null");
            return (Criteria) this;
        }

        public Criteria andSmscontentEqualTo(String value) {
            addCriterion("smscontent =", value, "smscontent");
            return (Criteria) this;
        }

        public Criteria andSmscontentNotEqualTo(String value) {
            addCriterion("smscontent <>", value, "smscontent");
            return (Criteria) this;
        }

        public Criteria andSmscontentGreaterThan(String value) {
            addCriterion("smscontent >", value, "smscontent");
            return (Criteria) this;
        }

        public Criteria andSmscontentGreaterThanOrEqualTo(String value) {
            addCriterion("smscontent >=", value, "smscontent");
            return (Criteria) this;
        }

        public Criteria andSmscontentLessThan(String value) {
            addCriterion("smscontent <", value, "smscontent");
            return (Criteria) this;
        }

        public Criteria andSmscontentLessThanOrEqualTo(String value) {
            addCriterion("smscontent <=", value, "smscontent");
            return (Criteria) this;
        }

        public Criteria andSmscontentLike(String value) {
            addCriterion("smscontent like", value, "smscontent");
            return (Criteria) this;
        }

        public Criteria andSmscontentNotLike(String value) {
            addCriterion("smscontent not like", value, "smscontent");
            return (Criteria) this;
        }

        public Criteria andSmscontentIn(List<String> values) {
            addCriterion("smscontent in", values, "smscontent");
            return (Criteria) this;
        }

        public Criteria andSmscontentNotIn(List<String> values) {
            addCriterion("smscontent not in", values, "smscontent");
            return (Criteria) this;
        }

        public Criteria andSmscontentBetween(String value1, String value2) {
            addCriterion("smscontent between", value1, value2, "smscontent");
            return (Criteria) this;
        }

        public Criteria andSmscontentNotBetween(String value1, String value2) {
            addCriterion("smscontent not between", value1, value2, "smscontent");
            return (Criteria) this;
        }

        public Criteria andMailtitleIsNull() {
            addCriterion("mailtitle is null");
            return (Criteria) this;
        }

        public Criteria andMailtitleIsNotNull() {
            addCriterion("mailtitle is not null");
            return (Criteria) this;
        }

        public Criteria andMailtitleEqualTo(String value) {
            addCriterion("mailtitle =", value, "mailtitle");
            return (Criteria) this;
        }

        public Criteria andMailtitleNotEqualTo(String value) {
            addCriterion("mailtitle <>", value, "mailtitle");
            return (Criteria) this;
        }

        public Criteria andMailtitleGreaterThan(String value) {
            addCriterion("mailtitle >", value, "mailtitle");
            return (Criteria) this;
        }

        public Criteria andMailtitleGreaterThanOrEqualTo(String value) {
            addCriterion("mailtitle >=", value, "mailtitle");
            return (Criteria) this;
        }

        public Criteria andMailtitleLessThan(String value) {
            addCriterion("mailtitle <", value, "mailtitle");
            return (Criteria) this;
        }

        public Criteria andMailtitleLessThanOrEqualTo(String value) {
            addCriterion("mailtitle <=", value, "mailtitle");
            return (Criteria) this;
        }

        public Criteria andMailtitleLike(String value) {
            addCriterion("mailtitle like", value, "mailtitle");
            return (Criteria) this;
        }

        public Criteria andMailtitleNotLike(String value) {
            addCriterion("mailtitle not like", value, "mailtitle");
            return (Criteria) this;
        }

        public Criteria andMailtitleIn(List<String> values) {
            addCriterion("mailtitle in", values, "mailtitle");
            return (Criteria) this;
        }

        public Criteria andMailtitleNotIn(List<String> values) {
            addCriterion("mailtitle not in", values, "mailtitle");
            return (Criteria) this;
        }

        public Criteria andMailtitleBetween(String value1, String value2) {
            addCriterion("mailtitle between", value1, value2, "mailtitle");
            return (Criteria) this;
        }

        public Criteria andMailtitleNotBetween(String value1, String value2) {
            addCriterion("mailtitle not between", value1, value2, "mailtitle");
            return (Criteria) this;
        }

        public Criteria andMailbodyIsNull() {
            addCriterion("mailbody is null");
            return (Criteria) this;
        }

        public Criteria andMailbodyIsNotNull() {
            addCriterion("mailbody is not null");
            return (Criteria) this;
        }

        public Criteria andMailbodyEqualTo(String value) {
            addCriterion("mailbody =", value, "mailbody");
            return (Criteria) this;
        }

        public Criteria andMailbodyNotEqualTo(String value) {
            addCriterion("mailbody <>", value, "mailbody");
            return (Criteria) this;
        }

        public Criteria andMailbodyGreaterThan(String value) {
            addCriterion("mailbody >", value, "mailbody");
            return (Criteria) this;
        }

        public Criteria andMailbodyGreaterThanOrEqualTo(String value) {
            addCriterion("mailbody >=", value, "mailbody");
            return (Criteria) this;
        }

        public Criteria andMailbodyLessThan(String value) {
            addCriterion("mailbody <", value, "mailbody");
            return (Criteria) this;
        }

        public Criteria andMailbodyLessThanOrEqualTo(String value) {
            addCriterion("mailbody <=", value, "mailbody");
            return (Criteria) this;
        }

        public Criteria andMailbodyLike(String value) {
            addCriterion("mailbody like", value, "mailbody");
            return (Criteria) this;
        }

        public Criteria andMailbodyNotLike(String value) {
            addCriterion("mailbody not like", value, "mailbody");
            return (Criteria) this;
        }

        public Criteria andMailbodyIn(List<String> values) {
            addCriterion("mailbody in", values, "mailbody");
            return (Criteria) this;
        }

        public Criteria andMailbodyNotIn(List<String> values) {
            addCriterion("mailbody not in", values, "mailbody");
            return (Criteria) this;
        }

        public Criteria andMailbodyBetween(String value1, String value2) {
            addCriterion("mailbody between", value1, value2, "mailbody");
            return (Criteria) this;
        }

        public Criteria andMailbodyNotBetween(String value1, String value2) {
            addCriterion("mailbody not between", value1, value2, "mailbody");
            return (Criteria) this;
        }

        public Criteria andCopycontentIsNull() {
            addCriterion("copycontent is null");
            return (Criteria) this;
        }

        public Criteria andCopycontentIsNotNull() {
            addCriterion("copycontent is not null");
            return (Criteria) this;
        }

        public Criteria andCopycontentEqualTo(String value) {
            addCriterion("copycontent =", value, "copycontent");
            return (Criteria) this;
        }

        public Criteria andCopycontentNotEqualTo(String value) {
            addCriterion("copycontent <>", value, "copycontent");
            return (Criteria) this;
        }

        public Criteria andCopycontentGreaterThan(String value) {
            addCriterion("copycontent >", value, "copycontent");
            return (Criteria) this;
        }

        public Criteria andCopycontentGreaterThanOrEqualTo(String value) {
            addCriterion("copycontent >=", value, "copycontent");
            return (Criteria) this;
        }

        public Criteria andCopycontentLessThan(String value) {
            addCriterion("copycontent <", value, "copycontent");
            return (Criteria) this;
        }

        public Criteria andCopycontentLessThanOrEqualTo(String value) {
            addCriterion("copycontent <=", value, "copycontent");
            return (Criteria) this;
        }

        public Criteria andCopycontentLike(String value) {
            addCriterion("copycontent like", value, "copycontent");
            return (Criteria) this;
        }

        public Criteria andCopycontentNotLike(String value) {
            addCriterion("copycontent not like", value, "copycontent");
            return (Criteria) this;
        }

        public Criteria andCopycontentIn(List<String> values) {
            addCriterion("copycontent in", values, "copycontent");
            return (Criteria) this;
        }

        public Criteria andCopycontentNotIn(List<String> values) {
            addCriterion("copycontent not in", values, "copycontent");
            return (Criteria) this;
        }

        public Criteria andCopycontentBetween(String value1, String value2) {
            addCriterion("copycontent between", value1, value2, "copycontent");
            return (Criteria) this;
        }

        public Criteria andCopycontentNotBetween(String value1, String value2) {
            addCriterion("copycontent not between", value1, value2, "copycontent");
            return (Criteria) this;
        }

        public Criteria andCopyhintIsNull() {
            addCriterion("copyhint is null");
            return (Criteria) this;
        }

        public Criteria andCopyhintIsNotNull() {
            addCriterion("copyhint is not null");
            return (Criteria) this;
        }

        public Criteria andCopyhintEqualTo(String value) {
            addCriterion("copyhint =", value, "copyhint");
            return (Criteria) this;
        }

        public Criteria andCopyhintNotEqualTo(String value) {
            addCriterion("copyhint <>", value, "copyhint");
            return (Criteria) this;
        }

        public Criteria andCopyhintGreaterThan(String value) {
            addCriterion("copyhint >", value, "copyhint");
            return (Criteria) this;
        }

        public Criteria andCopyhintGreaterThanOrEqualTo(String value) {
            addCriterion("copyhint >=", value, "copyhint");
            return (Criteria) this;
        }

        public Criteria andCopyhintLessThan(String value) {
            addCriterion("copyhint <", value, "copyhint");
            return (Criteria) this;
        }

        public Criteria andCopyhintLessThanOrEqualTo(String value) {
            addCriterion("copyhint <=", value, "copyhint");
            return (Criteria) this;
        }

        public Criteria andCopyhintLike(String value) {
            addCriterion("copyhint like", value, "copyhint");
            return (Criteria) this;
        }

        public Criteria andCopyhintNotLike(String value) {
            addCriterion("copyhint not like", value, "copyhint");
            return (Criteria) this;
        }

        public Criteria andCopyhintIn(List<String> values) {
            addCriterion("copyhint in", values, "copyhint");
            return (Criteria) this;
        }

        public Criteria andCopyhintNotIn(List<String> values) {
            addCriterion("copyhint not in", values, "copyhint");
            return (Criteria) this;
        }

        public Criteria andCopyhintBetween(String value1, String value2) {
            addCriterion("copyhint between", value1, value2, "copyhint");
            return (Criteria) this;
        }

        public Criteria andCopyhintNotBetween(String value1, String value2) {
            addCriterion("copyhint not between", value1, value2, "copyhint");
            return (Criteria) this;
        }

        public Criteria andQrcodepicIsNull() {
            addCriterion("qrcodepic is null");
            return (Criteria) this;
        }

        public Criteria andQrcodepicIsNotNull() {
            addCriterion("qrcodepic is not null");
            return (Criteria) this;
        }

        public Criteria andQrcodepicEqualTo(String value) {
            addCriterion("qrcodepic =", value, "qrcodepic");
            return (Criteria) this;
        }

        public Criteria andQrcodepicNotEqualTo(String value) {
            addCriterion("qrcodepic <>", value, "qrcodepic");
            return (Criteria) this;
        }

        public Criteria andQrcodepicGreaterThan(String value) {
            addCriterion("qrcodepic >", value, "qrcodepic");
            return (Criteria) this;
        }

        public Criteria andQrcodepicGreaterThanOrEqualTo(String value) {
            addCriterion("qrcodepic >=", value, "qrcodepic");
            return (Criteria) this;
        }

        public Criteria andQrcodepicLessThan(String value) {
            addCriterion("qrcodepic <", value, "qrcodepic");
            return (Criteria) this;
        }

        public Criteria andQrcodepicLessThanOrEqualTo(String value) {
            addCriterion("qrcodepic <=", value, "qrcodepic");
            return (Criteria) this;
        }

        public Criteria andQrcodepicLike(String value) {
            addCriterion("qrcodepic like", value, "qrcodepic");
            return (Criteria) this;
        }

        public Criteria andQrcodepicNotLike(String value) {
            addCriterion("qrcodepic not like", value, "qrcodepic");
            return (Criteria) this;
        }

        public Criteria andQrcodepicIn(List<String> values) {
            addCriterion("qrcodepic in", values, "qrcodepic");
            return (Criteria) this;
        }

        public Criteria andQrcodepicNotIn(List<String> values) {
            addCriterion("qrcodepic not in", values, "qrcodepic");
            return (Criteria) this;
        }

        public Criteria andQrcodepicBetween(String value1, String value2) {
            addCriterion("qrcodepic between", value1, value2, "qrcodepic");
            return (Criteria) this;
        }

        public Criteria andQrcodepicNotBetween(String value1, String value2) {
            addCriterion("qrcodepic not between", value1, value2, "qrcodepic");
            return (Criteria) this;
        }

        public Criteria andContactusinfoIsNull() {
            addCriterion("contactusinfo is null");
            return (Criteria) this;
        }

        public Criteria andContactusinfoIsNotNull() {
            addCriterion("contactusinfo is not null");
            return (Criteria) this;
        }

        public Criteria andContactusinfoEqualTo(String value) {
            addCriterion("contactusinfo =", value, "contactusinfo");
            return (Criteria) this;
        }

        public Criteria andContactusinfoNotEqualTo(String value) {
            addCriterion("contactusinfo <>", value, "contactusinfo");
            return (Criteria) this;
        }

        public Criteria andContactusinfoGreaterThan(String value) {
            addCriterion("contactusinfo >", value, "contactusinfo");
            return (Criteria) this;
        }

        public Criteria andContactusinfoGreaterThanOrEqualTo(String value) {
            addCriterion("contactusinfo >=", value, "contactusinfo");
            return (Criteria) this;
        }

        public Criteria andContactusinfoLessThan(String value) {
            addCriterion("contactusinfo <", value, "contactusinfo");
            return (Criteria) this;
        }

        public Criteria andContactusinfoLessThanOrEqualTo(String value) {
            addCriterion("contactusinfo <=", value, "contactusinfo");
            return (Criteria) this;
        }

        public Criteria andContactusinfoLike(String value) {
            addCriterion("contactusinfo like", value, "contactusinfo");
            return (Criteria) this;
        }

        public Criteria andContactusinfoNotLike(String value) {
            addCriterion("contactusinfo not like", value, "contactusinfo");
            return (Criteria) this;
        }

        public Criteria andContactusinfoIn(List<String> values) {
            addCriterion("contactusinfo in", values, "contactusinfo");
            return (Criteria) this;
        }

        public Criteria andContactusinfoNotIn(List<String> values) {
            addCriterion("contactusinfo not in", values, "contactusinfo");
            return (Criteria) this;
        }

        public Criteria andContactusinfoBetween(String value1, String value2) {
            addCriterion("contactusinfo between", value1, value2, "contactusinfo");
            return (Criteria) this;
        }

        public Criteria andContactusinfoNotBetween(String value1, String value2) {
            addCriterion("contactusinfo not between", value1, value2, "contactusinfo");
            return (Criteria) this;
        }

        public Criteria andContactusnumberIsNull() {
            addCriterion("contactusnumber is null");
            return (Criteria) this;
        }

        public Criteria andContactusnumberIsNotNull() {
            addCriterion("contactusnumber is not null");
            return (Criteria) this;
        }

        public Criteria andContactusnumberEqualTo(String value) {
            addCriterion("contactusnumber =", value, "contactusnumber");
            return (Criteria) this;
        }

        public Criteria andContactusnumberNotEqualTo(String value) {
            addCriterion("contactusnumber <>", value, "contactusnumber");
            return (Criteria) this;
        }

        public Criteria andContactusnumberGreaterThan(String value) {
            addCriterion("contactusnumber >", value, "contactusnumber");
            return (Criteria) this;
        }

        public Criteria andContactusnumberGreaterThanOrEqualTo(String value) {
            addCriterion("contactusnumber >=", value, "contactusnumber");
            return (Criteria) this;
        }

        public Criteria andContactusnumberLessThan(String value) {
            addCriterion("contactusnumber <", value, "contactusnumber");
            return (Criteria) this;
        }

        public Criteria andContactusnumberLessThanOrEqualTo(String value) {
            addCriterion("contactusnumber <=", value, "contactusnumber");
            return (Criteria) this;
        }

        public Criteria andContactusnumberLike(String value) {
            addCriterion("contactusnumber like", value, "contactusnumber");
            return (Criteria) this;
        }

        public Criteria andContactusnumberNotLike(String value) {
            addCriterion("contactusnumber not like", value, "contactusnumber");
            return (Criteria) this;
        }

        public Criteria andContactusnumberIn(List<String> values) {
            addCriterion("contactusnumber in", values, "contactusnumber");
            return (Criteria) this;
        }

        public Criteria andContactusnumberNotIn(List<String> values) {
            addCriterion("contactusnumber not in", values, "contactusnumber");
            return (Criteria) this;
        }

        public Criteria andContactusnumberBetween(String value1, String value2) {
            addCriterion("contactusnumber between", value1, value2, "contactusnumber");
            return (Criteria) this;
        }

        public Criteria andContactusnumberNotBetween(String value1, String value2) {
            addCriterion("contactusnumber not between", value1, value2, "contactusnumber");
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
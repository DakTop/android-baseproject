package com.runtop.android.client.bean;

import java.util.Date;

/**
 * Auto-generated: 2022-05-24 22:4:49
 *
 * @author bejson.com (i@bejson.com)
 * @website http://www.bejson.com/java2pojo/
 */
public class LoginResp {

    private String agent_code;
    private String client_id;
    private String merchant_id;
    private String client_key;
    private String login_time;
    private String merchant_code;
    private String merchant_name;
    private String platform_code;
    private String version;
    private int role;
    private int is_manager;
    private int employee_id;
    private String employee_name;
    private int theme;//系统主题 0旧版 1新版 2新版连锁
    public int getTheme() {
        return theme;
    }
    public void setTheme(int theme) {
        this.theme = theme;
    }
    private int isInvalid;//是否在有效期
    private String activationTime;//激活开始时间
    private String invalidEndTime;//有效截止时间


    public String getMerchant_id() {
        return merchant_id;
    }

    public void setMerchant_id(String merchant_id) {
        this.merchant_id = merchant_id;
    }

    public void setAgent_code(String agent_code) {
        this.agent_code = agent_code;
    }

    public String getAgent_code() {
        return agent_code;
    }

    public void setClient_id(String client_id) {
        this.client_id = client_id;
    }

    public String getClient_id() {
        return client_id;
    }

    public void setClient_key(String client_key) {
        this.client_key = client_key;
    }

    public String getClient_key() {
        return client_key;
    }

    public void setLogin_time(String login_time) {
        this.login_time = login_time;
    }

    public String getLogin_time() {
        return login_time;
    }

    public void setMerchant_code(String merchant_code) {
        this.merchant_code = merchant_code;
    }

    public String getMerchant_code() {
        return merchant_code;
    }

    public void setMerchant_name(String merchant_name) {
        this.merchant_name = merchant_name;
    }

    public String getMerchant_name() {
        return merchant_name;
    }

    public int getEmployee_id() {
        return employee_id;
    }

    public void setEmployee_id(int employee_id) {
        this.employee_id = employee_id;
    }

    public String getEmployee_name() {
        return employee_name;
    }

    public void setEmployee_name(String employee_name) {
        this.employee_name = employee_name;
    }

    public void setPlatform_code(String platform_code) {
        this.platform_code = platform_code;
    }

    public String getPlatform_code() {
        return platform_code;
    }

    public void setRole(int role) {
        this.role = role;
    }

    public int getRole() {
        return role;
    }

    public int getIs_manager() {
        return is_manager;
    }

    public void setIs_manager(int is_manager) {
        this.is_manager = is_manager;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public int getIsInvalid() {
        return isInvalid;
    }

    public void setIsInvalid(int isInvalid) {
        this.isInvalid = isInvalid;
    }

    public String getActivationTime() {
        return activationTime;
    }

    public void setActivationTime(String activationTime) {
        this.activationTime = activationTime;
    }

    public String getInvalidEndTime() {
        return invalidEndTime;
    }

    public void setInvalidEndTime(String invalidEndTime) {
        this.invalidEndTime = invalidEndTime;
    }
}
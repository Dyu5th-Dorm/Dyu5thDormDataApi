package com.github.nutt1101;

import lombok.Getter;

@Getter
public enum Keys {
    BODY_LOGIN_ID("login_id"),
    BODY_LOGIN_PWD("login_pwd"),

    URL_LOGIN("https://adm_ra.dyu.edu.tw/entrance/save_id.php"),
    URL_DATA_SOURCE("https://adm_ra.dyu.edu.tw/sa/dorm/dorm_qry/kernel/kdorm_space.php?page=MTA5NA=="),

    JSON_DATA("data"),

    JSON_DATA_ROW_NUMBER("row_no"),
    JSON_DATA_SEMESTER_YEAR("smye"),
    JSON_DATA_SEMESTER("smty"),
    JSON_DATA_EDU("edu"),
    JSON_DATA_DEPARTMENT("dept"),
    JSON_DATA_STUDENT_NUMBER("stno"),
    JSON_DATA_STUDENT_NAME("cname"),
    JSON_DATA_BED_ID("dormno"),
    JSON_DATA_SEX("sex"),
    JSON_DATA_TELEPHONE("tel"),
    JSON_DATA_CITIZENSHIP("nation"),
    JSON_DATA_SQL("space"),
    JSON_DATA_YEAR("yesc"),
    JSON_DATA_KEYWORDS("keywords");

    private final String val;

    Keys(String val) {
        this.val = val;
    }
}

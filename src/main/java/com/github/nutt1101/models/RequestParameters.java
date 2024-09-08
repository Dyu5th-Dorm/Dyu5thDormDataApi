package com.github.nutt1101.models;

import com.github.nutt1101.Keys;
import lombok.Builder;
import lombok.ToString;
import org.json.JSONArray;
import org.json.JSONObject;

@Builder
@ToString
public class RequestParameters implements Parameters{
    private String semesterYear;
    private String semester;
    private Dormitory dormitory;

    @Override
    public String toRequestBody() {
        JSONArray array = new JSONArray();

        JSONObject json = new JSONObject();
        json.put("col_name", Keys.JSON_DATA_SEMESTER_YEAR.getVal());
        json.put("col_search_value", this.semesterYear);

        array.put(json);

        json = new JSONObject();
        json.put("col_name", Keys.JSON_DATA_SEMESTER.getVal());
        json.put("col_search_value", this.semester);

        array.put(json);

        json = new JSONObject();
        json.put("col_name", Keys.JSON_DATA_SQL.getVal());
        json.put("col_search_value", this.dormitory.getId());

        array.put(json);

        return array.toString();
    }
}

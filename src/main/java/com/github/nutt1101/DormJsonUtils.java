package com.github.nutt1101;

import com.github.nutt1101.models.*;
import lombok.Getter;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

@Getter
public class DormJsonUtils {
    private final JSONObject jsonObject;
    private JSONArray bedJsonArray;
    private final Dormitory dormitory;

    public DormJsonUtils(String jsonString, Dormitory dormitory) {
        System.out.println(jsonString);
        this.dormitory = dormitory;
        this.jsonObject = new JSONObject(jsonString);
        this.prepareRoomJsonObject();
    }

    void prepareRoomJsonObject() {
        if (
                this.jsonObject.isNull(Keys.JSON_DATA.getVal()) ||
                !this.jsonObject.has(Keys.JSON_DATA.getVal())
        ) {
            throw new RuntimeException("can not find bed data");
        }

        this.bedJsonArray = this.jsonObject.getJSONArray(
                Keys.JSON_DATA.getVal()
        );
    }

    List<Bed> parse() {
        List<Bed> beds = new ArrayList<>();

        for (int i = 0; i < bedJsonArray.length(); i++) {
            JSONObject bedJson = bedJsonArray.getJSONObject(i);

            String bedId = bedJson.getString(Keys.JSON_DATA_BED_ID.getVal());
            int dormId = Character.getNumericValue(bedId.charAt(0));
            if (dormId != this.dormitory.getId()) continue;

            Student student;
            Bed bed;

            if (bedJson.isNull(Keys.JSON_DATA_STUDENT_NUMBER.getVal())) {
                student = null;
            } else {
                String department = bedJson.getString(Keys.JSON_DATA_DEPARTMENT.getVal()),
                        studentId = bedJson.getString(Keys.JSON_DATA_STUDENT_NUMBER.getVal()),
                        name = bedJson.getString(Keys.JSON_DATA_STUDENT_NAME.getVal()),
                        sex = bedJson.getString(Keys.JSON_DATA_SEX.getVal()),
                        citizenship = bedJson.getString(Keys.JSON_DATA_CITIZENSHIP.getVal());

                student = Student.builder()
                        .id(studentId)
                        .major(department)
                        .citizenship(citizenship)
                        .sex(sex.equals("男") ? "M" : "F")
                        .name(name)
                        .build();
            }

            String dormStatus = bedJson.getString(Keys.JSON_DATA_DORM_STATUS.getVal());
            bed = Bed.builder()
                    .id(bedId)
                    .student(student)
                    .status(dormStatus)
                    .build();

            beds.add(bed);
        }

        return beds;
    }
}

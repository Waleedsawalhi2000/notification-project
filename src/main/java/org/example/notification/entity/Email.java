package org.example.notification.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Email {
    private String subject;
    private String to;
    private String username;
    private Long date;
    private String location;
    private Integer total;


    public Map<String, String> toMap() {
        Map<String, String> map = new HashMap<>();
        map.put("username", username);
        map.put("date", new Date(date).toString());
        map.put("location", location);
        map.put("total", total.toString());
        return map;
    }
}

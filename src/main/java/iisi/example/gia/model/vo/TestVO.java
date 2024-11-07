package iisi.example.gia.model.vo;

import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Getter
@Setter
public class TestVO {
    private String name;
    private Date date;
}

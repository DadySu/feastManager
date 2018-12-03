package base;

import lombok.*;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * @description:
 * @author liub
 * @date 2018/12/2 12:33
 * @version 1.0
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public abstract class BaseModel implements Serializable {

    private Long id;
    private Timestamp createTime;
    private String creator;
    private Timestamp updateTime;
    private String updator;
    private String auditor;
    private Long version;
    private String sort;//desc asc

}

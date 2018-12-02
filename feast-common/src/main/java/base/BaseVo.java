package base;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @Description:
 * @Author mengfanzhu
 * @Date 9/27/17 16:48
 * @Version 1.0
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BaseVo {
    private String id;
    private String createTime;
    private String creator;
    private String updateTime;
    private String updator;
    private String auditor;
    private String version;
}

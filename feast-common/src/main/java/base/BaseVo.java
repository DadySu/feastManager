package base;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
public class BaseVo {
    private String id;
    private String createTime;
    private String creator;
    private String updateTime;
    private String updator;
    private String auditor;
    private String version;
}
